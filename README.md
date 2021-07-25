# HRMS Spring Boot Application
---
## Running Application with Docker

### Dependencies
- Docker
- Maven
- Cloudinary Account

Get Docker from [here](https://docs.docker.com/get-docker/)
Create free cloudinary account from [here](https://cloudinary.com/users/register/free)
Get Maven from [here](https://maven.apache.org/download.cgi)

```sh
git clone https://github.com/bedirhannbayrak/hrms.git
cd hrms
```

##### Set cloudinary keys
![cloudinary keys](https://raw.githubusercontent.com/bedirhannbayrak/hrms/master/assets/cloudinary-keys.png)

Get your cloud name api key and api secret from account details

Go to `docker-compose.yml`

Edit this section in the file
```yml
    environment:
      - PROFILE=docker
      - CLOUDINARY_CLOUD_NAME=your-cloud-name
      - CLOUDINARY_API_KEY=yourapikey
      - CLOUDINARY_API_SECRET=yourapisecret
```
Fill in the fields with your cloudinary informations
in the `hrms` directory

Run this command
```sh
./mvnw clean install
```
```sh
docker-compose up -d --build
```
Check http://localhost:8080/swagger-ui.html

---
## Running Application without Docker

### Dependencies
- Postgresql
- Maven
- Cloudinary Account

Start postgres server on your local machine 

```sh
git clone https://github.com/bedirhannbayrak/hrms.git
cd hrms\src\main\resources
```
Edit application.yml file 


![application.yml](https://raw.githubusercontent.com/bedirhannbayrak/hrms/master/assets/application.yml.png)

Change the relevant places according to your database information

```sh
$ cd ../../../ # going to hrms directory
./mvnw clean install
cd target
```
Run java application
```sh
java -Dspring.profiles.active=local -Dcloudinary.cloud-name=YOURCLOUDNAME -Dcloudinary.api-key=YOURAPIKEY -Dcloudinary.api-secret=YOURAPISECRET -jar hrms.jar
```
We have entered our own information in the local profile in the `application.yml` file. Here we have started our application from this spring profile with the command `"-Dspring.profiles.active=local"`


Our app should have run successfully


Check http://localhost:8080/swagger-ui.html

## License

MIT
