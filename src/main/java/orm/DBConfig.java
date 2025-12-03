package orm;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    private static Properties prop = new Properties();
    
    // 정적 초기화 블록 - 클래스 로드될 때 한 번 실행
    static {
        try {
            // resources 폴더의 config.properties 읽기
            InputStream input = DBConfig.class.getClassLoader()
                .getResourceAsStream("config.properties");
            
            if (input == null) {
                System.out.println("config.properties 파일을 찾을 수 없습니다!");
                throw new NullPointerException("config.properties 파일이 없습니다");
            }
            
            prop.load(input);
            input.close();
            
            System.out.println("✓ config.properties 파일 로드 성공!");
            
        } catch (Exception e) {
            System.err.println("❌ config.properties 파일 읽기 실패!");
            e.printStackTrace();
        }
    }
    
    // DB URL 가져오기
    public static String getUrl() {
        return prop.getProperty("db.url");
    }
    
    // DB 사용자명 가져오기
    public static String getUser() {
        return prop.getProperty("db.user");
    }
    
    // DB 비밀번호 가져오기
    public static String getPassword() {
        return prop.getProperty("db.password");
    }
    
    // DB 드라이버 가져오기
    public static String getDriver() {
        return prop.getProperty("db.driver");
    }
}
