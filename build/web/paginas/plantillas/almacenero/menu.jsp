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
        <div class="avatar"><img src="img/avatar/almacenero.jpg" alt="#"></div>
            <h3><a><s:property value="obj_user.nombre_persona" /></a></h3>
            <h3><span style="color:white;font-size:08.0pt;border:1px solid white;padding: 4px 14px ; border-radius:10px;">Almacén</span></h3>
        </div>
        <ul class="side_menu">
            <s:url id="verificar" action="ir_verificar_stock"></s:url>
            <li><s:a href="%{verificar}"><i class="fa fa-bookmark-o"></i>Verificar Stock</s:a></li> 
            
            <s:url id="factura" action="ir_factura_compra"></s:url>
            <li><s:a href="%{factura}"><i class="fa fa-th"></i>Factura Compra</s:a></li> 
            
            <s:url id="salida" action="ir_salidas_almacen"></s:url>
            <li><s:a href="%{salida}"><i class="fa fa-list"></i>Salidas de almacén</s:a></li>
                        
            <s:url id="reporte" action="ir_reportes"></s:url>
            <li><s:a href="%{reporte}"><i class="fa fa-file-text-o"></i>Reportes</s:a></li>
        </ul>
    </nav>