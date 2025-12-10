# PowerShell script to download Paris traffic data
# This downloads the comptages-routiers-permanents dataset

Write-Host "Downloading Paris Traffic Data..." -ForegroundColor Cyan
Write-Host "Source: Open Data Paris - Comptages routiers permanents" -ForegroundColor Gray
Write-Host ""

# Create data directory if it doesn't exist
if (-Not (Test-Path ".\data")) {
    New-Item -ItemType Directory -Path ".\data" | Out-Null
    Write-Host "Created data directory" -ForegroundColor Green
}

# Data source URL
# Data source URL (avec LIMIT)
$url = "https://opendata.paris.fr/api/explore/v2.1/catalog/datasets/comptages-routiers-permanents/exports/csv?lang=fr&timezone=Europe%2FParis&use_labels=true&delimiter=%3B&limit=10000"
$outputFile = ".\data\paris_traffic_data.csv"


Write-Host "Downloading from Paris Open Data API..." -ForegroundColor Yellow

try {
    Invoke-WebRequest -Uri $url -OutFile $outputFile
    
    if (Test-Path $outputFile) {
        $fileSize = (Get-Item $outputFile).Length / 1MB
        Write-Host ""
        Write-Host "Download successful!" -ForegroundColor Green
        Write-Host "File: $outputFile" -ForegroundColor Cyan
        Write-Host "Size: $([math]::Round($fileSize, 2)) MB" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "Next step: Load data into PostgreSQL" -ForegroundColor Yellow
        Write-Host "Run: python .\data-loader\load_paris_traffic.py .\data\paris_traffic_data.csv" -ForegroundColor Gray
    }
} catch {
    Write-Host ""
    Write-Host "Download failed: $_" -ForegroundColor Red
    Write-Host ""
    Write-Host "Alternative: Download manually from:" -ForegroundColor Yellow
    Write-Host $url -ForegroundColor Gray
    Write-Host "Save it as: $outputFile" -ForegroundColor Gray
}
