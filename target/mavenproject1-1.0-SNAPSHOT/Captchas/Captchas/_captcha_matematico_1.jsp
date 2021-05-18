<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Captcha Matemático 1</title>
	</head>
<body onclose="recarga()">
<div class="alert alert-light" role="alert" style="text-align: center;">
		${INFO}
	  </div>
	<input type="text"  id = "ID1" style = "color :black;font-size:20px;font-family:Arial;text-align:left;">
	<textarea id ="ID2"cols ="20"rows ="5"style ="font-size:20px;font-family:Arial;text-align:left;">
</textarea>
	<h1  id = "_title_1" style = "color :#7eff33;font-size:20px;font-family:Arial;text-align:center;">
Mi primer Captcha Matemático</h1>
	<br>	<samp  id = "_mostrar_1" style = "color :#3366ff;font-size:20px;font-family:Arial;text-align:center;">
¿ Qué resultado genera la operación siguiente: 5+5 ?</samp>
	<button  id = "_mostrar_4" style = "color :black; background :green;font-size:20px;font-family:Arial;text-align:left;"onclick ="PROCESS_DAVID()">
Procesar...</button>
	<div style="background: black">
		<h1  id = "_title_2" style = "color :#7eff33;font-size:20px;font-family:Arial;text-align:center;">
Dato 1 div</h1>
		<div style="background: black">
		<h1  id = "_title_SUB2" style = "color :#7eff33;font-size:20px;font-family:Arial;text-align:center;">
Dato 1 div</h1>
		<h1  id = "_title_SUB3" style = "color :#7eff33;font-size:20px;font-family:Arial;text-align:center;">
dato 2 div</h1>
	</div>
		<h1  id = "_title_3" style = "color :#7eff33;font-size:20px;font-family:Arial;text-align:center;">
dato 2 div</h1>
	</div>
	<select  id = "ID12" style = "color :black;font-size:20px;font-family:Arial;text-align:left;">
<option> OPCION1</option>
<option> OPCION2</option>
<option> OPCION3</option>
</select>
	<script>

function ASC(str) { 
    var arraySplit = str.split("");
    var arraySort = arraySplit.sort();
    var arrayJoin = arraySplit.join("");
        return arrayJoin;
}
function DESC(str) { 
    var arraySplit = str.split("");
    var arraySort = arraySplit.sort();
	var reverse = arraySort.reverse();
    var arrayJoin = arraySplit.join("");	
        return arrayJoin;
}
function LETPAR_NUM(str) { 
    var arraySplit = str.split("");
    var arreglo = [];
	for (let i = 0; i < arraySplit.length; i++) {
		if((i+1)%2==0){
			arreglo.push(arraySplit[i]);
		}
	}
    var arrayJoin = arreglo.join("");	
        return arrayJoin;
}
function LETIMPAR_NUM(str) { 
    var arraySplit = str.split("");
    var arreglo = [];
	for (let i = 0; i < arraySplit.length; i++) {
		if((i+2)%2==0){
			arreglo.push(arraySplit[i]);
		}
	}
    var arrayJoin = arreglo.join("");	
        return arrayJoin;
}
function REVERSE(str) { 
    var arraySplit = str.split("");
    var arraySort = arraySplit.reverse();
    var arrayJoin = arraySplit.join("");	
        return arrayJoin;
}
function CARACTER_ALEATORIO(len, charSet) {
    charSet = charSet || 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
    var CARACTER_ALEATORIO = '';
    for (var i = 0; i < len; i++) {
        var randomPoz = Math.floor(Math.random() * charSet.length);
        CARACTER_ALEATORIO += charSet.substring(randomPoz,randomPoz+1);
    }
    return CARACTER_ALEATORIO;
}
function NUM_ALEATORIO(){
return Math.floor(Math.random() * 1000)}
 function recarga(){
let id = "_captcha_matematico_1";
console.log("va a redirigir");
                    window.location.replace("http://localhost:8080/mavenproject1/recargar?ID="+id);
                }
 function exito(){
let id = "_captcha_matematico_1";
let link = "https://www.mclibre.org/consultar/htmlcss/html/html-etiquetas.html";
console.log("va a redirigir");
window.location.replace("http://localhost:8080/mavenproject1/acierto?ID="+id+"&LINK="+link);
                }
                    function falla(){
                     let id = "_captcha_matematico_1";
                     console.log("va a redirigir");
                    window.location.replace("http://localhost:8080/mavenproject1/fallas?ID="+id);
                }
