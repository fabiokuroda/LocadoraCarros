FROM mysql:latest
MAINTAINER me
ENV MYSQL_DATABASE=db_locadora \
    MYSQL_ROOT_PASSWORD=password
ADD db_locadora.sql /docker-entrypoint-initdb.d
EXPOSE 5454
