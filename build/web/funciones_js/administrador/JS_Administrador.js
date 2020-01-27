/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// jquery
$(document).ready(function(){
    
    var p_id_persona=0;
    //
    fill_cbx_rol_user();
     listar("");
    
    
    
    
    
    
    $('#btn_agregar').click(function(){
        $('#crearusuario').show();
        $('#modificarusu').hide();
        $('#div_estado').hide();  
        $('#span_actualizar').hide();  
        $('#span_nuevo').show();
        limpiar();
    });
    
    // insertar datos persona
    // registrar usuario
    $('#crearusuario').on('click',function(){
         // ---cargar datos---      
         // ------finish datos--
        var cbx_rol_name=$('#cbx_rol_name').val();
        var nombre=$('#txt_nombrepersona').val();
        var email=$('#txt_email').val();         
        var login=$('#txt_usuario').val();
        var pass=$('#txt_password').val();
        nombre=nombre.trim();
        email=email.trim();
        login=login.trim();
        pass=pass.trim();
        if(cbx_rol_name==""){alertify.error('Debe seleccionar Rol de usuario');return};
        if(nombre.length==0){alertify.error('Debe ingresar Nombre de usuario');return};
        if(email.length==0){alertify.error('Debe ingresar email de usuario');return};
        if(login.length==0){alertify.error('Debe ingresar usuario');return};
        if(pass.length==0){alertify.error('Debe ingresar Password');return};
        
        //console.log(login,pass,combo_tipo,estado,fecha_nacimiento );
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{cbx_rol_name:cbx_rol_name,txt_nombrepersona:nombre,
                  txt_email:email,txt_usuario:login,txt_password:pass
                  },
                url: 'insertar_usuario',
                success: function(response){
                    alertify.success('se inserto exitosamente');
                    listar('');
                    limpiar();                   
               }                      
        });        
    });
    
    // busquedad filtrada
    // busqueda
    function listar(str) { 
        $.ajax({
            type:"GET",
            data:{texto_busqueda:str},
            url: 'busqueda',
            success: function(response){
                // cojia y limpiaba dagtos 
                var cantidad=response.lista_busqueda.length;
                var listaa=response.lista_busqueda;
                var limite=4;
                $('.header_lis').remove();
                // agregar los botones
                agregar_botones_lista(limite,cantidad);
                // remover el contenido
                agregar_filas(1,limite,cantidad,listaa);
               // click a un determinado boton
               $('.hojas').click(function(){
                  var ids=$(this).attr('id');
                  //alert(ids);
                  $('.botones').removeClass('active');
                 $('#lista_btn'+ids).addClass('active');
                   agregar_filas(ids,limite,cantidad,listaa);
                 // alert($(this).attr('id'));
                 // se deben mostrar la lista segun sea su orden
                      traer_datos(listaa);
                      //eliminar_objetos(listaa);
               });
               traer_datos(listaa);
               //eliminar_objetos(listaa);
            }
           
        }) ;
        
      
        
        
    }
    
    $('#txt_busqueda').on('keyup',function(){       
        var valor_texto=$(this).val();
        listar(valor_texto);
    });
    
   
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
                var nombre_completo = respondar[k].nombres ;
                var tipo_usuario = respondar[k].tipo_usuario;
                var email = respondar[k].email ;
                var stado = respondar[k].estado_usuario;
                if(stado == 1 )stado= '<span class="label label-success">Habilitado</span>';
                else stado = '<span class="label label-danger">Inactivo</span>'   ;
                $('#contenido').append('<tr class="fila" id="fila_'+k+'"> <td>' + i + '</td> <td>' + nombre_completo + '</td> <td>' + tipo_usuario +
                        '</td> </td><td>' + email + '</td> </td><td>' + stado + '</td>' +
                        '<td><a  class="btn btn-md btn-primary editable" id='+k+' ><i class="fa  fa-edit"></i></a></td>' +                        
                        '</tr>'
                        ); 
                        //'<td><a  class="btn btn-md btn-primary btn_eliminar" id='+k+' ><i class="fa fa-remove"></i></a></td>' +
            }else{
              $('#contenido').append('<tr class="fila"> <td>' + " " + '</td> <td>' + "" + '</td> <td>' + "" +
                    '</td><td>' + "" + '</td>' +
                    '<td></td>' +
                    '<td></td>' +
                    '</tr>'
                    );  
            }            
        }
    }
    
    
    function traer_datos(listaa_datos){
        
        $('.editable').click(function(){
           
            $('#nuevoProducto').modal("show");
            //$('#modificarusu').css("display","block");
            //$('#crearusuario').css("display","none");
            $('#crearusuario').hide();
            $('#modificarusu').show();            
            $('#div_estado').show();
            $('#span_nuevo').hide();
            $('#span_actualizar').show();
            //document.getElementById('crearusuario').style.display= 'none';
            //document.getElementById('modificarusu').style.display= 'block';
            
            var id_actualizar = $(this).attr('id');
                // mostar datos
            $('#cbx_rol_name').val(listaa_datos[id_actualizar].id_tipo_usuario);
            $('#txt_nombrepersona').val(listaa_datos[id_actualizar].nombres);
            $('#txt_email').val(listaa_datos[id_actualizar].email);         
            $('#txt_usuario').val(listaa_datos[id_actualizar].login);
            $('#txt_password').val(listaa_datos[id_actualizar].passwordd);
            $('#cbx_estado').val(listaa_datos[id_actualizar].estado_usuario);

            p_id_persona=listaa_datos[id_actualizar].id_usuario;
        
        });
    }
    
    
    
    $('#modificarusu').click(function(){
        actualizar(p_id_persona);
    });
    
    function actualizar(id_persona){
        
        var cbx_rol_name=$('#cbx_rol_name').val();
        var nombre=$('#txt_nombrepersona').val();
        var email=$('#txt_email').val();         
        var login=$('#txt_usuario').val();
        var pass=$('#txt_password').val();
        var estado=$('#cbx_estado').val();
        nombre=nombre.trim();
        email=email.trim();
        login=login.trim();
        pass=pass.trim();
        if(cbx_rol_name==""){alertify.error('Debe seleccionar Rol de usuario');return};
        if(nombre.length==0){alertify.error('Debe ingresar Nombre de usuario');return};
        if(email.length==0){alertify.error('Debe ingresar email de usuario');return};
        if(login.length==0){alertify.error('Debe ingresar usuario');return};
        if(pass.length==0){alertify.error('Debe ingresar Password');return};
        
        //console.log(login,pass,combo_tipo,estado,fecha_nacimiento );
        // crear objeto response
         $.ajax({
            type:"POST",
            data:{txt_nombrepersona:nombre,txt_email:email,cbx_rol_name:cbx_rol_name,
                  txt_usuario:login,txt_password:pass,id_persona_e:id_persona,cbx_estado_user:estado
                  },
                url: 'actualizar_usuario',
                success: function(response){
                    alertify.success('Se actualizo exitosamente');
                    listar('');
                    $('#nuevoProducto').modal("hide");  
                }                      
        });        
        
        
    }
    
    
    function limpiar(){
        $('#cbx_rol_name').val("");
        $('#txt_nombrepersona').val("");
        $('#txt_email').val("");         
        $('#txt_usuario').val("");
        $('#txt_password').val("");
        $('#cbx_estado').val("1");
    }
    
    //LLENAR COMBO TIPO USER
    function fill_cbx_rol_user(){
        $.ajax({
               type:"GET",
               url: 'fill_combo_rol',
              success: function(response){
               var cant_list=response.lista_roles.length;
//                     
                     // remover los datos
                     $('.opciones').remove();
                   for(var x=0;x<cant_list;x++){
//                           
                    $('#cbx_rol_name').append( '<option class="opciones" value="'+response.lista_roles[x].id_tipousuario+'">'+response.lista_roles[x].descripcion_tipousuario+'</option>' );

                   }
               }
           }) ;       
   }
    
    
    
    
    
