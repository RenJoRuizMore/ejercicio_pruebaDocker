/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// jquery
$(document).ready(function(){
    
    // variables    
    var p_id_categoria=0;
    
    var p_id_persona=0;
    
    var Txtcod_producto_s;
    var Txtnombre_producto_s;
    var id_producto_s=0;
    var id_tmp_detsal;
    
    var Txtcod_producto_detsal;
    var Txtnombre_producto_detsal;
    var id_producto_detsal=0;
    
    var id_Salida=0;
    
    var listaa;//lista de productos
    var listaa_detsal;//lista de productos
    
    var listIdsprod;//lista de id productos
    var listIdsprod_detsal;//lista de id productos
    
    //funcion inicial    
    load();
    
    
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    //:::::::::::::::::::::::::::::::::::::::::: TAB NUEVA S A L I D A S   ************************************:+:
    //    
    
    //=============== OBTENER NUMERO SALIDA
    function obtener_numsal(){
        var numero="0010000001";
        $.ajax({
            type:"GET",
            data:{},
            url: 'obtener_numsal',
            success: function(response){
                // cojia y limpiaba dagtos                 
                var cantidadreg=response.codigo_salida.length;//console.log("count "+response.codigo_salida.length);
                var listaa=response.codigo_salida;
                if(cantidadreg > 0){
                    var str  = listaa[0].numero_salida;
                    var str3 = str.substring(0,3);//console.log("str3 :"+str3);
                    var str7 = str.substring(3,10);//console.log("str7 :"+str7);
                    str3 = parseInt(str3);
                    str7 = parseInt(str7);
                    str7 = str7 + 1;
                    if(str7 > 9999999){
                        str7 = 1;
                        str3 = str3 + 1;
                    }                                     
                    numero = zfill(str3, 3)+zfill(str7, 7);                    
                }//console.log("num "+numero);
                $('#txt_numero_salida').val(numero);
            }           
        });
    }
    
    //---------------------------------  DATALIST --
    function clickproductos (){
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
                    $("#txtcantidad").val("1");
                    $("#txtcantidad").focus();
                    
                }
            });
        });
    }
    function listar_producto(str) { 
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
                agregar_producto_datalist_detsal(cantidad,listaa);
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
    //---------------------------------  LIST TMP DETALLE SALIDA --

    // LISTAR tmp_detsalida
    function listar_tmp_detsalida() { 
        $.ajax({
            type:"GET",
            data:{},
            url: 'listar_tmp_detsalida',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_detsalida.length;//console.log("count lista_detsalida : "+cantidad);
                var listaa=response.lista_detsalida;
                agregar_filas_tmp_detsalida(1,0,cantidad,listaa);
                // click a un determinado boton                
                traer_datos_tmp_detsalida(listaa);
                del_listmp(listaa);
            }           
        });
    }
    // agregar las filas  TMP DETALLE SALIDA
    function agregar_filas_tmp_detsalida(id,limite,cantidad_datos,respondar){        
        listIdsprod=[];//vaciamos lista ids productos
         $('.fila_pro').remove();//clase CSS q remove todas las acitvite
        for(var k=0;k<cantidad_datos ;k++){
            listIdsprod.push(respondar[k].id_producto);
            var cod = respondar[k].cod_producto;
            var descripcion = respondar[k].nombre_producto ;
            var cant = respondar[k].cantidad_detallesalida ;
            $('#contenido_tmp_detsalida').append('<tr class="fila_pro" id="'+k+'"> '+
                    '<td>' + (k+1) + '</td> <td>' + cod + '</td> <td>' + descripcion + '</td>' +
                    '<td>' + cant + '</td> ' +
                    '<td><a  class="btn btn-primary btn-xs editable_tmp_detsalida" id='+k+'  ><i class="fa  fa-edit"></i></a></td>' +
                    '<td><a  class="btn btn-danger btn-xs remover_tmp_detsalida" id='+k+'  ><i class="fa  fa-remove"></i></a></td>' +                        
                    '</tr>'
            );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +                 
        }//console.log("listIds "+listIdsprod.toString());
    }
    function traer_datos_tmp_detsalida(listaa_datos){
        
        $('.editable_tmp_detsalida').click(function(){
           
            $('#buscarproducto').modal("show");
            
            var id_actualizar = $(this).attr('id');
            // mostar datos
            $('#txt_name_p').val(listaa_datos[id_actualizar].nombre_producto);
            $('#txt_cantidad_p').val(listaa_datos[id_actualizar].cantidad_detallesalida);
            $('#txt_stock_p').val(listaa_datos[id_actualizar].stock);

            id_tmp_detsal = id_actualizar;
        
        });
    }
    // agregar producto a detalle salida TMP
    $('#add_pro').click(function(){//console.log(id_producto_s);
        if(id_producto_s==0){$('#txt_produc').focus();
            alertify.dialog('alert').set({transition:'flipy',title:'Salidas',message: 'Falta seleccionar un producto.'}).show();
        }else{
            if(existe_n_tmpdetallesal(id_producto_s)==1){$('#txt_produc').focus();
                alertify.dialog('alert').set({transition:'flipy',title:'Salidas',message: 'El producto ya existe en la lista.'}).show();                
            }else{
                var cantidad=$('#txtcantidad').val();
                cantidad= cantidad.trim();
                if(cantidad.length==0){ $('#txtcantidad').focus();//return;
                    alertify.dialog('alert').set({transition:'flipy',title:'Salidas',message: 'Falta ingresar cantidad.'}).show();
                }else{
                    var stock=$('#txtstock').val();
                    stock = parseInt(stock);
                    cantidad = parseInt(cantidad);
                    console.log(stock+" "+cantidad);
                    if( stock < cantidad ){$('#txtcantidad').focus();
                        alertify.dialog('alert').set({transition:'flipy',title:'Salidas',message: 'La cantidad no debe ser mayor al stock.'}).show();
                    }else{
                        console.log("se agregoooo");
                        add_listmp();
                        id_producto_s=0;
                        $('#txtcantidad').val("");
                        $('#txtstock').val("");
                        $('#txt_produc').val("");
                        $('#txt_produc').focus();                    
                    }
                } 
            }    
        }        
    });    
    function add_listmp(){
        // ---cargar datos---        // ---cargar datos---
        var nombre=Txtnombre_producto_s;
        var cod=Txtcod_producto_s;
        var cantidad=$('#txtcantidad').val();
        var stock=$('#txtstock').val();
        
                   //console.log(nombre+" - "+cod+" - "+cantidad);
         $.ajax({
            type:"POST",
            data:{id_producto_s:id_producto_s,txtcod_producto_s:cod,txtnombre_producto_s:nombre,                
                  txtstock:stock,txtcantidad:cantidad
                  },
                url: 'add_listmp',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    listar_tmp_detsalida();
                    //limpiar_p();                  
                }                      
        });        
    }
    function del_listmp(listaa_datos){
        $('.remover_tmp_detsalida').click(function(){
            
            var id_a_eliminar = $(this).attr('id');
            alertify.confirm("Nueva Salida","Desea Eliminar el producto <b> "+listaa_datos[id_a_eliminar].nombre_producto+" </b> de la lista",            
            function(){
                // mostar datos            
                //id_tmp_detsal=listaa_datos[id_actualizar].id_producto;
                $.ajax({
                    type:"POST",
                    data:{id_tmp_detsal:id_a_eliminar},
                        url: 'del_listmp',
                        success: function(response){
                            alertify.success('Se elimino exitosamente');
                            listar_tmp_detsalida();
                            //limpiar_p();                  
                        }                      
                });
                
            },
            function(){//cancelo
            });
           
        });       
    }
    $('#modificarcantidad').click(function(){
        var cant=$('#txt_cantidad_p').val();
        cant=cant.trim();
        if(cant.length==0){
            alertify.dialog('alert').set({transition:'flipy',title:'Salidas',message: 'Falta ingresar cantidad.'}).show();
            return;
        }
        if(cant==0){
            alertify.dialog('alert').set({transition:'flipy',title:'Salidas',message: 'La cantidad no debe ser cero.'}).show();
            return;
        }
        cant = parseInt(cant);
        var stock = $('#txt_stock_p').val();
        stock = parseInt(stock);
        if(stock < cant){
            alertify.dialog('alert').set({transition:'flipy',title:'Salidas',message: 'La cantidad no debe ser mayor al stock.'}).show();
            return;
        }         
        console.log("id : "+id_tmp_detsal);
        edit_listmp_x(id_tmp_detsal,cant);console.log("id y cant: "+id_tmp_detsal+" - "+cant);
    });
    function edit_listmp_x(id_,k){        
        // crear objeto response
        $.ajax({
            type:"POST",
            data:{id_tmp_detsal:id_,txt_cantidad_p:k},
                url: 'edit_listmp',
                success: function(response){
                    alertify.success('se actualizo exitosamente');
                    listar_tmp_detsalida();
                    $('#buscarproducto').modal("hide");                  
                }                      
        });     
    }
    function limpiar_detalletmp(){
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{},
                url: 'limpiar_detalletmp',
                success: function(response){
                    console.log("se limpio lista");//alertify.success('Se actualizo exitosamente');
                }                      
        });      
    }
    
    function existe_n_tmpdetallesal(idp){
        var sino=0;
        for(var i=0;i<listIdsprod.length ;i++){
            if(idp==listIdsprod[i]){
                sino=1;
                break;
            }                
        }        
        return sino;
    }
    
    
    ///------------------------- SALIDA
    
    // registrar  SALIDA Y DETALLE SALIDAAAAAA
    $('#crearsalida').on('click',function(){
        
        var fecha=$('#txtfecha').val();
        fecha=fecha.trim();
        if(fecha.length==0){$('#txtfecha').focus();alertify.error('Debe ingresar Fecha de la salida');return};
        
        if(listIdsprod.length==0){
            alertify.dialog('alert').set({transition:'flipy',title:'Salidas',message: 'Falta ingresar productos a la lista.'}).show();
            return;
        }
        var id_user=3;//dgdfgdfggdfgdfgfgdfgdfgdfgdfgdfgdgdgdgdgdfgdgdgdfgdgdg;gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg
        var num=$('#txt_numero_salida').val();
        var fecha=$('#txtfecha').val();
        fecha=fecha.trim();
        if(fecha.length==0){alertify.error('Debe ingresar fecha');return};
        fecha=fecha+" "+gethoraAct();
        // crear objeto response
        $.ajax({
            type:"POST",
            data:{txt_numero_salida:num,id_usuario:id_user,txtfecha:fecha},
                url: 'insertar_salida',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    listar_tmp_detsalida();
                    obtener_numsal();
                    getfechaAct();
               }                      
        });        
    }); 
    
    
    
