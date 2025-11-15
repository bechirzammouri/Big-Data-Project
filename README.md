# Traffic Incident Data Pipeline

## Overview

This project processes real-time traffic incident data from TomTom API, streams it through Kafka, and stores it in HDFS using Apache Spark.

```
TomTom API → Producer → Kafka → Spark Streaming → HDFS
```

## Dependencies

### Python Packages
```bash
pip install kafka-python requests pandas
```

### System Requirements
- Apache Kafka
- Apache Spark 3.x
- Hadoop HDFS
- Python 3.7+

### Spark-Kafka Connector
```bash
# Download connector JARs automatically
chmod +x fix_spark_kafka_connector.sh
./fix_spark_kafka_connector.sh
```

## Quick Start

### 1. Start Kafka & Zookeeper
```bash
# Run this script to start services
./start-kafka-zookeeper.sh
# Wait 30 seconds for services to initialize
```


### 3. Setup TomTom API Key
Edit `produce_incident.py` and replace the API_KEY:
```python
API_KEY = "your_tomtom_api_key_here"
```

### 4. Run the Pipeline

**Terminal 1 - Start Producer:**
```bash
python3 produce_incident.py
```

**Terminal 2 - Start Spark Streaming:**
```bash
python3 kafka_to_hdfs.py
```

## Scripts Description

| Script | Purpose |
|--------|---------|
| `produce_incident.py` | Fetches traffic data from TomTom API and sends to Kafka |
| `kafka_to_hdfs.py` | Spark streaming job that reads from Kafka and writes to HDFS |
| `fix_spark_kafka_connector.sh` | Downloads required Spark-Kafka JAR files |
| `view_hdfs_data.sh` | Displays stored data in HDFS |
| `view_parquet.py` | Views parquet files using pandas |
| `test_events_persistency.sh` | Checks if data is properly stored in HDFS |

## Verify Data

### Check HDFS Storage
```bash
# List files in HDFS
hdfs dfs -ls /traffic/incidents

# View data structure
./view_hdfs_data.sh

# Check data persistence
./test_events_persistency.sh
```


### View Local Parquet Files
```bash
python3 view_parquet.py <path_to_parquet_file>
```

## Troubleshooting

| Problem | Solution |
|---------|----------|
| Kafka connection refused | Check if Kafka is running: `jps \| grep Kafka` |
| ClassNotFoundException for Kafka | Run `./fix_spark_kafka_connector.sh` |
| HDFS path not found | Create directory: `hdfs dfs -mkdir -p /traffic/incidents` |
| TomTom API errors | Verify API key is valid and has traffic API access |
| No data in HDFS | Check producer is running and Kafka topic exists |

## Data Flow

1. **Producer** (`produce_incident.py`) → Fetches Paris traffic incidents every 60 seconds
2. **Kafka** → Stores messages in `traffic_events` topic  
3. **Spark** (`kafka_to_hdfs.py`) → Processes messages and saves to HDFS
4. **HDFS** → Stores data as partitioned Parquet files

## Getting TomTom API Key

1. Visit https://developer.tomtom.com/
2. Create account and log in
3. Go to "My Dashboard" → "API Keys"
4. Create new key with "Traffic API" permissions
5. Copy key to `produce_incident.py`
