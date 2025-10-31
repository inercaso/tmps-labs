# Run script for Windows PowerShell
# Runs the compiled Java application

Write-Host "Running Vought International Management System..." -ForegroundColor Cyan
Write-Host ""

# Check if bin directory exists
if (-not (Test-Path "bin")) {
    Write-Host "Error: 'bin' directory not found!" -ForegroundColor Red
    Write-Host "Please compile the project first using: .\compile.ps1" -ForegroundColor Yellow
    exit 1
}

# Run the application with Java 23
& 'C:\Program Files\Java\jdk-23\bin\java.exe' -cp bin client.Main
