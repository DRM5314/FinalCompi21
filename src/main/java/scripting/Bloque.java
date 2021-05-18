package scripting;

import java.util.ArrayList;
import java.util.List;

public class Bloque {
    private int numeroProcesos = 0;
    
    private List<String> id = new ArrayList<>();
    private List<String> tipo = new ArrayList<>();
    private List<String> condicion = new ArrayList<>();
    private List<String> inicio = new ArrayList<>();
    private List<String> modo = new ArrayList<>();
    private List<String> tipoVar = new ArrayList<>();
    
    public void agregarLinea(String id, String tipo,String condicion,String inicio,String modo,String tipoVar){
        this.id.add(id);
        this.tipo.add(tipo);
        this.condicion.add(condicion);
        this.inicio.add(inicio);
        this.modo.add(modo);
        this.tipoVar.add(tipoVar);
        //System.out.println("        Agrego instruccion tipo: "+tipo+" id "+id+" condicion: "+condicion+" inicializacion "+inicio+" modo: "+modo);
    }

    public List<String> getTipoVar() {
        return tipoVar;
    }
    
    public void getInfo(){
        System.out.println("Llamado desde reset locales ");
        for (int i = 0; i < tipo.size(); i++) {
            System.out.print(tipo.get(i));
            if(id.get(i)!=null){
                System.out.print(" Id: "+id.get(i));
            }
            if(condicion.get(i)!=null){
                System.out.print(" Condicion: "+condicion.get(i));
            }
            if(inicio.get(i)!=null){
                System.out.print(" Inicio: "+inicio.get(i));
            }
            if(modo.get(i)!=null){
                System.out.print(" Modo: "+modo.get(i));
            }
            System.out.println("");
        }
    }

    public List<String> getId() {
        return id;
    }

    public List<String> getTipo() {
        return tipo;
    }

    public List<String> getCondicion() {
        return condicion;
    }

    public List<String> getInicio() {
        return inicio;
    }

    public List<String> getModo() {
        return modo;
    }
    
}
