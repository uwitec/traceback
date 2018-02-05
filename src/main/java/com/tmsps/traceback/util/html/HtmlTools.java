package com.tmsps.traceback.util.html;

import java.util.regex.Pattern;

import com.tmsps.traceback.util.ChkTools;

public class HtmlTools {

	public static String html2Text(String inputString) {
		if (ChkTools.isNull(inputString)) {
			return inputString;
		}
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
			String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(" "); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(" "); // 过滤html标签

			textStr = htmlStr.replaceAll(" ", "");

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	public static boolean is_html(String inputString) {
		if (ChkTools.isNull(inputString)) {
			return false;
		}
		String htmlStr = inputString; // 含html标签的字符串
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
			String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			if (m_script.find()) {
				return true;
			}
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			if (m_style.find()) {
				return true;
			}
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			if (m_html.find()) {
				return true;
			}
			htmlStr = m_html.replaceAll(" "); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			if (m_html1.find()) {
				return true;
			}
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return false;
	}

	public static void main(String[] args) {
		StringBuffer htmlStr = new StringBuffer();
		htmlStr.append(
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>")
				.append("<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en'><head><title>aaa</title>xcv<mce:script type='text/javascript'></mce:script>")
				.append("<link href='static_files/help.css' mce_href='static_files/help.css' rel='stylesheet' type='text/css' media='all' />")
				.append("</head><body><script></script><ul><li>XXXX</li></ul><p>ppp</p><img src='d:\tt.jpg'></img></body></html>");

		System.out.println(htmlStr.toString());
		System.out.println(html2Text(htmlStr.toString()));
	}
}