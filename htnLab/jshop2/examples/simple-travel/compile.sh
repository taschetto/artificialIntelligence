#!/bin/sh
if [ $# -lt 3 ]
	then echo "Usage ${0} <domain-name> <problem-name> <gui|nogui> [a|<0..n>]"
	exit 1
else
	echo $CLASSPATH
	# You will need to get jshop2 and put its libraries in ../jshop2
	# export CLASSPATH=../jshop2/antlr.jar:../jshop2/JSHOP2.jar:.
	export CLASSPATH=.:/Users/meneguzzi/Dropbox/Pankaj-Commitments-Planning/jshop2/antlr.jar
	export CLASSPATH=${CLASSPATH}:/Users/meneguzzi/Dropbox/Pankaj-Commitments-Planning/jshop2/JSHOP2.jar
	java JSHOP2.InternalDomain ${1}.jshop
	java JSHOP2.InternalDomain -r${4} ${2}.jshop
	javac *.java
	if [ $3 = "gui" ]; then
		java gui $2
	else
		java run $2
	fi
	rm *.class
	rm ${1}.java ${2}.java
fi