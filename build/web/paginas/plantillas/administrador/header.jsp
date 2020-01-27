<%-- 
    Document   : header
    Created on : 13-jul-2018, 16:40:18
    Author     : Rene Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<div class="container-fluid header inner_head">
    <div class="fixed_w">
      <div class="row">		      
        <div class="col-md-12" >
            <span style="color:white;padding:0px;margin-top:10px;font-size:18.0pt;" class="logo hidden-xs"> <b><em>FOTO STUDIO</em> CARRASCO </b></span>
            
            <span class="pull-right" style="padding:0px;margin-top:15px;font-size:12.0pt;" >
            <s:url id="salir" action="ir_index"></s:url>            
            <s:a href="%{salir}"   style="color:#fff;padding:6px 14px;background-color: #218DEA;border-radius:3px;" > 
                Salir <i class="fa fa-sign-out"></i>
            </s:a>
            </span>
            <span class=" pull-right hidden-xs" id="txt_turno" style="color:white;margin-top:15px;margin-right:20px;font-weight: 300;">
                <s:property value="obj_user.turno" />
            </span>
            <span class=" pull-right hidden-xs" style="color:white;margin-top:15px;margin-right:20px;font-weight: 300;">
                <s:property value="obj_user.nombre_persona" />
            </span>
        </div>
      </div>
    </div>
</div>
