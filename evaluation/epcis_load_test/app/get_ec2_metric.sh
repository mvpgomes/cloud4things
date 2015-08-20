#!/bin/bash

# This scripts executes requests to AWS Cloud Watch API to get metric statistics from the EC2 instances
# ------------------------------------------------------------------------------------------------------
# $1 : The metric name = (CPUUtilization, NetworkIn, etc)
# $2 : The start time = (hh:mm:ssTdd/mm/yy)
# $3 : The end time = (hh:mm:ssTdd/mm/yy)
# $4 : The statistics = (Average, Minimum, Maximum)
# $5 : The instance id = (i-xxxxxx)
 aws cloudwatch get-metric-statistics --metric-name $1 --start-time $2 --end-time $3 --period 60 --namespace AWS/EC2 --statistics $4 --dimensions Name=InstanceId,Value=$5
