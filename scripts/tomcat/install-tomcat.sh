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

  else
    echo "Could not extract."
  fi
}


TMP_DIR='/tmp'
TOMCAT_DIR='/tmp/tomcat'
TOMCAT_MIRROR='http://mirrors.ibiblio.org/apache/tomcat/tomcat-7/v7.0.59/bin'
TOMCAT_TARBALL='apache-tomcat-7.0.59.tar.gz'
TOMCAT_TARBALL_NAME='apache-tomcat-7.0.59'

cd ${TMP_DIR}
download ${TOMCAT_MIRROR}/${TOMCAT_TARBALL} ${TOMCAT_TARBALL}
untar ${TOMCAT_TARBALL} ${TOMCAT_DIR}

export CATALINA_HOME=${TOMCAT_DIR}

echo 'Tomcat installed successfuly.'
