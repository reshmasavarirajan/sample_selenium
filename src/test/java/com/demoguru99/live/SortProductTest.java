package com.demoguru99.live;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SortProductTest {

	private WebDriver driver;
	private String baseUrl;
	private Actions action;
	private WebDriverWait wait;
	
	@BeforeMethod
	public void openBrowser() throws Exception {
		driver = new ChromeDriver();
		baseUrl = "http://live.demoguru99.com/";
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		action = new Actions(driver);
		wait = new WebDriverWait(driver,30);
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception {
		driver.quit();
	}
	
	@Test
	public void sortMobileNAme() {
		driver.get(baseUrl);
		Assert.assertEquals(driver.getTitle(),"Home page");
		driver.findElement(By.linkText("MOBILE")).click();
		String selector = "select[title='Sort By']";
		driver.findElement(By.cssSelector(selector)).click();
		String optionSelector = "select[title='Sort By'] option:nth-child(2)";
		driver.findElement(By.cssSelector(optionSelector)).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".products-grid li h2"));
		Assert.assertEquals(products.get(0).getText(),"IPHONE");
		Assert.assertEquals(products.get(1).getText(),"SAMSUNG GALAXY");
		Assert.assertEquals(products.get(2).getText(),"SONY XPERIA");	
	}	
}