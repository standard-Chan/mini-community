# DataBase

---

## ORM
>Object-relational mapping
> 
> 자바의 객체와 DB를 연결하는 프로그래밍 방법.
> 이를 이용하여 JAVA에서 데이터베이스를 다룰 수 있다.
> 대표적인 예시로 JPA가 있다.

### JPA와 Hibernate
> JPA(Java Persistence API) : 자바 객체와 데이터베이스를 연결시켜주는 API
> 
> Hibernate : JPA를 구현하여 만든 프레임워크. 내부적으로 JDBC API를 사용한다.

---

## ENTITY
JPA를 통해서 데이터 구조를 설계하다보면 ENTITY를 많이 쓰게된다.
### Entity
> ENTITY는 JAVA에서 사용되는 객체로 DB 테이블에 해당되는 객체를 의미한다. 
> DB에서 배우는 Relation 객체와 직접적인 연결이 되어있는 JAVA 객체를 Entity라고 생각하면 되겠다.

### Entity Manager
> Entity Manager는 엔티티를 관리하여 데이터베이스에 저장, 삭제, 수정 작업을 하는 역할을 한다.
> 사용자는 Entity Manager를 통해서 DB에 작업을 할 수 있는 것임. 
> 그리고 이러한 Entity Mangaer를 생성하는 곳이 Entity Manager Factory이다.

### Entity의 상태
> Entity는 4가지 상태가 존재한다.
> - Detached : : 영속성 컨텍스트가 관리하고 있지 않은 상태
> - managed : 관리
> - transient : 비영속 (영속성 컨텍스트로부터 분리)
> - removed : 제거
> ```
> @Autowired
> EntityManager em;
> 
> public void ex() {
>   Entity entity = new Entity(1L, "name"); // 비영속 상태
>   em.persist(entity); // 관리
>   em.detach(entity); // 분리
>   em.remove(entity;) // 제거
> }
> ```
위처럼 entity들은 개발자가 직접 .persist와 같은 명령을 하여 관리를 해야한다.
하지만 JpaRepository를 사용하면 개발자가 직접 관리할 필요 없이, 자동으로 관리가 가능하다.
사용자가 요청을 보내면 JpaRepository에서 DB를 조회하여 CRUD가 가능하게 해준다.
>```
>@Autowired
>EntityRepository entityRepository;
>
>public void a () {
>   entityRepository.save(new Entity(1L, value))
>}
>```
>


---

# JPA가 성능을 증가시키는 방법
## Persistence Context (영속성 컨텍스트)
> 영속성 컨텍스트란, JPA에서 엔티티를 관리하는 가상의 공간을 의미한다.
> 이를 이용하여 성능을 높힌 방법에는 다음이 있다.
> - 1차 캐시
> - 쓰기 지연
> - 변경 감지
> - 지연 로딩

> 1차캐시 : 컴퓨터에서 RAM과 레지스터 사이에 캐시의 역할과 동일하다.
> 자주 사용하는 데이터를 캐시 저장하여 바로 꺼내 쓸수 있도록 도와준다.
> 
> 쓰기 지연 : Stream Buffer와 비슷하게, 일정 데이터들을 즉시 쓰지 않고
> 모아두었다가 한번에 쓰는 것을 말한다.
> 
> 변경 감지 : 데이터의 변경이 일어날 경우, 캐시에 있는 데이터에도 반영을 해야한다.
> 데이터의 변경을 감지하면 캐시에 있는 값과 비교하여 자동으로 반영한다.
> 
> 지연 로딩 : 필요한 데이터를 미리 가져오는 것이 아니라, 필요한 순간에 가져오는 것을 말한다.


