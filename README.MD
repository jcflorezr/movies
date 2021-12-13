# Movies App

* This project has been implemented using the [Hexagonal Architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)) principle.
  * The Domain layer is found in: `com.movies.domain`
  * The Application layer is found in: `com.movies.application`
  * The Adapters layer is found in: `com.movies.adapters`
* Integration (End to end) tests are located in `com.movies.integrationtest` package inside the `test` module (`src/main/test`).
* Integration tests run along with the unit tests, they are not run as part of a separate Gradle task

### How to execute it:
* The following Environment Variables must be created with their correponding values:
  ```
  export OMDB_SERVICE_URL=http://www.omdbapi.com/ && \
  export OMDB_SERVICE_API_KEY=<<your api key received from OMDb API>>
  ```
* The following Environment Variables have default values in `application.yml` Spring config file. In case we do want to provide other values, this is an example of how to do it:
  ```
  export MOVIES_DB_HOST=localhost && \
  export MOVIES_DB_PORT=5432 && \
  export MOVIES_DB_NAME=test && \
  export MOVIES_DB_SCHEMA=movies
  ```
* This would be a fast way to create a Postgres Database for the Movies App according to the Env Vars listed above:
  ```
  docker run --name movies-db-test -p 5432:5432 -e POSTGRES_USER=test -e POSTGRES_PASSWORD=test -e POSTGRES_DB=movies -d postgres:11
  ```
* Run the gradle command in the root folder: `./gradlew bootRun`

### Please be aware of this:
* I was not able to implement Swagger Docs due to [an unresolved bug](https://github.com/springfox/springfox/issues/3791) that does not allow the latest version of Spring Boot to work fine with Springfox, the library to integrate Spring and Swagger.
* Please run the tests using IntelliJ IDEA rather than using the Gradle command `./gradlew clean build`. There is a problem between the latest version of Junit 5 that somehow only detects the tests located in `com.movies.adapters.rest.api.controller`. Other tests and the Integration tests will not run by executing `./gradlew clean build`, that is why I kindly ask to run the test context using the IDE.
* Since there is no REST API documentation, these are the endpoints created:

  ```
  - An internal endpoint in which they (i.e. the cinema owners) can update show times and prices for their movie catalog
  POST /api/v1/movies/{movieId}/catalog-info
  PUT /api/v1/movies/{movieId}/catalog-info
  
  - An endpoint in which their customers (i.e. moviegoers) can fetch movie times
  GET /api/v1/movies/{movieId}/catalog-info
  
  - An endpoint in which their customers (i.e. moviegoers) can fetch details about one of their movies (e.g. name, description, release date, rating, IMDb rating, and runtime). Even though there's a limited offering, please use the OMDb APIs (detailed below) to demonstrate how to communicate across APIs.
  GET /api/v1/movies/{movieId}
  
  - An endpoint to register a movie
  POST /api/v1/movies
  
  - An endpoint in which their customers (i.e. moviegoers) can leave a review rating (from 1-5 stars) about a particular movie
  POST /api/v1/movies/{movieId}/ratings/{movigoerId}
  
  - An endpoint to register a Movigoer
  POST /api/v1/movigoers
  ```
  Please refer to tests in `com.movies.integrationtest` package to get more reference about the endpoints

### Technology Stack
* [Kotlin](https://kotlinlang.org/) - as main programming language
* [Spring Boot](https://spring.io/projects/spring-boot) - Web Application framework
* [JUnit5](https://junit.org/junit5/) - as testing library
* [TestContainers](https://www.testcontainers.org/) - This library is used to spin up a Docker Container containing a PostgreSQL database to be used by the Integration Tests
* [Gradle](https://gradle.org/)
