# Spring boot
## IoC, DI
> #### IoC(Inversion of Control) : 제어의 역전
> 
> 객체의 생성과 관리를 프레임워크에서 처리하는 것.


> #### DI(Dependecy Injection) : 의존성 주입
> 
> 객체를 Spring이 주입해 주는 방식 


## Bean, Container
> #### Bean
> 
> 스프링에서 관리하는 객체
> 아래와 같이 @Component를 붙이면 해당 class를 Spring에서 객체로 생성하는데 이를 Bean이라고 한다. 
> 
>(싱글톤으로 관리하여 애플리케이션 전역전으로 하나만 사용)
> ```
>@Component
>public class A {
>   public void A() {
>       System.out.println("A 생성");
>   }
>}
>```

> #### Container
> 
> Bean으로 만든 객체들을 생성하고 관리하는 곳.
> 

> #### Bean 주입
> 개발자가 @Autowired 를 사용하면 컨테이너에 있는 빈을 주입할 수 있다. 생성자가 1개일 경우에 아래와 같이 주입이 가능하다.
> ```
> public class B {
>    // A Bean을 주입받는다.
>    @Autowired
>    A a;
> }
> ```
> @RequiredArgsConstructor : Lombok을 사용하면 자동으로 주입이 가능하다.

----

# 응답코드

- 200 OK : 성공적으로 수행
- 201 Created : 성공적으로 수행 + 리소스 생성
- 400 Bad Request : 요청 값이 잚못된
- 403 Forbidden : 권한이 없음
- 404 Not Found : 리소스가 없음
- 500 Internal Server Error : 서버 상 문제

---

## REST API

### DTO (Data Transfer Object)
> 클라이언트와 서버 사이에 데이터를 교환하기 위해 사용하는 객체
> request의 body의 내용(JSON/XML)을 자바에서 사용하기 위해 
> 자바객체(DTO)로 변환해서 사용함.
> 
단순하게 다음과 같은 형태로 생겼음. 비지니스 로직 없고, 순수하게 데이터를 담는 역할만 한다.
```
public class AddQuestionRequest {
    private String title;
    private String content;
}
```

#### 구체적인 흐름
만약 클라이언트가 다음 JSON 데이터를 POST로 전송한다고 하면,
```
{
  "title": "첫 번째 게시글",
  "content": "내용",
}
```
위 내용이 body에 전달되어서 HTTP 요청으로 들어온다고 해보자.
```
@PostMapping
public ResponseEntity<String> createPost(@RequestBody AddQuestionRequest addQuestionRequest) {
    System.out.println(postDTO.getTitle());  // → 첫 번째 게시글
    System.out.println(postDTO.getContent()); // → Spring Boot 게시판 만들기!
    
    return ResponseEntity.ok("게시글 생성 완료!");
}
```
그러면 위 코드에서 **@RequestBody** 부분으로 body 내용이 들어가서 addQuestionRequest 라는 DTO에 
저장이 되고, 이를 자바에서 사용할 수 있게 되는거!

### DAO (Data Access Object)
> DB에 접근하여 CRUD 작업을 하기 위해 사용하는 객체

원래 DB에서 CRUD를 하려면 직접 SQL언어를 사용해야하는데, Java에서 DB CRUD를 가능하게 해주는 객체임.
많이 쓰이는 DAO로 JPA가 쓰임.
```
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
```

### 왜 Entity로 바로 전달하지 않고 DTO를 쓸까?
Question을 Entity로 얻은 이후 Response에 바로 전달할 수도 있다. 
하지만 왜 번거롭게 DTO로 변환하는 과정을 한번 더 해야할까?

> Entity를 바로 전달할 경우, 복잡하거나 민감한 정보가 포함될 수 있다. 
> 따라서 이를 방지하기 위해서, 최적화시키고 민감한 정보는 제외한 DTO를 통해 데이터를 전달한다. 

### 흐름 이해하기

> 1. 클라이언트가 게시글 등록 요청 (POST)
> 2. controller가 해당 요청을 **DTO** 로 받음.
> 3. Service가 DTO를 Entity로 변환함.
> 4. Entity를 DAO에 적용 (CRUD)
> 5. DAO가 DB에 저장
> 6. 저장된 데이터를 다시 DTO로 변환하여 클라이언트에 응답
> 7. 
```
[Client] → [Controller] → [Service] → [DAO] → [DB]
     ↑                                     ↓
   응답 DTO ←---------------------------- 데이터
```

---

# Controller

## Rest API
### ResponseEntity<>
> 클라이언트에게 response를 보낼 때 데이터 뿐만아니라, HTTP 상태, 헤더, 바디를 포함해서 보낼 수 있는 객체

#### 주요 메서드
메서드	설명 
- ok(T body) :	200 OK + 응답 바디 전송
- status(HttpStatus status)	: 원하는 상태 코드로 응답 전송
- badRequest()	: 400 Bad Request 전송
- notFound()	: 404 Not Found 전송
- created(URI location) :	201 Created + 리소스 위치 전달
- noContent() :	204 No Content 전송
- body(T body) :	응답 바디에 데이터 넣기
- build() : body 없이 response 생성
- headers(HttpHeaders headers) :	응답에 커스텀 헤더 추가
