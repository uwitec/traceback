package com.tmsps.traceback.util.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * 去除多余sql
 * 
 * @author 冯晓东
 *
 */
public class SybaseDbHandleTools {

	public static void main(String[] args) throws FileNotFoundException {
		String sqlFile = "F:\\a\\seal.sql";
		File file = new File(sqlFile);
		String sqlDdl = readSql(file);

		sqlDdl = sqlDdl.replace("sealdb.dbo.", "");
		sqlDdl = sqlDdl.replaceAll("[,]\\s*[)]", "\r\n)");
		sqlDdl = sqlDdl.replaceAll("[;]\\s*[;]", "");
		sqlDdl = sqlDdl.replaceFirst(";", "");
		sqlDdl += ";";
		sqlDdl = sqlDdl.replaceAll("[)]\\s*[;]", ");");
		sqlDdl = sqlDdl.replaceAll("[\n]\\s*[\n]", "");

		System.err.println(sqlDdl);

		// 写入sql文件
		String sqlBakFile = "seal_bak.sql";
		write(new File(file.getParent(), sqlBakFile), sqlDdl);

	}

	public static void write(File f, String sqlDdl) {
		try {
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(sqlDdl.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean isStartWith(String line) {
		String[] split = { "print", "use", "CONSTRAINT", "exec", "checkpoint",
				"SETUSER", "create database", "alter database",
				"lock allpages", "sp_addthreshold", "--" };

		for (String s : split) {
			if (line.startsWith(s) || line.toUpperCase().startsWith(s)
					|| line.toLowerCase().startsWith(s)) {
				return true;
			}
		}// #for

		return false;
	}

	public static String readSql(File f) {
		String sqlDdl = "";

		try {
			FileInputStream fis = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (isStartWith(line)) {
					continue;
				}
				if (line.contains("on 'default'")) {
					continue;
				}
				if (line.startsWith("go")) {
					line = ";";
				}

				sqlDdl += line + "\r\n";
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return sqlDdl;
	}
}
