#!/bin/bash

function unzip() {

  zip_archive=$1
  destination=$2

  if [ ! -d ${destination} ]; then
    if [[ ${zip_archive} == *".zip"* ]]; then

      set +e
      unzip_cmd=$(which unzip)
      set -e

      if [[ -z ${unzip_cmd} ]]; then
        ctx logger error "Cannot extract ${zip_archive}: 'unzip' command not found."
        exit 1
      fi

      inner_name=$(unzip -qql "${zip_archive}" | sed -r '1 {s/([ ]+[^ ]+){3}\s+//;q}')
      ctx logger info "Unzipping ${zip_archive}"
      unzip ${zip_archive}

      ctx logger info "Moving ${inner_name} to ${destination}"
      mv ${inner_name} ${destination}

    else

      # assuming tarball if the archive is not a zip.
      # we dont check that tar exists since if we made it
      # this far, it definitely exists (nodejs used it)
      inner_name=$(tar -tf "${zip_archive}" | grep -o '^[^/]\+' | sort -u)
      ctx logger info "Untaring ${zip_archive}"
      tar -zxvf ${zip_archive}

      ctx logger info "Moving ${inner_name} to ${destination}"
      mv ${inner_name} ${destination}
    fi
  fi
}
