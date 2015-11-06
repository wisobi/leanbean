#!/usr/bin/env bash
HTTP_STATUS=$(curl -H "Content-Type: application/json" -s -o /dev/null -w "%{http_code}" -X POST -d '{"alias":"Bob","uuid":"1ab9f8ta452bg2eq2"}' http://leanbean-proxy/leanbean/v1/devices/)
echo "HTTP_STATUS is $HTTP_STATUS"

: <<'END'
if [ -z "$1" ]
  then
    echo "No ip for leanbean-proxy provided. On OS X, run ./test.sh $(docker-machine ip leanbean)"
    exit 127
fi

CMD="curl -H \"Content-Type: application/json\" -s -o /dev/null -w \"%{http_code}\" -X POST -d '{\"alias\":\"Bob\",\"uuid\":\"1ab9f8ta452bg2eq2\"}' http://$1/leanbean/v1/devices/"
echo "Running $CMD"
HTTP_STATUS=$(eval $CMD)
echo "HTTP Status ${HTTP_STATUS}"

if [ $HTTP_STATUS -eq 201 ]
then
  echo "Test run successful"
  exit 0;
else
  echo "Test failures"
  exit 1;
fi
END
