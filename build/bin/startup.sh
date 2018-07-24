#!/bin/sh

cd `dirname $0`
cd ..
SERVER_HOME=`pwd`
SERVER_BIN_DIR=$SERVER_HOME/bin
CONF_DIR=$SERVER_HOME/config
LOG_DIR=$SERVER_HOME/logs
STDOUT_FILE=$LOG_DIR/stdout.log
echo "server dir is $SERVER_DIR"

# load env-server.sh
if [ -e "$SERVER_BIN_DIR/env-server.sh" ]; then
  . "$SERVER_BIN_DIR/env-server.sh"
else
  echo "ERROR: $SERVER_BIN_DIR/env-server.sh is not fond"
  exit 1
fi

if [ ! -e "$SERVER_HOME/$SERVER_NAME" ]; then
    echo "ERROR: the $SERVER_NAME does not exists!"
    exit 1
fi

if [ ! -d "$LOG_DIR" ]; then
    echo 'log dir is not exists'
    mkdir -p "$LOG_DIR"
fi

PIDS=`ps -f | grep java | grep "${SERVER_NAME}" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The ${SERVER_NAME} already started!"
    echo "PID: $PIDS"
    exit 1
fi

echo -e "Starting the ${SERVER_NAME} ...\c"
echo "JAVA_OPTS=$JAVA_OPTS"

nohup $JAVA_HOME/java $JAVA_OPTS -jar $SERVER_HOME/$SERVER_NAME  --spring.config.location=$CONF_DIR --logging.config=$CONF_DIR/logback-spring.xml > $STDOUT_FILE 2>&1 &

COUNT=0
while [ $COUNT -lt 1 ]; do
    #echo -e ".\c"
    sleep 1
    COUNT=`ps -f | grep java | grep "$SERVER_HOME/$SERVER_NAME" | awk '{print $2}' | wc -l`
    if [ $COUNT -gt 0 ]; then
        break
    fi
done

echo "start ${SERVER_NAME} OK!"
PIDS=`ps -f | grep java | grep "$SERVER_HOME/$SERVER_NAME" | awk '{print $2}'`
echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"