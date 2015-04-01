#!/usr/bin/env bash

CONTAINER_NAME='fosstrak_ale'

docker run -it --name ${CONTAINER_NAME} --link fosstrak_capture:capturerepo -p 8082:8082 -d mvpgomes/fosstrak_ale
