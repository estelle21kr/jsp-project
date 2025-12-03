# 🎓 JSP Board Project with MyBatis

> MyBatis를 활용한 **동적 SQL 기반 게시판 프로젝트**
> 
> JSP/Servlet + MyBatis + MySQL로 구현한 풀스택 웹 애플리케이션

---

## 📋 프로젝트 개요

이 프로젝트는 **Java 기반의 게시판 시스템**으로, MyBatis ORM을 사용하여 데이터베이스와 효율적으로 통신합니다.
MVC 패턴을 따르며, 확장 가능하고 유지보수하기 쉬운 구조로 설계되었습니다.

### ✨ 주요 특징

- ✅ **MyBatis 기반 데이터 접근 계층** - SQL 쿼리 관리 용이
- ✅ **MVC 아키텍처** - Controller, Service, Repository 계층 분리
- ✅ **Log4j2 로깅** - 애플리케이션 이벤트 추적
- ✅ **JSTL & EL** - 동적 JSP 템플릿
- ✅ **외부 설정 관리** - config.properties로 민감한 정보 보호

---

## 🛠️ 기술 스택

### Backend
- **Java 11+** - 프로그래밍 언어
- **JSP/Servlet** - 웹 프레임워크
- **MyBatis 3.5.10** - ORM 프레임워크
- **Apache Tomcat 9.0** - 웹 서버

### Database
- **MySQL 8.0.33** - 관계형 데이터베이스
- **MySQL Connector/J 8.0.33** - JDBC 드라이버

### 로깅 & 유틸
- **Log4j2 2.18.0** - 로깅 라이브러리
- **SLF4J 1.7.36** - 로깅 파사드
- **JSTL 1.2** - JSP 표준 태그 라이브러리
- **json-simple 1.1.1** - JSON 처리

---

## 📁 프로젝트 구조

```
jsp_project2/
├── src/main/
│   ├── java/
│   │   ├── controller/
│   │   │   └── BoardController.java       # 요청 처리
│   │   ├── service/
│   │   │   ├── BoardService.java          # 비즈니스 로직 인터페이스
│   │   │   └── BoardServiceImpl.java       # 비즈니스 로직 구현
│   │   ├── repository/
│   │   │   ├── BoardDAO.java              # 데이터 접근 인터페이스
│   │   │   └── BoardDAOImpl.java           # MyBatis 구현
│   │   ├── domain/
│   │   │   └── Board.java                 # DTO (게시물 데이터)
│   │   ├── mapper/
│   │   │   └── boardMapper.xml            # SQL 매핑 (작성 예정)
│   │   └── orm/
│   │       ├── DBConfig.java              # DB 설정 관리
│   │       └── (기타 설정 클래스)
│   ├── resources/
│   │   ├── config.properties              # 환경설정 (로컬 생성)
│   │   ├── mybatis-config.xml             # MyBatis 설정
│   │   └── log4j2.xml                     # 로깅 설정 (WEB-INF/에 위치)
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── lib/                       # 의존성 라이브러리
│       │   ├── web.xml                    # 배포 설정자
│       │   ├── log4j2.xml                 # Log4j2 설정
│       │   └── views/                     # JSP 파일들
│       └── index.jsp                      # 시작 페이지
├── build/                                 # 컴파일 결과물 (자동 생성)
├── .gitignore                             # Git 무시 파일 목록
├── .classpath                             # Eclipse 클래스패스
├── .project                               # Eclipse 프로젝트 설정
└── README.md                              # 이 파일
```

---

## 🚀 빠른 시작

### 필수 요구사항

- ✅ Java JDK 11 이상
- ✅ Apache Tomcat 9.0
- ✅ MySQL 8.0 이상
- ✅ Eclipse IDE (또는 IntelliJ IDEA)
- ✅ Git

### 설치 및 설정

#### 1️⃣ 저장소 클론

```bash
git clone https://github.com/estelle21kr/jsp-project.git
cd jsp-project2
```

#### 2️⃣ 데이터베이스 설정

MySQL에서 데이터베이스 생성:

```sql
CREATE DATABASE jsp_db;

USE jsp_db;

CREATE TABLE board (
    board_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    writer VARCHAR(50) NOT NULL,
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

#### 3️⃣ 설정 파일 생성

`src/main/resources/config.properties` 파일을 생성하고 다음 내용 입력:

```properties
# Database Configuration
db.url=jdbc:mysql://localhost:3306/jsp_db
db.user=root
db.password=your_password_here
db.driver=com.mysql.cj.jdbc.Driver
```

**주의:** `your_password_here`를 실제 MySQL 비밀번호로 변경하세요!

#### 4️⃣ Eclipse에서 프로젝트 임포트

```
File → Import → General → Existing Projects into Workspace
→ jsp-project 폴더 선택 → Finish
```

#### 5️⃣ 서버 설정

```
프로젝트 우클릭 → Properties → Project Facets
→ Dynamic Web Module 버전 확인 (4.0 권장)
→ Runtimes 탭에서 Tomcat 9.0 선택
```

#### 6️⃣ 서버 실행

```
프로젝트 우클릭 → Run As → Run on Server
→ Tomcat 9.0 선택 → Finish
```

브라우저에서 `http://localhost:8080/jsp_project2` 접속 ✓

---

## 📚 주요 클래스 설명

