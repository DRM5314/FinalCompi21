package reportes;

import java.util.List;

public class Reporte {

    String id;
    int fallas, aciertos, usos;

    public void parserEntrada(List<String> parametros, List<Integer> datos) {
        for (int i = 0; i < parametros.size(); i++) {
            switch (parametros.get(i)) {
                case "FALLAS":
                    this.fallas = datos.get(i);
                    break;
                case "ACIERTOS":
                    this.aciertos = datos.get(i);
                    break;
                case "USOS":
                    this.usos = datos.get(i);
                    break;
            }
        }
    }

    public void aumentarFalla() {
        this.fallas = fallas + 1;
    }

    public void aumentarAcierto() {
        this.aciertos = aciertos + 1;
    }

    public void aumentarUso() {
        this.usos = usos + 1;
    }

    public int getFallas() {
        return fallas;
    }

    public int getAciertos() {
        return aciertos;
    }

    public int getUsos() {
        return usos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

}
