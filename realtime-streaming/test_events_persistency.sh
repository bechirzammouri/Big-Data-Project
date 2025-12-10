#!/bin/bash
echo "🔍 Checking HDFS /traffic/incidents"
echo "===================================="

if hdfs dfs -test -d /traffic/incidents; then
    echo "✓ Directory exists"
    
    FILE_COUNT=$(hdfs dfs -ls -R /traffic/incidents 2>/dev/null | grep -c "\.parquet")
    echo "📁 Parquet files: $FILE_COUNT"
    
    SIZE=$(hdfs dfs -du -s -h /traffic/incidents 2>/dev/null | awk '{print $1 " " $2}')
    echo "💾 Total size: $SIZE"
    
    echo ""
    echo "📂 Recent files:"
    hdfs dfs -ls /traffic/incidents | tail -5
else
    echo "❌ Directory doesn't exist yet"
    echo "   Streaming might still be initializing"
fi
