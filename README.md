## Springboot

### 스프링부트 프로젝트 생성 
```
https://start.spring.io/

Project
- Gradle 

Language
- Java

Dependencies
- Spring Web
- Thymeleaf
- Spring Data JPA
- Lombok
- H2 Database(sqlite 처럼 개발용 DB)
```

### 프로젝트 개발전 확인 사항 
```
build.gradle 체크

Intellij Setting 체크
1. Plugin 설치 (dependency 에 설치한 것)
2. Build - Compiler - Annotation Processors 
- Enable annotation processing : check true

spring-boot devtools (html, js, css 변경 적용)
https://imspear.tistory.com/138
```

## Documents
### Spring Coding Guide
```shell
https://spring.io/guides
```

### Thymeleaf
```shell
https://www.thymeleaf.org/
https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html
```

### H2 Database
```shell
https://www.h2database.com/html/main.html

접속
http://localhost:8082/

생성
JDBC URL : jdbc:h2:~/test

- Generic H2 (Server)
- jdbc:h2:tcp://localhost/~/test
```

### JPA
```
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts
```

### Jar build
```shell
 .\gradlew clean build
 
 build/libs/demo-0.0.1-SANPSHOT.jar 생기면 성공
 
 java -jar demo-0.0.1-SANPSHOT.jar 실행시 정상이면 성공
```