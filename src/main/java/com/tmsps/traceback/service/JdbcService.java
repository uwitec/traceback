package com.tmsps.traceback.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.util.jdbc.JdbcUtils;

@Service
public class JdbcService extends BaseService {
	/* 
	 * 金开来 1070330
	 * 雪顿 5414
	 * 爱里 14784
	 */

	public List<Map<String, Object>> getProduceinfo() {
        JdbcUtils jdbcUtil = new JdbcUtils();  
        jdbcUtil.getConnection();   
        String sql = "select * from produceinfo";
        
        List<Map<String, Object>> result = null;
        try {
            result = jdbcUtil.findResult(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
		return result;
	}
	
	public List<Map<String, Object>> getProducefood() {
        JdbcUtils jdbcUtil = new JdbcUtils();  
        jdbcUtil.getConnection();   
        String sql = "select * from producefood where userid = '5414' and regdate > '2018-01-31' ";
        
        List<Map<String, Object>> result = null;
        try {
            result = jdbcUtil.findResult(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
		return result;
	}
	
	public List<Map<String, Object>> getSalefood() {
        JdbcUtils jdbcUtil = new JdbcUtils();  
        jdbcUtil.getConnection();   
        String sql = "select * from salefood where userid = '5414' and createtime > '2018-02-02 16:59:09' ";
        
        List<Map<String, Object>> result = null;
        try {
            result = jdbcUtil.findResult(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
		return result;
	}
}
