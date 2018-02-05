package com.tmsps.traceback.util.wx;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SignatureException;
import java.util.*;
/**
 * @author 
 */
public class PayUtil 
{
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) 
    {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    
    public static String sign(String text,String input_charset) 
    {
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    /**
     * 签名字符串
     *  @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) 
    {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if (mysign.equals(sign)) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }
    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    public static byte[] getContentBytes(String content, String charset) 
    {
        if (charset == null || "".equals(charset)) 
        {
            return content.getBytes();
        }
        try 
        {
            return content.getBytes(charset);
        } 
        catch (UnsupportedEncodingException e) 
        {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
    /**
     * 生成6位或10位随机数 param codeLength(多少位)
     * @return
     */
    public static String createCode(int codeLength) 
    {
        String code = "";
        for (int i = 0; i < codeLength; i++) 
        {
            code += (int) (Math.random() * 9);
        }
        return code;
    }
    private static boolean isValidChar(char ch) 
    {
        if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
            return true;
        if ((ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f))
            return true;// 简体中文汉字编码
        return false;
    }
    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static SortedMap<String, String> paraFilter(Map<String, String> sArray) 
    {
    	SortedMap<String, String> result = new TreeMap<String, String>();
        if (sArray == null || sArray.size() <= 0) 
        {
            return result;
        }
        for (String key : sArray.keySet()) 
        {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")|| key.equalsIgnoreCase("sign_type")) 
            {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) 
    {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) 
        {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) 
            {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } 
            else 
            {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }
    
    
    public static String createSign(String characterEncoding,SortedMap<String,String> parameters)
    {  
	    StringBuffer sb = new StringBuffer();  
	    Set es = parameters.entrySet();  
	    Iterator it = es.iterator();  
	    while(it.hasNext()) 
	    {  
	        Map.Entry entry = (Map.Entry)it.next();  
	        String k = (String)entry.getKey();  
	        Object v = entry.getValue();  
	        if(null != v && !"".equals(v)  
	                && !"sign".equals(k) && !"key".equals(k)) {  
	            sb.append(k + "=" + v + "&");  
	        }  
	    }  
	    sb.append("key=QIHANGaqixing2008080809qikukejiw");
	   // String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
	    return sb.toString();   
    
    }
    
    
    
    
    
    
    /**
     * 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方法
     * @param outputStr 参数
     */
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr)
    {
        // 创建SSLContext
    	/*StringBuffer buffer=null;
        try
        {
	     URL url = new URL(requestUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod(requestMethod);
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        conn.connect();
	        //往服务器端写内容
	        if(null !=outputStr)
	        {
	            OutputStream os=conn.getOutputStream();
	            os.write(outputStr.getBytes("utf-8"));
	            os.close();
	        }
	        // 读取服务器端返回的内容
	        InputStream is = conn.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is, "utf-8");
	        BufferedReader br = new BufferedReader(isr);
	        buffer = new StringBuffer();
	        String line = null;
	        while ((line = br.readLine()) != null) 
	        {
              buffer.append(line);
	        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return buffer.toString();*/
      
        
        
        
        
      
        
        try 
        {  
            URL url = new URL(requestUrl);  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            conn.setRequestMethod(requestMethod);  
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");  
            // 当outputStr不为null时向输出流写数据  
            if (null != outputStr) 
            {  
                OutputStream outputStream = conn.getOutputStream();  
                // 注意编码格式  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
            // 从输入流读取返回内容  
            InputStream inputStream = conn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            
            StringBuffer buffer = new StringBuffer();  
            
            while ((str = bufferedReader.readLine()) != null) 
            {  
                buffer.append(str);  
            }  
            // 释放资源  
            bufferedReader.close();  
            inputStreamReader.close();  
            inputStream.close();  
            inputStream = null;  
            conn.disconnect();  
            return buffer.toString();  
        } 
        catch (ConnectException ce) 
        {  
            System.out.println("连接超时：{}"+ ce);  
        } 
        catch (Exception e) 
        {  
            System.out.println("https请求异常：{}"+ e);  
        }  
        
        return null;  
        
    }   
    
    
    
    public static String urlEncodeUTF8(String source)
    {
        String result=source;
        try 
        {
            result=java.net.URLEncoder.encode(source, "UTF-8");
        } 
        catch (UnsupportedEncodingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    
}
