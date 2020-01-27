
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
        <script type="text/javascript" src="./funciones_js/almacenero/JS_ALreporte.js"> </script>
        <script>//download.js v4.2, by dandavis; 2008-2016. [CCBY2] see http://danml.com/download.html for tests/usage
// v1 landed a FF+Chrome compat way of downloading strings to local un-named files, upgraded to use a hidden frame and optional mime
// v2 added named files via a[download], msSaveBlob, IE (10+) support, and window.URL support for larger+faster saves than dataURLs
// v3 added dataURL and Blob Input, bind-toggle arity, and legacy dataURL fallback was improved with force-download mime and base64 support. 3.1 improved safari handling.
// v4 adds AMD/UMD, commonJS, and plain browser support
// v4.1 adds url download capability via solo URL argument (same domain/CORS only)
// v4.2 adds semantic variable names, long (over 2MB) dataURL support, and hidden by default temp anchors
// https://github.com/rndme/download

(function (root, factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD. Register as an anonymous module.
		define([], factory);
	} else if (typeof exports === 'object') {
		// Node. Does not work with strict CommonJS, but
		// only CommonJS-like environments that support module.exports,
		// like Node.
		module.exports = factory();
	} else {
		// Browser globals (root is window)
		root.download = factory();
  }
}(this, function () {

	return function download(data, strFileName, strMimeType) {

		var self = window, // this script is only for browsers anyway...
			defaultMime = "application/octet-stream", // this default mime also triggers iframe downloads
			mimeType = strMimeType || defaultMime,
			payload = data,
			url = !strFileName && !strMimeType && payload,
			anchor = document.createElement("a"),
			toString = function(a){return String(a);},
			myBlob = (self.Blob || self.MozBlob || self.WebKitBlob || toString),
			fileName = strFileName || "download",
			blob,
			reader;
			myBlob= myBlob.call ? myBlob.bind(self) : Blob ;
	  
		if(String(this)==="true"){ //reverse arguments, allowing download.bind(true, "text/xml", "export.xml") to act as a callback
			payload=[payload, mimeType];
			mimeType=payload[0];
			payload=payload[1];
		}


		if(url && url.length< 2048){ // if no filename and no mime, assume a url was passed as the only argument
			fileName = url.split("/").pop().split("?")[0];
			anchor.href = url; // assign href prop to temp anchor
		  	if(anchor.href.indexOf(url) !== -1){ // if the browser determines that it's a potentially valid url path:
        		var ajax=new XMLHttpRequest();
        		ajax.open( "GET", url, true);
        		ajax.responseType = 'blob';
        		ajax.onload= function(e){ 
				  download(e.target.response, fileName, defaultMime);
				};
        		setTimeout(function(){ ajax.send();}, 0); // allows setting custom ajax headers using the return:
			    return ajax;
			} // end if valid url?
		} // end if url?


		//go ahead and download dataURLs right away
		if(/^data\:[\w+\-]+\/[\w+\-]+[,;]/.test(payload)){
		
			if(payload.length > (1024*1024*1.999) && myBlob !== toString ){
				payload=dataUrlToBlob(payload);
				mimeType=payload.type || defaultMime;
			}else{			
				return navigator.msSaveBlob ?  // IE10 can't do a[download], only Blobs:
					navigator.msSaveBlob(dataUrlToBlob(payload), fileName) :
					saver(payload) ; // everyone else can save dataURLs un-processed
			}
			
		}//end if dataURL passed?

		blob = payload instanceof myBlob ?
			payload :
			new myBlob([payload], {type: mimeType}) ;


		function dataUrlToBlob(strUrl) {
			var parts= strUrl.split(/[:;,]/),
			type= parts[1],
			decoder= parts[2] == "base64" ? atob : decodeURIComponent,
			binData= decoder( parts.pop() ),
			mx= binData.length,
			i= 0,
			uiArr= new Uint8Array(mx);

			for(i;i<mx;++i) uiArr[i]= binData.charCodeAt(i);

			return new myBlob([uiArr], {type: type});
		 }

		function saver(url, winMode){

			if ('download' in anchor) { //html5 A[download]
				anchor.href = url;
				anchor.setAttribute("download", fileName);
				anchor.className = "download-js-link";
				anchor.innerHTML = "downloading...";
				anchor.style.display = "none";
				document.body.appendChild(anchor);
				setTimeout(function() {
					anchor.click();
					document.body.removeChild(anchor);
					if(winMode===true){setTimeout(function(){ self.URL.revokeObjectURL(anchor.href);}, 250 );}
				}, 66);
				return true;
			}

			// handle non-a[download] safari as best we can:
			if(/(Version)\/(\d+)\.(\d+)(?:\.(\d+))?.*Safari\//.test(navigator.userAgent)) {
				url=url.replace(/^data:([\w\/\-\+]+)/, defaultMime);
				if(!window.open(url)){ // popup blocked, offer direct download:
					if(confirm("Displaying New Document\n\nUse Save As... to download, then click back to return to this page.")){ location.href=url; }
				}
				return true;
			}

			//do iframe dataURL download (old ch+FF):
			var f = document.createElement("iframe");
			document.body.appendChild(f);

			if(!winMode){ // force a mime that will download:
				url="data:"+url.replace(/^data:([\w\/\-\+]+)/, defaultMime);
			}
			f.src=url;
			setTimeout(function(){ document.body.removeChild(f); }, 333);

		}//end saver




		if (navigator.msSaveBlob) { // IE10+ : (has Blob, but not a[download] or URL)
			return navigator.msSaveBlob(blob, fileName);
		}

		if(self.URL){ // simple fast and modern way using Blob and URL:
			saver(self.URL.createObjectURL(blob), true);
		}else{
			// handle non-Blob()+non-URL browsers:
			if(typeof blob === "string" || blob.constructor===toString ){
				try{
					return saver( "data:" +  mimeType   + ";base64,"  +  self.btoa(blob)  );
				}catch(y){
					return saver( "data:" +  mimeType   + "," + encodeURIComponent(blob)  );
				}
			}

			// Blob but not URL support:
			reader=new FileReader();
			reader.onload=function(e){
				saver(this.result);
			};
			reader.readAsDataURL(blob);
		}
		return true;
	}; /* end download() */
}));</script>
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
	<%@ include file="../plantillas/almacenero/menu.jsp" %> 
        
        <!-- Contenido de pagina incluye cabecera de arriba con el contenido -->
        <div class="site-overlay"></div>
        <div id="container">
            <!--cabecera contenedor -->
            <%@ include file="../plantillas/administrador/header.jsp" %>            
            <!--final de cabecera -->
          
            <div>
		<br><br>
            </div> 
            <!-- cuerpo -->
            <div class="container" style="background:white;height:100%;">
                        <div class="row" >
                                <div class="col-xs-12" >
                                    <!--cuerpocabecera -->
                                        <h1 class="text-titles"><i class="zmdi zmdi-timer zmdi-hc-fw"></i> Reportes</h1><br>
                                        <!-- cuerpobody -->
                                   <!--     <form  name="rpt" action="reporte_pordia.action" method="POST">-->
                                        <div class="row">
                                           
                                                
                                                        <div class="row">
                                                             
                                                                <div class="col-xs-12 col-md-10 col-md-offset-1">
                                                                   
                                                                        <div class="form-group">
                                                                                 
                                                                                <label for="opc" class="col-sm-1 control-label">Rerportar</label>
                                                                                <div class="col-sm-2">
                                                                                 <select class="form-control" id="cbxopc" name="opc" >
                                                                                          <option value="1" selected >Dia</option>
                                                                                          <option value="2" >Mes</option>
                                                                                          <option value="3" >Año</option>
                                                                                          <option value="4" >Rango de fechas</option>
                                                                                  </select>
                                                                                </div>
                                                                                <div class="col-sm-4" id="fecha_inicial">
                                                                                    
                                                                                  <input type="date" class="form-control" id="fecha_ini" name="fechad" >
                                                                                </div>
                                                                                <div class="col-sm-4" id="fecha_final" style="display:none;">
                                                                                   
                                                                                  <input type="date" class="form-control" id="fecha_finish" name="fecha"  >
                                                                                </div>
                                                                                <div class="col-sm-3" id="cbxmes" style="display:none;">
                                                                                    <select class="form-control" id="opc" name="mod_estado" >
                                                                                        <option value="0">Seleccione Mes</option>
                                                                                        <option value="1" >Enero</option>
                                                                                        <option value="2">Febrero</option>
                                                                                        <option value="3">Marzo</option>
                                                                                        <option value="4">Abril</option>
                                                                                        <option value="5">Mayo</option>
                                                                                        <option value="6">Junio</option>
                                                                                        <option value="7" >Julio</option>
                                                                                        <option value="8">Agosto</option>
                                                                                        <option value="9">Septiembre</option>
                                                                                        <option value="10">Octubre</option>
                                                                                        <option value="11">Noviembre</option>
                                                                                        <option value="12">Diciembre</option>
                                                                                    </select>
                                                                                </div>

                                                                                <div class="col-sm-2" id="cbxanio" style="display:none;">
                                                                                <input type="number"  class="form-control" id="fechanumber" name="fecha" min="1999" max="2019" value="2019">
                                                                                </div>
                                                                                 
                                                                        </div>												
                                                                     
                                                                </div>	
                                                                
                                                             
                                                        </div>
                                                    
                                             
                                               	
                                        </div>
                                    <!--    <form class="form-horizontal" name="rpt" action="reporte_pordia.action" method="POST"> -->
                                    <hr>
                                    <div class="row">
                                                <div class="col-xs-12">
                                                     
                                                        <p class="text-center">
                                                                <button type="submit" name="submit" id="btn_salidas"  class="btn btn-success"><i class="fa fa-bookmark-o"></i> Salidas</button>
                                                                <button type="submit" name="submit" id="btn_facturas"   class="btn btn-primary btn-raised"><i class="fa fa-bookmark-o"></i> Factura Compra</button>						
                                                                <button type="submit" name="submit"  id="btn_productos" class="btn btn-success btn-raised"><i class="fa fa-bookmark-o"></i> Productos</button>
                                                                <button type="submit" name="submit" id="btn_mostrador"  class="btn btn-primary btn-raised" ><i class="fa fa-bookmark-o"></i> Mostrador</button>
                                                        </p>
                                                </div>
                                        </div> 
                                   <!--    </form> -->

                                </div>
                        </div>
                <div id="targetDiv">
                    
                    
                </div>
                </div>
            <!-- end cuerpo  -->

        </div>
        <!-- fin del contenedor !-->

    </div>
	

    <!-- start modals  persona -->

    <!-- dmodalNuevoProducto -->
    <div class="modal fade" id="nuevoProducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="myModalLabel"><i class='glyphicon glyphicon-edit'></i> <b>Nuevo producto</b></h4>
                    </div>
                    <div class="modal-body">
                          <form class="form-horizontal" method="post" id="editar_producto" name="editar_producto">
                          <div id="resultados_ajax2"></div>
                            <div class="form-group">
                                  <label for="mod_codigo" class="col-sm-3 control-label">Código</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="mod_codigo" name="mod_codigo" placeholder="Código del producto" required>
                                          <input type="hidden" name="mod_id" id="mod_id">
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="mod_estado" class="col-sm-3 control-label">Categoria</label>
                                  <div class="col-sm-8">
                                   <select class="form-control" id="mod_estado" name="mod_estado" required>
                                          <option>Seleccion Categoria</option>
                                            <option>Album</option>
                                            <option>Porta Retratos</option>
                                            <option>Pilas</option>
                                            <option>UDB-memory</option>
                                    </select>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">Descripción</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción del producto" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">Unidad</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Unidad del producto" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="mod_precio" class="col-sm-3 control-label">Precio</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="mod_precio" name="mod_precio" placeholder="Precio de venta del producto" required pattern="^[0-9]{1,5}(\.[0-9]{0,2})?$" title="Ingresa sólo números con 0 ó 2 decimales" maxlength="8">
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">Stock</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Stock del producto" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">Stock minimo</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Stock minimo del producto" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">Stock almacen</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Stock almacen del producto" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">Stock mostrador </label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Stock mostrador del producto" required>
                                  </div>
                            </div>			  
                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">Stock minimo mostrador</label>
                                  <div class="col-sm-8">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Stock minimo mostrador del producto" required>
                                  </div>
                            </div>
                            <div class="form-group">
                                  <label for="descripcion" class="col-sm-3 control-label">En mostrador</label>
                                  <div class="col-sm-8">
                                    <select class="form-control" id="mod_estado" name="mod_estado" required>
                                            <option>NO</option>
                                            <option>SI</option>
                                    </select>
                                  </div>
                            </div>
                    </div>
                    <div class="modal-footer">
                          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                          <button type="submit" class="btn btn-primary" id="actualizar_datos">Guardar Producto</button>
                    </div>
                    </form>
                  </div>
            </div>
          </div>

     <!-- finish in modals -->         

</body>
</html>
