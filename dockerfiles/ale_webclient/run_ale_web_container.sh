#!/usr/bin/env bash

CONTAINER_NAME='fosstrak_ale_webclient'

docker run -it --name ${CONTAINER_NAME} --link fosstrak_ale_server:aleserver -p 8083:8080 -d mvpgomes/fosstrak_ale_webclient
