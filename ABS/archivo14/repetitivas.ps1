# Ejercicio 1
$i = 0
$palabra = Read-Host "Introduce una palabra"
while($i -lt 10) {
    Write-Host $palabra
    $i++
}

# Ejercicio 2 - while
[int]$edad = Read-Host "Introduce tu edad"
$i = 1
while($i -lt $edad) {
    Write-Host $i
    $i++
}

# Ejercicio 2 - for
[int]$edad = Read-Host "Introduce tu edad"
for($i=1; $i -le $edad; $i++){
    Write-Host $i
}

# Ejercicio 3
[int]$numero = Read-Host "Introduce un numero"
$texto = ""
for($i=1; $i -lt $numero; $i++){
    if($i%2 -eq 1) {
        $texto+=[string]$i + ","
    }
}
Write-Host $texto

# Ejercicio 4
[int]$numero = Read-Host "Introduce un numero"
$salida = ""
for($i=$numero; $i -ge 0; $i--){
    $salida+=[string]$i + ","
}
Write-Host $salida

# Ejercicio 5
[int]$numero = Read-Host "Introduce un numero"
$salida = ""
for($i = 0; $i -lt $numero ; $i++) {
    $salida+="*"
    Write-Host $salida
}

# Ejercicio 6
for($i = 1 ; $i -le 10 ; $i++) {
    Write-Host " Tabla del $i : "
    for($b = 1 ; $b -le 10; $b++) {
        Write-Host "$i * $b = " , ($i * $b)
    }
    Write-Host "------"
}

# Ejercicio 7
$valid_pass = "secret"
do {
    $pass = Read-host "Introduce la contraseña"
}while($valid_pass -ne $pass)
Write-Host "Bienvenido a tu cuenta"

# Ejercicio 8
[int]$numero = Read-Host "Introduce un numero"
$i = 2
$tiene_divisores = $false
while ($i -le $numero / 2) {
    if(($numero % $i) -eq 0){
        $tiene_divisores = $true
        Break
    } else {
        $i++
    }
}
if ($tiene_divisores -eq $true) {
    Write-Host "$numero no es primo"
} else {
    Write-Host "$numero es primo"
}

# Ejercicio 9
$frase = Read-Host "Introduce una frase"
$letra = Read-Host "Introduce una letra"
$apariciones = 0
for($i = 0 ; $i -lt $frase.Length ; $i++) {
    if($frase[$i] -eq $letra) {
        $apariciones++
    }
}
Write-Host "Aparece $apariciones veces"
