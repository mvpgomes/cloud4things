#!/usr/bin/env bash

set -e
set -x

. mysql/run_db_container.sh
. epcis/run_epcis_container.sh
. capture/run_capture_container.sh
. ale_server/run_ale_server_container.sh
