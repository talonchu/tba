#!/bin/bash

basepath=$(cd `dirname $0`; pwd)

cd $basepath
cd ../../
git pull

if [ "$1" = "prod" ]
then
        echo "Maven Build Profile: prod"
        mvn clean package -Pprod -Dmaven.test.skip=true
else
        echo "Maven Build Profile: "$1
        mvn clean package -P$1 -Dmaven.test.skip=true
fi

if docker images | grep -w 'app/team-building'
	then docker rmi 'app/team-building'
fi

mv target/tba.war docker/app/
cd docker/app/
docker build -t app/team-building .
