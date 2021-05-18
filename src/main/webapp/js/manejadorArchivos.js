var salida = "";
function processFiles(files) {
    var file = files[0];

    var reader = new FileReader();

    reader.onload = (e) =>{
        // Cuando éste evento se dispara, los datos están ya disponibles.
        // Se trata de copiarlos a una área <div> en la página.
        const csv = reader.result ;
        document.getElementById("fileInput").textContent = csv;
        llenarCampo(e.target.result);
    };
    reader.readAsText(file);
}
function llenarCampo(texto) {    
    var txt = texto;
    for (var i = 0; i < txt.length; i++) {
        salida += txt[i];
    }
    editor.setValue(salida);
    document.getElementById("areaTexto").innerHTML = salida;
}