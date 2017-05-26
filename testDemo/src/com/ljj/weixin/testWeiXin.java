package com.ljj.weixin;

import java.util.SortedMap;
import java.util.TreeMap;



public class testWeiXin {
	  public static final String appid = "a20170307000004355";//在微信开发平台登记的app应用  
	    public static final String appsecret = "5c20cc0c85be7c4cb7a2a237a4d929b9";  
	    public static final String partner = "m20170307000004355";//商户号  
	    public static final String partnerkey ="e7278c40283888848e918c9a620cb705";//不是商户登录密码，是商户在微信平台设置的32位长度的api秘钥，  
	   // public static final String createOrderURL="https://api.mch.weixin.qq.com/pay/unifiedorder";  
	   // public static final String backUri="http://XXXXXXXX/api/weixin/topay.jhtml";//微信支付下单地址 
	    public static final String notify_url="http://share.zhonghuibao.com:81/helpCommunity/page/wechartpay/return.jsp";//异步通知地址  
	
public String getCode(){
	// 1 参数
	// 订单号
	String orderId = "201705031020";
	 //商户门店的ID
	String store_appid="s20170324000002709";
	//商户门店名称
	String store_name="中汇宝共享合作社";
	// 附加数据 原样返回
	String attach = "`store_appid="+store_appid+"#store_name="+store_name;
	// 总金额以分为单位，不带小数点
	String totalFee = getMoney("100");
	
	// 订单生成的机器 IP
	String spbill_create_ip = "127.0.0.1";
	// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
	//String notify_url = "";
	String trade_type = "NATIVE";
     
	// 商户号
	String mch_id = partner;
	// 随机字符串
	String nonce_str = getorderNo();

	// 商品描述根据情况修改
	String body = "中汇宝共享合作社微信支付测试";

	// 商户订单号
	String out_trade_no = orderId;
    //商户ID
	String  product_id="1234567890";

	SortedMap<String, String> packageParams = new TreeMap<String, String>();
	packageParams.put("appid", appid);
	packageParams.put("mch_id", mch_id);
	packageParams.put("nonce_str", nonce_str);
	packageParams.put("body", body);
	packageParams.put("attach", attach);
	packageParams.put("out_trade_no", out_trade_no);
	
	// 这里写的金额为1 分到时修改
	packageParams.put("total_fee", totalFee);
	packageParams.put("spbill_create_ip", spbill_create_ip);
	packageParams.put("notify_url", notify_url);
  
	packageParams.put("trade_type", trade_type);//

	RequestHandler reqHandler = new RequestHandler(null, null);
	reqHandler.init(appid, appsecret, partnerkey);

	String sign = reqHandler.createSign(packageParams);
	String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
			+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
			+ "</nonce_str>" + "<sign>" + sign + "</sign>"
			+ "<body><![CDATA[" + body + "]]></body>" 
			+ "<out_trade_no>" + out_trade_no
			+ "</out_trade_no>" + "<attach><![CDATA [" + attach + "]]></attach>"
			+ "<total_fee>" + totalFee + "</total_fee>"
			+ "<spbill_create_ip>" + spbill_create_ip
			+ "</spbill_create_ip>" + "<notify_url>" + notify_url
			+ "</notify_url>" + "<trade_type>" + trade_type
			+ "</trade_type>" +"<product_id>"+ product_id+"</product_id>"+"</xml>";
	String code_url = "";
	String createOrderURL = "https://api.cib.dcorepay.com/pay/unifiedorder";
	System.out.println("4444444");
	System.out.println("xml:"+xml);
	code_url = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
	System.out.println("code_url----------------"+code_url);
	
	return code_url;
}

public String getorderNo() {
	// 随机数
	String currTime = TenpayUtil.getCurrTime();
	// 8位日期
	String strTime = currTime.substring(8, currTime.length());
	// 四位随机数
	String strRandom = TenpayUtil.buildRandom(4) + "";
	// 10位序列号,可以自行调整。
	return strTime + strRandom;
}
	
/**
 * 元转换成分
 * @param money
 * @return
 */
public static String getMoney(String amount) {
	if(amount==null){
		return "";
	}
	// 金额转化为分为单位
	String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
    int index = currency.indexOf(".");  
    int length = currency.length();  
    Long amLong = 0l;  
    if(index == -1){  
        amLong = Long.valueOf(currency+"00");  
    }else if(length - index >= 3){  
        amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
    }else if(length - index == 2){  
        amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
    }else{  
        amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
    }  
    return amLong.toString(); 
}	
	
	public static void main(String[] args) {
		testWeiXin tt=new testWeiXin();
		System.out.println(tt.getCode());
	}

}
