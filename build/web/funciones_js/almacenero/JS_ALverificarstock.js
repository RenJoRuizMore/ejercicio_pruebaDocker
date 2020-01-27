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
    
    var listaa_cat;
    var listaa_pro;
    var listaa_prove;
    
    //funcion inicial    
    load();
    
    
    //::::::::::::::::::::::::::::::::::::::::::  C A T E G O R I A ************************************:+:
 
    $('#txt_busqueda_categoria').on('keyup',function(){       
        var valor_texto=$(this).val();
        listar_categoria(valor_texto);
    });    
    
    // busquedad filtrada CATEGORIA
    function listar_categoria(str) { 
        $.ajax({
            type:"GET",
            data:{texto_busqueda_categoria:str},
            url: 'busqueda_categoria',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_categoria.length;
                listaa_cat=response.lista_busqueda_categoria;
                var limite=10;
                $('.header_lis').remove();
                // agregar los botones
                agregar_botones_lista_categoria(limite,cantidad);
                // remover el contenido
                agregar_filas_categoria(1,limite,cantidad,listaa_cat);
                // click a un determinado boton
                $('.hojas_categoria').click(function(){
                    var ids=$(this).attr('id');
                    //alert(ids);
                    $('.botones').removeClass('active');
                    $('#lista_btn_categoria'+ids).addClass('active');
                    agregar_filas_categoria(ids,limite,cantidad,listaa_cat);
                    // se deben mostrar la lista segun sea su orden
                    traer_datos_categoria(listaa_cat);
                    //eliminar_objetos(listaa);
                });
                traer_datos_categoria(listaa_cat);
                //eliminar_objetos(listaa);
            }           
        });
    }
    
    // botones  PAGINADO // 
    function agregar_botones_lista_categoria(limite,cantidad_datos) {
            var cbtn=0;
           // var num_fila=0;
            if(cantidad_datos%limite==0){
                cbtn=cantidad_datos/limite;
            }else{
                cbtn=(cantidad_datos/limite)+1;
            }
            // remover todo lo que             
             $('.hojas_categoria').remove();
            // agregar botones
            //$('#boton_lista').append('<li class="header_lis disabled"><a href="#!"><i class="fa fa-angle-double-left"></i></a></li>');
            // agregar  la cantidad 
            for (var k = 1; k <= cbtn; k++) {
                $('#boton_lista_categoria').append('<li class="botones" id="lista_btn_categoria'+k+'"><a class="hojas_categoria" id="' + k + '" href="#!">' + k + '</a></li>');
            }
            //$('#boton_lista').append('<li class="header_lis"><a  href="#!"><i class="fa fa-angle-double-right"></i></a></li>');
                       
        }
    
    // agregar las filas 
    function agregar_filas_categoria(id,limite,cantidad_datos,respondar){
        var fin= id*limite;
        var inicio=((fin-limite)+1);
         $('.fila_cate').remove();//clase CSS q remove todas las acitvite
        for(var i=inicio,k=inicio-1;i<=fin;i++,k++){
            if (k < cantidad_datos) {
                var descripcion = respondar[k].descripcion_categoria ;
                var stado = respondar[k].estado_categoria;
                if(stado == 1 )stado= '<span class="label label-success">Habilitado</span>';
                else stado = '<span class="label label-danger">Inactivo</span>'   ;
                $('#contenido_categoria').append('<tr class="fila_cate" id="fila_categoria_'+k+'"> <td>' + i + '</td> <td>' + descripcion + '</td> <td>' + stado + '</td>' +
                        '<td><a  class="btn btn-md btn-primary editable_categoria btn-xs" id='+k+'  ><i class="fa  fa-edit"></i></a></td>' +                        
                        '</tr>'
                        );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
                $('#contenido_categoria').append('<tr class="fila_cate"> <td></td> <td></td> <td></td> <td></td> </tr>');  
            }            
        }
    }
    
    $('#btn_agregar_c').click(function(){
        $('#crearcategoria').show();
        $('#modificarcategoria').hide();
        $('#div_estado_c').hide();  
        $('#span_actualizar_c').hide();  
        $('#span_nuevo_c').show();
        limpiar_c();
    });
    
    function traer_datos_categoria(listaa_datos){
        
        $('.editable_categoria').click(function(){
           
            $('#nuevaCategoria').modal("show");
            $('#crearcategoria').hide();
            $('#modificarcategoria').show();            
            $('#div_estado_c').show();
            $('#span_nuevo_c').hide();
            $('#span_actualizar_c').show();
            
            var id_actualizar = $(this).attr('id');
            // mostar datos
            $('#txt_descripcion_c').val(listaa_datos[id_actualizar].descripcion_categoria);
            $('#cbx_estado_c').val(listaa_datos[id_actualizar].estado_categoria);

            p_id_categoria=listaa_datos[id_actualizar].id_categoria;
        
        });
    }
    
    //actualizar categoria
    $('#modificarcategoria').click(function(){
        actualizar_categoria(p_id_categoria);
    });
    function actualizar_categoria(id_){
        
        var desc=$('#txt_descripcion_c').val();
        desc=desc.trim();
        if(desc.length==0){alertify.error('Debe ingresar descripcion');return};
        if(categoria_igual(desc,id_)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Categoria',message: 'La categoria ya existe.'}).show(); return;}
        var estado=$('#cbx_estado_c').val();
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{txt_descripcion_c:desc,cbx_estado_c:estado,id_categoria:id_
                  },
                url: 'actualizar_categoria',
                success: function(response){
                    alertify.success('Se actualizo exitosamente');
                    listar_categoria('');fill_cbx_categoria();
                    $('#nuevaCategoria').modal("hide");  
                }                      
        });      
    }
    
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
                    listar_categoria('');fill_cbx_categoria();
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
                 $('.opciones').remove();// remover los datos
                 for(var x=0;x<cant_list;x++){                           
                     $('#Cbx_id_categoria').append( '<option class="opciones" value="'+response.lista_categorias[x].id_categoria+'">'+response.lista_categorias[x].descripcion_categoria+'</option>' );
                }
            }
        });       
    }
    
    $('#txt_busqueda_producto').on('keyup',function(){       
        var valor_texto=$(this).val();
        listar_producto(valor_texto);
    });  
    // busquedad filtrada PRODUCTO
    function listar_producto(str) { 
        $.ajax({
            type:"GET",
            data:{texto_busqueda_pro:str,allproductos:1,productservis:0},
            url: 'busqueda_producto',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_producto.length;//console.log("c"+cantidad);
                listaa_pro=response.lista_busqueda_producto;
                var limite=10;
                $('.header_lis_pro').remove();
                // agregar los botones
                agregar_botones_lista_producto(limite,cantidad);
                // remover el contenido
                agregar_filas_producto(1,limite,cantidad,listaa_pro);
                // click a un determinado boton
                $('.hojas_producto').click(function(){
                    var ids=$(this).attr('id');
                    //alert(ids);
                    $('.botones_p').removeClass('active');
                    $('#lista_btn_producto'+ids).addClass('active');
                    agregar_filas_producto(ids,limite,cantidad,listaa_pro);
                    // se deben mostrar la lista segun sea su orden
                    traer_datos_producto(listaa_pro);
                    //eliminar_objetos(listaa);
                });
                traer_datos_producto(listaa_pro);
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
                var categoria = respondar[k].desc_categoria ;
                var precio = respondar[k].precio_producto ;
                var und = respondar[k].unidades_producto ;
                //var stock = respondar[k].stock_producto ;
                var stock_a = respondar[k].stock_a_producto ;
                var stock_m = respondar[k].stock_m_producto ;
                var stock_min = respondar[k].stock_minimo_producto ;
                var stock_min_m = respondar[k].stock_minimo_mostrador_producto ;
                var en_mos = respondar[k].en_mostrador_producto ;
                var stado = respondar[k].estado_producto;
                if(en_mos == 1 )en_mos= '<span class="label label-success">SI</span>';
                else en_mos = '<span class="label label-warning">NO</span>'   ;
                if(stado == 1 )stado= '<span class="label label-success">Habilitado</span>';
                else stado = '<span class="label label-danger">Inactivo</span>'   ;
                
                $('#contenido_producto').append('<tr class="fila_pro" id="fila_producto_'+k+'"> '+
                        '<td>' + i + '</td> <td>' + cod + '</td> <td>' + categoria + '</td>' +
                        '<td>' + descripcion + '</td> <td>' + und + '</td> <td>' + precio + '</td>' +
                        '<td>' + stock_min + '</td> <td>' + stock_a + '</td>' +
                        '<td>' + stock_m + '</td> <td>' + stock_min_m + '</td> <td>' + en_mos + '</td>' +
                        '<td>' + stado + '</td>' +
                        '<td><a  class="btn btn-md btn-primary btn-xs editable_producto" id='+k+'  ><i class="fa  fa-edit"></i></a></td>' +                        
                        '</tr>'
                        );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
                $('#contenido_producto').append('<tr class="fila_pro"> <td></td> <td></td> <td></td> <td></td> </tr>');  
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
                  Txt_precio_producto:precio,Txt_unidades_producto:und,
                  Txt_stock_a_producto:stock_a,Txt_stock_m_producto:stock_m,
                  Txt_stock_minimo_producto:stock_min,Txt_stock_minimo_mostrador_producto:stock_min_m,Txt_en_mostrador_producto:en_m
                  },
                url: 'insertar_producto',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    listar_producto("");
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
            $('#Cbx_id_categoria').val(listaa_datos[id_actualizar].id_categoria );//console.log("en id cat"+listaa_datos[id_actualizar].id_categoria );
            $('#Txt_descripcion_producto').val(listaa_datos[id_actualizar].descripcion_producto);
            $('#Txt_codigo_producto').val(listaa_datos[id_actualizar].codigo_producto);
            $('#Txt_precio_producto').val(listaa_datos[id_actualizar].precio_producto);
            $('#Txt_unidades_producto').val(listaa_datos[id_actualizar].unidades_producto);
            //$('#Txt_stock_producto').val(listaa_datos[id_actualizar].stock_producto);
            $('#Txt_stock_a_producto').val(listaa_datos[id_actualizar].stock_a_producto);
            $('#Txt_stock_m_producto').val(listaa_datos[id_actualizar].stock_m_producto);
            $('#Txt_stock_minimo_producto').val(listaa_datos[id_actualizar].stock_minimo_producto);
            $('#Txt_stock_minimo_mostrador_producto').val(listaa_datos[id_actualizar].stock_minimo_mostrador_producto);
            $('#Txt_en_mostrador_producto').val(listaa_datos[id_actualizar].en_mostrador_producto);//console.log("en mos"+listaa_datos[id_actualizar].en_mostrador_producto);
            $('#Txt_estado_producto').val(listaa_datos[id_actualizar].estado_producto);
            
            
            if(listaa_datos[id_actualizar].en_mostrador_producto == 1 ){
                $('#div_stock_mostrador').show();
                $('#div_stock_min_mostrador').show();
            }else{
                $('#div_stock_mostrador').hide();
                $('#div_stock_min_mostrador').hide();
            }
            
            p_id_producto=listaa_datos[id_actualizar].id_producto;
        
        });
    }    
    //actualizar producto
    $('#modificarpro').click(function(){
        actualizar_producto(p_id_producto);
    });
    function actualizar_producto(id_){
        
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
        var estado=$('#Txt_estado_producto').val();//console.log(id_);
        
        if(codigo_o_nombre_igual(1,cod,id_)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Producto',message: 'El Codigo ya existe.'}).show(); return;}
        if(codigo_o_nombre_igual(2,nombre,id_)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Producto',message: 'El nombre ya existe.'}).show(); return;}
        
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{Txt_codigo_producto:cod,Cbx_id_categoria:cat,Txt_descripcion_producto:nombre,                
                  Txt_precio_producto:precio,Txt_unidades_producto:und,
                  Txt_stock_a_producto:stock_a,Txt_stock_m_producto:stock_m,
                  Txt_stock_minimo_producto:stock_min,Txt_stock_minimo_mostrador_producto:stock_min_m,Txt_en_mostrador_producto:en_m,
                  Txt_estado_producto:estado,Txt_id_producto:id_
                  },
                url: 'actualizar_producto',
                success: function(response){
                    alertify.success('Se actualizo exitosamente');
                    listar_producto('');//fill_cbx_categoria();
                    $('#nuevoProducto').modal("hide");  
                }                      
        });      
    }
    
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
    //::::::::::::::::::::::::::::::::::::::::::  S T O C K     M I N I M O  ************************************:+:
    
    $('#cbx_filtro_sm').on('change',function(){       
        var str=$('#Texto_busqueda_sm').val();
        listar_sm(str);
    });
    
    $('#Texto_busqueda_sm').on('keyup',function(){       
        var valor_texto=$(this).val();
        listar_sm(valor_texto);
    });  
    // busquedad filtrada Stock minimo
    function listar_sm(str) {
        var cbx_sm=$('#cbx_filtro_sm').val();
        $.ajax({
            type:"GET",
            data:{Texto_busqueda_sm:str,cbx_filtro_sm:cbx_sm},
            url: 'busqueda_sm',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_sm.length;//console.log("c"+cantidad);
                var listaa=response.lista_busqueda_sm;
                var limite=10;
                $('.header_lis_sm').remove();
                // agregar los botones
                agregar_botones_lista_sm(limite,cantidad);
                // remover el contenido
                agregar_filas_sm(1,limite,cantidad,listaa);
                // click a un determinado boton
                $('.hojas_sm').click(function(){
                    var ids=$(this).attr('id');
                    //alert(ids);
                    $('.botones_sm').removeClass('active');
                    $('#lista_btn_sm'+ids).addClass('active');
                    agregar_filas_sm(ids,limite,cantidad,listaa);
                    // se deben mostrar la lista segun sea su orden
                    add_listacompra(listaa); 
                });
                add_listacompra(listaa); 
            }           
        });
    }
    
    // botones  PAGINADO // 
    function agregar_botones_lista_sm(limite,cantidad_datos) {
            var cbtn=0;
           // var num_fila=0;
            if(cantidad_datos%limite==0){
                cbtn=cantidad_datos/limite;
            }else{
                cbtn=(cantidad_datos/limite)+1;
            }
            // remover todo lo que             
             $('.hojas_sm').remove();
            // agregar botones
            //$('#boton_lista').append('<li class="header_lis disabled"><a href="#!"><i class="fa fa-angle-double-left"></i></a></li>');
            // agregar  la cantidad 
            for (var k = 1; k <= cbtn; k++) {
                $('#boton_lista_sm').append('<li class="botones_sm" id="lista_btn_sm'+k+'"><a class="hojas_sm" id="' + k + '" href="#!">' + k + '</a></li>');
            }
            //$('#boton_lista').append('<li class="header_lis"><a  href="#!"><i class="fa fa-angle-double-right"></i></a></li>');
                       
        }
    
    // agregar las filas 
    function agregar_filas_sm(id,limite,cantidad_datos,respondar){
        var fin= id*limite;
        var inicio=((fin-limite)+1);
         $('.fila_sm').remove();//clase CSS q remove todas las acitvite
        for(var i=inicio,k=inicio-1;i<=fin;i++,k++){
            if (k < cantidad_datos) {
                
                var cod = respondar[k].codigo_producto;
                var descripcion = respondar[k].descripcion_producto ;
                var categoria = respondar[k].desc_categoria ;
                
                var cbx_sm=$('#cbx_filtro_sm').val();
                var stock = respondar[k].stock_a_producto ;
                var stock_min = respondar[k].stock_minimo_producto ;
                if(cbx_sm == 2){
                    stock = respondar[k].stock_m_producto ;
                    stock_min = respondar[k].stock_minimo_mostrador_producto ;
                }
                stock= '<span class="label label-warning">'+stock+'</span>';
                $('#contenido_sm').append('<tr class="fila_sm" id="fila_sm_'+k+'"> '+
                        '<td>' + i + '</td> <td>' + cod + '</td> <td>' + categoria + '</td>' +
                        '<td>' + descripcion + '</td> <td>' + stock_min + '</td> <td>' + stock + '</td>' +
                        '<td><a  class="btn btn-md btn-primary btn-xs btn_add_lc" id='+k+'  ><i class="fa fa-plus"></i></a></td>' +                        
                        '</tr>'
                        );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
                $('#contenido_sm').append('<tr class="fila_sm"> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>');  
            }            
        }
    }
    
        
    // add_listacompra 
    function add_listacompra(listaa_datos) {

        $('.btn_add_lc').click(function () {
            
            var cbx_sm=$('#cbx_filtro_sm').val();
            if(cbx_sm==1){
                var id_add = $(this).attr('id');
                alertify.confirm("Stock minimo","Desea agregar el producto a la lista compra",            
                function(){
                    $.ajax({
                    type:"POST",
                    data:{valoradddel:1,id_producto_add_listcompra:listaa_datos[id_add].id_producto},
                    url: 'add_del_listcompra',
                        success: function(response){
                            //console.log(response.msg);                        
                            alertify.success("Se agrego a la lista compra con exito");
                            var str=$('#Texto_busqueda_sm').val();
                            listar_sm(str);
                        }
                    });
                },
                function(){//cancelo
                });
            }else{
                alertify.dialog('alert').set({transition:'flipy',title:'Stock Minimo',message: 'Los productos en MOSTRADOR no se pueden agregar a la lista, solo los de almacen.'}).show(); 
            }
        
        });
    }
    
    
    
    
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    //::::::::::::::::::::::::::::::::::::::::::  L I S T A     C O M P R A  ************************************:+:
    
    
    
    $('#texto_busqueda_lc').on('keyup',function(){       
        var valor_texto=$(this).val();
        listar_lc(valor_texto);
    });  
    // busquedad filtrada Stock minimo
    function listar_lc(str) {
        $.ajax({
            type:"GET",
            data:{texto_busqueda_lc:str},
            url: 'busqueda_lc',
            success: function(response){//console.log("-- "+response.lista_busqueda_lc);
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_lc.length;//console.log("c"+cantidad);
                var listaa=response.lista_busqueda_lc;
                var limite=10;
                $('.header_lis_lc').remove();
                // agregar los botones
                agregar_botones_lista_lc(limite,cantidad);
                // remover el contenido
                agregar_filas_lc(1,limite,cantidad,listaa);
                // click a un determinado boton
                $('.hojas_lc').click(function(){
                    var ids=$(this).attr('id');
                    //alert(ids);
                    $('.botones_lc').removeClass('active');
                    $('#lista_btn_lc'+ids).addClass('active');
                    agregar_filas_lc(ids,limite,cantidad,listaa);
                    // se deben mostrar la lista segun sea su orden
                    del_listacompra(listaa); 
                });
                del_listacompra(listaa); 
            }           
        });
    }
    
    // botones  PAGINADO // 
    function agregar_botones_lista_lc(limite,cantidad_datos) {
            var cbtn=0;
           // var num_fila=0;
            if(cantidad_datos%limite==0){
                cbtn=cantidad_datos/limite;
            }else{
                cbtn=(cantidad_datos/limite)+1;
            }
            // remover todo lo que             
             $('.hojas_lc').remove();
            // agregar botones
            //$('#boton_lista').append('<li class="header_lis disabled"><a href="#!"><i class="fa fa-angle-double-left"></i></a></li>');
            // agregar  la cantidad 
            for (var k = 1; k <= cbtn; k++) {
                $('#boton_lista_lc').append('<li class="botones_lc" id="lista_btn_lc'+k+'"><a class="hojas_lc" id="' + k + '" href="#!">' + k + '</a></li>');
            }
            //$('#boton_lista').append('<li class="header_lis"><a  href="#!"><i class="fa fa-angle-double-right"></i></a></li>');
                       
        }
    
    // agregar las filas 
    function agregar_filas_lc(id,limite,cantidad_datos,respondar){
        var fin= id*limite;
        var inicio=((fin-limite)+1);
         $('.fila_lc').remove();//clase CSS q remove todas las acitvite
        for(var i=inicio,k=inicio-1;i<=fin;i++,k++){
            if (k < cantidad_datos) {
                
                var cod = respondar[k].codigo_producto;
                var descripcion = respondar[k].descripcion_producto ;
                var categoria = respondar[k].desc_categoria ;
                
                var cbx_lc=$('#cbx_filtro_lc').val();
                var stock = respondar[k].stock_a_producto ;
                var stock_min = respondar[k].stock_minimo_producto ;
                if(cbx_lc == 2){
                    stock = respondar[k].stock_m_producto ;
                    stock_min = respondar[k].stock_minimo_mostrador_producto ;
                }
                stock= '<span class="label label-warning">'+stock+'</span>';
                $('#contenido_lc').append('<tr class="fila_lc" id="fila_lc_'+k+'"> '+
                        '<td>' + i + '</td> <td>' + cod + '</td> <td>' + categoria + '</td>' +
                        '<td>' + descripcion + '</td> <td>' + stock_min + '</td> <td>' + stock + '</td>' +
                        '<td><a  class="btn btn-md btn-danger btn-xs btn_del_lc" id='+k+'  ><i class="fa fa-remove"></i></a></td>' +                        
                        '</tr>'
                        );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
                $('#contenido_lc').append('<tr class="fila_lc"> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>');  
            }            
        }
    }
    
    // add_listacompra 
    function del_listacompra(listaa_datos) {

        $('.btn_del_lc').click(function () {
            var id_del = $(this).attr('id');
            
            alertify.confirm("Lista Compra","Desea eliminar el producto de la lista compra",            
            function(){
                $.ajax({
                type:"POST",
                data:{valoradddel:0,id_producto_add_listcompra:listaa_datos[id_del].id_producto},
                url: 'add_del_listcompra',
                    success: function(response){
                        //console.log(response.msg);                        
                        alertify.success("Se elimino el producto de la lista compra con exito");
                        var str=$('#texto_busqueda_lc').val();
                        listar_lc(str);
                    }
                });
            },
            function(){//cancelo
            });
        });
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
                      //eliminar_objetos(listaa);
               });
               traer_datos(listaa_prove);
               //eliminar_objetos(listaa);
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
                var direccion = respondar[k].direccion_proveedor ;
                var documento = respondar[k].ruc_proveedor ;
                var celular = respondar[k].telefono_proveedor ;
                var email = respondar[k].email_proveedor;
                var stado = respondar[k].estado_proveedor;
                if(stado == 1 )stado= '<span class="label label-success">Habilitado</span>';
                else stado = '<span class="label label-danger">Inactivo</span>'   ;
                $('#contenido').append('<tr class="fila" id="fila_'+k+'"> <td>' + i + '</td> <td>' + documento + '</td> <td>' + nombre_completo + '</td><td>' + direccion +
                        '</td><td>' + celular + '</td> </td><td>' + email + '</td> </td><td>' + stado + '</td>' +
                        '<td><a  class="btn btn-md btn-primary editable" id='+k+' ><i class="fa  fa-edit"></i></a></td>' +                        
                        '</tr>'
                        ); 
                        //'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
              $('#contenido').append('<tr class="fila"> <td>' + " " + '</td> <td>' + "" + '</td> <td>' + "" + '</td><td>' + "" +
                    '</td><td>' + "" + '</td>' +
                    '<td></td>' +
                    '<td></td>' +
                    '</tr>'
                    );  
            }
            
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
           
            $('#nuevoProveedor').modal("show");
            //$('#modificarusu').css("display","block");
            //$('#crearusuario').css("display","none");
            $('#crearproveedor').hide();
            $('#modificarproveedor').show();            
            $('#div_estado').show();
            $('#span_nuevo').hide();
            $('#span_actualizar').show();
            //document.getElementById('crearusuario').style.display= 'none';
            //document.getElementById('modificarusu').style.display= 'block';
            
            var id_actualizar = $(this).attr('id');
                // mostar datos
            $('#txt_nombrepersona').val(listaa_datos[id_actualizar].nombre_proveedor);
            $('#txt_documento').val(listaa_datos[id_actualizar].ruc_proveedor);
            $('#txt_direccion').val(listaa_datos[id_actualizar].direccion_proveedor);
            $('#txt_celular').val(listaa_datos[id_actualizar].telefono_proveedor);
            $('#txt_email').val(listaa_datos[id_actualizar].email_proveedor);
            $('#cbx_estado').val(listaa_datos[id_actualizar].estado_proveedor);

            p_id_persona=listaa_datos[id_actualizar].id_proveedor;
        
        });
    }
    
    $('#modificarproveedor').click(function(){
        actualizar(p_id_persona);
    });    
    function actualizar(id_persona){
         
        var nombre=$('#txt_nombrepersona').val();
        var documento=$('#txt_documento').val();
        var direccion=$('#txt_direccion').val();
        var celular=$('#txt_celular').val();
        var email=$('#txt_email').val();
        var estado=$('#cbx_estado').val();
        nombre=nombre.trim();
        documento=documento.trim();
        direccion=direccion.trim();
        celular=celular.trim();
        email=email.trim();
        if(nombre.length==0){alertify.error('Debe ingresar Nombre de proveedor');return};
        if(documento.length==0){alertify.error('Debe ingresar documento de proveedor');return};
        if(documento.length<11){alertify.error('El RUC debe tener 11 digitos');return};
        if(direccion.length==0){alertify.error('Debe ingresar direccion de proveedor');return};
        if(celular.length==0){alertify.error('Debe ingresar celular de proveedor');return};
        if(email.length==0){alertify.error('Debe ingresar email de proveedor');return};
                
        if(ruc_o_nombre_igual(1,documento,id_persona)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Proveedor',message: 'El RUC ya existe.'}).show(); return;}
        if(ruc_o_nombre_igual(2,nombre,id_persona)=="SI"){alertify.dialog('alert').set({transition:'flipy',title:'Proveedor',message: 'El nombre ya existe.'}).show(); return;}
        if(ruc_valido(documento)=="NO"){alertify.dialog('alert').set({transition:'flipy',title:'Proveedor',message: 'El RUC no es valido.'}).show(); return;}
        
        //console.log(login,pass,combo_tipo,estado,fecha_nacimiento );
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{txt_nombrepersona:nombre,
                  txt_documento:documento,txt_direccion:direccion,txt_celular:celular,
                  txt_email:email,id_persona_e:id_persona,cbx_estado_user:estado
                  },
                url: 'actualizar_proveedor',
                success: function(response){
                    alertify.success('Se actualizo exitosamente');
                    listar('');
                    $('#nuevoProveedor').modal("hide");  
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
        for(var i=0;i < listaa_pro.length;i++){
            if(x==1){
                if(listaa_pro[i].codigo_producto==cat && listaa_pro[i].id_producto != id){
                    sino="SI";
                    break;
                }
            }else{
                if(listaa_pro[i].descripcion_producto==cat && listaa_pro[i].id_producto != id){
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
    
    $('#tab_productos').click(function(){               
        $('#div_stock_').hide();//no lo elimine        
        $('#div_stock_mostrador').hide();
        $('#div_stock_min_mostrador').hide();
        fill_cbx_categoria();
        var str=$('#txt_busqueda_producto').val();
        listar_producto(str);
    });
    $('#tab_categoria').click(function(){       
        var str=$('#txt_busqueda_categoria').val();
        listar_categoria(str);
    });
    $('#tab_stockmin').click(function(){               
        var str=$('#Texto_busqueda_sm').val();
        listar_sm(str);
    });    
     $('#tab_listcompra').click(function(){               
        var str=$('#texto_busqueda_lc').val();
        listar_lc(str);
    }); 
     $('#tab_proveedor').click(function(){               
        var str=$('#texto_busqueda').val();
        listar(str);
    }); 
    
    //=================0000 impirmir reporte ==============================
    
    $('#imprimir_lista').click(function(){
            $.ajax({
            type:"GET",
            
                url: 'imprimir_listaReport',
                success: function(response){
                    alertify.success('Se actualizo exitosamente');
                  
                    window.location= "imprimir_listaReport.action";
                }                      
        });
    });
    
    function load(){
        listar_sm("");
    }
});





