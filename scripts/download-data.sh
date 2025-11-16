#!/bin/bash
# Bash script to download Paris traffic data
# This downloads the comptages-routiers-permanents dataset

echo -e "\033[0;36mDownloading Paris Traffic Data...\033[0m"
echo -e "\033[0;90mSource: Open Data Paris - Comptages routiers permanents\033[0m"
echo ""

# Create data directory if it doesn't exist
if [ ! -d "./data" ]; then
    mkdir -p ./data
    echo -e "\033[0;32mCreated data directory\033[0m"
fi

# Data source URL
URL="https://opendata.paris.fr/api/explore/v2.1/catalog/datasets/comptages-routiers-permanents/exports/csv?lang=fr&timezone=Europe%2FParis&use_labels=true&delimiter=%3B&limit=100000"
OUTPUT_FILE="./data/paris_traffic_data.csv"

echo -e "\033[0;33mDownloading from Paris Open Data API...\033[0m"

# Download the file using wget or curl
if command -v wget &> /dev/null; then
    # Use wget if available
    if wget -O "$OUTPUT_FILE" "$URL" 2>&1 | grep -q "saved"; then
        download_success=true
    else
        download_success=false
    fi
elif command -v curl &> /dev/null; then
    # Use curl if wget is not available
    if curl -L -o "$OUTPUT_FILE" "$URL"; then
        download_success=true
    else
        download_success=false
    fi
else
    echo -e "\033[0;31mError: Neither wget nor curl is installed.\033[0m"
    echo "Please install wget or curl to download files."
    exit 1
fi

# Check if download was successful
if [ "$download_success" = true ] && [ -f "$OUTPUT_FILE" ]; then
    file_size=$(du -h "$OUTPUT_FILE" | cut -f1)
    echo ""
    echo -e "\033[0;32mDownload successful!\033[0m"
    echo -e "\033[0;36mFile: $OUTPUT_FILE\033[0m"
    echo -e "\033[0;36mSize: $file_size\033[0m"
    echo ""
    echo -e "\033[0;33mNext step: Load data into PostgreSQL\033[0m"
    echo -e "\033[0;90mRun: python ./data-loader/load_paris_traffic.py ./data/paris_traffic_data.csv\033[0m"
else
    echo ""
    echo -e "\033[0;31mDownload failed!\033[0m"
    echo ""
    echo -e "\033[0;33mAlternative: Download manually from:\033[0m"
    echo -e "\033[0;90m$URL\033[0m"
    echo -e "\033[0;90mSave it as: $OUTPUT_FILE\033[0m"
    exit 1
fi
