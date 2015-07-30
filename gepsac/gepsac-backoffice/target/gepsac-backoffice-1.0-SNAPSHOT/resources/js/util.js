function esAlfaNumLogin(event) {
    // Codigos Javascript del Teclado � KeyCodes
    // tab:         9 
    // arrow left:  37 - %
    // arrow right: 39 
    // end:         35 - #
    // home:        36 - $
    // backspace:   8 
    // delete:      46 - .
    if (!window.event) {
        if ((event.which > 64 && event.which < 91) || (event.which > 96 && event.which < 123) || (event.which >= 48 && event.which <= 57) || event.which == 13) {
            return true;
        }
        if ((event.keyCode == 37 && event.which != 37) || event.keyCode == 39 || event.keyCode == 9 || (event.keyCode == 35 && event.which != 35) || (event.keyCode == 36 && event.which != 36) || event.keyCode == 8 || (event.keyCode == 46 && event.which != 46)) {
            return true;
        }
        // �:   241 ; �:    209
        // �:   225 ; �:    193
        // �:   233 ; �:    201
        // �:   237 ; �:    205
        // �:   243 ; �:    211
        // �:   250 ; �:    218
        if (event.which == 241 || event.which == 209 || event.which == 225 || event.which == 193 || event.which == 233 || event.which == 201 || event.which == 237 || event.which == 205 || event.which == 243 || event.which == 211 || event.which == 250 || event.which == 218) {
            return true;
        }
    } else {
        if ((event.keyCode > 64 && event.keyCode < 91) || (event.keyCode > 96 && event.keyCode < 123) || (event.keyCode >= 48 && event.keyCode <= 57) || event.keyCode == 13) {
            return true;
        }
        if (event.keyCode == 241 || event.keyCode == 209 || event.keyCode == 225 || event.keyCode == 193 || event.keyCode == 233 || event.keyCode == 201 || event.keyCode == 237 || event.keyCode == 205 || event.keyCode == 243 || event.keyCode == 211 || event.keyCode == 250 || event.keyCode == 218) {
            return true;
        }
    }
    return false;
}

function esAlfaNum(event) {
    /*
     var k;
     document.all ? k = e.keyCode : k = e.which;
     return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57));
     */
    var keyCode = event.keyCode || event.which;
    charIngresado = String.fromCharCode(keyCode);
    // Codigos Javascript del Teclado � KeyCodes
    // tab:         9 
    // arrow left:  37 - %
    // arrow right: 39 
    // end:         35 - #
    // home:        36 - $
    // backspace:   8 
    // delete:      46 - .
    if (!window.event) {
        if ((event.keyCode == 37 && event.which != 37) || event.keyCode == 39 || event.keyCode == 9 || (event.keyCode == 35 && event.which != 35) || (event.keyCode == 36 && event.which != 36) || event.keyCode == 8 || (event.keyCode == 46 && event.which != 46)) {
            return true;
        }
    }
    patron = /([A-Za-z0-9������������ ])/;
    return patron.test(charIngresado);
}

function esAlfaNumGionB(e) {
    var k;
    document.all ? k = e.keyCode : k = e.which;
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 95 || k == 8 || k == 32 || (k >= 48 && k <= 57));
}

function esAlfaNumPunto(e) {
    var k;
    document.all ? k = e.keyCode : k = e.which;
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 95 || k == 46 || (k >= 48 && k <= 57));
}

function esAlfaNumEsp(event) {
    /*
     var k;
     document.all ? k = e.keyCode : k = e.which;
     return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k==58 || k==47 || k==40 || k==41 || k == 32 || k== 46 || k == 95 ||(k >= 48 && k <= 57));
     */
    var keyCode = event.keyCode || event.which;
    charIngresado = String.fromCharCode(keyCode);
    // Codigos Javascript del Teclado � KeyCodes
    // tab:         9 
    // arrow left:  37 - %
    // arrow right: 39 
    // end:         35 - #
    // home:        36 - $
    // backspace:   8 
    // delete:      46 - .
    if (!window.event) {
        if ((event.keyCode == 37 && event.which != 37) || event.keyCode == 39 || event.keyCode == 9 || (event.keyCode == 35 && event.which != 35) || (event.keyCode == 36 && event.which != 36) || event.keyCode == 8 || (event.keyCode == 46 && event.which != 46)) {
            return true;
        }
    }
    patron = /([A-Za-z0-9������������:./() ])/;
    return patron.test(charIngresado);
}

function esAlfaNumCorreo(event)
{
    /*
     var k;
     document.all ? k = e.keyCode : k = e.which;
     return ((k >= 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 95 || k== 46 ||(k >= 48 && k <= 57));
     */
    var keyCode = event.keyCode || event.which;
    charIngresado = String.fromCharCode(keyCode);
    // Codigos Javascript del Teclado � KeyCodes
    // tab:         9 
    // arrow left:  37 - %
    // arrow right: 39 
    // end:         35 - #
    // home:        36 - $
    // backspace:   8 
    // delete:      46 - .
    if (!window.event) {
        if ((event.keyCode == 37 && event.which != 37) || 
                event.keyCode == 39 || event.keyCode == 9 || 
                (event.keyCode == 35 && event.which != 35) || 
                (event.keyCode == 36 && event.which != 36) || 
                event.keyCode == 8 || event.keyCode == 45 || event.keyCode == 95 ||
                (event.keyCode == 46 && event.which != 46) ||
                (event.keyCode == 45 || event.which != 45)) {
            return true;
        }
    }
    patron = /([A-Za-z0-9������������@._-])/;
    return patron.test(charIngresado);
}

