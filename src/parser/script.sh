#!/bin/bash

yacc -v parser.y
yacc -J parser.y
sed -i '1s/^/package parser; /' Parser.java
sed -i '1s/^/package parser; /' ParserVal.java
