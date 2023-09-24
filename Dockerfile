FROM openjdk:17
VOLUME /tmp
EXPOSE 8082
COPY target/challenge-0.0.1-SNAPSHOT.jar challenge.jar
ENTRYPOINT ["java","-jar","/challenge.jar"]