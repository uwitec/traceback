package com.tmsps.traceback.action_rest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tmsps.traceback.base.action.ProjBaseAction;
import com.tmsps.traceback.model.t_fk_file;
import com.tmsps.traceback.service.SettingService;
import com.tmsps.traceback.util.DateTools;
import com.tmsps.traceback.util.file.FileTools;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.util.ocr.XunLongOCR;

@Controller
@Scope("prototype")
public class UploadController extends ProjBaseAction {

	@Autowired
	public SettingService settingService;

	@RequestMapping("/upload_form")
	public ModelAndView list(String area_parent_id) {
		ModelAndView mv = new ModelAndView("/jsp_cp/file/list");
		return mv;
	}

	@RequestMapping("/upload")
	public void upload(@RequestParam MultipartFile file) throws IllegalStateException, IOException {
		resp.setCharacterEncoding("utf-8");
		String suffix = FileTools.getSuffix(file.getOriginalFilename());
		String filename = System.currentTimeMillis() + "." + suffix;

		int year = DateTools.getYear();
		int month = DateTools.getMonth();
		String folder = year + File.separator + month;
		String folder_url = year + "/" + month;

		// 保存文件
		t_fk_file tf = new t_fk_file();
		tf.setFile_name(file.getOriginalFilename());
		tf.setNew_file_name(filename);
		tf.setFolder(folder);
		tf.setFolder_url(folder_url);
		tf.setContent_type(file.getContentType());
		tf.setSize(file.getSize());
		// 获取保存路径
		String DATA_PATH = settingService.getVal("DATA_PATH");
		
		System.out.println(DATA_PATH + File.separator + folder + File.separator + tf.getNew_file_name());
		File newFile = new File(DATA_PATH + File.separator + folder + File.separator + tf.getNew_file_name());
		if (!newFile.getParentFile().exists()) {
			newFile.getParentFile().mkdirs();
		}
		file.transferTo(newFile);
		bs.saveObj(tf);

		Map<String, String> end = new HashMap<String, String>();

		// 发送保存结果
		end.put("file_id", tf.getKid());
		end.put("file_name", tf.getFile_name());
		end.put("content_type", tf.getContent_type());

		resp.getWriter().print(JsonTools.toJson(end));
		resp.getWriter().flush();
	}
	
	/**
	 * @param file
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/video_upload")
	public void video_upload(@RequestParam MultipartFile file) throws IllegalStateException, IOException {
		resp.setCharacterEncoding("utf-8");
		String suffix = FileTools.getSuffix(file.getOriginalFilename());
		String filename = System.currentTimeMillis() + "." + suffix;

		int year = DateTools.getYear();
		int month = DateTools.getMonth();
		String folder = year + File.separator + month;
		String folder_url = year + "/" + month;

		// 保存文件
		t_fk_file tf = new t_fk_file();
		tf.setFile_name(file.getOriginalFilename());
		tf.setNew_file_name(filename);
		tf.setFolder(folder);
		tf.setFolder_url(folder_url);
		tf.setContent_type(file.getContentType());
		tf.setSize(file.getSize());
		tf.setNote("视频");
		// 获取保存路径

		File newFile = new File("/usr/java/tomcat-8.5.8/webapps/ROOT/video" + File.separator + folder + File.separator + tf.getNew_file_name());
		if (!newFile.getParentFile().exists()) {
			newFile.getParentFile().mkdirs();
		}
		file.transferTo(newFile); 
		bs.saveObj(tf);

		Map<String, String> end = new HashMap<String, String>();

		// 发送保存结果
		end.put("file_id", tf.getKid());
		end.put("file_name", tf.getFile_name());
		end.put("content_type", tf.getContent_type());

		resp.getWriter().print(JsonTools.toJson(end));
		resp.getWriter().flush();
	}

	@RequestMapping("/wx_orc")
	@ResponseBody
	public String wx_orc(@RequestParam MultipartFile file) throws IllegalStateException, IOException{
		resp.setCharacterEncoding("utf-8");
		String suffix = FileTools.getSuffix(file.getOriginalFilename());
		String filename = System.currentTimeMillis() + "." + suffix;

		int year = DateTools.getYear();
		int month = DateTools.getMonth();
		String folder = year + File.separator + month;
		String folder_url = year + "/" + month;

		// 保存文件
		t_fk_file tf = new t_fk_file();
		tf.setFile_name(file.getOriginalFilename());
		tf.setNew_file_name(filename);
		tf.setFolder(folder);
		tf.setFolder_url(folder_url);
		tf.setContent_type(file.getContentType());
		tf.setSize(file.getSize());
		// 获取保存路径
		String DATA_PATH = settingService.getVal("DATA_PATH");

		File newFile = new File(DATA_PATH + File.separator + folder + File.separator + tf.getNew_file_name());
		if (!newFile.getParentFile().exists()) {
			newFile.getParentFile().mkdirs();
		}
		file.transferTo(newFile);
		bs.saveObj(tf);
		
		System.out.println(File.separator + folder + File.separator + tf.getNew_file_name());
		byte[] bytes = XunLongOCR.getImageBinary(DATA_PATH + File.separator + folder + File.separator + tf.getNew_file_name());
		String result = XunLongOCR.xunlongOcr("https://www.mengxsh.net/hualing/", bytes, "{'Content-type':'image/jpeg'}");
		System.out.println(result);
		
		Map<String, Object> end = JsonTools.jsonStrToJsonObject(result);
		return end.get("code")+","+end.get("result");
	}
}
