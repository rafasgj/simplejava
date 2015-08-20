#!/bin/sh

if [ $# -ne 2 ]
then
    echo "usage: `basename $0` <javadoc dir> <markdown dir>" >&2
    exit 1
fi

SRC="$1"
TARGET="$2"
shift 2

FILES="`find "${SRC}/" -name '*.html'`"

for f in $FILES
do
    dir="${TARGET}/`dirname "$f"`"
    mkdir -p "${dir}/"
    pandoc -f html -t markdown -o "${dir}/`basename "$f" .html`.md" -s "$f"
done

