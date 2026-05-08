from flask import Flask, request
import pyttsx3
import speech_recognition as sr
import sounddevice as sd
from scipy.io.wavfile import write
import io
import numpy as np

# 1. PRIMERO definimos la app
app = Flask(__name__)

# 2. Inicializar motor de voz (TTS)
try:
    engine = pyttsx3.init()
except Exception as e:
    print(f"Error al inicializar voz: {e}")

# --- RUTA PARA LEER (Java -> Python) ---
@app.route('/leer', methods=['GET'])
def leer():
    texto = request.args.get('texto')
    if texto:
        print(f"Leyendo: {texto}")
        try:
            engine.say(texto)
            engine.runAndWait()
            return "OK", 200
        except Exception as e:
            return f"Error: {e}", 500
    return "No hay texto", 400

# --- RUTA PARA ESCUCHAR (Python -> Java) ---
@app.route('/escuchar', methods=['GET'])
def escuchar():
    r = sr.Recognizer()
    fs = 44100  # Frecuencia de muestreo
    segundos = 5 # Tiempo de grabación
    
    try:
        print("Grabando... Habla ahora.")
        # Grabación directa con sounddevice
        grabacion = sd.rec(int(segundos * fs), samplerate=fs, channels=1, dtype='int16')
        sd.wait() # Espera a que pasen los 5 segundos
        print("Grabación finalizada, procesando...")

        # Convertir la grabación a un formato que entienda SpeechRecognition
        byte_io = io.BytesIO()
        write(byte_io, fs, grabacion)
        byte_io.seek(0)
        
        with sr.AudioFile(byte_io) as source:
            audio_data = r.record(source)
            # Intentar reconocer el texto
            texto = r.recognize_google(audio_data, language="es-ES")
            print(f"He entendido: {texto}")
            return texto, 200
            
    except sr.UnknownValueError:
        return "Error: No se entendió nada", 400
    except Exception as e:
        print(f"Error detallado: {e}")
        return f"Error: {e}", 500

# 3. LANZAR EL SERVIDOR
if __name__ == '__main__':
    print("--- SERVIDOR ACTIVO (TTS + STT) ---")
    app.run(host='0.0.0.0', port=5000, debug=False)