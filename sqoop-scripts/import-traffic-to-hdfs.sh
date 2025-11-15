#!/bin/bash

export SQOOP_HOME=/opt/sqoop
export PATH=$PATH:$SQOOP_HOME/bin
export HADOOP_HOME=/usr/local/hadoop

# Démarrer Hadoop (ne jamais reformater)
$HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-yarn.sh

sleep 10

# Vérifier les démons
jps

# Créer les dossiers HDFS si pas existants
hdfs dfs -mkdir -p /user/bigdata

# Import Sqoop
sqoop import \
  --connect jdbc:postgresql://postgres:5432/traffic_db \
  --username bigdata \
  --password bigdata123 \
  --table traffic_data \
  --target-dir /user/bigdata/traffic \
  --delete-target-dir \
  --num-mappers 1

# Vérification
hdfs dfs -ls /user/bigdata/traffic/
