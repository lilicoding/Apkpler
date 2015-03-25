#! /bin/sh

APP=$1
APP_NAME=`basename $APP`

ANDROID_PLATFORMS=/Users/li.li/Project/github/android-platforms

LIB_PATH=.:libs/axml-2.0.jar:libs/AXMLPrinter2.jar:libs/junit.jar:libs/jdom-2.0.5.jar:libs/apkpler-plugin-icc.jar:libs/apkpler-plugin-ais.jar:libs/apkpler-interface.jar:Apkpler.jar

java -Xmx8G -cp $LIB_PATH soot.jimple.apkpler.framework.test.Main $APP $ANDROID_PLATFORMS 