
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
         <script type="text/javascript" src="./funciones_js/vendedor/JS_ReporteVenta.js"> </script>
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
	<%@ include file="../plantillas/vendedor/menu.jsp" %> 
        
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
            <div class="container">
              <div class="row">
              <!--content-->
                    <div class="col-md-12 basic">
                        <div class="place_li_cont">
                                 <!--Blog post style one-->
                              <div class="post p_style_one">
                                  <div class="post_info">
                                      
                                    <h2><a href="08.html">Ventas Realizadas</a><span></span></h2>
                                    <div class="row">
                                        <div class="col-sm-4">
                                        <a href="#" style="color: #fff;background-color: #218DEA;border-color: #2e6da4;
                                                             padding: 8px 12px;border-radius: 4px;
                                        transition: 0.3s;font-size: 14px;
                                        font-weight: normal;
                                        line-height: 1.42857143;
                                        text-align: center;" id="btn_imprimirventa"><i class="fa fa-print"></i> Imprimir Ventas</a>
                                        </div>
                                        <div class="col-sm-3 col-sm-offset-4" style="font-size: 32px">S/.
                                            <span id="lbl_suma" > </span>
                                       </div>
                                        
                                    </div>
                                        
                                    
                                    
                                        <input class="form-control" type="hidden" id="obj_id_user" value="<s:property value="obj_user.id_usuario" />">
                                  
                                    <hr>
                                    <div class="p_text">
                                         <table class="table table-hover text-center">
                                            <thead>
                                              <tr>
                                                <th class="text-center">#</th>
                                                <th class="text-center">Numero Pedido</th>
                                                <th class="text-center">Pago</th>
                                                <th class="text-center">IGV</th>
                                                <th class="text-center">Vuelto</th>

                                                <th class="text-center">Total</th>
                                                <th class="text-center">estado</th>
                                              </tr>
                                            </thead>
                                            <tbody id="contenido_tmp_detfactura">
                                             
                                             
                                            </tbody>
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



     <!-- finish in modals -->         

</body>
</html>
