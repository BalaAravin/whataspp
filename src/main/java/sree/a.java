package sree;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class a {///worked
    public static void main(String[] args) {
        try (FileInputStream file = new FileInputStream("E:\\bala.xlsx")) {
            XSSFWorkbook wb = new XSSFWorkbook(file); // To read
            XSSFSheet sheet = wb.getSheet("Consolidated");

            int newRowNumber = sheet.getLastRowNum() + 1;
            Row dataRow = sheet.createRow(newRowNumber);

            // Column index (0-based) where you want to write the data
            int columnIndex = 5;

            dataRow.createCell(columnIndex).setCellValue("Fail");

            try (FileOutputStream fileOut = new FileOutputStream("E:\\bala.xlsx")) {
                // Save the changes
                wb.write(fileOut);
                System.out.println("Data written successfully.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