//     // eliminar objeto
//    function eliminar_objetos(listaa_datos) {
//        //        alertify.confirm("This is a confirm dialog.",
//        function(){
//          alertify.success('Ok');
//        },
//        function(){
//          alertify.error('Cancel');
//        });
//        $('.btn_eliminar').click(function () {
//            //var id_eliminar = $(this).attr('id');
//           // alert(id_eliminar+listaa_datos[id_eliminar].nombres + " " + listaa_datos[id_eliminar].apellido_paterno + " " + listaa_datos[id_eliminar].apellido_materno);
//            // agregar  el switch
//           // alert(listaa_datos[id_eliminar].id_usuario);
//            swal({ 
//		  	title: 'Estas seguro de eliminar a un usuario  ?',
//		  	text: "Esta elimando al usuario: ",
//		  	type: 'warning',
//		  	showCancelButton: true,
//		  	confirmButtonColor: '#03A9F4',
//		  	cancelButtonColor: '#F44336',
//		  	confirmButtonText: '<i class="zmdi zmdi-run"></i> Yes, Exit!',
//		  	cancelButtonText: '<i class="zmdi zmdi-close-circle"></i> No, Cancel!'
//		}).then(function () {
//                    // ejecutar la leiminacion
////                     $.ajax({
////                      type:"POST",
////                      data:{id_usuario_eliminar:listaa_datos[id_eliminar].id_usuario},
////                      url: 'eliminar_usuario',
////                      success: function(response){
////                          console.log(response.msg);
////                         var ht = response.msg;
////			$("#resp").html(ht);
//                          //$('.result').html(response);
////                      }
////                  }) ;   
//			 //$('#fila_'+id_eliminar).remove();
//		});
//            });
//        }
//    //finish eliminar
    
    
    
    
});





