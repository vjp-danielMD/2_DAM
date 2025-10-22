#!/bin/bash

mostrar_particiones() {
    echo "----------------------------------"
    echo "ESQUEMA DE PARTICIONES Y VOLÚMENES"
    echo "----------------------------------"
    lsblk -o NAME,FSTYPE,SIZE,MOUNTPOINT,LABEL,TYPE
    echo ""
}

uso_espacio() {
    echo "-----------------------"
    echo "USO DE ESPACIO EN DISCO"
    echo "-----------------------"
    df -hT
    echo ""
}

monitorizar_sistema() {
    echo "----------------------------------"
    echo "MONITORIZACIÓN EN VIVO DEL SISTEMA"
    echo "----------------------------------"
    echo "Pulsa 'q' para salir."
    sleep 1
    top
}

ultimos_eventos() {
    echo "------------------------------"
    echo "ÚLTIMOS 20 EVENTOS DEL SISTEMA"
    echo "------------------------------"
    journalctl -n 20 --no-pager
    echo ""
}

eventos_en_tiempo_real() {
    echo "----------------------------------------"
    echo "ONITORIZACIÓN DE EVENTOS EN TIEMPO REAL"
    echo "----------------------------------------"
    echo "Pulsa Ctrl+C para detener."
    sleep 1
    journalctl -f
}

opcion=0
while [ "$opcion" != "6" ]; do
    echo ""
    echo "====================================="
    echo "    ADMINISTRACIÓN Y MONITOREO DEL SISTEMA"
    echo "====================================="
    echo "1) Mostrar esquema de particiones y volúmenes"
    echo "2) Mostrar uso de espacio en disco"
    echo "3) Monitorizar el sistema en vivo (top)"
    echo "4) Mostrar los últimos 20 eventos del sistema"
    echo "5) Monitorizar eventos del sistema en tiempo real"
    echo "6) Salir"
    echo "-------------------------------------"
    read -p "Elige una opción [1-6]: " opcion

    case $opcion in
        1) mostrar_particiones ;;
        2) uso_espacio ;;
        3) monitorizar_sistema ;;
        4) ultimos_eventos ;;
        5) eventos_en_tiempo_real ;;
        6) echo "Saliendo..." ;;
        *) echo "pción no válida." ;;
    esac
done
