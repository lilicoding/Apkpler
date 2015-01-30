#! /bin/sh

APP=$1
APP_NAME=`basename $APP`

ANDROID_PLATFORMS=/work/users/lli/gitbitbucket/android-platforms

DUR=600

timeout $DUR java -Xmx8G -jar Apkpler.jar $APP $ANDROID_PLATFORMS > sys_out/$APP_NAME.txt 2>&1 

BYTE_SIZE=`unzip -l $APP | grep classes.dex | awk '{print $1}'`
#TIME=`grep "\[Apkpler\]====>" sys_out/$APP_NAME.txt | sed 's@\[Apkpler\]====>@@'`

echo "====>"$BYTE_SIZE >> sys_out/$APP_NAME.txt
