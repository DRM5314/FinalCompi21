package operaciones;

public class AuxAsignacion {
    private String concatenacion;
    String resultado = "";
    public void setResultadoOperacionTipo(String resultado,String concatenacion){
        this.concatenacion = concatenacion;
        this.resultado = resultado;
    }

    public String getConcatenacion() {
        return concatenacion;
    }

    public String getResultado() {
        return resultado;
    }
    
}
