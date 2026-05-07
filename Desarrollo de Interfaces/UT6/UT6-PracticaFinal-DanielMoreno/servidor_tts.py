from flask import Flask, request
import pyttsx3

app = Flask(__name__)

# Inicializar motor de voz
try:
    engine = pyttsx3.init()
except Exception as e:
    print(f"Error al inicializar voz: {e}")

@app.route('/leer', methods=['GET'])
def leer():
    texto = request.args.get('texto')
    if texto:
        print(f"Peticion recibida: {texto}")
        try:
            engine.say(texto)
            engine.runAndWait()
            return "OK", 200
        except Exception as e:
            return f"Error al hablar: {e}", 500
    return "Error: No hay texto", 400

if __name__ == '__main__':
    print("--- SERVIDOR TTS ACTIVO ---")
    print("Escuchando en: http://127.0.0.1:5000/leer")
    app.run(host='0.0.0.0', port=5000, debug=False)