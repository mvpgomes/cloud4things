#!/bin/bash

set -e

function download() {

  url=$1
  name=$2

  if [ -f "`pwd`/${name}" ]; then
    ctx logger info "`pwd`/${name} already exists, no need to download"
  else
    # download the given directory
    ctx logger info "Dowloading ${url} to `pwd`/${name}"

    set +e
    curl_cmd=$(which curl)
    wget_cmd=$(which wget)
    set -e

    if [[ ! -z ${curl_cmd} ]]; then
      curl -L -o ${name} ${url}
    elif [[ ! -z ${wget_cmd} ]]; then
      wget -O ${name} ${url}
    else
      ctx logger error "Failed to download ${url}: Neither 'cURL' nor 'wget' were found on the system"
      exit 1;
    fi
  fi
}
