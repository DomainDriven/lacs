#!/bin/bash

DB=lacs
FOLDER=src/main/resources/data

mongo $DB --eval "db.dropDatabase()" && mongoimport --db lacs --collection seminars --file $FOLDER/seminars.json && mongoimport --db lacs --collection instructors --file $FOLDER/instructors.json && mongoimport --db lacs --collection workers --file $FOLDER/workers.json