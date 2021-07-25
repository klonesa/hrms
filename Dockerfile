FROM adoptopenjdk
ADD target/hrms.jar hrms.jar
ENV PROFILE=docker
ENV CLOUDINARY_CLOUD_NAME=CLOUD_NAME
ENV CLOUDINARY_API_KEY=CLOUDINARY_API_KEY
ENV CLOUDINARY_API_SECRET=CLOUDINARY_API_SECRET
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -Dspring.profiles.active=$PROFILE \
-Dcloudinary.cloud-name=$CLOUDINARY_CLOUD_NAME \
-Dcloudinary.api-key=$CLOUDINARY_API_KEY \
-Dcloudinary.api-secret=$CLOUDINARY_API_SECRET \
-jar hrms.jar" ]