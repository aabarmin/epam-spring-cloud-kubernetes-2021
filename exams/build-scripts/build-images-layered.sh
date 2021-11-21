#!/bin/bash

function unpack() {
  FOLDER=$1
  NAME=$2
  VERSION=$3

  CURRENT=$(pwd)

  cd $FOLDER/target
  java -jar -Djarmode=layertools ${NAME}-${VERSION}.jar extract

  cd $CURRENT
}

function build() {
  FOLDER=$1
  NAME=$2

  docker build -f ./build-scripts/docker/layered/Dockerfile \
    --build-arg JAR_FOLDER=${FOLDER}/target \
    -t ${NAME}:latest \
    -t ${NAME}:layered .
}

APP_VERSION=0.0.1-SNAPSHOT

# Building the app
cd ..

echo "Building JAR files"
mvn clean package -DskipTests

echo "Unpacking JARs"
unpack config-server config-server ${APP_VERSION}
unpack discovery-service-eurika discovery-service ${APP_VERSION}
unpack examinator examinator ${APP_VERSION}
unpack mathematic mathematic ${APP_VERSION}
unpack theology theology ${APP_VERSION}

echo "Building Docker image"
build config-server application/config-server
build discovery-service-eurika application/discovery-service
build examinator application/examinator
build mathematic application/provider-math
build theology application/provider-theology