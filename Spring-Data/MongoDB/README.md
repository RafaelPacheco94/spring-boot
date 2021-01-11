
## MongoDB - Docker Install
`docker pull mongo` - download the latest official Docker image for MongoDB database

`docker images` - list the images from your Docker repository

> By default, MongoDB stores data in the **/data/db** directory within the Docker container. In order to change this, it is necessary to mount a directory from the host system to the container running the MongoDB database.

`mkdir -p /mongodbdata` - create a directory on the system host

`docker run -it -v mongodbdata:/data/db --name [mongodatabase] -d mongo` - start the docker container with the mongo image

- `-it`- provides an interactive shell to the Docker container
- `-v`- option to attach the /**mongodbdata** host volume to the **/data/db** container volume
- `-d` - start the container as a backgroung process
- `--name`- name of the container [mongodatabase]

> Check the status of container by running:\
`docker ps`

> Optionally you can specify the MongoDB port explicitly:\
`docker run -it -v mongodbdata:/data/db -p 27017:27017 --name [mongodatabase] -d mongo`

> You can check the Docker logs by running:\
`docker logs [mongodatabase]`

>In order to connect to the Docker container process, which is currently running in **detach mode**, run:\
`docker exec -it [mongodatabase] bash`\
You can start  using MongoDB shell after typing **mongo**. To exit simply type **exit**.

> In order to start and stop the Docker container you can run\
 `docker stop [mongodatabase]` - to stop\
 `docker start [mongodatabase]` - to start
 
 Finnaly, you can get the IP address of the container by running the following command:\
 `sudo docker inspect [mongodatabase] | grep IPAddress`


## MongoDB - Create a new user
Connect to the Docker container process by running:\
 `docker exec -it [mongodatabase] bash`\
 Then type:\
 `mongo`
>MongoDB shell version ...

`> use admin` - use admin database
```javascript
// Create a new user on database
> db.createUser({user: "admin",pwd: "secrectP@wd",roles: [ { role: "readWrite", db: "reporting" } ],mechanisms: [ "SCRAM-SHA-256" ]})
```

