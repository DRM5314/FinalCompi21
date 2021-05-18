package cic;

import java.util.ArrayList;
import java.util.List;

public class ParametrosDefecto {
    private int contadorId = 0;
    private String idCreciente (){
        contadorId++;
        return "ID"+contadorId;        
    }
    private List<String> parametrosFonts(){
        List<String> aux = new ArrayList<>();
        aux.add("FONT_SIZE");
        aux.add("FONT_FAMILY");
            aux.add("TEXT_ALIGN");
        return aux;
    }
    private List<String> datosFont(){
        List<String> aux = new ArrayList<>();
        aux.add("20px");
        aux.add("Arial");
        aux.add("left");        
        return aux;
    }
    private List<String> parametros_ID_COLOR_FONTS(){
        List<String> aux = new ArrayList<>();
        aux.addAll(parametrosFonts());
        aux.add("COLOR");
        aux.add("ID");
        return aux;
    }
    private List<String> datos_ID_COLOR_FONTS(){
        List<String> aux = new ArrayList<>();
        aux.addAll(datosFont());
        aux.add("black");
        aux.add(idCreciente());        
        return aux;
    }
        
    public Parametros manejadorParametros(String tipo){        
        List<String> parametrosDefecto = new ArrayList<>();
        List<String> datosDefecto = new ArrayList<>();
        Parametros retorno = new Parametros(parametrosDefecto, datosDefecto);
        switch(tipo){
            case "C_GCIC":
                parametrosDefecto.add("ID");
                datosDefecto.add(idCreciente());                
                parametrosDefecto.add("NAME");
                datosDefecto.add("NAME1");                
                return new Parametros(parametrosDefecto,datosDefecto);            
            case "C_LINK":
                parametrosDefecto.add("HREF");
                datosDefecto.add("https://www.google.com/");
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_BODY":
                parametrosDefecto.add("BACKGROUND");
                datosDefecto.add("black");
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_SPAM":
                parametrosDefecto.add("ID");
                datosDefecto.add(idCreciente());
                parametrosDefecto.add("COLOR");
                datosDefecto.add("black");
                parametrosDefecto.addAll(parametrosFonts());
                datosDefecto.addAll(datosFont());
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_INPUT":
                parametrosDefecto.addAll(parametros_ID_COLOR_FONTS());
                datosDefecto.addAll(datos_ID_COLOR_FONTS());
                parametrosDefecto.add("TYPE");
                datosDefecto.add("text");
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_TEXTAREA":
                parametrosDefecto.add("ID");
                datosDefecto.add(idCreciente());
                parametrosDefecto.add("COLS");
                datosDefecto.add("20");
                parametrosDefecto.add("ROWS");
                datosDefecto.add("5");
                parametrosDefecto.addAll(parametrosFonts());
                datosDefecto.addAll(datosFont());
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_SELECT":
                parametrosDefecto.addAll(parametros_ID_COLOR_FONTS());
                datosDefecto.addAll(datos_ID_COLOR_FONTS());
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_DIV":
                parametrosDefecto.addAll(parametros_ID_COLOR_FONTS());
                datosDefecto.addAll(datos_ID_COLOR_FONTS());
                parametrosDefecto.add("CLASS");
                datosDefecto.add("col1");
                parametrosDefecto.add("BACKGROUND");
                datosDefecto.add("black");
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_IMG":
                parametrosDefecto.add("SRC");
                datosDefecto.add("https://cdn.lifehack.org/wp-content/uploads/2015/01/Fed-up-with-Distorted-Texts-for-Verification-Google-Is-Offering-New-CAPTCHA.jpg");
                parametrosDefecto.add("WIDTH");
                datosDefecto.add("600px");
                parametrosDefecto.add("HEIGHT");
                datosDefecto.add("600px");
                parametrosDefecto.add("ALT");
                datosDefecto.add("IMAGEN NO CARGADA");
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_BUTTON":
                parametrosDefecto.addAll(parametros_ID_COLOR_FONTS());
                datosDefecto.addAll(datos_ID_COLOR_FONTS());
                parametrosDefecto.add("BACKGROUND");
                datosDefecto.add("black");
                parametrosDefecto.add("ONCLICK");
                datosDefecto.add("crear()");
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_H1":
                parametrosDefecto.addAll(parametros_ID_COLOR_FONTS());
                datosDefecto.addAll(datos_ID_COLOR_FONTS());                
                return new Parametros(parametrosDefecto,datosDefecto);
            case "C_P":
                parametrosDefecto.addAll(parametros_ID_COLOR_FONTS());
                datosDefecto.addAll(datos_ID_COLOR_FONTS());                
                return new Parametros(parametrosDefecto,datosDefecto);
        }
        return retorno;
    }        
}
