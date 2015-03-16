#!bin/bash

TOMCAT_DIR='/usr/local/'
TOMCAT_MIRROR='http://mirrors.ibiblio.org/apache/tomcat/tomcat-7/v7.0.59/bin'
TOMCAT_TARBALL='apache-tomcat-7.0.59.tar.gz'
TOMCAT_TARBALL_NAME='apache-tomcat-7.0.59'

cd ${TOMCAT_DIR}
../download.sh ${TOMCAT_MIRROR}/${TOMCAT_TARBALL} ${TOMCAT_TARBALL}
../untar.sh ${TOMCAT_TARBALL} ${TOMCAT_DIR}

export CATALINA_HOME=${TOMCAT_DIR}/${TOMCAT_TARBALL_NAME}

ctx logger info 'Tomcat installed successfuly.'
