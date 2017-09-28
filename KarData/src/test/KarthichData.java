package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KarthichData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		FileInputStream ExcelFile = new FileInputStream("//home/bhavik/Downloads/KarthichData.xlsx");
		XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
		XSSFSheet ExcelWSheet = ExcelWBook.getSheet("KarthichData");	//.getSheet("NseStocknames");
		int RowCount=ExcelWSheet.getLastRowNum()+1;
		System.out.println(RowCount);
		List<String> SymbolDataList = new ArrayList<String>();
		List<String> NameDataList = new ArrayList<String>();
		List<String> DataFoundLst = new ArrayList<String>();
		List<String> DataNotFoundLst = new ArrayList<String>();
		for(int i=1;i<RowCount;i++)
		{
			SymbolDataList.add(ExcelWSheet.getRow(i).getCell(0).getStringCellValue().toString());
			NameDataList.add(ExcelWSheet.getRow(i).getCell(1).getStringCellValue().toString());
		}
		System.out.println(SymbolDataList.size());
		
		System.setProperty("webdriver.chrome.driver", "/home/bhavik/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		driver.get("http://economictimes.indiatimes.com/");
		driver.manage().window().maximize();
		
			
		for(int i=0;i<RowCount-1;i++)
		{
			driver.findElement(By.xpath("//*[@id='searchBar']/form/input")).clear();
			driver.findElement(By.xpath("//*[@id='searchBar']/form/input")).sendKeys(SymbolDataList.get(i));
			
			XSSFRow Row  = ExcelWSheet.getRow(i+1);
		    Cell Cell = Row.getCell(2);
			try
			{
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='searchBar']/form/ul/li[2]/a")).click();
				Thread.sleep(1000);
				String parentWindow = driver.getWindowHandle();
				Set<String> handles =  driver.getWindowHandles();
				for(String windowHandle  : handles)
				{
				    if(!windowHandle.equals(parentWindow))
				   {
				     driver.switchTo().window(windowHandle);
				     if (Cell == null) {
						   Cell = Row.createCell(2);
						   Cell.setCellValue(driver.getCurrentUrl());
						   DataFoundLst.add(driver.getCurrentUrl());
					   } else {
							Cell.setCellValue(driver.getCurrentUrl());
							DataFoundLst.add(driver.getCurrentUrl());
							}
					 driver.close(); //closing child window
				     driver.switchTo().window(parentWindow);
				     Thread.sleep(1000);//cntrl to parent window
				    }
				}
			}
			catch (Exception e) 
			{
				driver.findElement(By.xpath("//*[@id='searchBar']/form/input")).clear();
				driver.findElement(By.xpath("//*[@id='searchBar']/form/input")).sendKeys(NameDataList.get(i));
				try
				{// TODO: handle exception
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='searchBar']/form/ul/li[2]/a")).click();
					Thread.sleep(1000);
					String parentWindow = driver.getWindowHandle();
					Set<String> handles =  driver.getWindowHandles();
					for(String windowHandle  : handles)
					{
					    if(!windowHandle.equals(parentWindow))
					   {
					     driver.switchTo().window(windowHandle);
					     if (Cell == null) {
							   Cell = Row.createCell(2);
							   Cell.setCellValue(driver.getCurrentUrl());
							   DataFoundLst.add(driver.getCurrentUrl());
						   } else {
								Cell.setCellValue(driver.getCurrentUrl());
								DataFoundLst.add(driver.getCurrentUrl());
								}
						 driver.close(); //closing child window
					     driver.switchTo().window(parentWindow); //cntrl to parent window
					     Thread.sleep(1000);
					    }
					}
				}
				catch (Exception a)
				{
					System.out.println("No data found for symbol "+ NameDataList.get(i));
					if (Cell == null) 
					{
						Cell = Row.createCell(2);
						Cell.setCellValue("No data found for symbol "+ NameDataList.get(i));
						DataNotFoundLst.add(NameDataList.get(i));
					 } 
					else 
					{
						Cell.setCellValue("No data found for symbol "+ NameDataList.get(i));
						DataNotFoundLst.add(NameDataList.get(i));
					}
				}
				
			}
			finally{
				FileOutputStream fileOut = new FileOutputStream("//home/bhavik/Downloads/KarthichData.xlsx");
				ExcelWBook.write(fileOut);
				fileOut.close();
			}
		}
		
			
		
		//System.out.println(DataNotFoundLst);
		FileOutputStream fileOut = new FileOutputStream("//home/bhavik/Downloads/KarthichData.xlsx");
		ExcelWBook.write(fileOut);
		fileOut.close();
		driver.quit();
	}

}
