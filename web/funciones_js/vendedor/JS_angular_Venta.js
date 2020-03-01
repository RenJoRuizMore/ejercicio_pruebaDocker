/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// obtener los datos del arreglo

// ===== AUTOCOMPLETE INPUT PRODUCTOS
 var idproducto;
 var catproduct;
 var nombreproduc;
 var precio ;
 var codigo_product;
 function trunc(x, posiciones) {
        var s = x.toString();
        var l = s.length;
        var decimalLength = s.indexOf('.') + 1;
        var numStr;
        if (decimalLength != 0) {
            numStr = s.substr(0, decimalLength + posiciones);
        } else {
            numStr = x;
        }
        return Number(numStr);
    }
    
  function getAddZerosToRight(str) {// 100.3 ==> 100.30 o 100 ==> 100.00
        str = "" + str;
        var divisiones = str.split('.');
        if (divisiones.length == 1) {
            str = str + ".00";
        } else {
            if (divisiones[1].length == 1) {
                str = str + "0";
            }
        }
        return str;
    }
    function zfill(number, width) {
        var numberOutput = Math.abs(number); /* Valor absoluto del número */
        var length = number.toString().length; /* Largo del número */
        var zero = "0"; /* String de cero */

        if (width <= length) {
            if (number < 0) {
                return ("-" + numberOutput.toString());
            } else {
                return numberOutput.toString();
            }
        } else {
            if (number < 0) {
                return ("-" + (zero.repeat(width - length)) + numberOutput.toString());
            } else {
                return ((zero.repeat(width - length)) + numberOutput.toString());
            }
        }
    }   
    
$(document).ready(function () { 
    var listproduc = [];
    carga_prueba();
    
    $("#txt_produc").autocomplete({
        minLength: 1,
        source: listproduc,
        focus: function (event, ui) {
            $("#txt_produc").val(ui.item.label);
            return false;
        },
        select: function (event, ui) {
            $("#txt_produc").val(ui.item.label);
            idproducto = ui.item.id;
            catproduct = ui.item.cat;
            nombreproduc=ui.item.label;
            precio =ui.item.precio;
           codigo_product = ui.item.codigo_pro;
            $("#txtprecio").val(ui.item.precio);
            $("#txtstocka").val(ui.item.sa);
            $("#txtstockm").val(ui.item.sm);
            $("#txtcantidad").focus();
            return false;
        }
    });
    
    
    function listar_producto(str) {
        $.ajax({
            type: "GET",
            data: {texto_busqueda_pro: str, allproductos: 1, productservis: 1},
            url: 'busqueda_producto',
            success: function (response) {//console.log("-- "+response.lista_busqueda_factura);
                var cantidad = response.lista_busqueda_producto.length;//console.log("c"+cantidad);
                var listaa = response.lista_busqueda_producto;
                agregar_filas(cantidad, listaa);
            }
        });
    }// agregar las filas 
    function agregar_filas(cantidad_datos, respondar) {//alert(cantidad_datos);  
        for (var i = 0; i < cantidad_datos; i++) {
            var produc = respondar[i].codigo_producto + " " + respondar[i].descripcion_producto;
            var codigo = respondar[i].codigo_producto;
            var idp = respondar[i].id_producto;
            var preciop = respondar[i].precio_producto;
            var stock_a = respondar[i].stock_a_producto;
            var stock_m = respondar[i].stock_m_producto;
            var cat = respondar[i].id_categoria;
            listproduc.push({value: produc, label: produc,codigo_pro:codigo, id: idp, precio: preciop, sa: stock_a, sm: stock_m, cat: cat});
        }
    }
    
    
    $("#txt_produc").on("keydown", function (event) {
        if (event.keyCode == $.ui.keyCode.LEFT || event.keyCode == $.ui.keyCode.RIGHT || event.keyCode == $.ui.keyCode.UP || event.keyCode == $.ui.keyCode.DOWN || event.keyCode == $.ui.keyCode.DELETE || event.keyCode == $.ui.keyCode.BACKSPACE)
        {
            idproducto = 0;
            catproduct = 0;
            $("#txtprecio").val("");
            $("#txtstocka").val("");
            $("#txtstockm").val("");
        }
        if (event.keyCode == $.ui.keyCode.DELETE) {
            $("#txt_produc").val("");
            idproducto = 0;
            catproduct = 0;
            $("#txtprecio").val("");
            $("#txtstocka").val("");
            $("#txtstockm").val("");
        }
    });
    function carga_prueba() {
        //generar_pedido();
        $("#btn_actualizar_cliente").hide();
        listar_producto("");
        
        $("#txt_produc").focus();
    }
    

});
//::::::::::::::. comienza las interpretaciones en angularjs
var modulo_venta=angular.module('m_venta',[]);

