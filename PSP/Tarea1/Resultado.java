public class Resultado {
    private int dorsal;
    private long tiempo;
    
    Resultado(int dorsal, long tiempo) {
        this.dorsal = dorsal;
        this.tiempo = tiempo;
    }

      public int getDorsal() {
            return dorsal;
        }

        public long getTiempo() {
            return tiempo;
        }
}
