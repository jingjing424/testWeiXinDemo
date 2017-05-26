<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script src="../../js/common/jquery-2.1.0.min.js"> </script>
   <script src="../../js/wechartpay/qrcode.js"></script>
   <!-- <script src="../../js/wechartpay/wechartpay.js"></script> -->
  
    <title>微信支付</title> 
  </head>
 

  <body>
<!--
    	<div  align="center">
    		<img src="../../img/weipayimg.jpg" alt="扫码支持" title="扫一扫" /> 		
    		<input type="button"   style="width: 50px; height:50px;" value="成功"  onclick="order();" />
    	<input type="text" id="userId" value="<%= request.getParameter("userId")%>">
    	</div>-->
	 <div align="center" id="qrcode">
		<p >
		扫我，扫我
		<br><br>
	加入我们 小盆友。请付100吧！
		</p>
	</div> 
	
  </body>
  <script>
 	//这个地址是Pay.java生成的code_url,这个很关键
	var url = "weixin://wxpay/bizpayurl?pr=iHuVKx1";
	
	//参数1表示图像大小，取值范围1-10；参数2表示质量，取值范围'L','M','Q','H'
	var qr = qrcode(10, 'M');
	qr.addData(url);
	qr.make();
	var dom=document.createElement('DIV');
	dom.innerHTML = qr.createImgTag();
	var element=document.getElementById("qrcode");
	element.appendChild(dom);
 </script>

</html>
