#!/bin/sh

javadoc -protected -sourcepath src -d doc -windowtitle "SimpleJava Reference" `find src -name "*.java"`

