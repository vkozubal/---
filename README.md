HOW TO RUN:

    REQUIREMENTS:
    - assembled jar file
    - java 1.7
    - run 'java -jar poster-spring-boot.jar'
    
    HOW TO GET JAR FILE:
    - you can build it by yourself
    - you ask somebody to build for you

HOW TO BUILD JAR FILE:

    REQUIREMENTS:
    - java 1.7
    - maven
    - run 'mvn package' from root  project directory
    - jar file will be accessible under ./poster-spring-boot/target/poster-spring-boot.jar directory

The spring security configuration ensures that:

    every request requires the user to be authenticated
    form based authentication is supported
    HTTP Basic Authentication is supported (mostly for integration tests now)


we can put swagger sources under resources/static directory of spring boot module
but we are using swagger-ui webjar 
so to access swagger please go to: http://localhost:8080/webjars/swagger-ui/2.0.12/index.html
and put there http://localhost:8080/api-docs address.

Documentation: https://drive.google.com/folderview?id=0B5KXxwMdmpt1MDQ4bVJNU2xDNms&usp=sharing


Database configuration from official tutorial  https://docs.docker.com/examples/mongodb/:


You can find out how to create a mongo db instance here: https://github.com/dockerfile/mongodb

or 

# Format: sudo docker build --tag/-t <user-name>/<repository> .
# Example:
$ sudo docker build --tag my/repo .

# Basic way
# Usage: sudo docker run --name <name for container> -d <user-name>/<repository>
$ sudo docker run --name mongo_instance_001 -d my/repo

# Dockerized MongoDB, lean and mean!
# Usage: sudo docker run --name <name for container> -d <user-name>/<repository> --noprealloc --smallfiles
$ sudo docker run --name mongo_instance_001 -d my/repo --noprealloc --smallfiles

# Checking out the logs of a MongoDB container
# Usage: sudo docker logs <name for container>
$ sudo docker logs mongo_instance_001

# Playing with MongoDB
# Usage: mongo --port <port you get from `docker ps`> 
$ mongo --port 12345