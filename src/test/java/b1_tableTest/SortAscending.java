package b1_tableTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortAscending 
{
	@Test
	public static void sortTest()
	//public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		List <WebElement> FilterPriceList_BeforeSort =driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		System.out.println(FilterPriceList_BeforeSort.size());
		List <Double> FilterPrice_BeforeSort=new ArrayList<Double>();
		for(WebElement p : FilterPriceList_BeforeSort)
		{
			FilterPrice_BeforeSort.add(Double.parseDouble((p.getText().replace("$", ""))));
		}
		System.out.print(FilterPrice_BeforeSort+" ");
		Collections.sort(FilterPrice_BeforeSort);
		Collections.reverse(FilterPrice_BeforeSort);
		System.out.println("");
		System.out.println("------------------------");
		System.out.print(FilterPrice_BeforeSort+" ");
		
		Select s=new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		s.selectByVisibleText("Price (high to low)");
		
		List <WebElement> FilterPriceList_AfterSort =driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		List <Double> FilterPrice_Aftersort=new ArrayList<Double>();
		for(WebElement p : FilterPriceList_AfterSort)
		{
			FilterPrice_Aftersort.add(Double.parseDouble((p.getText().replace("$", ""))));
		}
		System.out.println("");
		System.out.println(FilterPrice_Aftersort);
		Assert.assertEquals(FilterPrice_BeforeSort, FilterPrice_Aftersort);
		driver.quit();
	}

}
