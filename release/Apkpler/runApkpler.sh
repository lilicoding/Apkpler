#! /bin/sh

APP=$1

APP_NAME=`basename $APP`

timeout $DUR java -Xmx8G -jar Apkpler.jar $APP > sys_out/$APP_NAME.txt 2>&1 
