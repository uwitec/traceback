package com.tmsps.traceback.util.http;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.tmsps.traceback.util.json.JsonTools;


public class HttpJsonTools {

	public static String get(String url) {
		CloseableHttpClient client = HttpClients.createDefault();

		System.err.println("请求url-->" + url);
		String strGetResponseBody = "";

		HttpGet get = new HttpGet(url);
		get.setHeader("Content-Type", "application/json;charset=utf-8");

		try {
			HttpResponse response = client.execute(get);
			System.err.println("状态-->" + response.getStatusLine());

			HttpEntity entity = response.getEntity();
			if (null != entity) {
				strGetResponseBody = EntityUtils.toString(entity);

				EntityUtils.consume(entity);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// try {
			// client.close();
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}

		return strGetResponseBody;

	}

	public static String post(String url, Map<?, ?> paramsMap) {
		return post(url, JsonTools.toJson(paramsMap));
	}

	public static String post(String url, String params) {
		System.err.println(url);
		CloseableHttpClient client = HttpClients.createDefault();

		String strPostResponseBody = "";
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type", "application/json;charset=utf-8");

		try {
			StringEntity se = new StringEntity(params, "UTF-8");
			post.setEntity(se);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				strPostResponseBody = EntityUtils.toString(entity);

				EntityUtils.consume(entity);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// try {
			// client.close();
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}

		return strPostResponseBody;

	}
	
	
	public static String getVideo(String url) {
		HttpClient client = HttpClients.createDefault();
//		String url3 = "http://www.hik-online.com/kakaxi12345";
		String url3 = "";
		System.err.println("请求url-->" + url);
		String strGetResponseBody = "";
		HttpGet get = new HttpGet(url);						
		String ip = "";
		try {
			HttpResponse response = client.execute(get);
			System.err.println("状态-->" + response.getStatusLine());
			String port = "";
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				strGetResponseBody = EntityUtils.toString(entity);
				String ipStr = strGetResponseBody.substring(strGetResponseBody.indexOf("var url=")+9, strGetResponseBody.indexOf("var url=")+40).trim();
				ip = ipStr.substring(0, ipStr.lastIndexOf("\'"));
				port = ip.substring(ip.lastIndexOf(":"));
				System.err.println("ip:"+ip);
				System.err.println("port:"+port);
				EntityUtils.consume(entity);
			}
			String url2 = ip +"/PSIA/Custom/SelfExt/userCheck";
			HttpGet get2 = new HttpGet(url2);
			String admin = "admin:12345";
			get2.setHeader("If-Modified-Since", "0");
			byte[] encode = Base64.getEncoder().encode(admin.getBytes());
			String e = "";
			try {
				e = new String(encode,"utf-8");
				System.err.println(e);
				get2.setHeader("Authorization", "Basic " + e);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			HttpResponse response2 = client.execute(get2);
			System.err.println("状态2-->" + response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			if (null != entity2) {
				strGetResponseBody = EntityUtils.toString(entity2);
				EntityUtils.consume(entity2);
				System.err.println(strGetResponseBody);
			}
			
//			HttpGet get3 = new HttpGet(url3);
//			get3.setHeader("Cookie","userInfo"+port+"="+e);
//			HttpResponse response3 = client.execute(get3);
//			System.err.println("状态3-->" + response3.getStatusLine());
//			HttpEntity entity3 = response3.getEntity();
//			if (null != entity3) {
//				strGetResponseBody = EntityUtils.toString(entity3);
//				EntityUtils.consume(entity3);
//				System.err.println(strGetResponseBody);
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// try {
			// client.close();
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}

		return ip;
	}

	public static void main(String[] args) {
		String url = "http://www.hik-online.com/kakaxi12346";
		
		System.err.println(getVideo(url));
	}
	
}
