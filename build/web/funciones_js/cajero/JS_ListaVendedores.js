/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 

$(document).ready(function(){

   var lista_usu;
   load();
 // LISTAR tmp_
    function listar_tmp() {
        $.ajax({
            type: "GET",
            data: {},
            url: 'listar_vendedores_venta',
            success: function (response) {
                // cojia y limpiaba dagtos 
                console.log(response.lst_datos_usuario);
                var cantidad = response.lst_datos_usuario.length;//console.log("count lista_detsalida : "+cantidad);
                lista_usu = response.lst_datos_usuario;
                agregar_filas_tmp_detfactura(cantidad, lista_usu);
                click_boton_venta();
                venta_producto();
                // click a un determinado boton  
            }
        });
    }  
 // agregar las filas  para vendedores
    function agregar_filas_tmp_detfactura(cantidad_datos, respondar) {
        $('.fila_pro_tmpdetfac').remove();//clase CSS q remove todas las acitvite
       
        for (var k = 0; k < cantidad_datos; k++) {
            var turno = respondar[k].turno;
            var nombre = respondar[k].nombre_persona;
            var id= respondar[k].id_usuario;
            $('#contenido_tmp_detfactura').append('<tr class="fila_pro_tmpdetfac" id="' + k + '"> ' +
                    '<td>' + (k + 1) + '</td> <td>' + turno + '</td> <td>' + nombre + '</td>' +
                    '<td> <button class="btn btn-primary ventas_dia"  id= ' + k + ' > <i class="fa fa-file-pdf-o fa-2x">\n\
                     </i></button> </td>' 
                    + '<td> <button class="btn btn-primary productos_dia"  id= '+ k + ' > <i class="fa fa-file-pdf-o fa-2x">\n\
                     </i></button> </td>' 
                    + 
                    '</tr>'
                    );
        }
    }  
   
      
   function click_boton_venta(){

            $('.ventas_dia').click(function(){
               var id_a_ventas_dia = $(this).attr('id');
               var idventa = lista_usu[id_a_ventas_dia].id_usuario;
               var turno = $('#txt_turno').text().trim();
                   
                        $.ajax({
                            type: "POST",
                            data: {turno_e: turno ,id_usuario_venta_e:idventa },
                            url: 'pdf_ventadiaria',
                            success: function (response) {
                                window.location="pdf_ventadiaria.action";
                                alertify.success('Se exporto exitosamente');
                            }
                        });
                    
             });
//
   }
   
   function venta_producto(){
         $('.productos_dia').click(function(){
               var id_a_productos_dia = $(this).attr('id');
               var idproducto_v = lista_usu[id_a_productos_dia].id_usuario;
               var turno = $('#txt_turno').text().trim();
                        $.ajax({
                            type: "POST",
                            data: {turno_e: turno ,id_usuario_venta_e:idproducto_v},
                            url: 'pdf_ventaproducto',
                            success: function (response) {
                                window.location="pdf_ventaproducto.action";
                                alertify.success('Se exporto exitosamente');
                            }
                        });
                    
             });
       
       
   }
   
   
    $('#btn_imprimir_efectivo').click(function () {

        $.ajax({
            type: "POST",
            data: {},
            url: 'pdf_ventaefectivo',
            success: function (response) {
                var configuracion_ventana = "menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes";
                var ventana = window.open('http://localhost:8080/StudioCarrasco/pdf_ventaefectivo.action', 'Factura', configuracion_ventana); //abrimos una ventana vacía nueva

            }
        });

    });
   
   
    $('#btn_imprimir_tarjeta').click(function () {

        $.ajax({
            type: "POST",
            data: {},
            url: 'pdf_ventatarjeta',
            success: function (response) {
                var configuracion_ventana = "menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes";
                var ventana = window.open('http://localhost:8080/StudioCarrasco/pdf_ventatarjeta.action', 'Factura', configuracion_ventana); //abrimos una ventana vacía nueva

            }
        });

    });
   
   
   
   
    function load() {
        listar_tmp();
        
    }
   
   
   
});
    