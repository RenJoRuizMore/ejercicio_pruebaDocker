
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
        <script type="text/javascript" src="./funciones_js/almacenero/JS_ALverificarstock.js"> </script>

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
        <script type="text/javascript">
        // Solo permite ingresar numeros.
        function soloNumeros(e){
                var key = window.Event ? e.which : e.keyCode
                return (key >= 48 && key <= 57)//return ((key >= 48 && key <= 57) || (key==46))
        }
        function soloNumeros_con_punto(e){
                var key = window.Event ? e.which : e.keyCode
                //return (key >= 48 && key <= 57)
                return ((key >= 48 && key <= 57) || (key==46))
        }
        </script>
</head>
<body class="inner_page innerpage">
    
    <div class="bg_parallax" id="inb">
        
        <!-- SideBar -->
	<%@ include file="../plantillas/almacenero/menu.jsp" %> 
        
        <!-- Contenido de pagina incluye cabecera de arriba con el contenido -->
        <div class="site-overlay"></div>
        <div id="container">
            <!--cabecera contenedor -->
            <%@ include file="../plantillas/administrador/header.jsp" %>            
            <!--final de cabecera -->
          
            <!--salto de lineas  -->
            <div>
                <br><br>
            </div>
            <!-- cuerpo !-->	
            <div class="container" style="background:white;">
                    <div class="row" >
                            <div class="col-xs-12" >
                                    <!-- cuerpocabecera -->	
                                    <h1 class="text-titles"><i class="zmdi zmdi-timer zmdi-hc-fw"></i> Almacén</h1><br>
                                    <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                                            <li class="active"><a href="#new" data-toggle="tab" id="tab_stockmin">Stock minimo </a></li>
                                            <li><a href="#listc" data-toggle="tab" id="tab_listcompra">Lista compra</a></li>
                                            <li><a href="#list" data-toggle="tab" id="tab_productos">Productos</a></li>
                                            <li><a href="#cate" data-toggle="tab" id="tab_categoria">Categoria</a></li>
                                            <li><a href="#provee" data-toggle="tab" id="tab_proveedor">Proveedor</a></li>
                                    </ul>
                                    <!--cuerpobody -->
                                    <div id="myTabContent" class="tab-content">
                                        <div class="tab-pane fade active in" id="new">
                                            <div class="row" style="padding-bottom:12px;">
                                                <div class="col-md-5">
                                                    <div class="input-group date" id="datetimepicker1">
                                                        <input type="text" class="form-control" placeholder="Codigo o Nombre de producto" id="Texto_busqueda_sm">
                                                        <span class="input-group-addon">
                                                            <span class="fa fa-search"></span>
                                                        </span>
                                                     </div>
                                                </div>
                                                <div class="col-sm-3">
                                                  <select class="form-control" id="cbx_filtro_sm"  required>
                                                          <option value="1">En almacen</option>
                                                          <option value="2">En mostrador</option>
                                                  </select>
                                                </div>
                                                 
                                            </div>
                                            <div class="table-responsive">
                                                <table class="table table-hover text-center">
                                                    <thead>
                                                        <tr>
                                                            <th class="text-center">#</th>
                                                            <th class="text-center">Codigo</th>
                                                            <th class="text-center">Cotegoria</th>
                                                            <th class="text-center">Nombre producto</th>
                                                            <th class="text-center">Stock minimo</th>
                                                            <th class="text-center">Stock </th>
                                                            <th class="text-center">Agregar lista compra</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="contenido_sm">
                                                         
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div aria-label="Page navigation example">
                                                <ul class="pagination pagination-sm" id="boton_lista_sm">

                                                </ul>
                                            </div>
                                        </div> 

                                        <div class="tab-pane fade" id="listc">
                                            <div class="row" style="padding-bottom:12px;">
                                                <div class="col-md-5">
                                                    <div class="input-group date" id="datetimepicker1">
                                                        <input type="text" class="form-control" placeholder="Codigo o Nombre de producto" id="texto_busqueda_lc">
                                                        <span class="input-group-addon">
                                                            <span class="fa fa-search"></span>
                                                        </span>
                                                     </div>
                                                </div>
                                                <div class="col-md-7">
                                                    <div class="pull-right">
                                                        
                                                        <!--<s:url id="url_semanal" action="jasper_semanal">             
                                                        </s:url>
                                                        <s:a href="%{url_semanal}">
                                                            <article class="full-box tile  text-center href-pagina" >
                                                                <div class="full-box tile-title text-center text-titles text-uppercase ">
                                                                    Consultas Semanales
                                                                </div>
                                                                <div class="full-box tile-icon text-center">
                                                                    <i class="zmdi zmdi-account"></i>
                                                                </div>
                                                                <div class="full-box tile-number text-titles">
                                                                    <p class="full-box">7</p>
                                                                    <small>Ver Reporte</small>
                                                                </div>
                                                            </article>
                                                        </s:a>  -->                                                      
                                                        
                                                        <a href="#" style="color: #fff;background-color: #218DEA;border-color: #2e6da4;
                                                             padding: 8px 12px;border-radius: 4px;
                                                        transition: 0.3s;font-size: 14px;
                                                        font-weight: normal;
                                                        line-height: 1.42857143;
                                                        text-align: center;" id="imprimir_lista"><i class="fa fa-print"></i> Imprimir Lista</a>
                                                            <!--<button href="%{verificar}" class="btn btn-primary btn-raised"><i class="fa fa-print"></i> Imprimir Lista</button>-->
                                                    </div>	
                                                </div>
                                            </div>
                                            <div class="table-responsive">
                                                <table class="table table-hover text-center">
                                                    <thead>
                                                        <tr>
                                                            <th class="text-center">#</th>
                                                            <th class="text-center">Codigo</th>
                                                            <th class="text-center">Cotegoria</th>
                                                            <th class="text-center">Nombre producto</th>
                                                            <th class="text-center">Stock minimo</th>
                                                            <th class="text-center">Stock </th>
                                                            <th class="text-center">Eliminar</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="contenido_lc">
                                                        
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div aria-label="Page navigation example">
                                                <ul class="pagination pagination-sm" id="boton_lista_lc">

                                                </ul>
                                            </div>
                                        </div>

                                        <div class="tab-pane fade" id="list">

                                                <div class="row" style="padding-bottom:12px;">
                                                    <div class="col-md-5">
                                                        <div class="input-group date" id="datetimepicker1">
                                                            <input type="text" class="form-control" placeholder="Codigo o Nombre de producto" id="txt_busqueda_producto">
                                                            <span class="input-group-addon">
                                                                <span class="fa fa-search"></span>
                                                            </span>
                                                         </div>
                                                    </div>
                                                    <div class="col-md-2 col-md-offset-5">
                                                            <div class="pull-right">
                                                                    <button href="#!" class="btn btn-primary btn-raised" id="btn_agregar_p" data-toggle="modal" data-target="#nuevoProducto" ><i class="fa fa-cube"></i> Nuevo Producto</button>
                                                            </div>	
                                                    </div>
                                                </div>
                                                <div class="table-responsive">
                                                    <table class="table table-hover text-center">
                                                        <thead>
                                                            <tr>
                                                                <th class="text-center">#</th>
                                                                <th class="text-center">Codigo</th>
                                                                <th class="text-center">Categoria</th>
                                                                <th class="text-center">Nombre producto</th>	
                                                                <th class="text-center">Unidad</th>
                                                                <th class="text-center">Precio</th>
                                                                <!--<th class="text-center">Stock</th>-->
                                                                <th class="text-center">Stock minimo</th>
                                                                <th class="text-center">Stock Almacén</th>
                                                                <th class="text-center">Stock mostrador</th>
                                                                <th class="text-center">Stock minimo mostrador</th>
                                                                <th class="text-center">en mostrador</th>
                                                                <th class="text-center">Estado</th>
                                                                <th class="text-center">Editar/inhabilitar</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="contenido_producto">

                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div aria-label="Page navigation example">
                                                    <ul class="pagination pagination-sm" id="boton_lista_producto">

                                                    </ul>
                                                </div>
                                        </div>

                                        <div class="tab-pane fade" id="cate">

                                            <div class="row" style="padding-bottom:12px;">
                                                <div class="col-md-5">
                                                    <div class="input-group date" id="datetimepicker1">
                                                        <input type="text" class="form-control" placeholder="Nombre de categoria" id="txt_busqueda_categoria">
                                                        <span class="input-group-addon">
                                                            <span class="fa fa-search"></span>
                                                        </span>
                                                     </div>
                                                </div>
                                                <!--<div class="col-md-1">
                                                     <button href="#!" class="btn btn-primary " id="btn_actualizar_categoria" ><i class="fa fa-refresh"></i> Actualizar</button>
                                                </div>-->
                                                <div class="col-md-2 col-md-offset-5">
                                                    <div class="pull-right">
                                                        <button  class="btn btn-primary btn-raised" data-toggle="modal" data-target="#nuevaCategoria" 
                                                                id="btn_agregar_c" ><i class="fa fa-server"></i> Nueva Categoria</button>
                                                    </div>	
                                                </div>
                                            </div>
                                            <div class="table-responsive">
                                                <table class="table table-hover text-center">
                                                    <thead>
                                                        <tr>
                                                            <th class="text-center">#</th>
                                                            <th class="text-center">Nombre</th>
                                                            <th class="text-center">Estado</th>
                                                            <th class="text-center">Editar / Inhabilitar</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="contenido_categoria">

                                                    </tbody>
                                                </table>
                                            </div>
                                            <div aria-label="Page navigation example">
                                                <ul class="pagination pagination-sm" id="boton_lista_categoria">

                                                </ul>
                                            </div>
                                        </div>
                                        
                                        <div class="tab-pane fade in" id="provee">
                                            <div class="row" style="padding-bottom:12px;">
                                                <div class="col-md-5">
                                                    <div class="input-group date" id="datetimepicker1">
                                                        <input type="text" class="form-control" placeholder="Nombre Proveedor" id="texto_busqueda">
                                                        <span class="input-group-addon">
                                                            <span class="fa fa-search"></span>
                                                        </span>
                                                     </div>
                                                </div>
                                                <div class="col-md-2 col-md-offset-5">
                                                    <button id="btn_agregar" type="button" class="btn btn-primary btn-sx pull-right"
                                                     data-toggle="modal" data-target="#nuevoProveedor" >
                                                    <i class="fa  fa-user"></i> Agregar Proveedor</button>
                                                </div>
                                                 
                                            </div>
                                            <div class="table-responsive">
                                                <table class="table table-hover text-center">
                                                    <thead>
                                                        <tr>
                                                            <th class="text-center">#</th>
                                                            <th class="text-center">R.U.C.</th>
                                                            <th class="text-center">Nombre</th>
                                                            <th class="text-center">Direccion</th>
                                                            <th class="text-center">Celular</th>
                                                            <th class="text-center">E-mail </th>
                                                            <th class="text-center">Estado </th>
                                                            <th class="text-center">Editar/inhabilitar</th>
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
            <!-- fin cuerpo !-->

        </div>
        <!-- fin del contenedor !-->

    </div>
	

    <!-- start modals  persona -->

    <!-- dmodalProducto Nuevo Producto -->
    <div class="modal fade" id="nuevoProducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"><i class='glyphicon glyphicon-edit'></i> <b><span id="span_nuevo_p">Nuevo</span><span id="span_actualizar_p">Actualizar</span> producto</b></h4>
                  </div>
                  <div class="modal-body">
                        <form class="form-horizontal" method="post" id="editar_producto" name="editar_producto">
                            <div id="resultados_ajax2"></div>
                            <div class="form-group">
                                <label  class="col-sm-3 control-label">Código</label>
                                <div class="col-sm-8">
                                  <input type="text" class="form-control" id="Txt_codigo_producto" name="Txt_codigo_producto" placeholder="Código del producto" maxlength="4" required >                                        
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-3 control-label">Categoria</label>
                                <div class="col-sm-8">
                                 <select class="form-control" id="Cbx_id_categoria" name="Cbx_id_categoria" required>
                                        <option value=""> ======== Seleccione Categoria ======== </option>
                                  </select>
                                </div>
                            </div>
                          
                            <div class="form-group">
                                <label  class="col-sm-3 control-label">Nombre</label>
                                <div class="col-sm-8">
                                  <input type="text" class="form-control" id="Txt_descripcion_producto" name="Txt_descripcion_producto" placeholder="Nombre del producto" maxlength="120" required>
                                </div>
                            </div>
                            <div class="form-group">
                                  <label  class="col-sm-3 control-label">Unidad</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Txt_unidades_producto" name="Txt_unidades_producto" placeholder="Unidad del producto" maxlength="45" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label  class="col-sm-3 control-label">Precio</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Txt_precio_producto" name="Txt_precio_producto" placeholder="Precio de venta del producto" required pattern="^[0-9]{1,5}(\.[0-9]{0,2})?$" onKeyPress="return soloNumeros_con_punto(event)" title="Ingresa sólo números con 0 ó 2 decimales" maxlength="13">
                                  </div>
                            </div>
                            <div class="form-group" id="div_stock_">
                                  <label  class="col-sm-3 control-label">Stock</label>
                                  <div class="col-sm-8">
                                    <input type="texte" class="form-control" id="Txt_stock_producto" name="Txt_stock_producto" placeholder="Stock del producto" maxlength="4" onKeyPress="return soloNumeros(event)" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label  class="col-sm-3 control-label">Stock almacen</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Txt_stock_a_producto" name="Txt_stock_a_producto" placeholder="Stock almacen del producto" maxlength="4" onKeyPress="return soloNumeros(event)" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label   class="col-sm-3 control-label">Stock minimo</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Txt_stock_minimo_producto" name="Txt_stock_minimo_producto; " placeholder="Stock minimo del producto" maxlength="4" onKeyPress="return soloNumeros(event)" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">En mostrador</label>
                                  <div class="col-sm-8">
                                    <select class="form-control" id="Txt_en_mostrador_producto" name="Txt_en_mostrador_producto" required>
                                            <option value="0">NO</option>
                                            <option value="1">SI</option>
                                    </select>
                                  </div>
                            </div>
                            <div class="form-group" id="div_stock_mostrador">
                                  <label for="descripcion" class="col-sm-3 control-label">Stock mostrador </label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Txt_stock_m_producto" name="Txt_stock_m_producto" placeholder="Stock mostrador del producto" maxlength="4" onKeyPress="return soloNumeros(event)" required>
                                  </div>
                            </div>			  
                            <div class="form-group" id="div_stock_min_mostrador">
                                  <label for="descripcion" class="col-sm-3 control-label">Stock minimo mostrador</label>
                                  <div class="col-sm-8">
                                      <input type="text" class="form-control" id="Txt_stock_minimo_mostrador_producto" name="Txt_stock_minimo_mostrador_producto" placeholder="Stock minimo mostrador del producto" maxlength="4" autocomplete="off" onKeyPress="return soloNumeros(event)" required>
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
                        <button type="button" class="btn btn-primary" id="crearpro">Guardar Producto</button>
                        <button type="button" class="btn btn-primary" id="modificarpro">Actualizar Producto</button>  
                  </div>
                            </form>
                </div>
          </div>
        </div>

    <!-- dmodalCategoria NUeva Categoria -->
    <div class="modal fade" id="nuevaCategoria" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"><i class='glyphicon glyphicon-edit'></i> 
                        <b><span id="span_nuevo_c">Nueva</span><span id="span_actualizar_c">Actualizar</span> Categoria</b></h4>
              </div>
              <div class="modal-body">
                    <form class="form-horizontal" method="post" id="editar_producto" name="editar_producto">
                    <div id="resultados_ajax2"></div>
                        <div class="form-group">
                            <label for="descripcion" class="col-sm-3 control-label">Nombre</label>
                            <div class="col-sm-8">
                              <input type="text" class="form-control" id="txt_descripcion_c" name="txt_descripcion_c" placeholder="Nombre de Categoria" maxlength="120" required>
                            </div>
                        </div>
                        <div class="form-group" id="div_estado_c">
                            <label class="col-sm-3 control-label">Estado</label>
                            <div class="col-sm-8">
                             <select class="form-control" id="cbx_estado_c" name="cbx_estado_c" required>
                                    <option value="1"> Habilitado </option>
                                    <option value="0"> Inactivo </option>
                              </select>
                            </div>
                        </div>  
              </div>
              <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    <button type="button" class="btn btn-primary" id="crearcategoria">Guardar Categoria</button>
                    <button type="button" class="btn btn-primary" id="modificarcategoria">Actualizar Categoria</button>                    
              </div>
                    </form>
            </div>
      </div>
    </div> 

    <!-- dmodalProveedor Nuevo Proveedor -->
    <div class="modal fade" id="nuevoProveedor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="myModalLabel"><i class='glyphicon glyphicon-edit'></i> 
                             <b><span id="span_nuevo">Nuevo</span><span id="span_actualizar">Actualizar</span> Proveedor</b>
                          </h4>
                    </div>
                    <div class="modal-body">
                          <form class="form-horizontal" method="post" id="editar_usuario" name="editar_usuario">

                            <div class="form-group">
                                  <label  class="col-sm-3 control-label">Nombre </label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="txt_nombrepersona" name="txt_nombrepersona" placeholder="Nombre Proveedor" maxlength="200" required>
                                  </div>
                            </div>

                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">R.U.C.</label>
                                  <div class="col-sm-8">
                                      <input type="text" class="form-control" id="txt_documento" autocomplete="off" name="txt_documento" placeholder="R.U.C. Proveedor" required maxlength="11" onKeyPress="return soloNumeros(event)" >
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label  class="col-sm-3 control-label">Direccion</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="txt_direccion" name="txt_direccion" placeholder="Direccion Proveedor" maxlength="200" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label  class="col-sm-3 control-label">Celular</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="txt_celular" name="txt_celular" placeholder="Numero Celular" required maxlength="200">
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label  class="col-sm-3 control-label">Email</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="txt_email" name="txt_email" placeholder="Correo Electronico" maxlength="200" required>
                                  </div>
                            </div>
                            
                        <div class="form-group" id="div_estado">
                                <label  class="col-sm-3 control-label">Estado</label>
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
                        <button type="button" class="btn btn-primary" id="crearproveedor">Guardar Proveedor</button>
                        <button type="button" class="btn btn-primary" id="modificarproveedor">Actualizar Proveedor</button>
                  </div>
                  </form>
                </div>
          </div>
        </div>
    
     <!-- finish in modals -->         

</body>
</html>
