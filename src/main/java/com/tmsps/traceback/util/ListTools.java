package com.tmsps.traceback.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTools {

	/**
	 *  对list 集合进行分组
	 * @param list
	 * @param groupFeild
	 * @return
	 */
	public static Map<String,List<Map<String,Object>>> groupByList(List<Map<String,Object>> list,String groupFeild){
		Map<String,List<Map<String,Object>>> result = new HashMap<String, List<Map<String,Object>>>();
		for(Map<String,Object> map : list){
			if(result.containsKey(map.get(groupFeild).toString())){
				result.get(map.get(groupFeild).toString()).add(map);
			}else{
				if(ChkTools.isNull(map.get(groupFeild))){
					throw new RuntimeException(groupFeild+"-->值不能为空");
				}else{
					List<Map<String,Object>> tmp = new ArrayList<Map<String,Object>>();
					tmp.add(map);
					result.put(map.get(groupFeild).toString(),tmp );
				}
				
			}
		}
		return result;
	}
}
