#! /bin/sh

ANDROID_PLATFORMS=/Users/li.li/Project/github/android-platforms

DIR=../../benchmarks/benchmark-apkpler-plugin-rdc

RESULT=benchmark_test_result.txt
rm -rf $RESULT

for file in `ls $DIR/*.apk`
do
	APP=$DIR/$file
	java -Xmx8G -jar ../../release/Apkpler/Apkpler.jar $APP $ANDROID_PLATFORMS > tmp.txt
	result=`grep "\[CheckPointAnalysis4RDCPlugin\]" tmp.txt | sed 's@\[.*\]@@'`
	
	time=`grep "====>" tmp.txt | sed 's@====>@@'`
	
	
	rm -rf tmp.txt
	
	echo $file,$result,$time >> $RESULT
done

rm -rf sootOutput

rm -rf tmp.txt
rm -rf cp1.txt
rm -rf cp2.txt