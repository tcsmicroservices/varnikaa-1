# Project
This Application is to understand the various annotations with spring boot application for a calculator.

##
Create Project

```bash
$mvn archetype:generate -DgroupId=com.ashish.app -DartifactId=spring-boot-calculator -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
## Import project
Import the project on either eclipse or inellij as a maven import project

## Building

### Maven
This project is maven project which will be build using maven command.

```bash
$ mvn clean install
```

## Dockerization


### Maven


Here i am using maven spotify plugin to create the docker image for this application.
Use the below command to create the docker image.
For creating please use your repository to create the image which will be easy to push image in your docker hub.
update in your pom.xml.
<docker.image.prefix> <aashi007> </docker.image.prefix>

#### Build docker images

```bash
$ mvn install dockerfile:build
```

### Push docker images

```bash
docker login
$ docker push <aashi007>/spring-boot-calculator
```

## Running

The application can be start is either locally using maven or start in docker container.

### Running the application in local environment
Using eclipse:It can be run as java application or run configuration to provide the program arguments.
You can run using maven command as:

```bash
$ mvn spring-boot:run
```

### Running the application in docker container

```bash
$ docker run -p 8081:8080 aashi007/spring-boot-calculator:0.0.1-SNAPSHOT 
$ docker-compose  pull
$ docker-compose  up
```

## Looking docker image

docker exec -it d1b789397ff5 bash

## Testing

1)http://localhost:8081/add 
2)http://localhost:8081/sub 
3)http://localhost:8081/mul
4)http://localhost:8081/div


