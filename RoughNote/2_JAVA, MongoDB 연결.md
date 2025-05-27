# 2_JAVA, MongoDB 연결

![Image](https://github.com/user-attachments/assets/7841e262-6a8d-4d80-9eda-26ca8e7a86c4)

## 📚 1. CRUD란?
CRUD는 데이터를 다룰 때의 네 가지 기본 기능을 의미하는 약어

| 알파벳   | 뜻      | 역할 (예시)                   |
| ----- | ------ | ------------------------- |
| **C** | Create | 데이터 **생성** (ex. 회원가입)     |
| **R** | Read   | 데이터 **조회** (ex. 회원 정보 보기) |
| **U** | Update | 데이터 **수정** (ex. 비밀번호 변경)  |
| **D** | Delete | 데이터 **삭제** (ex. 회원 탈퇴)    |

```java
// Create
INSERT INTO users (id, name) VALUES ('abc', '홍길동');

// Read
SELECT * FROM users;

// Update
UPDATE users SET name = '김철수' WHERE id = 'abc';

// Delete
DELETE FROM users WHERE id = 'abc';
```

## 🧩 2. JDBC란?
- JDBC는 Java Database Connectivity의 약자로,  
자바에서 데이터베이스에 접속해서 SQL을 실행할 수 있게 해주는 기술

### 💡 JDBC는 무엇을 할 수 있나?
- Java에서 DB(MySQL, Oracle 등)에 접속
- SQL문을 작성하고 실행
- 결과를 받아서 처리

### ⚙️ JDBC의 기본 구조
```java
// 1. 드라이버 로드
Class.forName("com.mysql.cj.jdbc.Driver");

// 2. 연결
Connection conn = DriverManager.getConnection(DB주소, 사용자명, 비밀번호);

// 3. SQL 실행
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// 4. 결과 처리
while (rs.next()) {
    System.out.println(rs.getString("name"));
}

// 5. 종료
rs.close();
stmt.close();
conn.close();
```



### 🧱 JDBC와 CRUD의 관계
- JDBC는 자바에서 CRUD를 수행하기 위한 도구  
즉, JDBC를 사용해서 자바 코드에서 DB에 접근하고 INSERT, SELECT, UPDATE, DELETE 같은 SQL문을 실행

| 역할   | 설명                       |
| ---- | ------------------------ |
| JDBC | SQL을 Java에서 실행할 수 있게 도와줌 |
| CRUD | SQL로 수행하는 작업의 종류         |

#### 📌 정리 요약
| 용어   | 의미                           | 역할                          |
| ---- | ---------------------------- | --------------------------- |
| CRUD | Create, Read, Update, Delete | 데이터 다루는 네 가지 기본 기능          |
| JDBC | Java Database Connectivity   | Java에서 DB에 연결하고 SQL 실행하는 도구 |

