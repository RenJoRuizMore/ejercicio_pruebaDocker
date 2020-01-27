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
        <div class="avatar"><img src="img/avatar/cajera.jpg" alt="#"></div>
            <h3><a><s:property value="obj_user.nombre_persona" /></a></h3>
            <h3><span style="color:white;font-size:08.0pt;border:1px solid white;padding: 4px 14px ; border-radius:10px;">Cajero</span></h3>
      </div>
      <ul class="side_menu">
        <s:url id="abrir" action="ir_abrircaja"></s:url>
        <li><s:a href="%{abrir}"><i class="fa fa-fax"></i>Abrir Caja</s:a></li>  

        <s:url id="ventas" action="ir_ingresar_ventas"></s:url>
        <li id="submenu_ingventas"><s:a href="%{ventas}"><i class="fa fa-server"></i>Ingresar Ventas</s:a></li>

        <s:url id="reportes" action="ir_reportes_vendedores"></s:url>
        <li id="submenu_reportvende"><s:a href="%{reportes}"><i class="fa fa-th"></i>Reportes de Ventas</s:a></li>  

        
        <s:url id="reportessx" action="ir_productos_vendedores_caja"></s:url>
        <li id="submenu_reportproducven"><s:a href="%{reportessx}"><i class="fa fa-th"></i>Productos Vendidos</s:a></li> 
        
        <s:url id="cerrar" action="ir_cerrarcaja"></s:url>
        <li id="submenu_cerrar-caja"><s:a href="%{cerrar}" ><i class="fa fa-times-circle"></i>Cerrar Caja</s:a></li>
      </ul>
    </nav>