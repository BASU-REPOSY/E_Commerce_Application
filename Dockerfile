FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/e-commerce-docker.jar e-commerce-docker.jar
ENTRYPOINT ["java","-jar","/e-commerce-docker.jar"]