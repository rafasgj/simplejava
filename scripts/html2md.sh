#!/bin/sh

#
# This does not really work well with much more work into it.
# I'd be very pleased if someone takes the task to convert the
# class reference javadocs (HTML) to Markdown so that it can be
# the project's Wiki. -- Rafael
#

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
    f="`echo "$f" | sed 's#//*#/#g' | cut -d/ -f2-`"
    echo "Converting ${f}"
    dir="${TARGET}/`dirname "${f}"`"
    mkdir -p "${dir}/"
    pandoc -f html -t markdown -o "$dir/`basename "$f" .html`.md" -s "$SRC/$f"
done

