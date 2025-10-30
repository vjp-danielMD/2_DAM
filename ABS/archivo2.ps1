## este script practica la entrada y salida de informacion en un script de PowerShell
# Simple: pedir nombre y edad
$name = Read-Host 'Introduce tu nombre'
$ageInput = Read-Host 'Introduce tu edad'
$age = [int]$ageInput
Write-Host "Hola, $name! Tienes $age años." -ForegroundColor Green
$birth=(Get-Date).Year - $age
Write-Host "Naciste aprox. en $birth."
# Fin
