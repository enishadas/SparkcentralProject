package com.search.com.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	
	public boolean searchSparkcentral(WebDriver driver) {
		System.out.println("Getting title of bing page");
		String abc = driver.getTitle();
		System.out.println(abc);
		System.out.println("Retrieved bing title correctly!");		
		try {
			WebElement searchKeywordField = driver.findElement(By.xpath("//input[@id='sb_form_q']"));
			searchKeywordField.clear();
			searchKeywordField.sendKeys("sparkcentral");
			WebElement searchButton = driver.findElement(By.id("sb_form_go"));
			searchButton.click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			String SearchPageTitle = driver.getTitle();
			//verify after keyword search correct results page is displayed
			if(SearchPageTitle.equalsIgnoreCase("sparkcentral - Bing")) {
				System.out.println("The correct page title appears");
			} else {
				Assert.fail("Correct Search page did not appear");
			}
			List<WebElement> keywordSearchResult = driver.findElements(By.linkText("Sparkcentral - Official Site"));
			//get first sparkcentral link
			for (int i = 0; i<=keywordSearchResult.size(); i++) {	 
				String firstSparkcentralLink = keywordSearchResult.get(0).getText();
				if(firstSparkcentralLink.equalsIgnoreCase("Sparkcentral - Official Site")) {
					System.out.println("The 1st link is received correctly");
				} else {
					Assert.fail("Could not retrieve the required search result");
				}
			}
			List<WebElement> verifyKeywordDisplay = driver.findElements(By.xpath("//strong[text()='Sparkcentral']"));
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			//verify sparkcentral text exists in all search results
			for (int i = 0; i<verifyKeywordDisplay.size(); i++) {	 
				String verifyKeyword = verifyKeywordDisplay.get(i).getText();
				if(verifyKeyword.contains("Sparkcentral")) {
					System.out.println("The link has text Sparkcentral:" +verifyKeyword);
					System.out.println(verifyKeywordDisplay.size());
				} else {
					Assert.fail("Link does not have correct keyword");
				}
			}
			System.out.println("Now checking Product URL");
			WebElement productLink = driver.findElement(By.xpath("//a[@href='https://www.sparkcentral.com/product/']"));
			productLink.click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			String ProductUrl = driver.getCurrentUrl();	
			System.out.println(ProductUrl);
			String productURLText = "https://www.sparkcentral.com/product/";			
			//verify Product link points to correct url
			Assert.assertEquals(ProductUrl, productURLText);	
		} catch (Throwable e) {
			System.out.println(e);
			return false;			
		}
		return true;
	}
}
