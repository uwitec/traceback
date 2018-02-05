package com.tmsps.traceback.util.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * 生成 drop sql
 * 
 * @author 冯晓东
 *
 */
public class SybaseDbDelHandleTools {

	public static void main(String[] args) throws FileNotFoundException {
		String sqlFile = "F:\\a\\seal_bak.sql";
		File file = new File(sqlFile);
		String sqlDdl = readSql(file);

		System.err.println(sqlDdl);

		// 写入sql文件
		String sqlBakFile = "seal_drop.sql";
		SybaseDbHandleTools.write(new File(file.getParent(), sqlBakFile),
				sqlDdl);

	}

	public static String readSql(File f) {
		String sqlDdl = "";

		try {
			FileInputStream fis = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("CREATE TABLE")) {
					continue;
				}
				line = line.replace("CREATE", "DROP").replace("(", ";");
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
