#!/bin/bash

# Verificar si se ejecuta como root
if [[ $EUID -ne 0 ]]; then
  echo "Este script debe ejecutarse como root. Saliendo..."
  exit 1
fi

# Función: Gestión de usuarios y grupos
gestion_usuarios() {
  echo "=== Gestión de Usuarios y Grupos ==="
  echo "1) Crear usuario"
  echo "2) Eliminar usuario"
  echo "3) Crear grupo"
  echo "4) Asignar grupo a usuario"
  read -p "Selecciona una opción: " opcion
  case $opcion in
    1)
      read -p "Nombre del usuario: " usuario
      if id "$usuario" &>/dev/null; then
        echo "El usuario ya existe."
      else
        useradd "$usuario" && echo "Usuario creado."
      fi
      ;;
    2)
      read -p "Nombre del usuario: " usuario
      if id "$usuario" &>/dev/null; then
        userdel "$usuario" && echo "Usuario eliminado."
      else
        echo "El usuario no existe."
      fi
      ;;
    3)
      read -p "Nombre del grupo: " grupo
      if getent group "$grupo" &>/dev/null; then
        echo "El grupo ya existe."
      else
        groupadd "$grupo" && echo "Grupo creado."
      fi
      ;;
    4)
      read -p "Usuario: " usuario
      read -p "Grupo: " grupo
      usermod -aG "$grupo" "$usuario" && echo "Grupo asignado."
      ;;
    *)
      echo "Opción inválida."
      ;;
  esac
}

# Función: Información de cuentas
info_cuentas() {
  echo "=== Usuarios del sistema ==="
  head -n 10 /etc/passwd
  echo "=== Grupos del sistema ==="
  head -n 10 /etc/group
}

# Función: Gestión de enlaces
gestion_enlaces() {
  read -p "Ruta origen: " origen
  read -p "Ruta destino: " destino
  echo "1) Enlace simbólico"
  echo "2) Enlace duro"
  read -p "Tipo de enlace: " tipo
  case $tipo in
    1) ln -s "$origen" "$destino" && echo "Enlace simbólico creado." ;;
    2) ln "$origen" "$destino" && echo "Enlace duro creado." ;;
    *) echo "Tipo inválido." ;;
  esac
}

# Función: Diagnóstico del sistema
diagnostico_sistema() {
  echo "=== Diagnóstico del Sistema ==="
  echo "CPU:"
  lscpu | grep 'Model name'
  echo "RAM:"
  free -h
  echo "Kernel:"
  uname -r
  echo "Distribución:"
  lsb_release -a 2>/dev/null || cat /etc/os-release
}

# Función: Gestión de software
gestion_software() {
  echo "1) Listar paquetes"
  echo "2) Buscar paquete"
  read -p "Opción: " opcion
  case $opcion in
    1) dpkg -l ;;
    2)
      read -p "Nombre del paquete: " paquete
      dpkg -s "$paquete" &>/dev/null && echo "Paquete instalado." || echo "No instalado."
      ;;
    *) echo "Opción inválida." ;;
  esac
}

# Función: Administración de discos
admin_discos() {
  echo "=== Esquema de particiones ==="
  lsblk
  echo "=== Uso de disco ==="
  df -h
}

# Función: Monitorización en vivo
monitor_vivo() {
  top
}

# Función: Visor de registros
visor_logs() {
  echo "1) Últimos eventos"
  echo "2) Monitorizar en tiempo real"
  read -p "Opción: " opcion
  case $opcion in
    1) journalctl -xe ;;
    2) journalctl -f ;;
    *) echo "Opción inválida." ;;
  esac
}

# Función: Gestión de procesos
gestion_procesos() {
  ps aux
  read -p "PID a terminar: " pid
  kill "$pid" && echo "Proceso terminado." || echo "Error al terminar proceso."
}

# Función: Monitorización de procesos
monitor_proceso() {
  read -p "Nombre del proceso: " nombre
  pid=$(pgrep -n "$nombre")
  if [[ -n "$pid" ]]; then
    echo "Monitorizando PID $pid..."
    watch -n 2 "ps -p $pid -o pid,cmd,%cpu,%mem"
  else
    echo "Proceso no encontrado."
  fi
}

# Función: Programación de backups
programar_backup() {
  read -p "Directorio origen: " origen
  read -p "Directorio destino: " destino
  if [[ ! -d "$origen" || ! -d "$destino" ]]; then
    echo "Directorios inválidos."
    return
  fi
  echo "Frecuencia:"
  echo "1) Diaria"
  echo "2) Semanal"
  echo "3) Mensual"
  read -p "Opción: " freq
  case $freq in
    1) tiempo="0 2 * * *" ;;
    2) tiempo="0 2 * * 1" ;;
    3) tiempo="0 2 1 * *" ;;
    *) echo "Frecuencia inválida."; return ;;
  esac
  comando="tar -czf $destino/backup_$(date +\%F).tar.gz $origen"
  (crontab -l 2>/dev/null; echo "$tiempo $comando") | crontab -
  echo "Backup programado."
}

# Función: Menú principal
mostrar_menu() {
  while true; do
    echo "===== SysAdmin Toolbox ====="
    echo "1) Gestión de Usuarios y Grupos"
    echo "2) Información de Cuentas"
    echo "3) Gestión de Enlaces"
    echo "4) Diagnóstico del Sistema"
    echo "5) Gestión de Software"
    echo "6) Administración de Discos"
    echo "7) Monitorización en Vivo"
    echo "8) Visor de Registros"
    echo "9) Gestión de Procesos"
    echo "10) Monitorización de Procesos"
    echo "11) Programación de Backups"
    echo "0) Salir"
    read -p "Selecciona una opción: " opcion
    case $opcion in
      1) gestion_usuarios ;;
      2) info_cuentas ;;
      3) gestion_enlaces ;;
      4) diagnostico_sistema ;;
      5) gestion_software ;;
      6) admin_discos ;;
      7) monitor_vivo ;;
      8) visor_logs ;;
      9) gestion_procesos ;;
      10) monitor_proceso ;;
      11) programar_backup ;;
      0) echo "👋 Saliendo..."; exit ;;
      *) echo "Opción inválida." ;;
    esac
    read -p "Presiona Enter para continuar..."
  done
}

# Manejo de parámetros
case "$1" in
  --info) diagnostico_sistema ;;
  --usuarios) gestion_usuarios ;;
  --discos) admin_discos ;;
  --procesos) gestion_procesos ;;
  *) mostrar_menu ;;
esac
