package ejemplillo;
public class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public synchronized String getNombre() {
        return nombre;
    }

    public synchronized void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public synchronized int getEdad() {
        return edad;
    }

    public synchronized void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public synchronized String toString() {
        return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
    }
}