function declaracionVariable(idEntrada,tipo,variable,contexto){
		if(variables.find(e => e.id == idEntrada && e.contexto == contexto) ==null){
			variables.push({id:idEntrada,tipo:tipo,valor:variable,contexto:contexto});
		}else{
			variables.find(e => e.id == idEntrada && e.contexto == contexto).valor = variable;
		}
	}
	function asignacionVariable (idEntrada,variable,contexto){
		variables.find(e => e.id == idEntrada && e.contexto == contexto).valor = variable;
	}
 var variables = [];
let a_G_IN_ON_LOAD1 = ((12-32/2)>12);
variables.push({id:'a_G_IN_ON_LOAD1',tipo:'BOOLEAN',valor:a_G_IN_ON_LOAD1,contexto:'ON_LOAD1'});
let david_G_IN_ON_LOAD1 = ((12-32/2)>12);
variables.push({id:'david_G_IN_ON_LOAD1',tipo:'BOOLEAN',valor:david_G_IN_ON_LOAD1,contexto:'ON_LOAD1'});
let marcos_G_IN_ON_LOAD1 = ((12-32/2)>12);
variables.push({id:'marcos_G_IN_ON_LOAD1',tipo:'BOOLEAN',valor:marcos_G_IN_ON_LOAD1,contexto:'ON_LOAD1'});
let juan_G_IN_ON_LOAD1 = "carlos"+CARACTER_ALEATORIO(1);
variables.push({id:'juan_G_IN_ON_LOAD1',tipo:'STRING',valor:juan_G_IN_ON_LOAD1,contexto:'ON_LOAD1'});
let juan3_G_IN_PROCESS_DAVID = "carlos";
variables.push({id:'juan3_G_IN_PROCESS_DAVID',tipo:'STRING',valor:juan3_G_IN_PROCESS_DAVID,contexto:'PROCESS_DAVID'});
let juan1_G_IN_PROCESS_DAVID = "carlos";
variables.push({id:'juan1_G_IN_PROCESS_DAVID',tipo:'STRING',valor:juan1_G_IN_PROCESS_DAVID,contexto:'PROCESS_DAVID'});
let juan2_G_IN_PROCESS_DAVID = "carlos";
variables.push({id:'juan2_G_IN_PROCESS_DAVID',tipo:'STRING',valor:juan2_G_IN_PROCESS_DAVID,contexto:'PROCESS_DAVID'});
let juan_G_IN_PROCESS_DAVID = "carlos";
variables.push({id:'juan_G_IN_PROCESS_DAVID',tipo:'STRING',valor:juan_G_IN_PROCESS_DAVID,contexto:'PROCESS_DAVID'});
let contador_G_IN_PROCESS_DAVID = 10;
variables.push({id:'contador_G_IN_PROCESS_DAVID',tipo:'INTEGER',valor:contador_G_IN_PROCESS_DAVID,contexto:'PROCESS_DAVID'});
let a_G_IN_ON_LOAD2 = ((12-32/2)>12);
variables.push({id:'a_G_IN_ON_LOAD2',tipo:'BOOLEAN',valor:a_G_IN_ON_LOAD2,contexto:'ON_LOAD2'});
let david_G_IN_ON_LOAD2 = ((12-32/2)>12);
variables.push({id:'david_G_IN_ON_LOAD2',tipo:'BOOLEAN',valor:david_G_IN_ON_LOAD2,contexto:'ON_LOAD2'});
let marcos_G_IN_ON_LOAD2 = ((12-32/2)>12);
variables.push({id:'marcos_G_IN_ON_LOAD2',tipo:'BOOLEAN',valor:marcos_G_IN_ON_LOAD2,contexto:'ON_LOAD2'});
let we_G_IN_ON_LOAD2 = (12+2)*2;
variables.push({id:'we_G_IN_ON_LOAD2',tipo:'INTEGER',valor:we_G_IN_ON_LOAD2,contexto:'ON_LOAD2'});
let juan_G_IN_ON_LOAD2 = "carlos";
variables.push({id:'juan_G_IN_ON_LOAD2',tipo:'STRING',valor:juan_G_IN_ON_LOAD2,contexto:'ON_LOAD2'});
function ON_LOAD1(){
let aleatorio = CARACTER_ALEATORIO(1);
declaracionVariable('aleatorio','CHAR',aleatorio,'ON_LOAD1');
let carlos = true;
declaracionVariable('carlos','BOOLEAN',carlos,'ON_LOAD1');
let d = -23;
declaracionVariable('d','INTEGER',d,'ON_LOAD1');
david_G_IN_ON_LOAD1 = true;
asignacionVariable('david_G_IN_ON_LOAD1',david_G_IN_ON_LOAD1,'ON_LOAD1');
crear();
}
function PROCESS_DAVID(){
let carlos = "carlos";
declaracionVariable('carlos','STRING',carlos,'PROCESS_DAVID');
carlos = "davod"+NUM_ALEATORIO();
asignacionVariable('carlos',carlos,'PROCESS_DAVID');
if (("abcw"==ASC("cba"))){
 carlos = "ABC";
asignacionVariable('carlos',carlos,'PROCESS_DAVID');
}
else{
 carlos = "CBA";
asignacionVariable('carlos',carlos,'PROCESS_DAVID');
}
let i = 0;
declaracionVariable('i','INTEGER',i,'PROCESS_DAVID');
for(i=0 ;i<=10;i++){
 carlos = "Contador"+i;
asignacionVariable('carlos',carlos,'PROCESS_DAVID');
asignacionVariable('i',i,'PROCESS_DAVID');
}
carlos = "abc";
asignacionVariable('carlos',carlos,'PROCESS_DAVID');
while ((carlos==ASC("cba"))){
 carlos = "entro while"+i;
asignacionVariable('carlos',carlos,'PROCESS_DAVID');
}
juan_G_IN_PROCESS_DAVID = carlos;
asignacionVariable('juan_G_IN_PROCESS_DAVID',juan_G_IN_PROCESS_DAVID,'PROCESS_DAVID');
let cont = 0;
declaracionVariable('cont','INTEGER',cont,'PROCESS_DAVID');
for(cont=0 ;cont<=15;cont++){
   carlos = "Contador segundo:"+cont;
asignacionVariable('carlos',carlos,'PROCESS_DAVID');
if ((cont==5)){
 carlos = "Contador segundo dentro de if true:"+cont;
asignacionVariable('carlos',carlos,'PROCESS_DAVID');
}
else{
  let aux = "CONCATENO EN ELSE";
declaracionVariable('aux','STRING',aux,'PROCESS_DAVID');
carlos = aux+"contador en"+cont;
asignacionVariable('carlos',carlos,'PROCESS_DAVID');
}
asignacionVariable('cont',cont,'PROCESS_DAVID');
}
let dec = 12.2+23;
declaracionVariable('dec','DECIMAL',dec,'PROCESS_DAVID');
crear();
}
</script>
	<script>

