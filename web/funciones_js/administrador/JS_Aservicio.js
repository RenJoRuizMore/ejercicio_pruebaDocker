/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// jquery
$(document).ready(function(){
    
    // variables    
    var p_id_producto=0;
    
    //funcion inicial    
    load();
    
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    //::::::::::::::::::::::::::::::::::::::::::  servicio ************************************:+:
    
    $('#texto_busqueda_servicio').on('keyup',function(){       
        var valor_texto=$(this).val();
        listar_serivcio(valor_texto);
    });  
    // busquedad filtrada servicio
    function listar_serivcio(str) { 
        $.ajax({
            type:"GET",
            data:{texto_busqueda_servicio:str},
            url: 'busqueda_servicio',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_servicio.length;//console.log("c"+cantidad);
                var listaa=response.lista_busqueda_servicio;
                var limite=10;
                $('.header_lis_pro').remove();
                // agregar los botones
                agregar_botones_lista_producto(limite,cantidad);
                // remover el contenido
                agregar_filas_producto(1,limite,cantidad,listaa);
                // click a un determinado boton
                $('.hojas_producto').click(function(){
                    var ids=$(this).attr('id');
                    //alert(ids);
                    $('.botones_p').removeClass('active');
                    $('#lista_btn_producto'+ids).addClass('active');
                    agregar_filas_producto(ids,limite,cantidad,listaa);
                    // se deben mostrar la lista segun sea su orden
                    traer_datos_producto(listaa);
                    //eliminar_objetos(listaa);
                });
                traer_datos_producto(listaa);
                //eliminar_objetos(listaa);
            }           
        });
    }
    
    // botones  PAGINADO // 
    function agregar_botones_lista_producto(limite,cantidad_datos) {
            var cbtn=0;
           // var num_fila=0;
            if(cantidad_datos%limite==0){
                cbtn=cantidad_datos/limite;
            }else{
                cbtn=(cantidad_datos/limite)+1;
            }
            // remover todo lo que             
             $('.hojas_producto').remove();
            // agregar botones
            //$('#boton_lista').append('<li class="header_lis disabled"><a href="#!"><i class="fa fa-angle-double-left"></i></a></li>');
            // agregar  la cantidad 
            for (var k = 1; k <= cbtn; k++) {
                $('#boton_lista_producto').append('<li class="botones_p" id="lista_btn_producto'+k+'"><a class="hojas_producto" id="' + k + '" href="#!">' + k + '</a></li>');
            }
            //$('#boton_lista').append('<li class="header_lis"><a  href="#!"><i class="fa fa-angle-double-right"></i></a></li>');
                       
        }
    
    // agregar las filas 
    function agregar_filas_producto(id,limite,cantidad_datos,respondar){
        var fin= id*limite;
        var inicio=((fin-limite)+1);
         $('.fila_pro').remove();//clase CSS q remove todas las acitvite
        for(var i=inicio,k=inicio-1;i<=fin;i++,k++){
            if (k < cantidad_datos) {
                
                var cod = respondar[k].codigo_producto;
                var descripcion = respondar[k].descripcion_producto ;
                var precio = respondar[k].precio_producto ;
                var stado = respondar[k].estado_producto;
                if(stado == 1 )stado= '<span class="label label-success">Habilitado</span>';
                else stado = '<span class="label label-danger">Inactivo</span>'   ;
                
                $('#contenido_producto').append('<tr class="fila_pro" id="fila_producto_'+k+'"> '+
                        '<td>' + i + '</td> <td>' + cod + '</td> <td>' + descripcion + '</td>' +
                        '<td>' + precio + '</td> <td>' + stado + '</td>' +
                        '<td><a  class="btn btn-md btn-primary btn-xs editable_producto" id='+k+'  ><i class="fa  fa-edit"></i></a></td>' +                        
                        '</tr>'
                        );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
                $('#contenido_producto').append('<tr class="fila_pro"> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>');  
            }            
        }
    }
    
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
        var nombre=$('#Txt_descripcion_producto').val();
        var cod=$('#Txt_codigo_producto').val();
        var precio=$('#Txt_precio_producto').val();
        nombre=nombre.trim();
        cod=cod.trim();
        precio=precio.trim();
        if(cod.length==0){alertify.error('Debe ingresar Codigo del servicio');return};
        if(nombre.length==0){alertify.error('Debe ingresar Descripcion del servicio');return};
        if(precio.length==0){alertify.error('Debe ingresar Precio del servicio');return};
             
        // ------finish datos--
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{Txt_codigo_producto:cod,Txt_descripcion_producto:nombre,                
                  Txt_precio_producto:precio
                  },
                url: 'insertar_servicio',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    listar_serivcio("");
                    limpiar_p();                  
                }                      
        });        
    });
    
    function traer_datos_producto(listaa_datos){
        
        $('.editable_producto').click(function(){
           
            $('#nuevoProducto').modal("show");
            $('#crearpro').hide();
            $('#modificarpro').show();            
            $('#div_estado_p').show();
            $('#span_nuevo_p').hide();
            $('#span_actualizar_p').show();
            
            var id_actualizar = $(this).attr('id');
            // mostar datos
            $('#Txt_descripcion_producto').val(listaa_datos[id_actualizar].descripcion_producto);
            $('#Txt_codigo_producto').val(listaa_datos[id_actualizar].codigo_producto);
            $('#Txt_precio_producto').val(listaa_datos[id_actualizar].precio_producto);
            $('#Txt_estado_producto').val(listaa_datos[id_actualizar].estado_producto);
            
            p_id_producto=listaa_datos[id_actualizar].id_producto;
        
        });
    }    
    //actualizar producto
    $('#modificarpro').click(function(){
        actualizar_producto(p_id_producto);
    });
    function actualizar_producto(id_){
        
        var nombre=$('#Txt_descripcion_producto').val();
        var cod=$('#Txt_codigo_producto').val();
        var precio=$('#Txt_precio_producto').val();        
        nombre=nombre.trim();
        cod=cod.trim();
        precio=precio.trim();
        if(cod.length==0){alertify.error('Debe ingresar Codigo del servicio');return};
        if(nombre.length==0){alertify.error('Debe ingresar Descripcion del servicio');return};
        if(precio.length==0){alertify.error('Debe ingresar Precio del servicio');return};
        var estado=$('#Txt_estado_producto').val();//console.log(id_);
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{Txt_codigo_producto:cod,Txt_descripcion_producto:nombre,                
                  Txt_precio_producto:precio,
                  Txt_estado_producto:estado,Txt_id_producto:id_
                  },
                url: 'actualizar_servicio',
                success: function(response){
                    alertify.success('Se actualizo exitosamente');
                    listar_serivcio('');
                    $('#nuevoProducto').modal("hide");  
                }                      
        });      
    }
    
    function limpiar_p(){
        $('#Txt_codigo_producto').val("");
        $('#Txt_descripcion_producto').val("");
        $('#Txt_precio_producto ').val("0.0");        
        $('#Txt_estado_producto').val("1");
    }
    
    
    //======================================XxXXxxXxxxxxx=============================================
       
        
    function load(){
        listar_serivcio("");
    }
});





