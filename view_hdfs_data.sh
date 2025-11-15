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
python3 <<EOF
from pyspark.sql import SparkSession

# Create Spark session
spark = SparkSession.builder \
    .appName("QuickView") \
    .getOrCreate()

# Set log level to reduce noise
spark.sparkContext.setLogLevel("ERROR")

# Your code
HDFS_PATH = "/traffic/incidents"
df = spark.read.parquet(f"hdfs://hadoop-master:9000{HDFS_PATH}")
print(f"\n📊 Total records: {df.count()}")
print("\n📋 Sample data (first 5 records):\n")
df.show(5, truncate=False)
print("\n📈 Records by severity:")
df.groupBy("severity").count().orderBy("severity").show()

# Stop Spark session
spark.stop()
EOF

echo ""
echo "============================"
echo "✅ Done!"
