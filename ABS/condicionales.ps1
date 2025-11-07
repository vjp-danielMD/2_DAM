# ===============================
# Ejercicios PowerShell: Condicionales
# ===============================

function Mostrar-Menu {
    Clear-Host
    Write-Host "==============================="
    Write-Host " Ejercicios: Estructuras Condicionales"
    Write-Host "==============================="
    Write-Host "1. Mayor de edad"
    Write-Host "2. Verificar contraseña"
    Write-Host "3. División segura"
    Write-Host "4. Par o impar"
    Write-Host "5. ¿Debe tributar?"
    Write-Host "6. Grupo A o B"
    Write-Host "7. Tramo impositivo"
    Write-Host "8. Evaluación laboral"
    Write-Host "9. Precio entrada"
    Write-Host "10. Pizza personalizada"
    Write-Host "11. Salir"
    Write-Host "==============================="
}

function Ejercicio1 {
    $edad = Read-Host "Introduce tu edad"
    if ([int]$edad -ge 18) {
        Write-Output "Eres mayor de edad."
    } else {
        Write-Output "No eres mayor de edad."
    }
}

function Ejercicio2 {
    $claveGuardada = "contraseña"
    $claveUsuario = Read-Host "Introduce la contraseña"
    if ($claveUsuario.ToLower() -eq $claveGuardada.ToLower()) {
        Write-Output "La contraseña es correcta."
    } else {
        Write-Output "La contraseña es incorrecta."
    }
}

function Ejercicio3 {
    $num1 = Read-Host "Introduce el dividendo"
    $num2 = Read-Host "Introduce el divisor"
    if ([double]$num2 -eq 0) {
        Write-Output "Error: No se puede dividir entre cero."
    } else {
        $resultado = [double]$num1 / [double]$num2
        Write-Output "Resultado: $resultado"
    }
}

function Ejercicio4 {
    $numero = Read-Host "Introduce un número entero"
    if ([int]$numero % 2 -eq 0) {
        Write-Output "El número es par."
    } else {
        Write-Output "El número es impar."
    }
}

function Ejercicio5 {
    $edad = Read-Host "Introduce tu edad"
    $ingresos = Read-Host "Introduce tus ingresos mensuales (€)"
    if ([int]$edad -gt 16 -and [int]$ingresos -ge 1000) {
        Write-Output "Debes tributar."
    } else {
        Write-Output "No estás obligado a tributar."
    }
}

function Ejercicio6 {
    $nombre = Read-Host "Introduce tu nombre"
    $sexo = Read-Host "Introduce tu sexo (M/F)"
    $letra = $nombre.Substring(0,1).ToUpper()
    if (($sexo -eq "F" -and $letra -lt "M") -or ($sexo -eq "M" -and $letra -gt "N")) {
        Write-Output "Perteneces al grupo A."
    } else {
        Write-Output "Perteneces al grupo B."
    }
}

function Ejercicio7 {
    $renta = Read-Host "Introduce tu renta anual (€)"
    $renta = [int]$renta
    if ($renta -lt 10000) {
        Write-Output "Tipo impositivo: 5%"
    } elseif ($renta -lt 20000) {
        Write-Output "Tipo impositivo: 15%"
    } elseif ($renta -lt 35000) {
        Write-Output "Tipo impositivo: 20%"
    } elseif ($renta -lt 60000) {
        Write-Output "Tipo impositivo: 30%"
    } else {
        Write-Output "Tipo impositivo: 45%"
    }
}

function Ejercicio8 {
    $puntos = Read-Host "Introduce tu puntuación (0.0, 0.4, 0.6 o más)"
    $puntos = [double]$puntos
    $dinero = $puntos * 2400
    if ($puntos -eq 0.0) {
        Write-Output "Nivel: Inaceptable. Beneficio: $dinero €"
    } elseif ($puntos -eq 0.4) {
        Write-Output "Nivel: Aceptable. Beneficio: $dinero €"
    } elseif ($puntos -ge 0.6) {
        Write-Output "Nivel: Meritorio. Beneficio: $dinero €"
    } else {
        Write-Output "Puntuación no válida."
    }
}

function Ejercicio9 {
    $edad = Read-Host "Introduce la edad del cliente"
    $edad = [int]$edad
    if ($edad -lt 4) {
        Write-Output "Entrada gratuita."
    } elseif ($edad -le 18) {
        Write-Output "Precio de entrada: 5 €"
    } else {
        Write-Output "Precio de entrada: 10 €"
    }
}

function Ejercicio10 {
    $tipo = Read-Host "¿Quieres una pizza vegetariana? (sí/no)"
    if ($tipo.ToLower() -eq "sí") {
        $ingredientes = @("Pimiento", "Tofu")
        $vegetariana = $true
    } else {
        $ingredientes = @("Peperoni", "Jamón", "Salmón")
        $vegetariana = $false
    }

    Write-Host "Ingredientes disponibles:"
    $ingredientes | ForEach-Object { Write-Host "- $_" }

    $eleccion = Read-Host "Elige un ingrediente"
    if ($ingredientes -contains $eleccion) {
        $pizza = @("Mozzarella", "Tomate", $eleccion)
        $tipoTexto = if ($vegetariana) { "vegetariana" } else { "no vegetariana" }
        Write-Output "Has elegido una pizza $tipoTexto con: $($pizza -join ', ')"
    } else {
        Write-Output "Ingrediente no válido."
    }
}

do {
    Mostrar-Menu
    $opcion = Read-Host "Selecciona una opción (1-11)"
    switch ($opcion) {
        "1"  { Ejercicio1 }
        "2"  { Ejercicio2 }
        "3"  { Ejercicio3 }
        "4"  { Ejercicio4 }
        "5"  { Ejercicio5 }
        "6"  { Ejercicio6 }
        "7"  { Ejercicio7 }
        "8"  { Ejercicio8 }
        "9"  { Ejercicio9 }
        "10" { Ejercicio10 }
        "11" { Write-Host "¡Hasta luego!" }
        default { Write-Host "Opción no válida. Intenta de nuevo." }
    }
    if ($opcion -ne "11") {
        Pause
    }
} while ($opcion -ne "11")
