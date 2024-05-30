FROM openjdk:17

ARG FILE_NAME=target/*.jar

COPY ${FILE_NAME} gatewayservice.jar

ENTRYPOINT [ "java","-jar","/gatewayservice.jar" ]

EXPOSE 9090