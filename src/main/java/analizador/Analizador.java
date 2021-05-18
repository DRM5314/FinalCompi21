/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import cic.ManejadorEstructura;
import error.ErroresPeticion;
import generadorHtml.GeneradorHtml;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
@WebServlet("/analizar")
public class Analizador extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        procesar(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        procesar(req, res);
    }

    private void procesar(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String entrada = req.getParameter("areaTexto").toString();
            //System.out.println("En la entrada esta "+entrada);
            ManejadorEstructura auxManejador = new ManejadorEstructura();
            generadorHtml.GeneradorHtml html;
            String resultado = "";
            int contadorErrores = 0;
            try {
                lexico lex = new lexico(new StringReader(entrada));
                lex.entradaManejadorEstructura(auxManejador);
                parser parse = new parser(lex, auxManejador);
                parse.parse();
                ErroresPeticion errores = auxManejador.getErrores();
                contadorErrores = errores.getError().size();
            } catch (Exception d) {
                contadorErrores++;
                auxManejador.agregarError("Sin recuperacion", "Sin recuperacion ", new int[]{0, 0});
            }
            if (!auxManejador.existenErrores()) {
                html = new GeneradorHtml(auxManejador, auxManejador.manejadorScript());
                String ruta = auxManejador.getRutaCaptcha();
                String salida = html.parser();
                auxManejador.setErrores(html.getErrores());
                if (!auxManejador.existenErrores()) {
                    archivos.ManejadorArchivos.escribirArchivo(salida, ruta);
                    req.setAttribute("info", "Captcha -" + auxManejador.getId() + "-creado correctamente, se agrego a los otros captchas");
                    req.setAttribute("variables", auxManejador.manejadorScript().getListadoVariablesTotales());
                } else {
                    resultado = "Captcha tiene " + auxManejador.getErrores().getError().size() + " error(es) son los siguientes";
                    req.setAttribute("info", resultado);
                }
            } else {
                if (resultado.isEmpty()) {
                    resultado = "Captcha tiene " + contadorErrores + " error(es) son los siguientes";
                }
                req.setAttribute("info", resultado);
                req.setAttribute("errores", auxManejador.getErrores().getError());
            }
            //auxManejador.manejadorScript().getInfoScript();
            req.setAttribute("codigo", entrada);
            req.getRequestDispatcher("index.jsp").forward(req, res);
        } catch (Exception ex) {
            Logger.getLogger(Analizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
