# Project Outline

Write a RESTful service that calculates and returns all the prime numbers  up to an including a number provided.

# Example

The REST call should look like  http://your.host.com/primes/10  and should return JSON content:
```
{
  “Initial”:  “10
  “Primes”: [2,3,5,7]
}
```
# Requirements

+ The project must be written in Java 7 / 8.
+ The project must use Maven OR Gradle to build, test and run.
+ The project must have unit and integration tests.
+ The project must run, in that the service should  be hosted in a container e.g. Tomcat, Jetty, Spring Boot etc.
+ You may use any frameworks or libraries for support e.g. Spring MVC, Apache CXF, Jackson etc.
+ The project must be accessible from Github.

# Optional Extensions

- Consider supporting varying return content type such as XML based on requested media type.
- Consider ways to improve performance e.g. caching, threading
- Consider supporting multiple algorithms based on optional parameters