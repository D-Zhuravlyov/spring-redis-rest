# Simple application for using redis over REST

### Uses Spring Boot

To run the application you need installed:
###Java, Maven.

To run the application in Docker containers use:

#### Docker, docker-compose 

1.
In terminal navigate to the project folder and run this Maven command to build project:


```
mvn clean install
```
 2.
 To run both containers: Application and Redis, with a single command.
 use docker-compose: 
 
```
docker-compose up
```

## Usage

####To publish new messages to the REDIS server using a REST API POST request, use curl:

```
curl --request POST \
  --url http://localhost:8080/api/rest/v1/redis/publish \
  --header 'Content-Type: application/json' \
  --data '{
	"content": "Some text to put to redis"
}'
```

####To retrieve the last message that was on the REDIS server using a REST API GET request.
                                              
```
curl --request GET \
  --url http://localhost:8080/api/rest/v1/redis/getLast \
  --header 'Accept: application/json'

```

A response will return the last message from the REDIS:

```
[
  "Some text to put to redis"
]

```
*Brackets here are used to see the response even if it is empty.
### Retrieve all messages that were on the REDIS and occurred between two given timestamps using a REST API GET request:

 ####Two parameters named “start” and “end” are used.

```
curl --request GET \
  --url 'http://localhost:8080/api/rest/v1/redis/getByTime?start=2021-01-20T06%3A33%3A56.316Z&end=2021-01-22T06%3A33%3A58.282Z' \
  --header 'Accept: application/json'

```

A response will return all messages between these two timestamps:

```
[
  "Some text to put to redis",
  "Some text to put to redis"
]

```