modulo_venta.controller('ctrl_venta',function controlador_venta($scope,$http){
    $scope.btn_recuperar_futura_venta=function(){
        $http.get('probar_deserializacion').
         then(function successCallback(response){
           alert("paso"+  response);
         });
    },
    $scope.btn_venta_futura_venta= function(){
       // alert("hola estamos probando angular ");
       $http.put('enviar_objeto',{
           dni : $scope.obj_venta.dni,
           tipo_documento : $scope.obj_venta.tipo_documento,
           nombre_cliente : $('#txtname').val(),
           direccion_cliente: $('#txtdireccion').val(),
           celular_cliente : $('#txtcelular').val(),
           sub_total : $('#span_total').text(),
           igv : $('#span_igv').text(),
           total : $('#span_Neto').text(),
           numero_pedido: $('#n_pedido_str').text(),
           products : $scope.products
           //
           
       }).
               then(function successCallback(response){
           
       },
       function errorCallBack(response){
           alert("hubo un error"+ response);
       });
       
    },
    // declare array products 
    $scope.products =[];
    $scope.getkey = function(event){
        if(event.keyCode === 13){
           // add function add productos in 
           // listproduc[0];
            var cantidad = $('#txtcantidad').val();
            var precio = $('#txtprecio').val();
            cantidad = parseInt(cantidad);
            precio = trunc(precio, 2);
            precio = parseFloat(precio);
            var importe = 0;
            importe = parseFloat(importe);
            importe = precio * cantidad;
            importe = trunc(importe, 2);
            
           var obj_prod ={
            product_idproducto : idproducto,
            product_catproduct : catproduct,
            product_codigo :codigo_product,
            product_nombre : nombreproduc,
            product_cantidad :cantidad,
            product_precio : precio,
            product_total : importe
           };
           $scope.products.push(obj_prod);
            
           // agregar en el arreglo de los controladores 
           //alert(areglo_p[0].product_nombre);
           funcion_prueba();
           
           $('#txtcantidad').val("");
           $('#txtprecio').val("");
           $('#txtstocka').val("");
           $('#txtstockm').val("");
           $('#txt_produc').val("");
           $('#txt_produc').focus();
           
        }
    },
    $scope.eliminar= function($index){
        $scope.products.splice($index, 1);
        funcion_prueba();
        
    },
    funcion_prueba = function(){
         var total = 0;
            var igv = 0;
            var total_neto = 0;
            total = parseFloat(total);
            igv = parseFloat(igv);
            total_neto = parseFloat(total_neto);
            // recoorer el arreglo
            for(var i=0; i< $scope.products.length;i++){
               importe = parseFloat($scope.products[i].product_total); 
              
                total = total + importe;
            }
           
           var migv = $('#txt_igv_tmp').val();
        migv = "0." + migv;
        migv = parseFloat(migv);

        total = trunc(total, 2);
        total = parseFloat(total);

        igv = migv * total; //console.log("igv-"+igv);
        igv = trunc(igv, 2);//console.log("trunc igv-"+igv);
        igv = parseFloat(igv); //console.log("parse-"+igv);

        total_neto = total;
        total_neto = trunc(total_neto, 2);
        
        $('#span_total').text(trunc(getAddZerosToRight(total - igv), 2));
        $('#span_igv').text(getAddZerosToRight(igv));
        $('#span_Neto').text(getAddZerosToRight(total));
        $('#txtmonto_fc').val(total_neto);
        $('#lbl_total').text("S/. " + getAddZerosToRight(total_neto));
    }
    
    
});
