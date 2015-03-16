#!bin/bash

TEMP_DIR='/tmp'
EPCIS_MIRROR='https://oss.sonatype.org/content/repositories/public/org/fosstrak/epcis/epcis-repository/0.5.0/'
EPCIS_TARBALL='epcis-repository-0.5.0-bin-with-dependencies.zip'

cd ${TEMP_DIR}
../download.sh ${EPCIS_MIRROR}/${EPCIS_TARBALL} ${EPCIS_TARBALL}
../untar.sh ${EPCIS_TARBALL} ${TEMP_DIR}

ctx logger info 'Fosstrak EPCIS repository installed successfuly.'
