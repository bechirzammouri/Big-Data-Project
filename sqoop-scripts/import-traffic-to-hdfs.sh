#!/bin/bash

export SQOOP_HOME=/opt/sqoop
export PATH=$PATH:$SQOOP_HOME/bin
export HADOOP_HOME=/usr/local/hadoop

$HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-yarn.sh

sleep 10

jps

hdfs dfs -mkdir -p /user/bigdata/traffic

sqoop import \
  --connect jdbc:postgresql://postgres:5432/traffic_db \
  --username bigdata \
  --password bigdata123 \
  --table traffic_data \
  --target-dir /user/bigdata/traffic \
  --delete-target-dir \
  --num-mappers 1

hdfs dfs -ls /user/bigdata/traffic/

