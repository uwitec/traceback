package com.tmsps.traceback.util.wx;

import java.util.HashMap;
import java.util.Map;

import com.tmsps.ne4Weixin.api.BaseAPI;
import com.tmsps.ne4Weixin.config.WxConfig;
import com.tmsps.ne4Weixin.utils.HttpClient;
import com.tmsps.ne4Weixin.utils.PaymentUtil;
import com.tmsps.ne4Weixin.utils.XmlHelper;

/**
 * 刷卡支付 API
 * 
 * @author 冯晓东
 *
 */
public class PaymentAPIScan extends BaseAPI {
	public PaymentAPIScan(WxConfig config) {
		super(config);
	}

	// 文档地址：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
	private static String WXSCANPAYURL = "https://api.mch.weixin.qq.com/pay/micropay";
	// 查询
	private static String WXSCANSEARCHPAYURL = "https://api.mch.weixin.qq.com/pay/orderquery";
	// 撤销
	private static String WXSCANREVERSEURL = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	/**
	 * 生成订单
	 * 
	 * @return
	 */
	public Map<String, String> scanQrcode(Map<String, String> params) {

		params.put("appid", config.getAppid());
		params.put("mch_id", config.getMch_id());

		params.put("nonce_str", System.currentTimeMillis() + "");
		String sign = PaymentUtil.createSign(params, config.getPayAPI());
		params.put("sign", sign);
		System.err.println(PaymentUtil.toXml(params));

		String body = HttpClient.postXML(WXSCANPAYURL, PaymentUtil.toXml(params));

		return XmlHelper.of(body).toMap();
	}

	public Map<String, String> searchScanQrcode(String trade_no) {
		Map<String, String> params = new HashMap<>();
		params.put("appid", config.getAppid());
		params.put("mch_id", config.getMch_id());
		params.put("out_trade_no", trade_no);
		params.put("nonce_str", System.currentTimeMillis() + "");
		String sign = PaymentUtil.createSign(params, config.getPayAPI());
		params.put("sign", sign);
		System.err.println(PaymentUtil.toXml(params));

		String body = HttpClient.postXML(WXSCANSEARCHPAYURL, PaymentUtil.toXml(params));

		return XmlHelper.of(body).toMap();
	}

	public Map<String, String> reverseScanQrcode(String trade_no) {
		Map<String, String> params = new HashMap<>();
		params.put("appid", config.getAppid());
		params.put("mch_id", config.getMch_id());
		params.put("out_trade_no", trade_no);
		params.put("nonce_str", System.currentTimeMillis() + "");
		String sign = PaymentUtil.createSign(params, config.getPayAPI());
		params.put("sign", sign);
		System.err.println(PaymentUtil.toXml(params));

		String body = HttpClient.postXML(WXSCANREVERSEURL, PaymentUtil.toXml(params));

		return XmlHelper.of(body).toMap();
	}

/*	public static void main(String[] args) {
		WxService s = SpringTools.getBean(WxService.class);
		WxConfig wxConfig = s.getShopWxByDomain("dd.ss.vososo.com");
		System.err.println(JsonTools.toJson(wxConfig));

		PaymentAPIScan api = new PaymentAPIScan(wxConfig);
		Map<String, String> params = new HashMap<>();
		params.put("body", "image");
		params.put("out_trade_no", System.currentTimeMillis() + "");
		params.put("total_fee", "1");
		params.put("spbill_create_ip", "127.0.0.1");
		params.put("auth_code", "130221833949997081");

		Map<String, String> end = api.scanQrcode(params);
		System.err.println(end);

	}*/
}
