package com.ljj.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class testUTF8 {
	public static void main(String[] args) {
		String aa="测试";
		try {
			System.out.println(aa);
			String b=new String (aa.getBytes("UTF-8"));
			System.out.println(b);
			System.out.println(getUTF8XMLString(b));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	  private static String MD5(String aa) {
		// TODO Auto-generated method stub
		return null;
	}

	  /**
	   * 编码设置
	  *@param xml
	  *@return 
	  *@author ljj 
	  *@date 2017-5-10 上午10:23:10 
	  *@comment
	   */
	      
	    
	public static String getUTF8XMLString(String xml) {  
		    // A StringBuffer Object  
		    StringBuffer sb = new StringBuffer();  
		    sb.append(xml);  
		    String xmString = "";  
		    String xmlUTF8="";  
		    try {  
		    xmString = new String(sb.toString().getBytes("UTF-8"));  
		    xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");  
		    xmlUTF8 = URLDecoder.decode(xmlUTF8);
		    System.out.println("utf-8 编码：" + xmlUTF8) ;  
		    } catch (UnsupportedEncodingException e) {  
		    // TODO Auto-generated catch block  
		    e.printStackTrace();  
		    }  
		    // return to String Formed  
		    return xmlUTF8;  
		    }  

}
