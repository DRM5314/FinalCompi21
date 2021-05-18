package variable;

import java.util.ArrayList;
import java.util.List;
import lexema.Lexema;
import operaciones.*;
public class Manejador {
    private AuxAsignacion auxAsignacion = new AuxAsignacion();
    private List<Variable> variablesTotales = new ArrayList<>();
    
    public boolean verificador(){
        return false;
    }
    
    public void agregarVariables(List<Variable> entrada){
        this.variablesTotales.addAll(entrada);
    }
    
    
    
    public void reliazarAsignacion(Lexema ladoIzquierdo,String operando,Lexema ladoDerecho){
        String resultadoOperacion = ResultadoOperacion.tipoResultado(ladoIzquierdo.getTipo(), operando,ladoDerecho.getTipo());        
        String auxOperadorIzquierdo = ladoIzquierdo.getValor();
        String auxOperadorDerecho = ladoDerecho.getValor();
        if(ladoIzquierdo.isVariable()){
            auxOperadorIzquierdo = ladoIzquierdo.getNombreVariable();
        }
        if(ladoDerecho.isVariable()){
            auxOperadorDerecho = ladoDerecho.getNombreVariable();
        }
        String aux =  auxOperadorIzquierdo+ operando + auxOperadorDerecho;
        if(operando.equals("==")||operando.equals("!=")||operando.equals(">=")||operando.equals("<=")||operando.equals("<")||operando.equals(">")||operando.equals("!")){
            aux = "("+aux+")";
        }
        this.auxAsignacion.setResultadoOperacionTipo(resultadoOperacion, aux);        
    }
    
    public void reliazarAsignacion(String operando,Lexema ladoDerecho){        
        String resultadoOperacion = ResultadoOperacion.tipoResultado(operando,ladoDerecho.getTipo());
        String aux = operando + ladoDerecho.getValor();
        if(operando.equals("==")||operando.equals("!=")||operando.equals(">=")||operando.equals("<=")||operando.equals("<")||operando.equals(">")||operando.equals("!")){
            aux = "("+aux+")";
        }
        this.auxAsignacion.setResultadoOperacionTipo(resultadoOperacion, aux);        
    }
    
    public String resultadoOperacino (){
        return this.auxAsignacion.getResultado();
    }
    
    public String getConcatenacion(){
        return this.auxAsignacion.getConcatenacion();
    }
        
    public Lexema convertirALexema(){
        return new lexema.Lexema(this.auxAsignacion.getConcatenacion(), this.auxAsignacion.getResultado());
    }
    
    public Lexema convertirALexema(Lexema entrada){
        this.auxAsignacion.setResultadoOperacionTipo(entrada.getTipo(), entrada.getValor());
        return entrada;
    }
}
