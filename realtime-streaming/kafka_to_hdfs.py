#!/usr/bin/python3
"""
Spark Streaming: Kafka HDFS
Consumes traffic incidents from Kafka and writes to HDFS
"""

from pyspark.sql import SparkSession
from pyspark.sql.functions import from_json, col, to_timestamp, current_timestamp, to_date, get_json_object
from pyspark.sql.types import StructType, StructField, StringType, IntegerType, DoubleType, ArrayType

# Define the schema for incoming Kafka messages (location removed to avoid parse issues)
incident_schema = StructType([
    StructField("id", StringType(), True),
    StructField("type", IntegerType(), True),
    StructField("description", StringType(), True),
    StructField("event_code", StringType(), True),
    # StructField("location", StructType([...]), True),  # removed
    StructField("severity", IntegerType(), True),
    StructField("delay_seconds", IntegerType(), True),
    StructField("length_meters", IntegerType(), True),
    StructField("roadNumbers", ArrayType(StringType()), True),
    StructField("timestamp", StringType(), True),
    StructField("start_time", StringType(), True),
    StructField("end_time", StringType(), True)
])

def create_spark_session():
    """Create Spark session with Kafka support"""
    return SparkSession.builder \
        .appName("TrafficIncidentsKafkaToHDFS") \
        .master("local[*]") \
        .config("spark.sql.streaming.checkpointLocation", "/tmp/checkpoint") \
        .getOrCreate()

def main():
    print("=" * 80)
    print("Starting Spark Streaming: Kafka -> HDFS")
    print("=" * 80)
    
    # Create Spark session
    spark = create_spark_session()
    spark.sparkContext.setLogLevel("WARN")
    
    print("\n[OK] Spark session created")
    print("  Version: {}".format(spark.version))
    print("  Master: {}".format(spark.sparkContext.master))
    
    # Read from Kafka
    print("\nConnecting to Kafka...")
    df = spark \
        .readStream \
        .format("kafka") \
        .option("kafka.bootstrap.servers", "localhost:9092") \
        .option("subscribe", "traffic_events") \
        .option("startingOffsets", "latest") \
        .option("maxOffsetsPerTrigger", "2000") \
        .option("failOnDataLoss", "false") \
        .load()
    
    print("[OK] Connected to Kafka topic: traffic_events")
    
    # Parse JSON messages (robust: extract fields individually to avoid schema mismatch)
    # Build a base frame that retains the casted raw JSON and Kafka timestamp
    base = df.withColumn("raw", col("value").cast("string")) \
             .withColumn("kafka_timestamp", col("timestamp"))

    incidents_df = base \
        .withColumn("id", get_json_object(col("raw"), "$.id")) \
        .withColumn("type", get_json_object(col("raw"), "$.type").cast("int")) \
        .withColumn("description", get_json_object(col("raw"), "$.description")) \
        .withColumn("event_code", get_json_object(col("raw"), "$.event_code")) \
        .withColumn("severity", get_json_object(col("raw"), "$.severity").cast("int")) \
        .withColumn("delay_seconds", get_json_object(col("raw"), "$.delay_seconds").cast("int")) \
        .withColumn("length_meters", get_json_object(col("raw"), "$.length_meters").cast("double")) \
        .withColumn("roadNumbers", get_json_object(col("raw"), "$.roadNumbers")) \
        .withColumn("timestamp", get_json_object(col("raw"), "$.timestamp")) \
        .withColumn("start_time", get_json_object(col("raw"), "$.start_time")) \
        .withColumn("end_time", get_json_object(col("raw"), "$.end_time"))

    # Add processing timestamp and derive partition date
    incidents_df = incidents_df.withColumn("processing_time", current_timestamp())
    incidents_df = incidents_df.withColumn("event_date", to_date(col("kafka_timestamp")))

    # Keep only rows that parsed correctly
    valid_incidents_df = incidents_df.filter(col("id").isNotNull() & col("event_date").isNotNull())

    print("[OK] Schema applied (using get_json_object parsing)")
    print("\nIncident DataFrame Schema:")
    incidents_df.printSchema()

    # Debug sink to verify rows are flowing (show key fields)
    debug_query = incidents_df.select(
        "id", "type", "description", "event_code", "severity", "delay_seconds", "length_meters",
        "kafka_timestamp", "event_date"
    ) \
        .writeStream \
        .outputMode("append") \
        .format("console") \
        .option("truncate", "false") \
        .option("numRows", "5") \
        .start()

    # Write to HDFS (Parquet format)
    hdfs_path = "hdfs://hadoop-master:9000/traffic/incidents"

    print("\nWriting to HDFS: {}".format(hdfs_path))
    print("  Format: Parquet (partitioned by event_date)")

    query = valid_incidents_df.drop("raw") \
        .writeStream \
        .outputMode("append") \
        .format("parquet") \
        .option("path", hdfs_path) \
        .option("checkpointLocation", "/tmp/checkpoint/incidents") \
        .partitionBy("event_date") \
        .trigger(processingTime='30 seconds') \
        .start()
    
    print("\n[SUCCESS] Streaming started successfully!")
    print("\n" + "=" * 80)
    print("STREAMING STATUS")
    print("=" * 80)
    print("  Status: {}".format(query.status))
    print("  Query ID: {}".format(query.id))
    print("  Output Path: {}".format(hdfs_path))
    print("\nPress Ctrl+C to stop...")
    print("=" * 80)
    
    # Wait for termination
    query.awaitTermination()

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n\nStreaming stopped by user")
    except Exception as e:
        print("\nERROR: {}".format(e))
        import traceback
        traceback.print_exc()
