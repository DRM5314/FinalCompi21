package scripting;

import error.ErroresPeticion;
import java.util.ArrayList;
import java.util.List;
import lexema.Lexema;
import operaciones.*;
import parserScript.ParserScript;
import variable.Variable;

public class Manejador {

    private variable.Manejador manejadorVariables = new variable.Manejador();
    private boolean error = false;
    private String nombreProcedimiento;

    private List<String> nombresVariblesAux = new ArrayList<>();
    private List<String> nombresVaribles = new ArrayList<>();

    private List<String> nombresFunciones = new ArrayList<>();
    private List<variable.Variable> variablesLocales = new ArrayList<>();
    private List<variable.Variable> variablesTotales = new ArrayList<>();
    private List<variable.Variable> variablesGlobales = new ArrayList<>();

    private List<Bloque> bloquesScript = new ArrayList<>();
    private Bloque bloqueAux = new Bloque();
    //Si esta repetida entonces no agrega nada y retorna false para indicar que esta repetida

    //agregarLinea(String tipo,String condicion,String inicio,String modo)
    public void agregarLinea(String id, String tipo, String condicion, String inicio, String modo) {
        this.bloqueAux.agregarLinea(id, tipo, condicion, inicio, modo, null);
    }

    public boolean existeFuncion(String nombre) {
        return this.nombresFunciones.contains(nombre);
    }

    public List<Variable> getVariablesGlobales() {
        return variablesGlobales;
    }

    public List<Bloque> getBloquesScript() {
        return bloquesScript;
    }

    public String errorVariableGlobal(String isGlobal) {
        String retorno = "";
        boolean existeError = false;
        if (isGlobal.equals("SI")) {
            for (int i = 0; i < nombresVariblesAux.size(); i++) {
                for (Variable varGlobal : variablesGlobales) {
                    if (nombresVariblesAux.size() > 0) {
                        if (varGlobal.getId().equals(nombresVariblesAux.get(i))) {
                            retorno += " - " + nombresVariblesAux.get(i) + " - ";
                            nombresVariblesAux.remove(i);
                            nombresVaribles.remove(i);
                            existeError = true;
                        }
                    }
                }
            }
            if (existeError) {
                return retorno;
            }
        }
        return null;
    }

    public void resetBloque() {
        this.bloquesScript.add(bloqueAux);
        bloqueAux = new Bloque();
    }

    public void resetLocales() {
        this.variablesTotales.addAll(variablesLocales);
        this.variablesLocales.clear();
        this.nombresVaribles.clear();
    }
    private String concatenadorGlobal = "_G_IN_";

    public Lexema getLexema(String id) {
        int posVar = posInVar(id);
        if (id.equals("contador_fallas")) {
            System.out.println("");
        }
        if (posVar >= 0) {
            Lexema retorno = this.variablesLocales.get(posVar).toLexema();
            //System.out.println("En busca de lexema"+id+" retorno "+retorno.getNombreVariable());
            //System.out.println("");
            return retorno;
        }
        return buscarLexemaGlobal(id + concatenadorGlobal + this.nombreProcedimiento);
    }

