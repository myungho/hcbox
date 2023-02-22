# Spring Cloud & Spring Boot Kotlin MSA 


# Docker Compose (3rd party package) 

````bash
docker-compose up -d
````

# schema-registry-ui

- http://localhost:8000/#/

# Keycloak

- http://localhost:8080/auth/admin

# Gateway Swagger

- http://localhost:8087/webjars/swagger-ui/index.html

# confluent repo 사내 인증서 오류
ref. https://velog.io/@won2oppa/%EB%B9%8C%EB%93%9C-%EC%97%90%EB%9F%AC-%EB%8C%80%EC%B2%98%EB%B2%95-Unable-to-find-valid-certification-path-to-requested-target

```agsl
sudo keytool -importcert -file /Users/teddy/Documents/confluent.io.cer -keystore /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/lib/security/cacerts -storepass changeit -noprompt
```
