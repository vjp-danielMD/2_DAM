package ejemplillo;

import java.util.Scanner;

public class prueba {
    // ANSI colors and styles for nicer console output
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String WHITE_BG = "\u001B[47m";
    private static final String BLACK_TEXT = "\u001B[30m";

    private static void printHeader(String title) {
        String line = "═".repeat(50);
        System.out.println(BLUE + "╔" + line + "╗" + RESET);
        System.out.println(BLUE + "║" + RESET + CENTER(title, 50) + BLUE + "║" + RESET);
        System.out.println(BLUE + "╚" + line + "╝" + RESET);
    }

    private static void printMenuItem(int num, String title, String description) {
        System.out.println(BLUE + "┌──────┬" + "─".repeat(42) + "┐" + RESET);
        System.out.println(BLUE + "│" + RESET + CYAN + BOLD + " [" + num + "]  " + RESET + 
                         BLUE + "│" + RESET + " " + title + " ".repeat(Math.max(0, 41 - title.length())) + 
                         BLUE + "│" + RESET);
        System.out.println(BLUE + "│      │" + RESET + " " + description + " ".repeat(Math.max(0, 41 - description.length())) + 
                         BLUE + "│" + RESET);
        System.out.println(BLUE + "└──────┴" + "─".repeat(42) + "┘" + RESET);
    }

