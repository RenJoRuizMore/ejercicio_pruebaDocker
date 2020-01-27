
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
        
        <script type="text/javascript" src="./funciones_js/cajero/JS_ListaVendedores.js"> </script>
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
                                      <div class="row">
                                          
                                              <h2><a href="08.html">Reporte De Ventas-Reportes de venta por Vendedor</a><span></span></h2>
                                          
                                              <div class="col-md-4">
                                               <a id="btn_imprimir_efectivo" href="#" style="color: #fff;background-color: #218DEA;border-color: #2e6da4;
                                                             padding: 8px 12px;border-radius: 4px;
                                                    transition: 0.3s;font-size: 14px;
                                                    font-weight: normal;
                                                    line-height: 1.42857143;
                                                    text-align: center;
                                                    margin-left: 30px;
                                                    margin-bottom: 30px;"><i class="fa fa-print"></i> Ventas En Efectivo </a>
                                              </div>
                                              <div class="col-md-4">
                                                  <a id="btn_imprimir_tarjeta" href="#" style="color: #fff;background-color: #218DEA;border-color: #2e6da4;
                                                             padding: 8px 12px;border-radius: 4px;
                                                            transition: 0.3s;font-size: 14px;
                                                            font-weight: normal;
                                                            line-height: 1.42857143;
                                                            text-align: center;
                                                            margin-left: 130px;
                                                            margin-bottom: 30px;
                                                            "><i class="fa fa-print"></i> Ventas En Tarjeta </a>  
                                              </div>
                                          
                                          
                                      </div>
                                      
                                   
                                      <hr>
                                        
                                      
                                    
                                    <div class="container">
                                     </div>
                                    <div class="p_text">
                                         <table class="table table-hover text-center">
                                            <thead>
                                              <tr>
                                                <th class="text-center">#</th>
                                                <th class="text-center">Turno</th>
                                                <th class="text-center">Vendedor</th>
                                                <th class="text-center">Ventas del Dia</th>
                                                <th class="text-center">Productos Vendidos</th>
                                              </tr>
                                            </thead>
                                            <tbody id="contenido_tmp_detfactura">
                                              
                                                
                                            </tbody>
                                          </table>
                                    </div>
                                    <div aria-label="Page navigation example">
                                          <ul class="pagination">
                                            <li class="page-item">
                                              <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                              </a>
                                            </li>
                                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                                            <li class="page-item">
                                              <a class="page-link" href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">Next</span>
                                              </a>
                                            </li>
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
	

    <!-- start modals  persona -->



     <!-- finish in modals -->         

</body>
</html>
