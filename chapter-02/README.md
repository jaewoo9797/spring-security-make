# Spring Security 2장

학습 목표
- 스프링 시큐리티 첫 프로젝트 만들기
- 인증과 권한 부여를 위한 기본 구성 요소로 간단한 기능 설계
- 구성 요소가 서로 어떻게 연관되는지 이해하기 위한 기본 계약 적용
- 주 책임에 대한 구현 작성
- 스프링 부트의 기본 구성 재정의

> [!NOTE]
> 스프링 부트는 미리 준비된 구성을 제공하므로 모든 구성을 작성하는 대신 일치하지 않은 부분만 재정의한다.   
> 이 접근법을 **설정보다 관습(convention-over-configuration)** 이라고 한다.

## First Step
1. REST API 하나를 노출하는 작은 웹 애플리케이션을 만든다.
2. security, web 종속성을 추가한다.
3. HTTP Basic을 이용해 사용자 인증

![user-server](./image/security-1-1.drawio.png)

**주 목적**
- 스프링 시큐리티의 기본 구성이 어떻게 동작하는지 확인한다.

### 결과

<img src="./image/first-success-test.png" alt="실패" width="500" height="auto">

기본 구성으로 올바른 종속성이 이용되도록하는 것을 확인할 수 있다.

## 기본 구성 재정의
> [!IMPORTANT]
> 스프링 시큐리티는 구성을 여러 방식으로 재정의할 수 있다.   
> 이 유연성 때문에 혼란을 느끼거나 복잡해질 수 있다. 이는 바람직하지 않다. 즉, 유연성을 남용해서는 안 된다.   

`UserDetailsService`와 `PasswordEncoder` 를 구성하는 방법을 배운다.

### 구성 재정의 변경점
> [!IMPORTANT]
> `WebSecurityConfigurerAdapter` 는 지원이 중단되어 더 이상 사용하지 않습니다.

[Spring Docs-구성하는 방법](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html)
