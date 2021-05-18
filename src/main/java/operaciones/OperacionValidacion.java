package operaciones;

import java.util.ArrayList;
import java.util.List;



public class OperacionValidacion {
    private static String integer = "INTEGER";
    private static String string = "STRING";
    private static String decimal = "DECIMAL";
    private static String char_ = "CHAR";
    private static String boolean_ = "BOOLEAN";
    
    public static boolean erroresSuma(String tipoIzquierda,String tipoDerecha){
        switch(tipoIzquierda){
            case "STRING":
                if(tipoDerecha == boolean_)return false;
            case "BOOLEAN":
                if(tipoDerecha == string)return false;
        }
        return true;
    }
    
    public static boolean AnalizarStringComleto(String tipoDerecha){
        if(tipoDerecha == integer)return false;
        if(tipoDerecha == string)return false;
        if(tipoDerecha == decimal)return false;
        if(tipoDerecha == char_)return false;
        if(tipoDerecha == boolean_)return false;
        return true;
    }
    
    public static boolean erroresResta (String tipoIzquierda,String tipoDerecha){
        switch(tipoIzquierda){
            case "INTEGER":
                if(tipoDerecha == string)return false;
            case "STRING":                
                return AnalizarStringComleto(tipoDerecha);
            case "DECIMAL":
                if(tipoDerecha == string)return false;
                
            case "CHAR":
                if(tipoDerecha == string)return false;
                if(tipoDerecha == boolean_)return false;
            
            case "BOOLEAN":
                if(tipoDerecha == string)return false;
                if(tipoDerecha == char_)return false;
                if(tipoDerecha == boolean_)return false;
        }
        return true;
    }
    
    public static boolean errorMultiplicacion(String tipoIzquierda,String tipoDerecha){
        switch(tipoIzquierda){
            case "INTEGER":
                if(tipoDerecha == string)return false;
            case "STRING":
                return AnalizarStringComleto(tipoDerecha);
            case "DECIMAL":
                if(tipoDerecha == string)return false;
            case "CHAR":
                if(tipoDerecha == string)return false;
            case "BOOLEAN":
                if(tipoDerecha == string)return false;                
        }
        return true;
    }
    
    public static boolean errorDivision(String tipoIzquierda,String tipoDerecha){
        switch(tipoIzquierda){
            case "INTEGER":
                if(tipoDerecha == string)return false;
            case "STRING":
                return AnalizarStringComleto(tipoDerecha);
            case "DECIMAL":
                if(tipoDerecha == integer)return false;
            case "CHAR":
                if(tipoDerecha == string)return false;
            case "BOOLEAN":
                if(tipoDerecha == string)return false;
                if(tipoDerecha == boolean_)return false;
        }
        return true;
    }
}
