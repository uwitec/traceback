package com.tmsps.traceback.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpTools {

	// public static CloseableHttpClient client = HttpClients.createDefault();
	public static DefaultHttpClient httpclient = new DefaultHttpClient();

	private static CookieSpecFactory csf = new CookieSpecFactory() {
		public CookieSpec newInstance(HttpParams params) {
			return new BrowserCompatSpec() {
				@Override
				public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {

				}
			};
		}
	};

	public static String get(String url) {
		System.err.println("请求url-->" + url);

		httpclient.getCookieSpecs().register("easy", csf);
		httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
		HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 2000);

		String strGetResponseBody = "";

		HttpGet get = new HttpGet(url);

		try {
			HttpResponse response = httpclient.execute(get);
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

	public static String post(String url, Map<String, Object> map) {
		CloseableHttpClient client = HttpClients.createDefault();
		String strPostResponseBody = "";
		HttpPost post = new HttpPost(url);
		List<NameValuePair> ListParams = new ArrayList<NameValuePair>();
		if (map != null) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				ListParams.add(new BasicNameValuePair(key, map.get(key) + ""));
			}
		}

		try {
			post.setEntity(new UrlEncodedFormEntity(ListParams, "utf-8"));
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

	public static void main(String[] args) {
		String url = "http://oa.epkoko.com";
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "aaa");
		map.put("b", "vbvv");
		System.err.println(get(url));
	}

	public static String readData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder result = new StringBuilder();
			br = request.getReader();
			for (String line=null; (line=br.readLine())!=null;) {
				result.append(line).append("\n");
			}
			
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (br != null)
				try {br.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
}
