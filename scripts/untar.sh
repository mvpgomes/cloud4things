#!/bin/bash

function untar() {

  tar_archive=$1
  destination=$2

  if [ ! -d ${destination} ]; then
    inner_name=$(tar -tf "${tar_archive}" | grep -o '^[^/]\+' | sort -u)
    ctx logger info "Untaring ${tar_archive}"
    tar -zxvf ${tar_archive}

    ctx logger info "Moving ${inner_name} to ${destination}"
    mv ${inner_name} ${destination}
  fi
}
