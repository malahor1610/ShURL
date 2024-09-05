# ShURL - Build Your Own Pastebin

ShURL is an application created based on a challenge #12 of [CodingChallenges](https://codingchallenges.fyi/challenges/challenge-url-shortener) by John Crickett.
Application is implemented in Kotlin using Spring Boot with Thymeleaf engine. It stores data in Redis. It is published as a public docker image.

## Description

This challenge is to build your own URL shortener like [TinyUrl](http://tinyurl.com/). A TinyUrl is a service that lets a client submit a long URL which is then shortened to make it easier to use.

ShURL implements only main functionalities such as:

- generating short links for given URL
- storing short links (and reuse them if the given URL has already shorten version)
- validating if the given URL is valid
- redirecting via short link to linked URL
- copying short link to clipboard easily

## Requirements

- Kotlin (build-in with IntelliJ IDEA)
- Java (JDK 21)
- Git
- Redis

Or

- Docker

## Usage without Docker

Clone the repository

```bash
git clone https://github.com/malahor1610/ShURL.git
```
Install redis based on instructions - [Redis installation guide](https://redis.io/docs/latest/operate/oss_and_stack/install/install-redis/)

Update *src/main/resources/application.properties* with correct data

```
spring.redis.host=localhost 
spring.redis.port=6379
```

Start the application running the following command in root project directory

```bash
./gradlew.bat bootRun
```
Open [http://localhost:8080](http://localhost:8080) and enjoy.

## Usage with Docker

Download docker compose file
```bash
https://github.com/malahor1610/ShURL/blob/main/compose.yaml
```
Run app within directory with compose file
```bash
docker compose up
```

Open [http://localhost:8080](http://localhost:8080) and enjoy.