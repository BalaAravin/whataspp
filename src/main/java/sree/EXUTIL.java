package sree;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EXUTIL {

	public  void WriteData(String path,String SheetName,String Status,int RowNum,int ColNum) {
		
		 try (FileInputStream file = new FileInputStream(path)) {
	            XSSFWorkbook wb = new XSSFWorkbook(file); // To read
	            XSSFSheet sheet = wb.getSheet(SheetName);

	         
	            Row dataRow = sheet.getRow(RowNum);
	            Cell cell = dataRow.createCell(ColNum);
	            if(Status=="Pass") {
	         // Create a style with red font color
	            CellStyle style = wb.createCellStyle();
	            Font font = wb.createFont();
	            font.setColor(IndexedColors.GREEN.getIndex());
	            style.setFont(font);

	            // Apply the style to the cell
	            cell.setCellStyle(style);
	            }
	            else {
	            	CellStyle style = wb.createCellStyle();
		            Font font = wb.createFont();
		            font.setColor(IndexedColors.RED.getIndex());
		            style.setFont(font);
		            cell.setCellStyle(style);
	            }
	            
	           cell.setCellValue(Status);
	         
	           

	            try (FileOutputStream fileOut = new FileOutputStream(path)) {
	                // Save the changes
	                wb.write(fileOut);
	                //System.out.println("Data written successfully.");
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}
	
}
