# Big Data Traffic Project

**CSV → PostgreSQL → HDFS** using Sqoop

## Quick Start

### 1. Start Services

```powershell
docker-compose up -d
```

### 2. Download Data

```powershell
.\scripts\download-data.ps1
```

### 3. Load to PostgreSQL

```powershell
cd data-loader
pip install -r requirements.txt
python load_paris_traffic.py ../data/paris_traffic_data.csv
cd ..
```

### 4. Fix Bash Scripts (Windows - First Time Only)

```powershell
docker exec -it hadoop_cluster bash -c "sed -i 's/\r$//' /sqoop-scripts/*.sh"
```

### 5. Configure Hadoop (First Time Only)

```powershell
docker exec -it hadoop_cluster bash -c "sed -i 's|hdfs://hadoop-master:9000|hdfs://hadoop:9000|g' /usr/local/hadoop/etc/hadoop/core-site.xml && sed -i 's|hadoop-master|hadoop|g' /usr/local/hadoop/etc/hadoop/yarn-site.xml && sed -i 's|hadoop-master|hadoop|g' /usr/local/hadoop/etc/hadoop/mapred-site.xml && echo 'hadoop' > /usr/local/hadoop/etc/hadoop/workers"
```

### 6. Install Sqoop (First Time Only)

```powershell
docker exec -it hadoop_cluster bash /sqoop-scripts/install-sqoop.sh
```

### 7. Transfer to HDFS

```powershell
docker exec -it hadoop_cluster bash /sqoop-scripts/import-traffic-to-hdfs.sh
```

**Result**: 21,928 records → 4.36 MB in HDFS (~1 minute)

## Verify

```powershell
docker exec -it hadoop_cluster hdfs dfs -ls /user/bigdata/traffic/
docker exec -it hadoop_cluster hdfs dfs -cat /user/bigdata/traffic/part-m-00000 | head -10
```

## Web UIs

- HDFS: http://localhost:9870
- YARN: http://localhost:8088

## Troubleshooting

**Error: `bad interpreter: /bin/bash^M`**  
→ Run Step 4 (fix line endings)

**Error: `UnknownHostException: hadoop-master`**  
→ Run Step 5 (fix Hadoop configs)

**Error: `0 datanode(s) running`**  
→ Run Step 5 (fix workers file)

## Stop

```powershell
docker-compose down
```

---

**Data Source**: [Paris Open Data - Traffic Counters](https://opendata.paris.fr/explore/dataset/comptages-routiers-permanents)

