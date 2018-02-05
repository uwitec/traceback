package com.tmsps.traceback.util.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpXmlTools {

	public static String postXml(String url, String xml) {
		String body = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(10000).build();
		CloseableHttpResponse response = null;
		post.setEntity(new StringEntity(xml, ContentType.APPLICATION_XML));
		post.setConfig(config);
		try {
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			body = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}

}
