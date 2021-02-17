## Spring Security JWT

##
### Run project
>Clean project and build project
```
mvn clean package
```
> Build from Dockerfile
```
docker build -t spring-boot-security-jwt .
```
> Run docker container
```
docker run -d -p 8081:8081 spring-boot-security-jwt
```
##
##### Send POST request to get JWT token

** Request **
```
curl -X POST \
-H "Content-Type: application/json" \
-d '{"email": "email@email.com", "password": "12345"}' \    ## embedded authentication data
http://localhost:8081/api/v1/authentication
```
** Response **
```
asdfasdf.asdfasdf.asdfasdf
```
##
##### Send request to test the token
```
curl -H "Authorization: Bearer asdfasdf.asdfasdf.asdfasdf" \
http://localhost:8081/test
```
