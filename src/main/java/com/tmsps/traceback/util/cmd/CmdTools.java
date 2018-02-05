package com.tmsps.traceback.util.cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CmdTools {
	
	public static String cmd(String cmd){
		BufferedReader br = null;
		StringBuilder sb = null;
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			br = new BufferedReader(new InputStreamReader(p.getErrorStream(),"gbk"));
			String line = null;
			sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line+"\n");
			} 
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	} 
	public static void main(String[] args) {
		String str = cmd("java -version");
		
	}
}
