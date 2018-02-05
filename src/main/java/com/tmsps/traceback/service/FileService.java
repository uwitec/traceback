package com.tmsps.traceback.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.tmsps.ne4spring.orm.param.NeParamList;
import com.tmsps.ne4spring.page.Page;
import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_fk_file;
@Service
public class FileService extends BaseService {

	// 查询所有文件列表
	public List<Map<String,Object>> selectFileList(String query, String qtype,JSONObject srh, Map<String, String> sort_param, Page page){
		String sql = " select * from t_fk_file t where t.status=0 and t.file_name like ? ";
		NeParamList params = NeParamList.makeParams();
		params.addLike(srh.getString("file_name"));
		List<Map<String, Object>> list = bs.findList(sql, params, sort_param, page);
		return list;
	}
	
	// 查询所有视频文件列表
	public List<Map<String,Object>> selectVideoFileList(String query, String qtype,JSONObject srh, Map<String, String> sort_param, Page page){
		String sql = " select * from t_fk_file t where t.status=0 and t.note='视频' and t.file_name like ? ";
		NeParamList params = NeParamList.makeParams();
		params.addLike(srh.getString("file_name"));
		List<Map<String, Object>> list = bs.findList(sql, params, sort_param, page);
		return list;
	}
	
	//根据kid查询文件
	public t_fk_file findFileByKid(String kid){
		String sql = " select * from t_fk_file t where t.status=0 and t.kid=? ";
		t_fk_file file = bs.findObj(sql, new Object[]{kid}, t_fk_file.class);
		return file;
	}
	
	//根据shop_id查询所有文件列表
	public List<Map<String,Object>> selectFileListByShopId(String shopId, JSONObject srh, Map<String, String> sort_param, Page page){
		String sql = " select * from t_fk_file t where t.status=0 and (t.shop_id=?) and (t.file_name like ?) ";
		NeParamList params = NeParamList.makeParams();
		params.add(shopId);
		params.addLike(srh.getString("file_name"));
		List<Map<String, Object>> list = bs.findList(sql, params, sort_param, page);
		return list;
	}
}