//      onChange="validarSiNumero(this.value);"
//    function validarSiNumero(numero){
//        if (!/^([0-9])*$/.test(numero))
//          alert("El valor " + numero + " no es un número");
//    }
//      onKeyPress="return soloNumeros(event)"
//   function soloNumeros(e)
//    {
//            var key = window.Event ? e.which : e.keyCode
//            return ((key >= 48 && key <= 57) || (key==8))
//    } 
       
    ////////////////////////////////////////////////////////////////////////////////////////77777//////////////////////////////7
    //
    //             TAB LISTA DE SALIDASSSS PAGINADO
    //             
    ///////////////////////////////////////////////////////////////////77}/////////////////////////////////////////////////7777
    
    $('#texto_busqueda_salida').on('keyup',function(){       
        var valor_texto=$(this).val();
        listar_salida(valor_texto);
    });  
    // busquedad filtrada 
    function listar_salida(str) {
        $.ajax({
            type:"GET",
            data:{texto_busqueda_salida:str},
            url: 'busqueda_salida',
            success: function(response){//console.log("-- "+response.lista_busqueda_salida);
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_salida.length;console.log("c"+cantidad);
                var listaa=response.lista_busqueda_salida;
                var limite=10;
                $('.header_lis_salida').remove();
                // agregar los botones
                agregar_botones_lista_salida(limite,cantidad);
                // remover el contenido
                agregar_filas_salida(1,limite,cantidad,listaa);
                // click a un determinado boton
                $('.hojas_salida').click(function(){
                    var ids=$(this).attr('id');
                    //alert(ids);
                    $('.botones_salida').removeClass('active');
                    $('#lista_btn_salida'+ids).addClass('active');
                    agregar_filas_salida(ids,limite,cantidad,listaa);
                    // se deben mostrar la lista segun sea su orden
                    traer_datos_detsalida(listaa); 
                });
                traer_datos_detsalida(listaa); 
            }           
        });
    }    
    // botones  PAGINADO // 
    function agregar_botones_lista_salida(limite,cantidad_datos) {
            var cbtn=0;
           // var num_fila=0;
            if(cantidad_datos%limite==0){
                cbtn=cantidad_datos/limite;
            }else{
                cbtn=(cantidad_datos/limite)+1;
            }
            // remover todo lo que             
             $('.hojas_salida').remove();
            // agregar botones
            //$('#boton_lista').append('<li class="header_lis disabled"><a href="#!"><i class="fa fa-angle-double-left"></i></a></li>');
            // agregar  la cantidad 
            for (var k = 1; k <= cbtn; k++) {
                $('#boton_lista_salida').append('<li class="botones_salida" id="lista_btn_salida'+k+'"><a class="hojas_salida" id="' + k + '" href="#!">' + k + '</a></li>');
            }
            //$('#boton_lista').append('<li class="header_lis"><a  href="#!"><i class="fa fa-angle-double-right"></i></a></li>');
                       
        }    
    // agregar las filas 
    function agregar_filas_salida(id,limite,cantidad_datos,respondar){
        var fin= id*limite;
        var inicio=((fin-limite)+1);
         $('.fila_salida').remove();//clase CSS q remove todas las acitvite
        for(var i=inicio,k=inicio-1;i<=fin;i++,k++){
            if (k < cantidad_datos) {
                
                var cod = respondar[k].numero_salida;
                var total = respondar[k].cantidad ;                
                var fecha = respondar[k].fecha_salida ;
                fecha = fecha.substring(8,10)+"-" +fecha.substring(5,7)+"-"+fecha.substring(0,4);
                var usuario = respondar[k].nombre_usuario ;
                var stado = respondar[k].estado_salida ;
                if(stado == 1 )stado= '<span class="label label-success">Habilitado</span>';
                else stado = '<span class="label label-danger">Inactivo</span>'   ;
                
                $('#contenido_salida').append('<tr class="fila_salida" id="fila_salida_'+k+'"> '+
                        '<td>' + i + '</td> <td>' + cod + '</td> <td>' + fecha + '</td> <td>' + total + '</td>' +
                        '<td>' + usuario + '</td> <td>' + stado + '</td>' +
                        '<td><a  class="btn btn-primary editable_salida btn-xs btn_del_salida" id='+k+'  ><i class="fa fa-edit"></i></a></td>' +                        
                        '</tr>'
                        );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
                $('#contenido_salida').append('<tr class="fila_salida"> <td></td> <td></td> <td></td><td></td> <td></td> <td></td> <td></td> </tr>');  
            }            
        }
    }
    function traer_datos_detsalida(listaa_datos){
        
        $('.editable_salida').click(function(){
           
            $('#lista_detalle').hide();
            $('#editar_detalle').show();
            
            var id_actualizar = $(this).attr('id');
            // mostar datos
            $('#txt_numero_salida_e').val(listaa_datos[id_actualizar].numero_salida);
            var fecha =listaa_datos[id_actualizar].fecha_salida;
            //console.log(fecha.substring(0,10)+"X");
            $('#txt_fecha_e').val(fecha.substring(0,10));
            $('#cbx_estado_e').val(listaa_datos[id_actualizar].estado_salida);
            id_Salida = listaa_datos[id_actualizar].id_salida;
            listar_detallesalida(id_Salida);
        
        });
    }
    
    $('#btn_cancelar').click(function(){
        var str=$('#texto_busqueda_salida').val();
        listar_salida(str);
        $('#lista_detalle').show();
        $('#editar_detalle').hide();
    });
    
    ////////////////////////////////////////////////////////////////////////////////////////////7    
    ///////                              edita salidas    
    ////////////////////////////////////////////////////////////////7777777////////////////777777
    //---------------------------------  DATALIST --////////////////////////////////////////77777
    function clickproductos_detsal (){
        $('#txt_produc_detsal').on("input", function() {
            var usertext = $(this).val();
            $('#listproductos_detsal').find("option").each(function()
            {
                if($(this).val()=== usertext){
                    var ids=$(this).attr('id');
                    //console.log("id -> "+ids);
                    //console.log(listaa);
                    id_producto_detsal=listaa[ids-1].id_producto;
                    Txtcod_producto_detsal=listaa[ids-1].codigo_producto;;
                    Txtnombre_producto_detsal=listaa[ids-1].descripcion_producto;;
                    
                    $('#txtstock_detsal').val(listaa[ids-1].stock_a_producto);
                    $("#txtcantidad_detsal").val("1");
                    $("#txtcantidad_detsal").focus();
                    
                }
            });
        });
    }
