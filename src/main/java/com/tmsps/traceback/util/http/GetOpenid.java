package com.tmsps.traceback.util.http;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;  
  
/**
 * 发起http请求并获取结果  
 * @author wangyp 
 * @date 20161201 
 * 
 */  
public class GetOpenid
{  
    /**  
     * 发起http请求并获取结果  
     *   
     * @param code code
     * @param requestMethod 请求方式（GET、POST）  
     * @param outputStr 提交的数据  
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)  
     */    
    public static JSONObject httpRequest(String code, String requestMethod, String outputStr)
    {    
        JSONObject jsonObject = null;    
        StringBuffer buffer = new StringBuffer();  
        InputStream inputStream=null;
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
        "appid=wx91f53096dd7595a3&secret=18e102c4f80884edaba378a3ef833ee5&js_code="+code+"&grant_type=authorization_code";
        try 
        { 
            URL url = new URL(requestUrl); 
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection(); 
            httpUrlConn.setDoOutput(true); 
            httpUrlConn.setDoInput(true); 
            httpUrlConn.setUseCaches(false); 
            // 设置请求方式（GET/POST） 
            httpUrlConn.setRequestMethod(requestMethod); 
            if ("GET".equalsIgnoreCase(requestMethod))
            {
                httpUrlConn.connect(); 
            } 
    
            // 当有数据需要提交时
            if (null != outputStr) 
            {    
                OutputStream outputStream = httpUrlConn.getOutputStream(); 
                // 注意编码格式，防止中文乱码 
                outputStream.write(outputStr.getBytes("UTF-8")); 
                outputStream.close(); 
            } 
            //将返回的输入流转换成字符串 
            inputStream = httpUrlConn.getInputStream(); 
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8"); 
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 

            String str = null; 
            while ((str = bufferedReader.readLine()) != null) 
            {
                buffer.append(str); 
            } 
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源 
            inputStream.close();
            inputStream = null; 
            httpUrlConn.disconnect(); 
//          jsonObject = JSONObject.fromObject(buffer.toString());
            jsonObject = JSONObject.parseObject(buffer.toString());
        }
        catch (ConnectException ce) 
        {
              ce.printStackTrace();  
              System.out.println("Weixin server connection timed out");  
        } 
        catch (Exception e) 
        { 
               e.printStackTrace();  
               System.out.println("http request error:{}");  
        }
        finally
        {  
            try 
            {  
                if(inputStream!=null)
                {  
                    inputStream.close();  
                }  
            } 
            catch (IOException e) 
            {  
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
        return jsonObject;
    } 



    public static void main (String [] args )
    {
    	System.out.println(httpRequest("001rjiYP1uLRf61t9TZP1K2ZXP1rjiYn","GET",""));
    }


}  
