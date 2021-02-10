#!/bin/bash
nohup java -jar mirai.jar >> log.out 2>&1 &
echo "starting，you can check the log.out"
tail -f log.out
