#! /bin/sh

DIR=$1
DUR=6000

for app in `ls $DIR`
do
	echo $DIR/$app

	gtimeout $DUR ./runApkpler.sh $DIR/$app > logs/$app.txt 2>&1

done
