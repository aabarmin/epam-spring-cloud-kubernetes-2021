#!/bin/bash

# Building the app
cd ..

echo "Building JAR files"
mvn clean package spring-boot:build-image -DskipTests