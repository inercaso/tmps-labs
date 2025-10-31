# Compile script for Windows PowerShell
# Compiles all Java files in the project

Write-Host "Compiling Vought International Management System..." -ForegroundColor Cyan
Write-Host ""

# Create bin directory if it doesn't exist
if (-not (Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
    Write-Host "Created bin directory" -ForegroundColor Green
}

# Find all Java files
$javaFiles = Get-ChildItem -Path "src" -Filter "*.java" -Recurse | ForEach-Object { $_.FullName }

Write-Host "Found $($javaFiles.Count) Java files to compile" -ForegroundColor Yellow
Write-Host ""

# Compile all Java files
Write-Host "Compiling Java files..." -ForegroundColor Yellow
javac -d bin -sourcepath src $javaFiles

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "COMPILATION SUCCESSFUL!" -ForegroundColor Green
    Write-Host ""
    Write-Host "Compiled files are in the 'bin' directory" -ForegroundColor Cyan
    Write-Host "Run the program with: .\run.ps1" -ForegroundColor Cyan
} else {
    Write-Host ""
    Write-Host "COMPILATION FAILED!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please check the error messages above" -ForegroundColor Red
}
