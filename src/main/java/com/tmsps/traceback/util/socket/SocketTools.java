package com.tmsps.traceback.util.socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.tmsps.traceback.util.ChkTools;

public class SocketTools {

	public static String send(String ip, int port, String data) {
		if (ChkTools.isNull(data)) {
			return null;
		}
		String result = null;
		Socket cSocket = null;
		try {
			cSocket = new Socket(ip.trim(), port);
			OutputStream os = cSocket.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(
					cSocket.getInputStream());

			os.write(data.getBytes());
			os.flush();
			byte[] b = new byte[1024];
			int len = -1;
			result = "";
			while ((len = bis.read(b, 0, b.length)) != -1) {
				result += new String(b, 0, len);
			}
			os.close();
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		} finally {
			try {
				if (cSocket != null) {
					cSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

}
