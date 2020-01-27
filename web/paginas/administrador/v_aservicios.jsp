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
        <script type="text/javascript" src="./funciones_js/administrador/JS_Aservicio.js"> </script>
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
            <!-- cabecera subtitulos-->
            <div class="container head_filters">
              <div class="row">
                  <div class="col-md-12 basic">
                      <p> Musica is my life</p>
                  </div>
              </div>
            </div>
            <!-- end cabecera subtitulos-->
            
            <!--  cuerpo-->
            <div class="container" style="background:white;">
                <div class="row" style="padding:20px;">
                    <h2><a href="#">Mantenimiento Servicios</a><span></span></h2>
                </div>  
                <div class="row" style="padding-bottom:20px;">
                    <div class="col-md-5">
                        <div class="input-group date" id="datetimepicker1">
                            <input type="text" class="form-control" placeholder="Codigo o Descripcion del Servicio" id="texto_busqueda_servicio">
                            <span class="input-group-addon">
                                <span class="fa fa-search"></span>
                            </span>
                         </div>
                    </div>
                    <div class="col-md-2 col-md-offset-5">
                            <div class="pull-right">
                                    <button href="#!" class="btn btn-primary btn-raised" id="btn_agregar_p" data-toggle="modal" data-target="#nuevoProducto" ><i class="fa fa-th"></i> Nuevo Servicio</button>
                            </div>	
                    </div>
                </div> 
                <div class="table-responsive" style="padding-bottom:5px;">
                    <table class="table table-hover text-center">
                        <thead>
                          <tr>
                            <th class="text-center">#</th>
                                <th class="text-center">Codigo</th>
                                <th class="text-center">Descripcion</th>
                                <th class="text-center">Precio</th>
                                <th class="text-center">Estado</th>
                                <th class="text-center">Editar/inhabilitar</th>
                          </tr>
                        </thead>
                        <tbody id="contenido_producto">

                        </tbody>
                    </table>
                    <div aria-label="Page navigation example">
                        <ul class="pagination pagination-sm" id="boton_lista_producto">

                        </ul>
                    </div>
                </div>
                
            </div>
                
              
            <!-- end cuerpo  -->
       
        <!-- fin del contenedor !-->

    </div>
	
    </div>
            
            
    <!-- start modals  persona -->

    <!-- dmodalProducto Nuevo Producto -->
    <div class="modal fade" id="nuevoProducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"><i class='glyphicon glyphicon-edit'></i> <b><span id="span_nuevo_p">Nuevo</span><span id="span_actualizar_p">Actualizar</span> Servicio</b></h4>
                  </div>
                  <div class="modal-body">
                        <form class="form-horizontal" method="post" id="editar_producto" name="editar_producto">
                            <div id="resultados_ajax2"></div>
                            <div class="form-group">
                                <label for="mod_codigo" class="col-sm-3 control-label">Código</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Txt_codigo_producto" name="Txt_codigo_producto" placeholder="Código del servicio"  maxlength="4"required>                                        
                                </div>
                            </div>
                          
                            <div class="form-group">
                                <label for="descripcion" class="col-sm-3 control-label">Descripción</label>
                                <div class="col-sm-8">
                                  <input type="text" class="form-control" id="Txt_descripcion_producto" name="Txt_descripcion_producto" placeholder="Descripción del servicio" required>
                                </div>
                            </div>
                            <div class="form-group">
                                  <label for="mod_precio" class="col-sm-3 control-label">Precio</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Txt_precio_producto" name="Txt_precio_producto" placeholder="Precio del servicio" required pattern="^[0-9]{1,5}(\.[0-9]{0,2})?$" title="Ingresa sólo números con 0 ó 2 decimales" maxlength="8">
                                  </div>
                            </div>
                            <div class="form-group" id="div_estado_p">
                                <label class="col-sm-3 control-label">Estado</label>
                                <div class="col-sm-8">
                                 <select class="form-control" id="Txt_estado_producto" name="Txt_estado_producto;" required>
                                        <option value="1"> Habilitado </option>
                                        <option value="0"> Inactivo </option>
                                  </select>
                                </div>
                            </div> 

                  </div>
                  <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="crearpro">Guardar Servicio</button>
                        <button type="button" class="btn btn-primary" id="modificarpro">Actualizar Servicio</button>  
                  </div>
                            </form>
                </div>
          </div>
        </div>

     <!-- finish in modals -->         

</body>
</html>
