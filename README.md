# MSA w/Spring Boot Kotlin

# 3rd party docker installation
````bash
docker-compose up -d
````

# confluent repo 사내 인증서 오류

ref. https://velog.io/@won2oppa/%EB%B9%8C%EB%93%9C-%EC%97%90%EB%9F%AC-%EB%8C%80%EC%B2%98%EB%B2%95-Unable-to-find-valid-certification-path-to-requested-target

```agsl
sudo keytool -importcert -file /Users/teddy/Documents/confluent.io.cer -keystore /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/lib/security/cacerts -storepass changeit -noprompt
```


# schema-registry api 관련
````
curl -X GET \
-H "Content-Type: application/json" \
http://localhost:8081/subjects/
````
```
curl -X GET \
-H "Content-Type: application/json" \
http://localhost:8081/subjects/order-events-v1-value/versions/1
```