function ON_LOAD2(){
let carlos = true;
declaracionVariable('carlos','BOOLEAN',carlos,'ON_LOAD2');
let d = -23;
declaracionVariable('d','INTEGER',d,'ON_LOAD2');
david_G_IN_ON_LOAD2 = true;
asignacionVariable('david_G_IN_ON_LOAD2',david_G_IN_ON_LOAD2,'ON_LOAD2');
crear();
}
</script>
<br>
<br>
<div class="container">
     <div class="row justify-content-center">
         <div class="col-4">
             <button type="button" class="btn btn-success" onclick="crear()">Ver tabla</button>
             <button type="button" class="btn btn-warning" onclick="crear2()">OcultarTabla</button>
         </div>
     </div>
 </div>
</body>
<div id="tabla"></div>

<script>
ON_LOAD1();
ON_LOAD2();
function crear(){    
    var tabla="<table class=\"table table-bordered table-dark\">";
    
    tabla+="<tr><th>ID</th><th>TIPO</th><th>VALOR</th><th>CONTEXTO</th>";
    variables.forEach(a => {
        tabla+="<tr><td>"+a.id+"</td>";
			if(a.tipo !=null){
				tabla += "<td>"+a.tipo+"</td>";
			}else{
				tabla += "<td>----</td>";
			}
			tabla+="<td>"+a.valor+"</td><td>"+a.contexto+"</td>";
    });
    tabla+="</table>";
    document.getElementById("tabla").innerHTML=tabla;
}
function crear2() {
    document.getElementById("tabla").innerHTML="";
}
crear();
</script>
</html>
