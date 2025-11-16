#!/bin/bash

echo "🚀 Starting Sqoop Import: PostgreSQL → HDFS"
echo "==========================================="
echo ""

export SQOOP_HOME=/opt/sqoop
export PATH=$PATH:$SQOOP_HOME/bin
export HADOOP_HOME=/usr/local/hadoop

# Démarrer Hadoop (ne jamais reformater)
echo "📦 Starting Hadoop services..."
$HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-yarn.sh

echo ""
echo "⏳ Waiting for Hadoop services to start..."
sleep 10

# Vérifier les démons
echo ""
echo "✅ Checking running daemons:"
jps

# Wait for HDFS to exit safe mode
echo ""
echo "🔒 Waiting for HDFS to exit safe mode..."
until hdfs dfsadmin -safemode get | grep -q "Safe mode is OFF"; do
    echo "   HDFS is still in safe mode, waiting..."
    sleep 5
done
echo "✅ HDFS safe mode is OFF!"

# Créer les dossiers HDFS si pas existants
echo ""
echo "📁 Creating HDFS directories..."
hdfs dfs -mkdir -p /user/bigdata

# Import Sqoop
echo ""
echo "📊 Starting Sqoop import..."
echo "   Source: PostgreSQL (postgres:5432/traffic_db)"
echo "   Target: HDFS (/user/bigdata/traffic)"
echo ""

sqoop import \
  --connect jdbc:postgresql://postgres:5432/traffic_db \
  --username bigdata \
  --password bigdata123 \
  --table traffic_data \
  --target-dir /user/bigdata/traffic \
  --delete-target-dir \
  --num-mappers 1 \
  --verbose

# Check import status
if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Sqoop import completed successfully!"
    echo ""
    echo "📊 Verifying imported data..."
    hdfs dfs -ls /user/bigdata/traffic/
    echo ""
    echo "📈 Record count:"
    hdfs dfs -cat /user/bigdata/traffic/part-m-00000 | wc -l
    echo ""
    echo "🔍 Sample data (first 5 lines):"
    hdfs dfs -cat /user/bigdata/traffic/part-m-00000 | head -5
else
    echo ""
    echo "❌ Sqoop import failed!"
    echo ""
    echo "🔍 Check logs above for error details"
    exit 1
fi
