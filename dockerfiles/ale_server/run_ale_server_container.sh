#!/usr/bin/env bash

CONTAINER_NAME='fosstrak_ale_server'

docker run -it --name ${CONTAINER_NAME} --link fosstrak_capture:capturerepo -p 8082:8080 -d mvpgomes/fosstrak_ale_server
