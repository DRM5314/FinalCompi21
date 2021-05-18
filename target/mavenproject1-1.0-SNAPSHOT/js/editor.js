let areatexo = document.querySelector('#areaTexto');
let editor = ace.edit("areaEditor");

let editorConfig = {
    init() {
        editor.setValue('');

        //Tema
        editor.setTheme("ace/theme/chrome");

        //Language
        editor.session.setMode("ace/mode/html_ruby");

        //Options
        editor.setOptions({            
            fontSize: '14pt',
            printMarginColumn : 0
        });
    }
};

editor.session.selection.on('changeCursor', function(e) {
    const line = editor.getCursorPosition().row;
    const column = editor.getCursorPosition().column;
    document.querySelector('#posXY').innerHTML = 'Linea: ' + (line + 1) + ' Col: ' + (column + 1);
});

editor.getSession().on('change', () => {
    areaTexto.innerHTML = editor.getValue();
});

editorConfig.init();