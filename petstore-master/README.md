# PetStore Application
B.H.R.CORAY

18000266

2018CS026

## Packaging and running the application

If you want to build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

The application can be also packaged using simple:

    ./gradlew build

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html

## TEST

    ./gradlew test

## APIs

Get All Pets

     ./gradlew test


## Deploying Application

To deploy the demo app on a docker-compose please visit [./deploy](https://github.com/rasika/petstore/tree/master/deploy)
