package HilosConcadenados;

import java.util.List;

public class Combinador implements Runnable {
    private List<Integer> numeros;

    public Combinador(List<Integer> numeros){
        this.numeros = numeros;
    }

    @Override
    public void run() {
        String resultado = "";
        for(Integer numero: numeros){
            resultado += numero;
        }
        
        System.out.println("Los números concatenados son: " + resultado);
    }
    
}