package com.ljj.weixin;

import java.util.HashMap;
import java.util.Map;

import com.alipay.util.AlipaySubmit;

public class testDemo {
public static void main(String[] args) {
	Map<String, String> sParaTemp = new HashMap<String, String>();
	String amount="100";
	//String body="注册认证费";
	sParaTemp.put("appid", "a20170307000004355");
	sParaTemp.put("mch_id", "m20170307000004355");
	sParaTemp.put("nonce_str", "1649367980");
	sParaTemp.put("device_info", "1000");
	sParaTemp.put("attach", "`store_appid=s20170324000002709#store_name=中汇宝共享合作社#op_user=001");
	sParaTemp.put("body", "支付测试");
	sParaTemp.put("out_trade_no", "1234567890123");
	sParaTemp.put("total_fee", amount);
	sParaTemp.put("trade_type", "NATIVE");
	sParaTemp.put("key", "e7278c40283888848e918c9a620cb705");
	//sParaTemp.put("notify_url", "http://share.zhonghuibao.com:81/helpCommunity/pay/weixin/AlipayNotifyPayActiona.action");	
	sParaTemp.put("product_id","123456");
	sParaTemp.put("spbill_create_ip", "127.0.0.1");
	sParaTemp.put("notify_url","http://share.zhonghuibao.com:81/helpCommunity/page/wechartpay/return.jsp");	
	//建立请求
	String sHtmlText = AlipaySubmit.PostRequest(sParaTemp,"post");
	System.out.println("3333333333333"+sHtmlText);
	//二维码
	String payCodeView = ViewUtil.buildForm(sHtmlText);
	System.out.println("4444444444444"+payCodeView);
}
}
