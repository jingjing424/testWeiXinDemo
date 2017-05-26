package com.alipay.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.MD5;
import com.alipay.tool.HttpClientConnectionManager;

/* *
 *类名：AlipaySubmit
 *功能：支付宝各接口请求提交类
 *详细：构造支付宝各接口表单HTML文本，获取远程HTTP数据
 *版本：3.3
 *修改人：zkq
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipaySubmit {
    
    /**
     * 兴业银行支付宝提供给商户的服务接入网关URL(新)
     */
   // private static final String ALIPAY_GATEWAY_NEW = "https://api.cib.dcorepay.com/pay/gateway";
	 private static final String ALIPAY_GATEWAY_NEW = "https://api.cib.dcorepay.com/pay//unifiedorder";
    public static DefaultHttpClient httpclient;
    static
    {
      httpclient = new DefaultHttpClient();
      httpclient = (DefaultHttpClient)HttpClientConnectionManager.getSSLInstance(httpclient);
    }
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        System.out.println(prestr);
        prestr+="&key="+AlipayConfig.key;
        if(AlipayConfig.sign_type.equals("MD5") ) {
        	mysign = MD5.mySign(prestr,AlipayConfig.input_charset).toUpperCase();
        }
        return mysign;
    }
	
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
//        sPara.put("sign_type", AlipayConfig.sign_type);

        return sPara;
    }


    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }
    
   
	
	 /**
     * 建立请求，以表单XML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @return 返回二维码
     */
    public static String PostRequest(Map<String, String> sParaTemp, String strMethod) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbXml = new StringBuffer();
        sbXml.append("<xml>");
        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);
            sbXml.append("<"+name+">"+value+"</"+name+">");
        }
        
        sbXml.append("</xml>");
        System.out.println(sbXml);
        //sbXml=new StringBuffer();
       // sbXml.append("<xml><appid>a20170307000004355</appid><mch_id>m20170307000004355</mch_id><body><![CDATA[JSAPI 支付测试]]></body><attach><![CDATA[`store_appid=s20170324000002709#store_name=中汇宝共享合作社]]></attach><total_fee>10000</total_fee><spbill_create_ip>127.0.0.1</spbill_create_ip><notify_url>http://share.zhonghuibao.com:81/helpCommunity/page/wechartpay/return.jsp</notify_url><nonce_str>b927722419c52622651a871d1d9ed8b2</nonce_str><out_trade_no>Y201705051123</out_trade_no><device_info>1000</device_info><trade_type>JSAPI</trade_type><sign>59D0544E013E4B4503E9FBCA5A6BFEE8</sign></xml>");
       // sbXml.append("<xml><appid>wx2421b1c4370ec43b</appid><attach>支付测试</attach><body>树屋树递快递到寝配送服务</body><detail>树屋树递快递到寝配送服务</detail><device_info>WEB</device_info><fee_type>CNY</fee_type><mch_id>10000100</mch_id><nonce_str><![CDATA[7&-1ib26HO5lWFoavTwe^]]></nonce_str><notify_url><![CDATA[http://www.treehouses.cn/front-4ae1dacda1f197bbfbc57ec9fbb4911f/api/wxpayresponse]]></notify_url><openid><![CDATA[o8nXct499ctQzioUQt93-vyq9hgM]]></openid>out_trade_no><![CDATA[60065]]></out_trade_no><product_id>0001</product_id><spbill_create_ip><![CDATA[49.140.188.1]]></spbill_create_ip><total_fee>1</total_fee><trade_type>JSAPI</trade_type><sign><![CDATA[F88FABC7BFD25B726864DC6D6FC8313B]]></sign></xml>");
        System.out.println(sbXml);
        String url=ALIPAY_GATEWAY_NEW;
		String payCodeUrl = getCodeUrl(url, sbXml.toString());
		System.out.println(payCodeUrl);
        return payCodeUrl;
    }
    
    /**
     *description:获取扫码支付连接
     *@param url
     *@param xmlParam
     *@return
     * @author ex_yangxiaoyi
     * @see
     */
    @SuppressWarnings("deprecation")
  public static String getCodeUrl(String url,String xmlParam){
  	  DefaultHttpClient client = new DefaultHttpClient();
  	  client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
  	  HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
  	  String code_url = "";
       try {
  		 httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
  		 CloseableHttpResponse response = httpclient.execute(httpost);
  	     String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
  	     System.out.println(jsonStr);
  	    if(jsonStr.indexOf("FAIL")!=-1){
  	    	return code_url;
  	    }
  	    Map map = doXMLParse(jsonStr);
  	    code_url  = (String) map.get("code_url");
  	} catch (Exception e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
  	return code_url;
    }
    /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc =  builder.build(in);
		Element root = (Element) doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	 public static InputStream String2Inputstream(String str) {
			return new ByteArrayInputStream(str.getBytes());
		}
}
