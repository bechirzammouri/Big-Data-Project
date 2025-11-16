#!/bin/bash

echo "🎯 Sqoop Import - Complete Workflow"
echo "===================================="
echo ""
echo "This script will:"
echo "  1. Diagnose current issues"
echo "  2. Kill stuck jobs"
echo "  3. Fix YARN issues"
echo "  4. Retry the import"
echo ""
read -p "Press Enter to continue or Ctrl+C to cancel..."
echo ""

# Step 1: Make all scripts executable
echo "📝 Making scripts executable..."
chmod +x ./sqoop-scripts/*.sh
echo "✅ Done"
echo ""

# Step 2: Diagnose
echo "═══════════════════════════════════════"
echo "STEP 1: DIAGNOSING ISSUES"
echo "═══════════════════════════════════════"
./sqoop-scripts/diagnose-yarn.sh
echo ""
read -p "Press Enter to continue to Step 2..."
echo ""

# Step 3: Kill stuck jobs
echo "═══════════════════════════════════════"
echo "STEP 2: KILLING STUCK JOBS"
echo "═══════════════════════════════════════"
./sqoop-scripts/kill-stuck-job.sh
echo ""
read -p "Press Enter to continue to Step 3..."
echo ""

# Step 4: Fix YARN
echo "═══════════════════════════════════════"
echo "STEP 3: FIXING YARN ISSUES"
echo "═══════════════════════════════════════"
./sqoop-scripts/fix-yarn-issue.sh
echo ""
read -p "Press Enter to continue to Step 4..."
echo ""

# Step 5: Verify services
echo "═══════════════════════════════════════"
echo "STEP 4: VERIFYING SERVICES"
echo "═══════════════════════════════════════"
echo ""
echo "🔍 Checking Java processes..."
jps
echo ""
echo "🔍 Checking YARN nodes..."
yarn node -list
echo ""
echo "🔍 Checking HDFS..."
hdfs dfsadmin -safemode get
echo ""

if jps | grep -q NodeManager && jps | grep -q ResourceManager; then
    echo "✅ All services are running!"
    echo ""
    read -p "Press Enter to start Sqoop import..."
    echo ""
    
    # Step 6: Run import
    echo "═══════════════════════════════════════"
    echo "STEP 5: RUNNING SQOOP IMPORT"
    echo "═══════════════════════════════════════"
    ./sqoop-scripts/import-traffic-to-hdfs.sh
else
    echo "❌ Some services are not running!"
    echo ""
    echo "Please check the output above and fix manually."
    echo ""
    echo "You can try:"
    echo "  1. Restart services: ./sqoop-scripts/fix-yarn-issue.sh"
    echo "  2. Check logs: yarn logs -applicationId <app_id>"
    echo "  3. Manual start: \$HADOOP_HOME/sbin/start-yarn.sh"
fi
