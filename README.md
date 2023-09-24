# Coding challenge with Spring Boot

<details>
  <summary>
<h2>Challenge requirements</h2>
  </summary>
Develop a REST API in Spring Boot using Java 11 or newer.
  
It has to expose a service which takes two numbers as parameters and returns the sum of the two numbers with an added percentage which needs to be retrieved from an external service.
For example: If we receive 5 and 15 and the returned percentage is 10, then (5+15) + 10% = 22

Have in mind the following considerations:
- The external service can be mocked
- Since the external percentage doesn't vary much, we can consider that the value will remain the same for 30 minutes.
- If the external service fails to respond, we have to return the last returned value. If there's none, return an error.
- If the external service fails, we can retry the call 3 times.
- Keep a log with all the endpoint calls with their response in case of success.
- Response must be in json and paginated.
- Saving the call history to the log must not add processing time to our service and if it fails, our main service must continue to work.
- Our API can take at most 3 RPM (Requests/minute), if we exceed that quota we must return an adequate message and http code.
- Call history must be stored in a Postgres Database.
- Include 4xx error messages.
- The app must be deployed in a Docker container.
- This Docker image can be stored in a public Dockerhub.
- The Postgres DB must run in a Docker container as well. It's recommended to use Docker Compose.
- A Postman collection must be included to test the API.
- The app's code must be stored in a public repository along with the instructions for deploying and consuming the API.
</details>

<details>
  <summary>
<h2>Instructions</h2>
  </summary>
1. Clone repository. <br>
2. Run command `mvn clean package -DskipTests` <br>
3. Run command `docker-compose up` <br>
4. Test API using provided Postman collection. <br>
</details>
