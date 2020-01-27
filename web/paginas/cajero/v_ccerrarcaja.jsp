
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
        
        <script type="text/javascript" src="./funciones_js/cajero/JS_CerrarCaja.js"> </script>
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
            .inputstyle {font-family: Arial; font-size: 60px; font-color: #00FF00}
            
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
                                        <h2><a href="08.html">Cerrar Caja</a><span></span></h2>

                                        <div class="row col-md-12 text-center">
                                              <div class="col-md-4 text-center">
                                              </div>
                                              <div class="col-md-4 text-center">
                                                <H2>Ingresar Monto </h2>
                                               <!-- <textarea class="form-control inputstyle"  placeholder="0.0" cols="5" rows="5"></textarea-->
                                               <input type="email" class="form-control inputstyle" size="40"
                                                      style="width:280px; height:100px;text-align: center"  id="txt_monto_in" aria-describedby="emailHelp" > 
                                               <input class="form-control" type="hidden" id="obj_id_user" value="<s:property value="obj_user.id_usuario" />">
                                              </div> 

                                         </div>
                                         <div class="row col-md-12"><hr></div>
                                         <div class="row col-md-12 text-center">
                                              <button type="button" class="btn btn-lg btn-success" id="btn_deacuerdo">
                                              <i class="fa  fa-plus-circle"></i> DE ACUERDO </button>
                                              <button type="button" class="btn btn-lg btn-danger"  data-toggle="modal" data-target="#nuevoProducto">
                                              <i class="fa  fa-plus-circle"></i> NO DEACUERDO </button>
                                         </div>
                                         <div class="row col-md-12"><hr></div>
                                         <div class="row col-md-12"><hr></div>
                                         <div class="row col-md-12"><hr></div>
                                         <div class="row col-md-12 text-center" id="btns_control_caja">

                                           <button type="button" class="btn btn-lg btn-warning" id="btn_cerrarcaja">
                                              <i class="fa  fa-cart-plus"></i> CERRAR CAJA </button>
                                              <button type="button" class="btn btn-lg btn-info" id="btn_cancelarcaja">
                                              <i class="fa  fa-times-circle"></i> CANCELAR </button>
                                         </div>


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
<div class="modal fade" id="nuevoProducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"><i class='glyphicon glyphicon-edit'></i> <span id="span_nuevo_p">Escribir Motivo</span></h4>
                  </div>
                  <div class="modal-body">
                        <form class="form-horizontal" method="post" id="editar_producto" name="editar_producto">
                            <div id="resultados_ajax2"></div>
                            <div class="form-group">
                                <label  class="col-sm-3 control-label">Escribir Motivo</label>
                                <div class="col-sm-8">
                                                                          
                                    <textarea class="form-control" id="Txt_motivo" name="Txt_codigo_producto" placeholder="Código del producto" rows="5" required></textarea>
                                </div>
                            </div>
                         </form> 
                  </div>
                  <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary"   id="btn_enviar_motivo">Enviar Respuesta</button>  
                  </div>
                   
                </div>
          </div>
        </div>


     <!-- finish in modals -->         

</body>
</html>
