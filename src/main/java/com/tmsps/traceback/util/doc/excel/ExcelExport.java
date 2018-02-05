package com.tmsps.traceback.util.doc.excel;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

//@Service
public class ExcelExport<T> {
	// 格式化日期
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * @param title
	 *            标题
	 * @param dataset
	 *            集合
	 * @param out
	 *            输出流
	 */
	@Deprecated
	public void exportExcel(String tit, String title, String[] cell_heads,List<Map<String, Object>> dataset,
			OutputStream out) {
		Object[] keysObj =  dataset.get(0).keySet().toArray();
		String[] keys = new String[keysObj.length];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = keysObj[i]+"";
		}
		exportExcel(tit, title, cell_heads, keys, dataset, out);
	}
	
	public void exportExcel(String tit, String title, String[] cell_heads,String[] keys, List<Map<String, Object>> dataset,
			OutputStream out) {
		
		// 声明一个工作薄
		try {
			// 首先检查数据看是否是正确的
			// 取得实际泛型类
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth(15);
			// 生成一个样式
			HSSFCellStyle style = workbook.createCellStyle();
			// 设置标题样式
//			style = ExcelStyle.setHeadStyle(workbook, style);
            HSSFFont f  = workbook.createFont();
            f.setFontHeightInPoints((short) 15);// 字号
            f.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗
            style.setFont(f);
            // 得到所有字段
			
			// 标题
			List<String> exportfieldtile = new ArrayList<String>();
            for(String cell_head : cell_heads){
                exportfieldtile.add(cell_head);
            }

			// 导出的字段的get方法
			// 遍历整个filed
			// 产生表格标题行
            HSSFRow pre_tit = sheet.createRow(0);
            HSSFCell pre_cell = pre_tit.createCell(0);
            pre_cell.setCellStyle(style);
            HSSFRichTextString pre_text = new HSSFRichTextString(tit);
            pre_cell.setCellValue(pre_text);
//style
            style = workbook.createCellStyle();

			HSSFRow row = sheet.createRow(1);
			
			for (int i = 1; i < exportfieldtile.size()+1; i++) {
				
				HSSFCell cell = row.createCell(i-1);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(
						exportfieldtile.get(i-1));
				cell.setCellValue(text);
			}
			
			// 循环整个集合
			
			// 从第二行开始写，第一行是标题
			for (int i = 2; i <= dataset.size()+1; i++) {
				row = sheet.createRow(i);
				
				Map<String, Object> data = dataset.get(i - 2);
				
//				Set<String> keys = data.keySet();
                int k = 0;
				for (String key : keys) {
                    String val = data.get(key)+"";
                    HSSFCell cell = row.createCell(k);
					String textValue = getValue(val);
                    if("null".equals(textValue))
                        textValue = "";
					cell.setCellValue(textValue);
					k++;

				}
			}
			
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

//导出模板
	public void exportExcelL(String tit, String title, String[] cell_heads, OutputStream out) {
		
		// 声明一个工作薄
		try {
			// 首先检查数据看是否是正确的
			// 取得实际泛型类
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth(15);
			// 生成一个样式
			HSSFCellStyle style = workbook.createCellStyle();
			// 设置标题样式
			// style = ExcelStyle.setHeadStyle(workbook, style);
            HSSFFont f  = workbook.createFont();
            f.setFontHeightInPoints((short) 15);// 字号
            f.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗
            style.setFont(f);
			// 得到所有字段
			// 标题
			List<String> exportfieldtile = new ArrayList<String>();
            for(String cell_head : cell_heads){
                exportfieldtile.add(cell_head);
            }

			// 导出的字段的get方法
			// 遍历整个filed
			// 产生表格标题行

            HSSFRow pre_tit = sheet.createRow(0);
            HSSFCell pre_cell = pre_tit.createCell(0);
            pre_cell.setCellStyle(style);
            HSSFRichTextString pre_text = new HSSFRichTextString(tit);
            pre_cell.setCellValue(pre_text);

            style = workbook.createCellStyle();

			HSSFRow row = sheet.createRow(1);
			
			for (int i = 1; i < exportfieldtile.size()+1; i++) {
				
				HSSFCell cell = row.createCell(i-1);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(
						exportfieldtile.get(i-1));
				cell.setCellValue(text);
			}
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String getValue(Object value) {
		String textValue = "";
		if (value == null)
			return textValue;

		if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			textValue = "是";
			if (!bValue) {
				textValue = "否";
			}
		} else if (value instanceof Date) {
			Date date = (Date) value;

			textValue = sdf.format(date);
		} else
			textValue = value.toString();

		return textValue;
	}

	/**
	 *  @param title
	 *            标题
	 * @param dataset
	 *            集合
	 * @param out
	 *            输出流
	 * @param cols
	 * 			指标的list集合，用于指标名称和 t_variety_report的 对应关系
	 */
	public void exportExcel(String tit, String title, String[] cell_heads, List<Map<String, Object>> dataset,
			OutputStream out,List<Map<String, Object>> cols) {
		
		// 声明一个工作薄
		try {
			// 首先检查数据看是否是正确的
			// 取得实际泛型类
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth(15);
			// 生成一个样式
			HSSFCellStyle style = workbook.createCellStyle();
			// 设置标题样式
//			style = ExcelStyle.setHeadStyle(workbook, style);
            HSSFFont f  = workbook.createFont();
            f.setFontHeightInPoints((short) 15);// 字号
            f.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗
            style.setFont(f);
            // 得到所有字段
			
			// 标题
			List<String> exportfieldtile = new ArrayList<String>();
            for(String cell_head : cell_heads){
                exportfieldtile.add(cell_head);
            }

			// 导出的字段的get方法
			// 遍历整个filed
			// 产生表格标题行
            HSSFRow pre_tit = sheet.createRow(0);
            HSSFCell pre_cell = pre_tit.createCell(0);
            pre_cell.setCellStyle(style);
            HSSFRichTextString pre_text = new HSSFRichTextString(tit);
            pre_cell.setCellValue(pre_text);
//style
            style = workbook.createCellStyle();

			HSSFRow row = sheet.createRow(1);
			
			for (int i = 1; i < exportfieldtile.size()+1; i++) {
				
				HSSFCell cell = row.createCell(i-1);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(
						exportfieldtile.get(i-1));
				cell.setCellValue(text);
			}
			
			// 循环整个集合
			
			// 从第二行开始写，第一行是标题
			for (int i = 2; i <= dataset.size()+1; i++) {
				
				row = sheet.createRow(i);
				Map<String, Object> data = dataset.get(i - 2);
				System.err.println(data);
                int k = 0;
				HSSFCell cell = row.createCell(k);
				String textValue = getValue( data.get("variety_name"));
				cell.setCellValue(textValue);
				k++;
				 cell = row.createCell(k);
				 textValue = getValue( data.get("variety_no"));
				 cell.setCellValue(textValue);
				k++;
				 cell = row.createCell(k);
				 textValue = getValue( data.get("variety_date"));
				 cell.setCellValue(textValue);
				k++;
				 cell = row.createCell(k);
				 textValue = getValue( data.get("variety_time"));
				 cell.setCellValue(textValue);
				k++;
				 cell = row.createCell(k);
				 textValue = getValue( data.get("work_time"));
				 cell.setCellValue(textValue);
				k++;
				 cell = row.createCell(k);
				 textValue = getValue( data.get("work_loca"));
				 cell.setCellValue(textValue);
				k++;
						 
				for (Map<String, Object> map1 : cols) {
					String key = "v" + map1.get("map_table_column");
					HSSFCell cell1 = row.createCell(k);
					 textValue = getValue( data.get(key)+""); 
					 cell1.setCellValue(textValue);
					 k++;
				}		
			}
			
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// ExcelExport<t_student> ts = new ExcelExport<t_student>();
		// StudentService s = SpringTools.getBean("studentService");
		// List<Map<String, Object>> list = s.selectStudentList(
		// "3xz6jUNScxLyyfC9cQYDmk", "");
		// System.err.println(list);
		// OutputStream os = new FileOutputStream("f:\\text.xls");
		// ts.exportExcel("text.xls", list, os);
	}

}
