package com.tmsps.traceback.util.wx.will_del;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.tmsps.ne4Weixin.api.BaseAPI;
import com.tmsps.ne4Weixin.config.WxConfig;
import com.tmsps.ne4Weixin.utils.PaymentUtil;
import com.tmsps.ne4Weixin.utils.XmlHelper;

/**
 * 企业付款 API
 * 
 * @author 冯晓东
 *
 */
public class PaymentAPIRefund extends BaseAPI {
	public PaymentAPIRefund(WxConfig config) {
		super(config);
	}

	// 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
	private static String WXPAYURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	/**
	 * 企业退款
	 * 
	 * @param apiclient_cert_p12_path 证书路径 API安全中心 证书下载
	 * @param params 企业付款参数,不含 mch_appid,mchid,nonce_str,sign四个参数
	 * @return
	 */
	public Map<String, String> payToUser(String apiclient_cert_p12_path, Map<String, String> params) {
		String body = "";
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(apiclient_cert_p12_path));
			try {
				keyStore.load(instream, config.getMch_id().toCharArray());
			} finally {
				instream.close();
			}

			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, config.getMch_id().toCharArray())
					.build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

			params.put("appid", config.getAppid());
			params.put("mch_id", config.getMch_id());

			params.put("nonce_str", System.currentTimeMillis() + "");
			String sign = PaymentUtil.createSign(params, config.getPayAPI());
			params.put("sign", sign);

			HttpPost httppost = new HttpPost(WXPAYURL);
			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000)
					.setSocketTimeout(10000).build();
			httppost.setEntity(new StringEntity(PaymentUtil.toXml(params), ContentType.APPLICATION_XML));
			httppost.setConfig(config);

			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				body = EntityUtils.toString(entity, "UTF-8");
			} catch (IOException e) {
				log.error(e.toString());
				e.printStackTrace();
			}

		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}

		return XmlHelper.of(body).toMap();
	}
}
