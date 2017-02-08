#!/bin/bash

if [ $# -ne 1 ]; then
    echo $0: usage: Input File not found
    exit 1
fi

name=$1

java -cp .. TLCompiler/TLCompiler $name