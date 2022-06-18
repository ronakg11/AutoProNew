package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	// Identify test cases column by scanning the entire 1st row
	// once column is identified then scan entire test case column
	// to identify purchase test case row
	// after you grab purchase test case row
	// pull all the data of that row and feed into test
	public static ArrayList<String> getExcelData(String testcaseName) throws IOException {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/data/InputData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		fis.close();

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Test Data")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Identify test cases column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstRow = rows.next();
				Iterator<Cell> columns = firstRow.cellIterator();// row is collection of cells
				int k = 0;
				int column = 0;

				while (columns.hasNext()) {
					Cell cell = columns.next();

					if (cell.getStringCellValue().equalsIgnoreCase("Test Cases")) {
						column = k;
					}
					k++;
				}
				// System.out.println(column);

				// Once column is identified then scan entire test case column to identify
				// purchase test case row
				while (rows.hasNext()) {
					Row row = rows.next();

					if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						// After you grab purchase test case row, pull all the data of that row
						// and feed into test
						Iterator<Cell> tcCell = row.cellIterator();
						while (tcCell.hasNext()) {
							Cell c = tcCell.next();
							if (c.getCellType() == CellType.STRING) {
								a.add(c.getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		workbook.close();
		return a;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<String> arrList = getExcelData("Delete Profile");

		for (int i = 0; i < arrList.size(); i++) {
			System.out.println(arrList.get(i));
		}
	}
}
