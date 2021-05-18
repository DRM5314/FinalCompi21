package cic;

import scripting.Manejador;
import error.ErroresPeticion;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import operaciones.ResultadoOperacion;

public class ManejadorEstructura {

    private String id, name, link;
    private List<String> idsEtiquetas = new ArrayList<>();
    /*
    Cada pos en el listado estara relacionado entre si, entre las relaciones 
    pueden haber nulos ya que algunos no usan parametros o texto
    primer parte para manejar etiquetas html
     */    
    private List<String> tipoEtiqueta = new ArrayList<>();
    private List<Parametros> parametros = new ArrayList<>();
    private List<String> texto = new ArrayList<>();
    private List<int[]> posxy = new ArrayList<>();
    //parte para manejar etiquetas y parte de logica de script
    
    
    private ErroresPeticion errores = new ErroresPeticion();
    
    private scripting.Manejador manejadorScript = new Manejador();                  
            
    private ParametrosDefecto parametrosDefecto = new ParametrosDefecto();
    public void agregarEtiqueta(String tipoEtiqueta, List<String> parametros, List<String> datosParametros, String texto, int[] posXY) {
        Parametros aux = parametrosDefecto.manejadorParametros(tipoEtiqueta);
        this.tipoEtiqueta.add(tipoEtiqueta.toUpperCase());
        aux.setParametros(parametros, datosParametros, aux);
        this.parametros.add(aux);
        this.texto.add(texto);
        this.posxy.add(posXY);
    }

    public List<int[]> getPosxy() {
        return posxy;
    }

    public ErroresPeticion getErrores() {
        return errores;
    }
    
    

    public void setErrores(ErroresPeticion entrada) {
        errores = entrada;
    }        
    
    public scripting.Manejador manejadorScript(){
        return this.manejadorScript;
    }
    
    public String getElementById(String id){
        for (int i = 0; i < parametros.size(); i++) {
            if(parametros.get(i).isThis(id)){
                return this.texto.get(i);
            }
        }
        return null;
    }
    
    public boolean existeCaptcha(String nombre){        
        String aux = rutas.Rutas.getRutaCaptchas()+nombre+rutas.Rutas.getExtencionCaptcha();
        //System.out.println("Verificara captcha de "+aux+"   "+archivos.ManejadorArchivos.existeArchivo(aux));
        return archivos.ManejadorArchivos.existeArchivo(aux);
    }
    
    public String getRutaCaptcha(){
        return rutas.Rutas.getRutaCaptchas()+this.id+rutas.Rutas.getExtencionCaptcha();
    }

    public void setDatosCig(String id, String name){
        //System.out.println("Agrego datos a cic id "+id+" name "+name);
        this.id = id;
        this.name = name;
    }
    public void setLink(String link){
        this.link = link;
    }

    public String getLink() {
        return link;
    }
    
    
    public void agregarError(String tipo, String info, int[] posXY) {
        //System.out.println("Agrego error tipo " + tipo + " info : " + info);
        this.errores.agregarError(tipo, info, posXY);
    }
    
    public boolean existenErrores(){
        return this.errores.existenErrores();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getTipoEtiqueta() {
        return tipoEtiqueta;
    }

    public List<Parametros> getParametros() {
        return parametros;
    }

    public List<String> getTexto() {
        return texto;
    }
    
    public boolean existeEtiqueta(String id){
        return this.idsEtiquetas.contains(id);
    }

    public void getInfo() {
        System.out.println("-------------------Info capturada---------------------------");
        for (int i = 0; i < tipoEtiqueta.size(); i++) {
            System.out.println("Tipo: " + tipoEtiqueta.get(i));
            parametros.get(i).getInfoParametros();
            //System.out.println(parametros.get(i).buscarInfo("background"));
            if (texto.get(i) != null) {
                System.out.println("Incluye texto y eso es: " + texto.get(i));
            }
            System.out.println("");
        }
    }        
}
