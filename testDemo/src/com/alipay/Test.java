package com.alipay;

import java.util.HashMap;
import java.util.Map;

import com.alipay.util.AlipaySubmit;

public class Test {
	
	public final static String sign_type = "aaa";
	
	
	public String erweima(){
		
		Map<String, String> sParaTemp = new HashMap<String, String>();
		
		String sHtmlText = AlipaySubmit.PostRequest(sParaTemp,"post");
		
		return sHtmlText;
	}

}
