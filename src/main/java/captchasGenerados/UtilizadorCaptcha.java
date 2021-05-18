package captchasGenerados;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import reportes.ManejadorReportes;
import reportes.Reporte;

@WebServlet("/redireccionar")
public class UtilizadorCaptcha extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        procesar(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        procesar(req, res);
    }

    private void procesar(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("ID").replaceAll(".jsp", "");
        ManejadorReportes.setReporte("USO", id);
        Reporte reporte = ManejadorReportes.parserEntrada(id);
        HttpSession s = req.getSession();
        s.setAttribute("ID", id);
        //System.out.println(req.getServletPath() + "/" + "Captchas/" + id + ".jsp");
        String info = "Fallas totales: "+reporte.getFallas()+" Aciertos totales: "+reporte.getAciertos()+" Usos totales: "+reporte.getUsos();
        String ruta = req.getContextPath()+"/Captchas/Captchas/"+id+".jsp?ID="+id;        
        s.setAttribute("INFO", info);        
        res.sendRedirect(ruta);
    }
}
