package operaciones;

public class ResultadoOperacion {

    private static String integer = "INTEGER";
    private static String string = "STRING";
    private static String decimal = "DECIMAL";
    private static String char_ = "CHAR";
    private static String boolean_ = "BOOLEAN";

    /*
    Matriz donde ser verifica que tipo de resultado arroja la operacion, antes esta es validad, pero 
    aqui tambine se valida, la matriz sique un tipo de 
    
                integer     string      decimal     caracter        boolean
    integer 
    string
    decimal
    caracter
    boolean
    
     */
    private static String suma[][] = {
        {integer, string, decimal, integer, integer},
        {string, string, string, string, null},
        {decimal, string, decimal, decimal, decimal},
        {integer, string, decimal, integer, integer},
        {integer, null, decimal, integer, boolean_}
    };

    private static String resta[][] = {
        {integer, null, decimal, integer, integer},
        {null, null, null, null, null},
        {decimal, null, decimal, decimal, decimal},
        {integer, null, decimal, integer, null},
        {integer, null, decimal, null, null}
    };

    private static String multiplicacion[][] = {
        {integer, null, decimal, integer, integer},
        {null, null, null, null, null},
        {decimal, null, decimal, decimal, decimal},
        {integer, null, decimal, integer, integer},
        {integer, null, decimal, integer, boolean_},};

    private static String division[][] = {
        {decimal, null, decimal, decimal, integer},
        {null, null, null, null, null},
        {decimal, null, decimal, decimal, decimal},
        {decimal, null, decimal, decimal, integer},
        {decimal, null, decimal, decimal, null},};

    private static String operadory[][] = {
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, boolean_}
    };

    private static String operadorO[][] = {
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, boolean_}
    };
    
    private static String operadorIgualIgual [][] = {
        {boolean_,null,boolean_,null,null},
        {null,boolean_,null,boolean_,null},
        {boolean_,null,boolean_,null,null},
        {null,boolean_,null,boolean_,null},
        {null,null,null,null,boolean_},
    };
    
    private static String operadorDiferente [][] = {
        {boolean_,null,boolean_,null,null},
        {null,boolean_,null,boolean_,null},
        {boolean_,null,boolean_,null,null},
        {null,boolean_,null,boolean_,null},
        {null,null,null,null,boolean_},
    };
    
    private static String operadorMayorIgual [][] = {
        {boolean_,null ,boolean_ ,null ,null},
        {null,null ,null ,null ,null},
        {boolean_,null ,boolean_ ,null ,null},
        {null,null ,null ,null ,null},
        {null,null ,null ,null ,null}
    };
    
    private static String operadorMenorIgual [][] = {
        {boolean_,null ,boolean_ ,null ,null},
        {null,null ,null ,null ,null},
        {boolean_,null ,boolean_ ,null ,null},
        {null,null ,null ,null ,null},
        {null,null ,null ,null ,null}
    };
    
    private static String operadorMayor [][] = {
        {boolean_,null ,boolean_ ,null ,null},
        {null,null ,null ,null ,null},
        {boolean_,null ,boolean_ ,null ,null},
        {null,null ,null ,null ,null},
        {null,null ,null ,null ,null}
    };
    
    private static String operadorMenor [][] = {
        {boolean_,null ,boolean_ ,null ,null},
        {null,null ,null ,null ,null},
        {boolean_,null ,boolean_ ,null ,null},
        {null,null ,null ,null ,null},
        {null,null ,null ,null ,null}
    };
    
    
    private static int convertidorPos(String tipoDato) {
        //System.out.println("Tipo de dato es " + tipoDato);
        switch (tipoDato) {
            case "INTEGER":
                return 0;
            case "STRING":
                return 1;
            case "DECIMAL":
                return 2;
            case "CHAR":
                return 3;
            case "BOOLEAN":
                return 4;
        }
        return -1;
    }

    private static int[] posTabla(String tipoIzquierda, String tipoDerecha) {
        return new int[]{convertidorPos(tipoIzquierda), convertidorPos(tipoDerecha)};
    }

    public static String tipoResultado(String tipoIzquierda, String operacion, String tipoDerecha) {
        int pos[] = posTabla(tipoIzquierda, tipoDerecha);
        //System.out.println("Pos en tabla con " + operacion + " " + pos[0] + " " + pos[1]);
        String retorno = "";
        switch (operacion) {
            case "+":
                return suma[pos[0]][pos[1]];
            case "-":
                return resta[pos[0]][pos[1]];
            case "*":
                return multiplicacion[pos[0]][pos[1]];
            case "/":
                return division[pos[0]][pos[1]];
            case "&&":
                return operadory[pos[0]][pos[1]];
            case "||":
                return operadorO[pos[0]][pos[1]];
            case "==":
                return operadorIgualIgual[pos[0]][pos[1]];
            case "!=":
                return operadorDiferente[pos[0]][pos[1]];
            case ">=":
                return operadorMayorIgual[pos[0]][pos[1]];
            case "<=":
                return operadorMenorIgual[pos[0]][pos[1]];
            case ">":
                return operadorMayor[pos[0]][pos[1]];
            case "<":
                return operadorMenor[pos[0]][pos[1]];
        }
        return null;
    }
    public static String tipoResultado(String operacion, String tipoDerecha) {
        switch (operacion) {
            case "!":
                if(tipoDerecha!=null && tipoDerecha.equals("BOOLEAN")){
                    return boolean_;
                }
        }
        return null;
    }

    public static String cambiosOperacion(lexema.Lexema parteIzquierda, String operador, lexema.Lexema parteDerecha) {
        switch (operador) {
            case "+":
                if (parteDerecha.getTipo().equals("BOOLEAN") && parteIzquierda.getTipo().equals("BOOLEAN")) {
                    return "||";
                } else {
                    break;
                }
            case "*":
                if (parteDerecha.getTipo().equals("BOOLEAN") && parteIzquierda.getTipo().equals("BOOLEAN")) {
                    return "&&";
                }
                break;
        }
        if (operador.equals("+") || operador.equals("-")) {
            int posAux[] = {convertidorPos2(parteIzquierda.getTipo()), convertidorPos2(parteDerecha.getTipo())};
            if (posAux[0] >= 0 && posAux[1] >= 0) {
                boolean convierte = char_Convierte_A_Int[posAux[0]][posAux[1]];
                if (convierte) {
                    parteDerecha.convertirChar_Int();
                    parteIzquierda.convertirChar_Int();
                }
            }
        }
        return operador;
    }
    /*
     
                integer     caracter
    integer
    
    caracter
    

     */

    private static boolean char_Convierte_A_Int[][] = {
        {false, true},
        {true, false}
    };

    private static int convertidorPos2(String tipo) {
        switch (tipo) {
            case "INTEGER":
                return 0;
            case "CHAR":
                return 1;
        }
        return -1;
    }

}
