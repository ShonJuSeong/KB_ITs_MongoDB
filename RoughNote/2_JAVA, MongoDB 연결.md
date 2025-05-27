# 2_JAVA, MongoDB 연결
## [JAVA](https://github.com/ShonJuSeong/KB_ITs_MySQL/tree/main/07_My_SQL)

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



#### 💾 가정 사항
- MySQL에 jdbc_test라는 DB가 있고
- 그 안에 users라는 테이블이 다음과 같이 생성되어 있음:

```SQL
  CREATE TABLE users (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(50),
    age INT
);
```

#### 📦 JDBC CRUD 예제 코드
```java
import java.sql.*;

public class JdbcCrudExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String password = "1234";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
        ) {
            // Create
            String insertSql = "INSERT INTO users (id, name, age) VALUES ('user1', '홍길동', 25)";
            stmt.executeUpdate(insertSql);
            System.out.println("CREATE 완료");

            // Read
            String selectSql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(selectSql);
            System.out.println("READ 결과:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id") +
                                   ", 이름: " + rs.getString("name") +
                                   ", 나이: " + rs.getInt("age"));
            }
            rs.close();

            // Update
            String updateSql = "UPDATE users SET age = 30 WHERE id = 'user1'";
            stmt.executeUpdate(updateSql);
            System.out.println("UPDATE 완료");

            // Delete
            String deleteSql = "DELETE FROM users WHERE id = 'user1'";
            stmt.executeUpdate(deleteSql);
            System.out.println("DELETE 완료");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### ✅ 설명 요약
| 부분                | 설명                         |
| ----------------- | -------------------------- |
| `Connection`      | DB에 연결을 생성                 |
| `Statement`       | SQL 실행 도구                  |
| `executeUpdate()` | INSERT, UPDATE, DELETE에 사용 |
| `executeQuery()`  | SELECT에 사용                 |
| `ResultSet`       | SELECT 결과를 받을 때 사용         |

### 🔐 추가 주의 사항
- 반드시 MySQL JDBC Driver를 pom.xml 또는 Gradle에 추가해야함  
- localhost, root, 1234, jdbc_test 등은 여러분의 환경에 맞게 변경해야함  
- 실제 프로젝트에서는 Statement 대신 PreparedStatement를 사용하는 것이 보안상 좋음  
