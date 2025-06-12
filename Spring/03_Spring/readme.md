# 03. Spring - MyBatis 연동 (기본 / 심화 연계)

## 문제풀이

### 1. MyBatis와 관련된 의존 라이브러리를 추가하세요.

```xml
 build.gradle
 → gradle sync
 …
 // 데이터베이스
implementation 'com.mysql:mysql-connector-j:8.1.0'
 implementation 'com.zaxxer:HikariCP:2.7.4'

 implementation "org.springframework:spring-tx:${springVersion}"
 implementation "org.springframework:spring-jdbc:${springVersion}"
 implementation 'org.mybatis:mybatis:3.4.6'
 implementation 'org.mybatis:mybatis-spring:1.3.2'
```
- 코끼리

### ✅ 2. RootConfig 에 MyBatis를 위한 기본 설정을 하세요.
- SqlSessionFactory 빈 등록
- DataSourceTransactionManager 빈 등록
##### 주로 등록하는 것:
- DataSource (HikariCP)
- SqlSessionFactory
- TransactionManager
- MyBatis 설정 파일 경로
- 
#### RootConfig.java
- 역할: 애플리케이션 전반의 공통 설정, 특히 DB, MyBatis, 트랜잭션 설정을 담당.



| 항목                             | 이유                                            |
| ------------------------------ | --------------------------------------------- |
| `SqlSessionFactory`            | MyBatis의 핵심 세션 생성기, Spring에서 DI를 통해 사용 가능해야 함 |
| `DataSourceTransactionManager` | DB 작업의 트랜잭션 제어를 Spring이 관리하기 위함               |


## ✅ 요약 정리 표

| 위치                             | 주요 클래스/파일                                    | 역할 요약                      |
| ------------------------------ | -------------------------------------------- | -------------------------- |
| `config/`                      | `RootConfig.java`                            | DB 및 MyBatis 설정 등록         |
|                                | `ServletConfig.java`                         | Spring MVC 전반 설정           |
|                                | `WebConfig.java`                             | 웹 관련 부가 설정 (Multipart 등)   |
| `controller/`                  | `HomeController.java`                        | 사용자 요청을 받아 응답하는 Controller |
| `ex04/`                        | `HelloServlet.java`                          | 서블릿 직접 구현 또는 실습용           |
| `exception/`                   | `CommonExceptionAdvice.java`                 | 공통 예외 처리 클래스               |
| `mapper/`                      | `TimeMapper.java`                            | MyBatis 인터페이스              |
| `resources/org.scoula.mapper/` | `TimeMapper.xml`                             | SQL 정의 파일                  |
| `resources/`                   | 설정파일들 (application.properties, log4j2.xml 등) |                            |
| `webapp/WEB-INF/views/`        | JSP 뷰 위치                                     |                            |
| `test/`                        | 테스트 클래스들                                     | 설정 및 Mapper 테스트            |



##### ※매핑
그대, **"매핑한다"**는 표현은 Spring이나 웹 개발에서 **"요청(request)을 적절한 처리 코드와 연결하는 것"**을 의미함.  
쉽게 말해, **"누군가 웹사이트의 어떤 주소로 들어왔을 때, 어떤 함수(또는 JSP, Servlet 등)가 실행될지를 정해주는 작업"**임.  
  
그대가 식당 주인이고, 메뉴판이 있음.  
손님이 "짜장면 주세요!"라고 말하면,  
→ 주방에서 짜장면을 담당하는 요리사가 그 주문을 받음.  
👉 이때 "짜장면"이라는 요청 → "짜장면 요리사"에게 연결하는 것 = 매핑  
| 매핑이 없으면             | 매핑이 있으면            |
| ------------------- | ------------------ |
| Java 코드에서 SQL 사용 불가 | Java 코드로 SQL 실행 가능 |
| SQL 결과 직접 처리해야 함    | 자동으로 객체에 담김        |
| SQL 수정이 어렵고 위험      | SQL만 수정 가능         |
| 도구의 지원 거의 없음        | IDE에서 완벽한 지원       |




![image](https://github.com/user-attachments/assets/8087fa9f-2c63-4f07-b999-e4fa407f2122)

#### 🔷 왜 mapper 디렉토리를 따로 만들까?
- mapper는 MyBatis 전용 디렉토리로,
- DB에 접근하는 코드들을 모아두는 공간임.
- MVC 패턴에서 보면 이것은 Model 계층의 일부임.

```yaml
controller : 요청/응답 처리 (웹 화면 담당)
service    : 비즈니스 로직 처리
mapper     : 실제 DB와 쿼리 연결 (MyBatis의 DAO 역할)
```
#### 🔷 TimeMapper 인터페이스는 왜 만들까?
 이건 MyBatis에서 SQL과 자바 코드를 연결하는 다리 역할을 함.
| 이유               | 설명                                           |
| ---------------- | -------------------------------------------- |
| ✔ SQL을 자바 코드로 연결 | 자바에서 `timeMapper.getTime()` 부르면 SQL이 실행되게 만듦 |
| ✔ 쿼리 추상화         | 쿼리문을 메서드 하나로 감싸서 코드가 깔끔해짐                    |
| ✔ 유지보수 용이        | 나중에 SQL을 XML이나 어노테이션으로 바꾸기 쉬움                |
| ✔ 테스트 가능         | 이 인터페이스만 Mock 처리해서 테스트할 수 있음                 |



