package auxReporte;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import reportes.ManejadorReportes;
import reportes.Reporte;
@WebServlet("/acierto")
public class Acierto extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        procesar(request, response);
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        procesar(request, response);
    }
    private void procesar(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
        System.out.println("Entro a fallas");
        String id = req.getParameter("ID").replaceAll(".jsp", "");
        String link = req.getParameter("LINK").replaceAll(".jsp", "");
        ManejadorReportes.setReporte("ACIERTO", id);
        Reporte reporte = ManejadorReportes.parserEntrada(id);
        HttpSession s = req.getSession();
        s.setAttribute("ID", id);        
        String info = "Fallas totales: "+reporte.getFallas()+" Aciertos totales: "+reporte.getAciertos()+" Usos totales: "+reporte.getUsos();
        String ruta = req.getContextPath()+"/Captchas/Captchas/"+id+".jsp?ID="+id;        
        s.setAttribute("INFO", info);                
        res.sendRedirect(link);
    }
}

