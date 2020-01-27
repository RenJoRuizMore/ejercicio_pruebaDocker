
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
        <script type="text/javascript" src="./funciones_js/almacenero/JS_ALfacturacompra.js"> </script>
        <!--<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        
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
          
            <!-- saltos de linea -->
            <div>
                <br><br>
            </div>
            <!-- cuerpo -->
            <div class="container" style="background:white;height:100%;">
                <div class="row none" >
                    <div class="col-xs-12 col-md-2 ">											
                        <div class="form-group label-floating">
                          <input class="form-control" type="hidden" id="obj_id_user" value="<s:property value="obj_user.id_usuario" />" required>
                        </div>
                    </div>
                </div>        
                    <div class="row" >                        
                            <div class="col-xs-12" >
                                <!-- cuerpocabecera -->
                                    <div class="row" style="margin-bottom:10px;border:0px solid red">
                                        <div class="col-md-6"  style="border:0px solid blue;">
                                            <h1 class="text-titles"><i class="zmdi zmdi-timer zmdi-hc-fw"></i> Factura Compra</h1>
                                        </div>
                                        <div class="col-md-6 "style="border:0px solid blue;height: 100%">
                                            <div class="pull-right" style="margin-top:36px;"> 
                                                <button  href="#!" class="btn btn-primary" id="btn_agregar_p" data-toggle="modal" data-target="#nuevoProducto">
                                                 <span class="glyphicon glyphicon-plus"></span> <i class="fa fa-cube"></i> Nuevo producto
                                                </button>
                                                <button href="#!" class="btn btn-primary btn-raised" id="btn_agregar_c" data-toggle="modal" data-target="#nuevaCategoria" >
                                                    <i class="fa fa-server"></i> Nueva Categoria
                                                </button>
                                            </div>	
                                        </div>
                                    </div>
                                    <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                                        <li class="active"><a href="#new" id="tab_new_factura" data-toggle="tab">Nueva Factura Compra</a></li>
                                        <li><a href="#list" id="tab_factura" data-toggle="tab">Lista Factura Compra</a></li>
                                    </ul>
                                    <!-- cuerpobody -->
                                    <div id="myTabContent" class="tab-content">
                                        <div class="tab-pane fade active in" id="new">
                                            <div class="container-fluid">
                                                <form action="">
                                                    <div class="row">
                                                        <div class="col-xs-12 col-md-12 col-md-offset-0">

                                                            <div class="row" >
                                                                <div class="col-xs-12 col-md-2 ">											
                                                                    <div class="form-group label-floating">
                                                                      <label class="control-label">Numero</label>
                                                                      <input class="form-control" type="text" id="txtnumero_fc" maxlength="12" placeholder="Ingrese Numero" required>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12 col-md-2 ">											
                                                                    <div class="form-group label-floating">
                                                                      <label class="control-label">Monto</label>
                                                                      <input class="form-control" type="text" id="txtmonto_fc" readonly placeholder="Monto" required>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12 col-md-2 col-md-offset-6" >											
                                                                    <div class="form-group" style="margin:0px;">
                                                                        <label class="control-label">Fecha </label>
                                                                        <input class="form-control" type="date" id="txfecha_fc" required>
                                                                    </div>
                                                                </div>
                                                            </div>	
                                                            <div class="row">		                                                                									
                                                                <div class="col-xs-12 col-md-2">
                                                                    <button style="width:100%;" type="button" class="btn btn-primary" data-toggle="modal" data-target="#buscarProveedor">
                                                                      <span class="glyphicon glyphicon-plus"></span> <i class="fa fa-search"></i> Buscar proveedor
                                                                     </button>
                                                                </div>			
                                                                <div class="col-xs-12 col-md-2">											
                                                                    <div class="form-group">
                                                                      <input class="form-control" id="txt_ruc" readonly type="text" placeholder="R.U.C Proveedor" required>
                                                                    </div>
                                                                </div>											
                                                                <div class="col-xs-12 col-md-4">											
                                                                    <div class="form-group">
                                                                      <input class="form-control" id="txt_nombre"readonly type="text" placeholder="Nombre proveedor" required>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12 col-md-2">											
                                                                    <div class="form-group">
                                                                      <input class="form-control" id="txt_cel" readonly type="text"  placeholder="Celular Proveedor" >
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12 col-md-2">											
                                                                    <button style="width:100%;" type="button" id="crearFactura" class="btn btn-primary pull-right">
                                                                        <span class="glyphicon glyphicon-print"></span> <i class="fa fa-floppy-o"></i> Guardar Factura
                                                                    </button>
                                                                </div>											
                                                            </div>
                                                            
                                                            <div class="row" >
                                                                <div class="col-md-2">
                                                                    <button style="width:100%;" type="button" class="btn btn-primary" data-toggle="modal" data-target="#buscarproducto">
                                                                     <span class="glyphicon glyphicon-plus"></span> <i class="fa fa-search"></i> Buscar producto
                                                                    </button>
                                                                    <!--<button type="button" class="btn btn-primary" id="btn_agregar" data-toggle="modal" data-target="#nuevoProveedor">
                                                                     <span class="glyphicon glyphicon-user"></span> <i class="fa fa-user"></i> Nuevo Proveedor
                                                                    </button>-->
                                                                </div>
                                                            </div>    

                                                        </div>
                                                    </div>
                                                </form>
                                                <hr>
                                                <div class="table-responsive">
                                                    <table class="table table-hover text-center">
                                                        <thead>
                                                            <tr>
                                                                <th class="text-center">#</th>
                                                                <th class="text-center">Codigo</th>
                                                                <th class="text-center">Nombre del producto</th>
                                                                <th style="text-align:right;">Cantidad</th>
                                                                <th style="text-align:right;">Precio</th>
                                                                <th style="text-align:right;">Total</th>
                                                                <th class="text-center">Eliminar</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="contenido_tmp_detfactura">

                                                        </tbody>
                                                        <tfoot>
                                                            <tr style="border-top:solid 1px #eee;">
                                                                <td colspan="4"  > </td>
                                                                <td  style="font-weight:bold;text-align:right;"> Sub Total</td>
                                                                <td  style="font-weight:bold;text-align:right;"><span id="span_total" ></span></td>
                                                         </tr>									 
                                                         <tr style="border-top:solid 1px #eee;">
                                                                <td colspan="4"  > </td>
                                                                <td  style="font-weight:bold;text-align:right;"> IGV
                                                                    <input type="text"  id="txt_igv_tmp" size="1" maxlength="2" style="text-align:center;" onKeyPress="return soloNumeros(event)" value="18" >
                                                                    %
                                                                </td>
                                                                <td  style="font-weight:bold;text-align:right;"><span id="span_igv" ></span></td>
                                                         </tr>									 
                                                         <tr style="border-top:solid 1px #eee;">
                                                                <td colspan="4"  > </td>
                                                                <td  style="font-weight:bold;text-align:right;"> Total </td>
                                                                <td  style="font-weight:bold;text-align:right;"><span id="span_Neto" ></span></td>
                                                         </tr>
                                                        </tfoot>
                                                    </table>
                                                </div>

                                            </div>
                                        </div>        



                                            <div class="tab-pane fade" id="list">
                                                <div id="listar_factura">
                                                    <div class="row" style="padding-bottom:12px;">
                                                        <div class="col-md-5">
                                                            <div class="input-group date" id="datetimepicker1">
                                                                <input type="text" class="form-control" placeholder="Numero Factura o Nombre del Proveedor" id="texto_busqueda_factura">
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
                                                                        <th class="text-center"># Factura</th>
                                                                        <th class="text-center">Proveedor</th>
                                                                        <th class="text-center">Monto</th>
                                                                        <th class="text-center">Fecha</th>
                                                                        <th class="text-center">Usuario</th>
                                                                        <th class="text-center">Estado</th>
                                                                        <th class="text-center">Editar / inhabilitar</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="contenido_factura">

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div aria-label="Page navigation example">
                                                        <ul class="pagination pagination-sm" id="boton_lista_factura">

                                                        </ul>
                                                    </div>
                                                    
                                                </div>
                                                <div id="factura_edit">
                                                    
                                                    <div class="row" style="margin-bottom:10px;">
                                                    <div class="col-md-12">
                                                        <div class="pull-right"> 
                                                            <button  href="#!" class="btn btn-primary" id="buspro_edit" data-toggle="modal" data-target="#buscarproducto">
                                                             <span class="glyphicon glyphicon-plus"></span> <i class="fa fa-search"></i> Buscar producto
                                                            </button> 
                                                            <!--
                                                            <button  href="#!" class="btn btn-primary" id="btn_agregar_p_edit" data-toggle="modal" data-target="#nuevoProducto">
                                                             <span class="glyphicon glyphicon-plus"></span> <i class="fa fa-cube"></i> Nuevo producto
                                                            </button>
                                                            <button href="#!" class="btn btn-primary btn-raised" id="btn_agregar_c_edit" data-toggle="modal" data-target="#nuevaCategoria" >
                                                                <i class="fa fa-server"></i> Nueva Categoria
                                                            </button>
                                                            <button type="button" class="btn btn-primary" id="btn_agregar" data-toggle="modal" data-target="#nuevoProveedor">
                                                             <span class="glyphicon glyphicon-user"></span> <i class="fa fa-user"></i> Nuevo Proveedor
                                                            </button>-->
                                                        </div>	
                                                    </div>
                                                </div>
                                                <form action="">
                                                    <div class="row">
                                                        <div class="col-xs-12 col-md-12 col-md-offset-0">

                                                            <div class="row" >
                                                                <div class="col-xs-12 col-md-2 ">											
                                                                    <div class="form-group label-floating">
                                                                      <label class="control-label">Numero</label>
                                                                      <input class="form-control" type="text" id="txtnumero_fc_edit" placeholder="Ingrese Numero" maxlength="12" required>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12 col-md-2 ">											
                                                                    <div class="form-group label-floating">
                                                                      <label class="control-label">Monto</label>
                                                                      <input class="form-control" type="text" id="txtmonto_fc_edit" readonly placeholder="Monto" required>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12 col-md-2 col-md-offset-0" >											
                                                                    <div class="form-group" style="margin:0px;">
                                                                        <label class="control-label">Fecha </label>
                                                                        <input class="form-control" type="date" id="txfecha_fc_edit" required>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12 col-md-2 col-md-offset-0" >											
                                                                    <div class="form-group" style="margin:0px;">
                                                                        <label class="control-label">Estado </label>
                                                                        <select class="form-control" id="cbx_stado_edit" >
                                                                            <option value="1">Habilitado</option>
                                                                            <option value="0">Inactivo</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>	
                                                            <div class="row">		                                                                									
                                                                <div class="col-xs-12 col-md-1">											
                                                                    <label class="control-label">Proveedor</label>
                                                                </div>			
                                                                <div class="col-xs-12 col-md-7">											
                                                                    <div class="form-group">
                                                                        <select class="form-control" id="cbx_proveedores" >
                                                             
                                                                        </select>
                                                                    </div>
                                                                </div>                                                                
                                                                <div class="col-xs-12 col-md-3">											
                                                                    <button  type="button" id="modificarFactura" class="btn btn-primary pull-right">
                                                                        <span class="glyphicon glyphicon-print"></span> <i class="fa fa-refresh"></i> Actualizar Factura
                                                                    </button>
                                                                </div>
                                                                <div class="col-xs-12 col-md-1">											
                                                                    <button  type="button" id="volver" class="btn btn-primary pull-right">
                                                                        <span class="glyphicon glyphicon-print"></span> <i class="fa fa-arrow-circle-left"></i> Volver
                                                                    </button>
                                                                </div>	
                                                            </div>


                                                        </div>
                                                    </div>
                                                </form>
                                                <hr>
                                                <div class="table-responsive">
                                                    <table class="table table-hover text-center">
                                                        <thead>
                                                            <tr>
                                                                <th class="text-center">#</th>
                                                                <th class="text-center">Codigo</th>
                                                                <th class="text-center">Nombre del producto</th>
                                                                <th style="text-align:right;">Cantidad</th>
                                                                <th style="text-align:right;">Precio</th>
                                                                <th style="text-align:right;">Sub-total</th>
                                                                <th class="text-center">Eliminar</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="contenido_detfactura">

                                                        </tbody>
                                                        <tfoot>
                                                            <tr style="border-top:solid 1px #eee;">
                                                                <td colspan="4"  > </td>
                                                                <td  style="font-weight:bold;text-align:right;"> Total</td>
                                                                <td  style="font-weight:bold;text-align:right;"><span id="span_total_e" ></span></td>
                                                         </tr>									 
                                                         <tr style="border-top:solid 1px #eee;">
                                                                <td colspan="4"  > </td>
                                                                <td  style="font-weight:bold;text-align:right; "> IGV
                                                                    <input type="text"  id="txt_igv_tmp_e" size="1" maxlength="2" style="text-align:center;" onKeyPress="return soloNumeros(event)" value="18" >
                                                                    %
                                                                </td>
                                                                <td  style="font-weight:bold;text-align:right;"><span id="span_igv_e" ></span></td>
                                                         </tr>									 
                                                         <tr style="border-top:solid 1px #eee;">
                                                                <td colspan="4"  > </td>
                                                                <td  style="font-weight:bold;text-align:right;"> Total Neto</td>
                                                                <td  style="font-weight:bold;text-align:right;"><span id="span_Neto_e" ></span></td>
                                                         </tr>
                                                        </tfoot>
                                                    </table>
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
	

    <!-- start modals  persona -->
    
        <!-- dmodalBuscarProducto -->
        <div class="modal fade bs-example-modal-lg" id="buscarproducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="myModalLabel"><b>Buscar productos</b></h4>
                    </div>
                    <div class="modal-body" style="padding-bottom:0px;">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-sm-6">
                                    <input list="listproductos" type="text"  autocomplete="off" class="form-control" id="txt_produc"  placeholder="Ingrese codigo o nombre producto">                                  
                                    <datalist id="listproductos" ></datalist> 
                                </div>                             
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="txtprecio" maxlength="13"  onKeyPress="return soloNumeros_con_punto(event)" placeholder="Precio">
                                </div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="txtcantidad" maxlength="4"  onKeyPress="return soloNumeros(event)" placeholder="Cantidad">
                                </div>                                
                                <button type="button" id="add_pro_tmp" class="btn btn-primary" ><i class="fa fa-plus"></i> Agregar</button>
                                <button type="button" id="add_pro_table" class="btn btn-danger" ><i class="fa fa-plus"></i> Agregar</button>
                            </div>
                        </form>
                    </div>  
                    <div class="modal-footer" >
                         <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
          </div>
        </div>
        
        
        <!-- dmodalBuscarProducto -->
        <div class="modal fade bs-example-modal-lg" id="buscarProveedor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="myModalLabel"><b>Buscar Proveedor</b></h4>
                    </div>
                    <div class="modal-body" style="padding-bottom:0px;">
                        
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
                                <i class="fa  fa-user"></i> Nuevo Proveedor</button>
                            </div>

                        </div>
                        <div class="table-responsive">
                            <table class="table table-hover text-center">
                                <thead>
                                    <tr>
                                        <th class="text-center">#</th>
                                        <th class="text-center">R.U.C.</th>
                                        <th class="text-center">Nombre</th>
                                        <th class="text-center">Celular</th>
                                        <th class="text-center">Estado </th>
                                        <th class="text-center">Seleccionar</th>
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
                    <div class="modal-footer" >
                         <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
          </div>
        </div>
        
        
    
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
