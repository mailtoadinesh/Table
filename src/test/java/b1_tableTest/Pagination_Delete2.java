package b1_tableTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Pagination_Delete2 {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver.get("https://datatables.net/examples/advanced_init/dt_events.html");
		
		String nextButton=driver.findElement(By.id("example_next")).getAttribute("class");
		
		List<WebElement> webNames=driver.findElements(By.xpath("//table[@id='example']//tr//td[1]"));
		
		List<String> name=new ArrayList<String>();
		
		for(WebElement addnames:webNames)
		{
			name.add(addnames.getText());
		}
		
		while(!nextButton.contains("disabled"))
		{
			driver.findElement(By.id("example_next")).click();
			webNames=driver.findElements(By.xpath("//table[@id='example']//tr//td[1]"));
			for(WebElement addnames:webNames)
			{
				name.add(addnames.getText());
			}
			nextButton=driver.findElement(By.id("example_next")).getAttribute("class");
		}
		
		int nameSize=name.size();
		System.out.println(nameSize);
		
		String WebSize=driver.findElement(By.xpath("//div[@id='example_info']")).getText().split(" ")[5];
		System.out.println(WebSize);
		
		if(nameSize==(Integer.parseInt(WebSize)))
		System.err.println("Count matches");
		else
			System.err.println("count doenst match");
		
		driver.close();
	}
}
