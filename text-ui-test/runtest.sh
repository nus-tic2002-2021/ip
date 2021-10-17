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

# delete tasks.json from previous run
if [ -e "./tasks.json" ]
then
    rm tasks.json
fi

# compile the code into the bin folder, terminates if error occurred
if ! bazel build //:TerminalDuke 
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
../bazel-bin/TerminalDuke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED [UI TESTS]"
else
    echo "Test result: FAILED [UI TESTS]"
    exit 1
fi

# compare the output to the expected output
diff tasks.json EXPECTED.json
if [ $? -eq 0 ]
then
    echo "Test result: PASSED [SERIALIZATION TESTS]"
    exit 0
else
    echo "Test result: FAILED [SERIALIZATION TESTS]"
    exit 1
fi
