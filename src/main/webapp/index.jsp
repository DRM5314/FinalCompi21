<%-- 
    Document   : editor
    Created on : 22/04/2021, 01:25:35 AM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <style>
        .sinDatos{
            text-align: center;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CGCIC_ANALYZER</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    </head>
    <body>        
        <div class="custom-file">
                <input type="file" class="custom-file-input" id="fileInput" onchange="processFiles(this.files)">
                <label class="custom-file-label" for="fileInput">Choose file</label>                
            </div>
            
        <form id="analizador" method="POST" action="${pageContext.request.contextPath}/analizar" id="id">                        
            <div>
                <div id="areaEditor" class="border my-2" style="height: 450px; width:100%"></div>
            </div>
            <textarea  id="areaTexto" class="d-none" name="areaTexto" required="required">${codigo}</textarea>
            <div>
                <label id="posXY">Linea: 1 Col: 1</label>
            </div>
            <div>                
                <button type="submit" class="btn btn-success" onclick="">Analizar</button>                
                <a id="link" target="_blank" download="file.txt">
                <button type="button" class="btn btn-success" onclick="guardar()">Guardar</button>
                </a>
                <button type="reset" class="btn btn-success" onclick="borrar()">
                    Limpiar Area
                </button>
                <a href="${pageContext.request.contextPath}/captchasGenerados">
                    <button type="button" class="btn btn-dark">
                        Captchas generados
                    </button>
                </a>
            </div>

        </form>    
        <j:if test="${!empty(info) && info!=null}">
            <div class="alert alert-warning" role="alert">                
                <h3 id="info" class="sinDatos">${info}</h3>
            </div>            
        </j:if>

        <div>
            <j:if test="${!empty(errores)}">
                <table class="table" id="tablaErrores">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Info</th>
                            <th scope="col">Ubicacion</th>
                        </tr>
                    </thead>
                    <j:forEach var="f" items="${errores}" varStatus="contador">                
                        <tbody>
                            <tr>
                                <th scope="row">${contador.count}</th>
                                <td>${f[0]}</td>
                                <td>${f[1]}</td>
                                <td>${f[2]}</td>                        
                            </tr>
                        </tbody>                
                    </j:forEach>
                </table>
            </j:if>
        </div>
        <div >
            <j:if test="${!empty(variables)}">
                <div class="alert alert-primary" role="alert">
                    <h3 id="info1" class="sinDatos">Variable(s): </h3>
                </div>            
                <table class="table" id="tablaVariables">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Id</th>
                            <th scope="col">Modo</th>
                            <th scope="col">Contexto</th>
                        </tr>
                    </thead>
                    <j:forEach var="f" items="${variables}" varStatus="contador">                
                        <tbody>
                            <tr>
                                <th scope="row">${contador.count}</th>
                                <td>${f[0]}</td>
                                <td>${f[1]}</td>
                                <td>${f[2]}</td>                        
                                <td>${f[3]}</td>                        
                            </tr>
                        </tbody>                
                    </j:forEach>
                </table>
            </j:if>
        </div>            
    </body>
    <script src="${pageContext.request.contextPath}/js/ace-editor/src-min/ace.js"></script>    
    <script src="${pageContext.request.contextPath}/js/ace-editor/src-min/mode-text.js"></script>
    <script src="${pageContext.request.contextPath}/js/manejadorArchivos.js"></script>

    <script src="${pageContext.request.contextPath}/js/editor.js"></script>    

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>    
    <script src="${pageContext.request.contextPath}/js/ace-editor/src-min/ace.js"></script>    
    <script src="${pageContext.request.contextPath}/js/ace-editor/src-min/mode-text.js"></script>
    <script src="${pageContext.request.contextPath}/js/manejadorArchivos.js"></script>

    <script src="${pageContext.request.contextPath}/js/editor.js"></script>    

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
    <script>
                    function borrar() {
                        let tablaErrores = document.getElementById("tablaErrores");
                        let tablaVariables = document.getElementById("tablaVariables");
                        let info1 = document.getElementById("info1");
                        if (tablaErrores != null)
                            tablaErrores.innerText = "";
                        if (tablaVariables != null) {
                            tablaVariables.innerText = "";
                            info1.innerText = ":) gracias por usar CGCIC_ANALYZER";
                        }
                        editor.setValue('');
                        document.getElementById("posXY").innerText = "Linea: 1 Col: 1";
                        document.getElementById("info").innerText = "Bienvenido al sistema CIC analizer ";
                        document.getElementById("link").innerText = "";
                    }
                    function  mostrarTexto() {
                        console.log("trae:\n" + document.getElementById("areaTexto").value);
                    }
                    function recargarTexto() {
                        editor.setValue(document.getElementById("areaTexto").value).typeof(String);
                    }
                    recargarTexto();
                    function guardar(fs) {
var file;
var data = [];
var texto = document.getElementById("areaTexto").value;
data.push(texto);
var properties = {type: 'text/plain'}; // Specify the file's mime-type.
        try {
  // Specify the filename using the File constructor, but ...
  file = new File(data, "file.txt", properties);
} catch (e) {
  // ... fall back to the Blob constructor if that isn't supported.
  file = new Blob(data, properties);
}
var url = URL.createObjectURL(file);
document.getElementById('link').href = url;
}
    </script>

</html>
