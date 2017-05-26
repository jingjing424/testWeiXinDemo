package com.ljj.test;
import java.util.Random;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class testDuanXin {

	public static void main(String[] args)  throws ApiException{
		String url="http://gw.api.taobao.com/router/rest";
		String appkey="23647688";
		String secret="63ace75923e0c440a62be303b4b92561";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req1 = new AlibabaAliqinFcSmsNumSendRequest();
		
		req1.setExtend("");
		req1.setSmsType("normal");
		req1.setSmsFreeSignName("ÖÐ»ã»¥Öú");
		String yzNum="";
		yzNum=getRandNum(6);
		System.out.println("yzNum==="+yzNum);
		
		req1.setSmsParamString("{number:'"+yzNum+"'}");
		req1.setRecNum("18837113454");
		req1.setSmsTemplateCode("SMS_48445080");
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req1);
		
		System.out.println(rsp.getBody());
	}
	
	public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }
	public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

}
