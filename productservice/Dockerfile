FROM openjdk:17

ARG FILE_NAME=target/*.jar

COPY ${FILE_NAME} productservice.jar

ENTRYPOINT [ "java","-jar","/productservice.jar" ]

EXPOSE 8081