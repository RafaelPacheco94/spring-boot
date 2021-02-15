## Zookeeper Discovery/Configuration with Spring Gateway

### Zookeeper Installation - Docker
> Pull Zookeeper docker image
```
docker pull zookeeper:'[3.5]'
```
> Execute Zookeeper container
```
docker run -d -p 2181:2181 --name zookeeper-spring-boot zookeeper:3.5
```
### Zookeeper Configuration
>Access Zookeeper container
```
docker exec -it zookeeper-spring-boot bash
```
> Execute Zookeeper Client
```
root@container-id:/apache-zookeeper-3.5-bin# bin/zkCli.sh
```
> Create configuration files according to the following:
```
[zk: host:2181(CONNECTED) 0] create /config/[application-name],[application-profile]/[key.key] "value"
```
>Example:
```
Example > create /config/microservice-one,dev/my.key.example "my value"
Example > get /config/microservice-one,dev/my.key.example
my value
```
