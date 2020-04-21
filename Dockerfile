FROM openjdk:8-jre-alpine
COPY  target/movie-api-0.0.1-SNAPSHOT.jar /MovieAPI.jar
EXPOSE 8080
CMD ["/usr/bin/java","-jar","/MovieAPI.jar"]