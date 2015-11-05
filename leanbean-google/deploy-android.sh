#!/usr/bin/env bash
if [ -z "$1" ]
  then
    echo "No password for key store provided."
    exit 127
fi

if [ -z "$2" ]
  then
    echo "No password for certificate provided."
    exit 127
fi
cd ../leanbean-mobile;
cordova build android --release -- --buildConfig=build.json --storePassword=$1 --password=$2;
cp platforms/android/build/outputs/apk/android-release.apk ../leanbean-goole/src/main/resources/;
cd ../leanbean-google;
mvn exec:java