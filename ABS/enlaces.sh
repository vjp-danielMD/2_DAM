#!/bin/bash
crear_enlace() {
	echo "-------------------"
	echo "CREACION DE ENLACES"
	echo "-------------------"
	read -p "introduce la ruta del fichero origen: " origen

	if [ ! -e "$origen" ]; then
		echo "El fichero de origen no existe."
		return
	fi

	read -p "Introduce las ruta del enlace a crear: " destino
	
	echo "¿Qué tipo de enlace deseas crear?"
    	echo "1) Enlace duro"
	echo "2) Enlace simbólico"
    	
	read -p "Opción [1-2]: " tipo
	case $tipo in
	1)
		ln "$origen" "$destino" && echo "Enlace duro creado."
		;;
	2)
		ln -s "$origen" "$destino" && echo "Enlace simbolico creado."
		;;
	*)
		echo "opcion no valida."
	esac
}

info_sistema(){
	echo "----------------"
	echo "INFO DEL SISTEMA"
	echo "----------------"
	echo ""
	echo "modelo de CPU, nº CPUs y velocidad"
	lscpu | grep -E 'Model name|CPU\(s\)|MHz'
	echo ""
    	echo "💾 Uso de memoria RAM:"
    	free -h
	echo ""
}

gestion_de_paquetes(){
	echo "--------------------------"
    	echo "GESTIÓN DE PAQUETES (dpkg)"
    	echo "--------------------------"
    	echo "1) Comprobar si un paquete está instalado"
    	echo "2) Listar todos los paquetes instalados"
    	read -p "Opción [1-2]: " opcion
	
	case $opcion in
        1)
            read -p "Introduce el nombre del paquete: " paquete
            dpkg -s "$paquete" &> /dev/null
            if [ $? -eq 0 ]; then
                echo "El paquete '$paquete' está instalado."
            else
                echo "El paquete '$paquete' NO está instalado."
            fi
            ;;
        2)
            echo "Listado de paquetes instalados:"
            dpkg -l | less
            ;;
        *)
            echo "opción no válida."
            ;;
    esac
}

opcion=0
while [ "$opcion" != "4" ]; do
    echo ""
    echo "====================================="
    echo "     GESTIÓN DE ENLACES Y SISTEMA"
    echo "====================================="
    echo "1) Crear enlace duro o simbólico"
    echo "2) Mostrar información del sistema"
    echo "3) Comprobar/Listar paquetes instalados"
    echo "4) Salir"
    echo "-------------------------------------"
    read -p "Elige una opción [1-4]: " opcion

    case $opcion in
        1) crear_enlace ;;
        2) info_sistema ;;
        3) gestion_de_paquetes ;;
        4) echo "Saliendo..." ;;
        *) echo "pción no válida." ;;
    esac
done
