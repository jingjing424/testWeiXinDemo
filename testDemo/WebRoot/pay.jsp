<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>中汇宝共享合作社-支付</title>
		<link rel="icon" href="../../img/logo.png" type="image/x-icon" />
		<link href="../../css/pay/pay.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="../../css/load/namp.css">
		<script src="../../js/wechartpay/qrcode.js"></script>
		<script src="../../js/common/jquery-2.1.0.min.js"></script>
		<script type="text/javascript" src="../../js/common/common-base.js"></script>

	</head>

<body>
	<!-- 	<div class="content">
			<div class="main">
				<div class="id">
					<img class="shang_logo" src="../../img/pay/logo1.png" alt="中汇宝" />
	

					<div class="shang_payimg">
						<img src="../../img/pay/weipayimg.jpg" alt="扫码支持" title="扫一扫" />
					</div>
					<div class="pay_explain">
						扫码打赏，你说多少就多少
					</div>
					<div class="shang_payselect">
						<div class="pay_item checked" data-id="weipay">
							<span style="margin-left: 61px;" class="radiobox"></span>
							<span class="pay_logo"><img src="../../img/pay/wechat.jpg"
									alt="微信" /> </span>
						</div>
				</div>
					<div class="shang_info">
						<p>
							打开
							<span id="shang_pay_txt">支付宝</span>扫一扫，即可进行加入我们哦
						</p>
						<p>
							Powered by
							<a href="http://www.zhonghuibao.com" target="_blank" title="中汇宝">中汇互助</a>，分享从这里开始，精彩与您同在
						</p>
					</div>
				</div>
			</div>-->
			
			
			
			
<div class="content">
    <div class="main">
    <div class="id">
        <img class="shang_logo" src="../../img/pay/logo1.png" alt="中汇宝" />
    	<!--<div class="shang_tit">
    		<p>感谢您的支持，我会继续努力的!</p>
    	</div>-->
    	<div class="shang_payimg">
    		<img src="/img/pay/alipayimg.jpg" alt="扫码支持" title="扫一扫" />
    	</div>
    		<div class="pay_explain">扫码打赏，你说多少就多少</div>
    	<div class="shang_payselect">
    		<div class="pay_item checked" data-id="alipay">
	    		<span class="radiobox"></span>
	    		<span class="pay_logo"><img src="../../img/pay/alipay.jpg" alt="支付宝" /></span>
    		</div>
    		<div class="pay_item" data-id="weipay">
	    		<span class="radiobox"></span>
	    		<span class="pay_logo"><img src="../../img/pay/wechat.jpg" alt="微信" /></span>
    		</div>
    	</div>
    	<div class="shang_info">
    		<p>打开<span id="shang_pay_txt">支付宝</span>扫一扫，即可进行加入我们哦</p>
    		<p>Powered by <a href="http://www.zhonghuibao.com" target="_blank" title="中汇宝">中汇互助</a>，分享从这里开始，精彩与您同在</p>
    	</div>
    </div>
    </div>
			<div class="pay">
				<!--   <input type="button"   class="pay1" value="支付成功"  onclick="order();"/> -->
				
				
			</div>
		</div>

    	<div class="shang_info">
    		<p >
					&nbsp;&nbsp;&nbsp;联系电话：010-56532798-1102
					&nbsp;&nbsp;&nbsp;公司邮箱：wangyiqiang@zhonghuibao.com
				&nbsp;&nbsp;&nbsp;公司地址：北京市经济技术开发区经海三路109号天骥智谷46号楼</p>
				<div class="right-font">
					<p class="foot-font">
						Copyright © 2013 - 2017 中汇宝网络科技股份有限公司（www.zhonghuibao.com）
					</p>
    	</div>
    </div>
    <script type="text/javascript">
    $(function(){
    	$(".pay_item").click(function(){
    		$(this).addClass('checked').siblings('.pay_item').removeClass('checked');
    		var dataid=$(this).attr('data-id');
    		$(".shang_payimg img").attr("src","../../img/pay/"+dataid+"img.jpg");
    		$("#shang_pay_txt").text(dataid=="weipay"?"微信":"支付宝");
    	});
    });  
    </script>
<script type="text/javascript">
$(function(){
	 setInterval(judgeStatus,5000);
	 function judgeStatus(){
		  $.ajax( {
			url : getBasePath() + "/pay/weixin/selectAlipayPayAction.action",
			type : "post",
			dataType : 'json',
			success : function(data) {
				if(data.return_code=="SUCCESS"){
					alert("SUCCESS");
				}
			},
			error : function(XMLResponse) {
				document.getElementById('countusers').innerHTML = 0;
			}
		});
	 }
})
</script>
  </body>

</html>
