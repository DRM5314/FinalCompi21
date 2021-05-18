package rutas;

public class Rutas {
    private static String rutaCaptchas = "/home/david/Documentos/Cursos/compi1_2021/Tareas/Proyectos/Proyecto2/mavenproject1/src/main/webapp/Captchas/Captchas/";
    private static String rutaReporteCaptcha = "/home/david/Documentos/Cursos/compi1_2021/Tareas/Proyectos/Proyecto2/mavenproject1/src/main/webapp/Captchas/Reportes/";
    private static String extencionCaptcha = ".jsp";
    private static String extencionReporteCaptcha = ".report";
    private static String rutaGuardar = "/home/david/Documentos/Cursos/compi1_2021/Tareas/Proyectos/Proyecto2/mavenproject1/src/main/webapp/Captchas";

    public static String getRutaGuardar() {
        return rutaGuardar;
    }
    
    
    public static String getRutaCaptchas() {
        return rutaCaptchas;
    }

    public static String getRutaReporteCaptcha() {
        return rutaReporteCaptcha;
    }

    public static String getExtencionCaptcha() {
        return extencionCaptcha;
    }

    public static String getExtencionReporteCaptcha() {
        return extencionReporteCaptcha;
    }
    
    
}
