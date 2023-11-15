FROM openjdk:17-jdk-alpine
LABEL maintainer="Vadym Pavlyk"
COPY target/lab6.war java-app-docker.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/java-app-docker.war"]