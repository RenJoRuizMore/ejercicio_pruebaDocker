
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
        <script type="text/javascript" src="./funciones_js/vendedor/JS_Vhome.js"> </script>
        
        <link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
        <script src="./js/jquery-ui.js"></script>
        <!--<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>-->
        
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
            .clasecss{
                color: blueviolet;
            }
            
        </style>
</head>
<body class="inner_page innerpage">
    
    <div class="bg_parallax" id="inb">
        
        <!-- SideBar -->
	<%@ include file="../plantillas/vendedor/menu.jsp" %> 
        
        <!-- Contenido de pagina incluye cabecera de arriba con el contenido -->
        <div class="site-overlay"></div>
        <div id="container">
            <!--cabecera contenedor -->
            <%@ include file="../plantillas/administrador/header.jsp" %>            
            <!--final de cabecera -->
                      
            <!--  cuerpo-->
            <div class="container" style="background:white"><br><br><br>
                <div class="row none" >
                    <div class="col-xs-12 col-md-2 ">											
                        <div class="form-group label-floating">
                          <input class="form-control" type="hidden" id="obj_id_user" value="<s:property value="obj_user.id_usuario" />">
                        </div>
                    </div>
                </div> 
                <div class="row">
                <!--content-->
                    <div class="col-md-12 basic"> 
                        <div class="">
                            <h2><a href="#">Ventas Realizadas</a><span></span></h2>
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="row">                                    
                                        <div class="form-group col-md-6">
                                            <label >DNI / RUC:</label>
                                            <div class="input-group date" id="datetimepicker1">
                                                <input type="text" class="form-control" id="txtdniruc"  placeholder="Buscar por DNI o RUC" maxlength="11" >
                                                <span class="input-group-addon">
                                                    <span class="fa fa-search"></span>
                                                </span>
                                            </div>
                                        </div>    
                                        <div class="form-group col-md-6">
                                            <label >Tipo de Documento</label>
                                            <select class="form-control" id="tipodoc">
                                                <option value="1">Boleta</option>
                                                <option value="2">Factura</option>
                                           </select>
                                        </div>
                                    </div>
                                    <div class="row">                                    
                                        <div class="form-group col-md-6">
                                            <label >Nombre:</label>
                                            <input type="text" class="form-control" id="txtname" placeholder="Ingrese Nombre">
                                        </div>
                                        <div class="col-md-6" >
                                            <button style="margin-top:27px;" type="button" class="btn btn-primary" id="btn_guardar_cliente">
                                              <i class="fa  fa-floppy-o"></i> Guarda Cliente 
                                            </button>
                                            <button style="margin-top:27px;" type="button" class="btn btn-warning" id="btn_actualizar_cliente">
                                              <i class="fa  fa-refresh"></i> Actualizar Cliente 
                                            </button><!---->
                                        </div>
                                    </div>                                
                                    <div class="row">                                    
                                        <div class="form-group col-md-6">
                                            <label >Dirección (opcional)</label>
                                            <input type="text" class="form-control" id="txtdireccion" placeholder="Ingrese Direccion (opcional)">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label >Celular (opcional)</label>
                                            <input type="text" class="form-control" id="txtcelular" placeholder="Ingrese Celular (opcional)">
                                        </div>
                                    </div>  
                                </div>
                                <div class="col-md-4" >
                                     <div class="pg style_two" style="border:2px solid #0a6abd;border-radius: 10px">
                                         <h2 class="text-center">  N° DE PEDIDO:<br>  <span id="n_pedido_str" ></span>   </h2>
                                        <hr>
                                        <p class="text-center">Vendedor : <b><s:property value="obj_user.nombre_persona" /></b></p>
                                        <h1 class="text-center text-primary" id="lbl_total"></h1>
                                     </div>
                                </div> 
                            </div>
                            <hr>
                            <div class="row">

                                 <div class="col-md-6">
                                    <div class="form-group">
                                        <label >Buscar Producto o Servicio</label>
                                        <div class="input-group date" id="datetimepicker1">
                                            <input type="text" class="form-control" id="txt_produc"  placeholder="Buscar por codigo o nombre producto">
                                            <span class="input-group-addon">
                                                <span class="fa fa-search"></span>
                                            </span>
                                            <input type="hidden" id="product-id">
                                        </div>                                        
                                        <small id="emailHelp" class="form-text text-muted">*codigo o nombre autocompletado</small>
                                    </div>
                                 </div>
                                  <div class="col-md-1">
                                     <div class="form-group">
                                       <label >Stock</label>
                                       <input type="text" class="form-control" id="txtstocka"  readonly>
                                        <small class="form-text text-muted">Almacen</small>
                                     </div>
                                 </div>
                                   <div class="col-md-1">
                                     <div class="form-group">
                                       <label >Stock</label>
                                       <input type="Number" class="form-control" id="txtstockm" readonly>
                                       <small  class="form-text text-muted">Mostrador</small>
                                     </div>
                                 </div>
                                  <div class="col-md-1">
                                     <div class="form-group">
                                       <label >Precio</label>
                                       <input type="text" class="form-control"  id="txtprecio" readonly>
                                     </div>
                                 </div>                                         
                                  <div class="col-md-1">
                                     <div class="form-group">
                                       <label >Cantidad</label>
                                       <input type="text" class="form-control" id="txtcantidad" onKeyPress="return soloNumeros(event)" maxlength="4">
                                     </div>
                                 </div>
                                   <div class="col-md-1">
                                     <div class="form-group">
                                       <label >Agregar</label> 
                                      <button type="button" class="btn btn-md btn-primary" id="add_pro">
                                       <i class="fa  fa-2x fa-plus-circle"></i>  </button>
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
                                            <td  style="font-weight:bold;text-align:right;">Sub Total</td>
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
                                            <td  style="font-weight:bold;text-align:right;"> Total</td>
                                            <td  style="font-weight:bold;text-align:right;"><span id="span_Neto" ></span></td>
                                     </tr>
                                    </tfoot>
                                </table>
                            </div>
                            
                             <div class="col-md-5 basic text-right">
                               
                            </div>
                            <div class="col-md-6 basic text-right">
                             <button style="margin-right:35px;" type="button" class="btn  btn-lg" id="btn_cancelar_venta">
                                              <i class="fa  fa-cart-arrow-down"></i> Cancelar Venta
                             </button>
                             <button  type="button" class="btn btn-lg btn-primary" id="btn_guardar_venta">
                               <i class="fa  fa-cart-plus"></i> Realizar Venta 
                             </button>
                            </div>
                                
                        </div>
                        <br>               
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
                              <label for="mod_codigo" class="col-sm-3 control-label">Stock Almacen</label>
                              <div class="col-sm-8">
                                <input type="text" class="form-control" id="txt_stock_p" readonly>
                              </div>
                        </div>
                        <div class="form-group">
                              <label for="mod_codigo" class="col-sm-3 control-label">Stock Mostrador</label>
                              <div class="col-sm-8">
                                <input type="text" class="form-control" id="txt_stock_m_p" readonly>
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
                    <button type="button" class="btn btn-primary" id="modificarcantidad">Actualizar Cantidad</button>
              </div>
                    </form>
            </div>
      </div>
    </div>

     <!-- finish in modals -->         

</body>
</html>
