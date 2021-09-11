#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
cp -R ../src/main/resources/ ../bin
if ! find ../src/main/java -name "*.java" -print | xargs javac -cp ../src/main/java -Xlint:none -d ../bin
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -cp ../bin com.alexooi.duke.Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT

if type dos2unix &> /dev/null
then
    dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT
else
    echo "dos2unix command not found. To prevent potential issues, please install dos2unix."
fi

find ../bin -type f

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi