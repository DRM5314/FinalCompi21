package generadorHtml;

public class FuncionesPredefinidas {

    protected static String caracterAleatorio() {
        return "function CARACTER_ALEATORIO(len, charSet) {\n"
                + "    charSet = charSet || 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';\n"
                + "    var CARACTER_ALEATORIO = '';\n"
                + "    for (var i = 0; i < len; i++) {\n"
                + "        var randomPoz = Math.floor(Math.random() * charSet.length);\n"
                + "        CARACTER_ALEATORIO += charSet.substring(randomPoz,randomPoz+1);\n"
                + "    }\n"
                + "    return CARACTER_ALEATORIO;\n"
                + "}\n";
    }

    protected static String numeroAleatorio() {
        return "function NUM_ALEATORIO(){\n"
                + "return Math.floor(Math.random() * 1000)"
                + "}\n";
    }

    protected static String ordenarCadena() {
        return "function ASC(str) { \n"
                + "    var arraySplit = str.split(\"\");\n"
                + "    var arraySort = arraySplit.sort();\n"
                + "    var arrayJoin = arraySplit.join(\"\");\n"
                + "        return arrayJoin;\n"
                + "}\n";
    }

    protected static String ordenarReverso() {
        return "function DESC(str) { \n"
                + "    var arraySplit = str.split(\"\");\n"
                + "    var arraySort = arraySplit.sort();\n"
                + "	var reverse = arraySort.reverse();\n"
                + "    var arrayJoin = arraySplit.join(\"\");	\n"
                + "        return arrayJoin;\n"
                + "}\n";
    }

    protected static String invertir() {
        return "function REVERSE(str) { \n"
                + "    var arraySplit = str.split(\"\");\n"
                + "    var arraySort = arraySplit.reverse();\n"
                + "    var arrayJoin = arraySplit.join(\"\");	\n"
                + "        return arrayJoin;\n"
                + "}\n";
    }

    protected static String letrasPares() {
        return "function LETPAR_NUM(str) { \n"
                + "    var arraySplit = str.split(\"\");\n"
                + "    var arreglo = [];\n"
                + "	for (let i = 0; i < arraySplit.length; i++) {\n"
                + "		if((i+1)%2==0){\n"
                + "			arreglo.push(arraySplit[i]);\n"
                + "		}\n"
                + "	}\n"
                + "    var arrayJoin = arreglo.join(\"\");	\n"
                + "        return arrayJoin;\n"
                + "}\n";
    }

    protected static String letrasImpares() {
        return "function LETIMPAR_NUM(str) { \n"
                + "    var arraySplit = str.split(\"\");\n"
                + "    var arreglo = [];\n"
                + "	for (let i = 0; i < arraySplit.length; i++) {\n"
                + "		if((i+2)%2==0){\n"
                + "			arreglo.push(arraySplit[i]);\n"
                + "		}\n"
                + "	}\n"
                + "    var arrayJoin = arreglo.join(\"\");	\n"
                + "        return arrayJoin;\n"
                + "}\n";
    }

    protected static String funcionesTabla() {
        return "function crear(){    \n"
                + "    var tabla=\"<table class=\\\"table table-bordered table-dark\\\">\";\n"
                + "    \n"
                + "    tabla+=\"<tr><th>ID</th><th>TIPO</th><th>VALOR</th><th>CONTEXTO</th>\";\n"
                + "    variables.forEach(a => {\n"
                + "        tabla+=\"<tr><td>\"+a.id+\"</td>\";\n"
                + "			if(a.tipo !=null){\n"
                + "				tabla += \"<td>\"+a.tipo+\"</td>\";\n"
                + "			}else{\n"
                + "				tabla += \"<td>----</td>\";\n"
                + "			}\n"
                + "			tabla+=\"<td>\"+a.valor+\"</td><td>\"+a.contexto+\"</td>\";\n"
                + "    });\n"
                + "    tabla+=\"</table>\";\n"
                + "    document.getElementById(\"tabla\").innerHTML=tabla;\n"
                + "}\n"
                + "function crear2() {\n"
                + "    document.getElementById(\"tabla\").innerHTML=\"\";\n"
                + "}\n"
                + "crear();\n";
    }

    protected static String funcionRecargar(String id) {
        return " function recarga(){\n"
                + "let id = \"" + id + "\";\n"
                + "console.log(\"va a redirigir\");\n"
                + "                    window.location.replace(\"http://localhost:8080/mavenproject1/recargar?ID=\"+id);\n"
                + "                }\n";
    }

    protected static String funcionRedirect(String link, String id) {
        return " function exito(){\n"
                + "let id = \"" + id + "\";\n"
                + "let link = \"" + link + "\";\n"
                + "console.log(\"va a redirigir\");\n"
                + "window.location.replace(\"http://localhost:8080/mavenproject1/acierto?ID=\"+id+\"&LINK=\"+link);\n"
                + "                }\n";
    }

    protected static String funcion_Exit(String id) {
        return "                    function falla(){\n"
                + "                     let id = \"" + id + "\";\n"
                + "                     console.log(\"va a redirigir\");\n"
                + "                    window.location.replace(\"http://localhost:8080/mavenproject1/fallas?ID=\"+id);\n"
                + "                }\n";
    }

    protected static String botonesCrearOcultarTabla() {
        return "<br>\n"
                + "<br>\n"
                + "<div class=\"container\">\n"                
                + "     <div class=\"row justify-content-center\">\n"
                + "         <div class=\"col-4\">\n"
                + "             <button type=\"button\" class=\"btn btn-success\" onclick=\"crear()\">Ver tabla</button>\n"
                + "             <button type=\"button\" class=\"btn btn-warning\" onclick=\"crear2()\">OcultarTabla</button>\n"
                + "         </div>\n"
                + "     </div>\n"
                + " </div>\n";                
    }
}
