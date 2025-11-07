Write-Host "Hola Mundo!"

$mensaje = "Hola Mundo!"

$mensaje = Read-Host "Como te llamas?"
Write-Host "Hola, $nombre !"

$nombre = Read-Host "Introduce un nombre"
[int]$veces = Read-Host "Introduzca el numero de veces"
for($i=0; $i -lt $veces; $i++){
Write-Host $nombre
}

$nombre = Read-Host "Introduce tu nombre"
Write-Host $nombre.ToUpper() "tiene" $nombre.length "letras"

$operacion = (3+2)/(2*5)
Write-Host ([math]::Pow($operacion , 2))

[float]$paga = Read-Host "Introduce tu sueldo por hora"
[int]$horas = Read-Host "Introduce las horas trabajadas"
$total = $paga * $horas
Write-Host "Te corresponde $total"

[int]$numero = Read-Host "Introduzca un numero"
$resultado = $numero * ($numero + 1) / 2
Write-Host $resultado

[int]$numero = Read-Host "Introduzca un numero"
$resultado = 0
for ($i=1; $i -le $numero; $i++) {
$resultado = $resultado + $i
}
Write-Host $resultado

[float]$peso = Read-Host "Introduce tu peso"
[float]$altura = Read-Host "Introduce tu altura"
[float]$resultado = $peso / [math]::Pow($altura,2)
$resultado = [math]::Round($resultado , 2)
Write-Host "Tu indice de masa corporal es $resultado"

[int]$numero1 = Read-Host "Introduce un numero"
[int]$numero2 = Read-Host "Introduce un segundo numero"
Write-Host "La division de $numero1 por $numero2 da" , ($numero1/$numero2) , "con resto " , ($numero1 % $numero2)

[float]$cantidad = Read-Host "Cuanto quieres invertir"
[float]$interes = Read-Host "Con cuanto interes"
[int]$tiempo = Read-Host "A cuantos años"

$interes_anual_ganado = $cantidad * ($interes/100)
$ganado = $interes_anual_ganado * $tiempo

Write-Host $ganado