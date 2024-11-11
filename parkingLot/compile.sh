#!/bin/bash
# chmod +x parkingLot/compile.sh
# ./parkingLot/compile.sh
find . -name "*.class" -type f -delete
javac $(find . -name "*.java")