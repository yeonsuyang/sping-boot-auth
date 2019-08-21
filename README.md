# sping-boot-auth



발생한 Error List

<br>

```
There is no PasswordEncoder mapped for the id "null"
```

> 1. clientId, secretId가 잘 설정 되어 있는 지
>
> 2. passwordDecoder가 잘 설정되어 있는 지
> 3. secretId, password 둘 다 noop 등 encoder되어 있는 지  > 이 부분 수정으로 해결 됨
> 4. BCryptPasswordEncoder 를  PasswordEncoderFactories.createDelegatingPasswordEncoder(); 로 변경

<br>

```
The dependencies of some of the beans in the application context form a cycle:

┌─────┐
|  customAuthorizationProvider defined in file [/home/ysyang/IdeaProjects/springboot-auth/out/production/classes/com/example/auth/config/CustomAuthorizationProvider.class]
↑     ↓
|  securityConfig defined in file [/home/ysyang/IdeaProjects/springboot-auth/out/production/classes/com/example/auth/config/SecurityConfig.class]
└─────┘
```

> Encoder @Bean을 SecurityConfig에 선언해주었었는데 그럼 순환 참조가 발생해서, WebMvcConfig로 이동해서 해결

<br>

```
org.h2.jdbc.JdbcSQLException: Database "/home/~/test" not found, and IFEXISTS=true, so we cant auto-create it
```

혹은 mem을 사용하는데 IFEXISTS=true  비슷한 오류가 발생하면

> h2 파일 버전을 사용할 경우 
>
> 발생하면 자동으로 안만들어져서 생기는 오류이므로 home 디렉토리에 test.mv.db 생성해줘서 해결
>
> mem 버전을 사용할 경우 아래 선언에 버전을 선언해줘서 해결함.
>
> ```
> runtimeOnly 'com.h2database:h2 
> ```