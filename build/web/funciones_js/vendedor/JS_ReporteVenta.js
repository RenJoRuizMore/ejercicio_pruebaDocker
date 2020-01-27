/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){

   var lista_usu;
   var id_usuario_venta_e= $('#obj_id_user').val();
   load();
 // LISTAR tmp_
    function listar_tmp() {
        $.ajax({
            type: "GET",
            data: {id_usuario_venta:id_usuario_venta_e },
            url: 'listar_ventas_report',
            success: function (response) {
                // cojia y limpiaba dagtos 
                console.log(response.lista_venta_usu);
                var cantidad = response.lista_venta_usu.length;//console.log("count lista_detsalida : "+cantidad);
                lista_usu = response.lista_venta_usu;
                agregar_filas_tmp_detfactura(cantidad, lista_usu);
                
            }
        });
    }  
 // agregar las filas  para vendedores
    function agregar_filas_tmp_detfactura(cantidad_datos, respondar) {
        $('.fila_pro_tmpdetfac').remove();//clase CSS q remove todas las acitvite
       var estado = "";
       var clase_e= "";
       var suma=0;
        for (var k = 0; k < cantidad_datos; k++) {
            var numero_pedido = respondar[k].numero_pedido;
            var pago = respondar[k].pago_con;
            var igv= respondar[k].igv_pedido;  //<span class="label label-success">Cancelado</span>
            var vuelto = respondar[k].vuelto;
            var total = respondar[k].monto_total_pedido;
            var n_estado =respondar[k].estado_pedido;
            suma = suma + total;
            if (n_estado == 1) {
               estado = " Cancelado";
               clase_e= "label-success";
            }
             if (n_estado == 0) {
               estado = " Anulada ";
               clase_e= "label-success";
            }
             if (n_estado == 2) {
               estado = " Sin Atender";
               clase_e= "label-success";
            }
            $('#contenido_tmp_detfactura').append('<tr class="fila_pro_tmpdetfac" id="' + k + '"> ' +
                    '<td>' + (k + 1) + '</td> <td>' + numero_pedido + '</td> <td>' + getAddZerosToRight(pago) + '</td>' +
                    '<td>' + getAddZerosToRight(igv) + '</td> <td>' + getAddZerosToRight(vuelto) + '</td>' +
                    '<td>' + getAddZerosToRight(total) + '</td> <td> <span  class="label '+clase_e+'" >' + estado + ' </span> </td>' +
                    '</tr>'
                    );
        }
        
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
   
    $('#btn_imprimirventa').click(function(){
          $.ajax({
            type: "GET",
            data: {id_usuario_venta:id_usuario_venta_e },
            url: 'imprimir_lista_venta',
            success: function (response) {
                // cojia y limpiaba dagtos 
                alertify.success('exporto con exito');
               window.location="imprimir_lista_venta.action";
                
            }
        });
        
    });
      
      
   
   
   
   
    function load() {
        listar_tmp();
        id_usuario_venta_e =  $('#obj_id_user').val();
        
    }
   
   
   
});
    
    



