<%-- 
    Document   : captchasGenerados
    Created on : 16/05/2021, 12:53:42 AM
    Author     : david
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Captchas generados</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/index.jsp">
            <button class="btn btn-success">Regresar a inicio</button>
        </a>
        <j:if test="${!empty(sinCaptchas)}">
            <div class="alert alert-warning" role="alert">                
                <h3 class="sinDatos">${sinCaptchas}</h3>                
            </div>
        </j:if>
        <j:if test="${!empty(nombres)}">
            <table class="table" id="tabla">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id Captcha</th>                        
                        <th scope="col">Link</th>                        
                    </tr>
                </thead>
                <j:forEach var="f" items="${nombres}" varStatus="contador">                
                    <tbody>
                        <tr>
                            <th scope="row">${contador.count}</th>
                            <td>${f}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/redireccionar?ID=${f}.jsp">
                                    http://localhost:8080${pageContext.request.contextPath}/redireccionar?ID=${f}.jsp
                                </a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/redireccionar?ID=${f}.jsp">
                                    <button type="button" class="btn btn-dark">Utilizar</button>
                                </a>
                            </td>
                        </tr>
                    </tbody>                
                </j:forEach>
            </table>
        </j:if>

    </body>
</html>
