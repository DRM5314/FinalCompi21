package analizador;
//package pruebasproyecto2;
import java_cup.runtime.*;
import lexema.Lexema;
import cic.ManejadorEstructura;
%%
%class lexico
%line
%column
%standalone

%cup

%{
private ManejadorEstructura manejadorEtiquetas;
public void entradaManejadorEstructura(ManejadorEstructura entrada){
	//Reportes entrada
	//this.reporte = entrada;
	this.manejadorEtiquetas = entrada;
}
private Symbol symbol (int tipo){
    return new Symbol (tipo,yyline,yycolumn);
}   
private Symbol symbol (int tipo, Object value){
    return new Symbol (tipo,yyline,yycolumn,value);
}

private Symbol symbol (int tipo, Lexema lex){
    return new Symbol (tipo,yyline,yycolumn,lex);
}

%}
opcionesComillasDobles = "\""|"“"|"”"
opcionesComillasSimples = "'"|"‘"|"’"


terminaLinea = \r|\n|\r\n
espacioBlanco = {terminaLinea} | [ \t\f]

idParametro = (_|-|\$)?([a-zA-Z]|[1-9]|(_|-|\$))+
idVariables = [a-zA-Z]([a-zA-Z]|[0-9]|"_")*

digito = [0-9]

IC = [Cc]_
GCIC = {IC}[Gg][Cc][Ii][Cc]
HEAD = {IC}[Hh][Ee][Aa][Dd]
TITLE = {IC}[Tt][Ii][Tt][Ll][Ee]
LINK = {IC} [Ll][Ii][Nn][Kk]
BODY = {IC} [Bb][Oo][Dd][Yy]
SPAM = {IC} [Ss][Pp][Aa][Mm]
INPUT = {IC} [Ii][Nn][Pp][Uu][Tt]
TEXTAREA = {IC} [Tt][Ee][Xx][Tt][Aa][Rr][Ee][Aa]
SELECT = {IC} [Ss][Ee][Ll][Ee][Cc][Tt]
OPTION = {IC} [Oo][Pp][Tt][Ii][Oo][Nn]
DIV = {IC} [Dd][Ii][Vv]
IMG = {IC} [Ii][Mm][Gg]
BR = {IC} [Bb][Rr]
BUTTON = {IC} [Bb][Uu][Tt][Tt][Oo][Nn]
H1 = {IC} [Hh]"1"
P = {IC} [Pp]
SCRIPTING = {IC} [Ss][Cc][Rr][Ii][Pp][Tt][Ii][Nn][Gg]

TIPOS_DATOS = "integer"|"decimal"|"boolean"|"char"|"string"
ENTERO = {digito}+
ENTERO_SCRIPTING = ([1-9]{digito}*){0,9}|0
DECIMAL = (0?|([1-9]0?)+)"."(0*[1-9]0*){1,4}
BOOLEAN = true|false
CARACTER = {opcionesComillasSimples}.{opcionesComillasSimples}
STRING = {opcionesComillasDobles}[^"\"""“""”"]*{opcionesComillasDobles}

