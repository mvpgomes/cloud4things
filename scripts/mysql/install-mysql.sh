#!bin/bash

MYSQL_DIR='/usr/local/'
MYSQL_MIRROR=''
MYSQL_TARBALL='mysql-server_5.6.23-1ubuntu14.10_i386.deb-bundle.tar'

cd ${MYSQL_DIR}
../download.sh ${MYSQL_MIRROR}/${MYSQL_TARBALL} ${MYSQL_TARBALL}
../untar.sh ${MYSQL_TARBALL} ${MYSQL_DIR}

ctx logger info 'MySQL installed successfuly.'
