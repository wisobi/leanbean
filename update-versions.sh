#!/usr/bin/env bash
POM_VERSION=$(mvn -f leanbean-api/pom.xml org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version|grep -Ev '(^\[|Download\w+:)')
VERSION=$(cut -f1 -d"-" <<< "$POM_VERSION")
RELEASE=$(echo $VERSION | ( IFS=".$IFS" ; read a b c && echo $a.$b.$((c + 1)) ))


CMD="mvn -f leanbean-api/pom.xml --batch-mode release:update-versions -DdevelopmentVersion=${RELEASE}-SNAPSHOT"
echo "Running $CMD"

CMD="mvn -f leanbean-google/pom.xml --batch-mode release:update-versions -DdevelopmentVersion=${RELEASE}-SNAPSHOT"
echo "Running $CMD"

CMD="sed -i 's/<widget id=\"com.wisobi.leanbean\" version=\"${VERSION}\"/<widget id=\"com.wisobi.leanbean\" version=\"${RELEASE}\"/g' leanbean-mobile/config.xml"
echo "Running $CMD"