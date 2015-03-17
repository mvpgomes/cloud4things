#!/bin/bash

EPCIS_HOME='/tmp/epcis'
EPCIS_WAR='epcis-repository-0.5.0.war'
TOMCAT_WEBAPP_DIR='/tmp/tomcat/webapps'

echo "Moving epcis.war to Tomcat Webapp directory."
cd ${EPCIS_HOME}
mv ${EPCIS_WAR} ${TOMCAT_WEBAPP_DIR}
echo "Moved .war to Tomcat webapp directory."
