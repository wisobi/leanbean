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

CMD="mvn --batch-mode \
-Dmaven.tomcat.url=${URL}/manager/text \
tomcat7:redeploy"
echo "Running $CMD"
eval $CMD
