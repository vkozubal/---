# HOW TO RUN:

    REQUIREMENTS:
    - assembled jar file
    - java 1.7
    - run 'java -jar poster-spring-boot.jar'
    
    HOW TO GET JAR FILE:
    - you can build it by yourself
    - you ask somebody to build for you

# HOW TO BUILD JAR FILE:

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
so to access swagger please go to: http://localhost:<port>/webjars/swagger-ui/2.1.8-M1/index.html
and put there http://localhost:8080/api-docs address.

Documentation: https://drive.google.com/folderview?id=0B5KXxwMdmpt1MDQ4bVJNU2xDNms&usp=sharing


Database configuration from official tutorial  https://docs.docker.com/examples/mongodb/:


You can find out how to create a mongo db instance here: https://github.com/dockerfile/mongodb

or with replication: https://github.com/inlight-media/docker-mongodb-replica-set

to start mongo run mongo localhost:<port>  (27017, 27018, 27019)


## Spring profiles:
we currently use two profiles: live and test
    -live (production ready profile)
    -test (used to replace some beans in tests)