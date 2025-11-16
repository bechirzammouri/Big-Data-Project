#!/bin/bash
echo "🔍 Viewing HDFS Parquet Data"
echo "============================"

HDFS_PATH="/traffic/incidents"

echo ""
echo "1️⃣ Directory structure:"
hdfs dfs -ls $HDFS_PATH | head -10

echo ""
echo "2️⃣ Partition directories:"
hdfs dfs -ls $HDFS_PATH | grep "timestamp=" | wc -l
echo "   partitions found"

echo ""
echo "3️⃣ Parquet files:"
hdfs dfs -ls -R $HDFS_PATH | grep "\.parquet" | wc -l
echo "   parquet files found"

echo ""
echo "4️⃣ Sample parquet file:"
SAMPLE_FILE=$(hdfs dfs -ls -R $HDFS_PATH | grep "\.parquet" | head -1 | awk '{print $8}')
echo "   $SAMPLE_FILE"

echo ""
echo "5️⃣ Viewing data with PySpark..."
python3 ~/Big-Data-Project/view_parquet.py $SAMPLE_FILE


echo ""
echo "============================"
echo "✅ Done!"
