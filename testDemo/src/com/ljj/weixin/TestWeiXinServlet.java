package com.ljj.weixin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.util.AlipaySubmit;

public class TestWeiXinServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> sParaTemp = new HashMap<String, String>();
		Map<String, String> returnTemp = new HashMap<String, String>();
		String orderNo = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String amount="10000";
		String body="微信扫码支付测试";
		sParaTemp.put("method", "dcorepay.alipay.native");
		sParaTemp.put("appid", "a20170307000004355");
		sParaTemp.put("mch_id", "m20170307000004355");
		sParaTemp.put("nonce_str", "ssdsds");
		sParaTemp.put("body", body);
		sParaTemp.put("out_trade_no", orderNo);
		sParaTemp.put("total_fee", amount);
		sParaTemp.put("trade_type", "NATIVE");
		sParaTemp.put("key", "e7278c40283888848e918c9a620cb705");
		sParaTemp.put("product_id","123456");
		sParaTemp.put("spbill_create_ip", "127.0.0.1");
		sParaTemp.put("attach", "`store_appid=s20170324000002709#store_name=中汇宝共享合作社#op_user=001");
		sParaTemp.put("notify_url", "http://share.zhonghuibao.com:81/helpCommunity/pay/weixin/AlipayNotifyPayAction.action");		
		//sParaTemp.put("notify_url","http://share.zhonghuibao.com:81/helpCommunity/page/wechartpay/return.jsp");	
		//建立请求
		String sHtmlText = AlipaySubmit.PostRequest(sParaTemp,"post");
		System.out.println("sHtmlText"+sHtmlText);
		//二维码
		String payCodeView = ViewUtil.buildForm(sHtmlText);
		System.out.println("payCodeView"+payCodeView);
		returnTemp.put("userid", "11111");
		returnTemp.put("amount", amount);
		returnTemp.put("orderNo", orderNo);
		returnTemp.put("sHtmlText", sHtmlText);
		
	}

}
