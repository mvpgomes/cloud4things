#!/usr/bin/env bash

CONTAINER_NAME='fosstrak_ale_server'

docker run -it --add-host=docker:192.168.59.3 --name ${CONTAINER_NAME} -p 8082:8080 -d cloud4things/fosstrak_ale
