# Spring Security 3장
- `UserDetails` 인터페이스로 사용자 기술하기
- 인증 흐름에 `UserDetailsService` 이용하기
- `UserDetailsService`의 맞춤형 구현 만들기
- `UserDetailsManager`의 맞춤형 구현 만들기
- 인증 흐름에 `JdbcUserDetailsManager` 이용하기

## 인증 구현
`UserDetailsService` 의 역할은 **사용자의 이름으로 사용자를 검색하는 것이다.**

Spring Security 프레임워크가 이해할 수 있도록 `UserDetails` 계약을 통해 기술해야한다.

```java
public interface UserDetailsService {
    
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
```

> [!NOTE]
> `UsernameNotFoundException` 은 `RuntimeException`입니다. 인터페이스의 throws 는 문서화를 위한 것입니다.   
> 다른 API를 사용할 때 인터페이스에 throws 절에 사용되는 Exception이 `RuntimeException`인지 확인해보자.   
> 나도 문서화를 위해 인터페이스 throws 절을 이용해서 어떤 예외가 발생하는지 알려는 방법도 좋을 것 같다.

## 예상하지 못한 문제
Spring 에서는 예외를 핸들링하기 위해서 `ControllerAdvice` 의 `@ExceptionHandler` 를 이용해서 예외를 적절히 처리한 후
사용자에게 반환해줍니다.

하지만, 스프링 시큐리티에서 발생하는 예외가 `Spring Context`에서 관리하는 `GlobalExceptionHandler` 에 잡히지 않는 문제가 있습니다.

왜냐하면, 스프링 시큐리티는 스프링이 관리하는 컨텍스트 전에 `Tomcat` 의 `Filter` 단에서 인터셉터되어 처리되기 떄문입니다.

<img src="./image/Security%20Architecture.png" alt="Security Architecture" width="900px" height="auto">
