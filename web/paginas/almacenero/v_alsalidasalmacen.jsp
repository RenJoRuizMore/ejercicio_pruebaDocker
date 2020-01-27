
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
        <script type="text/javascript" src="./funciones_js/almacenero/JS_ALsalidalmacen.js"> </script>
        
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
          
            <!-- saltos de linea -->
            <div>
                <br><br>
            </div>
            <!-- cuerpo -->
            <div class="container" style="background:white;height:100%;">
                <div class="row" >
                    <div class="col-xs-12" >
                        <!-- cuerpocabecera !-->
                            <h1 class="text-titles"><i class="zmdi zmdi-timer zmdi-hc-fw"></i> SALIDAS </h1><br>

                            <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                                    <li class="active"><a href="#new" id="tab_new_salida" data-toggle="tab">Nueva Salida </a></li>
                                    <li><a href="#list" id="tab_lista_salida" data-toggle="tab">Lista de Salidas</a></li>
                            </ul>
                            <!-- cuerpobody -->
                            <div id="myTabContent" class="tab-content">
                                <div class="tab-pane fade active in" id="new">
                                        <div class="container-fluid">
                                                <form class="form-horizontal" action="">
                                                <div class="row">
                                                    <div class="col-xs-12 col-md-12 col-md-offset-6">
                                                        <div class="form-group">
                                                            <label for="numero" class="col-sm-1 control-label">Numero</label>
                                                            <div class="col-sm-2 ">
                                                                <input type="text" class="form-control" id="txt_numero_salida" placeholder="Numero Salida" readonly required>
                                                            </div>
                                                            <label for="fecha" class="col-sm-1 control-label">Fecha</label>
                                                            <div class="col-sm-2">
                                                              <input type="date" class="form-control" id="txtfecha"  placeholder="Fecha Salida" required>
                                                            </div>
                                                        </div>		
                                                    </div>									    
                                                </div>
                                                </form>
                                                <hr>
                                                <div class="row">

                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                          <label >Buscar Producto</label>
                                                          <input list="listproductos" type="text"  autocomplete="off" class="form-control" id="txt_produc"  placeholder="Codigo o nombre producto">
                                                          <!--<small id="emailHelp" class="form-text text-muted">*Numero de Documento autocompletado</small>-->
                                                        </div>
                                                        <datalist id="listproductos"  >
                                                        </datalist>
                                                    </div>
                                                    <div class="col-md-1">
                                                        <div class="form-group">
                                                          <label >Stock</label>
                                                          <input type="Numero" class="form-control"  id="txtstock" readonly>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-1   ">
                                                        <div class="form-group">
                                                          <label for="exampleInputEmail1">Cantidad</label>
                                                          <input type="text" class="form-control" id="txtcantidad" maxlength="4"  onKeyPress="return soloNumeros(event)">
                                                        <!--<small id="emailHelp" class="form-text text-muted">*S A: 16</small>-->
                                                        </div>

                                                    </div>
                                                    <div class="col-md-1">
                                                        <div class="form-group">
                                                            <label for="exampleInputEmail1">Agregar</label> 
                                                            <button type="button" class="btn btn-md btn-primary" id="add_pro">
                                                            <i class="fa  fa-2x fa-plus-circle"></i>  </button>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="form-group pull-right">
                                                            <button type="submit" class="btn btn-lg btn-primary" id="crearsalida">
                                                                <i class="fa fa-floppy-o"></i> Guardar Salida
                                                            </button>
                                                        </div>
                                                    </div>
                                               </div>
                                                <div class="table-responsive">
                                                    <table class="table table-hover text-center">
                                                        <thead>
                                                            <tr>
                                                                <th class="text-center">#</th>
                                                                <th class="text-center">Codigo</th>
                                                                <th class="text-center">Nombre del producto</th>
                                                                <th class="text-center">Cantidad</th>
                                                                <th class="text-center">Editar</th>
                                                                <th class="text-center">Eliminar</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="contenido_tmp_detsalida">

                                                        </tbody>
                                                    </table>
                                                </div>
                                                
                                                <hr>
                                                <br>
                                        </div>
                                </div>

                                <div class="tab-pane fade" id="list">
                                    <div id="lista_detalle">
                                        <div class="row" style="padding-bottom:12px;">
                                            <div class="col-md-5">
                                                <div class="input-group date" id="datetimepicker1">
                                                    <input type="text" class="form-control" placeholder="Numero Salida " id="texto_busqueda_salida">
                                                    <span class="input-group-addon">
                                                        <span class="fa fa-search"></span>
                                                    </span>
                                                 </div>
                                            </div>
                                        </div>
                                        <div class="table-responsive">
                                            <table class="table table-hover text-center">
                                                <thead>
                                                    <tr>
                                                        <th class="text-center">#</th>
                                                        <th class="text-center">Numero Salida</th>
                                                        <th class="text-center">Fecha</th>
                                                        <th class="text-center">Total de productos</th>
                                                        <th class="text-center">Encargado</th>
                                                        <th class="text-center">Estado</th>
                                                        <th class="text-center">Editar / Inhabilitar</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="contenido_salida">

                                                </tbody>
                                            </table>
                                        </div>
                                        <div aria-label="Page navigation example">
                                            <ul class="pagination pagination-sm" id="boton_lista_salida">

                                            </ul>
                                        </div>
                                    </div>
                                    
                                    <div id="editar_detalle">
                                        
                                        <form class="form-horizontal" action="">
                                            <div class="row" style="margin-top:16px;">
                                            <div class="col-xs-12 col-md-12 col-md-offset-0">
                                                <div class="form-group">
                                                    <label for="numero" class="col-sm-1 control-label">Numero</label>
                                                    <div class="col-sm-2 ">
                                                        <input type="text" class="form-control" id="txt_numero_salida_e" placeholder="Numero Salida" readonly required>
                                                    </div>
                                                    <label for="fecha" class="col-sm-1 control-label">Fecha</label>
                                                    <div class="col-sm-2">
                                                      <input type="date" class="form-control" id="txt_fecha_e"  placeholder="Fecha Salida" required>
                                                    </div>
                                                    <label  class="col-sm-1 control-label">Estado</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control" id="cbx_estado_e" required>
                                                               <option value="1"> Habilitado </option>
                                                               <option value="0"> Inactivo </option>
                                                        </select>
                                                    </div>
                                                    <div class="col-xs-12 col-md-3 ">
                                                        <button type="button" class="btn btn-lg btn-primary pull-right" id="modificarSalida">
                                                            <i class="fa fa-refresh"></i> Actualizar Salida
                                                        </button>	
                                                    </div>                                                    
                                                </div>
                                            </div>									    
                                        </div>
                                        </form>
                                        <hr>
                                        <div class="row">

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                  <label >Buscar Producto</label>
                                                  <input list="listproductos_detsal" type="text" autocomplete="off" class="form-control" id="txt_produc_detsal"  placeholder="Codigo o nombre producto">
                                                  <!--<small id="emailHelp" class="form-text text-muted">*Numero de Documento autocompletado</small>-->
                                                </div>
                                                <datalist id="listproductos_detsal" >
                                                </datalist>
                                            </div>
                                             <div class="col-md-1">
                                                <div class="form-group">
                                                  <label >Stock</label>
                                                  <input type="Numero" class="form-control"  id="txtstock_detsal" readonly>
                                                </div>
                                            </div>
                                             <div class="col-md-1   ">
                                                <div class="form-group">
                                                  <label for="exampleInputEmail1">Cantidad</label>
                                                  <input type="text" class="form-control" id="txtcantidad_detsal" maxlength="4"  onKeyPress="return soloNumeros(event)">
                                                <!--<small id="emailHelp" class="form-text text-muted">*S A: 16</small>-->
                                                </div>

                                            </div>
                                            <div class="col-md-1">
                                                <div class="form-group">
                                                    <label for="exampleInputEmail1">Agregar</label> 
                                                    <button type="button" class="btn btn-md btn-primary" id="add_pro_detsal">
                                                    <i class="fa  fa-2x fa-plus-circle"></i>  </button>
                                                </div>
                                            </div>
                                            
                                            <div class="col-xs-12 col-md-3 ">
                                                <button type="submit" class="btn btn-lg btn-primary pull-right" id="btn_cancelar">
                                                    <i class="fa fa-arrow-left"></i> Volver
                                                </button>	
                                            </div>
                                       </div>
                                        <div class="table-responsive">
                                            <table class="table table-hover text-center">
                                                <thead>
                                                    <tr>
                                                        <th class="text-center">#</th>
                                                        <th class="text-center">Codigo</th>
                                                        <th class="text-center">Nombre del producto</th>
                                                        <th class="text-center">Cantidad</th>
                                                        <th class="text-center">Eliminar</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="contenido_detallesalida">

                                                </tbody>
                                            </table>
                                        </div>

                                        <hr>
                                        <br>
                                        
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
	

    <!-- start modals  persona -->

    <!-- dmodalBuscarProducto -->
    <div class="modal fade" id="buscarproducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog modal-xs" role="document">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"><b>Producto</b></h4>
              </div>
              <div class="modal-body">
                    <form class="form-horizontal" method="post" id="product_" name="product_">
                        <div class="form-group">
                              <label for="mod_codigo" class="col-sm-3 control-label">Nombre </label>
                              <div class="col-sm-8">
                                <input type="text" class="form-control" id="txt_name_p" readonly>
                              </div>
                        </div>
                        <div class="form-group">
                              <label for="mod_codigo" class="col-sm-3 control-label">Stock </label>
                              <div class="col-sm-8">
                                <input type="text" class="form-control" id="txt_stock_p" readonly>
                              </div>
                        </div>
                        <div class="form-group">
                              <label for="descripcion" class="col-sm-3 control-label">Cantidad</label>
                              <div class="col-sm-8">
                                <input type="text" class="form-control" id="txt_cantidad_p" placeholder="Ingrese Cantidad" maxlength="4" onKeyPress="return soloNumeros(event)" >
                              </div>
                        </div>
              </div>
              <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                    <button type="button" class="btn btn-primary" id="modificarcantidad">Actualizar </button>
              </div>
                    </form>
            </div>
      </div>
    </div>



     <!-- finish in modals -->         

</body>
</html>
