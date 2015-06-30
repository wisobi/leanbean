FROM tomcat:8-jre7
MAINTAINER Jonas Björk <bjork@wisobi.com>

# Install dependencies
RUN apt-get update && apt-get install -y \
  libmysql-java

# Setup Tomcat
RUN cp /usr/share/java/mysql-connector-java.jar ${CATALINA_HOME}/lib
RUN rm -r ${CATALINA_HOME}/webapps/ROOT ${CATALINA_HOME}/webapps/docs ${CATALINA_HOME}/webapps/examples ${CATALINA_HOME}/webapps/host-manager ${CATALINA_HOME}/conf/server.xml
ADD tomcat-users.xml ${CATALINA_HOME}/conf/
ADD server.xml ${CATALINA_HOME}/conf/
ADD setenv.sh ${CATALINA_HOME}/bin/

EXPOSE 8080

CMD exec ${CATALINA_HOME}/bin/catalina.sh run