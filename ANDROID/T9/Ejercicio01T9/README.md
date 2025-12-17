# Ejercicio 01 T9 - Implementación MVC con Retrofit

## 📋 Resumen de Cambios

Se ha implementado correctamente el patrón **MVC (Modelo-Vista-Controlador)** para realizar peticiones HTTP a la API Rest de https://restful-api.dev/

### ✅ Problemas Solucionados

1. **Error de compilación en ClienteRetrofit.kt**: Se corrigió el tipo de retorno en `APIService` de `Response<List<Any>>` a `Response<List<Device>>`
2. **Modelos de datos actualizados**: Se ajustaron `Device` y `Data` para coincidir con la estructura real de la API
3. **Implementación completa del patrón MVC**

---

## 🏗️ Arquitectura MVC Implementada

### **Modelo** (Model)
📁 `modelos/`
- **`Device.kt`**: Representa un objeto de la API con id, name y data
- **`Data.kt`**: Representa los datos dinámicos de un dispositivo (color, capacity, price, etc.)

### **Vista** (View)
📁 `res/layout/`
- **`activity_main.xml`**: 
  - Layout superior (35%): Campo de texto para ID + botón "Consultar"
  - Layout inferior (50%): Muestra los datos del dispositivo (Name, Color, Price)

### **Controlador** (Controller)
📁 `controladores/`
- **`DeviceController.kt`**: Maneja la lógica de negocio
  - `obtenerDispositivoPorId(id: String)`: Consulta un dispositivo específico
  - `obtenerTodosLosDispositivos()`: Obtiene todos los dispositivos
  - Manejo de errores y logging

### **Servicios** (Services)
📁 `servicios/`
- **`APIService.kt`**: Define los endpoints de la API
  - `GET /objects`: Obtener todos los objetos
  - `GET /objects/{id}`: Obtener un objeto por ID
- **`ClienteRetrofit.kt`**: Configura Retrofit para hacer peticiones HTTP

---

## 🔧 Dependencias Utilizadas

```kotlin
// Retrofit 2 - Cliente HTTP
implementation("com.squareup.retrofit2:retrofit:2.11.0")

// GSON - Conversión JSON
implementation("com.squareup.retrofit2:converter-gson:2.11.0")

// OkHttp - Peticiones HTTP
implementation("com.squareup.okhttp3:okhttp:4.12.0")

// Coroutines - Operaciones asíncronas
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
```

---

## 🚀 Cómo Usar la Aplicación

### Paso 1: Ejecutar la App
1. Abre el proyecto en Android Studio
2. Conecta un dispositivo Android o inicia un emulador
3. Ejecuta la aplicación (Run > Run 'app')

### Paso 2: Consultar un Dispositivo
1. En el campo de texto superior, ingresa un **ID numérico** (por ejemplo: `1`, `2`, `3`, `7`, `10`)
2. Presiona el botón **"Consultar"**
3. La app hará una petición HTTP a `https://api.restful-api.dev/objects/{id}`
4. Los datos se mostrarán en el layout inferior:
   - **Name**: Nombre del dispositivo
   - **Color**: Color del dispositivo (si está disponible)
   - **Price/Capacity**: Precio o capacidad del dispositivo

### IDs de Ejemplo para Probar
- `1` - Google Pixel 6 Pro
- `2` - Apple iPhone 12 Mini
- `3` - Apple iPhone 12 Pro Max
- `7` - Apple MacBook Pro 16
- `10` - Apple iPad Mini 5th Gen

---

## 📱 Flujo de la Aplicación

```
Usuario ingresa ID → Presiona "Consultar"
    ↓
MainActivity (Vista)
    ↓
DeviceController (Controlador)
    ↓
ClienteRetrofit → APIService (Servicio)
    ↓
API Rest (https://api.restful-api.dev/objects/{id})
    ↓
Response JSON → Device (Modelo)
    ↓
MainActivity actualiza UI (Vista)
```

---

## 🎯 Características Implementadas

✅ Patrón MVC correctamente implementado
✅ Peticiones HTTP asíncronas con Coroutines
✅ Manejo de errores (try-catch)
✅ Validación de entrada (ID vacío)
✅ Mensajes Toast para feedback al usuario
✅ Logging para debugging
✅ Soporte para diferentes tipos de datos de la API
✅ Layout dividido en superior e inferior como se solicitó

---

## 🔍 Manejo de Errores

La aplicación maneja los siguientes casos:
- ✅ ID vacío: Muestra mensaje "Por favor, ingresa un ID"
- ✅ ID no encontrado: Muestra "No se encontró el dispositivo con ID: X"
- ✅ Error de red: Muestra "Error al consultar: [mensaje de error]"
- ✅ Datos nulos: Muestra "N/A" en los campos sin información

---

## 📝 Notas Técnicas

### Permisos Configurados
- `INTERNET`: Permite realizar peticiones HTTP
- `usesCleartextTraffic="true"`: Permite tráfico HTTP (no solo HTTPS)

### Coroutines
- Se usa `lifecycleScope.launch` para ejecutar las peticiones en el ciclo de vida de la Activity
- `withContext(Dispatchers.IO)` para ejecutar operaciones de red en un hilo secundario

### Modelos Flexibles
- La clase `Data` tiene todos los campos como opcionales (`?`) para soportar diferentes tipos de objetos de la API
- Se usan valores por defecto (`= null`) para campos que pueden no estar presentes

---

## 🐛 Solución de Problemas

### Si la app no compila:
1. Verifica que todas las dependencias estén sincronizadas
2. Ejecuta: `Build > Clean Project` y luego `Build > Rebuild Project`
3. Asegúrate de tener conexión a Internet para descargar las dependencias

### Si no se cargan los datos:
1. Verifica que el dispositivo/emulador tenga conexión a Internet
2. Revisa los logs en Logcat filtrando por "DeviceController"
3. Prueba con diferentes IDs (1, 2, 3, 7, 10)

---

## 👨‍💻 Autor
Proyecto desarrollado para el curso de Android - 2º DAM

**API utilizada**: https://restful-api.dev/
