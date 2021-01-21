FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
COPY ./target/redis-rest-0.0.1-SNAPSHOT.jar  /usr/src/app/redis-rest.jar
WORKDIR /usr/src/app
EXPOSE 8080
CMD java -jar /usr/src/app/redis-rest.jar