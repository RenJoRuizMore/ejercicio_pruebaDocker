/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// jquery
$(document).ready(function(){
    
    // variables    
    var p_id_categoria=0;
    var p_id_producto=0;
    var p_id_persona=0;
    
    //var lista_name_productos;
    //var listaproductos;
    
    
    //---- DATA LIST
    var Txtcod_producto_s;
    var Txtnombre_producto_s;
    var id_producto_s=0;
    var id_tmp_detsal;    
    var listaa;// lista de productos
    var listaa_cat;
    var listaa_prove;
    //------ detalle factura TMP
    var listIdsprod;// lista de ids de productos
    
    //-----
    var p_id_proveedor=0;
    
    //----- sub-tab edit factura
    var id_Factura=0;
    var listIdsprod_detfact;
    //funcion inicial    
    load();
            
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    //:::::::::::::::::::::::::::::::::::::::::: F A C T U R A   C O M P R R A ************************************:+:
     
    
    /////////////////////////////////////
    //                                  //
    //      TAB NUEVA FACTURA           //
    //                                  ///////////////////////////////////////////////////////////////////////////////////////////
    
    //---------------------------------  DATALIST ---------------------------------------------------------------------------------
    function clickproductos(){
        $('#txt_produc').on("input", function() {
            var usertext = $(this).val();
            $('#listproductos').find("option").each(function()
            {
                if($(this).val()=== usertext){
                    var ids=$(this).attr('id');
                    //console.log("id -> "+ids);
                    //console.log(listaa);
                    id_producto_s=listaa[ids-1].id_producto;
                    Txtcod_producto_s=listaa[ids-1].codigo_producto;;
                    Txtnombre_producto_s=listaa[ids-1].descripcion_producto;;
                    
                    $('#txtstock').val(listaa[ids-1].stock_a_producto);
                    //$('#txtprecio').val(getAddZerosToRight(listaa[ids-1].precio_producto));
                    $('#txtprecio').val("0.00");
                    $("#txtcantidad").val("1");
                    $("#txtprecio").focus();                    
                }
            });
        });
    }
    function listar_producto__(str) { 
        $.ajax({
            type:"GET",
            data:{texto_busqueda_pro:str,allproductos:1,productservis:0},
            url: 'busqueda_producto',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_producto.length;//console.log("c"+cantidad);
                listaa=response.lista_busqueda_producto;
                // remover el contenido
                agregar_producto_datalist(0,0,cantidad,listaa);
                //agregar_producto_datalist_detsal(cantidad,listaa);
            }           
        });
    }
    // agregar las filas A DATALIST 
    function agregar_producto_datalist(id,limite,cantidad_datos,respondar){
        //var fin= id*limite;
        //var inicio=((fin-limite)+1);
        //$('.fila_pro').remove();//clase CSS q remove todas las acitvite
        for(var k=0;k<cantidad_datos ;k++){
            var cod = respondar[k].codigo_producto;
            var descripcion = respondar[k].descripcion_producto ;
            $('#listproductos').append('<option  id="'+(k+1)+' "value="'+cod+' - '+descripcion+'"></option>' );
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
    //---------------------------------  LIST TMP DETALLE FACTURA --

    function limpiar_detallefacturatmp(){
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{},
                url: 'limpiar_detallefacturatmp',
                success: function(response){
                    //console.log("se limpio lista factura");//alertify.success('Se actualizo exitosamente');
                }                      
        });      
    }
    // LISTAR tmp_detsalida
    function listar_tmp_detfactura() { 
        $.ajax({
            type:"GET",
            data:{},
            url: 'listar_tmp_detfactura',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_detfactura.length;//console.log("count lista_detsalida : "+cantidad);
                var listaa=response.lista_detfactura;
                agregar_filas_tmp_detfactura(cantidad,listaa);
                // click a un determinado boton                
                //traer_datos_tmp_detfactura(listaa);
                del_listmp(listaa);
            }           
        });
    }
    // agregar las filas  TMP DETALLE SALIDA
    function agregar_filas_tmp_detfactura(cantidad_datos,respondar){        
        listIdsprod=[];//vaciamos lista ids productos
         $('.fila_pro_tmpdetfac').remove();//clase CSS q remove todas las acitvite
        var total=0;
        var igv=0;
        var total_neto=0;
        total = parseFloat(total);
        igv = parseFloat(igv);
        total_neto = parseFloat(total_neto);
        for(var k=0;k<cantidad_datos ;k++){
            listIdsprod.push(respondar[k].id_producto);
            var cod = respondar[k].cod_producto;
            var descripcion = respondar[k].nombre_producto ;
            var cant = respondar[k].cantidad_detallefactura ;
            var precio = respondar[k].precio_detallefactura ;
            var importe = respondar[k].importe_detallefactura ;
            importe = parseFloat(importe);
            total = total + importe;
            
            $('#contenido_tmp_detfactura').append('<tr class="fila_pro_tmpdetfac" id="'+k+'"> '+
                    '<td>' + (k+1) + '</td> <td>' + cod + '</td> <td>' + descripcion + '</td>' +
                    '<td style="text-align:right;" >' + cant + '</td> ' + '<td style="text-align:right;" >' + getAddZerosToRight(precio) + '</td> ' + '<td style="text-align:right;" >' + getAddZerosToRight(importe) + '</td> ' +
                    '<td><a  class="btn btn-danger btn-xs remover_tmp_detfactura" id='+k+'  ><i class="fa  fa-remove"></i></a></td>' +                        
                    '</tr>'
            );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +                 
        }//console.log("listIds "+listIdsprod.toString());
        var migv= $('#txt_igv_tmp').val();
        migv = "0."+migv;        
        migv = parseFloat(migv);
        
        total= trunc(total,2);
        total = parseFloat(total);
        
        //console.log("migv-"+migv);
        igv =  migv * total; //console.log("igv-"+igv);
        igv = trunc(igv,2);//console.log("trunc igv-"+igv);
        igv = parseFloat(igv); //console.log("parse-"+igv);
         
        total_neto = total;
        total_neto = trunc(total_neto,2);
        var fsubtotal= trunc(total_neto - igv,2);
        $('#span_total').text(getAddZerosToRight(fsubtotal));
        $('#span_igv').text(getAddZerosToRight(igv));
        $('#span_Neto').text(getAddZerosToRight(total_neto));
        $('#txtmonto_fc').val(total_neto);
        
    }
    $("#txt_igv_tmp").keydown(function (e) {
        if (e.keyCode === 13) {
            var igv=$('#txt_igv_tmp').val();
            igv = igv.trim();
            if(igv.length==0){
                alertify.dialog('alert').set({transition:'flipy',title:'Factura Compra',message: 'Falta ingresar valor de igv.'}).show();
            }else{
                listar_tmp_detfactura();
            }
        }
    });
    

    // agregar producto a detalle factura TMP
    $('#add_pro_tmp').click(function(){//console.log(id_producto_s);
        
        var igv=$('#txt_igv_tmp').val();
        igv = igv.trim();
        if(igv.length==0){
            $('#txt_igv_tmp').focus();
            alertify.dialog('alert').set({transition:'flipy',title:'Factura Compra',message: 'Falta ingresar valor de igv.'}).show();
            return;
        }
        
        if(id_producto_s==0){$('#txt_produc').focus();
            alertify.dialog('alert').set({transition:'flipy',title:'Factura Compra',message: 'Falta seleccionar un producto.'}).show();
        }else{
            if(existe_n_tmpdetallefact(id_producto_s)==1){$('#txt_produc').focus();
                alertify.dialog('alert').set({transition:'flipy',title:'Factura Compra',message: 'El producto ya existe en la lista.'}).show();                
            }else{
                var cantidad=$('#txtcantidad').val();
                cantidad= cantidad.trim();
                if(cantidad.length==0){ $('#txtcantidad').focus();//return;
                    alertify.dialog('alert').set({transition:'flipy',title:'Factura Compra',message: 'Falta ingresar cantidad.'}).show();
                }else{
                    var precio=$('#txtprecio').val();
                    precio= precio.trim();
                    
                    if( precio.length==0 ){$('#txtprecio').focus();
                        alertify.dialog('alert').set({transition:'flipy',title:'Factura Compra',message: 'Falta ingresar precio.'}).show();
                    }else{
                        console.log("se agregoooo");
                        add_listmp_detfac();
                        id_producto_s=0;
                        $('#txtcantidad').val("");
                        $('#txtprecio').val("");
                        $('#txt_produc').val("");
                        $('#txt_produc').focus();                    
                    }
                } 
            }    
        }        
    });    
    function add_listmp_detfac(){
        // ---cargar datos---        // ---cargar datos---
        var nombre=Txtnombre_producto_s;
        var cod=Txtcod_producto_s;
        var cantidad=$('#txtcantidad').val();
            
        var precio=$('#txtprecio').val();
        cantidad = parseInt(cantidad);
        precio= trunc(precio,2);        
        precio = parseFloat(precio);
        var importe = 0;    
        importe = parseFloat(importe);
        importe = precio * cantidad ;
        importe= trunc(importe,2);
                   //console.log(nombre+" - "+cod+" - "+cantidad);
         $.ajax({
            type:"POST",
            data:{id_producto_df:id_producto_s,cod_producto_df:cod,nombre_producto_df:nombre,                
                  cantidad_df:cantidad,precio_df:precio,importe_df:importe
                  },
                url: 'add_listmp_detfac',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    listar_tmp_detfactura();
                    //limpiar_p();                  
                }                      
        });        
    }
    function del_listmp(listaa_datos){
        $('.remover_tmp_detfactura').click(function(){
            
            var id_a_eliminar = $(this).attr('id');
            alertify.confirm("Factura Compra","Desea Eliminar el producto <b> "+listaa_datos[id_a_eliminar].nombre_producto+" </b> de la lista",            
            function(){
                $.ajax({
                    type:"POST",
                    data:{id_tmp_detsal:id_a_eliminar},
                        url: 'del_listmpdetfac',
                        success: function(response){
                            alertify.success('Se elimino exitosamente');
                            listar_tmp_detfactura();        
                        }                      
                });                
            },
            function(){//cancelo
            });
           
        });       
    }    
    function existe_n_tmpdetallefact(idp){
        var sino=0;
        for(var i=0;i<listIdsprod.length ;i++){
            if(idp==listIdsprod[i]){
                sino=1;
                break;
            }                
        }        
        return sino;
    }
    
    
    
    ////// CREAR FACTURAAAAAA
     $('#crearFactura').on('click',function(){
        
        var num=$('#txtnumero_fc').val();
        num=num.trim();
        if(num.length==0){$('#txtnumero_fc').focus();alertify.error('Debe ingresar Numero factura');return};        
        
        var fecha=$('#txfecha_fc').val();
        fecha=fecha.trim();
        if(fecha.length==0){$('#txfecha_fc').focus();alertify.error('Debe ingresar Fecha');return};
        
        if(p_id_proveedor == 0){
            alertify.dialog('alert').set({transition:'flipy',title:'Factura Compra',message: 'Falta seleccionar proveedor.'}).show();
            return;
        }
        
        if(listIdsprod.length==0){
            alertify.dialog('alert').set({transition:'flipy',title:'Factura Compra',message: 'Falta ingresar productos a la lista.'}).show();
            return;
        }
        
        //return;
        //obj_id_user
        var id_user=$('#obj_id_user').val();;//dgdfgdfggdfgdfgfgdfgdfgdfgdfgdfgdgdgdgdgdfgdgdgdfgdgdg;gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg
        var monto=$('#txtmonto_fc').val();
        
         // crear objeto response
        $.ajax({
            type:"POST",
            data:{id_proveedor_fact: p_id_proveedor , id_usuario_fact: id_user, txt_numero_fact: num , txt_fecha_fact :fecha, 
                  txt_monto_fact: monto },
                url: 'insertar_factura',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    //limpiar_detallefacturatmp();
                    p_id_proveedor=0;
                    $('#txfecha_fc').val("");
                    $('#txtnumero_fc').val("");
                    
                     $('#txt_ruc').val("");
                    $('#txt_nombre').val("");
                     $('#txt_cel').val("");
                    listar_tmp_detfactura();
                    
                    //listar_tmp_detsalida();
                    //obtener_numsal();
                    //getfechaAct();
               }                      
        });        
    });    
    
    
    /////////////////////////////////////
    //                                  //
    //      LISTA FACTURA               //
    //                                  ///////////////////////////////////////////////////////////////////////////////////////////
    
    
    $('#texto_busqueda_factura').on('keyup',function(){       
        var valor_texto=$(this).val();
        listar_factura(valor_texto);
    });  
    // busquedad filtrada Stock minimo
    function listar_factura(str) {
        $.ajax({
            type:"GET",
            data:{texto_busqueda_factura:str},
            url: 'busqueda_factura',
            success: function(response){//console.log("-- "+response.lista_busqueda_factura);
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_factura.length;//console.log("c"+cantidad);
                var listaa=response.lista_busqueda_factura;
                var limite=10;
                $('.header_lis_factura').remove();
                // agregar los botones
                agregar_botones_lista_factura(limite,cantidad);
                // remover el contenido
                agregar_filas_factura(1,limite,cantidad,listaa);
                // click a un determinado boton
                $('.hojas_factura').click(function(){
                    var ids=$(this).attr('id');
                    //alert(ids);
                    $('.botones_factura').removeClass('active');
                    $('#lista_btn_factura'+ids).addClass('active');
                    agregar_filas_factura(ids,limite,cantidad,listaa);
                    // se deben mostrar la lista segun sea su orden
                    traer_datos_detfactura(listaa);
                });
                traer_datos_detfactura(listaa);
            }           
        });
    }
    // botones  PAGINADO // 
    function agregar_botones_lista_factura(limite,cantidad_datos) {
            var cbtn=0;
           // var num_fila=0;
            if(cantidad_datos%limite==0){
                cbtn=cantidad_datos/limite;
            }else{
                cbtn=(cantidad_datos/limite)+1;
            }
            // remover todo lo que             
             $('.hojas_factura').remove();
            // agregar botones
            //$('#boton_lista').append('<li class="header_lis disabled"><a href="#!"><i class="fa fa-angle-double-left"></i></a></li>');
            // agregar  la cantidad 
            for (var k = 1; k <= cbtn; k++) {
                $('#boton_lista_factura').append('<li class="botones_factura" id="lista_btn_factura'+k+'"><a class="hojas_factura" id="' + k + '" href="#!">' + k + '</a></li>');
            }
            //$('#boton_lista').append('<li class="header_lis"><a  href="#!"><i class="fa fa-angle-double-right"></i></a></li>');
                       
        }
    // agregar las filas 
    function agregar_filas_factura(id,limite,cantidad_datos,respondar){
        var fin= id*limite;
        var inicio=((fin-limite)+1);
         $('.fila_factura').remove();//clase CSS q remove todas las acitvite
        for(var i=inicio,k=inicio-1;i<=fin;i++,k++){
            if (k < cantidad_datos) {
                
                var cod = respondar[k].numero_factura;
                var proveedor = respondar[k].nombre_proveedor ;
                var monto = respondar[k].monto_factura ;                
                var fecha = respondar[k].fecha_factura ;
                fecha = fecha.substring(8,10)+"-" +fecha.substring(5,7)+"-"+fecha.substring(0,4);
                var usuario = respondar[k].nombre_usuario ;
                var stado = respondar[k].estado_factura ;
                if(stado == 1 )stado= '<span class="label label-success">Habilitado</span>';
                else stado = '<span class="label label-danger">Inactivo</span>'   ;
                
                $('#contenido_factura').append('<tr class="fila_factura" id="fila_factura_'+k+'"> '+
                        '<td>' + i + '</td> <td>' + cod + '</td> <td>' + proveedor + '</td>' +
                        '<td>' + monto + '</td> <td>' + fecha + '</td> <td>' + usuario + '</td> <td>' + stado + '</td>' +
                        '<td><a  class="btn btn-primary editable_factura btn-xs btn_del_factura" id='+k+'  ><i class="fa fa-edit"></i></a></td>' +                        
                        '</tr>'
                        );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
                $('#contenido_factura').append('<tr class="fila_factura"> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>');  
            }            
        }
    }
    //
    function traer_datos_detfactura(listaa_datos){
        
        $('.editable_factura').click(function(){
            
            $('#listar_factura').hide();
            $('#factura_edit').show();
            
            var id_actualizar = $(this).attr('id');
            // mostar datos
            $('#txtnumero_fc_edit').val(listaa_datos[id_actualizar].numero_factura);
            //$('#txtmonto_fc_edit').val(listaa_datos[id_actualizar].monto_factura);
            $('#txfecha_fc_edit').val(listaa_datos[id_actualizar].fecha_factura);
            $('#cbx_stado_edit').val(listaa_datos[id_actualizar].estado_factura);
            $('#cbx_proveedores').val(listaa_datos[id_actualizar].id_proveedor);//console.log(listaa_datos[id_actualizar].id_proveedor)
            
            id_Factura = listaa_datos[id_actualizar].id_factura;console.log(id_Factura);
            listar_detallefactura(id_Factura);
        
        });
    }
    
    $('#volver').click(function(){
        var str=$('#texto_busqueda_factura').val();
        listar_factura(str);
        $('#factura_edit').hide();    
        $('#listar_factura').show();
    }); 
    
    
    
    //=========================  E D I T
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////777
    // busquedad filtrada  x id_salida detallesalida editttttttt
    function listar_detallefactura(str) { 
        $.ajax({
            type:"GET",
            data:{id_factura:str},
            url: 'listar_detallefactura',
            success: function(response){
                var cantidad=response.lista_busqueda_detallefactura.length;//console.log("c"+cantidad);
                var listaa=response.lista_busqueda_detallefactura;
                agregar_filas_detallefactura(cantidad,listaa);
                del_detallefactura(listaa);
            }           
        });
    }
    // agregar las filas 
    function agregar_filas_detallefactura(cantidad_datos,respondar){
        var total=0;
        var igv=0;
        var total_neto=0;
        
        igv = parseFloat(igv);
        total = parseFloat(total) ;
        total_neto = parseFloat(total_neto);
        $('.fila_pro_e').remove();//clase CSS q remove todas las acitvite
        listIdsprod_detfact=[];
        for(var k=0 ; k < cantidad_datos ; k++){
            listIdsprod_detfact.push(respondar[k].id_producto);            
            var cod = respondar[k].cod_producto;
            var descripcion = respondar[k].nombre_producto ;
            var cantidad = respondar[k].cantidad_detallefactura ;
            var precio = respondar[k].precio_detallefactura ;
            var importe = respondar[k].importe_detallefactura ;
            importe = parseFloat(importe);
            total = total + importe;
                        
            $('#contenido_detfactura').append('<tr class="fila_pro_e" id="'+k+'"> '+
                '<td>' + (k+1) + '</td> <td>' + cod + ' </td> <td>' + descripcion + '</td>' +
                '<td style="text-align:right;" >' + cantidad + '</td> '+'<td style="text-align:right;" >' + getAddZerosToRight(precio) + '</td> '+ '<td style="text-align:right;" >' + getAddZerosToRight(importe) + '</td> '+
                '<td><a  class="btn btn-danger btn-xs del_detallefactura_e" id='+k+'  ><i class="fa fa-remove"></i></a></td>' +                        
                '</tr>'
            );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' + 
            var migv= $('#txt_igv_tmp_e').val();
            migv = "0."+migv;        
            migv = parseFloat(migv);

            total= trunc(total,2) ;
            total = parseFloat(total);

            //console.log("migv-"+migv);
            igv =  migv * total; //console.log("igv-"+igv);
            igv = trunc(igv,2);//console.log("trunc igv-"+igv);
            igv = parseFloat(igv); //console.log("parse-"+igv);

            total_neto = total ;
            total_neto = trunc(total_neto,2);
            var fsubtotal= total_neto - igv;
            $('#span_total_e').text(getAddZerosToRight(fsubtotal));
            $('#span_igv_e').text(getAddZerosToRight(igv));
            $('#span_Neto_e').text(getAddZerosToRight(total_neto));
            $('#txtmonto_fc_edit').val(total_neto);
        }
    }
    function del_detallefactura(listaa_datos){
        $('.del_detallefactura_e').click(function(){
            
            var id_a_eliminar = $(this).attr('id');
            var id_detallefactura = listaa_datos[id_a_eliminar].id_detallefactura;
            alertify.confirm("Editar Factura ","Desea Eliminar el producto <b> "+listaa_datos[id_a_eliminar].nombre_producto+" </b> de la lista.",            
            function(){
                // mostar datos            
                //id_tmp_detsal=listaa_datos[id_actualizar].id_producto;
                $.ajax({
                    type:"POST",
                    data:{id_detfactura:id_detallefactura},
                        url: 'eliminar_detallefactura',
                        success: function(response){
                            alertify.success('Se elimino exitosamente');
                            listar_detallefactura(id_Factura);   
                        }                      
                });                
            },
            function(){//cancelo
            });
           
        });       
    }
    $('#add_pro_table').click(function(){//console.log(id_producto_s);
        
        var igv=$('#txt_igv_tmp_e').val();
        igv = igv.trim();
        if(igv.length==0){
            $('#txt_igv_tmp').val();
            alertify.dialog('alert').set({transition:'flipy',title:'Editar Factura Compra',message: 'Falta ingresar valor de igv.'}).show();
            return;
        }
        
        if(id_producto_s==0){$('#txt_produc').focus();
            alertify.dialog('alert').set({transition:'flipy',title:'Editar Factura Compra',message: 'Falta seleccionar un producto.'}).show();
        }else{
            if(existe_n_tabledetallefact(id_producto_s)==1){$('#txt_produc').focus();
                alertify.dialog('alert').set({transition:'flipy',title:'Editar Factura Compra',message: 'El producto ya existe en la lista.'}).show();                
            }else{
                var cantidad=$('#txtcantidad').val();
                cantidad= cantidad.trim();
                if(cantidad.length==0){ $('#txtcantidad').focus();//return;
                    alertify.dialog('alert').set({transition:'flipy',title:'Editar Factura Compra',message: 'Falta ingresar cantidad.'}).show();
                }else{
                    var precio=$('#txtprecio').val();
                    precio= precio.trim();
                    
                    if( precio.length==0 ){$('#txtprecio').focus();
                        alertify.dialog('alert').set({transition:'flipy',title:'Editar Factura Compra',message: 'Falta ingresar precio.'}).show();
                    }else{
                        console.log("se agregoooo detfact");
                        insertar_detfac();                  
                    }
                } 
            }    
        }        
    });
    function existe_n_tabledetallefact(idp){
        var sino=0;
        for(var i=0;i<listIdsprod_detfact.length ;i++){
            if(idp==listIdsprod_detfact[i]){
                sino=1;
                break;
            }                
        }        
        return sino;
    }
    function insertar_detfac(){
        //var nombre=Txtnombre_producto_s;
        //var cod=Txtcod_producto_s;
        var cantidad=$('#txtcantidad').val();            
        var precio=$('#txtprecio').val();
        cantidad = parseInt(cantidad);
        precio= trunc(precio,2);        
        precio = parseFloat(precio);
        var importe = 0;
        importe = parseFloat(importe);
        importe = cantidad * precio;
        importe= trunc(importe,2);
        $.ajax({
            type:"POST",
            data:{id_factura:id_Factura,id_producto_df:id_producto_s,cantidad_df:cantidad,precio_df:precio,importe_df:importe},
                url: 'insertar_detallefactura',
                success: function(response){//alert("2"+gethoraAct());
                    alertify.success('se inserto exitosamente');
                    //alert("enytoooo");
                    listar_detallefactura(id_Factura);
                    //console.log("se agregoooo detalle_salida");
                    id_producto_s=0;
                    $('#txtcantidad').val("");
                    $('#txtprecio').val("");
                    $('#txt_produc').val("");
                    $('#txt_produc').focus();                                     
               }                      
        }); 
    }
    $('#modificarFactura').click(function(){
        actualizar_Factura(id_Factura);
    });
    function actualizar_Factura(id_){
        var p_id_proveedor_edit=$('#cbx_proveedores').val();
        var num=$('#txtnumero_fc_edit').val();
        var monto=$('#txtmonto_fc_edit').val();
        var estado=$('#cbx_stado_edit').val();
        var fecha=$('#txfecha_fc_edit').val();
        fecha=fecha.trim();
        if(fecha.length==0){alertify.error('Debe ingresar Fecha de la factura');return};
        //fecha = fecha+" "+gethoraAct();
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{id_proveedor_fact: p_id_proveedor_edit ,  txt_numero_fact: num , txt_fecha_fact :fecha, 
                  txt_monto_fact: monto, stado:estado,id_factura:id_
                  },
                url: 'actualizar_Factura',
                success: function(response){
                    alertify.success('Se actualizo exitosamente');
                }                      
        });      
    }
    $("#txt_igv_tmp_e").keydown(function (e) {
        if (e.keyCode === 13) {
            var igv=$('#txt_igv_tmp_e').val();
            igv = igv.trim();
            if(igv.length==0){
                alertify.dialog('alert').set({transition:'flipy',title:'Editar Factura Compra',message: 'Falta ingresar valor de igv.'}).show();
            }else{
                listar_detallefactura(id_Factura);
            }
        }
    });
    //::::::::::::::::::::::::::::::::::::::::::  C A T E G O R I A ************************************:+:
 
