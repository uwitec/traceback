package com.tmsps.traceback.util.doc.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import com.tmsps.traceback.util.ChkTools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作Excel表格的功能类
 */
public class ExcelReadTools {
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @return String 表头内容的数组
	 */
	@SuppressWarnings("deprecation")
	public String[] readExcelTitle(InputStream is) {
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValue(row.getCell((short) i));
        }
		return title;
	}
	/**
	 * 读取Excel数据内容 --> List< Map<String,Object>>
	 * 
	 * @return Map 包含单元格数据内容的Map对象
	 */
	@SuppressWarnings("deprecation")
	public List<Map<String,Object>> readExcel2ListMap(InputStream is,String[] classFields) {
		ExcelReadTools excelReader = new ExcelReadTools();
		return excelReader.readExcel2ListMap(is, classFields ,2,0);
	}
	
	/**
	 * 读取Excel数据内容 --> List< Map<String,Object>>
	 * 
	 * @return Map 包含单元格数据内容的Map对象
	 */
	@SuppressWarnings("deprecation")
	public List<Map<String,Object>> readExcel2ListMap(InputStream is,String[] classFields,int rowIndex,int checkColnum) {
		
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		
		row = sheet.getRow(1);
		int colNum = row.getPhysicalNumberOfCells();
		
		if(colNum != classFields.length){
			throw new RuntimeException("excel 列数 和 classFields的长度不一致！！！");
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = rowIndex; i <= rowNum+1; i++) {
			row = sheet.getRow(i);
			if(row==null || ChkTools.isNull(row.getCell((short) checkColnum))){
				continue;
			}
			int j = 0;
			Map<String,Object> map = new HashMap<String, Object>();
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
			
				map.put(classFields[j],getCellFormatValue(row.getCell((short) j)).trim());
				
                j++;
			}
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 读取Excel数据内容
	 * 
	 * @return Map 包含单元格数据内容的Map对象
	 */
	@SuppressWarnings("deprecation")
	public Map<Integer, String> readExcelContent(InputStream is) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();System.err.println(rowNum+"-----------");
		
		row = sheet.getRow(1);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 0; i <= rowNum+1; i++) {
			row = sheet.getRow(i);
			if(row==null){
				content.put(i, "");
				continue;
			}
			int j = 0;			
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				str += getCellFormatValue(row.getCell((short) j)).trim()
						+ "#";


                j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		if (cell == null) {
			return "";
		}
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}

		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	@SuppressWarnings("deprecation")
	public String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC: 
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				}
				// 如果是纯数字
				else {
                    cellvalue = getValueByExcelFunc(cell);
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}

    //读取excel函数的值

    public static String getValueByExcelFunc(HSSFCell cell){

        FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
        evaluator.evaluateFormulaCell(cell);
        CellValue cellValue = evaluator.evaluate(cell);

        String value = "";

        switch (cellValue.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                value = cellValue.getBooleanValue()+"";
                break;
            case Cell.CELL_TYPE_NUMERIC:
                value = cellValue.getNumberValue()+"";
                break;
            case Cell.CELL_TYPE_STRING:
               value = cellValue.getStringValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_ERROR:
                break;

            // CELL_TYPE_FORMULA will never happen
            case Cell.CELL_TYPE_FORMULA:
                break;
            default:
            	break;
        }
        return value;
    }





	public static void main(String[] args) {
		InputStream is = null;
		try {
			// 对读取Excel表格标题测试
			is = new FileInputStream("d:\\info.xls");
			System.err.println("excel 在该工具类下面.");
			ExcelReadTools excelReader = new ExcelReadTools();
			List<Map<String,Object>> list = excelReader.readExcel2ListMap(is, new String[]{"a","b","c","d","e","f","g"});
			for(Map<String,Object> map : list){
				String updatesql="update t_car set times="+map.get("e")+",sum_weight="+map.get("f")+" where carno='"+map.get("a")+"'";
				System.err.println(updatesql);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}