#!/bin/bash

EPCIS_DIR='tmp/epcis'
EPCIS_WAR='epcis-0.5.0.war'
TOMCAT_WEBAPP_DIR='/tmp/tomcat/webapps'

echo "Moving epcis.war to Tomcat Webapp directory."
cd ${EPCIS_DIR}
mv ${EPCIS_WAR} ${TOMCAT_WEBAPP_DIR}
echo "Moved .war to Tomcat webapp directory."
