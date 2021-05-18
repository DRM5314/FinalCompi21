package captchasGenerados;

import archivos.ManejadorArchivos;
import cic.ManejadorEstructura;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/captchasGenerados")
public class CaptchasGenerados extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        procesar(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        procesar(req,res);
    }
    private void procesar(HttpServletRequest req, HttpServletResponse res) throws IOException{
        try {            
            List<String> nombresCaptchas = ManejadorArchivos.nombresArchivos(rutas.Rutas.getRutaCaptchas());            
            if(nombresCaptchas.size()>0){
                req.setAttribute("nombres",nombresCaptchas);                
            }else{
                req.setAttribute("sinCaptchas","Aun no se a creado un captcha");
            }
            req.getRequestDispatcher("captchasGenerados.jsp").forward(req, res);
        } catch (ServletException ex) {
            Logger.getLogger(CaptchasGenerados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
