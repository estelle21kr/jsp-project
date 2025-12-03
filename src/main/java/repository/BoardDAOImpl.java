package repository;

import domain.Board;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BoardDAOImpl implements BoardDAO {
    
    private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
    private static SqlSessionFactory sqlSessionFactory;
    
    // 정적 초기화 블록 - 프로그램 시작 시 1번만 실행
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            
            if (inputStream == null) {
                throw new IOException("mybatis-config.xml 파일을 찾을 수 없습니다!");
            }
            
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            log.info("✓ MyBatis SqlSessionFactory 초기화 성공!");
            
        } catch (IOException e) {
            log.error("❌ MyBatis 설정 파일 로드 실패!", e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
    // SqlSession 가져오기
    private SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
    
    @Override
    public List<Board> selectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            return sqlSession.selectList("board.selectAll");
        } finally {
            sqlSession.close();
        }
    }
    
    @Override
    public Board selectById(int boardId) {
        SqlSession sqlSession = getSqlSession();
        try {
            return sqlSession.selectOne("board.selectById", boardId);
        } finally {
            sqlSession.close();
        }
    }
    
    @Override
    public int insert(Board board) {
        SqlSession sqlSession = getSqlSession();
        try {
            int result = sqlSession.insert("board.insert", board);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }
    
    @Override
    public int update(Board board) {
        SqlSession sqlSession = getSqlSession();
        try {
            int result = sqlSession.update("board.update", board);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }
    
    @Override
    public int delete(int boardId) {
        SqlSession sqlSession = getSqlSession();
        try {
            int result = sqlSession.delete("board.delete", boardId);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }
}
