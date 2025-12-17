# 🚀 INICIO RÁPIDO - Ejercicio01T9

## ⚡ Pasos para Ejecutar la App (5 minutos)

### 1️⃣ Abrir el Proyecto
```
1. Abre Android Studio
2. File > Open
3. Selecciona la carpeta: Ejercicio01T9
4. Espera a que Gradle sincronice las dependencias
```

### 2️⃣ Verificar Configuración
```
✅ SDK instalado: Android SDK 36 (o superior)
✅ Emulador configurado o dispositivo físico conectado
✅ Conexión a Internet activa
```

### 3️⃣ Ejecutar la App
```
1. Click en el botón "Run" (▶️) o presiona Shift+F10
2. Selecciona tu dispositivo/emulador
3. Espera a que la app se instale y se abra
```

### 4️⃣ Probar la Funcionalidad
```
1. En el campo de texto, ingresa: 1
2. Presiona el botón "Consultar"
3. Verás aparecer:
   - Toast: "Consultando..."
   - Toast: "Datos cargados correctamente"
   - Name: Google Pixel 6 Pro
   - Color: (valor del dispositivo)
   - Price: (valor del dispositivo)
```

---

## 🎯 IDs Rápidos para Probar

| ID | Dispositivo |
|----|-------------|
| 1  | Google Pixel 6 Pro |
| 2  | Apple iPhone 12 Mini |
| 3  | Apple iPhone 12 Pro Max |
| 7  | Apple MacBook Pro 16 |
| 10 | Apple iPad Mini 5th Gen |

---

## ❓ ¿Problemas?

### La app no compila
```bash
# Limpia el proyecto
.\gradlew.bat clean

# Reconstruye
.\gradlew.bat build
```

### No se muestran los datos
```
1. Verifica que el dispositivo tenga Internet
2. Prueba con ID: 1, 2, 3, 7 o 10
3. Revisa Logcat (filtro: DeviceController)
```

### La app se crashea
```
1. Verifica que INTERNET permission esté en AndroidManifest.xml
2. Revisa el stack trace en Logcat
3. Asegúrate de usar un emulador con Google Play
```

---

## 📚 Documentación Completa

- **README.md** - Documentación general del proyecto
- **ARQUITECTURA_MVC.txt** - Diagrama de la arquitectura
- **GUIA_PRUEBAS.md** - Casos de prueba detallados
- **RESUMEN_CAMBIOS.md** - Todos los cambios realizados

---

## ✅ Checklist Antes de Entregar

- [ ] La app compila sin errores
- [ ] Probé con al menos 3 IDs diferentes
- [ ] Los datos se muestran correctamente
- [ ] Los mensajes de error funcionan
- [ ] El código está comentado
- [ ] La documentación está completa

---

## 🎓 Estructura del Código (MVC)

```
📁 Modelo (modelos/)
   ├── Device.kt - Representa un dispositivo
   └── Data.kt - Datos del dispositivo

📁 Vista (MainActivity.kt + activity_main.xml)
   ├── Layout superior - Input del ID
   └── Layout inferior - Muestra los datos

📁 Controlador (controladores/)
   └── DeviceController.kt - Lógica de negocio

📁 Servicios (servicios/)
   ├── APIService.kt - Endpoints de la API
   └── ClienteRetrofit.kt - Configuración de Retrofit
```

---

## 🌐 API Utilizada

**URL Base:** https://api.restful-api.dev/

**Endpoints:**
- `GET /objects` - Obtener todos los objetos
- `GET /objects/{id}` - Obtener un objeto por ID

---

## 💡 Consejos

1. **Usa Logcat** para ver los logs y depurar
2. **Prueba con diferentes IDs** para ver diferentes datos
3. **Revisa el código** para entender el flujo MVC
4. **Lee la documentación** para más detalles

---

## 🎉 ¡Listo!

Tu app está completamente funcional y lista para usar.

**¿Dudas?** Revisa los archivos de documentación o consulta con tu profesor.

---

**Última actualización:** 2025-12-17
**Versión:** 1.0
**Estado:** ✅ Funcional
