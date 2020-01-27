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
        <!---->
        <script type="text/javascript" src="./funciones_js/administrador/JS_Aprodvendidos.js"> </script>
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
                    <div class="col-md-12 basic" style="background:white;height:100%;">
                        <br><br><br>

                        <div class="row">
                            <div class="col-md-12">

                            <h2>Productos Vendidos</h2><br>

                            <div class="row">
                                <div class="col-md-2"> 
                                    <input type="date" class="form-control" id="txt_fechai">
                                    <small> Fecha Inicio</small>
                                </div>
                                <div class="col-md-2">
                                    <input type="date" class="form-control" id="txt_fechaf"> 
                                    <small> Fecha Final</small>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select class="form-control" id="cbx_productos">
                                            <option value="0"> Todos los Productos</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-1">
                                    <button type="button" class="btn btn-primary pull-right" id="btn_buscar">
                                    <i class="fa  fa-search"></i> Buscar</button>                                     
                                </div>                                
                                <div class="col-md-3">
                                    <button type="button" class="btn btn-primary pull-right" id="btn_exportar_producvend">
                                    <i class="fa  fa-download" ></i> Exportar PDF</button>                                     
                                </div>
                            </div>
                            
                            <hr>
                              <div class="col-md-12">
                                   <table class="table table-hover text-center">
                                      <thead>
                                        <tr>
                                          <th class="text-center">#</th>
                                          <th class="text-center">Nombre Producto</th>
                                          <th class="text-center">Stock Actual</th>
                                          <th class="text-center">Cant. Vendidos</th>
                                          <th class="text-center">Importe</th>
                                          <th class="text-center">Prec. Unit</th>
                                           <th class="text-center">Stock Almacen</th>
                                          <th class="text-center">Stock Mostrador</th>
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
                       </div>

                    </div>
              </div>
            </div>
            <!-- end cuerpo  -->

        </div>
        <!-- fin del contenedor !-->

    </div>
	

    <!-- start modals  persona -->



     <!-- finish in modals -->         

</body>
</html>
