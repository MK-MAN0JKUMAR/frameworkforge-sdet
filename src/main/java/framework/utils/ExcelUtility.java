package framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private final String filePath;

	public ExcelUtility(String filePath) {
		this.filePath = filePath;
	}

	public Object[][] readSheet(String sheetName) throws IOException {

		try (FileInputStream fis = new FileInputStream(filePath);
			 Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			int rows = sheet.getPhysicalNumberOfRows();
			int cols = sheet.getRow(0).getPhysicalNumberOfCells();

			Object[][] data = new Object[rows - 1][cols];
			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i < rows; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < cols; j++) {
					data[i - 1][j] = formatter.formatCellValue(row.getCell(j));
				}
			}
			return data;
		}
	}
}
