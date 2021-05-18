package reportes;

import archivos.ManejadorArchivos;
import java.io.StringReader;
import static rutas.Rutas.getExtencionReporteCaptcha;
import static rutas.Rutas.getRutaReporteCaptcha;

public class ManejadorReportes extends rutas.Rutas {

    public static Reporte parserEntrada(String nombre) {
        String ruta = getRutaReporteCaptcha() + nombre + getExtencionReporteCaptcha();
        String entrada = ManejadorArchivos.leerArchivo(ruta);
        System.out.println("Nomre "+nombre+" Ruta "+ruta+" contenido "+entrada);
        if (ruta != null) {
            lexico lex = new lexico(new StringReader(entrada));
            parser par = new parser(lex);
            try {
                par.parse();
                Reporte salida = par.getParserReportes();
                salida.setId(nombre);
                return salida;
            } catch (Exception ex) {
                return null;
            }
        }
        return null;
    }

    public static void escribirInicial(String id) {
        String salida = "{\n";
        salida += "FALLAS:0,\n";
        salida += "ACIERTOS:0,\n";
        salida += "USOS:0\n";
        salida += "}";
        String ruta = getRutaReporteCaptcha() + id + getExtencionReporteCaptcha();
        ManejadorArchivos.escribirArchivo(salida, ruta);
    }

    private static void escribirModificado(Reporte entrada) {
        String salida = "{\n";
        salida += "FALLAS:" + entrada.getFallas() + ",\n";
        salida += "ACIERTOS:" + entrada.getAciertos() + ",\n";
        salida += "USOS:" + entrada.getUsos() + "\n";
        salida += "}";
        String ruta = getRutaReporteCaptcha() + entrada.getId() + getExtencionReporteCaptcha();
        ManejadorArchivos.escribirArchivo(salida, ruta);
    }

    public static void setReporte(String tipo, String id) {
        Reporte aux = parserEntrada(id);
        switch (tipo) {
            case "FALLA":
                aux.aumentarFalla();
                break;
            case "ACIERTO":
                aux.aumentarAcierto();
                break;
            case "USO":
                aux.aumentarUso();
                break;
        }
        escribirModificado(aux);
    }
}