    private int posInVar(String id) {
        for (int i = 0; i < variablesLocales.size(); i++) {
            if (variablesLocales.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private Lexema buscarLexemaGlobal(String id) {
        for (Variable variable : variablesGlobales) {
            //System.out.println("Buscando en "+variable.getId()+" contra "+id);
            if (variable.getId().equals(id)) {
                Lexema retorno = variable.toLexema();
                retorno.setIsVariable(id);
                //System.out.println("En busca de "+id+" retorno "+retorno.getNombreVariable()+"\n");
                return retorno;
            }
        }
        return null;
    }
    private int contador_Onload = 0;

    public int getContador_Onload() {
        return contador_Onload;
    }

    public void setNombreProcedimiento(String nombre) {
        if (nombre.equals("ON_LOAD")) {
            contador_Onload++;
            nombre = nombre + contador_Onload;
        }
        this.nombreProcedimiento = nombre;
    }

    public boolean agregoNombreFuncion(String nombre) {
        if (!nombresFunciones.contains(nombre)) {
            this.nombresFunciones.add(nombre);
            return true;
        }
        return false;
    }

    public boolean agregarID(String id) {
        if (!this.nombresVariblesAux.contains(id) && !nombresVaribles.contains(id)) {
            nombresVariblesAux.add(id);
            nombresVaribles.add(id);
            return true;
        } else {
            return false;
        }
    }

    public void agregarVariables(String tipo, String modo, Lexema lexemaEntrada, ErroresPeticion entrada, int pos[]) {
        String valor = null;
        if (lexemaEntrada != null) {
            valor = lexemaEntrada.getValor();
        }
        boolean sinError = true;
        for (String id : nombresVariblesAux) {
            Variable aux = new Variable(tipo, id, modo, valor, this.nombreProcedimiento);
            if (modo.equals("SI")) {
                //System.out.println("Nueva variable global " + id+nombreProcedimiento);
                String idGlobal = id + concatenadorGlobal + nombreProcedimiento;
                if (!existeVariableGlobal(idGlobal)) {
                    aux.setId(idGlobal);
                    this.nombresVariblesAux.set(this.nombresVariblesAux.indexOf(id), aux.getId());
                    this.nombresVaribles.set(this.nombresVaribles.indexOf(id), aux.getId());
                    this.variablesGlobales.add(aux);
                } else {
                    entrada.agregarError("Semantico", "Id global ya existe:  " + id, pos);
                    sinError = false;
                }
            }
            if (sinError) {
                this.bloqueAux.agregarLinea(aux.getId(), "DECLARACION", null, valor, modo, aux.getTipoVariable());
                this.variablesLocales.add(aux);
            }
        }
    }

    public boolean existeVariableGlobal(String nombre) {
        for (Variable var : variablesGlobales) {
            if (var.getId().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void operar(lexema.Lexema parteIzquierda, String operador, lexema.Lexema parteDerecha) {
        this.manejadorVariables.reliazarAsignacion(parteIzquierda, operador, parteDerecha);
    }

    public void operar(String operador, lexema.Lexema parteDerecha) {
        this.manejadorVariables.reliazarAsignacion(operador, parteDerecha);
    }

    public String cambiosOperacion(lexema.Lexema parteIzquierda, String operador, lexema.Lexema parteDerecha) {
        return ResultadoOperacion.cambiosOperacion(parteIzquierda, operador, parteDerecha);
    }

    public void errorOperar(boolean entrada) {
        this.error = true;
    }

    public boolean errorOperacion() {
        return error;
    }

    public String getOperacion() {
        return this.manejadorVariables.getConcatenacion();
    }

    public String getTipoDato() {
        return this.manejadorVariables.resultadoOperacino();
    }

    public void resetOperacion() {
        //if(manejadorScript.resultadoOperacino()!=null)System.out.println(this.manejadorScript.getConcatenacion());
        this.nombresVariblesAux.clear();
        this.error = false;
        this.manejadorVariables = new variable.Manejador();
    }

    public lexema.Lexema convertirALexema() {
        if (!error) {
            return this.manejadorVariables.convertirALexema();
        }
        return null;
    }

    public Lexema convertirALexema(Lexema entrada) {
        return this.manejadorVariables.convertirALexema(entrada);
    }

    public String resultadoOperacion(lexema.Lexema parteIzquierda, String operador, lexema.Lexema parteDerecha) {
        return operaciones.ResultadoOperacion.tipoResultado(parteIzquierda.getTipo(), operador, parteDerecha.getTipo());
    }

    public String resultadoOperacion(String operador, lexema.Lexema parteDerecha) {
        return operaciones.ResultadoOperacion.tipoResultado(operador, parteDerecha.getTipo());
    }

    public List<String[]> getListadoVariablesTotales() {
        List<String[]> retorno = new ArrayList<>();
        for (Variable var : variablesTotales) {
            String modo;
            if (var.isGlobal()) {
                modo = "@global";
            } else {
                modo = "-----";
            }
            retorno.add(new String[]{var.getTipoVariable(), var.getId(), modo, var.getProcedimiento()});
            //System.out.println("Id " + var.getId() + " valor " + var.getValor() + " contexto " + var.getProcedimiento()+" modo "+var.isGlobal());
        }
        return retorno;
    }

    public void getInfoScript() {
        System.out.println("\n\n");
        System.out.println("-------Variables globales---------");
        for (Variable var : variablesGlobales) {
            System.out.println("Id " + var.getId() + " valor " + var.getValor() + " contexto " + var.getProcedimiento() + " modo " + var.isGlobal());
        }
        System.out.println("-------Variables totales---------");
        for (Variable var : variablesTotales) {
            System.out.println("Id " + var.getId() + " valor " + var.getValor() + " contexto " + var.getProcedimiento() + " modo " + var.isGlobal());
        }
        System.out.println("----------------contenido script------------------");
        for (int i = 0; i < bloquesScript.size(); i++) {
            System.out.println("Script # " + i + "\n\n");
            bloquesScript.get(i).getInfo();
            System.out.println("\n\nDe el esa onda debe salir esto \n");
            parserScript.ParserScript pr = new ParserScript(bloquesScript.get(i));
            String script = pr.parse();
            System.out.println(script);
        }
    }
}
