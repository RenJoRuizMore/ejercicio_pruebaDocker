<%-- 
    Document   : menu
    Created on : 13-jul-2018, 16:28:20
    Author     : Rene Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- SideBar -->
<!--navigation swipe-->
    <div class="menu-btn">&#9776;</div>
    <nav class="pushy pushy-left">
      <!-- Perfil -->
        <div class="profile">
          <div class="avatar"><img src="img/avatar/administrador.jpg" alt="#"></div>
              <h3><a><s:property value="obj_user.nombre_persona" /></a></h3>
              <h3><span style="color:white;font-size:08.0pt;border:1px solid white;padding: 4px 14px ; border-radius:10px;">Administrador</span></h3>
        </div>
              
        <ul class="side_menu">
            <s:url id="ventas" action="ir_ventas"></s:url>
            <li><s:a href="%{ventas}"><i class="fa fa-bookmark-o"></i>Ventas</s:a></li>    

            <s:url id="compras" action="ir_compras"></s:url>
            <li><s:a href="%{compras}" ><i class="fa fa-shopping-cart"></i>Compras</s:a></li>

            <s:url id="productos" action="ir_productos_vendidos"></s:url>
            <li><s:a href="%{productos}"><i class="fa fa-server"></i>Productos Vendidos</s:a></li>

            <s:url id="servicios" action="ir_servicios"></s:url>
            <li><s:a href="%{servicios}"><i class="fa fa-th"></i>Servicios</s:a></li>
            
            <s:url id="usuarios" action="ir_usuarios"></s:url>
            <li><s:a href="%{usuarios}"><i class="fa fa-user"></i>Usuarios</s:a></li>
        </ul>
    </nav>