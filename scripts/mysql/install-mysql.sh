#!/bin/bash -e

function download() {

  url=$1
  name=$2

  if [ -f "`pwd`/${name}" ]; then
    echo "`pwd`/${name} already exists, no need to download"
  else
    # download the given directory
    echo "Dowloading ${url} to `pwd`/${name}"

    set +e
    curl_cmd=$(which curl)
    wget_cmd=$(which wget)
    set -e

    if [[ ! -z ${curl_cmd} ]]; then
      curl -L -o ${name} ${url}
    elif [[ ! -z ${wget_cmd} ]]; then
      wget -O ${name} ${url}
    else
      echo "Failed to download ${url}: Neither 'cURL' nor 'wget' were found on the system"
      exit 1;
    fi
  fi
}

function untar() {

  tar_archive=$1
  destination=$2

  if [ ! -d ${destination} ]; then
    inner_name=$(tar -tf "${tar_archive}" | grep -o '^[^/]\+' | sort -u)
    echo "Untaring ${tar_archive}"
    tar -zxvf ${tar_archive}

    echo "Moving ${inner_name} to ${destination}"
    mv ${inner_name} ${destination}

  fi
}

TMP_DIR='/tmp'
MYSQL_HOME='/tmp/mysql'
MYSQL_MIRROR='http://dev.mysql.com/get/Downloads/MySQL-5.6/'
MYSQL_TARBALL='mysql-server_5.6.23-1ubuntu14.10_i386.deb-bundle.tar'

cd ${TMP_DIR}
download ${MYSQL_MIRROR}/${MYSQL_TARBALL} ${MYSQL_TARBALL}
untar ${MYSQL_TARBALL} ${MYSQL_HOME}

export MYSQL_HOME=${MYSQL_HOME}
export PATH=${PATH}:${MYSQL_HOME}/bin

echo 'MySQL installed successfuly.'
