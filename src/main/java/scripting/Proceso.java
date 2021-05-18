package scripting;

import java.util.ArrayList;
import java.util.List;

public class Proceso {
    private String nombre;
    private List <variable.Variable> variables = new ArrayList<>();
    private List<String> instrucciones = new ArrayList<>();

    public Proceso(String nombre,List<variable.Variable> variables) {
        this.nombre = nombre;
        this.variables.addAll(variables);
    }
    
    
}
