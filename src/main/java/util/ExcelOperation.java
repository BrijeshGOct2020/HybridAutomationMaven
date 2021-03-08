package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constantPath.ConfigFilePath;

public class ExcelOperation {

	public static String[][] getExcelData(String fileName, String sheetName) throws IOException {
		String filePath = ConfigFilePath.TEST_DATA + fileName;
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum(); // 3
		int totalCols = sheet.getRow(0).getLastCellNum(); // 3
		String[][] data = new String[totalRows][totalCols];
		for (int rowIndex = 1; rowIndex <= totalRows; rowIndex++) {
			for (int colIndex = 0; colIndex < totalCols; colIndex++) {
				Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
				if(cell == null)
					data[rowIndex-1][colIndex] = "";
				else if(cell.getCellType() == CellType.NUMERIC) 
					data[rowIndex-1][colIndex] = String.valueOf((int)cell.getNumericCellValue());
				else if(cell.getCellType() == CellType.BOOLEAN)
					data[rowIndex-1][colIndex] = String.valueOf(cell.getBooleanCellValue());
				else if(cell.getCellType() == CellType.STRING)
					data[rowIndex-1][colIndex] = cell.getStringCellValue();
				else if (cell.getCellType() == CellType.BLANK)
					data[rowIndex-1][colIndex] = "";
				System.out.print(data[rowIndex-1][colIndex] + " ");
			}
			System.out.println();
		}
		wb.close();
		return data;
	}
}
