package com.tmsps.traceback.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.traceback.service.SettingService;
import com.tmsps.traceback.model.t_fk_file;
import com.tmsps.traceback.util.DateTools;
import com.tmsps.traceback.util.qrcode.QRTools;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.web.SessionTools;

@Service
public class ShopSellService extends BaseService {
	
	@Autowired
	public SettingService settingService;

	public List<Map<String, Object>> selectShopSellList(JSONObject srh, Map<String, String> sort_params, Page page) {
		String sql = "select t.* from t_shop_sell t "
				+ "where t.status=0 and (t.name like ?) and (t.shop_id = ?) ";
		NeParamList param = NeParamList.makeParams();
		param.addLike(srh.getString("name"));
		param.add(SessionTools.getCurrentShopId());
		List<Map<String, Object>> list = bs.findList(sql, param, sort_params, page);
		return list;
	}
	
	public List<Map<String, Object>> selectSellTixoneList(String tixone_code) {
		String sql = "select t.* from t_shop_sell t where t.status=0 and (t.tixone_code = ?) order by barcode ASC";
		NeParamList param = NeParamList.makeParams();
		param.add(tixone_code);
		List<Map<String, Object>> list = bs.findList(sql, param);
		return list;
	}
	
	// 生成一票通二维码
	public String getQRcodeFile(String content) throws IOException{
		int year = DateTools.getYear();
		int month = DateTools.getMonth();
		String folder = year + File.separator + month;
		String folder_url = year + "/" + month;
		
		// 获取保存路径
		String DATA_PATH = settingService.getVal("DATA_PATH");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date d=new Date();
		String str=sdf.format(d);
		String imgPath = DATA_PATH + File.separator + folder + File.separator + str+".png"; 
		File file =QRTools.getQRCodeFile(content, imgPath);

		t_fk_file tf = new t_fk_file();
		// 保存文件
		tf.setFile_name(file.getName());
		tf.setNew_file_name(file.getName());
		tf.setFolder(folder);
		tf.setFolder_url(folder_url);
		tf.setContent_type("image/jpeg");
		tf.setSize(file.length());
		bs.saveObj(tf);
		return tf.getKid();
	}
}
