FROM adoptopenjdk
ADD target/hrms-postgres.jar hrms.jar
#ENV PROFILE=docker
EXPOSE 8080
ENTRYPOINT ["java","-jar","hrms.jar"]
