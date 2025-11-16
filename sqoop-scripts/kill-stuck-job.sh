#!/bin/bash

echo "🛑 Killing Stuck Sqoop Job"
echo "========================="
echo ""

# Find and kill stuck applications
echo "1️⃣ Looking for stuck YARN applications..."
yarn application -list 2>/dev/null | grep -E "RUNNING|ACCEPTED"
echo ""

echo "2️⃣ Killing specific application..."
APP_ID="application_1763327284381_0001"
yarn application -kill $APP_ID 2>&1

if [ $? -eq 0 ]; then
    echo "✅ Application $APP_ID killed successfully"
else
    echo "⚠️  Could not kill application (may already be finished)"
fi
echo ""

echo "3️⃣ Checking for any remaining stuck jobs..."
STUCK_APPS=$(yarn application -list 2>/dev/null | grep -E "RUNNING|ACCEPTED" | awk '{print $1}')
if [ ! -z "$STUCK_APPS" ]; then
    echo "Found stuck applications:"
    for app in $STUCK_APPS; do
        echo "   Killing: $app"
        yarn application -kill $app 2>/dev/null
    done
else
    echo "✅ No stuck applications found"
fi
echo ""

echo "4️⃣ Current application status..."
yarn application -list
echo ""

echo "✅ Done! You can now retry the Sqoop import."