function esCaracter(event) {
    /*
     var k;
     document.all ? k = e.keyCode : k = e.which;
     return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32);
     */
    var keyCode = event.keyCode || event.which;
    charIngresado = String.fromCharCode(keyCode);
    // Codigos Javascript del Teclado � KeyCodes
    // tab:         9 
    // arrow left:  37 - %
    // arrow right: 39 
    // end:         35 - #
    // home:        36 - $
    // backspace:   8 
    // delete:      46 - .
    if (!window.event) {
        if ((event.keyCode == 37 && event.which != 37) || event.keyCode == 39 || event.keyCode == 9 || (event.keyCode == 35 && event.which != 35) || (event.keyCode == 36 && event.which != 36) || event.keyCode == 8 || (event.keyCode == 46 && event.which != 46)) {
            return true;
        }
    }
    patron = /([A-Za-z������������ ])/;
    return patron.test(charIngresado);
}

function esNumero(event) {
    /*
     var k;
     document.all ? k = e.keyCode : k = e.which;
     return ((k >= 48 && k <= 57) || k == 8);
     */
    var keyCode = event.keyCode || event.which;
    charIngresado = String.fromCharCode(keyCode);
    // Codigos Javascript del Teclado � KeyCodes
    // tab:         9 
    // arrow left:  37 - %
    // arrow right: 39 
    // end:         35 - #
    // home:        36 - $
    // backspace:   8 
    // delete:      46 - .
    if (!window.event) {
        if ((event.keyCode == 37 && event.which != 37) || event.keyCode == 39 || event.keyCode == 9 || (event.keyCode == 35 && event.which != 35) || (event.keyCode == 36 && event.which != 36) || event.keyCode == 8 || (event.keyCode == 46 && event.which != 46)) {
            return true;
        }
    }
    patron = /([0-9])/;
    return patron.test(charIngresado);
}

function hasClass(ele, cls) {
    return ele.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
}

function addClass(ele, cls) {
    if (!this.hasClass(ele, cls))
        ele.className += " " + cls;
}

function removeClass(ele, cls) {
    if (hasClass(ele, cls)) {
        var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
        ele.className = ele.className.replace(reg, ' ');
    }
}

/**
 * @param formulario    : nombre del pop up
 * @param titulo        : nuevo titulo
 */
function popupTitulo(formulario, titulo) {
    $(formulario).dialog({
        title: titulo
    });
}

/**
 * @param formulario    : nombre del pop up
 */
function popupClose(formulario) {
    $(formulario).dialog("close");
}

/**
 * @param formulario    : nombre del pop up
 */
function popupOpen(formulario) {
    $(formulario).dialog("open");
}

function popupSetDimension(formulario, _width, _height) {
    $(formulario).dialog({
        width: _width
    });
    $(formulario).dialog({
        height: _height
    });
}

/**
 * @param source    : name del select que debe extraerse el text actual
 * @param target    : name del hidden a modificar
 */
function setValue(source, target) {
    var value = $('select[name="' + source + '"] option:selected').text();
    $('input[name="' + target + '"]').val(value);
}

/**
 * @param column   : index de la columna de la tabla
 * @return         : data de la celda
 */
function obtenerDataCeldaByIndexColumn(col) {
    var oSettings = oTable.fnSettings();  // you can find all sorts of goodies in the Settings
    var col_id = oSettings.aoColumns[col].mData;
    return obtenerDataCeldaTabla(col_id);
}

/**
 * @param column   : key que le da el valor a la celda de cada columna
 * @return         : data de la celda
 */
function obtenerDataCeldaTabla(column) {
    var aData = oTable.fnGetData(index);
    
    if(aData!=null){
        return aData[column];
    }else{
        return "";
    }
}

/**
 * Funcion que permite obtener el index de la fila
 * de la tabla de resultados
 */
function asociarEventoActualizador() {
    $(table + " tbody td").click(function() {
        var aPos = oTable.fnGetPosition(this);
        index = aPos[0];
    });
}

function esNumerico(numero) {
    if (/^([0-9])*$/.test(numero)) {
        return true;
    }

    return false;
}


function validarTextoNumerico(field) {
    var re = /^[0-9-'.'-',']*$/;
    if (!re.test(field.value)) {
        field.value = field.value.replace(/[^0-9-'.'-',']/g, "");
    }
}

function setearComboName(combo, response) {
    var select = $("select[name='" + combo + "']");
    select.empty();
    select.append($("<option></option>").val('-1').html('Seleccione'));
    $.each(response, function() {
        select.append($("<option></option>").val(this['lId']).html(this['nombre']));
    });
}

function setearCombo(combo, response) {
    var select = $("select[id='" + combo + "']");
    select.empty();
    select.append($("<option></option>").val('-1').html('Seleccione'));
    $.each(response, function() {
        select.append($("<option></option>").val(this['lId']).html(this['nombre']));
    });
}

function reiniciarCombo(combo) {
    var select = $("select[id='" + combo + "']");
    select.empty();
    select.append($("<option></option>").val('-1').html('Seleccione'));
}

//function contador(compo, maxlength){
//    var value = compo.value;
//    if(value !== null && value.length > 0){
//        length = value.length;
//        if(length <= maxlength){
//            compo.value = value;
//        }else{
//            compo.value = value.substring(0,maxlength);
//        }
//    }
//}

//function contador(inicio, maxlength) {
//    var value = inicio.value;
//    var tam = value.length;
//    if (tam > maxlength) {
//        inicio.value = value.substring(0, maxlength);
//    }
//}