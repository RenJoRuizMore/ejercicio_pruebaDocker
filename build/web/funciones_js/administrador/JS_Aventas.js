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
        listar_ventas();
    });
    
    
    //LLENAR COMBO PROVEEDOR
    function fill_cbx_vendedores(){
        $.ajax({
            type:"GET",
            url: 'fill_combo_vendedores',
            success: function(response){
            var cant_list=response.lista_usuario.length;
                 $('.opciones').remove();// remover los datos
                 for(var x=0;x<cant_list;x++){                           
                     $('#cbx_vende').append( '<option class="opciones" value="'+response.lista_usuario[x].id_usuario+'">'+response.lista_usuario[x].nombre_persona+'</option>' );
                }
            }
        });       
    }    
    
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    //::::::::::::::::::::::::::::::::::::::::::   ************************************:+:
    
    function listar_ventas() {
        var anio = $('#txt_anio').val();
        var fi = $('#txt_fechai').val();
        var ff = $('#txt_fechaf').val();
        var mes = $('#cbx_mes').val();
        var opc = $('#cbx_opcion').val();
        var prov = $('#cbx_vende').val();
        var tipo = $('#cbx_tipo').val();
        var turno = $('#cbx_turno').val();
        $.ajax({
            type:"GET",
            data:{cbx_opcion:opc,cbx_mes:mes,txt_fechaf:ff,txt_fechai:fi,txt_anio:anio,cbx_vende:prov,
            cbx_tipo:tipo,cbx_turno:turno},
            url: 'busqueda_ventass',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda_ventas.length;//console.log("c- "+cantidad);
                var listaa=response.lista_busqueda_ventas;
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
         var suma=0;
        for(var i=inicio,k=inicio-1;i<=fin;i++,k++){
            if (k < cantidad_datos) {
                
                var cod = respondar[k].numero_pedido;
                var monto = respondar[k].monto_total_pedido ;
                suma = suma + monto;
                var fecha = respondar[k].fecha_pedido ;
                fecha = fecha.substring(8,10)+"-" +fecha.substring(5,7)+"-"+fecha.substring(0,4);
                var usuario = respondar[k].nombre_usuario ;
                
                var tipo = respondar[k].tipo_documento ;
                if(tipo == 1 )tipo= '<span class="label label-success">Boleta</span>';
                else tipo = '<span class="label label-info">Factura</span>'   ;
                
                var tpago = respondar[k].tipo_pago ;
                if(tpago == 1 )tpago= '<span class="label label-success">Tarjeta</span>';
                else tpago = '<span class="label label-warning">Efectivo</span>'   ;
                
                var stado = respondar[k].estado_pedido ;
                if(stado == 1 )stado= '<span class="label label-success">Cancelado</span>';
                else stado = '<span class="label label-danger">Anulado</span>'   ;
                
                $('#contenido_factura').append('<tr class="fila_factura" id="fila_factura_'+k+'"> '+
                        '<td>' + i + '</td> <td>' + tipo  + '</td> <td>' + fecha  + '</td>' +
                        '<td>' + usuario + '</td> <td>' + cod + '</td> <td>' + tpago + '</td> <td>' + getAddZerosToRight(monto) + '</td> <td>' + stado + '</td> ' +                         
                        '</tr>'
                        );//'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
                $('#contenido_factura').append('<tr class="fila_factura"> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>');  
            }            
        }
        // suma
        $('#lbl_suma').text(getAddZerosToRight(trunc(suma,2)));
        
        
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
       
  
    // ============= reporte para ventas ====================
    // ============= conlista se ejecuta antes de listar 
    // =================================================
      $('#bnt_exportar').click(function(){
         
        var anio = $('#txt_anio').val();
        var fi = $('#txt_fechai').val();
        var ff = $('#txt_fechaf').val();
        var mes = $('#cbx_mes').val();
        var opc = $('#cbx_opcion').val();
        var prov = $('#cbx_vende').val();
        var tipo = $('#cbx_tipo').val();
        var turno = $('#cbx_turno').val();
        $.ajax({
            type:"GET",
            data:{cbx_opcion:opc,cbx_mes:mes,txt_fechaf:ff,txt_fechai:fi,txt_anio:anio,cbx_vende:prov,
            cbx_tipo:tipo,cbx_turno:turno},
            url: 'reporte_ventaspdf',
            success: function(response){
            alertify.success('descargando pdf');
            window.location="reporte_ventaspdf.action";
            } 
        });
    
      });
        
    function load(){
        $('#div_fechaf').hide();
        $('#div_mes').hide();
        $('#div_anio').hide();
        $('#txt_anio').val("2019");
        $('#txt_fechai').val(getfechaAct());
        $('#txt_fechaf').val(getfechaAct());
        
        fill_cbx_vendedores();
        listar_ventas();
    }
});





