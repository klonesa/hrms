FROM adoptopenjdk
ADD target/hrms-postgres.jar hrms.jar
ENV PROFILE=docker
EXPOSE 8080
#ENTRYPOINT ["java","-Dspring.profiles.active=$PROFILE","-jar","hrms.jar"] #calismadi
#CMD java -Dspring.profiles.active=$PROFILE -jar hrms.jar # calisti
ENTRYPOINT [ "sh", "-c", "java -Dspring.profiles.active=$PROFILE -jar hrms.jar" ]
