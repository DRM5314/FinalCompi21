package generadorHtml;

import cic.ManejadorEstructura;
import cic.Parametros;
import error.ErroresPeticion;
import java.util.List;
import parserScript.ParserScript;
import reportes.ManejadorReportes;
import scripting.Bloque;
import scripting.Manejador;
import variable.Variable;

public class GeneradorHtml extends FuncionesPredefinidas {

    private ManejadorEstructura manejadorEstructura;
    private Manejador manejadorScript;
    private List<Variable> variablesGlobales;
    private List<Bloque> bloquesSript;
    private boolean existeErro = false;

    public GeneradorHtml(ManejadorEstructura manejadorEstructura, Manejador manejador) {
        this.manejadorEstructura = manejadorEstructura;
        this.variablesGlobales = manejador.getVariablesGlobales();
        this.bloquesSript = manejador.getBloquesScript();
        this.manejadorScript = manejador;
    }

    public ErroresPeticion getErrores() {
        return this.manejadorEstructura.getErrores();
    }

    public String parser() {
        StringBuilder acoplador = new StringBuilder();
        acoplador.append(inicoHtml());
        acoplador.append(inicioHead_titulo_finHead(manejadorEstructura.getName()));
        i = manejadorEstructura.getTipoEtiqueta().indexOf("C_BODY") + 1;
        acoplador.append("<body onclose=\"recarga()\">\n");
        acoplador.append("<div class=\"alert alert-light\" role=\"alert\" style=\"text-align: center;\">\n"
                + "		${INFO}\n"
                + "	  </div>\n");
        acoplador.append(etiquetas(""));
        acoplador.append(botonesCrearOcultarTabla());
        acoplador.append(cerrador("body"));
        acoplador.append(generarOnloads());
        acoplador.append(cerrador("html"));
        //System.out.println("La salida html sera \n" + acoplador.toString());
        ManejadorReportes.escribirInicial(this.manejadorEstructura.getId());
        return acoplador.toString();
    }

    private String generarOnloads() {
        String aux = "<div id=\"tabla\"></div>\n\n";
        aux += "<script>\n";
        for (int k = 0; k < this.manejadorScript.getContador_Onload(); k++) {
            aux += "ON_LOAD" + (k + 1) + "();\n";
        }
        aux += funcionesTabla();        
        aux += "</script>\n";
        return aux;
    }

    private String etiquetas(String aux) {

        if (manejadorEstructura.getTipoEtiqueta().get(i).equals("/C_BODY")) {
            return aux;
        } else {
            return etiquetas(aux + agregarEtiqueta());
        }
    }

    private String inicoHtml() {
        return "<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>\n"
                + "<%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n"
                + "<html>\n";

    }

    //el titulo en este caso sera el name que viene de entrada
    private String inicioHead_titulo_finHead(String titulo) {
        return "\t<head>\n"
                + "\t\t<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6\" crossorigin=\"anonymous\">\n"
                + "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "\t\t<title>" + titulo + "</title>\n"
                + "\t</head>\n";
    }
    //agrega etiquetas, las cuales no son div
    private int i = 0;
    private int j = 0;

    private String agregarEtiqueta() {

        String tipo = manejadorEstructura.getTipoEtiqueta().get(i);
        String aux = "";
        Parametros auxParametros = manejadorEstructura.getParametros().get(i);
        switch (tipo) {
            case "C_SPAM":
                aux += "<samp "
                        + convertirParametros_ID_FONTS_COLOR(manejadorEstructura.getParametros().get(i), false)
                        + ">\n";
                aux += manejadorEstructura.getTexto().get(i);
                aux += cerrador("samp");
                break;
            case "C_INPUT":
                aux += "<input "
                        + "type=\"text\" "
                        + convertirParametros_ID_FONTS_COLOR(manejadorEstructura.getParametros().get(i), false)
                        + ">\n";
                break;
            case "C_TEXTAREA":
                aux += "<textarea "
                        + convertidorGenerico("id", auxParametros)
                        + convertidorGenerico("cols", auxParametros)
                        + convertidorGenerico("rows", auxParametros)
                        + "style =\"" + convertirParametrosFonts(auxParametros) + "\""
                        + ">\n";
                aux += manejadorEstructura.getTexto().get(i);
                aux += cerrador("textarea");
                break;
            case "C_SELECT":
                aux += "<select "
                        + convertirParametros_ID_FONTS_COLOR(manejadorEstructura.getParametros().get(i), false)
                        + ">\n";
                while (!manejadorEstructura.getTipoEtiqueta().get(i).equalsIgnoreCase("/C_SELECT")) {
                    i++;
                    if (!manejadorEstructura.getTipoEtiqueta().get(i).equalsIgnoreCase("/C_SELECT")) {
                        aux += "<option> "
                                + manejadorEstructura.getTexto().get(i)
                                + cerrador("option");
                    }
                }
                aux += cerrador("select");
                break;
            case "C_DIV":
                String back = manejadorEstructura.getParametros().get(i).buscarInfo("BACKGROUND");
                i++;
                aux += agrupadorDiv("<div style=\"background: " + back + "\">\n");
                aux += "\t" + cerrador("div");
                break;
            case "C_IMG":
                aux += "<img "
                        + convertidorGenerico("id", auxParametros)
                        + convertidorGenerico("SRC", auxParametros)
                        + convertidorGenerico("WIDTH", auxParametros)
                        + convertidorGenerico("HEIGHT", auxParametros)
                        + convertidorGenerico("ALT", auxParametros)
                        + ">\n";
                break;
            case "C_BR":
                aux += "<br>";
                break;
            case "C_BUTTON":
                aux += "<button "
                        + convertirParametros_ID_FONTS_COLOR(auxParametros, true);
                aux += verificadorFunciones(auxParametros, manejadorEstructura.getPosxy().get(i));
                aux += ">\n";
                aux += manejadorEstructura.getTexto().get(i);
                aux += cerrador("button");

                break;
            case "C_H1":
                aux += "<h1 "
                        + convertirParametros_ID_FONTS_COLOR(manejadorEstructura.getParametros().get(i), false)
                        + ">\n";
                aux += manejadorEstructura.getTexto().get(i);
                aux += cerrador("h1");
                break;
            case "C_P":
                aux += "<p "
                        + convertirParametros_ID_FONTS_COLOR(manejadorEstructura.getParametros().get(i), false)
                        + convertidorGenerico("ONCLICK", auxParametros)
                        + ">\n";
                aux += manejadorEstructura.getTexto().get(i);
                aux += cerrador("p");
                break;
            case "C_SCRIPTING":
                aux += "<script>\n";
                aux += paseadorBloque();
                aux += "</script>\n";
                while (!manejadorEstructura.getTipoEtiqueta().get(i).equalsIgnoreCase("/C_SCRIPTING")) {
                    i++;
                }
                break;
            case "/C_DIV":
                return "/C_DIV";
            case "/C_BODY":
                return "/C_BODY";
        }
        i++;
        return "\t" + aux;
    }