//    $('#txt_busqueda_categoria').on('keyup',function(){       
//        var valor_texto=$(this).val();
//        listar_categoria(valor_texto);
//    });    
//    
//    // busquedad filtrada CATEGORIA
//    function listar_categoria(str) { 
//        $.ajax({
//            type:"GET",
//            data:{texto_busqueda_categoria:str},
//            url: 'busqueda_categoria',
//            success: function(response){
//                // cojia y limpiaba dagtos 
//                listaa_cat=response.lista_busqueda_categoria;                
//            }           
//        });
//    }
//    

    $('#btn_agregar_c').click(function(){
        $('#crearcategoria').show();
        $('#modificarcategoria').hide();
        $('#div_estado_c').hide();  
        $('#span_actualizar_c').hide();  
        $('#span_nuevo_c').show();
        limpiar_c();
    });
    
    
    // registrar categoria
    $('#crearcategoria').on('click',function(){
        var desc=$('#txt_descripcion_c').val();
        desc=desc.trim();
        if(desc.length==0){alertify.error('Debe ingresar descripcion');return};
        if(categoria_igual(desc,0)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Categoria',message: 'La categoria ya existe.'}).show(); return;}
        // crear objeto response
        $.ajax({
            type:"POST",
            data:{txt_descripcion_c:desc},
                url: 'insertar_categoria',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    //listar_categoria('');
                    fill_cbx_categoria();
                    limpiar_c();                   
               }                      
        });        
    });    
    
    function limpiar_c(){
        $('#txt_descripcion_c').val("");
        $('#cbx_estado_c').val("1");
    }
    
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    //::::::::::::::::::::::::::::::::::::::::::  P R O D U C T O ************************************:+:
    
    //en mostrar divs de mostrador    
    $('#Txt_en_mostrador_producto').on('change',function(){       
        var str=$('#Txt_en_mostrador_producto').val();
        ocultar_mostrr_divs(str);
    });
    function ocultar_mostrr_divs(str){
        if(str== 1 ){
            $('#div_stock_mostrador').show();
            $('#div_stock_min_mostrador').show();
        }else{
            $('#div_stock_mostrador').hide();
            $('#div_stock_min_mostrador').hide();
        }
    }
    
    //LLENAR COMBO CATEGORIA
    function fill_cbx_categoria(){
        $.ajax({
            type:"GET",
            url: 'fill_combo_categoria',
            success: function(response){
            var cant_list=response.lista_categorias.length;
            listaa_cat = response.lista_categorias;
                 $('.opciones').remove();// remover los datos
                 for(var x=0;x<cant_list;x++){                           
                     $('#Cbx_id_categoria').append( '<option class="opciones" value="'+response.lista_categorias[x].id_categoria+'">'+response.lista_categorias[x].descripcion_categoria+'</option>' );
                }
            }
        });       
    }
    
