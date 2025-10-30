Write-Host "Starting the script..."
$serviceName = "wuauserv"  # Windows Update service
$maxRetries = 5
$retryInterval = 10  # seconds
$attempt = 0
while ($attempt -lt $maxRetries) {
    $service = Get-Service -Name $serviceName -ErrorAction SilentlyContinue
    if ($service -and $service.Status -eq 'Running') {
        Write-Host "Service '$serviceName' is running."
        break
    } else {
        Write-Host "Service '$serviceName' is not running. Attempt $($attempt + 1) of $maxRetries."
        Start-Sleep -Seconds $retryInterval
        $attempt++
    }
}
if ($attempt -eq $maxRetries) {
    Write-Host "Service '$serviceName' did not start after $maxRetries attempts. Exiting script."
    exit 1
}
Write-Host "Continuing with the rest of the script..."  