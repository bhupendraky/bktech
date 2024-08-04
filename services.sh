#!/bin/bash

cmd=${1}
sid=${2}

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
  if [ -n "$sid" ]; then
    startService $sid
  else
    #startService config
    #sleep 30
    startService eureka
    startService gateway
  fi
elif [ "$cmd" = 'stop' ]; then
  if [ -n "$sid" ]; then
    stopService $sid
  else
    stopService gateway
    stopService eureka
    #stopService config
  fi
else
  jcmd | grep service-1.0.2-SNAPSHOT.jar
fi

