#!/bin/bash

FOLDER=src/main/resources/data

rm -rf $FOLDER/* && mongoexport --db lacs --collection seminars --out $FOLDER/seminars.json && mongoexport --db lacs --collection workers --out $FOLDER/workers.json && mongoexport --db lacs --collection instructors --out $FOLDER/instructors.json
