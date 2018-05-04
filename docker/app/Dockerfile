FROM tomcat:8.0.43-jre7
MAINTAINER malachi ye <malachi.ye@perficient.com>

RUN rm -rf /usr/local/tomcat/webapps/*
ADD tba.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
EXPOSE 8009

# start tomcat
CMD ["catalina.sh", "run"]