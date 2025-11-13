#!/bin/bash

# Sqoop script to import traffic data from PostgreSQL to HDFS
# Run this inside the hadoop container

echo "=========================================="
echo "Starting Sqoop Import: PostgreSQL -> HDFS"
echo "=========================================="

# Start Hadoop services if not running
start-all.sh

# Wait a bit for HDFS to be ready
echo "Waiting for HDFS..."
sleep 10

# Create HDFS directory
hdfs dfs -mkdir -p /user/bigdata/traffic

# Import full table to HDFS
echo "Importing traffic_data table to HDFS..."
sqoop import \
  --connect jdbc:postgresql://postgres:5432/traffic_db \
  --username bigdata \
  --password bigdata123 \
  --table traffic_data \
  --target-dir /user/bigdata/traffic/full \
  --delete-target-dir \
  --num-mappers 1 \
  --fields-terminated-by ',' \
  --lines-terminated-by '\n'

echo "Import completed!"

# List HDFS contents
echo ""
echo "HDFS Contents:"
hdfs dfs -ls /user/bigdata/traffic/full/

echo ""
echo "=========================================="
echo "Sqoop Import Completed Successfully!"
echo "=========================================="

