package error;

import java.util.ArrayList;
import java.util.List;

public class ErroresPeticion {

    //tipo,dato,posXY
    private List<String[]> error = new ArrayList<>();
    private List<int[]> posAnterior = new ArrayList<>();
    private int contadorLexicos = 0;
    private int contadorSintacticos = 0;

    public void agregarError(String tipo, String info, int[] posXY) {
        boolean errorLexicoAdd = true;
        if (tipo.equalsIgnoreCase("SINTACTICO") || tipo.equalsIgnoreCase("SEMANTICO")) {
            contadorSintacticos++;
        } else if (tipo.equalsIgnoreCase("LEXICO")) {
            if (posAnterior.size() > 0 && this.error.size() > 0) {
                int pos = this.error.size() - 1;
                if (this.error.get(pos)[0].equalsIgnoreCase("LEXICO")) {
                    int ultimaPosArreglo = posAnterior.size() - 1;
                    int posUltError[] = posAnterior.get(ultimaPosArreglo);
                    if (posUltError[0] == posXY[0]) {
                        int resta = posXY[1] - posUltError[1];
                        if (resta == 1) {
                            errorLexicoAdd = false;
                            agrupadorMensaje(info);
                        }
                    }
                }
            }
            posAnterior.add(posXY);
        }
        if (errorLexicoAdd) {
            this.error.add(new String[]{tipo, info, "Linea: " + posXY[0] + " Columna: " + posXY[1]});
        }
    }

    public void agrupadorMensaje(String concatenar) {
        int pos = this.error.size() - 1;
        String mensajeAnterior = this.error.get(pos)[1];
        this.error.get(pos)[1] = mensajeAnterior + " " + concatenar;
    }

    public List<String[]> getError() {
        return error;
    }

    public int getContadorLexicos() {
        return contadorLexicos;
    }

    public int getContadorSintacticos() {
        return contadorSintacticos;
    }

    private void verErrores() {
        System.out.println("---- errores llamada desde existen errores en errores peticion-------");
        System.out.println("Errores :");
        for (String[] errores : error) {
            System.out.println("Tipo " + errores[0] + " Info " + errores[1] + " " + errores[2]);
        }
        System.out.println("---FIN ERRORES----");
    }

    public boolean existenErrores() {
        verErrores();
        return error.size() > 0;
    }

}
