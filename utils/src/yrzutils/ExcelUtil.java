package yrzutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	/**
	 *
	 * @param filePath
	 *            需要读取的文件路径
	 * @param column
	 *            指定需要获取的列数，例如第一列 1
	 * @param startRow
	 *            指定从第几行开始读取数据
	 * @param endRow
	 *            指定结束行
	 * @return 返回读取列数据的set
	 */
	public static ArrayList<String> getColumnSet(String filePath, int column, int startRow, int endRow) {
		Workbook wb = readExcel(filePath); // 文件
		Sheet sheet = wb.getSheetAt(0); // sheet
		int rownum = sheet.getPhysicalNumberOfRows(); // 行数
		Row row = null;
		ArrayList<String> result = new ArrayList();
		String cellData = null;
		if (wb != null) {
			for (int i = startRow - 1; i < endRow; i++) {
				System.out.println(i);
				row = sheet.getRow(i);
				if (row != null) {
					cellData = (String) getCellFormatValue(row.getCell(column - 1));
					result.add(cellData.replaceAll(" ", ""));
				} else {
					break;
				}
				System.out.println(cellData);
			}
		}
		return result;
	}

	/**
	 *
	 * @param filePath
	 *            需要读取的文件路径
	 * @param column
	 *            指定需要获取的列数，例如第一列 1
	 * @param startRow
	 *            指定从第几行开始读取数据
	 * @return 返回读取列数据的set
	 */
	public static ArrayList<String> getColumnSet(String filePath, int column, int startRow) {
		Workbook wb = readExcel(filePath); // 文件
		Sheet sheet = wb.getSheetAt(0); // sheet
		int rownum = sheet.getPhysicalNumberOfRows(); // 行数
		System.out.println("sumrows " + rownum);

		return getColumnSet(filePath, column, startRow, rownum - 1);
	}

	/**
	 * 读取excel
	 * @Description:
	 * @author:yrz
	 * @data :2019年4月28日上午10:12:01
	 * @version: 
	 * @param filePath
	 * @return
	 */
	public static Workbook readExcel(String filePath) {
		Workbook wb = null;
		if (filePath == null) {
			return null;
		}
		String extString = filePath.substring(filePath.lastIndexOf("."));
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			if (".xls".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else {
				return wb = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * 格式转换
	 * @Description:
	 * @author:yrz
	 * @data :2019年4月28日上午10:11:42
	 * @version: 
	 * @param cell
	 * @return
	 */
	public static Object getCellFormatValue(Cell cell) {
		Object cellValue = null;
		if (cell != null) {
			cellValue = cell.getStringCellValue();
		} else {
			cellValue = "";
		}
		return cellValue;
	}

	public static void main(String[] args) {
		ArrayList<String> columnSet = ExcelUtil.getColumnSet("E:\\Abaumannii.xlsx", 1, 1); // 读取第一列的从第90行开始往后的数据
																							// 到set
		System.out.println(columnSet.size());
		System.out.println(columnSet.toString());

	}

}
