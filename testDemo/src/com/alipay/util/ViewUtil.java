package com.alipay.util;
/**
* @ClassName: ViewUtil 
* @Description: 生成二维码页面
* @author zkq
* @date 2017年3月25日
 */
public class ViewUtil {
	public static String buildForm(String url)
	   {
	     StringBuffer sbHtml = new StringBuffer();
	     System.out.println("生成页面");
	     //sbHtml.append("<script src='resources/js/qrcode.js'></script>");
	     sbHtml.append("<div align='center' id='qrcode'><p ><br><br></p></div>");
	     
		sbHtml.append("<script>"+
	 	//这个地址是Demo.java生成的code_url,这个很关键
		"var url = '"+url+"'; "+
		
		//参数1表示图像大小，取值范围1-10；参数2表示质量，取值范围'L','M','Q','H'
		"var qr = qrcode(10, 'M');"+
		"qr.addData(url);"+
		"qr.make();"+
		"var dom=document.createElement('DIV');"+
		"dom.innerHTML = qr.createImgTag();"+
		"var element=document.getElementById('qrcode');"+
		"element.appendChild(dom);"+ 
		"</script>");
		
	     return sbHtml.toString();
	   }
}
