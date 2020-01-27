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
        <script type="text/javascript" src="./funciones_js/administrador/JS_Acompras.js"> </script>
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
            <div class="container" style="background:white">
              <div class="row">
              <!--content-->
                    <div class="col-md-12 basic">
                                 <!--Blog post style one-->
                              <div class="row" >
                                  <div class="col-md-12">
                                    <br>
                                    <div class="container">
                                      <div class="row"> <h2>Compras</h2> </div>
                                      <br>
                                      <div class="row">
                                        <div class="col-sm-2">
                                            <div class="form-group">
                                                <select class="form-control" id="cbx_opcion">
                                                    <option value="1"> Por Fecha</option>
                                                    <option value="2"> Por Mes</option>
                                                    <option value="3"> Por Año</option>
                                                    <option value="4"> Por Fechas</option>
                                              </select>
                                             </div>
                                        </div>
                                        <div class="col-sm-2" id="div_fechai">
                                            <input type="date" class="form-control" id="txt_fechai">
                                        </div>
                                        <div class="col-sm-2" id="div_fechaf">
                                            <input type="date" class="form-control" id="txt_fechaf">
                                            <small style="align-text:center;"> Fecha Final</small>
                                        </div>                                        
                                        <div class="col-sm-2" id="div_mes">
                                            <div class="form-group">
                                                <select class="form-control" id="cbx_mes">
                                                    <option value="01"> Enero  </option>
                                                    <option value="02"> Febrero  </option>
                                                    <option value="03"> Marzo  </option>
                                                    <option value="04"> Abril  </option>
                                                    <option value="05"> Mayo  </option>
                                                    <option value="06"> Junio  </option>
                                                    <option value="07"> Julio  </option>
                                                    <option value="08"> Agosto  </option>
                                                    <option value="09"> Septiembre  </option>
                                                    <option value="10"> Octubre  </option>
                                                    <option value="11"> Noviembre  </option>
                                                    <option value="12"> Diciembre  </option>
                                                </select>
                                             </div>
                                        </div>  
                                        <div class="col-md-1" id="div_anio">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="txt_anio" onKeyPress="return soloNumeros(event)" maxlength="4">
                                             </div>
                                        </div>                                          
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <select class="form-control" id="cbx_provee">
                                                    <option value="0"> ---Todos los Proveedores --- </option>                                                      
                                                </select>
                                             </div>
                                        </div>  
                                        <div class="col-md-1">
                                            <button type="button" class="btn btn-primary" id="btn_buscar">
                                                <i class="fa  fa-search"></i> Buscar
                                            </button>
                                        </div> 
                                          
                                    </div>
                                    <div class="row" >
                                        <div class="col-md-2 ">
                                            <button type="button" class="btn btn-primary" id="btn_exportar_pdf">
                                            <i class="fa  fa-file-pdf-o"></i> Exportar PDF
                                          </button>
                                      </div>
                                        
                                        <div class="col-sm-4 col-sm-offset-6" style="font-size: 32px">S/.
                                            <span id="lbl_suma"> </span>
                                        </div>
                                    </div>
                                  </div>
                                  <hr>
                                    <div class="col-md-12">
                                         <table class="table table-hover text-center">
                                            <thead>
                                              <tr>
                                                <th class="text-center">#</th>
                                                <th class="text-center">Fecha</th>
                                                <th class="text-center">Numero Factura</th>
                                                <th class="text-center">Proovedor</th>
                                                <th class="text-center">Monto</th>
                                                <th class="text-center">Almacenero</th>
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
