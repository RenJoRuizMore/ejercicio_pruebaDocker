/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
     var monto_inicio;
     var motivo_apertura_caja;
     var turno;
     var id_usuario=$('#obj_id_user').val();;
     inicio();
     //==============abrir caja =============
     $('#btn_cerrarcaja').click(function(){
         capturar_textos();
          $.ajax({
            type:"POST",
            data:{monto_inicio_e:monto_inicio,motivo_apertura_caja_e:motivo_apertura_caja,
                turno_e:turno, id_usuario_e :id_usuario},
            url: 'cerrar_caja',
            success: function(response){
                // cojia y limpiaba datos 
                var resul=response.resultado;
                console.log(response.resultado);
                if(resul=== 1){
                    alertify.success('se cerro caja exitosamente');
                     location.reload();
                }
              else{
                  alertify.dialog('alert').set({transition: 'flipy', title: 'Ventas', message: 'la caja ya esta aperturada porfavor verificar'}).show();
                   inicio();
              }
            }           
        });
     });
         
     
     function capturar_textos(){
          monto_inicio= $('#txt_monto_in').val();
          motivo_apertura_caja=$('#Txt_motivo').val();
          turno =$('#txt_turno').text().trim();
          id_usuario=$('#obj_id_user').val();
     }
     
     
     //==============cancelar caja  =============
     $('#btn_cancelarcaja').click(function(){
        inicio();
     });
     
     
     $('#btn_deacuerdo').click(function(){
          $('#btns_control_caja').show();
         
     });
         
     
     
     $('#btn_enviar_motivo').click(function(){
        $('#nuevoProducto').hide(); 
        $('#btns_control_caja').show();
     });   
 
     //=========== dessactivacion de submenus 
     function desactivar_menus(){
         $('#submenu_ingventas').hide();
         $('#submenu_reportvende').hide();
         $('#submenu_cerrar-caja').hide();
     }
     
      //=========== activacion de submenus 
     function activar_menus(){
         $('#submenu_ingventas').show();
         $('#submenu_reportvende').show();
         $('#submenu_cerrar-caja').show();
     }
     
     //======== verificacion si aperturo caja o no 
     function verificar_apertura_caja(){
            capturar_textos();
            $.ajax({
            type:"POST",
            data:{turno_e:turno, id_usuario_e :id_usuario},
            url: 'verificar_aperturar_caja',
            success: function(response){
                // cojia y limpiaba datos 
                var resul=response.resultado;
                console.log(response.resultado);
                if(resul=== 1){
                   // se aperturo caja 
                   activar_menus();
                }
              else{
                  // no se aperturo caja 
                 desactivar_menus(); 
              }
            }           
        });
         
     }
     
     
     
 
    function inicio(){
        verificar_apertura_caja();
        $('#btns_control_caja').hide();
        $('#txt_monto_in').val('');
    } 
 
});
