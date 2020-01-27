<%-- 
    Document   : index
    Created on : 12/09/2019, 05:00:56 PM
    Author     : Rene Jose
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Foto Studio Carrasc</title>
  <link rel=icon href='img/foto.jpg' sizes="32x32" type="image/png">
  
<%@ include file="/paginas/plantillas/enlaces.jsp" %> 
<%@ include file="/paginas/plantillas/enlaces_css.jsp" %> 

</head>
<body style="background: url(./img/fondo.jpg) 50% 100% no-repeat; background-size: cover;">
  <!--autorization-->
  <div class="add_place none" id="autorized">
    <div class="place_form login_form" style="height:auto;">
      <i class="fa fa-times close_window" id="closeau" style="background:#218dea;padding:10px 12px;border-radius:50%;"></i>
      <h3>Autorization<span></span></h3>
      <form action="loguear" method="post" >
          <label>Usuario:<input type="text" name="txt_user" required></label>
          <label>Password:<input type="password" name="txt_contrasenia" required></label>
        <center><button type="submit" class="btn btn-success" style="margin-bottom:40px;"> <i class="fa fa-power-off"></i> Iniciar </button></center>
        
      </form>
    </div>
  </div>
	<!-- Site Overlay --> 
	<div id="container">
            <div class="row" style="padding-top:50px;">
                <div class="col-xs-12 col-md-10 col-md-offset-1">
                    <div>
                        <span style="color:white;padding:0px;margin-top:10px;font-size:18.0pt;text-shadow: black 2px 2px 2px;" > <b><em>FOTO STUDIO </em></b></span>
                    </div>
                    <div style="padding-bottom:20px;">
                        <span style="color:white;padding:0px;margin-top:10px;font-size:18.0pt;text-shadow: black 2px 2px 2px;"> 
                                            <b><i class="fa fa-film"></i></b> CARRASCO <small style="text-shadow: black 2px 2px 2px;color:white;font-size:9pt;"> S.A.C.</small><b><i class="fa fa-film"></i></b></span>
					<span style="color:white;padding:0px;margin-top:10px;font-size:18.0pt;text-shadow: black 2px 2px 2px;">
                                            <b></b></span><p class="text-center" >
                            <span class="text-uppercase" style="background-color: #f2dede;border-color: #b94a48;color: #b94a48;
                                  padding: 15px;font-weight: bold;border: 1px solid transparent;border-radius: 2px;">  
                                Usuario o Contraseña Incorrecta
                            </span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row" >
                <div class="col-xs-12 col-md-10 col-md-offset-1">
                    <a href="#" class="log_btn " style="background:#218dea;color: #ffffff;border: 2px solid;padding: 10px 40px;border-radius: 3px;font-size: 15px;float: right;">Iniciar Sesión</a>
                </div>	
            </div>

	</div>

</body>
</html>
