package parserScript;

import scripting.Bloque;

public class ParserScript {
    private Bloque bloque;

    public ParserScript(Bloque bloque) {
        this.bloque = bloque;
    }
    
    public String parse(){        
        return auxParse("");
    }
    
    public String auxParse(String texto){
        if(bloque.getTipo().size()<(i+1)){
            return texto;
        }else{
            return auxParse(texto+agregarLinea());
        }
    }
        
    private int i = 0;
    String contexto = null;
    private String agregarLinea(){
        String tipo = this.bloque.getTipo().get(i);        
        String acoplador = "";
        String aux = "";
        switch(tipo){
            case "INICIO_FUNCION":
                aux = this.bloque.getId().get(i);
                this.contexto = this.bloque.getId().get(i);
                i++;
                acoplador+="function " + aux + "(){\n"+acopladorFuncion("");
                acoplador+="crear();\n}\n";
                break;
            case "INICIO_IF":
                aux = this.bloque.getCondicion().get(i);
                i++;
                acoplador+="if ("+aux+"){\n"+acopladorIf("");
                acoplador+="}\n";
                break;
            case "INICIO_ELSE":                
                i++;
                acoplador+="else{\n"+acopladorElse("");
                acoplador+="}\n";
                break;
            case "INICIO_REPEAT":                
                String inicio = this.bloque.getInicio().get(i);
                aux = this.bloque.getCondicion().get(i);
                String id = this.bloque.getId().get(i);
                i++;                
                acoplador+="for("+id+"="+inicio+" ;"+id+"<="+aux+";"+id+"++){\n"+acopladorRepeat("");
                acoplador+="asignacionVariable('"+id+"\',"+id+",\'"+this.contexto+"\');\n";
                acoplador+="}\n";
                break;
            case "INICIO_WHILE":
                aux = this.bloque.getCondicion().get(i);
                i++;
                acoplador+="while ("+aux+"){\n"+acopladorWhile("");
                acoplador+="}\n";
                break;
            case "ASIGNACION":
                String idAux = this.bloque.getId().get(i); 
                acoplador+= idAux+" = "+bloque.getInicio().get(i)+";\n";
                acoplador+="asignacionVariable('"+idAux+"\',"+idAux+",\'"+this.contexto+"\');\n";
                break;
            case "DECLARACION":
                if(this.bloque.getModo().get(i).equals("NO")){
                    String idAux1 = this.bloque.getId().get(i); 
                    acoplador+="let "+idAux1;
                    if(this.bloque.getInicio()!=null){
                        acoplador+=" = "+this.bloque.getInicio().get(i);
                    }
                    acoplador+=";\n";
                    acoplador+="declaracionVariable('"+idAux1+"',\'"+this.bloque.getTipoVar().get(i)+"\',"+idAux1+",\'"+this.contexto+"\');\n";
                }
                break;  
            case "ALERT_INFO":
                aux = this.bloque.getInicio().get(i);
                acoplador+="alert ("+aux+");\n";                
                break;
            case "EXIT":                
                acoplador+="falla();\n";                
                break;
            case "REDIRECT":                
                acoplador+="exito();\n";                
                break;
        }
        i++;
        return acoplador;
    }
    
    private String acopladorFuncion(String entrada){
        if(this.bloque.getTipo().get(i).equals("FIN_FUNCION")){
            return entrada;
        }else{            
            return acopladorFuncion(entrada+agregarLinea());
        }
    }
    private String acopladorIf(String entrada){
        if(this.bloque.getTipo().get(i).equals("FIN_IF")){
            return entrada;
        }else{            
            return acopladorIf(" "+entrada+agregarLinea());
        }
    }
    private String acopladorElse(String entrada){
        if(this.bloque.getTipo().get(i).equals("FIN_ELSE")){
            return entrada;
        }else{            
            return acopladorElse(" "+entrada+agregarLinea());
        }
    }
    private String acopladorRepeat(String entrada){
        if(this.bloque.getTipo().get(i).equals("FIN_REPEAT")){
            return entrada;
        }else{            
            return acopladorRepeat(" "+entrada+agregarLinea());
        }
    }
    private String acopladorWhile(String entrada){
        if(this.bloque.getTipo().get(i).equals("FIN_WHILE")){
            return entrada;
        }else{            
            return acopladorWhile(" "+entrada+agregarLinea());
        }
    }
}
