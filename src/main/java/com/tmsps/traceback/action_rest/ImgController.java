package com.tmsps.traceback.action_rest;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.model.t_fk_file;
import com.tmsps.traceback.service.SettingService;
import com.tmsps.traceback.util.ChkTools;
import com.tmsps.traceback.util.img.ImgTools;

@Controller
@Scope("prototype")
public class ImgController extends ProjBaseAction {

	@Autowired
	public SettingService settingService;

	@RequestMapping("/download/{file_id}")
	public void download(@PathVariable String file_id, String mw, String mh) throws Exception {

		t_fk_file file = (t_fk_file) bs.findById(file_id, t_fk_file.class);
		String DATA_PATH = settingService.getVal("DATA_PATH");
		ServletOutputStream os = resp.getOutputStream();
		FileInputStream fis = null;
		try {

			File img = null;
			if (file != null) {
				img = new File(DATA_PATH + File.separator + file.getFolder(), file.getNew_file_name());
			}
			if (img == null || !img.exists()) {
				img = new File(req.getSession().getServletContext().getRealPath("") + File.separator + "resource"
						+ File.separator + "img", "file404.jpg");
			}

			// 设置页面不缓存
			resp.setHeader("Pragma", "No-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
			resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(file.getFile_name(), "UTF-8"));

			fis = new FileInputStream(img);
			byte[] b = new byte[10240];
			int len = -1;
			while ((len = fis.read(b)) != -1) {
				os.write(b, 0, len);
			}

		} catch (Exception e) {
			System.err.println("文件找不到:" + e);
		} finally {
			os.close();
			if (fis != null) {
				fis.close();
			}
		}
	}

	@RequestMapping("/img/{file_id}")
	public void downloadys(@PathVariable String file_id, String mw, String mh) throws Exception {

		t_fk_file file = (t_fk_file) bs.findById(file_id, t_fk_file.class);
		ServletOutputStream os = resp.getOutputStream();
		// 获取保存路径
		String DATA_PATH = settingService.getVal("DATA_PATH");
		try {
			File img = null;
			if (file != null) {
				img = new File(
						DATA_PATH + File.separator + file.getFolder() + File.separator + file.getNew_file_name());
			}
			if (img == null || !img.exists()) {
				img = new File(req.getSession().getServletContext().getRealPath("") + File.separator + "resource"
						+ File.separator + "img", "file404.jpg");
			}

			int height = ChkTools.getInteger(mh);
			int width = ChkTools.getInteger(mw);
			height = height < 0 ? 0 : height;
			width = width < 0 ? 0 : width;

			// 设置页面不缓存
			resp.setHeader("Pragma", "No-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);

			ImgTools.thumbnail_w_h(img, width, height, os);

		} catch (Exception e) {
			System.err.println("图片找不到:" + e);
		} finally {
			os.close();
		}
	}

	@RequestMapping("/download")
	public void download(String fileName) throws Exception {

		ServletOutputStream os = resp.getOutputStream();
		FileInputStream fis = null;
		try {
			String DATA_PATH = settingService.getVal("DATA_PATH");
			File file = new File(DATA_PATH + File.separator + fileName);

			// 设置页面不缓存
			resp.setHeader("Pragma", "No-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
			resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			fis = new FileInputStream(file);
			byte[] b = new byte[10240];
			int len = -1;
			while ((len = fis.read(b)) != -1) {
				os.write(b, 0, len);
			}

		} catch (Exception e) {
			System.err.println("文件找不到:" + e);
		} finally {
			os.close();
			if (fis != null) {
				fis.close();
			}
		}
	}

}
