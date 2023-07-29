FROM openjdk:17-jdk-alpine
ADD target/University-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar University-0.0.1-SNAPSHOT.jar