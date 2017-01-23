package com.search.com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.search.com.pages.SearchPage;

public class SearchTest {	

	@Test
	public void searchVerification() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("http://www.bing.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		SearchPage searchPage = new SearchPage();
		Assert.assertTrue(searchPage.searchSparkcentral(driver), "Search Testing for keyword failed");
		driver.quit();
	}
}
