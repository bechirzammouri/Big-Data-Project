#!/usr/bin/python3
"""
Spark Streaming: Kafka HDFS
Consumes traffic incidents from Kafka and writes to HDFS
"""

from pyspark.sql import SparkSession
from pyspark.sql.functions import from_json, col, to_timestamp, current_timestamp
from pyspark.sql.types import StructType, StructField, StringType, IntegerType, DoubleType, ArrayType

# Define the schema for incoming Kafka messages
incident_schema = StructType([
    StructField("id", StringType(), True),
    StructField("type", IntegerType(), True),
    StructField("description", StringType(), True),
    StructField("event_code", StringType(), True),
    StructField("location", StructType([
        StructField("from", StringType(), True),
        StructField("to", StringType(), True),
        StructField("coordinates", ArrayType(ArrayType(DoubleType())), True)
    ]), True),
    StructField("severity", IntegerType(), True),
    StructField("delay_seconds", IntegerType(), True),
    StructField("length_meters", IntegerType(), True),
    StructField("road_numbers", ArrayType(StringType()), True),
    StructField("timestamp", StringType(), True),
    StructField("start_time", StringType(), True),
    StructField("end_time", StringType(), True)
])

def create_spark_session():
    """Create Spark session with Kafka support"""
    return SparkSession.builder \
        .appName("TrafficIncidentsKafkaToHDFS") \
        .master("local[*]") \
        .config("spark.jars.packages", "org.apache.spark:spark-sql-kafka-0-10_2.12:3.1.2") \
        .config("spark.sql.streaming.checkpointLocation", "/tmp/checkpoint") \
        .getOrCreate()

def main():
    print("=" * 80)
    print("🚀 Starting Spark Streaming: Kafka → HDFS")
    print("=" * 80)
    
    # Create Spark session
    spark = create_spark_session()
    spark.sparkContext.setLogLevel("WARN")
    
    print("\n✓ Spark session created")
    print(f"  Version: {spark.version}")
    print(f"  Master: {spark.sparkContext.master}")
    
    # Read from Kafka
    print("\n📡 Connecting to Kafka...")
    df = spark \
        .readStream \
        .format("kafka") \
        .option("kafka.bootstrap.servers", "localhost:9092") \
        .option("subscribe", "traffic_events") \
        .option("startingOffsets", "earliest") \
        .load()
    
    print("✓ Connected to Kafka topic: traffic_events")
    
    # Parse JSON messages
    print("\n🔄 Parsing JSON messages...")
    incidents_df = df.select(
        from_json(col("value").cast("string"), incident_schema).alias("incident"),
        col("timestamp").alias("kafka_timestamp")
    ).select("incident.*", "kafka_timestamp")
    
    # Add processing timestamp
    incidents_df = incidents_df.withColumn("processing_time", current_timestamp())
    
    print("✓ Schema applied")
    print("\nIncident DataFrame Schema:")
    incidents_df.printSchema()
    
    # Write to HDFS (Parquet format)
    hdfs_path = "hdfs://hadoop-master:9000/traffic/incidents"
    
    print(f"\n💾 Writing to HDFS: {hdfs_path}")
    print("  Format: Parquet (partitioned by date)")
    
    query = incidents_df \
        .writeStream \
        .outputMode("append") \
        .format("parquet") \
        .option("path", hdfs_path) \
        .option("checkpointLocation", "/tmp/checkpoint/incidents") \
        .partitionBy("timestamp") \
        .trigger(processingTime='30 seconds') \
        .start()
    
    print("\n✅ Streaming started successfully!")
    print("\n" + "=" * 80)
    print("📊 STREAMING STATUS")
    print("=" * 80)
    print(f"  Status: {query.status}")
    print(f"  Query ID: {query.id}")
    print(f"  Output Path: {hdfs_path}")
    print("\nPress Ctrl+C to stop...")
    print("=" * 80)
    
    # Wait for termination
    query.awaitTermination()

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n\n⚠️  Streaming stopped by user")
    except Exception as e:
        print(f"\n❌ ERROR: {e}")
        import traceback
        traceback.print_exc()
