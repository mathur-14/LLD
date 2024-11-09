#!/bin/bash
find . -name "*.class" -type f -delete
javac $(find . -name "*.java")