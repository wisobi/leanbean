#!/bin/bash
cordova platform add android
cordova platform add ios
cordova plugin add org.apache.cordova.device
cordova plugin add org.apache.cordova.statusbar
cordova plugin add https://github.com/EddyVerbruggen/LaunchMyApp-PhoneGap-Plugin.git --variable URL_SCHEME=leanbean
