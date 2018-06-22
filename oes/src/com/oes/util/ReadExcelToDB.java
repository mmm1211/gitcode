package com.oes.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelToDB {

	@SuppressWarnings("resource")
	public List<List<String>> importExcel(File fl) {
		List<List<String>> dataList = new ArrayList<List<String>>();
		try {
			Workbook workbook = null; // excel对象
			String fileName = fl.getName().toLowerCase(); // 获取文件名

			if (fileName.endsWith("xls")) {
				workbook = new HSSFWorkbook(new FileInputStream(fl));
			} else if (fileName.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(new FileInputStream(fl));
			} else {
				throw new RuntimeException("您选择的不是一个Excel文件...");
			}
			Sheet sheet = workbook.getSheet("Sheet1");// 获取Excel的第一个表格
			int rows = sheet.getLastRowNum();// 获取最后一行，即得到表格中的数据行数
			if (rows == 0) {
				throw new RuntimeException("表格中没有数据...");
			}
			Row row = null;// 行对象
			Iterator<Cell> cols = null;// 列对象
			List<String> list = null;// 存放一行数据
			for (int i = 1; i <= rows; i++) { // 循环获取每一行的数据(此处省去表头)
				row = sheet.getRow(i);
				if (row != null) {
					cols = row.cellIterator();
					list = new ArrayList<String>();
					while (cols.hasNext()) { // 循环获取每一列的数据存放在list中
						list.add(getCellString(cols.next()));
					}
					dataList.add(list);// 将这一行该数据存到dataList中
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	@SuppressWarnings("deprecation")
	public String getCellString(Cell cell) {
		if (cell == null) {
			return "";
		}
		String cellString = "";
		switch (cell.getCellType()) {

		case HSSFCell.CELL_TYPE_STRING:// 字符串
			cellString = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字
			cellString = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔
			cellString = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:// 公式
			cellString = String.valueOf(cell.getCellFormula());
			break;
		case HSSFCell.CELL_TYPE_BLANK:// 空值
			cellString = "";
			break;
		case HSSFCell.CELL_TYPE_ERROR:// 故障
			cellString = "";
			break;
		default:
			cellString = "";
			break;

		}
		return cellString;
	}
}
