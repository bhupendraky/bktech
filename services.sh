#!/bin/bash

cmd=${1}

cd "$(dirname $0)"

log_dir=~/project/logs
mkdir -p $log_dir

pid () {
  jcmd | grep $1-service-1.0.2-SNAPSHOT.jar | cut -d ' ' -f1
}
startService () {
  local service=$1
  local service_pid=$(pid $service)
  if [ -z "$service_pid" ]; then
    echo Starting the service $service-service
    nohup ./$service-service/start.sh >> $log_dir/$service-service.log 2>&1 &
  else
    echo The $service service is already runnig with pid: $service_pid
  fi
}

stopService () {
  local service_pid=$(pid $1)
  if [ -n "$service_pid" ]; then
    kill -9 $service_pid
  fi
}
if [ "$cmd" = 'start' ]; then
  startService config
  sleep 30
  startService eureka
  startService gateway
elif [ "$cmd" = 'stop' ]; then
  stopService gateway
  stopService eureka
  stopService config
else
  jcmd | grep service-1.0.2-SNAPSHOT.jar
fi

