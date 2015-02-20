LeanBean Dockerized
========

LeanBean can preferably run in Docker containers.

#### Build Docker images

```bash
$ docker build -t wisobi/leanbean-dev leanbean-docker/dev
$ docker build -t wisobi/leanbean-tomcat leanbean-docker/tomcat
$ docker build -t wisobi/leanbean-mysql leanbean-docker/mysql
```

#### Start Docker containers

The names of the containers are used as host names for the containers to interact. E.g. in context.xml the db host is leanbean-db.

```bash
$ docker run -d --name leanbean-db wisobi/leanbean-mysql
$ docker run -d -p 80:8080 --name leanbean-app --link leanbean-db:leanbean-db wisobi/leanbean-tomcat
$ docker run -it -d --name leanbean-dev --link leanbean-db:leanbean-db --link leanbean-app:leanbean-app -v ${HOME}/wisobi/leanbean:/opt/leanbean wisobi/leanbean-dev bash
```

#### Deploy LeanBean API

```bash
$ docker attach leanbean-dev
$ cd /opt/leanbean/leanbean-api
$ mvn -Dmaven.tomcat.url=http://leanbean-app:8080/manager/text tomcat7:redeploy
```