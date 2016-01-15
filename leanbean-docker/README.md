LeanBean Dockerized
========

LeanBean can preferably run in Docker containers.

#### Build Docker images

```bash
$ docker build -t wisobi/leanbean-dev leanbean-docker/dev
$ docker build -t wisobi/leanbean-tomcat leanbean-docker/tomcat
$ docker build -t wisobi/leanbean-mysql leanbean-docker/mysql
$ docker build -t wisobi/leanbean-nginx leanbean-docker/nginx
```

#### Start Docker containers

The names of the containers are used as host names for the containers to interact. E.g. in context.xml the db host is leanbean-db.

```bash
$ docker run -d --name leanbean-db wisobi/leanbean-mysql
$ docker run -d --name leanbean-api --link leanbean-db:leanbean-db wisobi/leanbean-tomcat
$ docker run -d -p 80:80 --name leanbean-proxy --link leanbean-api:leanbean-api wisobi/leanbean-nginx
$ docker run -it -d --name leanbean-dev --link leanbean-db:leanbean-db --link leanbean-api:leanbean-api -v ${HOME}/wisobi/leanbean:/opt/leanbean --privileged -v /dev/bus/usb:/dev/bus/usb wisobi/leanbean-dev
$ docker run -it -d --name leanbean-dev -v ${HOME}/wisobi/leanbean:/opt/leanbean -v ${HOME}/.m2/repository:/root/.m2/repository wisobi/leanbean-dev
$ docker run -it -d --name leanbean-dev --link leanbean-db:leanbean-db --link leanbean-api:leanbean-api -v ${HOME}/wisobi/leanbean:/opt/leanbean -v ${HOME}/.m2/repository:/root/.m2/repository wisobi/leanbean-dev
$ docker run -it -d --name leanbean-build --link leanbean-api:leanbean-api -v ${HOME}/wisobi/leanbean:/opt/leanbean -v ${HOME}/.m2:/root/.m2 wisobi/leanbean-build
```
The development container links to both the database and application server containers. Two volumes are mounted, the first one is the directory containing the source code, so this needs to be updated accordingly to match your path. The second one mounts the USB devices which makes it possible to access mobile devices connected locally to the laptop.

#### Deploy LeanBean API

```bash
$ docker attach leanbean-dev
$ cd /opt/leanbean/leanbean-api
$ mvn -Dmaven.tomcat.url=http://leanbean-app:8080/manager/text tomcat7:redeploy
$ boot2docker ssh -vnNTL 5037:localhost:5037 -vnNTL 5554:localhost:5554 -vnNTL 5555:localhost:5555
```

#### Deploy LeanBean App to Device
```bash
$ ./adb install -r ~/wisobi/leanbean/leanbean-mobile/platforms/android/build/outputs/apk/android-debug.apk
```