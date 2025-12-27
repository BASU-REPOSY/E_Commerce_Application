FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8084
ADD target/e-commerce-docker.jar e-commerce-docker.jar
ENTRYPOINT ["java","-jar","/e-commerce-docker.jar"]