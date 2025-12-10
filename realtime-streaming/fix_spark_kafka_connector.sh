#!/bin/bash
# fix_spark_kafka_connector.sh - Download and install correct Kafka connector for Spark

echo "================================================================================"
echo "🔧 Fixing Spark-Kafka Connector"
echo "================================================================================"
echo ""

# Detect Spark version
SPARK_VERSION=$($SPARK_HOME/bin/spark-submit --version 2>&1 | grep "version" | head -1 | awk '{print $NF}')
echo "📌 Detected Spark version: $SPARK_VERSION"

# Extract major.minor version (e.g., 3.5 from 3.5.0)
SPARK_MAJOR_MINOR=$(echo $SPARK_VERSION | cut -d. -f1,2)
echo "📌 Using Spark major.minor: $SPARK_MAJOR_MINOR"

# Determine Scala version (Spark 3.5 uses Scala 2.12)
SCALA_VERSION="2.12"
echo "📌 Scala version: $SCALA_VERSION"

# Maven repository URL
MAVEN_REPO="https://repo1.maven.org/maven2"

# Create jars directory if not exists
mkdir -p $SPARK_HOME/jars
cd $SPARK_HOME/jars

echo ""
echo "📦 Downloading Spark-Kafka connector JARs..."
echo "--------------------------------------------------------------------------------"

# Download spark-sql-kafka JAR (matching Spark version)
JAR_NAME="spark-sql-kafka-0-10_${SCALA_VERSION}-${SPARK_VERSION}.jar"
JAR_URL="${MAVEN_REPO}/org/apache/spark/spark-sql-kafka-0-10_${SCALA_VERSION}/${SPARK_VERSION}/${JAR_NAME}"

echo "Downloading: ${JAR_NAME}"
wget -q --show-progress "${JAR_URL}" 2>/dev/null

if [ $? -ne 0 ]; then
    echo "⚠️  Exact version not found, trying alternative..."
    # Try with common Spark 3.x versions
    for ver in "3.5.0" "3.4.1" "3.3.2" "3.2.4" "3.1.3"; do
        JAR_NAME="spark-sql-kafka-0-10_${SCALA_VERSION}-${ver}.jar"
        JAR_URL="${MAVEN_REPO}/org/apache/spark/spark-sql-kafka-0-10_${SCALA_VERSION}/${ver}/${JAR_NAME}"
        echo "  Trying version ${ver}..."
        wget -q "${JAR_URL}" 2>/dev/null && break
    done
fi

# Download Kafka clients
echo ""
echo "Downloading: kafka-clients-3.6.1.jar"
wget -q --show-progress "${MAVEN_REPO}/org/apache/kafka/kafka-clients/3.6.1/kafka-clients-3.6.1.jar" 2>/dev/null

# Download commons-pool2
echo ""
echo "Downloading: commons-pool2-2.11.1.jar"
wget -q --show-progress "${MAVEN_REPO}/org/apache/commons/commons-pool2/2.11.1/commons-pool2-2.11.1.jar" 2>/dev/null

# Download spark-token-provider-kafka
echo ""
echo "Downloading: spark-token-provider-kafka-0-10"
SPARK_TOKEN_JAR="spark-token-provider-kafka-0-10_${SCALA_VERSION}-${SPARK_VERSION}.jar"
wget -q --show-progress "${MAVEN_REPO}/org/apache/spark/spark-token-provider-kafka-0-10_${SCALA_VERSION}/${SPARK_VERSION}/${SPARK_TOKEN_JAR}" 2>/dev/null

echo ""
echo "--------------------------------------------------------------------------------"
echo "✓ JARs downloaded"
echo ""

# List downloaded JARs
echo "📋 Installed JARs:"
ls -lh $SPARK_HOME/jars/ | grep -E "kafka|commons-pool2" | awk '{print "  " $9 " (" $5 ")"}'

echo ""
echo "================================================================================"
echo "✅ Setup complete!"
echo "================================================================================"
echo ""
echo "Next steps:"
echo "  1. Run: spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_${SCALA_VERSION}:${SPARK_VERSION} kafka_to_hdfs.py"
echo "  OR"
echo "  2. Run without --packages (JARs are now in classpath): spark-submit kafka_to_hdfs.py"
echo ""
echo "================================================================================"

