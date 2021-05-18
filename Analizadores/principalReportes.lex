package reportes;
import java_cup.runtime.*;
%%
%class lexico
%line
%column
%cup

%{
private Symbol symbol (int tipo){
    return new Symbol (tipo,yyline,yycolumn);
}   
private Symbol symbol (int tipo, Object value){
    return new Symbol (tipo,yyline,yycolumn,value);
}
%}
terminaLinea = \r|\n|\r\n
digito = [0-9]
espacioBlanco = {terminaLinea} | [ \t\f] | ""
%%
{espacioBlanco} { }
\{ {
//System.out.println("Detecto LLAVE_ABIERTA");  
return symbol(sym.LLAVE_ABIERTA);
}

\} {
//System.out.println("Detecto LLAVE_CERRADA"); 
return symbol(sym.LLAVE_CERRADA);
}
\: { 
//System.out.println("Detecto DOS_PUNTOS");  
return symbol(sym.DOS_PUNTOS);
}
\, {
//System.out.println("Detecto COMA");  
return symbol(sym.COMA);
}

"FALLAS" {   
return symbol(sym.FALLAS); 
}

"ACIERTOS" {   
return symbol(sym.ACIERTOS); 
}

"USOS" {   
return symbol(sym.USOS); 
}

{digito}+ {
	return symbol(sym.NUMERO,Integer.parseInt(yytext()));
}
.
{
//System.out.println("Detecto Error lexico :"+yytext());
} 
