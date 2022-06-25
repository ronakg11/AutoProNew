package resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	public static XSSFWorkbook workbook = null;
	public static XSSFSheet sheet = null;
	public static String pathToFile = System.getProperty("user.dir") + "/data/Learning_Notes.xlsx";
	public static File file = new File(pathToFile);
	private static Logger log = LogManager.getLogger(WriteExcel.class.getName());
	
	public static void createExcelWkbk() throws IOException {
    	file.delete();
        workbook = new XSSFWorkbook();
        log.info("Workbook created with name - Learning_Notes.xlsx");
        sheet = workbook.createSheet("Selenium");
        log.info("Sheet 1 - Selenium created");
        addColumnHeaders(sheet);
        log.info("Sheet 1 - Selenium headers added");
        sheet = workbook.createSheet("REST Assured");
        log.info("Sheet 2 - REST Assured created");
        addColumnHeaders(sheet);
        log.info("Sheet 2 - REST Assured headers added");
	}
	
	public static void writeInColumns(List<String> ls, int shtId) throws IOException {
//		File xlsxFile = new File(pathToFile);
//		FileInputStream fis = new FileInputStream(xlsxFile);
//		XSSFWorkbook workbook = new XSSFWorkbook(fis);
//		fis.close();

		sheet = workbook.getSheetAt(shtId);
		log.info("Sheet name: " + sheet.getSheetName());
		int currentNewRow = sheet.getLastRowNum() + 1;
		log.info("Current new row: " + currentNewRow);
		Row r = sheet.createRow(currentNewRow);
		
		for(int i = 0; i < ls.size(); i++) {
			log.info("Adding value " + ls.get(i) + " to (row, col) : (" + currentNewRow + ", " + i + ")");
			r.createCell(i).setCellValue(ls.get(i));
		}

//		FileOutputStream fos = new FileOutputStream(xlsxFile);
//	    workbook.write(fos);
//	    fos.close();
//		workbook.close();
	}
	
	public static void closeWorkbook() throws IOException {
	    FileOutputStream fos = new FileOutputStream(file);
	    workbook.write(fos);
	    fos.close();
	    workbook.close();
	    log.info("Workbook closed");
	}
	
	private static void addColumnHeaders(XSSFSheet sheet) {
		Row r0 = sheet.createRow(0);
		r0.createCell(0).setCellValue("S. No.");
		r0.createCell(1).setCellValue("Section#");
		r0.createCell(2).setCellValue("Lecture#");
		r0.createCell(3).setCellValue("Time");
		r0.createCell(4).setCellValue("Comments");
	}
}
