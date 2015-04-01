#!/usr/bin/env bash

CONTAINER_NAME='fosstrak_epcis'

docker run -it --name ${CONTAINER_NAME} --link fosstrak_db:db -p 8080:8080 -d mvpgomes/fosstrak_epcis
