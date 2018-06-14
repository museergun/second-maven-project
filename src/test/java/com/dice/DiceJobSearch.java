package com.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().fullscreen();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String url ="https://dice.com";
		
		driver.get(url);
		
		String actual = driver.getTitle();
		String expected = "Job Search for Technology Professionals | Dice.com";
		
		if(actual.equals(expected)) {
			System.out.println("Success");
		}else {
			System.out.println("failed");
		}
		
		List<String> jobs = new ArrayList();
		FileReader fr = new FileReader("jobs.txt");
		BufferedReader br = new BufferedReader(fr);
		String jobTitle = "";
		
		while((jobTitle=br.readLine())!=null) {
			jobs.add(jobTitle);
		}
		
		//Random rd = new Random();
		driver.findElement(By.name("q")).clear();
		String job="";
		//driver.findElement(By.name("q")).sendKeys(job=jobs.get(rd.nextInt(jobs.size())));
		
		ArrayList<String> total = new ArrayList();
		for(String i:jobs) {
			driver.findElement(By.name("l")).clear();
			driver.findElement(By.name("q")).sendKeys(i);
		
		
		String zip ="22102";
		driver.findElement(By.name("l")).clear();
		driver.findElement(By.name("l")).sendKeys(zip);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String str = driver.findElement(By.id("posiCountId")).getText();
		
		int count =Integer.parseInt(str.replace(",",""));
		System.out.println("Job Title : "+job+ " available positions : "+ count);
		total.add(i+"-->"+count);
		driver.navigate().back();
		
		}
		
		

		driver.close();
		for(String i :total) {
			System.out.println(i);
		}
		
		
		
		
		
		
		

	}

}
