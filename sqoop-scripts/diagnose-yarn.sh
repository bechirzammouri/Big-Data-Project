#!/bin/bash

echo "🔍 YARN & MapReduce Diagnostic Tool"
echo "===================================="
echo ""

echo "1️⃣ Checking Java processes..."
jps
echo ""

echo "2️⃣ Checking YARN ResourceManager status..."
yarn node -list 2>&1
echo ""

echo "3️⃣ Checking active applications..."
yarn application -list 2>&1
echo ""

echo "4️⃣ Checking stuck application status..."
echo "Looking for application_1763327284381_0001..."
yarn application -status application_1763327284381_0001 2>&1
echo ""

echo "5️⃣ Checking NodeManager status..."
if jps | grep -q NodeManager; then
    echo "✅ NodeManager is running"
else
    echo "❌ NodeManager is NOT running - This is likely the problem!"
fi
echo ""

echo "6️⃣ Checking ResourceManager status..."
if jps | grep -q ResourceManager; then
    echo "✅ ResourceManager is running"
else
    echo "❌ ResourceManager is NOT running"
fi
echo ""

echo "7️⃣ Checking YARN cluster nodes..."
yarn node -list -all 2>&1
echo ""

echo "8️⃣ Checking HDFS status..."
hdfs dfsadmin -report | head -20
echo ""

echo "9️⃣ Checking YARN ResourceManager Web UI..."
echo "   URL: http://hadoop-master:8088"
echo "   Try opening this in your browser"
echo ""

echo "🔟 Checking YARN configuration..."
if [ -f "$HADOOP_HOME/etc/hadoop/yarn-site.xml" ]; then
    echo "✅ yarn-site.xml exists"
    grep -A 2 "yarn.nodemanager" $HADOOP_HOME/etc/hadoop/yarn-site.xml | head -10
else
    echo "❌ yarn-site.xml not found"
fi
echo ""

echo "📋 Summary:"
echo "==========="
if jps | grep -q NodeManager && jps | grep -q ResourceManager; then
    echo "✅ YARN services are running"
    echo "⚠️  Job might be stuck in queue or waiting for resources"
    echo ""
    echo "Recommended actions:"
    echo "  1. Kill the stuck job: yarn application -kill application_1763327284381_0001"
    echo "  2. Check YARN logs: yarn logs -applicationId application_1763327284381_0001"
    echo "  3. Retry the import"
else
    echo "❌ YARN services are not properly running"
    echo ""
    echo "Recommended actions:"
    echo "  1. Run: ./sqoop-scripts/fix-yarn-issue.sh"
    echo "  2. Retry the import"
fi
