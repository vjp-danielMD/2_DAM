# Ejercicio 1
[int]$edad = Read-Host "Introduce tu edad"
if($edad -lt 18) {
    Write-Host "Eres menor de edad"
} else {
    Write-Host "Eres mayor de edad"
}

# Ejercicio 2
$pass = "secreto"
$pass_introducida = Read-Host "Introduce tu contraseña"
if($pass_introducida -eq $pass) {
    Write-Host "Contraseña correcta"
}else {
    Write-Host "Contraseña incorrecta"
}

# Ejercicio 2 solución bloqueo
$pass = "secreto"
$pass_introducida = Read-Host "Introduce tu contraseña"
while ($pass_introducida -ne $pass) {
    Write-Host "Contraseña incorrecta"
    $pass_introducida = Read-Host "Introduce tu contraseña"
}
Write-Host "Contraseña correcta"

# Ejercicio 3
[int]$numero1 = Read-Host "Primer numero de la división"
[int]$numero2 = Read-Host "Segundo numero de la division"
if($numero2 -eq 0) {
    Write-Host "El divisor no puede ser 0"
} else {
    $resultado = $numero1 / $numero2
    Write-Host $resultado
}

# Ejercicio 4
[int]$numero = Read-Host "Introduce un numero"
if($numero % 2 -eq 0) {
    Write-Host "El numero es par"
} else {
    Write-Host "El numero es impar"
}

# Ejercicio 5
[int]$edad = Read-Host "Introduce tu edad"
[float]$ingresos = Read-Host "Introduce tus ingresos"
if($edad -gt 16 -and $ingresos -ge 1000) {
    Write-Host "Tributa"
} else {
    Write-Host "No tributa"
}

# Ejercicio 6
$nombre = Read-Host "Introduce tu nombre"
$sexo = Read-Host "Introduce tu sexo"
if( ($sexo -eq "mujer" -and $nombre -lt "m") -or ($sexo -eq "hombre" -and $nombre -gt "n") ) {
    Write-Host "Perteneces al grupo A"
} else {
    Write-Host "Perteneces al grupo B"
}

# Ejercicio 7
[float]$renta = Read-Host "Introduce tu renta anual"
$porcentaje = $false
if($renta -lt 10000) {
    $porcentaje = "5%"
}elseif($renta -lt 20000) {
    $porcentaje = "15%"
}elseif($renta -lt 35000) {
    $porcentaje = "20%"
}elseif($renta -lt 60000) {
    $porcentaje = "30%"
}else {
    $porcentaje = "45%"
}
Write-Host "Te corresponde un $porcentaje"

# Ejercicio 8
[decimal]$puntuacion = Read-Host "Introduzca su puntuación"
$nivel = ""
if($puntuacion -eq 0.0) {
    $nivel = "Inaceptable"
}
elseif($puntuacion -eq 0.4) {
    $nivel = "Aceptable"
}
elseif($puntuacion -ge 0.6) {
    $nivel = "Meritorio"
}else {
    $nivel = "Invalido"
}
$dinero = (2400 * $puntuacion)
Write-Host "su Nivel es $nivel y le corresponden $dinero euros"

# Ejercicio 8 solución con switch
[decimal]$puntuacion = Read-Host "Introduzca su puntuación"
$nivel = ""
switch ($puntuacion) {
    {$_ -eq 0.0} { $nivel = "Inaceptable"; Break }
    {$_ -eq 0.4} { $nivel = "Aceptable"; Break }
    {$_ -ge 0.6} { $nivel = "Meritorio"; Break }
    default { $nivel = "Invalido" }
}
$dinero = (2400 * $puntuacion)
Write-Host "su Nivel es $nivel y le corresponden $dinero euros"
