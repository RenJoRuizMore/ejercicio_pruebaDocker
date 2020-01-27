/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// jquery
$(document).ready(function () {
    // variables  
    var opcion_reporte = 0;
    //funcion inicial    
    load();
    var id_mes = 1;
    var id_anio = 1;
    //:::::::::::::::::::::::::::::::::::::::::: Reporte sin parametros   ************************************:+:
    // 
    // si se escogio el dia 
    // evento change de jquery
    $('#cbxopc').change(function () {
        //obtener el valor del texto seleccionado
        var id = $('#cbxopc option:selected').val();
        // selecion por dia 
        if (id == 1) {
            opcion_reporte = 1;
            
            ocultar_activar_elementos('none', 'none', 'none');
        }
        // selecion por mes 
        if (id == 2) {
            opcion_reporte = 2;
            ocultar_activar_elementos('block', 'none', 'none');
        }
        // selecion por año 
        if (id == 3) {
            opcion_reporte = 3;
            ocultar_activar_elementos('none', 'block', 'none');
        }
        // selecion por rangog de fechas  
        if (id == 4) {
            opcion_reporte = 4;
            ocultar_activar_elementos('none', 'none', 'block');
        }
    });
    
    
     $('#cbxmes').change(function () {
        id_mes = $('#cbxmes option:selected').val(); 
        
     });
     
 //============================= reporte de salidas ======================================
//=============================================================================================== 
    
//     click reporte de salidas 
    $('#btn_salidas').click(function () {
        
        // por dia 
        if (opcion_reporte == 1) {
            //window.location="reporte_pordia.action";
            $.ajax({
                type: "POST",
                data: {},
                url: 'reporte_pordia',
                success: function (response) {
                    alertify.success('se inserto exitosamente');
                   window.location="reporte_pordia.action";
                }
            });
        }
        // por mes 
        if (opcion_reporte == 2) {
            
            $.ajax({
                type: "POST",
                data: {num_mes:id_mes},
                url: 'reporte_pormes',
                success: function (response) {
                    alertify.success('se inserto exitosamente');
                    window.location="reporte_pormes.action";

                }
            });
        }
        // por año
        if (opcion_reporte == 3) {
            //id_anio = $('#fecha').val(); 
            id_anio= $("#fechanumber").val();
            
            $.ajax({
                type: "POST",
                data: {num_anio: id_anio
                },
                url: 'reporte_poranio',
                success: function (response) {
                   alertify.success('se inserto exitosamente');
                    window.location="reporte_poranio.action";

                }
            });
        }
        // selecion por rangog de fechas
         if (opcion_reporte == 4) {
             
             var fi = $('#fecha_ini').val();
             var ff = $('#fecha_finish').val();
             
            $.ajax({
                type: "POST",
                data: {fini: fi, ffin: ff },
                url: 'reporte_porfechas',
                success: function (response) {
                    alertify.success('se inserto exitosamente');
                    window.location="reporte_porfechas.action";

                }
            });
        }
    });

//============================= reporte de facturas compras ======================================
//===============================================================================================
//     click reporte de salidas 
    $('#btn_facturas').click(function () {
        // por dia 
        if (opcion_reporte == 1) {
            //window.location="reporte_pordia.action";
            $.ajax({
                type: "POST",
                data: {},
                url: 'reportefactura_pordia',
                success: function (response) {
                    alertify.success('se inserto exitosamente');
                   window.location="reportefactura_pordia.action";
                }
            });
        }
        // por mes 
        if (opcion_reporte == 2) {
            
            $.ajax({
                type: "POST",
                data: {num_mes:id_mes},
                url: 'reportefactura_pormes',
                success: function (response) {
                    alertify.success('se inserto exitosamente');
                    window.location="reportefactura_pormes.action";

                }
            });
        }
        // por año
        if (opcion_reporte == 3) {
            //id_anio = $('#fecha').val(); 
            id_anio= $("#fechanumber").val();
            
            $.ajax({
                type: "POST",
                data: {num_anio: id_anio
                },
                url: 'reportefactura_poranio',
                success: function (response) {
                   alertify.success('se inserto exitosamente');
                    window.location="reportefactura_poranio.action";

                }
            });
        }
        // selecion por rangog de fechas
         if (opcion_reporte == 4) {
             
             var fi = $('#fecha_ini').val();
             var ff = $('#fecha_finish').val();
             
            $.ajax({
                type: "POST",
                data: {fini: fi, ffin: ff },
                url: 'reportefactura_porfechas',
                success: function (response) {
                    alertify.success('se inserto exitosamente');
                    window.location="reportefactura_porfechas.action";

                }
            });
        }
    });
//============================= reporte de Productos ======================================
//===============================================================================================
   $('#btn_productos').click(function () {
       
           $.ajax({
                type: "POST",
                data: {},
                url: 'reporteproductos',
                success: function (response) {
                    alertify.success('se inserto exitosamente');
                    window.location="reporteproductos.action";
//                    
                }
            });
   });
   
//============================= reporte de Mostrador ======================================
//===============================================================================================
   $('#btn_mostrador').click(function () {
       
           $.ajax({
                type: "POST",
                data: {},
                url: 'reportemostrador',
                success: function (response) {
                    alertify.success('se inserto exitosamente');
                    window.location="reportemostrador.action";

                }
            });
   });






    //::::::::::::::::::::::::::::::::::::::::::   ************************************:+:
    //::::::::::::::::::::::::::::::::::::::::::******************************************************:+:
    function ocultar_activar_elementos(mes, anio, fechas) {
        $('#cbxmes').css('display', mes);
        $('#cbxanio').css('display', anio);
        $('#fecha_inicial').css('display', fechas);
        $('#fecha_final').css('display', fechas);
    }

    function load() {
        $('#fecha_inicial').css('display', 'none');
        console.log("reporte");//listar_sm("");
    }
});





