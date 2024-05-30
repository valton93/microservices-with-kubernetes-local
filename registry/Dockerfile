FROM openjdk:17

ARG FILE_NAME=target/*.jar

COPY ${FILE_NAME} registry.jar

ENTRYPOINT ["java","-jar","/registry.jar"]

EXPOSE 8761