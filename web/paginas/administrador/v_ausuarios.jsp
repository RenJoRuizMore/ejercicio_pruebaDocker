<%-- 
    Document   : usuarios
    Created on : 14-jul-2018, 16:59:59
    Author     : Rene Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<title>Foto Studio Carrasco</title>
	<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
      
        <%@ include file="../plantillas/enlaces_css.jsp" %> 
        <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">-->
        <%@ include file="../plantillas/enlaces.jsp" %> 
        <script type="text/javascript" src="./funciones_js/administrador/JS_Administrador.js"> </script>
        <!--
        <script type="text/javascript" src="./funciones_js/administrador/administrados_usuario.js"> </script>-->
        <style type="text/css">
            .separador{
                margin-left: 10% !important;
            }
            .centrar{
                text-align: center;
            }
            .texto{
                font-size: 20px !important;
            }
            
        </style>
</head>
<body class="inner_page innerpage">
    
    <div class="bg_parallax" id="inb">
        
        <!-- SideBar -->
	<%@ include file="../plantillas/administrador/menu.jsp" %> 
        
        <!-- Contenido de pagina incluye cabecera de arriba con el contenido -->
        <div class="site-overlay"></div>
        <div id="container">
            <!--cabecera contenedor -->
            <%@ include file="../plantillas/administrador/header.jsp" %>            
            <!--final de cabecera -->
          
            <!--  cuerpo-->
            <div class="container">
                <div class="row">
                <!--content-->
                  <div class="col-md-12 basic">
                      <div class="place_li_cont">
                               <!--Blog post style one-->
                            <div class="post p_style_one">
                                <div class="post_info">
                                    <h2><a href="#">Mantenimiento Usuarios</a><span></span></h2>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="input-group date" id="datetimepicker1">
                                                <input type="text" class="form-control" placeholder="Nombre completo" id="txt_busqueda">
                                                <span class="input-group-addon">
                                                    <span class="fa fa-search"></span>
                                                </span>
                                             </div>
                                        </div>
                                        <div class="col-md-2 col-md-offset-5">
                                            <button id="btn_agregar" type="button" class="btn btn-lg btn-primary pull-right"
                                             data-toggle="modal" data-target="#nuevoProducto">
                                            <i class="fa  fa-user"></i> Agregar Usuario</button>
                                        </div>
                                    </div>
                                <hr>
                                  <div class="p_text">
                                       <table class="table table-hover text-center">
                                          <thead>
                                            <tr>
                                              <th class="text-center">#</th>
                                              <th class="text-center">Nombre Completo</th>
                                              <th class="text-center">Rol</th>
                                              <th class="text-center">E-mail</th>
                                              <th class="text-center">Estado</th>
                                              <th class="text-center">Editar / Inhabilitar</th>
                                              <!--<th class="text-center">Eliminar</th>-->
                                            </tr>
                                          </thead>
                                          <tbody id="contenido">
                                              
                                          </tbody>
                                        </table>
                                  </div>
                                  <div aria-label="Page navigation example">
                                        <ul class="pagination pagination-sm" id="boton_lista">
								  	
					</ul>
                                  </div>
                                </div>
                           </div>
                      </div>
                  </div>
            </div>
        </div>
        <!-- end cuerpo  -->

        </div>
        <!-- fin del contenedor !-->

    </div>
	

    
   <!-- dmodalUsuario Nueva Usuario -->
    <div class="modal fade" id="nuevoProducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"><i class='glyphicon glyphicon-edit'></i> 
                           <b><span id="span_nuevo">Nuevo</span><span id="span_actualizar">Actualizar</span> Usuario</b>
                        </h4>
                  </div>
                  <div class="modal-body">
                        <form class="form-horizontal" method="post" id="editar_usuario" name="editar_usuario">
                        
                        <div class="form-group">
                            <label for="mod_estado" class="col-sm-3 control-label">Rol Usuario</label>
                            <div class="col-sm-8">
                             <select class="form-control" id="cbx_rol_name" name="cbx_rol_name" required>
                                    <option value=""> ======== Seleccione Rol ======== </option>
                              </select>
                            </div>
                        </div>    
                        <div id="resultados_ajax2"></div>
                          <div class="form-group">
                                <label for="mod_codigo" class="col-sm-3 control-label">Nombre</label>
                                <div class="col-sm-8">
                                  <input type="text" class="form-control" id="txt_nombrepersona" name="txt_nombrepersona" placeholder="Nombre " required>
                                </div>
                          </div>
                          <div class="form-group">
                                <label for="descripcion" class="col-sm-3 control-label">Email</label>
                                <div class="col-sm-8">
                                  <input type="text" class="form-control" id="txt_email" name="txt_email" placeholder="Correo Electronico" required>
                                </div>
                          </div>
                          <div class="form-group">
                                <label for="descripcion" class="col-sm-3 control-label">Usuario</label>
                                <div class="col-sm-8">
                                  <input type="text" class="form-control" id="txt_usuario" name="txt_usuario" placeholder="usuario" required maxlength="20">
                                </div>
                          </div>
                          <div class="form-group">
                                <label for="descripcion" class="col-sm-3 control-label">Password</label>
                                <div class="col-sm-8">
                                  <input type="text" class="form-control" id="txt_password" name="txt_password" placeholder="password" required maxlength="20">
                                </div>
                          </div>
                        <div class="form-group" id="div_estado">
                                <label for="mod_estado" class="col-sm-3 control-label">Estado</label>
                                <div class="col-sm-8">
                                 <select class="form-control" id="cbx_estado" name="cbx_estado" required>
                                        <option value="1"> Habilitado </option>
                                        <option value="0"> Inactivo </option>
                                  </select>
                                </div>
                        </div>    
                  </div>
                  <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="crearusuario">Guardar Usuario</button>
                        <button type="button" class="btn btn-primary" id="modificarusu">Actualizar Usuario</button>
                  </div>
                  </form>
                </div>
          </div>
        </div>

     <!-- finish in modals -->         

</body>
</html>
