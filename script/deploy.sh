#!/usr/bin/env bash

REPOSITORY=/home/ubuntu
PROJECT=thechef.site

cd $REPOSITORY

JAR_NAME=$(ls $REPOSITORY/$PROJECT/  | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/$PROJECT/$JAR_NAME

CURRENT_PID=$(pgrep -fl $JAR_PATH | awk '{print $1}')

if [ -z $CURRENT_PID ]
then
  echo "> Nothing to end."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> $JAR_PATH deploy"

nohup java -jar \
  -Dspring.config.location=file:///$REPOSITORY/$PROJECT/application.properties \
  $JAR_PATH >> /$REPOSITORY/$PROJECT/log/nohup_log.out 2>&1 &
