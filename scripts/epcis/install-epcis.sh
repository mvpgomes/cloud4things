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


function extract() {

  zip_archive=$1
  destination=$2

  if [ ! -d ${destination} ]; then
    if [[ ${zip_archive} == *".zip"* ]]; then

      set +e
      unzip_cmd=$(which unzip)
      set -e

      if [[ -z ${unzip_cmd} ]]; then
        echo "Cannot extract ${zip_archive}: 'unzip' command not found."
        exit 1
      fi

      inner_name=$(unzip -qql "${zip_archive}")
      echo "Unzipping ${zip_archive}"
      unzip ${zip_archive}

      echo "Moving ${inner_name} to ${destination}"
      mv ${inner_name} ${destination}

    else

      # assuming tarball if the archive is not a zip.
      # we dont check that tar exists since if we made it
      # this far, it definitely exists (nodejs used it)
      inner_name=$(tar -tf "${zip_archive}" | grep -o '^[^/]\+' | sort -u)
      echo "Untaring ${zip_archive}"
      tar -zxvf ${zip_archive}

      echo "Moving ${inner_name} to ${destination}"
      mv ${inner_name} ${destination}
    fi
  fi
}



TEMP_DIR='/tmp'
EPCIS_DIR='/tmp/epcis'
EPCIS_MIRROR='https://oss.sonatype.org/content/repositories/public/org/fosstrak/epcis/epcis-repository/0.5.0/'
EPCIS_TARBALL='epcis-repository-0.5.0-bin-with-dependencies.zip'

cd ${TEMP_DIR}
#download ${EPCIS_MIRROR}/${EPCIS_TARBALL} ${EPCIS_TARBALL}
extract ${EPCIS_TARBALL} ${EPCIS_DIR}

ctx logger info 'Fosstrak EPCIS repository installed successfuly.'
