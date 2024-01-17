import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EXUTIL {
	//public String Path;
		public  void WriteData(String path,String SheetName,String Status,int RowNum,int ColNum) {
			
			//"E:\\bala.xlsx"
			//"Consolidated"
			 try (FileInputStream file = new FileInputStream(path)) {
		            XSSFWorkbook wb = new XSSFWorkbook(file); // To read
		            XSSFSheet sheet = wb.getSheet(SheetName);

		         
		            Row dataRow = sheet.getRow(RowNum);
		            Cell cell = dataRow.createCell(ColNum);
		            
		           cell.setCellValue(Status);
		         

		            try (FileOutputStream fileOut = new FileOutputStream(path)) {
		                // Save the changes
		                wb.write(fileOut);
		                System.out.println("Data written successfully.");
		            }

		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		}
		
	}
