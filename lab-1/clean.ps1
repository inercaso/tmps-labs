# Clean script for Windows PowerShell
# Removes all compiled files

Write-Host "Cleaning compiled files..." -ForegroundColor Yellow

if (Test-Path "bin") {
    Remove-Item -Path "bin" -Recurse -Force
    Write-Host "✓ Removed bin directory" -ForegroundColor Green
} else {
    Write-Host "Nothing to clean - bin directory doesn't exist" -ForegroundColor Cyan
}
