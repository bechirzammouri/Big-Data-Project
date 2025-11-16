#!/bin/bash
# Force HDFS to exit safe mode

echo "🔒 Checking HDFS Safe Mode Status..."
echo ""

STATUS=$(hdfs dfsadmin -safemode get)
echo "Current status: $STATUS"

if echo "$STATUS" | grep -q "Safe mode is ON"; then
    echo ""
    echo "⚠️  HDFS is in safe mode!"
    echo ""
    echo "Options:"
    echo "  1. Wait for automatic exit (recommended)"
    echo "  2. Force exit safe mode (use with caution)"
    echo ""
    read -p "Choose option (1/2): " OPTION
    
    if [ "$OPTION" = "2" ]; then
        echo ""
        echo "⚡ Forcing HDFS to exit safe mode..."
        hdfs dfsadmin -safemode leave
        echo ""
        echo "✅ Safe mode disabled!"
    else
        echo ""
        echo "⏳ Waiting for safe mode to exit automatically..."
        until hdfs dfsadmin -safemode get | grep -q "Safe mode is OFF"; do
            echo "   Still in safe mode, waiting 5 seconds..."
            sleep 5
        done
        echo ""
        echo "✅ Safe mode is now OFF!"
    fi
else
    echo ""
    echo "✅ HDFS is NOT in safe mode. Ready to use!"
fi

echo ""
echo "📊 HDFS Status:"
hdfs dfsadmin -report | head -20
