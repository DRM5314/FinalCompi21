/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import analizador.Analizador;
import analizador.lexico;
import analizador.parser;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class pruebaPlana {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String prueba = 
                "<!-- Mi primer captcha\n" +
"     en el curso de Organización de Lenguajes y Compiladores 1\n" +
" -->\n" +
"\n" +
"<C_GCIC [id= \"captcha_matematico_1\"] [name= \"Captcha Matemático 1\"]>\n" +
"    !! El encabezado de la página que tendrá mi captcha\n" +
"    <C_HEAD>\n" +
"       <C_LINK \n" +
"          !! El link al que redirige mi captcha\n" +
"          [href= “https://www.mclibre.org/consultar/htmlcss/html/html-etiquetas.html”]>\n" +
"       </C_LINK>\n" +
"          !! El título de mi página\n" +
"       <C_TITLE> Mi primer Captcha Matemático</C_TITLE>\n" +
"    </C_HEAD>\n" +
"    !! El cuerpo de la página\n" +
"    <C_BODY [background= \"#e5e6ea\"] >\n" +
"<C_SPAM >\n" +
"           ¿ Qué resultado genera la operación siguiente: 5+5 ?\n" +
"        </C_SPAM>"+
"    </C_BODY>\n" +
"</C_GCIC>\n" +
"!! Fin de estructura GCIC\n";
                ;
        lexico lex = new lexico(new StringReader(prueba));
        parser p = new parser(lex);
        try {
            p.parse();
        } catch (Exception ex) {
            Logger.getLogger(Analizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
