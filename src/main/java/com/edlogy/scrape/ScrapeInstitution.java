package com.edlogy.scrape;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.Option;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.edlogy.beans.Institution;
import com.edlogy.common.PropertyReader;
import com.edlogy.exception.EdlogyException;
import com.edlogy.util.BeanUtil;
import com.edlogy.util.StringUtil;
import com.edlogy.webdriver.WebDriverPoolService;

public class ScrapeInstitution {
	
	private Logger logger = Logger.getLogger(ScrapeInstitution.class);
	
	public void getInstitutionsInfo(String state, String district, boolean isRural){
		WebDriver driver = null;
		int entriesSSize  = 100;
		List<Institution>insList = new ArrayList<Institution>();
		try{
			
			driver = intialize();
			setStateAndDistrict(driver, state, district);
			
			if(isRural){
				logger.info("Getting Instituion for only for rural Areas of State - > " + state  + " and district -> " + district );
				driver.findElement(By.id("Schooltype1")).click(); 
			}else{
				logger.info("Getting Instituion for only for Urban Areas of State - > " + state +  " and district -> " + district );
				driver.findElement(By.id("Schooltype1")).click(); 
			}
			
			// submit 
			driver.findElement(By.id("schooldirectoryforviewonly_pu_true_search")).click(); 
			int totalInst = totalNoInstitutions(driver);
			
			setEntriesSize(driver,entriesSSize);
			
			for(int i=1; i<= totalInst; i++){
				List<WebElement> institutions = driver.findElements(By.xpath("//table[@id='example2']/tbody/tr"));
				for(WebElement institution : institutions){
					insList.add(getInstitution(institution));
				}
				//next submit
				driver.findElement(By.id("example2_next")).click(); 
			}
			
		}catch(EdlogyException ex){
			
		}catch(Exception e){
			
		}finally{
			if(driver != null) getWebDriverPoolService().releaseDriver(driver) ;
		}
	}
	
	
	
	private void setStateAndDistrict(WebDriver driver, String state, String district){
		
		boolean isStatePresent =  false, isDistrictPresent = false;
		
		if(StringUtil.isInvalidString(state) || StringUtil.isInvalidString(district)){
			logger.error("Invalid  State or District");
			throw new EdlogyException("Invalid  State or District");
		}
		
		Select selectState = new Select(driver.findElement(By.id("stateCode")));
		
		for(WebElement option : selectState.getOptions()){
			String text = option.getText();
			if(StringUtil.isValidString(text) && text.contains(state)){
				option.click();
				isStatePresent = true;
				break;
			}
		}
		
		Select selectDistrict = new Select(driver.findElement(By.id("districtCode")));
		
		for(WebElement option : selectDistrict.getOptions()){
			String text = option.getText();
			if(StringUtil.isValidString(text) && text.contains(district)){
				option.click();
				isDistrictPresent = true;
				break;
			}
		}
		
		if(!isStatePresent || ! isDistrictPresent){
			logger.error("State or District is Not present");
			throw new EdlogyException("State or District is Not present");
		}
	}
	
	private WebDriver intialize() throws Exception{
		
		String url = PropertyReader.getPropertyValue("crawl.url");
		
		if(StringUtil.isInvalidString(url)){
			logger.error("Invalid  Crawl  Url");
			throw new EdlogyException("Invalid Url");
		}
		
		WebDriver driver = null;
		driver = getWebDriverPoolService().getDriver();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}
	
	private int totalNoInstitutions(WebDriver driver){
		List<WebElement> elements =  driver.findElements(By.xpath("//div[@id='summaryTable']/fieldset/table[@class='display']/tbody/tr/td[3]"));
		int total = 0;
		
		for(WebElement element : elements){
			total += Integer.parseInt(element.getText());
		}
		logger.info("Total No of Institutions - > " + total);
		return total;
	}
	
	private void  setEntriesSize(WebDriver driver, int size){
		Select selectState = new Select(driver.findElement(By.name("example2_length")));
		
		for(WebElement option : selectState.getOptions()){
			String text = option.getText();
			if(StringUtil.isValidString(text) && text.contains(Integer.toString(size))){
				option.click();
				break;
			}
		}
	}
	
	private Institution getInstitution(WebElement instution){
		Institution institution = new Institution();
		institution.setCode(instution.findElement(By.xpath(".//td[1]")).getText());
		institution.setCategory(instution.findElement(By.xpath(".//td[2]")).getText());
		institution.setName(instution.findElement(By.xpath(".//td[3]")).getText());
		institution.setAddress(instution.findElement(By.xpath(".//td[4]")).getText());
		institution.setTownName(instution.findElement(By.xpath(".//td[5]")).getText());
		institution.setWardName(instution.findElement(By.xpath(".//td[6]")).getText());
		
		return institution;
	}
	
	private static WebDriverPoolService getWebDriverPoolService(){
		return (WebDriverPoolService) BeanUtil.getInstance().getBean("webDriverService");
	}
	
}
