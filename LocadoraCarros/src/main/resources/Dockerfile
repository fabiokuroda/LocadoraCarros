FROM java:8
LABEL maintainer=“fabio.kuroda@gmail.com”
EXPOSE 8080
ADD /LocadoraCarros-0.0.1-SNAPSHOT.jar LocadoraCarros-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","LocadoraCarros-0.0.1-SNAPSHOT.jar"]

FROM mysql:latest
MAINTAINER me
ENV MYSQL_DATABASE=db_locadora \
    MYSQL_ROOT_PASSWORD=password
ADD db_locadora.sql /docker-entrypoint-initdb.d
EXPOSE 3306
