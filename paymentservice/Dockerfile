FROM openjdk:17

ARG FILE_NAME=target/*.jar

COPY ${FILE_NAME} paymentservice.jar

ENTRYPOINT [ "java","-jar","/paymentservice.jar" ]

EXPOSE 9090