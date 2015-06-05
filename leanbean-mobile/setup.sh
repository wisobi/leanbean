#!/bin/bash
cordova platform add android
#cordova platform add ios
cordova plugin add cordova-plugin-device
cordova plugin add cordova-plugin-statusbar
cordova plugin add cordova-plugin-whitelist
cordova plugin add https://github.com/EddyVerbruggen/LaunchMyApp-PhoneGap-Plugin.git --variable URL_SCHEME=leanbean
