#!/bin/bash

#cd `dirname $0`/../target
#target_dir=`pwd`

pid=`ps ax | grep -i 'mirai.jar' | grep java | grep -v grep | awk '{print $1}'`
if [ -z "$pid" ] ; then
        echo "No mirai running."
        exit 0;
fi

echo "The mirai(${pid}) is running..."

kill ${pid}

echo "Send shutdown request to mirai(${pid}) OK"