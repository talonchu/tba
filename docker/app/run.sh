#!/bin/sh

echo "=> run application"

docker run -d \
-it	\
--cap-add SYS_PTRACE \
--restart=always \
-p 8180:8080 \
-p 8109:8009 \
-v /home/perficient/app/team-building/logs:/tomcat/logs/ \
-v /etc/localtime:/etc/localtime:ro \
--add-host gdcsso:10.2.1.117 \
--add-host gdcsso.perficient.com:10.2.1.117 \
--name team-building \
app/team-building \

echo "=> run application successfully"

docker logs --tail=50 -f team-building
