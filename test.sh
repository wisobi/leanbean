#!/usr/bin/env bash
if [ -z "$1" ]
  then
    echo "No ip for leanbean-proxy provided. On OS X, run ./test.sh $(docker-machine ip leanbean)"
    exit 127
fi

HTTP_STATUS=$(curl -H "Content-Type: application/json" -s -o /dev/null -w "%{http_code}" -X POST -d '{"alias":"Bob","uuid":"1ab9f8ta32bg2eq2"}' http://$1/leanbean/v1/devices/)

if [ $HTTP_STATUS -eq 201 ]
then
  echo "Test run successful"
  exit 0;
else
  echo "Test failures"
  exit 1;
fi
