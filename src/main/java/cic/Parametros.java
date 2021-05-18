package cic;

import java.util.ArrayList;
import java.util.List;

public class Parametros {

    private List<String> tipo = new ArrayList<>();
    private List<String> datos = new ArrayList<>();

    public Parametros(List<String> parametros, List<String> datos) {
        this.tipo.addAll(parametros);
        this.datos.addAll(datos);
    }

    public void getInfoParametros() {
        for (int i = 0; i < tipo.size(); i++) {
            System.out.println(i + "  " + tipo.get(i) + "  " + datos.get(i));
        }
    }

    public String buscarInfo(String tipoParametro) {
        if(tipoParametro.equals("BACKGROUND")){
            System.out.println("exito viejo");
        }
        tipoParametro = tipoParametro.toUpperCase();
        //System.out.print("Buscando " + tipoParametro + "  ");
        if (this.tipo.contains(tipoParametro)) {
            int pos = this.tipo.indexOf(tipoParametro);
            return this.datos.get(pos);
        } else {
            return null;
        }
    }

    public void setParametro(String tipoDato, String dato) {
        int buscador = this.tipo.indexOf(tipoDato.toUpperCase());
        if (buscador >= 0) {
            //System.out.println("Seteo " + this.datos.get(buscador) + "  a " + dato);
            this.datos.set(buscador, dato);
        }

    }

    public void setParametros(List<String> parametros, List<String> datos, Parametros parametrosOff) {
        if (parametros.size() > 0) {
            for (int i = 0; i < parametros.size(); i++) {
                parametrosOff.setParametro(parametros.get(i), datos.get(i));
            }
        }
    }
    
    public String[] getParametros(int i){
        return new String [] {this.tipo.get(i),this.datos.get(i)};
    }
    public int size(){
        return this.tipo.size();
    }
    public boolean isThis(String idEntrada){
        return this.datos.contains(idEntrada);
    }
}
