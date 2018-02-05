package com.tmsps.traceback.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tmsps.ne4Weixin.config.WxConfig;

public class WxConfigTools {

	private static Logger log = LoggerFactory.getLogger(WxConfigTools.class);
	private static Cache<String, WxConfig> cache = CacheBuilder.newBuilder().build();

	private WxConfigTools() {
	}

	public static WxConfig getWxConfig() {
		WxConfig wc = null;
		try {
			wc = cache.get("default", new Callable<WxConfig>() {
				@Override
				public WxConfig call() throws Exception {
					log.warn("not find cache and new it");

					String appid = "wxe8e326172f65c25b";
					String secret = "39b99f14671528dc9edff7a3ce7b8428";
					String encodingaeskey = "T8U6H86FUvwzpYYOcXH3W4aZvPEZnLCydGe2tukA59g";
					String token = "XuToOlCtitxRu4Tcxf4Fu9xX4OCT1cqD";
					String mch_id = "1444030802";
					String payAPI = "T8U6H86FUvwzpYYOcXH3W4aZvPEZnLCydGe2tukA59g";

					WxConfig wxConfig = new WxConfig(appid, secret, encodingaeskey, true, token, mch_id, payAPI, 3600);

					return wxConfig;
				}
			});
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return wc;
	}

}
