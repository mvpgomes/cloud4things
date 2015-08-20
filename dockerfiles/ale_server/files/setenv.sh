#!/bin/sh

## Get the EC2 instance public ip address.
RMI_HOST=$(hostname --ip-address)

## Set the CATALINA_OPTS environment variable. By default the Java RMI uses
## the port 1099. To change this option add the following field to the
## CATALINA_OPTS variable:
## -Dcom.sun.management.jmxremote.authenticate.jmxremote.port={port to access}
export CATALINA_OPTS="-Djava.rmi.server.logCalls=true -Djava.rmi.server.hostname=$RMI_HOST"
