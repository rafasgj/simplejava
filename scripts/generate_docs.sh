#!/bin/sh

JAVADOC="/usr/java/jdk1.8.0_211-amd64/bin/javadoc"

"$JAVADOC" -protected -sourcepath src -d doc -windowtitle "SimpleJava Reference" `find src -name "*.java"`

