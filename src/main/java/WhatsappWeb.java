import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.formula.functions.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import common.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;




public class WhatsappWeb {
	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait pwait=new WebDriverWait(driver, 120,5000);
		WebDriverWait wait=new WebDriverWait(driver, 20);
	//driver.get("https://web.whatsapp.com");
	//WebElement barcodepage = driver.findElement(By.xpath("//span[text()='Link with phone number']"));
	  
	//  pwait.until(ExpectedConditions.visibilityOf(barcodepage));
	 // Thread.sleep(15000);
	
		
		
	FileInputStream file = new FileInputStream("E:\\bala.xlsx");
	       XSSFWorkbook wb = new XSSFWorkbook(file);
	       XSSFSheet sheet = wb.getSheet("Consolidated");
	       int rowcount = sheet.getLastRowNum();
	       
	     
	     
	      for (int i = 1; i <= 4; i++) {
	    	  String name = sheet.getRow(i).getCell(1).getStringCellValue();
	    	  System.out.println(name);
		      String mobile = sheet.getRow(i).getCell(2).getRawValue();
		      System.out.println(mobile);
		      String amount = sheet.getRow(i).getCell(3).getRawValue();
		      System.out.println(amount);
		      String message ="Dear "+name+" We have received RS."+amount+" from you.Thanking You";
	    	  String url = "https://api.whatsapp.com/send?phone=" + mobile + "&text=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
		// System.out.println(url);
	    	  
				//driver.get(url);
				 
				 
				 //continue chat
				 WebElement continuechat = driver.findElement(By.xpath("//a[@id='action-button']"));
				 wait.until(ExpectedConditions.elementToBeClickable(continuechat));
				 continuechat.click();
				 
		    	  Thread.sleep(1000);
		    	  WebElement whatsappweb = driver.findElement(By.xpath("//span[text()='use WhatsApp Web']"));
		    	  wait.until(ExpectedConditions.elementToBeClickable(whatsappweb));
		    	  whatsappweb.click();
		    	  Thread.sleep(20000);
		    	  try {
		    		  WebElement no_whatsapp = driver.findElement(By.xpath("//div[text()='Phone number shared via url is invalid.']"));
						 WebElement no_whatsapp_OKbtn = driver.findElement(By.xpath("//div[text()='OK']"));
						 no_whatsapp.isDisplayed(); 
						 System.out.println(name+"doesn't have whatsapp number");
						 wait.until(ExpectedConditions.elementToBeClickable(no_whatsapp_OKbtn));
						 no_whatsapp_OKbtn.click(); 
					
				} catch (Exception e) {
					 driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			    	  Thread.sleep(5000);
			    	  WebElement send_btn = driver.findElement(By.xpath("//span[@data-icon='send']"));
			    	  pwait.until(ExpectedConditions.elementToBeClickable(send_btn));
			    	  send_btn.click();
					
				}
		    	  
		    	  
		    	  
		    	 
		    	  
		    	  //JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		          
		    	  
		    	 // jsExecutor.executeScript("arguments[0].click();", send_btn);
		    	  
		    	  try {
		    	  Thread.sleep(1000);
		    	  
		    	  Alert alert = driver.switchTo().alert();
		    	  alert.dismiss();
		    	 
			} catch (UnhandledAlertException e) {
				
				driver.navigate().refresh();
				 
			//	continue;
//				 WebElement no_whatsapp = driver.findElement(By.xpath("//div[text()='Phone number shared via url is invalid.']"));
//				 WebElement no_whatsapp_OKbtn = driver.findElement(By.xpath("//div[text()='OK']"));
//				 if (no_whatsapp.isDisplayed()) {
//					 wait.until(ExpectedConditions.elementToBeClickable(no_whatsapp_OKbtn));
//					 no_whatsapp_OKbtn.click();
					
//					 continue;
//				
//				 }
				
//				Alert alert = driver.switchTo().alert();
//				
			           }
		      System.out.println("The messages sent upto row "+i+". "+name);
		      
		      
			         }
	  
			
	      
	      
			       }
	           
                }
      
		
		
	
	
	
	


