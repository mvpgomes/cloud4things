#!/usr/bin/env bash

set -e
set -x

. mysql/stop_db_container.sh
. epcis/stop_epcis_container.sh
. capture/stop_capture_container.sh
. ale_server/stop_ale_server_container.sh
. ale_webclient/stop_ale_web_container.sh