//    function listar_producto_detsal(str) { 
//        $.ajax({
//            type:"GET",
//            data:{texto_busqueda_pro:str,allproductos:1},
//            url: 'busqueda_producto',
//            success: function(response){
//                // cojia y limpiaba dagtos 
//                var cantidad=response.lista_busqueda_producto.length;console.log("c-deta"+cantidad);
//                listaa_detsal=response.lista_busqueda_producto;
//                // remover el contenido
//                agregar_producto_datalist_detsal(cantidad,listaa_detsal);
//            }           
//        });
//    }
    // agregar las filas A DATALIST 
    function agregar_producto_datalist_detsal(cantidad_datos,respondar){
        //var fin= id*limite;
        //var inicio=((fin-limite)+1);
        //$('.fila_pro').remove();//clase CSS q remove todas las acitvite
        for(var k=0;k<cantidad_datos ;k++){
                var cod = respondar[k].codigo_producto;
                var descripcion = respondar[k].descripcion_producto ;
                $('#listproductos_detsal').append('<option   id="'+(k+1)+' "value="'+cod+' - '+descripcion+'"></option>' );
                //console.log(k+" - "+cod+" "+descripcion);
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////777
    // busquedad filtrada  x id_salida detallesalida editttttttt
    function listar_detallesalida(str) { 
        $.ajax({
            type:"GET",
            data:{id_salida:str},
            url: 'busqueda_detallesalida',
            success: function(response){
                var cantidad=response.lista_busqueda_detallesalida.length;//console.log("c"+cantidad);
                var listaa=response.lista_busqueda_detallesalida;
                agregar_filas_detallesalida(cantidad,listaa);
                del_detallesalida(listaa);
            }           
        });
    }
    // agregar las filas 
    function agregar_filas_detallesalida(cantidad_datos,respondar){
        $('.fila_pro').remove();//clase CSS q remove todas las acitvite
        listIdsprod_detsal=[];
        for(var k=0 ; k < cantidad_datos ; k++){
            listIdsprod_detsal.push(respondar[k].id_producto);            
            var cod = respondar[k].cod_producto;
            var descripcion = respondar[k].nombre_producto ;
            var cantidad = respondar[k].cantidad_detallesalida ;

            $('#contenido_detallesalida').append('<tr class="fila_pro" id="fila_detallesalida_'+k+'"> '+
                '<td>' + (k+1) + '</td> <td>' + cod + '</td> <td>' + descripcion + '</td>' +
                '<td>' + cantidad + '</td> '+
                '<td><a  class="btn btn-danger btn-xs del_detallesalida" id='+k+'  ><i class="fa fa-remove"></i></a></td>' +                        
                '</tr>'
            );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +                         
        }
    }
    function del_detallesalida(listaa_datos){
        $('.del_detallesalida').click(function(){
            
            var id_a_eliminar = $(this).attr('id');
            var id_detallesalida = listaa_datos[id_a_eliminar].id_detallesalida;
            alertify.confirm("Editar Salida","Desea Eliminar el producto <b> "+listaa_datos[id_a_eliminar].nombre_producto+" </b> de la lista.",            
            function(){
                // mostar datos            
                //id_tmp_detsal=listaa_datos[id_actualizar].id_producto;
                $.ajax({
                    type:"POST",
                    data:{id_detsalida:id_detallesalida},
                        url: 'eliminar_detallesalida',
                        success: function(response){
                            alertify.success('Se elimino exitosamente');
                            listar_detallesalida(id_Salida);   
                        }                      
                });
                
            },
            function(){//cancelo
            });
           
        });       
    }
    // agregar producto a detalle_salida
    $('#add_pro_detsal').click(function(){//console.log(id_producto_s);
        if(id_producto_detsal==0){$('#txt_produc_detsal').focus();
            alertify.dialog('alert').set({transition:'flipy',title:'Editar Salidas',message: 'Falta seleccionar un producto.'}).show();
        }else{
            if(existe_n_detsal(id_producto_detsal)==1){$('#txt_produc_detsal').focus();
                alertify.dialog('alert').set({transition:'flipy',title:'Editar Salida',message: 'El producto ya existe en la lista.'}).show();                
            }else{
                var cantidad=$('#txtcantidad_detsal').val();
                cantidad= cantidad.trim();
                if(cantidad.length==0){ $('#txtcantidad_detsal').focus();//return;
                    alertify.dialog('alert').set({transition:'flipy',title:'Editar Salidas',message: 'Falta ingresar cantidad.'}).show();
                }else{
                    var stock=$('#txtstock_detsal').val();
                    stock = parseInt(stock);
                    cantidad = parseInt(cantidad);
                    console.log(stock+" "+cantidad);
                    if( stock < cantidad ){$('#txtcantidad_detsal').focus();
                        alertify.dialog('alert').set({transition:'flipy',title:'Editar Salidas',message: 'La cantidad no debe ser mayor al stock.'}).show();
                    }else{
                        registrar_detsal(id_Salida,id_producto_detsal,cantidad) ;                  
                    }
                } 
            }    
        }        
    });
    function registrar_detsal(id,idp,k){
        $.ajax({
            type:"POST",
            data:{id_Salida:id,id_Prodcut:idp,Cantidad:k},
                url: 'insertar_detallesalida',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    //alert("enytoooo");
                    listar_detallesalida(id_Salida);
                    //console.log("se agregoooo detalle_salida");
                    id_producto_detsal=0;
                    $('#txtcantidad_detsal').val("");
                    $('#txtstock_detsal').val("");
                    $('#txt_produc_detsal').val("");
                    $('#txt_produc_detsal').focus();                                    
               }                      
        }); 
    }
    
    $('#modificarSalida').click(function(){
        actualizar_salida(id_Salida);
    });
    function actualizar_salida(id_){
        
        var estado=$('#cbx_estado_e').val();
        var fecha=$('#txt_fecha_e').val();
        fecha=fecha.trim();
        if(fecha.length==0){alertify.error('Debe ingresar Fecha de la salida');return};
        fecha = fecha+" "+gethoraAct();
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{txtfecha:fecha,stado:estado,id_salida:id_
                  },
                url: 'actualizar_salida',
                success: function(response){
                    alertify.success('Se actualizo exitosamente');
                }                      
        });      
    }
    
    function existe_n_detsal(idp){
        var sino=0;
        for(var i=0;i<listIdsprod_detsal.length ;i++){
            if(idp==listIdsprod_detsal[i]){
                sino=1;
                break;
            }                
        }        
        return sino;
    }
    
    
    
    
    
    
    //::::::::::::::::::::::::::::::::::::::::::   ************************************:+:
 
    
    
    //======================================XxXXxxXxxxxxx=============================================
        
    $('#tab_new_salida').click(function(){   
        limpiar_detalletmp();
        listar_tmp_detsalida();
        getfechaAct();
//        $('#div_stock_').hide();
//        fill_cbx_categoria();
//        var str=$('#txt_busqueda_producto').val();
//        listar_producto(str);
    });
    
    $('#tab_lista_salida').click(function(){
        $('#lista_detalle').show();
        $('#editar_detalle').hide();
        var str=$('#texto_busqueda_salida').val();
        listar_salida(str);
    }); 
    
    function getfechaAct(){
        n =  new Date();
        y = n.getFullYear();
        m = n.getMonth() + 1;
        if(m<10){m="0"+m;}
        d = n.getDate();
        $('#txtfecha').val(y + "-" + m + "-" + d);
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
    
    function load(){
        console.log("salida almacen");// listar_sm("");
        obtener_numsal();
        
        listar_producto("");
        clickproductos();
                
        //listar_producto_detsal("");        
        clickproductos_detsal();
        
        listar_tmp_detsalida();
        getfechaAct();
        //gethoraAct();
        
        limpiar_detalletmp();
    }
});