//    $('#txt_busqueda_producto').on('keyup',function(){       
//        var valor_texto=$(this).val();
//        listar_producto(valor_texto);
//    });  
    // busquedad filtrada PRODUCTO
        
    
    $('#btn_agregar_p').click(function(){
        $('#crearpro').show();
        $('#modificarpro').hide();
        $('#div_estado_p').hide();  
        $('#span_actualizar_p').hide();  
        $('#span_nuevo_p').show(); 
        limpiar_p();
    });    
    $('#crearpro').on('click',function(){
        // ---cargar datos---
        var cat=$('#Cbx_id_categoria').val();
        var nombre=$('#Txt_descripcion_producto').val();
        var cod=$('#Txt_codigo_producto').val();
        var precio=$('#Txt_precio_producto').val();
        var und=$('#Txt_unidades_producto').val();
        //var stock=$('#Txt_stock_producto').val();
        var stock_a=$('#Txt_stock_a_producto').val();
        var stock_m=$('#Txt_stock_m_producto').val();
        var stock_min=$('#Txt_stock_minimo_producto').val();
        var stock_min_m=$('#Txt_stock_minimo_mostrador_producto').val();
        var en_m=$('#Txt_en_mostrador_producto').val();
        nombre=nombre.trim();
        cod=cod.trim();
        precio=precio.trim();
        und=und.trim();
        //stock=stock.trim();
        stock_a=stock_a.trim();
        stock_m=stock_m.trim();
        stock_min=stock_min.trim();
        stock_min_m=stock_min_m.trim();
        if(cod.length==0){alertify.error('Debe ingresar Codigo del producto');return};
        if(cat==""){alertify.error('Debe seleccionar Categoria del producto');return};
        if(nombre.length==0){alertify.error('Debe ingresar Descripcion del producto');return};
        if(precio.length==0){alertify.error('Debe ingresar Precio del producto');return};
        if(und.length==0){alertify.error('Debe ingresar Unidad del producto');return};
        //if(stock.length==0){alertify.error('Debe ingresar Stock del producto');return};
        if(stock_a.length==0){stock_a.error('Debe ingresar Stock de almacen');return};
        if(stock_m.length==0){alertify.error('Debe ingresar Stock de mostrador');return};
        if(stock_min.length==0){alertify.error('Debe ingresar Stock minimo');return};
        if(stock_min_m.length==0){alertify.error('Debe ingresar Stock minimo en mostrador');return};
        
        if(codigo_o_nombre_igual(1,cod,0)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Producto',message: 'El Codigo ya existe.'}).show(); return;}
        if(codigo_o_nombre_igual(2,nombre,0)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Producto',message: 'El nombre ya existe.'}).show(); return;}
        
             
        // ------finish datos--
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{Txt_codigo_producto:cod,Cbx_id_categoria:cat,Txt_descripcion_producto:nombre,                
                  Txt_precio_producto:precio,Txt_unidades_producto:und,Txt_stock_a_producto:stock_a,Txt_stock_m_producto:stock_m,
                  Txt_stock_minimo_producto:stock_min,Txt_stock_minimo_mostrador_producto:stock_min_m,Txt_en_mostrador_producto:en_m
                  },
                url: 'insertar_producto',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    //listar_producto("");
                    //data lista
                    
                    listar_producto__("");
                    clickproductos();
                    id_producto_s=0;
                    $('#txtcantidad').val("");
                    $('#txtprecio').val("");
                    $('#txt_produc').val("");
                    
                    limpiar_p();                  
                }                      
        });        
    });
    
    
    function limpiar_p(){
        $('#Txt_codigo_producto').val("");
        $('#Cbx_id_categoria').val("");
        $('#Txt_descripcion_producto').val("");
        $('#Txt_precio_producto ').val("0.0");
        $('#Txt_unidades_producto').val("");
        //$('#Txt_stock_producto').val("0");
        $('#Txt_stock_a_producto').val("0");
        $('#Txt_stock_m_producto').val("0");
        $('#Txt_stock_minimo_producto').val("0");
        $('#Txt_stock_minimo_mostrador_producto').val("0");
        $('#Txt_en_mostrador_producto').val("0");        
        $('#Txt_estado_producto').val("1");
        
        $('#div_stock_mostrador').hide();
        $('#div_stock_min_mostrador').hide();
    }
    
    
        
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    //::::::::::::::::::::::::::::::::::::::::::  P R O V E E D O R  ************************************:+:
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    
    
    
    $('#texto_busqueda').on('keyup',function(){       
        var valor_texto=$(this).val();
        listar(valor_texto);
    });
    // busquedad filtrada
    // busqueda
    function listar(str) { 
        $.ajax({
            type:"GET",
            data:{texto_busqueda:str},
            url: 'busqueda_proveedor',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda.length;
                listaa_prove=response.lista_busqueda;
                var limite=4;
                $('.header_lis').remove();
                // agregar los botones
                agregar_botones_lista(limite,cantidad);
                // remover el contenido
                agregar_filas(1,limite,cantidad,listaa_prove);
                fill_cbx_provee(cantidad,listaa_prove);
               // click a un determinado boton
               $('.hojas').click(function(){
                  var ids=$(this).attr('id');
                  //alert(ids);
                  $('.botones').removeClass('active');
                 $('#lista_btn'+ids).addClass('active');
                   agregar_filas(ids,limite,cantidad,listaa_prove);
                 // alert($(this).attr('id'));
                 // se deben mostrar la lista segun sea su orden
                      traer_datos(listaa_prove);
                      //eliminar_objetos(listaa_prove);
               });
               traer_datos(listaa_prove);
               //eliminar_objetos(listaa_prove);
            }
           
        }) ;
    }
    
       // botones // limite y x para el numero
    function agregar_botones_lista(limite,cantidad_datos) {
            var cbtn=0;
           // var num_fila=0;
            if(cantidad_datos%limite==0){
                cbtn=cantidad_datos/limite;
            }
            else{
                cbtn=(cantidad_datos/limite)+1;
            }
            // remover todo lo que 
            
             $('.hojas').remove();
            // agregar botones
            //$('#boton_lista').append('<li class="header_lis disabled"><a href="#!"><i class="fa fa-angle-double-left"></i></a></li>');
            // agregar  la cantidad 
            for (var k = 1; k <= cbtn; k++) {
                $('#boton_lista').append('<li class="botones" id="lista_btn'+k+'"><a class="hojas" id="' + k + '" href="#!">' + k + '</a></li>');

            }
            //$('#boton_lista').append('<li class="header_lis"><a  href="#!"><i class="fa fa-angle-double-right"></i></a></li>');
            
            // finish de agregar :p
            // obtener el id presionado
           
        }
    
   // agregar las filas 
    function agregar_filas(id,limite,cantidad_datos,respondar){
        var fin= id*limite;
        var inicio=((fin-limite)+1);
        //alert("el fin es: "+fin+ " el incio es: "+ inicio);
        // elimar 
        //console.log(respondar);
         $('.fila').remove();
         //antes remover todas las acitvite
        for(var i=inicio,k=inicio-1;i<=fin;i++,k++){
        if (k < cantidad_datos) {
            var nombre_completo = respondar[k].nombre_proveedor ;
                //var direccion = respondar[k].direccion_proveedor ;
                var documento = respondar[k].ruc_proveedor ;
                var celular = respondar[k].telefono_proveedor ;
                //var email = respondar[k].email_proveedor;
                var stado = respondar[k].estado_proveedor;
                if(stado == 1 )stado= '<span class="label label-success">Habilitado</span>';
                else stado = '<span class="label label-danger">Inactivo</span>'   ;
                $('#contenido').append('<tr class="fila" id="fila_'+k+'"> <td>' + i + '</td> <td>' + documento + '</td> <td>' + nombre_completo + '</td>'+
                        '<td>' + celular + '</td> </td> </td><td>' + stado + '</td>' +
                        '<td><a  class="btn btn-md btn-primary editable" id='+k+' ><i class="fa  fa-plus"></i></a></td>' +                        
                        '</tr>'
                        ); 
                        //'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
              $('#contenido').append('<tr class="fila"> <td>' + " " + '</td> <td>' + "" + '</td> <td>' + "" + '</td><td>' + "" +
                    '</td><td>' + "" + '</td>' +
                    '<td></td>' +
                    '</tr>'
                    );  
            }
            
        }
    }
    function fill_cbx_provee(cantidad_datos,respondar){
         $('.fila_cbx').remove();
         //antes remover todas las acitvite
        for(var k=0; k<cantidad_datos;k++){            
            var nombre_completo = respondar[k].nombre_proveedor ;
            var id_provee = respondar[k].id_proveedor ;
            var stado = respondar[k].estado_proveedor;
            if(stado == 1 )
            $('#cbx_proveedores').append('<option  value="'+id_provee+'">'+nombre_completo+' </option>' );//console.log(id_provee);
        }
    }
    
    $('#btn_agregar').click(function(){
        $('#crearproveedor').show();
        $('#modificarproveedor').hide();
        $('#div_estado').hide();  
        $('#span_actualizar').hide();  
        $('#span_nuevo').show(); 
        limpiar();
    });
    // insertar datos persona
    // registrar proveedor
    $('#crearproveedor').on('click',function(){
         // ---cargar datos---- 
        var nombre=$('#txt_nombrepersona').val();
        var documento=$('#txt_documento').val();
        var direccion=$('#txt_direccion').val();
        var celular=$('#txt_celular').val();
        var email=$('#txt_email').val();                
         // ------finish datos-
        nombre=nombre.trim();
        documento=documento.trim();
        direccion=direccion.trim();
        celular=celular.trim();
        email=email.trim();  
        if(nombre.length==0){alertify.error('Debe ingresar Nombre de proveedor');return};
        if(documento.length==0){alertify.error('Debe ingresar RUC de proveedor');return};
        if(documento.length<11){alertify.error('El RUC debe tener 11 digitos');return};
        if(direccion.length==0){alertify.error('Debe ingresar direccion de proveedor');return};
        if(celular.length==0){alertify.error('Debe ingresar celular de proveedor');return};
        if(email.length==0){alertify.error('Debe ingresar email de proveedor');return}; 
        
        if(ruc_o_nombre_igual(1,documento,0)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Proveedor',message: 'El RUC ya existe.'}).show(); return;}
        if(ruc_o_nombre_igual(2,nombre,0)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Proveedor',message: 'El nombre ya existe.'}).show(); return;}
        if(ruc_valido(documento)=="NO"){alertify.dialog('alert').set({transition:'flipy',title:'Proveedor',message: 'El RUC no es valido.'}).show(); return;}
        
        
        //console.log(login,pass,combo_tipo,estado,fecha_nacimiento );
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{txt_nombrepersona:nombre,
                  txt_documento:documento,txt_direccion:direccion,txt_celular:celular,
                  txt_email:email
                  },
                url: 'insertar_proveedor',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    listar('');
                    limpiar();                   
               }                      
        });        
    });
    
    function traer_datos(listaa_datos){
        
        $('.editable').click(function(){
            var id_actualizar = $(this).attr('id');
            if(listaa_datos[id_actualizar].estado_proveedor==0){
                alertify.dialog('alert').set({transition:'flipy',title:'Factura Compra',message: 'No se puede seleccionar un <b>proveedor inhabilitado</b>.'}).show();
            }else{
                $('#buscarProveedor').modal("hide"); 
                    // mostar datos
                $('#txt_nombre').val(listaa_datos[id_actualizar].nombre_proveedor);
                $('#txt_ruc').val(listaa_datos[id_actualizar].ruc_proveedor);
                $('#txt_cel').val(listaa_datos[id_actualizar].telefono_proveedor);

                p_id_proveedor=listaa_datos[id_actualizar].id_proveedor;//console.log(p_id_proveedor);
            }
            
        
        });
    }
    
    function limpiar(){
        $('#txt_nombrepersona').val("");
        $('#txt_documento').val("");
        $('#txt_direccion').val("");
        $('#txt_celular').val("");
        $('#txt_email').val(""); 
        $('#cbx_estado').val("1");
    }
    
    
    
    
    
    //======================================XxXXxxXxxxxxx=============================================
        
    $('#tab_new_factura').click(function(){               
        limpiar_detallefacturatmp();
        listar_tmp_detfactura();
        $('#add_pro_tmp').show();
        $('#add_pro_table').hide();        
    });
    
    $('#tab_factura').click(function(){
        $('#listar_factura').show();
        $('#factura_edit').hide();
        var str=$('#texto_busqueda_factura').val();
        listar_factura(str);
        $('#add_pro_table').show();
        $('#add_pro_tmp').hide();        
    });
    
    function categoria_igual(cat,id){
        var sino="NO";
        for(var i=0;i < listaa_cat.length;i++){
            if(listaa_cat[i].descripcion_categoria==cat && listaa_cat[i].id_categoria != id){
                sino="SI";
                break;
            }            
        }
        return sino;
    }
    function codigo_o_nombre_igual(x,cat,id){
        var sino="NO";
        for(var i=0;i < listaa.length;i++){
            if(x==1){
                if(listaa[i].codigo_producto==cat && listaa[i].id_producto != id){
                    sino="SI";
                    break;
                }
            }else{
                if(listaa[i].descripcion_producto==cat && listaa[i].id_producto != id){
                    sino="SI";
                    break;
                }
            }
        }
        return sino;
    }
    function ruc_o_nombre_igual(x,cat,id){
        var sino="NO";
        for(var i=0;i < listaa_prove.length;i++){
            if(x==1){
                if(listaa_prove[i].ruc_proveedor==cat && listaa_prove[i].id_proveedor != id){
                    sino="SI";
                    break;
                }
            }else{
                if(listaa_prove[i].nombre_proveedor==cat && listaa_prove[i].id_proveedor != id){
                    sino="SI";
                    break;
                }
            }
        }
        return sino;
    }
    function ruc_valido(ruc){
        var arr = [5, 4, 3, 2, 7, 6, 5, 4, 3, 2 ];
        var d2 = ruc.substring(0,2);
        var result=0;
        var suma=0;
        var num_verificador = ruc.substring(10,11);
        num_verificador = parseInt(num_verificador);
        if( d2 != "10" && d2 != "20" && d2 != "15" && d2 != "16" && d2 !="17" ){            
            return "NO";
        }
        result = parseInt(result);
        suma = parseInt(suma);
        for (var i = 0; i < arr.length; i++) {
            result= parseInt(ruc.substring(i,(i+1))) * parseInt(arr[i]);
            suma =suma + result;
        }
        console.log( (11-(suma % 11))+" "+ num_verificador);
        if(( 11-(suma % 11) ) != num_verificador ) {
           return "NO";
        }
        return "SI";
    }
    
    
    function trunc (x, posiciones) {
        var s = x.toString(); 
        var l = s.length; 
        var decimalLength = s.indexOf('.') + 1; 
        var numStr;
        if(decimalLength !=0){
            numStr = s.substr(0, decimalLength + posiciones); 
        }else{
            numStr = x;
        }
        return Number(numStr);
    }    
    function gethoraAct(){
        var momentoActual = new Date();
        var hora = momentoActual.getHours();
        var minuto = momentoActual.getMinutes();
        var segundo = momentoActual.getSeconds();

        var str_segundo = new String (segundo)
        if (str_segundo.length == 1)
           segundo = "0" + segundo

        var str_minuto = new String (minuto)
        if (str_minuto.length == 1)
           minuto = "0" + minuto

        var str_hora = new String (hora)
        if (str_hora.length == 1)
           hora = "0" + hora

        var horaImprimible = hora + ":" + minuto + ":" + segundo;
        console.log(horaImprimible);
        return horaImprimible;
    }
    function getAddZerosToRight(str){// 100.3 ==> 100.30 o 100 ==> 100.00
        str = ""+str;
        var divisiones = str.split('.');    
        if(divisiones.length==1){
            str=str+".00";
        }else{
            if(divisiones[1].length==1){
                str=str+"0";
            }
        }        
        return str;
    }
    function load(){
        fill_cbx_categoria();
        //data lista
        listar_producto__("");
        clickproductos();        
        //listar tmp detfactura
        limpiar_detallefacturatmp();
        listar_tmp_detfactura();        
        //listar proveedor
        listar("");
        //---------
        $('#add_pro_table').hide();
    }
});





