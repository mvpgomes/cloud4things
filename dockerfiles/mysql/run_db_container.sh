#!/usr/bin/env bash

CONTAINER_NAME='fosstrak_db'
DB_ROOT_PASSWORD='rootroot'

docker run --name ${CONTAINER_NAME} -e MYSQL_ROOT_PASSWORD=${DB_ROOT_PASSWORD} -d mvpgomes/epcisdb
