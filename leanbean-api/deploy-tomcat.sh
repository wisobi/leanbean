#!/usr/bin/env bash
if [ -z "$1" ]
  then
    echo "No environment supplied"
  else
    case "$1" in
        "prod") echo "Deploying to environment prod."
                URL="http://aws.asdf.com"
                ;;
        "test") echo "Deploying to environment test."
                URL="http://leanbean-proxy"
                ;;
        *)      echo "Unknown environment, cannot deploy."
                exit 127;
                ;;
    esac
fi

if [ -z "$2" ]
  then
    echo "No Tomcat user provided"
  else
    TOMCAT_USER=$2
fi

if [ -z "$3" ]
  then
    echo "No Tomcat password provided"
  else
    TOMCAT_PASS=$3
fi


CMD="mvn --batch-mode \
-Dmaven.tomcat.url=${URL}/manager/text \
tomcat7:redeploy"
echo "Running $CMD"
eval $CMD
