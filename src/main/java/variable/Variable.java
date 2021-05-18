package variable;

import lexema.Lexema;

public class Variable {
    private String tipo,id,procedimiento,valor=null;
    private boolean global = false;

    public String getId() {
        return id;
    }

    public Variable(String tipo,String id,String modoEntrada, String valorEntrada,String procedimiento) {
        this.tipo= tipo;
        this.id = id;
        if(modoEntrada.equals("SI")){
            this.global = true;
        }
        if(valorEntrada!=null){
            this.valor = valorEntrada;
        }
        this.procedimiento = procedimiento;
        //System.out.println("Agrego variable "+id+" tipo "+tipo+" global "+global+" inicializacion "+valor+" en procedimiento "+procedimiento);
    }

    public boolean isGlobal() {
        return global;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public lexema.Lexema toLexema(){
        lexema.Lexema retorno = new Lexema(valor, tipo);
        if(id!=null)retorno.setIsVariable(id);
        return retorno;
    }

    public String getTipoVariable(){
        return this.tipo;
    }
    public String getTipo() {
        return procedimiento;
    }

    public String getValor() {
        return valor;
    }    

    public String getProcedimiento() {
        return procedimiento;
    }
    
}
