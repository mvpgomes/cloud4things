#!/usr/bin/env bash

CONTAINER_NAME='fosstrak_capture'

docker run -it --name ${CONTAINER_NAME} --link fosstrak_epcis:epcisrepo -p 9999:9999 -d cloud4things/fosstrak_capture
