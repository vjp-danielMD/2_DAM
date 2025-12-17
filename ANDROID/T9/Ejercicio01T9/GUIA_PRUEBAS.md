# 🧪 GUÍA DE PRUEBAS - Ejercicio01T9

## ✅ Checklist de Verificación

### 1. Compilación del Proyecto
- [ ] El proyecto compila sin errores
- [ ] No hay warnings críticos en el build
- [ ] Todas las dependencias se descargan correctamente

**Comando para compilar:**
```bash
.\gradlew.bat assembleDebug
```

---

### 2. Permisos y Configuración
- [ ] Permiso INTERNET está en AndroidManifest.xml
- [ ] usesCleartextTraffic="true" está configurado
- [ ] La app se instala correctamente en el dispositivo/emulador

---

### 3. Interfaz de Usuario (Layout)
- [ ] Se muestra el layout superior (color teal/verde azulado)
- [ ] Se muestra el layout inferior (color azul)
- [ ] El campo de texto acepta números
- [ ] El botón "Consultar" es visible y clickable
- [ ] Los TextViews están correctamente posicionados

---

### 4. Funcionalidad - Casos de Prueba

#### ✅ Caso 1: Consultar ID Válido
**Pasos:**
1. Ingresa el ID: `1`
2. Presiona "Consultar"

**Resultado Esperado:**
- Toast: "Consultando..."
- Toast: "Datos cargados correctamente"
- Name: "Google Pixel 6 Pro"
- Color: (valor del dispositivo o "N/A")
- Price: (valor del dispositivo o "N/A")

---

#### ✅ Caso 2: Consultar Otro ID Válido
**Pasos:**
1. Ingresa el ID: `7`
2. Presiona "Consultar"

**Resultado Esperado:**
- Toast: "Consultando..."
- Toast: "Datos cargados correctamente"
- Name: "Apple MacBook Pro 16"
- Color: (valor del dispositivo)
- Price: (valor del dispositivo)

---

#### ❌ Caso 3: ID Vacío
**Pasos:**
1. Deja el campo de texto vacío
2. Presiona "Consultar"

**Resultado Esperado:**
- Toast: "Por favor, ingresa un ID"
- No se realiza ninguna petición HTTP
- Los datos anteriores permanecen (o están vacíos)

---

#### ❌ Caso 4: ID No Existente
**Pasos:**
1. Ingresa el ID: `99999`
2. Presiona "Consultar"

**Resultado Esperado:**
- Toast: "Consultando..."
- Toast: "No se encontró el dispositivo con ID: 99999"
- Los TextViews se limpian (vacíos)

---

### 5. IDs de Prueba Recomendados

| ID | Dispositivo Esperado | Tiene Data |
|----|---------------------|------------|
| 1  | Google Pixel 6 Pro  | ✅ Sí      |
| 2  | Apple iPhone 12 Mini | ✅ Sí     |
| 3  | Apple iPhone 12 Pro Max | ✅ Sí  |
| 4  | Apple iPhone 11     | ✅ Sí      |
| 5  | Samsung Galaxy Z Fold2 | ✅ Sí   |
| 6  | Apple AirPods       | ✅ Sí      |
| 7  | Apple MacBook Pro 16 | ✅ Sí     |
| 8  | Apple Watch Series 8 | ✅ Sí     |
| 9  | Beats Studio3 Wireless | ✅ Sí   |
| 10 | Apple iPad Mini 5th Gen | ✅ Sí  |

---

### 6. Verificación de Logs (Logcat)

**Filtro recomendado:** `DeviceController`

**Logs esperados al consultar ID 1:**
```
D/DeviceController: Dispositivo obtenido: Device(id=1, name=Google Pixel 6 Pro, data=Data(...))
```

**Logs esperados al consultar ID inválido:**
```
E/DeviceController: Error: 404 - Not Found
```

---

### 7. Pruebas de Red

#### Con Conexión a Internet
- [ ] Las peticiones se completan exitosamente
- [ ] Los datos se muestran correctamente
- [ ] Los Toasts aparecen en el momento adecuado

#### Sin Conexión a Internet
- [ ] Se muestra un mensaje de error
- [ ] La app no se crashea
- [ ] Se puede volver a intentar cuando se restaure la conexión

---

### 8. Pruebas de Rotación de Pantalla

**Pasos:**
1. Consulta un dispositivo (ej: ID 1)
2. Rota el dispositivo (portrait ↔ landscape)

**Resultado Esperado:**
- ⚠️ Los datos se pierden (comportamiento normal sin ViewModel)
- La app no se crashea
- Se puede volver a consultar

**Nota:** Para mantener los datos en rotación, se necesitaría implementar ViewModel (tema avanzado).

---

### 9. Pruebas de Rendimiento

- [ ] La app responde rápidamente al click del botón
- [ ] No hay lag en la UI durante la petición HTTP
- [ ] Los Toasts no se superponen

---

### 10. Verificación del Patrón MVC

#### Modelo (Model)
- [ ] `Device.kt` existe y tiene los campos correctos
- [ ] `Data.kt` existe y tiene campos opcionales
- [ ] Los modelos son data classes

#### Vista (View)
- [ ] `MainActivity.kt` maneja la UI
- [ ] `activity_main.xml` define el layout
- [ ] No hay lógica de negocio en MainActivity

#### Controlador (Controller)
- [ ] `DeviceController.kt` existe
- [ ] Contiene la lógica de negocio
- [ ] Maneja errores correctamente
- [ ] Usa coroutines para operaciones asíncronas

#### Servicios (Services)
- [ ] `APIService.kt` define los endpoints
- [ ] `ClienteRetrofit.kt` configura Retrofit
- [ ] La URL base es correcta

---

## 🐛 Problemas Comunes y Soluciones

### Problema: "Unable to resolve dependency"
**Solución:**
1. Verifica tu conexión a Internet
2. Ejecuta: `.\gradlew.bat clean`
3. Ejecuta: `.\gradlew.bat build --refresh-dependencies`

### Problema: "CLEARTEXT communication not permitted"
**Solución:**
- Verifica que `android:usesCleartextTraffic="true"` esté en AndroidManifest.xml

### Problema: La app se crashea al presionar "Consultar"
**Solución:**
1. Revisa Logcat para ver el stack trace
2. Verifica que el permiso INTERNET esté configurado
3. Asegúrate de que el dispositivo/emulador tenga conexión a Internet

### Problema: No se muestran los datos
**Solución:**
1. Verifica los logs con filtro "DeviceController"
2. Prueba con diferentes IDs (1, 2, 3, 7, 10)
3. Verifica que la API esté disponible: https://api.restful-api.dev/objects/1

---

## 📊 Resultados Esperados

### ✅ Prueba Exitosa
- Compilación sin errores
- Todos los casos de prueba pasan
- Los logs muestran información correcta
- La UI se actualiza correctamente
- No hay crashes

### ❌ Prueba Fallida
- Errores de compilación
- Crashes al consultar
- Datos no se muestran
- Toasts no aparecen

---

## 📝 Notas Finales

- La API https://restful-api.dev/ es pública y gratuita
- Los datos pueden cambiar con el tiempo
- Algunos IDs pueden no existir
- La API puede estar temporalmente no disponible

**Fecha de última prueba:** [Pendiente]
**Resultado:** [Pendiente]
**Probado por:** [Tu nombre]
