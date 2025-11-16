#!/bin/bash

echo "🔧 Fixing YARN & Hadoop Issues"
echo "=============================="
echo ""

echo "1️⃣ Killing stuck applications..."
STUCK_APPS=$(yarn application -list 2>/dev/null | grep "RUNNING\|ACCEPTED" | awk '{print $1}')
if [ ! -z "$STUCK_APPS" ]; then
    for app in $STUCK_APPS; do
        echo "   Killing application: $app"
        yarn application -kill $app 2>/dev/null
    done
else
    echo "   No stuck applications found"
fi
echo ""

echo "2️⃣ Stopping Hadoop services..."
$HADOOP_HOME/sbin/stop-yarn.sh
$HADOOP_HOME/sbin/stop-dfs.sh
sleep 5
echo ""

echo "3️⃣ Cleaning temporary files..."
rm -rf /tmp/hadoop-root/nm-local-dir/* 2>/dev/null
rm -rf /tmp/hadoop-yarn/staging/* 2>/dev/null
rm -rf /tmp/hadoop-yarn/* 2>/dev/null
echo "   ✅ Temporary files cleaned"
echo ""

echo "4️⃣ Starting HDFS..."
$HADOOP_HOME/sbin/start-dfs.sh
sleep 5
echo ""

echo "5️⃣ Starting YARN..."
$HADOOP_HOME/sbin/start-yarn.sh
sleep 10
echo ""

echo "6️⃣ Waiting for HDFS to exit safe mode..."
until hdfs dfsadmin -safemode get 2>/dev/null | grep -q "Safe mode is OFF"; do
    echo "   HDFS is in safe mode, waiting..."
    sleep 5
done
echo "✅ HDFS safe mode is OFF!"
echo ""

echo "7️⃣ Verifying Hadoop services..."
jps
echo ""

echo "8️⃣ Checking NodeManager..."
if jps | grep -q NodeManager; then
    echo "✅ NodeManager is running"
else
    echo "❌ NodeManager is NOT running - Manual intervention needed!"
    echo ""
    echo "Try starting it manually:"
    echo "  $HADOOP_HOME/sbin/yarn-daemon.sh start nodemanager"
fi
echo ""

echo "9️⃣ Checking ResourceManager..."
if jps | grep -q ResourceManager; then
    echo "✅ ResourceManager is running"
else
    echo "❌ ResourceManager is NOT running - Manual intervention needed!"
fi
echo ""

echo "🔟 Testing YARN cluster..."
yarn node -list
echo ""

echo "=============================="
echo "✅ Hadoop restart completed!"
echo ""
echo "Next steps:"
echo "  1. Verify services: jps"
echo "  2. Check YARN: yarn node -list"
echo "  3. Retry Sqoop import: ./sqoop-scripts/import-traffic-to-hdfs.sh"
