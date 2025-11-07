# ===============================
# Ejercicios PowerShell sobre Arrays
# ===============================

# Constante global de asignaturas
$ASIGNATURAS = @("Matemáticas", "Física", "Química", "Historia", "Lengua")

function Mostrar-Menu {
    Clear-Host
    Write-Host "==============================="
    Write-Host "   Ejercicios PowerShell"
    Write-Host "==============================="
    Write-Host "1. Mostrar asignaturas"
    Write-Host "2. Yo estudio <asignatura>"
    Write-Host "3. Notas por asignatura"
    Write-Host "4. Lotería ordenada"
    Write-Host "5. Números 1 al 10 inverso"
    Write-Host "6. Abecedario sin múltiplos de 3"
    Write-Host "7. Palíndromo"
    Write-Host "8. Contar vocales"
    Write-Host "9. Salir"
    Write-Host "==============================="
}

function Ejercicio1 {
    $ASIGNATURAS | ForEach-Object { Write-Output $_ }
}

function Ejercicio2 {
    $ASIGNATURAS | ForEach-Object { Write-Output "Yo estudio $_" }
}

function Ejercicio3 {
    $notas = @{}
    foreach ($asignatura in $ASIGNATURAS) {
        $nota = Read-Host "¿Qué nota has sacado en $asignatura?"
        $notas[$asignatura] = $nota
    }
    foreach ($asignatura in $ASIGNATURAS) {
        Write-Output "En $asignatura has sacado $($notas[$asignatura])"
    }
}

function Ejercicio4 {
    $numeros = @()
    for ($i = 1; $i -le 6; $i++) {
        $num = Read-Host "Introduce el número ganador #$i"
        $numeros += [int]$num
    }
    $numeros = $numeros | Sort-Object
    Write-Output "Números ganadores ordenados: $($numeros -join ', ')"
}

function Ejercicio5 {
    $numeros = 1..10
    $inverso = $numeros | Sort-Object -Descending
    Write-Output ($inverso -join ', ')
}

function Ejercicio6 {
    $abecedario = [char[]](65..90)
    $resultado = @()
    for ($i = 0; $i -lt $abecedario.Length; $i++) {
        if (($i + 1) % 3 -ne 0) {
            $resultado += $abecedario[$i]
        }
    }
    Write-Output "Resultado: $($resultado -join ', ')"
}

function Ejercicio7 {
    $palabra = Read-Host "Introduce una palabra"
    $reversa = -join ($palabra.ToCharArray() | Reverse)
    if ($palabra -eq $reversa) {
        Write-Output "La palabra '$palabra' es un palíndromo."
    } else {
        Write-Output "La palabra '$palabra' no es un palíndromo."
    }
}

function Ejercicio8 {
    $palabra = Read-Host "Introduce una palabra"
    $vocales = @("a", "e", "i", "o", "u")
    foreach ($v in $vocales) {
        $conteo = ($palabra.ToLower() -split "").Where({ $_ -eq $v }).Count
        Write-Output "La vocal '$v' aparece $conteo veces."
    }
}

do {
    Mostrar-Menu
    $opcion = Read-Host "Selecciona una opción (1-9)"
    switch ($opcion) {
        "1" { Ejercicio1 }
        "2" { Ejercicio2 }
        "3" { Ejercicio3 }
        "4" { Ejercicio4 }
        "5" { Ejercicio5 }
        "6" { Ejercicio6 }
        "7" { Ejercicio7 }
        "8" { Ejercicio8 }
        "9" { Write-Host "¡Hasta luego!" }
        default { Write-Host "Opción no válida. Intenta de nuevo." }
    }
    if ($opcion -ne "9") {
        Pause
    }
} while ($opcion -ne "9")
