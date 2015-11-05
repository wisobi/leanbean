#!/usr/bin/env bash
docker build -t wisobi/leanbean-tomcat tomcat
docker build -t wisobi/leanbean-mysql mysql
docker build -t wisobi/leanbean-nginx nginx
docker build -t wisobi/leanbean-dev dev
docker build -t wisobi/leanbean-build build
docker login
docker push wisobi/leanbean-tomcat
docker push wisobi/leanbean-mysql
docker push wisobi/leanbean-nginx