    private String verificadorFunciones(Parametros parametros, int[] posxy) {
        String proceso = parametros.buscarInfo("ONCLICK");
        boolean aux = true;
        String salida = "";
        if (!proceso.equals("crear()")) {
            aux = manejadorScript.existeFuncion(proceso.replace("()", ""));
            if (!aux) {
                this.manejadorEstructura.agregarError("SEMANTICO", "No existe nombre de funcion", posxy);
            } else {
                salida = convertidorGenerico("ONCLICK", parametros);
            }
        }
        return salida;
    }

    private String paseadorBloque() {
        String aux = "\n";
        String link = manejadorEstructura.getLink();
        if(link == null)link = "https://www.google.com/";
        String id = manejadorEstructura.getId();
        if (j == 0) {
            aux += ordenarCadena() + 
                    ordenarReverso() + 
                    letrasPares() + 
                    letrasImpares() + 
                    invertir() + 
                    caracterAleatorio() + 
                    numeroAleatorio() +
                    funcionRecargar(id) + 
                    funcionRedirect(link,id) +
                    funcion_Exit(id); 
            aux
                    += "function declaracionVariable(idEntrada,tipo,variable,contexto){\n"
                    + "		if(variables.find(e => e.id == idEntrada && e.contexto == contexto) ==null){\n"
                    + "			variables.push({id:idEntrada,tipo:tipo,valor:variable,contexto:contexto});\n"
                    + "		}else{\n"
                    + "			variables.find(e => e.id == idEntrada && e.contexto == contexto).valor = variable;\n"
                    + "		}\n"
                    + "	}\n"
                    + "	function asignacionVariable (idEntrada,variable,contexto){\n"
                    + "		variables.find(e => e.id == idEntrada && e.contexto == contexto).valor = variable;\n"
                    + "	}\n";
            aux += " var variables = [];\n";
            for (Variable var : variablesGlobales) {
                aux += "let " + var.getId();
                if (var.getValor() != null) {
                    aux += " = " + var.getValor();
                }
                aux += ";\nvariables.push({id:\'" + var.getId() + "\',tipo:\'" + var.getTipoVariable() + "\',valor:" + var.getId() + ",contexto:\'" + var.getProcedimiento() + "\'});\n";
            }
        }
        ParserScript parser = new ParserScript(this.bloquesSript.get(j));
        j++;
        return aux + parser.parse();
    }

    private String agrupadorDiv(String textoEntrada) {
        if (manejadorEstructura.getTipoEtiqueta().get(i).equals("/C_DIV")) {
            return textoEntrada;
        } else {
            return agrupadorDiv(textoEntrada + "\t" + agregarEtiqueta());
        }
    }

    private String cerrador(String tipo) {
        return "</" + tipo + ">\n";
    }

    private String convertidorGenerico(String tipo, Parametros entrada) {
        return tipo.toLowerCase() + " =\"" + entrada.buscarInfo(tipo.toUpperCase()) + "\"";
    }

    private String convertirParametrosFonts(Parametros parametrosEntrada) {
        String retorno = "";
        retorno
                += "font-size:" + parametrosEntrada.buscarInfo("FONT_SIZE") + ";"
                + "font-family:" + parametrosEntrada.buscarInfo("FONT_FAMILY") + ";"
                + "text-align:" + parametrosEntrada.buscarInfo("TEXT_ALIGN") + ";";
        return retorno;
    }

    private String convertirParametros_ID_FONTS_COLOR(Parametros parametrosEntrada, boolean backround) {
        String retorno = " id = \"" + parametrosEntrada.buscarInfo("ID") + "\""
                + " style = \""
                + "color :" + parametrosEntrada.buscarInfo("COLOR") + ";";
        if (backround) {
            retorno += " background :" + parametrosEntrada.buscarInfo("BACKGROUND") + ";";
        }
        retorno
                += convertirParametrosFonts(parametrosEntrada)
                + "\"";
        return retorno;
    }
}
