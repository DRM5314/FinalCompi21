package lexema;

public class Lexema {
    private String valor,tipo;    
    private boolean isVariable = false;
    private String nombreVariable = null;
    public Lexema(String valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public void setValor(String valor) {
        //System.out.println("Seteo: valor anterior "+this.valor+" nuevo valor "+valor);
        this.valor = valor;
    }
            
    public boolean isVariable(){
        return this.isVariable;
    }
    
    public void setIsVariable(String nombreVariable){
        //System.out.println("Seteo lexema a varible de "+nombreVariable);
        this.nombreVariable = nombreVariable;
        this.isVariable = true;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }
    
    public String toString(){
        return valor;
    }
    public int getInt(){
        return Integer.parseInt(valor);
    }

    public String getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }
    public void convertirChar_Int(){
        if(this.tipo.equals("CARACTER")){            
            int aux = this.valor.charAt(1);
            this.valor = Integer.toString(aux);
        }
    }    
}
