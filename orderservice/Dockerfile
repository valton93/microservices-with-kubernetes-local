FROM openjdk:17

ARG FILE_NAME=target/*.jar

COPY ${FILE_NAME} orderservice.jar

ENTRYPOINT [ "java","-jar","/orderservice.jar" ]

EXPOSE 9090