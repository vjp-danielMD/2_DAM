function Ejercicio1 {
    $palabra = Read-Host "Introduce una palabra"
    for ($i = 1; $i -le 10; $i++) {
        Write-Output $palabra
    }
}

function Ejercicio2 {
    $edad = Read-Host "Introduce tu edad"
    for ($i = 1; $i -le $edad; $i++) {
        Write-Output "Año $i"
    }
}

function Ejercicio3 {
    $numero = Read-Host "Introduce un número entero positivo"
    $resultado = ""
    for ($i = 1; $i -le $numero; $i += 2) {
        $resultado += "$i,"
    }
    Write-Output $resultado.TrimEnd(',')
}

function Ejercicio4 {
    $numero = Read-Host "Introduce un número entero positivo"
    $resultado = ""
    for ($i = $numero; $i -ge 0; $i--) {
        $resultado += "$i,"
    }
    Write-Output $resultado.TrimEnd(',')
}

function Ejercicio5 {
    $cantidad = [double](Read-Host "Cantidad a invertir")
    $interes = [double](Read-Host "Interes anual (%)")
    $anios = [int](Read-Host "Numero de anios")
    $capital = $cantidad

    for ($i = 1; $i -le $anios; $i++) {
        $capital *= (1 + $interes / 100)
        Write-Output ("Año {0}: {1:N2}" -f $i, $capital)
    }
}



function Ejercicio6 {
    $altura = Read-Host "Introduce la altura del triángulo"
    for ($i = 1; $i -le $altura; $i++) {
        Write-Output ("*" * $i)
    }
}

function Ejercicio7 {
    for ($i = 1; $i -le 10; $i++) {
        for ($j = 1; $j -le 10; $j++) {
            Write-Output "$i x $j = " + ($i * $j)
        }
        Write-Output ""
    }
}

function Ejercicio8 {
    $altura = Read-Host "Introduce la altura del triángulo"
    for ($i = 1; $i -le $altura; $i++) {
        $linea = ""
        for ($j = ($i * 2 - 1); $j -ge 1; $j -= 2) {
            $linea += "$j "
        }
        Write-Output $linea.Trim()
    }
}

function Ejercicio9 {
    $clave = "secreta"
    do {
        $entrada = Read-Host "Introduce la contraseña"
    } while ($entrada -ne $clave)
    Write-Output "Contraseña correcta"
}

function Ejercicio10 {
    $numero = Read-Host "Introduce un número entero"
    $esPrimo = $true
    if ($numero -le 1) {
        $esPrimo = $false
    } else {
        for ($i = 2; $i -lt $numero; $i++) {
            if ($numero % $i -eq 0) {
                $esPrimo = $false
                break
            }
        }
    }
    if ($esPrimo) {
        Write-Output "$numero es primo"
    } else {
        Write-Output "$numero no es primo"
    }
}

function Ejercicio11 {
    $palabra = Read-Host "Introduce una palabra"
    for ($i = $palabra.Length - 1; $i -ge 0; $i--) {
        Write-Output $palabra[$i]
    }
}

function Ejercicio12 {
    $frase = Read-Host "Introduce una frase"
    $letra = Read-Host "Introduce una letra"
    $contador = 0
    foreach ($c in $frase.ToCharArray()) {
        if ($c -eq $letra) {
            $contador++
        }
    }
    Write-Output "La letra '$letra' aparece $contador veces"
}

function Ejercicio13 {
    do {
        $entrada = Read-Host "Escribe algo ('salir' para terminar)"
        if ($entrada -ne "salir") {
            Write-Output $entrada
        }
    } while ($entrada -ne "salir")
}

function MostrarMenu {
    Write-Output "`n--- MENÚ DE EJERCICIOS ---"
    for ($i = 1; $i -le 13; $i++) {
        Write-Output "$i. Ejercicio $i"
    }
    Write-Output "0. Salir"
}

do {
    MostrarMenu
    $opcion = Read-Host "Selecciona una opción"
    switch ($opcion) {
        1 { Ejercicio1 }
        2 { Ejercicio2 }
        3 { Ejercicio3 }
        4 { Ejercicio4 }
        5 { Ejercicio5 }
        6 { Ejercicio6 }
        7 { Ejercicio7 }
        8 { Ejercicio8 }
        9 { Ejercicio9 }
        10 { Ejercicio10 }
        11 { Ejercicio11 }
        12 { Ejercicio12 }
        13 { Ejercicio13 }
        0 { Write-Output "Saliendo..." }
        default { Write-Output "Opción no válida" }
    }
} while ($opcion -ne "0")
