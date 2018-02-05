package com.tmsps.traceback.util.ocr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class XunLongOCR { 

  static Logger logger = LoggerFactory.getLogger(XunLongOCR.class);
  
  public static void main(String[] args) throws Exception {
	  String contentType = "{'Content-type':'image/jpeg'}";
	  String url = "https://www.mengxsh.net/hualing/";
	  byte[]  bytes =getImageBinary("C:\\Users\\Administrator\\Desktop\\华羚\\IMG_0296.jpg");
	  System.out.println(xunlongOcr(url, bytes, contentType));
  }
  
  /** 
   * 将图片转换成二进制 
   *  
   * @return 
   */  
  public static byte[] getImageBinary(String imgurl) {
      File f = new File(imgurl);  
      BufferedImage bi;  
      try {  
          bi = ImageIO.read(f);  
          ByteArrayOutputStream baos = new ByteArrayOutputStream();  
          ImageIO.write(bi, "jpg", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真  
          byte[] bytes = baos.toByteArray();  

          return bytes;  
      } catch (IOException e) {  
          e.printStackTrace();  
      }  
      return null;  
  } 

  public static String xunlongOcr(String url, byte[] bytes, String contentType) throws IOException {
	  HttpPost httpPost = new HttpPost(url);
      httpPost.setEntity(new ByteArrayEntity(bytes));
      String result = null;
      
      HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
      CloseableHttpClient m_HttpClient = httpClientBuilder.build();
      if (contentType != null)
          httpPost.setHeader("Content-type", contentType);
      CloseableHttpResponse httpResponse = m_HttpClient.execute(httpPost);
      
      try{
          HttpEntity httpEntity = httpResponse.getEntity();
          result = EntityUtils.toString(httpEntity, "utf-8");
          EntityUtils.consume(httpEntity);
      }finally{
          try{
              if(httpResponse!=null){
                  httpResponse.close();
              }
          }catch(IOException e){
              logger.info("## release resouce error ##" + e);
          }
      }
	return result;
      
/*      try {
          HttpEntity entityResponse = httpResponse.getEntity();
          int contentLength = (int) entityResponse.getContentLength();
          if (contentLength <= 0)
              throw new IOException("No response");
          byte[] respBuffer = new byte[contentLength];
          if (entityResponse.getContent().read(respBuffer) != respBuffer.length)
              throw new IOException("Read response buffer error");
          return respBuffer;
      } finally {
          httpResponse.close();
      }*/
  }
}
			