
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
        
        <script type="text/javascript" src="./funciones_js/cajero/JS_IngresarVenta.js"> </script>
        
        <link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
        <script src="./js/jquery-ui.js"></script>
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
	<%@ include file="../plantillas/cajero/menu.jsp" %> 
        
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
                            <h2><a href="#">Ingresar Ventas</a><span></span></h2>
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="row">                                    
                                        <div class="form-group col-md-6">
                                            <label>Ingresar Numero Pedido</label>
                                            <div class="input-group date" id="txt_numpedido">
                                                <input type="text" class="form-control" id="txtnumeropedido"  placeholder="Ingresar Numero Pedido" maxlength="11" >
                                                <span class="input-group-addon">
                                                    <span class="fa fa-search"></span>
                                                </span>
                                            </div>
                                        </div>    
                                      
                                    </div>
                                    <div class="row">                                    
                                        <div class="form-group col-md-6">
                                            <label >Tipo de Pago :</label>
                                            <select class="form-control" id="cbx_tipodoc">
                                                <option value="1">Efectivo</option>
                                                <option value="2">Con Tarjeta</option>
                                           </select>
                                        </div>
                                        <div class="col-md-6">
                                            <button style="margin-top:27px;" type="button" class="btn btn-warning" 
                                                    data-toggle="modal" data-target="#buscarproducto" id="btn_pago_tarjeta">
                                              <i class="fa  fa-refresh"></i> Ingresar Datos de Pago 
                                            </button>
                                        </div>
                                    </div>                                
                                    <div class="row">                                    
                                        <div class="form-group col-md-7">
                                            <button style="margin:35px;" type="button" class="btn  btn-lg" id="btn_cancelar_venta">
                                                <i class="fa  fa-cart-arrow-down"></i> Cancelar Venta
                                            </button>
                                            <button type="button" class="btn btn-lg btn-primary" id="btn_guardar_venta">
                                                <i class="fa  fa-cart-plus"></i> Realizar Venta 
                                            </button>
                                        </div>
                                      
                                    </div>  
                                </div>
                                <div class="col-md-4" >
                                     <div class="pg style_two" style="border:2px solid #0a6abd;border-radius: 10px">
                                         <h2 class="text-center"  id="txt_tipodoc">  Factura:<br>  <span id="n_pedido_str" ></span>   </h2>
                                        <hr>
                                        <h1 class=" text-lead fa-4x text-center text-primary" id="lbl_total">S/. 0.0</h1>
                                        <p class="text-center">Cliente : <b><span id="lbl_cliente"> </span></b></p>
                                        <p class="text-center">Nº Documento : <b><span id="lbl_dni"> </span></b></p>
                                        
                                     </div>
                                </div> 
                            </div>
                            <div class="row">
                               <div class="row col-md-12">
                                    <div class="col-md-5">
                                        <div class="col-md-8">
                                                <div class="form-group">
                                                  <label for="exampleInputEmail1">Pago Con: </label>
                                                  <input type="email" class="form-control" id="txt_pago_con" aria-describedby="emailHelp" placeholder="Numero de Pedido">
                                                  <small id="emailHelp" class="form-text text-muted">*Numero de Documento autocompletado</small>
                                                </div>
                                        </div>
                                        <div class="col-md-3">
                                           
                                           <button type="button" style="margin-top:25px;" class="btn btn-md btn-primary">Calcular</button>
                                        </div>   
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Vuelto: </label>
                                            <input type="email" class="form-control" id="txt_vuelto" aria-describedby="emailHelp" placeholder="Numero de Pedido">
                                            <small id="emailHelp" class="form-text text-muted">*Numero de Documento autocompletado</small>
                                        </div>
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
                                            <td  style="font-weight:bold;text-align:right;">Sub-Total</td>
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

    <!-- dmodalpago Tarjeta -->
    <div class="modal fade" id="buscarproducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog modal-xs" role="document">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"><b> Datos de Cuenta para el pago con tarjeta</b></h4>
              </div>
              <div class="modal-body">
                  <form class="form-horizontal" method="post" id="editar_producto" name="editar_producto">
                        <div class="form-group">
                              <label for="mod_codigo" class="col-sm-3 control-label">Nº de Voucher </label>
                              <div class="col-sm-8">
                                <input type="text" class="form-control" id="txt_name_p" >
                              </div>
                        </div>
                  
                        <div class="form-group">
                            <label for="mod_codigo" class="col-sm-3 control-label"> Sustento : </label>
                            <div class="col-sm-8">
                                <select class="form-control" id="cbx_tipotarjeta">
                                <option value="1">Tarjeta de Debito</option>
                                <option value="2">Tarjeta de Credito</option>
                            </select>
                            </div>

                        </div>
                  <br>
                        <div class="form-group">
                            <label for="mod_codigo" class="col-sm-3 control-label"> Banco : </label>
                            <div class="col-sm-8">
                                <select class="form-control" id="cbx_banco">
                                <option value="1">Credito del Peru</option>
                                <option value="2">Pichincha</option>
                                <option value="3">BBVVA</option>
                                <option value="4">Caja Piura</option>
                            </select>
                            </div>
                        </div>
                  <br>     
                  <div class="form-group">
                              <label for="descripcion" class="col-sm-3 control-label">Tarjeta :</label>
                               <div class="col-sm-8">
                                <select class="form-control" id="cbx_tajeta_visa">
                                <option value="1">Visa</option>
                                <option value="2">Matercard</option>
                                <option value="3">Electron</option>
                            </select>
                            </div>
                        </div>
                        
              </div>
              <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                    <button type="button" class="btn btn-primary" id="btn_send_pago">Enviar</button>
              </div>
               </form>     
            </div>
      </div>
    </div>

     <!-- finish in modals -->         

</body>
</html>
