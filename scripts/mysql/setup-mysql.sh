#!/bin/bash

function create_db() {
  db_name=$1

  MYSQL=`which mysql`
  CREATE_DB="CREATE DATABASE IF NOT EXISTS ${db_name};"

  echo "Creating database."

  ${MYSQL} -uroot -p -e "${CREATE_DB}"

  echo "Database ${db_name} created successfuly."
}

function grant_access() {
  db_user=$1
  db_password=$2

  MYSQL=`which mysql`
  GRANT_ACCESS="GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO ${db_user} IDENTIFIED BY ${db_password};"

  echo "Granting access to user ${db_user} to the database."

  ${MYSQL} -uroot -p -e "${GRANT_ACCESS}"

  echo "Access granted to user ${db_user}."
}

function create_schema() {
  db_name=$1
  db_schema_path=$2

  MYSQL=`which mysql`
  USE_DB="USE ${db_name};"
  CREATE_SCHEMA="SOURCE ${db_schema_path};"

  echo "Creating schema."

  ${MYSQL} -uroot -p -e "${USE_DB}${CREATE_DB}"

  echo "Created schema on ${db_name} successfully."
}


DB_NAME='epcis'
DB_USERNAME='epcis'
DB_PASSWORD='epcis'
DB_SCHEMA_PATH='../../files/epcis-repository-0.5.0.xml'

echo "Configuring MySQL database."

create_db ${DB_NAME}
grant_access ${DB_USERNAME} ${DB_PASSWORD}
create_schema ${DB_NAME} ${DB_SCHEMA_PATH}
