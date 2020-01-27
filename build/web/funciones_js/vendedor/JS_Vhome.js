/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// jquery
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode
    return (key >= 48 && key <= 57)//return ((key >= 48 && key <= 57) || (key==46))
}
function soloNumeros_con_punto(e) {
    var key = window.Event ? e.which : e.keyCode
    //return (key >= 48 && key <= 57)
    return ((key >= 48 && key <= 57) || (key == 46))
}

$(document).ready(function () {
    var lista_tmp;
    var n_pedido = "";
    var n_pedido_str = "";
    var id_user = $('#obj_id_user').val();
    ;
    var id_tmp;
    var cat_tmp;
    var pre_tmp;

    var idcliente = 0;
    var idproducto = 0;
    var catproduct = 0;
    var listproduc = [];
    var listaCliente = [];
    var nombreproduc="";
    load();
    $('#btn_guardar_cliente').click(function () {//console.log(id_producto_s);
        if (idcliente == 0) {
            var dr = $("#txtdniruc").val();
            var name = $("#txtname").val();
            dr = dr.trim();
            name = name.trim();
            if (dr.length == 0) {
                $('#txtdniruc').focus();
                alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'Falta ingresar dni o ruc.'}).show();
                return;
            }
            if (name.length == 0) {
                $('#txtname').focus();
                alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'Falta ingresar el nombre.'}).show();
                return;
            }
            var direc = $("#txtdireccion").val();
            var cel = $("#txtcelular").val();
            var tipocli = $("#tipodoc").val();
            $.ajax({
                type: "POST",
                data: {tipo_cliente: tipocli, txt_nombrepersona: name,
                    txt_documento: dr, txt_direccion: direc, txt_celular: cel,
                    txt_email: "email"
                },
                url: 'insertar_cliente',
                success: function (response) {
                    alertify.success('se guardo el cliente exitosamente');
                    listar_cliente();
                    get_lastIdcliente();
                }
            });
        } else {
            alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'No se puede guardar el mismo cliente.'}).show();
        }
    });

    $('#btn_actualizar_cliente').click(function () {//console.log(id_producto_s);
        //alert("ya se actualizo!! naaa mentiraaaaa !!! aun no se llama al ayax :3"); 
        var dr = $("#txtdniruc").val();
        var name = $("#txtname").val();
        var direc = $("#txtdireccion").val();
        var cel = $("#txtcelular").val();
        var tipocli = $("#tipodoc").val();//alert(idcliente);
        $.ajax({
            type: "POST",
            data: {tipo_cliente: tipocli, txt_nombrepersona: name,
                txt_documento: dr, txt_direccion: direc, txt_celular: cel,
                txt_email: "email", estado: 1, id_cliente: idcliente
            },
            url: 'actualizar_cliente',
            success: function (response) {
                alertify.success('se Actualizo el cliente exitosamente');
                listar_cliente();
                //get_lastIdcliente();
            }
        });
    });

    $('#add_pro').click(function () {
        addpro();
    });
    
    $("#txtcantidad").keydown(function (e) {
        if (e.keyCode === 13) {
            addpro();
        }
    });
   
    // ===== AUTOCOMPLETE INPUT PRODUCTOS
    $("#txt_produc").autocomplete({
        minLength: 1,
        source: listproduc,
        focus: function (event, ui) {
            $("#txt_produc").val(ui.item.label);
            return false;
        },
        select: function (event, ui) {
            $("#txt_produc").val(ui.item.label);
            idproducto = ui.item.id;
            catproduct = ui.item.cat;
            nombreproduc=ui.item.label;
            $("#txtprecio").val(ui.item.precio);
            $("#txtstocka").val(ui.item.sa);
            $("#txtstockm").val(ui.item.sm);
            $("#txtcantidad").focus();
            return false;
        }
    });
    function listar_producto(str) {
        $.ajax({
            type: "GET",
            data: {texto_busqueda_pro: str, allproductos: 1, productservis: 1},
            url: 'busqueda_producto',
            success: function (response) {//console.log("-- "+response.lista_busqueda_factura);
                var cantidad = response.lista_busqueda_producto.length;//console.log("c"+cantidad);
                var listaa = response.lista_busqueda_producto;
                agregar_filas(cantidad, listaa);
            }
        });
    }// agregar las filas 
    function agregar_filas(cantidad_datos, respondar) {//alert(cantidad_datos);  
        for (var i = 0; i < cantidad_datos; i++) {
            var produc = respondar[i].codigo_producto + " " + respondar[i].descripcion_producto;
            var idp = respondar[i].id_producto;
            var preciop = respondar[i].precio_producto;
            var stock_a = respondar[i].stock_a_producto;
            var stock_m = respondar[i].stock_m_producto;
            var cat = respondar[i].id_categoria;
            listproduc.push({value: produc, label: produc, id: idp, precio: preciop, sa: stock_a, sm: stock_m, cat: cat});
        }
    }
    $("#txt_produc").on("keydown", function (event) {
        if (event.keyCode == $.ui.keyCode.LEFT || event.keyCode == $.ui.keyCode.RIGHT || event.keyCode == $.ui.keyCode.UP || event.keyCode == $.ui.keyCode.DOWN || event.keyCode == $.ui.keyCode.DELETE || event.keyCode == $.ui.keyCode.BACKSPACE)
        {
            idproducto = 0;
            catproduct = 0;
            $("#txtprecio").val("");
            $("#txtstocka").val("");
            $("#txtstockm").val("");
        }
        if (event.keyCode == $.ui.keyCode.DELETE) {
            $("#txt_produc").val("");
            idproducto = 0;
            catproduct = 0;
            $("#txtprecio").val("");
            $("#txtstocka").val("");
            $("#txtstockm").val("");
        }
    });
    // ===== END AUTOCOMPLETE INPUT PRODUCTOS ===== //


    // ===== AUTOCOMPLETE INPUT CLIENTES DNIRUC
    $("#txtdniruc").autocomplete({
        minLength: 1,
        source: listaCliente,
        focus: function (event, ui) {
            $("#txtdniruc").val(ui.item.label);
            return false;
        },
        select: function (event, ui) {
            $("#txtdniruc").val(ui.item.label);
            $("#txtname").val(ui.item.name);
            $("#txtdireccion").val(ui.item.dir);
            $("#txtcelular").val(ui.item.cel);
            $("#btn_guardar_cliente").hide();
            $("#btn_actualizar_cliente").show();
            idcliente = ui.item.id;
            return false;
        }
    });
    function listar_cliente() {
        $.ajax({
            type: "GET",
            data: {},
            url: 'listar_clienre_autocomplt',
            success: function (response) {//console.log("-- "+response.lista_busqueda_factura);
                var cantidad = response.lista_busqueda_clientes.length;//console.log("c"+cantidad);
                var listaa = response.lista_busqueda_clientes;//alert(listaa);
                console.log(response.lista_busqueda_clientes);
                cargar_listClient(cantidad, response.lista_busqueda_clientes);
            }
        });
    }// agregar las filas 
    function cargar_listClient(cantidad_datos, respondar) {
        while (listaCliente.length > 0) //limpiar
            listaCliente.pop();        //array listaCliente
        for (var i = 0; i < cantidad_datos; i++) {
            var dniruc = respondar[i].value;
            var idc = respondar[i].id_cliente;
            var name = respondar[i].nombre_persona;
            var dir = respondar[i].direccion_persona;
            var cel = respondar[i].celular_persona;
            var email = respondar[i].email_persona;
            listaCliente.push({value: dniruc, label: dniruc, id: idc, name: name, dir: dir, cel: cel, email: email});
        }    //alert(listaCliente[2].value);
    }
    $("#txtdniruc").on("keydown", function (event) {
        if (event.keyCode == $.ui.keyCode.LEFT || event.keyCode == $.ui.keyCode.RIGHT || event.keyCode == $.ui.keyCode.UP || event.keyCode == $.ui.keyCode.DOWN || event.keyCode == $.ui.keyCode.DELETE || event.keyCode == $.ui.keyCode.BACKSPACE)
        {
            idcliente = 0;//$("#id_cliente" ).val("");
            $("#txtname").val("");
            $("#txtdireccion").val("");
            $("#txtcelular").val("");

            $("#btn_guardar_cliente").show();
            $("#btn_actualizar_cliente").hide();
        }
        if (event.keyCode == $.ui.keyCode.DELETE) {
            $("#txtdniruc").val("");
            idcliente = 0;//$("#id_cliente" ).val("");
            $("#txtname").val("");
            $("#txtdireccion").val("");
            $("#txtcelular").val("");

            $("#btn_guardar_cliente").show();
            $("#btn_actualizar_cliente").hide();
        }
    });
    // ===== END AUTOCOMPLETE INPUT CLIENTES DNIRUC ===== //    

    //---------------------------------  --------------------------- 
    function get_lastIdcliente() {
        $.ajax({
            type: "GET",
            data: {},
            url: 'obtener_idcliente',
            success: function (response) {
                var listaa = response.cliente_id;//alert(listaa.length);                            
                idcliente = listaa[0].id_cliente;//alert(idcliente);
            }
        });
    }
    function trunc(x, posiciones) {
        var s = x.toString();
        var l = s.length;
        var decimalLength = s.indexOf('.') + 1;
        var numStr;
        if (decimalLength != 0) {
            numStr = s.substr(0, decimalLength + posiciones);
        } else {
            numStr = x;
        }
        return Number(numStr);
    }
    function getfechaAct() {
        n = new Date();
        y = n.getFullYear();
        m = n.getMonth() + 1;
        if (m < 10) {
            m = "0" + m;
        }
        d = n.getDate();
        $('#txtfecha').val(y + "-" + m + "-" + d);
    }
    function gethoraAct() {
        var momentoActual = new Date();
        var hora = momentoActual.getHours();
        var minuto = momentoActual.getMinutes();
        var segundo = momentoActual.getSeconds();

        var str_segundo = new String(segundo)
        if (str_segundo.length == 1)
            segundo = "0" + segundo

        var str_minuto = new String(minuto)
        if (str_minuto.length == 1)
            minuto = "0" + minuto

        var str_hora = new String(hora)
        if (str_hora.length == 1)
            hora = "0" + hora

        var horaImprimible = hora + ":" + minuto + ":" + segundo;
        console.log(horaImprimible);
        return horaImprimible;
    }
    function getAddZerosToRight(str) {// 100.3 ==> 100.30 o 100 ==> 100.00
        str = "" + str;
        var divisiones = str.split('.');
        if (divisiones.length == 1) {
            str = str + ".00";
        } else {
            if (divisiones[1].length == 1) {
                str = str + "0";
            }
        }
        return str;
    }
    function zfill(number, width) {
        var numberOutput = Math.abs(number); /* Valor absoluto del número */
        var length = number.toString().length; /* Largo del número */
        var zero = "0"; /* String de cero */

        if (width <= length) {
            if (number < 0) {
                return ("-" + numberOutput.toString());
            } else {
                return numberOutput.toString();
            }
        } else {
            if (number < 0) {
                return ("-" + (zero.repeat(width - length)) + numberOutput.toString());
            } else {
                return ((zero.repeat(width - length)) + numberOutput.toString());
            }
        }
    }
    //--------------------------------- TMP DETALLEPEDIDO TMP ---------------------------
    // LISTAR tmp_
    function listar_tmp() {
        $.ajax({
            type: "GET",
            data: {id_pedido: n_pedido},
            url: 'listar_tmp',
            success: function (response) {
                // cojia y limpiaba dagtos 
                var cantidad = response.lista_busqueda_tmp.length;//console.log("count lista_detsalida : "+cantidad);
                lista_tmp = response.lista_busqueda_tmp;
                agregar_filas_tmp_detfactura(cantidad, lista_tmp);
                // click a un determinado boton                
                traer_datos_tmp(lista_tmp);
                del_listmp(lista_tmp);
            }
        });
    }
    // agregar las filas  TMP DETALLE SALIDA
    function agregar_filas_tmp_detfactura(cantidad_datos, respondar) {
        $('.fila_pro_tmpdetfac').remove();//clase CSS q remove todas las acitvite
        var total = 0;
        var igv = 0;
        var total_neto = 0;
        total = parseFloat(total);
        igv = parseFloat(igv);
        total_neto = parseFloat(total_neto);
        for (var k = 0; k < cantidad_datos; k++) {
            var cod = respondar[k].codigo_producto;
            var descripcion = respondar[k].descripcion_producto;
            var cant = respondar[k].cantidad_tmp;
            var precio = respondar[k].precio_producto;
            var importe = respondar[k].importe_tmp;
            importe = parseFloat(importe);
            total = total + importe;

            $('#contenido_tmp_detfactura').append('<tr class="fila_pro_tmpdetfac" id="' + k + '"> ' +
                    '<td>' + (k + 1) + '</td> <td>' + cod + '</td> <td>' + descripcion + '</td>' +
                    '<td style="text-align:right;" > <a  class="btn btn-primary edit_tmp" id=' + k + ' >' + cant + '</a></td> ' + '<td style="text-align:right;" >' + getAddZerosToRight(precio) + '</td> ' + '<td style="text-align:right;" >' + getAddZerosToRight(importe) + '</td> ' +
                    '<td><a  class="btn btn-danger btn-xs remover_tmp" id=' + k + '  ><i class="fa  fa-remove"></i></a></td>' +
                    '</tr>'
                    );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +                 
        }//console.log("listIds "+listIdsprod.toString());
        var migv = $('#txt_igv_tmp').val();
        migv = "0." + migv;
        migv = parseFloat(migv);

        total = trunc(total, 2);
        total = parseFloat(total);

        igv = migv * total; //console.log("igv-"+igv);
        igv = trunc(igv, 2);//console.log("trunc igv-"+igv);
        igv = parseFloat(igv); //console.log("parse-"+igv);

        total_neto = total;
        total_neto = trunc(total_neto, 2);
        
        $('#span_total').text(trunc(getAddZerosToRight(total - igv), 2));
        $('#span_igv').text(getAddZerosToRight(igv));
        $('#span_Neto').text(getAddZerosToRight(total));
        $('#txtmonto_fc').val(total_neto);
        $('#lbl_total').text("S/. " + getAddZerosToRight(total_neto));

    }
    
    $("#txt_igv_tmp").keydown(function (e) {
        if (e.keyCode === 13) {
            var igv = $('#txt_igv_tmp').val();
            igv = igv.trim();
            if (igv.length == 0) {
                alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'Falta ingresar valor de igv.'}).show();
            } else {
                listar_tmp();
            }
        }
    });

    function addpro() {
        var igv = $('#txt_igv_tmp').val();
        igv = igv.trim();
        if (igv.length == 0) {
            $('#txt_igv_tmp').focus();
            alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'Falta ingresar valor de igv.'}).show();
            return;
        }
        if (idproducto == 0) {
            $('#txt_produc').focus();
            alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'Falta seleccionar un producto.'}).show();
        } else {
            if (existe_n_tmp(idproducto) == 1) {
                $('#txt_produc').focus();
                alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'El producto ya existe en la lista.'}).show();
            } else {
                var cantidad = $('#txtcantidad').val();
                cantidad = cantidad.trim();
                if (cantidad.length == 0) {
                    $('#txtcantidad').focus();//return;
                    alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'Falta ingresar cantidad.'}).show();
                } else {
                    if (catproduct != 1) {//alert(" PRODUCTO "); 
                        var stocka = parseInt($('#txtstocka').val());
                        var stockm = parseInt($('#txtstockm').val());
                        var cant = parseInt($('#txtcantidad').val());
                        if (cant > (stocka + stockm)) {
                            alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'La cantidad es mayor al stock.'}).show();
                        } else {
                            addpro_ayax();
                        }
                    } else {//alert(" SERVICIO ");
                        addpro_ayax();
                    }
                }
            }
        }
    }
    function addpro_ayax() {
        var cantidad = $('#txtcantidad').val();
        var precio = $('#txtprecio').val();
        cantidad = parseInt(cantidad);
        precio = trunc(precio, 2);
        precio = parseFloat(precio);
        var importe = 0;
        importe = parseFloat(importe);
        importe = precio * cantidad;
        importe = trunc(importe, 2);
        $.ajax({
            type: "POST",
            data: {id_pedido: n_pedido, id_producto: idproducto,
                cantidad: cantidad, importe: importe },
            url: 'insertar_temp',
            success: function (response) {
                alertify.success('se inserto exitosamente');
                listar_tmp();
                idproducto = 0;
                $('#txtcantidad').val("");
                $('#txtprecio').val("");
                $('#txtstocka').val("");
                $('#txtstockm').val("");
                $('#txt_produc').val("");
                $('#txt_produc').focus();
            }
        });
    }
    function del_listmp(listaa_datos) {
        $('.remover_tmp').click(function () {
            var id_a_eliminar = $(this).attr('id');
            var idtemp = listaa_datos[id_a_eliminar].id_tmp; //alert(idtemp);
            alertify.confirm("Ventas", "Desea Eliminar el producto <b> " + listaa_datos[id_a_eliminar].descripcion_producto + " </b> de la lista",
                    function () {
                        $.ajax({
                            type: "POST",
                            data: {id_temp: idtemp},
                            url: 'del_temp',
                            success: function (response) {
                                alertify.success('Se elimino exitosamente');
                                listar_tmp();
                            }
                        });
                    },
                    function () {//cancelo
                    });
        });
    }
    function traer_datos_tmp(listaa_datos) {

        $('.edit_tmp').click(function () {

            $('#buscarproducto').modal("show");

            var id_actualizar = $(this).attr('id');
            // mostar datos
            var idpro = listaa_datos[id_actualizar].id_producto;
            var stocka = 0;
            var stockm = 0;//id:idp,precio:preciop,sa:stock_a,sm:stock_m,cat:cat
            for (var i = 0; i < listproduc.length; i++) {
                if (idpro == listproduc[i].id) {
                    cat_tmp = listproduc[i].cat;//
                    stocka = listproduc[i].sa;
                    stockm = listproduc[i].sm;
                    pre_tmp = listproduc[i].precio;//alert(pre_tmp);
                    break;
                }
            }
            $('#txt_name_p').val(listaa_datos[id_actualizar].descripcion_producto);

            $('#txt_stock_p').val(stocka);
            $('#txt_stock_m_p').val(stockm);
            $('#txt_cantidad_p').val(listaa_datos[id_actualizar].cantidad_tmp);

            id_tmp = listaa_datos[id_actualizar].id_tmp;

        });
    }
    $('#modificarcantidad').click(function () {
        var cant = $('#txt_cantidad_p').val();
        cant = cant.trim();
        if (cant.length == 0) {
            alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'Falta ingresar cantidad.'}).show();
            return;
        }
        if (cant == 0) {
            alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'La cantidad no debe ser cero.'}).show();
            return;
        }
        cant = parseInt(cant);
        if (((parseInt($('#txt_stock_p').val()) + parseInt($('#txt_stock_m_p').val())) < cant) && (cat_tmp != 1)) {
            alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'La cantidad no debe ser mayor al stock.'}).show();
            return;
        }

        var cantidad = $('#txt_cantidad_p').val();
        var precio = pre_tmp;
        cantidad = parseInt(cantidad);
        precio = trunc(precio, 2);
        precio = parseFloat(precio);
        var importe = 0;
        importe = parseFloat(importe);
        importe = precio * cantidad;
        importe = trunc(importe, 2);

        $.ajax({
            type: "POST",
            data: {id_temp: id_tmp, cantidad: cant, importe: importe},
            url: 'actualizar_tmp',
            success: function (response) {
                alertify.success('se Actualizo la cantidad exitosamente');
                listar_tmp();
                $('#buscarproducto').modal("hide");
            }
        });
    });
    function existe_n_tmp(idp) {
        var sino = 0;
        for (var i = 0; i < lista_tmp.length; i++) {
            if (idp == lista_tmp[i].id_producto) {
                sino = 1;
                break;
            }
        }
        return sino;
    }
   

     $('#btn_guardar_venta').click(function(){
         var nombre= $('#txtname').val();
         var dni= $('#txtdniruc').val();
         var igv= $('#span_igv').text();
         var valor_neto= $('#span_Neto').text();
         var tipo_documento=$('#tipodoc').val();
         var valor_total_producto=$('#span_total').text();
        $.ajax({
            type: "POST",
            data: {snombre: nombre, sdni: dni,digv : igv, dvalor_neto:valor_neto,
                ntipo_doc:tipo_documento,id_pedido:n_pedido,id_user_e: id_user,id_cliente:idcliente,valor_total_producto_e:valor_total_producto},
            url: 'enviar_a_caja_vender',
            success: function (response) {
                // cojia y limpiaba dagtos                 
                alertify.success('se envio la venta exitosamente! ');
                location.reload();
            }
        });
         // llmar a actualizar pedido y actualizar
         generar_pedido();
         limpiar();
         // este es un nuevo comentario
         
      });
      
    function generar_pedido(){ 
        n = new Date();
        y = n.getFullYear();
        m = n.getMonth() + 1;
        if (m < 10) {
            m = "0" + m;
        }
        var anio = y;
        var mes = m;
        var xn = "" + anio + "" + mes;
        var str = xn.substring(2, 6) + "" + id_user;
        var nd = str.length;
        $.ajax({
            type: "GET",
            data: {id_user_e : $('#obj_id_user').val()},
            url: 'obtener_numero_pedido_last',
            success: function (response) {
                // cojia y limpiaba dagtos  
                var cantidadreg = response.numero_pedido;
                var zerofilled = ('000'+cantidadreg).slice(-3);
                n_pedido_str = str + "-" + zerofilled;
                $('#n_pedido_str').text(n_pedido_str);
                n_pedido=str +zerofilled;
                listar_tmp();
            }
        });
    }
    
    function limpiar(){
        $('#txtname').val('');
        $('#txtdniruc').val('');
        $('#tipodoc').val(1);
         $('#txtdireccion').val('');
        $('#txtcelular').val('');
        
    }
   
   
    function load() {
        generar_pedido();
        $("#btn_actualizar_cliente").hide();
        listar_producto("");
        listar_cliente();
        $("#txt_produc").focus();
    }

});