LINK_PARAMETRO = {opcionesComillasDobles}(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?{opcionesComillasDobles}
COLOR1 = {opcionesComillasDobles}#([a-fA-F0-9]{6}|[a-fA-F0-9]{3}){opcionesComillasDobles}
COLOR2 = {opcionesComillasDobles}(black| olive| teal| red| blue| maroon| navy| gray| lime| fuchsia| green| white| green| purple| silver| yellow| aqua){opcionesComillasDobles}

SIZE_PARAMETRO = [0-9]{1,4}"px"{opcionesComillasDobles}
SIZE_PARAMETRO_IMAGEN = {opcionesComillasDobles}[0-9]{1,4}"%"{opcionesComillasDobles}
FAMILY_PARAMETRO = {opcionesComillasDobles}(Courier|Verdana |Arial| Geneva| sans-serif){opcionesComillasDobles}
ALING_PARAMETRO = {opcionesComillasDobles}(left | right | center | justify){opcionesComillasDobles}
TYPE_PARAMETRO = {opcionesComillasDobles}(text | number | radio | checkbox){opcionesComillasDobles}

TEXTOS_ETIQUETAS = "</"{TITLE}">"
	| "</"{SPAM}">" 
	| "</"{TEXTAREA}">" 
	| "</"{OPTION}">"
	| "</"{BUTTON}">" 
	| "</"{H1}">" 
	| "</"{P}">" 

%state textoEtiqueta,SCRIPTING

%%
<YYINITIAL> {

	{espacioBlanco} {
	}

	\< { 
	////System.out.println("Detecto MENOR_QUE");
	return symbol(sym.MENOR_QUE);
	}

	\> {
	////System.out.println("Detecto MAYOR_QUE");  
	return symbol(sym.MAYOR_QUE);
	}

	\[ {
	////System.out.println("Detecto CORCHETE_ABIERTO");  
	return symbol(sym.CORCHETE_ABIERTO);
	}

	\] {
	////System.out.println("Detecto CORCHETE_CERRADO");  
	return symbol(sym.CORCHETE_CERRADO);
	}

	\( {
	System.out.println("Detecto PARENTESIS_ABIERTO");  
	return symbol(sym.PARENTESIS_ABIERTO);
	}

	\) {
	System.out.println("Detecto PARENTESIS_CERRADO");  
	return symbol(sym.PARENTESIS_CERRADO);
	}

	\, {
		////System.out.println("Detecto COMA");  
		return symbol(sym.COMA);
	}

	\= {
		////System.out.println("Detecto IGUAL");  
		return symbol(sym.IGUAL);
	}

	\/ {
		////System.out.println("Detecto DIAGONAL");
		return symbol(sym.DIAGONAL);
		}

	{GCIC} {
	////System.out.println("Detecto C_GCIC");  
	return symbol(sym.C_GCIC);	
	}

	{HEAD} {
	////System.out.println("Detecto C_HEAD");  
	return symbol(sym.C_HEAD);	
	}

	{TITLE} {
	////System.out.println("Detecto C_TITLE");  
	return symbol(sym.C_TITLE);	
	}

	{LINK} {
	////System.out.println("Detecto C_LINK");  
	return symbol(sym.C_LINK);	
	}

	{LINK_PARAMETRO} {
	System.out.println("Detecto LINK_PARAMETRO:"+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));  
	return symbol(sym.LINK_PARAMETRO,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
	}

	{BODY} {
	////System.out.println("Detecto C_BODY");  
	return symbol(sym.C_BODY);	
	}

	{SPAM} {
	////System.out.println("Detecto C_SPAM");  
	return symbol(sym.C_SPAM);	
	}

	{INPUT} {
	////System.out.println("Detecto C_INPUT");  
	return symbol(sym.C_INPUT);	
	}

	{TEXTAREA} {
	////System.out.println("Detecto C_TEXTAREA");  
	return symbol(sym.C_TEXTAREA);	
	}

	{SELECT} {
	////System.out.println("Detecto C_SELECT");  
	return symbol(sym.C_SELECT);	
	}

	{OPTION} {
	////System.out.println("Detecto C_OPTION");  
	return symbol(sym.C_OPTION);	
	}

	{DIV} {
	////System.out.println("Detecto C_DIV");  
	return symbol(sym.C_DIV);	
	}

	{IMG} {
	////System.out.println("Detecto C_IMG");  
	return symbol(sym.C_IMG);	
	}

	{BR} {
	////System.out.println("Detecto C_BR");  
	return symbol(sym.C_BR);	
	}

	{BUTTON} {
	////System.out.println("Detecto C_BUTTON");  
	return symbol(sym.C_BUTTON);	
	}

	{H1} {
	////System.out.println("Detecto C_H1");  
	return symbol(sym.C_H1);  
	}

	[^"<>/"]+/{TEXTOS_ETIQUETAS} {
		System.out.println("Detecto TEXTO_ETIQUETA: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		return symbol(sym.TEXTO_ETIQUETA,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
	}

	{P} {
	////System.out.println("Detecto C_P");  
	return symbol(sym.C_P);	
	}

	<YYINITIAL>{SCRIPTING} {
		System.out.println("		Detecto C_SCRIPTING entrada");
		//System.out.println("Va a entrar a estado scripting");
		yybegin(SCRIPTING);
		return symbol(sym.C_SCRIPTING);	
	}

	"id" {
	////System.out.println("Detecto id");  
	return symbol(sym.ID);	
	}	

	"name" {
	////System.out.println("Detecto name");  
	return symbol(sym.NAME);	
	}

	"background" {
	////System.out.println("Detecto background");  
	return symbol(sym.BACKGROUND);	
	} 

	"color" {
	////System.out.println("Detecto color");  
	return symbol(sym.COLOR);	
	}

	{COLOR1}|{COLOR2} {
		////System.out.println("Detecto COLOR_PARAMETRO: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));  
		return symbol(sym.COLOR_PARAMETRO,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
	}

	"font-size" {
	////System.out.println("Detecto font-size");  
	return symbol(sym.FONT_SIZE);	
	}

	{SIZE_PARAMETRO} {
		////System.out.println("Detecto SIZE_PARAMETRO: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));  
		return symbol(sym.SIZE_PARAMETRO,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
	}

	"font-family" {
	////System.out.println("Detecto font-family");  
	return symbol(sym.FONT_FAMILY);	
	}

	{FAMILY_PARAMETRO} {
	////System.out.println("Detecto FAMILY_PARAMETRO");  
	return symbol(sym.FAMILY_PARAMETRO,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
	}

	"text-align" {
	////System.out.println("Detecto text-align");  
	return symbol(sym.TEXT_ALIGN);	
	}

	{ALING_PARAMETRO} {
	////System.out.println("Detecto ALING_PARAMETRO");  
	return symbol(sym.ALING_PARAMETRO,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));	
	}

	"type" {
	////System.out.println("Detecto type");  
	return symbol(sym.TYPE);	
	}

	{TYPE_PARAMETRO} {
	////System.out.println("Detecto TYPE_PARAMETRO");  
	return symbol(sym.TYPE_PARAMETRO,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));	
	}

	"cols" {
	////System.out.println("Detecto cols");  
	return symbol(sym.COLS);	
	}

	"rows" {
	////System.out.println("Detecto rows");  
	return symbol(sym.ROWS);	
	}

	"class" {
	////System.out.println("Detecto class");  
	return symbol(sym.CLASS);	
	}

	"src" {
	////System.out.println("Detecto src");  
	return symbol(sym.SRC);	
	}

	"width" {
	////System.out.println("Detecto width");  
	return symbol(sym.WIDTH);	
	}

	"height" {
	////System.out.println("Detecto height");  
	return symbol(sym.HEIGHT);	
	}

	{ENTERO} {
		////System.out.println("Detecto ENTERO");  
		return symbol(sym.ENTERO,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
	}

	{SIZE_PARAMETRO_IMAGEN} {
		////System.out.println("Detecto SIZE_PARAMETRO_IMAGEN");  
		return symbol(sym.SIZE_PARAMETRO_IMAGEN,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
	}

	"alt" {
	////System.out.println("Detecto alt");  
	return symbol(sym.ALT);	
	}

	"onclick" {
	////System.out.println("Detecto onclick");  
	return symbol(sym.ONCLICK);	
	}

	"href" {
	////System.out.println("Detecto href");  
	return symbol(sym.HREF);	
	}

	{opcionesComillasDobles}"PROCESS_"[^\n"(){}:;"]+"()"{opcionesComillasDobles} {	
		//System.out.println("Detecto NOMBRE_FUNCION_PARAMETRO : "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		return symbol(sym.NOMBRE_FUNCION_PARAMETRO,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
	}	

	{opcionesComillasDobles}{idParametro}{opcionesComillasDobles} {
		//System.out.println("Detecto ID_PARAMETRO:"+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));  
		return symbol(sym.ID_PARAMETRO,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
	}
	
	{STRING} {
		//System.out.println("Detecto STRING: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		return symbol(sym.STRING_PARAMETRO,new Lexema(yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""),"STRING"));
	}	

	"<!--"[^*"/"]*"-->" { 
		////System.out.println("Comentario de bloque detectado");
	}

	"!!"[^\n]* { 
		////System.out.println("Comentario de linea detectado "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", "")); 
	}

	<SCRIPTING>{

		{espacioBlanco} {
		}

		"<!--"[^*"/"]*"-->" { 
		////System.out.println("Comentario de bloque detectado");
		}

		"!!"[^\n]* { 
			////System.out.println("Comentario de linea detectado "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", "")); 
		}

		\, {
		////System.out.println("Detecto COMA");  
		return symbol(sym.COMA);
		}

		\< { 
		////System.out.println("Detecto MENOR_QUE");
		return symbol(sym.MENOR_QUE);
		}

		\> {
		////System.out.println("Detecto MAYOR_QUE");  
		return symbol(sym.MAYOR_QUE);
		}
		
		"==" {
		////System.out.println("Detecto igual_igual");  
		return symbol(sym.IGUAL_IGUAL);
		}
		
		"!=" {
		////System.out.println("Detecto DIFERENTE");  
		return symbol(sym.DIFERENTE);
		}
		
		"<=" {
		////System.out.println("Detecto MENOR_IGUAL");  
		return symbol(sym.MENOR_IGUAL);
		}
		
		">=" {
		////System.out.println("Detecto MAYOR_IGUAL");  
		return symbol(sym.MAYOR_IGUAL);
		}

		"&&" {
		////System.out.println("Detecto OPERADOR_Y");
		return symbol(sym.OPERADOR_Y);
		}

		"||" {
		////System.out.println("Detecto OPERADOR_O");
		return symbol(sym.OPERADOR_O);
		}

		"!" {
		////System.out.println("Detecto Not");
		return symbol(sym.NOT);
		}

		"!" {
		////System.out.println("Detecto Not");
		return symbol(sym.NOT);
		}

		\[ {
		//System.out.println("Detecto LLAVECORCHETE_ABIERTO_ABIERTA  en estado script");
		return symbol(sym.CORCHETE_ABIERTO);
		}

		\] {
		//System.out.println("Detecto CORCHETE_CERRADO  en estado script");
		return symbol(sym.CORCHETE_CERRADO);
		}

		\{ {
		//System.out.println("Detecto LLAVE_ABIERTA  en estado script");
		return symbol(sym.LLAVE_ABIERTA);
		}

		\} {
		//System.out.println("Detecto LLAVE_CERRADA  en estado script");
		return symbol(sym.LLAVE_CERRADA);
		}

		\( {
		//System.out.println("Detecto PARENTESIS_ABIERTO  en estado script");
		return symbol(sym.PARENTESIS_ABIERTO);
		}

		\) {
		//System.out.println("Detecto PARENTESIS_CERRADO  en estado script");  
		return symbol(sym.PARENTESIS_CERRADO);
		}

		\= {
		//System.out.println("Detecto IGUAL  en estado script");  
		return symbol(sym.IGUAL);
		}

		"/" {
		//System.out.println("Detecto DIAGONAL  en estado script");
		return symbol(sym.DIAGONAL);
		}

		\* {
			//System.out.println("Detecto MULTIPLICACION en estado script");
			return symbol(sym.MULTIPLICACION);
		}

		\+ {
			//System.out.println("Detecto SUMA en estado script");
			return symbol(sym.SUMA);
		}

		"-" {
			System.out.println("Detecto RESTA en estado script "+yytext());
			return symbol(sym.RESTA);
		}

		\; {
			//System.out.println("Detecto PUNTO_COMA en estado script");
			return symbol(sym.PUNTO_COMA);
		}

		\: {
			//System.out.println("Detecto DOS_PUNTOS en estado script");
			return symbol(sym.DOS_PUNTOS);
		}

		"@global" {
			//System.out.println("Detecto global en estado script");
			return symbol(sym.GLOBAL );
		}

		{TIPOS_DATOS} {
		////System.out.println("Detecto tipoDato: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		return symbol(sym.TIPOS_DATOS,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", "").toUpperCase());
		}
		
		"PROCESS_"[^\n"()"]+ {	
		System.out.println("Detecto NOMBRE_FUNCION : "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		return symbol(sym.NOMBRE_FUNCION,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		}

		"ON_LOAD" {
		////System.out.println("Detecto ON_LOAD");  
		return symbol(sym.ON_LOAD);	
		}

		"ASC" {
		////System.out.println("Detecto ASC");  
		return symbol(sym.ASC);	
		}

		"DESC" {
		////System.out.println("Detecto DESC");  
		return symbol(sym.DESC);	
		}

		"LETPAR_NUM" {
		////System.out.println("Detecto LETPAR_NUM");  
		return symbol(sym.LETPAR_NUM);	
		}

		"LETIMPAR_NUM" {
		////System.out.println("Detecto LETIMPAR_NUM");  
		return symbol(sym.LETIMPAR_NUM);	
		}

		"REVERSE" {
		////System.out.println("Detecto REVERSE");  
		return symbol(sym.REVERSE);	
		}

		"CARACTER_ALEATORIO" {
		////System.out.println("Detecto CARACTER_ALEATORIO");  
		return symbol(sym.CARACTER_ALEATORIO);	
		}

		"NUM_ALEATORIO" {
		////System.out.println("Detecto NUM_ALEATORIO");  
		return symbol(sym.NUM_ALEATORIO);	
		}

		"ALERT_INFO" {
		////System.out.println("Detecto ALERT_INFO");  
		return symbol(sym.ALERT_INFO);	
		}

		"EXIT" {
		////System.out.println("Detecto EXIT");  
		return symbol(sym.EXIT);	
		}
		
		"REDIRECT" {
		////System.out.println("Detecto REDIRECT");  
		return symbol(sym.REDIRECT);	
		}

		"getElemenById" {
		System.out.println("Detecto getElementById");  
		return symbol(sym.GET_ELEMENT_BY_ID);	
		}	

		"INIT" {
		////System.out.println("Detecto INIT");  
		return symbol(sym.INIT);	
		}

		"END" {
		////System.out.println("Detecto END");  
		return symbol(sym.END);	
		}

		"IF" {
		////System.out.println("Detecto IF");  
		return symbol(sym.IF);	
		}

		"THEN" {
		//System.out.println("Detecto THEN");  
		return symbol(sym.THEN);	
		}

		"ELSE" {
		////System.out.println("Detecto ELSE");  
		return symbol(sym.ELSE);	
		}

		"REPEAT" {
		////System.out.println("Detecto REPEAT");  
		return symbol(sym.REPEAT);	
		}

		"HUNTIL" {
		////System.out.println("Detecto HUNTIL");  
		return symbol(sym.HUNTIL);	
		}

		"WHILE" {
		////System.out.println("Detecto WHILE");  
		return symbol(sym.WHILE);	
		}

		"THENWHILE" {
		////System.out.println("Detecto THENWHILE");  
		return symbol(sym.THENWHILE);	
		}

		"INSERT" {
		////System.out.println("Detecto INSERT");  
		return symbol(sym.INSERT);	
		}

		{STRING} {
		////System.out.println("Detecto STRING: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		return symbol(sym.STRING_PARAMETRO,new Lexema(yytext().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", "").trim(),"STRING"));
		}

		{DECIMAL} {
			////System.out.println("Detecto DECIMAL: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
			return symbol(sym.DECIMAL_POSITIVO,new Lexema(yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""),"DECIMAL"));
		}

		"-"{DECIMAL} {
			////System.out.println("Detecto DECIMAL: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
			return symbol(sym.DECIMAL_NEGATIVO,new Lexema(yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""),"DECIMAL"));
		}

		{ENTERO_SCRIPTING} {
			//System.out.println("Detecto ENTERO_SCRIPTING: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
			return symbol(sym.INTEGER_POSITIVO,new Lexema(yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""),"INTEGER"));
		}
		
		"-"{ENTERO_SCRIPTING} {
			//System.out.println("Detecto ENTERO_SCRIPTING_NEGATIVO: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
			return symbol(sym.INTEGER_NEGATIVO,new Lexema(yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""),"INTEGER"));
		}

		{BOOLEAN} {
			//System.out.println("Detecto BOOLEAN: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
			return symbol(sym.BOOLEAN,new Lexema(yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""),"BOOLEAN"));
		}

		{CARACTER} {
			////System.out.println("Detecto CHAR: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
			return symbol(sym.CARACTER,new Lexema(yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", "").replaceAll("'", "").replaceAll("‘", "").replaceAll("’", ""),"CHAR"));
		}

		{SCRIPTING} {
			//System.out.println("Detecto C_SCRIPTING salida");
			//System.out.println("Va a regeresar a estado inicial");
			yybegin(YYINITIAL);
			return symbol(sym.C_SCRIPTING);	
		}

		{opcionesComillasSimples}{idParametro}{opcionesComillasSimples} {
		System.out.println("Detecto ID_PARAMETRO element :"+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));  
		return symbol(sym.ID_PARAMETRO_ELEMENT,yytext().trim().replaceAll("'", "").replaceAll("‘", "").replaceAll("’", ""));
		}

		{opcionesComillasSimples}[.]{opcionesComillasSimples} {		
		return symbol(sym.ID_PARAMETRO_INSERT,yytext().trim().replaceAll("'", "").replaceAll("‘", "").replaceAll("’", ""));
		}

		{idVariables} {
			////System.out.println("Detecto CHAR: "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
			return symbol(sym.ID_VARIABLE,yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		}	
		[a-zA-Z]+[0-9]*  {   
		System.out.println("		Detecto error alfanumerico DENTRO "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		manejadorEtiquetas.agregarError("Lexico",yytext(),new int []{(yyline+1),(yycolumn+1)});
	}
	[^] {
		System.out.println("		Detecto otras cosas DENTRO "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		manejadorEtiquetas.agregarError("Lexico",yytext(),new int []{(yyline+1),(yycolumn+1)});
	}
	
	}


	[a-zA-Z]+[0-9]*  {   
		System.out.println("		Detecto error alfanumerico FUERA "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		manejadorEtiquetas.agregarError("Lexico",yytext(),new int []{(yyline+1),(yycolumn+1)});
	}
	[^] {
		System.out.println("		Detecto otras cosas fuera "+yytext().trim().replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
		manejadorEtiquetas.agregarError("Lexico",yytext(),new int []{(yyline+1),(yycolumn+1)});
	}

}