package ejemplillo;
public class PersonaRunnable implements Runnable {
    private final Persona persona;
    private final int id;

    public PersonaRunnable(Persona persona, int id) {
        this.persona = persona;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println("Hilo-" + id + " preparándose...");
            // Simular trabajo previo
            Thread.sleep((long) (Math.random() * 400 + 100));

            // Cambiar atributos al entrar
            persona.setNombre("Persona-entrando-" + id);
            persona.setEdad(20 + id);
            System.out.println("Hilo-" + id + " ENTRA con: " + persona);

            // Estar dentro durante un tiempo aleatorio
            Thread.sleep((long) (Math.random() * 1000 + 500));

            // Cambiar atributos al salir
            persona.setNombre("Persona-saliendo-" + id);
            persona.setEdad(30 + id);
            System.out.println("Hilo-" + id + " SALE con: " + persona);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