### Board.java (VO)
```java
게시물 정보를 담는 Value Object
- boardId: 게시물 ID
- title: 제목
- content: 내용
- writer: 작성자
- regDate: 등록 날짜
```

### BoardDAO & BoardDAOImpl
```java
데이터베이스 접근 계층
- selectAll(): 전체 게시물 조회
- selectById(int id): 특정 게시물 조회
- insert(Board board): 게시물 등록
- update(Board board): 게시물 수정
- delete(int id): 게시물 삭제
```

### BoardService & BoardServiceImpl
```java
비즈니스 로직 계층
DAO를 호출하여 비즈니스 규칙 적용
```

### BoardController
```java
요청 처리 계층
클라이언트 요청을 받아 Service 호출
결과를 View로 전달
```

---

## 🔐 보안 설정

### config.properties 보호

**민감한 정보는 절대 GitHub에 올리지 않습니다:**

```bash
# .gitignore에 이미 포함됨
config.properties
```

로컬 환경에서만 `config.properties`를 생성하여 사용합니다.

### 환경별 설정

**개발 환경 (로컬)**
```properties
db.url=jdbc:mysql://localhost:3306/jsp_db
db.user=root
db.password=1234
```

**프로덕션 환경 (서버)**
```properties
db.url=jdbc:mysql://prod-server:3306/jsp_db
db.user=prod_user
db.password=${PROD_PASSWORD}  # 환경변수로 관리
```

---

## 🧪 테스트 및 디버깅

### Log4j2 로깅 확인

```java
private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);

log.info("✓ 게시물 조회 성공");
log.error("❌ 데이터베이스 연결 실패", exception);
```

### MyBatis 쿼리 확인

`log4j2.xml` 설정으로 SQL 쿼리 로깅 활성화:

```xml
<Logger name="org.apache.ibatis" level="DEBUG" />
```

---

## 🤝 개발 워크플로우

### 1. 기능 추가

```bash
git checkout -b feature/새로운-기능
# 코드 작성 및 테스트
git add .
git commit -m "feat: 새로운 기능 추가"
git push origin feature/새로운-기능
```

### 2. 변경사항 반영

```bash
cd jsp_project2
git add .
git commit -m "Update: 설명"
git push origin master
```

---

## 📝 MyBatis Mapper 작성 예시

`src/main/java/mapper/boardMapper.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="board">
    <!-- 전체 조회 -->
    <select id="selectAll" resultType="domain.Board">
        SELECT * FROM board ORDER BY reg_date DESC
    </select>
    
    <!-- 상세 조회 -->
    <select id="selectById" parameterType="int" resultType="domain.Board">
        SELECT * FROM board WHERE board_id = #{boardId}
    </select>
    
    <!-- 등록 -->
    <insert id="insert" parameterType="domain.Board">
        INSERT INTO board (title, content, writer)
        VALUES (#{title}, #{content}, #{writer})
    </insert>
    
    <!-- 수정 -->
    <update id="update" parameterType="domain.Board">
        UPDATE board 
        SET title = #{title}, content = #{content}
        WHERE board_id = #{boardId}
    </update>
    
    <!-- 삭제 -->
    <delete id="delete" parameterType="int">
        DELETE FROM board WHERE board_id = #{boardId}
    </delete>
</mapper>
```

---

## 🐛 일반적인 오류 및 해결법

### 1. "config.properties 파일을 찾을 수 없습니다"
```
✓ src/main/resources/ 폴더에 config.properties가 있는지 확인
✓ Eclipse → Project → Clean 실행
```

### 2. "Cannot find symbol: class SqlSession"
```
✓ WEB-INF/lib/ 폴더에 mybatis-3.5.10.jar가 있는지 확인
✓ Eclipse 프로젝트 우클릭 → Build Path → Configure Build Path에서 라이브러리 확인
```

### 3. "JDBC 드라이버를 찾을 수 없습니다"
```
✓ WEB-INF/lib/mysql-connector-j-8.0.33.jar 확인
✓ com.mysql.cj.jdbc.Driver 클래스명 확인
```

### 4. MySQL 연결 오류
```
✓ MySQL이 실행 중인지 확인
✓ 비밀번호 확인
✓ db.url 형식 확인: jdbc:mysql://localhost:3306/jsp_db
```

---

## 📖 추가 학습 자료

- [MyBatis 공식 문서](https://mybatis.org/mybatis-3/ko/)
- [JSP/Servlet 튜토리얼](https://www.oracle.com/java/technologies/jsp.html)
- [MySQL 8.0 문서](https://dev.mysql.com/doc/mysql-8-0-en/)
- [Apache Tomcat 설정](https://tomcat.apache.org/)

---

## 📞 문제 해결

**문제가 있으신가요?**

1. README의 "일반적인 오류" 섹션 확인
2. 로그 파일 검토 (`build/` 폴더의 로그)
3. Console 탭에서 오류 메시지 확인
4. GitHub Issues에서 유사 문제 검색

---

## 📄 라이선스

이 프로젝트는 **MIT License** 하에 배포됩니다.

---

## 🙌 기여하기

버그 리포트, 기능 제안, 풀 리퀘스트는 언제든 환영합니다!

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 👨‍💻 작성자

**estelle21kr** - JSP 학습 프로젝트

---

## ⭐ 프로젝트 상태

🚧 **진행 중** - MyBatis Mapper 구현 및 UI 개발 예정

**작성 날짜:** 2025년 12월 3일
