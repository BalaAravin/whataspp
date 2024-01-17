package sree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class sreewhatsappp  {

	public static String name;
	public static String mobile;
	protected static	FileInputStream file;
	protected static	XSSFWorkbook wb;
	protected static XSSFSheet sheet;


	public static void main(String[] args) throws IOException, InterruptedException {
		EXUTIL excel=new EXUTIL();
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait pwait=new WebDriverWait(driver, 60);
		WebDriverWait wait=new WebDriverWait(driver, 20);

		file = new FileInputStream("E:\\bala.xlsx");		 
		wb = new XSSFWorkbook(file); //To read		
		sheet = wb.getSheet("Consolidated");
		int rowcount = sheet.getLastRowNum();


		for (int i = 1; i <= rowcount; i++) {

			try {

				System.out.println(" ");
				name = sheet.getRow(i).getCell(1).getStringCellValue();
				System.out.println(name);
				mobile = sheet.getRow(i).getCell(2).getRawValue();
				System.out.println(mobile);
				String amount = sheet.getRow(i).getCell(3).getRawValue();
				System.out.println(amount);
				String message ="Dear "+name+" We have received RS."+amount+" from you.Thanking You";
				//String message ="திரு/திருமதி. "+name+"  உங்களிடம் இருந்து ருபாய்."+amount+"  அய்யா வைகுண்டர் நிழல் தாங்கல் திருப்பணிக்காக அன்பாக பெற்றுக்கொண்டோம். நன்றி. அய்யா உண்டு.";
				//String url = "https://api.whatsapp.com/send?phone=" + mobile + "&text=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
				String url = "https://wa.me/" + mobile + "?text="
						+ URLEncoder.encode(message, StandardCharsets.UTF_8);
				//System.out.println(url);
				Thread.sleep(2000);
				try {	
					driver.get(url);
					Thread.sleep(2000);
					try {

					} catch (UnhandledAlertException e) {
						Alert alert = driver.switchTo().alert();
						alert.dismiss();		
						System.out.println("catch 1 st alert dismissed");			
						driver.navigate().refresh();
						Thread.sleep(2000);
					}

				} catch (UnhandledAlertException e3) {
					Alert alert = driver.switchTo().alert();
					alert.dismiss();		
					System.out.println("catch 1 st alert dismissed");			
					driver.navigate().refresh();
					Thread.sleep(2000);
				}
				Thread.sleep(1000);
				WebElement continuechat = driver.findElement(By.xpath("//a[@id='action-button']"));
				wait.until(ExpectedConditions.elementToBeClickable(continuechat));
				continuechat.click();
				Thread.sleep(2000);

				WebElement whatsappweb = driver.findElement(By.xpath("//span[text()='use WhatsApp Web']"));
				wait.until(ExpectedConditions.elementToBeClickable(whatsappweb));
				whatsappweb.click();
				Thread.sleep(2000);
				try {
					Thread.sleep(1000);
					
				} catch (UnhandledAlertException e3) {
					System.out.println("alert present");
					Alert alert = driver.switchTo().alert();
					alert.dismiss();

					driver.navigate().refresh();

				}
				Thread.sleep(2000);
				WebElement send_btn = driver.findElement(By.xpath("//span[@data-icon='send']"));
				pwait.until(ExpectedConditions.elementToBeClickable(send_btn));

				Thread.sleep(1000);
				send_btn.click();
				System.out.println("Message sended to "+name+" "+mobile);
				Thread.sleep(1000);
				excel.WriteData("E:\\bala.xlsx", "Consolidated", "Pass",i,5);
				
			} catch (Exception e) {
				System.out.println("Message NOT sended to "+name+" "+mobile);
				excel.WriteData("E:\\bala.xlsx", "Consolidated", "Fail",i,5);
                driver.navigate().refresh();
			}

		}

	}

}
