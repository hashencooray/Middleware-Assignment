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

     GET "http://localhost:8080/pets"
     
Get All Pet Types

     GET "http://localhost:8080/pets/type/all"
     
Add pet

     POST "http://localhost:8080/pets/add"
     
Add Pet Type

     POST "http://localhost:8080/pets/type/add"
     
Update Pet

     PUT "http://localhost:8080/pets/update/4" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"petAge\":5,\"petName\":\"Rooney\",\"petType\":\"Dog\"}"
     
Update Pet Type

     PUT "http://localhost:8080/pets/type/update/1" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"petType\":\"cats\"}"
     
Delete pet

     DELETE "http://localhost:8080/pets/delete/7"
     
Add Pet Type

     DELETE "http://localhost:8080/pets/type/delete/3"
     
Search pet

     GET "http://localhost:8080/pets/search" -H  "accept: application/json" -d "{\"name\":\"Kitty\"}"

     
