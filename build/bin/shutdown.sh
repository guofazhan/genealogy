#!/bin/sh

cd `dirname $0`
cd ..
SERVER_HOME=`pwd`
SERVER_BIN_DIR=$SERVER_HOME/bin

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

PIDS=`ps -f | grep java | grep "$SERVER_HOME/$SERVER_NAME" | awk '{print $2}'`

if [ -z "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME does not started!"
    exit 1
fi

echo -e "Stopping the $SERVER_NAME ...\c"
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
done

COUNT=0
while [ $COUNT -lt 1 ]; do
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $PIDS ; do
        PID_EXIST=`ps -f -p $PID | grep java`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
done

echo "OK!"
echo "PID: $PIDS"
