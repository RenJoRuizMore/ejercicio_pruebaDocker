
$(document).ready(function () {
   
       var listar_pedido = [];
        var id_pedido_e;
        var lista_tmp;
        var n_pedido;
        // id_del usuario _caja
        var id_user_caja = $('#obj_id_user').val();
        // id_usaurio_vendedor
        var id_vendedor = 0;
        var numero_voucher;
        // sacar el texto
        var tipo_sustento;
        var nombre_banco;
        var tipo_tarjeta;
        var flag_tarjeta=0;
        // boleta 0 , factura 1:
        var tipo_documento_et=0;
        var  pago_con;
        var  vuelto;
        var  pago_total;
    load();
    
    // ===== AUTOCOMPLETE INPUT numero pedido
    $("#txtnumeropedido").autocomplete({
        minLength: 1,
        source: listar_pedido,
        focus: function (event, ui) {
           // $("#txtnumeropedido").val(ui.item.value);
            return false;
        },
        select: function (event, ui) { // seleccion
           $("#txtnumeropedido").val(ui.item.value);
            id_pedido_e = ui.item.value;
            get_id_user_vendedor(id_pedido_e);
            fmostrar_datos_pedido(id_pedido_e);
            return false;
        }
    });
    
    function flistar_pedido(){
        $.ajax({
            type: "GET",
            data: {},
            url: 'listar_numpedido_autocomplt',
            success: function (response) {
                var cantidad = response.lista_pedido_p.length;//
                var lista = response.lista_pedido_p;//alert(listaa);
                //console.log(lista[0].id_pedido);
                //cargar_listPed(cantidad,lista[0]);
                  while (listar_pedido.length > 0) //limpiar
                   listar_pedido.pop();
                            //array listaCliente
                  for (var i = 0; i < cantidad; i++) {
                        var num_pedido = lista[i].id_pedido;
                        listar_pedido.push({value: num_pedido});
                    }
                  //  alert("fdsf");
               console.log(listar_pedido);
            }
        });
    }
    $("#txtnumeropedido").on("keyup", function (event) {
        flistar_pedido();
    });
    // ===== fin de autocompletado
    // =============== tomapor el numero de pedido =================
    function fmostrar_datos_pedido(varid_pedido){
        $.ajax({
            type: "POST",
            data: {id_pedido_e: varid_pedido},
            url: 'listar_datos_pedidos',
            success: function (response) {
               var cantidad = response.lst_datos_pedido.length;
                console.log(cantidad);
                $('#lbl_total').text("S/. " +getAddZerosToRight(response.lst_datos_pedido[0].total));
                $('#lbl_cliente').text(response.lst_datos_pedido[0].nombre);
                $('#lbl_dni').text(response.lst_datos_pedido[0].dni);
                $('#span_total').text(getAddZerosToRight(response.lst_datos_pedido[0].subtototal));
                $('#span_igv').text(getAddZerosToRight(response.lst_datos_pedido[0].igv));
                $('#span_Neto').text(getAddZerosToRight(response.lst_datos_pedido[0].total));
                $('#txtmonto_fc').val(getAddZerosToRight(response.lst_datos_pedido[0].subtototal));
                pago_total =response.lst_datos_pedido[0].total;
                if (response.lst_datos_pedido[0].tipo_documento == 1 ) {
                    $('#txt_tipodoc').text("BOLETA");
                    tipo_documento_et=0;
                }
                else{
                    $('#txt_tipodoc').text("FACTURA");
                    tipo_documento_et=1;
                }
                $('#txt_igv_m').val(response.lst_datos_pedido[0].igv);
                n_pedido = varid_pedido;
                listar_tmp();
            }
        });
    }
    // agregar las filas  TMP DETALLE SALIDA
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
    
    // ================ controles ===============
    
    $('#cbx_tipodoc').change(function () {
        var index = $('#cbx_tipodoc option:selected').val();
        if (index == 1) {
            $('#btn_pago_tarjeta').hide();
            flag_tarjeta = 0;
        }
        // con tarjeta 
        else {
            $('#btn_pago_tarjeta').show();
            flag_tarjeta = 1;
        }
    });
    
    $('#btn_send_pago').click(function(){
      //  $(this).dialog('close');
        $('#buscarproducto').modal('hide');
        $("#btn_pago_tarjeta").removeClass("btn-warning"); 
        $("#btn_pago_tarjeta").addClass("btn-success"); 
        $("#btn_pago_tarjeta").text("Datos Correctos");
       numero_voucher= $('#txt_name_p').val();
        // sacar el texto
       tipo_sustento= $('#cbx_tipotarjeta').val();
       nombre_banco= $('#cbx_banco').val();
       tipo_tarjeta= $('#cbx_tajeta_visa').val();
     
    });
    
    // establecer codigo de vendedor
    function get_id_user_vendedor(numero_pedidos){
        var str=numero_pedidos + "";
        var extrastr= str.substring(4,5) ;
        id_vendedor= extrastr;
       
    }
    
    // ================ realizacion de venta ===============
    $('#btn_guardar_venta').click(function(){
          pago_con= $('#txt_pago_con').val(); ;
          vuelto= $('#txt_vuelto').val();
          var monto_t_producto_x =$('#span_total').text();
          var tipo_valor =$('#txt_tipodoc').text();
          
           $.ajax({
            type: "POST",
            data: {numero_pedido_e: id_pedido_e,tipo_pago_e:tipo_documento_et,
                   id_usuario_caja_e: id_user_caja ,turno_mov_caja_e:'Mañana' ,
                   flag_tarjeta : flag_tarjeta,numero_voucher_e : numero_voucher,
                   sustento_e: tipo_sustento ,banco_e: nombre_banco,tarjeta_e : tipo_tarjeta,
                   id_usuario_vendedor_e:id_vendedor, pago_con_e:pago_con , vuelto_e :vuelto,
                   monto_t_producto_e :monto_t_producto_x
            },
            url: 'registrar_venta',
            success: function (response) {
                alertify.success('se Actualizo el cliente exitosamente');
                //get_lastIdcliente();
            }
        });
       
        if (tipo_documento_et == 0) {
            
              $.ajax({
                    type: "POST",
                    data: {numero_pedido_e: id_pedido_e },
                    url: 'imprimir_venta',
                    success: function (response) {
                        var configuracion_ventana = "menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes";
                        var ventana = window.open('imprimir_venta.action','Boletas',configuracion_ventana); //abrimos una ventana vacía nueva
                         ventana.print(); //imprimimos la ventana
                       // alertify.success('se Actualizo el cliente exitosamente');
                        //listar_cliente();
                        //get_lastIdcliente();
                    }
                });
            
        }
        else{
          
            $.ajax({
              type: "POST",
              data: {numero_pedido_e: id_pedido_e },
              url: 'imprimir_factura',
              success: function (response) {
                  var configuracion_ventana = "menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes";
                  var ventana = window.open('imprimir_factura.action','Factura',configuracion_ventana); //abrimos una ventana vacía nueva
                   ventana.print(); //imprimimos la ventana
                 // alertify.success('se Actualizo el cliente exitosamente');
                  //listar_cliente();
                  //get_lastIdcliente();
              }
          });
            
        }
        // fin de realizar una venta 
        limpiar_cajas();
     
    });
    
    // ============== vuelto 
    
    $("#txt_pago_con").keydown(function (e) {
        if (e.keyCode === 13) {
          // total  pago_total
          var pago = $("#txt_pago_con").val();
          var result = pago - pago_total;
          $('#txt_vuelto').val(result);
          
        }
    });
    
    // ================= limpiar cajas =======================
    function limpiar_cajas(){
        
        $('#txtnumeropedido').val("");
        $('#txt_pago_con').val("");
        $('#txt_vuelto').val("");
        $('#cbx_tipodoc').val(1);
        $('#btn_pago_tarjeta').hide();
        $('#lbl_total').text("S/. 0.0");
        $('#txt_tipodoc').text("");
        $('#lbl_cliente').text("");
        $('#lbl_dni').text("");
        n_pedido=0;
        listar_tmp();
    }
    
    
    
    
    function load() {
        flistar_pedido();
        $('#btn_pago_tarjeta').hide();
        $("#btn_pago_tarjeta").addClass("btn-warning"); 
    }
    
});