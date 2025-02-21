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
> 