    private static String CENTER(String text, int width) {
        int spaces = width - text.length();
        int padLeft = spaces / 2;
        int padRight = spaces - padLeft;
        return " ".repeat(padLeft) + text + " ".repeat(padRight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            clearScreen();
            printHeader(" 📚 Demos de Concurrencia en Java (PSP) 📚 ");
            System.out.println();
            
            printMenuItem(1, "Instancia Compartida", "Hilos modificando un objeto común");
            printMenuItem(2, "Wait/Notify Pattern", "Productor/Consumidor sincronizado");
            printMenuItem(3, "Synchronized vs Normal", "Comparativa de sincronización");
            printMenuItem(4, "Hilos Encadenados", "Ejecución ordenada con join()");
            printMenuItem(5, "Salir", "Terminar programa");
            
            System.out.println();
            System.out.print(YELLOW + BOLD + " ⭐ Selecciona una opción: " + RESET);

            String line = sc.nextLine().trim();
            System.out.println();

            try {
                switch (line) {
                    case "1" -> runPersonaDemo();
                    case "2" -> runWaitNotifyDemo();
                    case "3" -> runSynchronizedDemo();
                    case "4" -> runChainedThreadsDemo();
                    case "5" -> running = false;
                    default -> System.out.println(RED + "Opción no válida." + RESET);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(RED + "Demo interrumpida." + RESET);
            }

            if (running) {
                System.out.println();
                System.out.print(CYAN + "Pulsa ENTER para volver al menú..." + RESET);
                sc.nextLine();
            }
        }

        sc.close();
        System.out.println(GREEN + "Saliendo. Hasta luego!" + RESET);
    }

    // Demo 1: instancia compartida y hilos que cambian sus atributos
    private static void runPersonaDemo() throws InterruptedException {
        printBoxedTitle("Demo 1: Instancia Compartida (Persona)", GREEN);
        System.out.println(CYAN + "➜ Objetivo: " + RESET + "Demostrar acceso concurrente a objeto compartido");
        System.out.println(CYAN + "➜ Descripción: " + RESET + "5 hilos modifican la misma instancia de Persona");
        System.out.println();
        
        Persona p = new Persona("Inicial", 0);
        printState("Estado inicial", p);

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            int id = i + 1;
            threads[i] = new Thread(() -> {
                try {
                    logStyled("⚡", "Hilo-" + id + " preparándose...", YELLOW);
                    Thread.sleep((long) (Math.random() * 300 + 100));
                    p.setNombre("Persona-entrando-" + id);
                    p.setEdad(20 + id);
                    logStyled("→", "Hilo-" + id + " ENTRA con: " + p, GREEN);
                    Thread.sleep((long) (Math.random() * 1000 + 300));
                    p.setNombre("Persona-saliendo-" + id);
                    p.setEdad(30 + id);
                    logStyled("←", "Hilo-" + id + " SALE con: " + p, YELLOW);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();
        System.out.println(CYAN + "Estado final compartido: " + p + RESET);
    }

    // Demo 2: wait / notify (productor consumidor simple con buffer de 1 elemento)
     private static void runWaitNotifyDemo() throws InterruptedException {
         printBoxedTitle("Demo 2: Wait/Notify (Productor/Consumidor)", GREEN);
         System.out.println(CYAN + "➜ Objetivo: " + RESET + "Demostrar sincronización mediante wait/notify");
         System.out.println(CYAN + "➜ Descripción: " + RESET + "Productor genera números, consumidor los procesa");
         System.out.println(CYAN + "➜ Buffer: " + RESET + "Capacidad 1 elemento (bloqueo si lleno/vacío)");
         System.out.println();

         final Object lock = new Object();
         final int[] buffer = new int[1];
         final boolean[] available = {false};

         Thread producer = new Thread(() -> {
             for (int i = 1; i <= 5; i++) {
                 synchronized (lock) {
                     while (available[0]) {
                         try {
                             logStyled("💤", "Productor esperando - buffer lleno", YELLOW);
                             lock.wait();
                         } catch (InterruptedException e) {
                             Thread.currentThread().interrupt();
                         }
                     }
                     buffer[0] = i;
                     available[0] = true;
                     logStyled("📤", "Productor genera: " + i, GREEN);
                     lock.notifyAll();
                 }
                 try {
                     Thread.sleep(200);
                 } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                 }
             }
         });

         Thread consumer = new Thread(() -> {
             for (int i = 1; i <= 5; i++) {
                 synchronized (lock) {
                     while (!available[0]) {
                         try {
                             logStyled("💤", "Consumidor esperando - buffer vacío", YELLOW);
                             lock.wait();
                         } catch (InterruptedException e) {
                             Thread.currentThread().interrupt();
                         }
                     }
                     int v = buffer[0];
                     available[0] = false;
                     logStyled("📥", "Consumidor procesa: " + v, PURPLE);
                     lock.notifyAll();
                 }
                 try {
                     Thread.sleep(300);
                 } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                 }
             }
         });

         System.out.println(BLUE + "┌" + "─".repeat(48) + "┐" + RESET);
         System.out.println(BLUE + "│" + RESET + " 🚀 Iniciando hilos productor y consumidor..." + 
                          " ".repeat(8) + BLUE + "│" + RESET);
         System.out.println(BLUE + "└" + "─".repeat(48) + "┘" + RESET);
         System.out.println();

         producer.start();
         consumer.start();
         producer.join();
         consumer.join();
         
         printBoxedTitle("✨ Demo Wait/Notify Completada", CYAN);
     }

     // Demo 3: synchronized vs unsynchronized counter
     private static void runSynchronizedDemo() throws InterruptedException {
         printBoxedTitle("Demo 3: Synchronized vs No-Sync Comparison", GREEN);
         System.out.println(CYAN + "➜ Objetivo: " + RESET + "Comparar resultados con/sin sincronización");
         System.out.println(CYAN + "➜ Operación: " + RESET + "4 hilos × 1000 incrementos cada uno");
         System.out.println(CYAN + "➜ Esperado: " + RESET + "4000 en ambos contadores");
         System.out.println();

         final Counter synchronizedCounter = new Counter(true);
         final Counter unsynchronizedCounter = new Counter(false);

         Thread[] t1 = new Thread[4];
         Thread[] t2 = new Thread[4];

         for (int i = 0; i < 4; i++) {
             t1[i] = new Thread(() -> {
                 for (int j = 0; j < 1000; j++) synchronizedCounter.increment();
             }, "Sync-" + (i+1));
             t2[i] = new Thread(() -> {
                 for (int j = 0; j < 1000; j++) unsynchronizedCounter.increment();
             }, "NoSync-" + (i+1));
         }

         System.out.println(BLUE + "┌" + "─".repeat(48) + "┐" + RESET);
         System.out.println(BLUE + "│" + RESET + " 🧪 Iniciando prueba de concurrencia..." + 
                          " ".repeat(12) + BLUE + "│" + RESET);
         System.out.println(BLUE + "└" + "─".repeat(48) + "┘" + RESET);
         System.out.println();

         long startTime = System.currentTimeMillis();
         for (int i = 0; i < 4; i++) { t1[i].start(); t2[i].start(); }
         for (int i = 0; i < 4; i++) { t1[i].join(); t2[i].join(); }
         long endTime = System.currentTimeMillis();

         System.out.println();
         printState("Contador Sincronizado  ", synchronizedCounter.get() + " ✅");
         printState("Contador Sin Sincronizar", unsynchronizedCounter.get() + 
                   (unsynchronizedCounter.get() == 4000 ? " ✅" : " ❌"));
         System.out.println();
         printState("Tiempo Total", (endTime - startTime) + "ms");
     }

     // Demo 4: hilos encadenados usando join para forzar orden
     private static void runChainedThreadsDemo() throws InterruptedException {
         printBoxedTitle("Demo 4: Hilos Encadenados con join()", GREEN);
         System.out.println(CYAN + "➜ Objetivo: " + RESET + "Forzar orden de ejecución usando join()");
         System.out.println(CYAN + "➜ Patrón: " + RESET + "Cada hilo espera a que termine el anterior");
         System.out.println(CYAN + "➜ Resultado: " + RESET + "Ejecución secuencial garantizada");
         System.out.println();

         System.out.println(BLUE + "┌" + "─".repeat(48) + "┐" + RESET);
         System.out.println(BLUE + "│" + RESET + " 🔗 Creando cadena de hilos..." + 
                          " ".repeat(20) + BLUE + "│" + RESET);
         System.out.println(BLUE + "└" + "─".repeat(48) + "┘" + RESET);
         System.out.println();

         Thread previous = null;
         for (int i = 1; i <= 5; i++) {
             final int id = i;
             Thread prevRef = previous;
             Thread t = new Thread(() -> {
                 try {
                     if (prevRef != null) {
                         logStyled("⏳", "Hilo-" + id + " esperando a Hilo-" + (id-1), YELLOW);
                         prevRef.join();
                     }
                 } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                 }
                 logStyled("✨", "Hilo-" + id + " ejecuta y termina", GREEN);
             }, "Chain-" + id);
             t.start();
             previous = t;
             Thread.sleep(50);
         }

         if (previous != null) previous.join();
         printBoxedTitle("🎉 Cadena de Hilos Completada", CYAN);
     }
     // ---- Helpers and small inner classes ----
     private static void printBoxedTitle(String title, String color) {
        String line = "─".repeat(50);
        System.out.println(color + "┌" + line + "┐");
        System.out.println("│" + CENTER(title, 50) + "│");
        System.out.println("└" + line + "┘" + RESET);
    }

    private static void printState(String label, Object state) {
        System.out.println(BLUE + "┌─" + "─".repeat(label.length()) + "─┬" + "─".repeat(35) + "┐" + RESET);
        System.out.println(BLUE + "│ " + RESET + BOLD + label + BLUE + " │" + RESET + 
                         " " + state.toString() + " ".repeat(Math.max(0, 34 - state.toString().length())) + 
                         BLUE + "│" + RESET);
        System.out.println(BLUE + "└─" + "─".repeat(label.length()) + "─┴" + "─".repeat(35) + "┘" + RESET);
    }

    private static void logStyled(String icon, String message, String color) {
        String threadName = Thread.currentThread().getName();
        System.out.println(color + icon + " [" + threadName + "] " + message + RESET);
    }

    private static void clearScreen() {
        // Intenta limpiar consola; si no funciona, imprime separador
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(BLUE + "╔" + "═".repeat(50) + "╗" + RESET);
    }

     // Simple persona para el demo (variables sincronizadas)
     private static class Persona {
         private String nombre;
         private int edad;

         Persona(String nombre, int edad) {
             this.nombre = nombre;
             this.edad = edad;
         }

         synchronized String getNombre() { return nombre; }
         synchronized void setNombre(String nombre) { this.nombre = nombre; }
         synchronized int getEdad() { return edad; }
         synchronized void setEdad(int edad) { this.edad = edad; }

         @Override
         public synchronized String toString() {
             return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
         }
     }

     // Counter demo
     private static class Counter {
         private int value = 0;
         private final boolean sync;

         Counter(boolean sync) { this.sync = sync; }

         void increment() {
             if (sync) {
                 synchronized (this) { value++; }
             } else {
                 value++;
             }
         }

         int get() { return value; }
     }

 }
