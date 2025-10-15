package main;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Ejercicio5 {

    public static void main(String[] args) {

        String json = "[{\"nombre\": \"Pepe\",\"apellidos\": \"Lopez\","
                + "\"edad\": 20,\"puestos\": [\"gerente de java con 2\" ]},"
                + "{\"nombre\": \"Maria\",\"apellidos\": \"Gutierrez\","
                + "\"edad\": 30,\"puestos\": [\"jefa RR.HH\"]}]";

        Gson gson = new Gson();
        Type tipolistaEmpleados = new TypeToken<List<Empleado>>() {}.getType();
        List<Empleado> empleados = gson.fromJson(json, tipolistaEmpleados);
        Iterator<Empleado> itemp = empleados.iterator();

        while (itemp.hasNext()) {
            System.out.println(itemp.next().toString());
        }
    }
}
