import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WhatsappWeb2 {

	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		WebDriverWait pwait=new WebDriverWait(driver, 80,5000);
		WebDriverWait wait=new WebDriverWait(driver, 20);
		FileInputStream file = new FileInputStream("E:\\bala.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet("Consolidated");
		int rowcount = sheet.getLastRowNum();



		for (int i = 1; i <= rowcount; i++) {
			System.out.println(" ");
			String name = sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println(name);
			String mobile = sheet.getRow(i).getCell(2).getRawValue();
			System.out.println(mobile);
			String amount = sheet.getRow(i).getCell(3).getRawValue();
			System.out.println(amount);
			//String message ="Dear "+name+" We have received RS."+amount+" from you.Thanking You";
			String message ="திரு/திருமதி. "+name+"  உங்களிடம் இருந்து ருபாய்."+amount+"  அய்யா வைகுண்டர் நிழல் தாங்கல் திருப்பணிக்காக அன்பாக பெற்றுக்கொண்டோம். நன்றி. அய்யா உண்டு.";
			//String url = "https://api.whatsapp.com/send?phone=" + mobile + "&text=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
			String url = "https://wa.me/" + mobile + "?text="
					+ URLEncoder.encode(message, StandardCharsets.UTF_8);
			System.out.println(url);

			//Load URL

			driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			//To check whether the alert present or not
			try {	
				driver.get(url);
				
				


		

		
				
				
			} catch (UnhandledAlertException e3) {
				Alert alert = driver.switchTo().alert();
				alert.dismiss();
			
				System.out.println("catch 1 st alert dismissed");
				Thread.sleep(2000);
				driver.navigate().refresh();
				Thread.sleep(2000);
			}

			// TO CLICK continue to chat button
			try {
				WebElement continuechat = driver.findElement(By.xpath("//a[@id='action-button']"));
				wait.until(ExpectedConditions.elementToBeClickable(continuechat));
				continuechat.click();
				//Thread.sleep(2000);

				WebElement whatsappweb = driver.findElement(By.xpath("//span[text()='use WhatsApp Web']"));
				wait.until(ExpectedConditions.elementToBeClickable(whatsappweb));
				whatsappweb.click();
				//Thread.sleep(5000);


				try {

					WebElement send_btn = driver.findElement(By.xpath("//span[@data-icon='send']"));
					pwait.until(ExpectedConditions.elementToBeClickable(send_btn));

					Thread.sleep(1000);
					send_btn.click();
					System.out.println("Message sended to "+name+" "+mobile);

				}catch(Exception e) {
//					WebElement no_whatsapp = driver.findElement(By.xpath("//div[text()='Phone number shared via url is invalid.']"));
//					WebElement no_whatsapp_OKbtn = driver.findElement(By.xpath("//div[text()='OK']"));
					//To check whether the alert present or not
					System.out.println("Try send button");
					try {
						Alert alert = driver.switchTo().alert();
						alert.dismiss();
						System.out.println("Alert Dismissed");
					} catch (Exception e1) {
						System.out.println("Try Alert");
						e1.printStackTrace();
						continue;
					}
//
//					if (no_whatsapp.isDisplayed()) {
//						System.out.println(name+" does not have whataspp number");
//						Thread.sleep(2000);
//						no_whatsapp_OKbtn.click();
//						
//					}
                      // continue;
				}

				//driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
				//Thread.sleep(1000);
				//	    	//div[text()='Starting chat']
				//WebElement Starting_chat = driver.findElement(By.xpath("//div[text()='Starting chat']"));
				//	    	  
				//	    	if(wait.until(ExpectedConditions.invisibilityOf(Starting_chat))) {
				//	    		
				//	    		
				//	    	}

				//	pwait.until(ExpectedConditions.invisibilityOf(Starting_chat));


			//	Robot rob = new Robot();

				// press ctrl+v
				//rob.keyPress(KeyEvent.VK_ENTER);

			//	rob.setAutoDelay(200);

				// release ctrl+v
				//  rob.keyRelease(KeyEvent.VK_ENTER);
			//	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;


				//jsExecutor.executeScript("arguments[0].click();", send_btn);


				//Thread.sleep(1000);
			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("Message NOT sended to "+name+" "+mobile);
            continue;

			}
			


		}

	}}
