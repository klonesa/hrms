FROM adoptopenjdk
ADD target/hrms-postgres.jar hrms.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","hrms.jar"]
