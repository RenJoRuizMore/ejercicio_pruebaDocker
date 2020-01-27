/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    // variables    
    var p_id_producto=0;
    
    //funcion inicial    
    load();
    
    
    $('#cbx_opcion').on('change',function(){       
        var str=$(this).val();
        $('#div_fechai').hide();             
        $('#div_fechaf').hide();
        $('#div_mes').hide();
        $('#div_anio').hide();
        if(str==1){
            $('#div_fechai').show();
        }else if(str==2){          
            $('#div_mes').show();
            $('#div_anio').show();            
        }else if(str==3){            
            $('#div_anio').show();            
        }else{
            $('#div_fechai').show();             
            $('#div_fechaf').show();            
        }
        //listar_sm(str);
    });
    $('#btn_buscar').click(function(){ 
        listar_provendidos();
    });
    
    
    //LLENAR COMBO PROVEEDOR
    function fill_cbx_pro(){
        $.ajax({
            type:"GET",
            url: 'fill_combo_productos_c',
            success: function(response){
            var cant_list=response.lista_productos.length;
                 $('.opciones').remove();// remover los datos
                 for(var x=0;x<cant_list;x++){                           
                     $('#cbx_productos').append( '<option class="opciones" value="'+response.lista_productos[x].id_producto+'">'+response.lista_productos[x].descripcion_producto+'</option>' );
                }
            }
        });       
    }    
    
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    //::::::::::::::::::::::::::::::::::::::::::   ************************************:+:
    
    function listar_provendidos() {
        var fi = $('#txt_fechai').val();
        var ff = $('#txt_fechaf').val();
        var prov = $('#cbx_productos').val();
        $.ajax({
            type:"GET",
            data:{txt_fechaf:ff,txt_fechai:fi,cbx_productos:prov},
            url: 'busqueda_provendidos_c',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_provendidos.length;//console.log("c- "+cantidad);
                var listaa=response.lista_busqueda_provendidos;
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
                    //traer_datos_detfactura(listaa);
                });
                //traer_datos_detfactura(listaa);
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
                
                var nom= respondar[k].nombre_pro;
                var stocka = respondar[k].sa ;
                var stockm = respondar[k].sm ;                
                var precio = respondar[k].precio ;
                var cant = respondar[k].cant ;
                var stock = parseInt(stocka) + parseInt(stockm);
                precio= trunc(precio,2);        
                precio = parseFloat(precio);
                var importe = 0;    
                importe = parseFloat(importe);
                importe = precio * cant ;
                importe= trunc(importe,2);
                $('#contenido_factura').append('<tr class="fila_factura" id="fila_factura_'+k+'"> '+
                        '<td>' + i + '</td> <td>' + nom  + '</td> <td>' + stock + '</td>' +
                        '<td>' + cant + '</td> <td>' + getAddZerosToRight(importe) + '</td> <td>' + getAddZerosToRight(precio) + '</td> <td>' + stocka + '</td> <td>' + stockm + '</td> ' +                       
                        '</tr>'
                        );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
                $('#contenido_factura').append('<tr class="fila_factura"> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>');  
            }            
        }
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
    
    
    //======================================XxXXxxXxxxxxx=============================================
    function getfechaAct(){
        n =  new Date();
        y = n.getFullYear();
        m = n.getMonth() + 1;
        if(m<10){m="0"+m;}
        d = n.getDate();
        if(d<10){d="0"+d;}
        return y + "-" + m + "-" + d;
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
     
         // ========================== ecxportar pdf =====================================
    $('#btn_exportar_producvend').click(function(){
        
       var fi = $('#txt_fechai').val();
        var ff = $('#txt_fechaf').val();
        var prov = $('#cbx_productos').val();
         $.ajax({
            type:"GET",
            data:{txt_fechaf:ff,txt_fechai:fi,cbx_productos:prov},
            url: 'reporte_producto_vendidopdf_c',
            success: function(response){
             alertify.success('descargando pdf');
            window.location="reporte_producto_vendidopdf_c.action";
                }
        });
        
    }); 
    
    
    
    function load(){
        $('#div_fechaf').hide();
        $('#div_mes').hide();
        $('#div_anio').hide();
        $('#txt_anio').val("2019");
        $('#txt_fechai').val(getfechaAct().substring(0,8)+"01");
        $('#txt_fechaf').val(getfechaAct());        
        fill_cbx_pro();
        listar_provendidos();
    }
});
