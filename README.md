# Spark + Kafka → HDFS Pipeline Guide

## 🎯 Overview

This pipeline consumes traffic incident data from Kafka and writes it to HDFS using PySpark.

## 📋 Architecture

```
TomTom API → Producer → Kafka Topic → Spark Streaming → HDFS
                      (traffic_events)              (Parquet files)
```

## 🚀 Quick Start

### 1. Start Services

```bash
# Inside hadoop-master container
cd /root
./fix-kafka-zookeeper.sh

# Wait for Kafka to start (30 seconds)
```

### 2. Setup Spark-Kafka Integration

```bash
chmod +x setup_spark_kafka.sh
./setup_spark_kafka.sh
```

### 3. Start Producer

```bash
# Terminal 1: Start producer
python3 producer_incidents.py
```

### 4. Run Spark Job

**Option A: Batch Processing** (process existing messages)
```bash
# Terminal 2: Batch processing
spark-submit \
  --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.1.2 \
  spark_kafka_to_hdfs_batch.py
```

**Option B: Streaming** (continuous processing)
```bash
# Terminal 2: Streaming
spark-submit \
  --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.1.2 \
  spark_kafka_to_hdfs.py
```

## 📊 Verify Data in HDFS

```bash
# List files
hdfs dfs -ls /traffic/incidents

# Show sample data
hdfs dfs -cat /traffic/incidents/part-*.parquet | head

# Or use Spark
pyspark
>>> df = spark.read.parquet("hdfs://hadoop-master:9000/traffic/incidents")
>>> df.show()
>>> df.count()
```

## 🔍 Query Data

```python
from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("QueryIncidents").getOrCreate()

# Read data
df = spark.read.parquet("hdfs://hadoop-master:9000/traffic/incidents")

# Query examples
df.show()
df.printSchema()
df.groupBy("type").count().show()
df.groupBy("severity").count().show()
df.filter(df.severity == 3).show()  # Major incidents only
```

## 📁 Output Structure

```
/traffic/incidents/
├── timestamp=2025-11-15/
│   ├── part-00000.parquet
│   ├── part-00001.parquet
│   └── ...
├── timestamp=2025-11-16/
│   └── ...
└── _spark_metadata/
```

## 🛠️ Troubleshooting

### Issue: "ClassNotFoundException: org.apache.spark.sql.kafka010"
**Solution:** Run setup_spark_kafka.sh to download required JARs

### Issue: "Connection refused" to Kafka
**Solution:** Make sure Kafka is running
```bash
jps  # Should show Kafka
netstat -tuln | grep 9092  # Should show listening
```

### Issue: "Path does not exist: hdfs://..."
**Solution:** Create HDFS directories
```bash
hdfs dfs -mkdir -p /traffic/incidents
```

## 📈 Performance Tips

1. **Increase parallelism:**
   ```python
   .config("spark.sql.shuffle.partitions", "200")
   ```

2. **Tune batch interval:**
   ```python
   .trigger(processingTime='10 seconds')  # Adjust as needed
   ```

3. **Use compression:**
   ```python
   .option("compression", "snappy")
   ```

## 🎓 What's Happening

1. **Producer** fetches data from TomTom API every 60 seconds
2. **Kafka** stores messages in `traffic_events` topic
3. **Spark** reads from Kafka in micro-batches
4. **HDFS** stores data in Parquet format, partitioned by date

## 📊 Data Schema

```
root
 |-- id: string
 |-- type: integer (incident type code)
 |-- description: string
 |-- location: struct
 |    |-- from: string
 |    |-- to: string
 |    |-- coordinates: array
 |-- severity: integer (1=minor, 2=moderate, 3=major)
 |-- delay_seconds: integer
 |-- length_meters: integer
 |-- road_numbers: array
 |-- timestamp: string
 |-- start_time: string
 |-- end_time: string
```

## ✅ Success Indicators

✓ Producer shows: "✓ Sent X incident(s) to Kafka"
✓ Spark shows: "Batch: X" with increasing numbers
✓ HDFS shows new files: `hdfs dfs -ls /traffic/incidents`
✓ Data is queryable in Spark
