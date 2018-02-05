package com.tmsps.traceback.util.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QRTools {
	
	public static void getQRCode(String content, String imgPath) { 
	       try { 
	           Qrcode qrcodeHandler = new Qrcode(); 
	        // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小 
	           qrcodeHandler.setQrcodeErrorCorrect('M');
	           //以二进制的形式存储内容
	           qrcodeHandler.setQrcodeEncodeMode('B'); 
	           //设置二维码版本共40个  第一版1  21 * 21 第二版2 25*25  每版加2
	           qrcodeHandler.setQrcodeVersion(7); 
//		           int imgSize = 67 + 12 * (size - 1);
	           //字符编码
//	           byte[] contentBytes = content.getBytes("gb2312"); 
	           byte[] contentBytes = new String(content.getBytes("ISO-8859-1"),"UTF-8").getBytes();
	           //创建一个图像数据缓冲区(创建一张纸出来) 以8位图像进行画图
	           BufferedImage bufImg = new BufferedImage(140, 140, 
	                   BufferedImage.TYPE_INT_RGB); 
	           //获取画笔
	           Graphics2D gs = bufImg.createGraphics(); 
	           //设置二维码的背景颜色
	           gs.setBackground(Color.WHITE); 
	           //填充颜色
//	           gs.fillRect(0, 0, 115, 115);
	           gs.clearRect(0, 0, 140, 140); 
	           // 设定二维码前景色-> BLACK 
	           gs.setColor(Color.BLACK); 
	           // 设置偏移量 不设置可能导致解析出错 
	           int pixoff = 2; 
	           // 输出内容-> 二维码 
	           if (contentBytes.length > 0 && contentBytes.length < 800) { 
	               boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes); 
	               for (int i = 0; i < codeOut.length; i++) { 
	                   for (int j = 0; j < codeOut.length; j++) { 
	                       if (codeOut[j][i]) { 
	                    	   //根据布尔数组配置绘制二维码,绘制矩形
	                           gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3); 
	                       } 
	                   } 
	               } 
	           } else { 
	               System.err.println("QRCode content bytes length = " 
	                       + contentBytes.length + " not in [ 0,800 ]. "); 
	           } 
	           gs.dispose(); 
	           bufImg.flush(); 
	           File imgFile = new File(imgPath); 
	           // 生成二维码QRCode图片 
	           ImageIO.write(bufImg, "png", imgFile); 
	       } catch (Exception e) { 
	           e.printStackTrace(); 
	       } 
	   } 
	
	public static File getQRCodeFile(String content, String imgPath) { 
		System.out.println(content);
		File imgFile = null;
		try { 
			Qrcode qrcodeHandler = new Qrcode(); 
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小 
			qrcodeHandler.setQrcodeErrorCorrect('M');
			//以二进制的形式存储内容
			qrcodeHandler.setQrcodeEncodeMode('B'); 
			//设置二维码版本共40个  第一版1  21 * 21 第二版2 25*25  每版加2
			qrcodeHandler.setQrcodeVersion(7); 
//		           int imgSize = 67 + 12 * (size - 1);
			//字符编码
//	           byte[] contentBytes = content.getBytes("gb2312"); 
			byte[] contentBytes = content.getBytes("UTF-8");
			//创建一个图像数据缓冲区(创建一张纸出来) 以8位图像进行画图
			BufferedImage bufImg = new BufferedImage(140, 140, 
					BufferedImage.TYPE_INT_RGB); 
			//获取画笔
			Graphics2D gs = bufImg.createGraphics(); 
			//设置二维码的背景颜色
			gs.setBackground(Color.WHITE); 
			//填充颜色
//	           gs.fillRect(0, 0, 115, 115);
			gs.clearRect(0, 0, 140, 140); 
			// 设定二维码前景色-> BLACK 
			gs.setColor(Color.BLACK); 
			// 设置偏移量 不设置可能导致解析出错 
			int pixoff = 2; 
			// 输出内容-> 二维码 
			if (contentBytes.length > 0 && contentBytes.length < 800) { 
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes); 
				for (int i = 0; i < codeOut.length; i++) { 
					for (int j = 0; j < codeOut.length; j++) { 
						if (codeOut[j][i]) { 
							//根据布尔数组配置绘制二维码,绘制矩形
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3); 
						} 
					} 
				} 
			} else { 
				System.err.println("QRCode content bytes length = " 
						+ contentBytes.length + " not in [ 0,800 ]. "); 
			}
			gs.dispose();
			bufImg.flush();
			
			imgFile = new File(imgPath);
			
			if (!imgFile.getParentFile().exists()) {
				imgFile.getParentFile().mkdirs();
			}
			
			// 生成二维码QRCode图片 
			ImageIO.write(bufImg, "png", imgFile);
			
			return imgFile;
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return imgFile; 
	} 
	   /**
	    * @param args the command line arguments
	    */ 
	   public static void main(String[] args) { 
	   	//取当前时间为图片名称 带毫秒的
	   	   SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMMddHHmmssSSS" );
	   	   Date d=new Date();
		   String str=sdf.format(d);
	       String imgPath = "D:/"+str+".png"; 
	       String content= "http://localhost:8080/traceback/moblie/sell/tixone.htm?tixone_code=109137568727";
	       QRTools.getQRCode(content, imgPath); 
	       System.out.println("imgPath:"+imgPath);
	       System.out.println("encoder QRcode success"); 
	   } 
}
