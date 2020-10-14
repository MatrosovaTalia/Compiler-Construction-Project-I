#!/bin/sh
java -cp . MetaMain
javap -c MetaMain.class > bytecode.bc
