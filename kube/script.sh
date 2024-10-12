#chmod +x ./kube/script.sh
#./kube/script.sh

#!/bin/sh
clear

if ! command -v mvn 2>&1 >/dev/null || (! command -v podman 2>&1 >/dev/null && ! command -v docker 2>&1 >/dev/null)
then
    echo "mvn or podman or docker could not be found"
    exit 1
fi

echo "---------------------------------[ Building environment           ]---------------------------------"
KUBE_PATH=$(dirname "$(realpath ${BASH_SOURCE:-$0})")
PROYECT_PATH=${KUBE_PATH%/kube}
cd $PROYECT_PATH
NPM=npm
MVN=mvn
command -v podman 2>&1 >/dev/null && CONTAINER=podman || CONTAINER=docker
IMAGE=collector/web-server
TAG=$(git rev-parse --short HEAD)
LATEST=latest
[ -d data ] || mkdir data

echo "---------------------------------[ Building the nodejs project    ]---------------------------------"
$NPM install
$NPM run build

echo "---------------------------------[ Building the maven project     ]---------------------------------"
$MVN clean install

echo "---------------------------------[ Building the docker image      ]---------------------------------"
$CONTAINER build -t $IMAGE:$TAG .
$CONTAINER tag $IMAGE:$TAG $IMAGE:$LATEST

echo "---------------------------------[ Building the container         ]---------------------------------"
cd $KUBE_PATH
$CONTAINER compose down
$CONTAINER compose up -d
