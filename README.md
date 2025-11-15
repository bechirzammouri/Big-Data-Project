# Big Data Traffic Project - Historical Data Pipeline

**Team**: Rami + Riadh

> **⚠️ Windows Users**: After cloning, you MUST run Step 4 to fix line endings in bash scripts!

## Task

Load historical Paris traffic data: **CSV → PostgreSQL → HDFS (using Sqoop)**

## Data Source

Paris Open Data - Comptages routiers permanents  
https://opendata.paris.fr/explore/dataset/comptages-routiers-permanents

## Quick Start

### 1. Start Docker Services

```powershell
docker-compose up -d
```

Wait 30 seconds, then verify:
```powershell
docker-compose ps
```

### 2. Download Data

```powershell
.\scripts\download-data.ps1
```

This downloads CSV to `data/paris_traffic_data.csv`

### 3. Load Data into PostgreSQL

```powershell
cd data-loader
pip install -r requirements.txt
python load_paris_traffic.py ../data/paris_traffic_data.csv
```

### 4. Fix Line Endings (Windows Only - First Time)

**If you cloned on Windows**, bash scripts have wrong line endings (CRLF). Fix them:

```powershell
docker exec -it hadoop_cluster bash -c "dos2unix /sqoop-scripts/*.sh 2>/dev/null || sed -i 's/\r$//' /sqoop-scripts/*.sh"
```

### 5. Setup Hadoop (First Time Only)

```powershell
docker exec -it hadoop_cluster bash -c "echo 'hadoop' > /usr/local/hadoop/etc/hadoop/workers"
```

### 6. Install Sqoop (First Time Only)

```powershell
docker exec -it hadoop_cluster bash /sqoop-scripts/install-sqoop.sh
```

### 7. Transfer to HDFS with Sqoop

```powershell
docker exec -it hadoop_cluster bash /sqoop-scripts/import-traffic-to-hdfs.sh
```

This will:
- Format namenode
- Start HDFS and YARN
- Import 21,928 records from PostgreSQL to HDFS
- Takes ~1 minute


## Verify

```powershell
# Check PostgreSQL
docker exec -it postgres_traffic psql -U bigdata -d traffic_db -c "SELECT COUNT(*) FROM traffic_data;"

# Check HDFS
docker exec -it hadoop_cluster hdfs dfs -ls /user/bigdata/traffic/

# View sample data
docker exec -it hadoop_cluster hdfs dfs -cat /user/bigdata/traffic/part-m-00000 | head -10
```

**Expected Output**: 21,928 records imported, ~4.36 MB data in HDFS

## Web UIs

- Hadoop HDFS: http://localhost:9870
- YARN: http://localhost:8088

## Database Connection

- Host: `localhost`
- Port: `5432`
- Database: `traffic_db`
- User: `bigdata`
- Password: `bigdata123`

## Architecture

```
Paris Open Data (CSV)
        ↓
   PostgreSQL
        ↓
   Sqoop Export
        ↓
      HDFS
```

## Stop Services

```powershell
docker-compose down
```

## Troubleshooting

### "bad interpreter: /bin/bash^M" or script fails

**Problem**: Windows line endings (CRLF) in bash scripts  
**Solution**: Run Step 4 to convert CRLF → LF:
```powershell
docker exec -it hadoop_cluster bash -c "sed -i 's/\r$//' /sqoop-scripts/*.sh"
```

### "0 datanode(s) running" error

**Problem**: Hadoop workers file misconfigured  
**Solution**: Run Step 5 to fix workers file:
```powershell
docker exec -it hadoop_cluster bash -c "echo 'hadoop' > /usr/local/hadoop/etc/hadoop/workers"
```

### "commons.lang.StringUtils" error

**Problem**: Missing Apache Commons Lang library  
**Solution**: Already fixed in `install-sqoop.sh` (downloads commons-lang-2.6.jar)

## Project Files

```
Bigdata/
├── docker-compose.yml              # PostgreSQL + Hadoop
├── README.md                       # This file
├── postgres-init/
│   └── 01-create-tables.sql       # Database schema
├── data-loader/
│   ├── load_paris_traffic.py      # CSV → PostgreSQL
│   └── requirements.txt
├── sqoop-scripts/
│   ├── install-sqoop.sh           # Install Sqoop + dependencies
│   └── import-traffic-to-hdfs.sh  # PostgreSQL → HDFS
├── scripts/
│   └── download-data.ps1          # Download CSV
└── data/
    └── paris_traffic_data.csv     # Traffic data (21,928 rows)
```

## Services

- **postgres_traffic**: PostgreSQL 15 (stores 21,928 traffic records)
- **hadoop_cluster**: Hadoop 3.3.6 single-node cluster (liliasfaxi/hadoop-cluster image)

**Note**: Sqoop 1.4.7 is installed via script (not in base image)

