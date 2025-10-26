#!/bin/bash

function listar_procesos() {
    clear
    echo "--- Listado de procesos del sistema ---"
    ps -ef | less
}

function terminar_proceso() {
    clear
    echo "--- Terminar un proceso ---"
    ps -ef | head -15
    echo
    read -p "¿Deseas terminar un proceso? (s/n): " opcion
    if [[ "$opcion" == "s" || "$opcion" == "S" ]]; then
        read -p "Introduce el PID del proceso que deseas terminar: " pid
        if ps -p "$pid" > /dev/null 2>&1; then
            kill "$pid"
            if [[ $? -eq 0 ]]; then
                echo "Proceso $pid terminado correctamente."
            else
                echo "Error al intentar terminar el proceso."
            fi
        else
            echo "No existe ningún proceso con PID $pid."
        fi
    else
        echo "Operación cancelada."
    fi
    read -p "Presiona [Enter] para continuar..."
}

function monitorizar_proceso() {
    clear
    echo "--- Monitorización de un proceso ---"
    read -p "Introduce el nombre del proceso que deseas monitorizar: " nombre
    pid=$(pgrep -f "$nombre" | head -n 1)

    if [[ -z "$pid" ]]; then
        echo "No se encontró ningún proceso con el nombre '$nombre'."
        read -p "Presiona [Enter] para continuar..."
        return
    fi

    echo "Monitorizando proceso '$nombre' (PID: $pid)..."
    echo "Presiona Ctrl + C para salir."
    sleep 2

    while true; do
        clear
        echo "=== Monitorizando proceso: $nombre (PID: $pid) ==="
        ps -p "$pid" -o pid,user,%cpu,%mem,cmd
        if ! ps -p "$pid" > /dev/null; then
            echo "El proceso $pid ya no está activo."
            break
        fi
        sleep 5
    done
    read -p "Presiona [Enter] para continuar..."
}

function programar_backup() {
    clear
    echo "--- Programar Copia de Seguridad con Cron ---"
    read -p "Ruta absoluta del directorio a respaldar: " origen

    if [[ ! -d "$origen" ]]; then
        echo "Error: El directorio de origen no existe."
        read -p "Presiona [Enter] para continuar..."
        return
    fi

    read -p "Ruta absoluta del directorio de destino: " destino
    mkdir -p "$destino"

    echo "Selecciona la frecuencia:"
    echo "1. Diario (02:00h)"
    echo "2. Semanal (Domingos a las 03:00h)"
    echo "3. Mensual (Día 1 a las 04:00h)"
    read -p "Opción [1-3]: " freq

    local cron_schedule
    case $freq in
        1) cron_schedule="0 2 * * *" ;;
        2) cron_schedule="0 3 * * 0" ;;
        3) cron_schedule="0 4 1 * *" ;;
        *) echo "Opción no válida."
           read -p "Presiona [Enter] para continuar..."
           return ;;
    esac

    local backup_file="$destino/backup_$(basename $origen)_\$(date +\%Y-\%m-\%d).tar.gz"
    local command="tar -czf $backup_file $origen"
    local cron_job="$cron_schedule $command"

    (crontab -l 2>/dev/null; echo "$cron_job") | crontab -

    if [[ $? -eq 0 ]]; then
        echo "Backup programado con éxito."
        echo "Se ha añadido la siguiente línea a 'crontab -l':"
        echo "$cron_job"
    else
        echo "Error al programar la tarea en cron."
    fi

    read -p "Presiona [Enter] para continuar..."
}

function menu() {
    while true; do
        clear
        echo "========================================"
        echo " GESTIÓN Y MONITORIZACIÓN DE PROCESOS "
        echo "    Y PROGRAMACIÓN DE BACKUPS"
        echo "========================================"
        echo "1. Listar procesos"
        echo "2. Terminar un proceso"
        echo "3. Monitorizar un proceso"
        echo "4. Programar copia de seguridad"
        echo "5. Salir"
        echo "----------------------------------------"
        read -p "Selecciona una opción [1-5]: " opcion

        case $opcion in
            1) listar_procesos ;;
            2) terminar_proceso ;;
            3) monitorizar_proceso ;;
            4) programar_backup ;;
            5) exit 0 ;;
            *) echo "Opción no válida."; sleep 1 ;;
        esac
    done
}

menu
