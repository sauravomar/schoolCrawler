package com.edlogy.webdriver;

import java.util.Stack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.edlogy.common.PropertyReader;

@Service("webDriverPool")
public class WebDriverPoolService implements InitializingBean {

	private Stack<WebDriver> webDriverPool;

	public void afterPropertiesSet() throws Exception {
		webDriverPool = new Stack<WebDriver>();
	}

	private WebDriver createInstance() throws Exception {
		DesiredCapabilities dCapabilities = new DesiredCapabilities();
		dCapabilities.setJavascriptEnabled(true);
		dCapabilities.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				PropertyReader.getPropertyValue("phantom.location"));
		WebDriver driver = new PhantomJSDriver(dCapabilities);
		webDriverPool.push(driver);
		return driver;
	}

	public synchronized WebDriver getDriver() throws Exception {
		return webDriverPool.empty() ? createInstance() : webDriverPool.pop();
	}

	public void releaseDriver(WebDriver instance) {
		webDriverPool.push(instance);
	}


	public void shutdown() {
		while (!webDriverPool.isEmpty()) {
			WebDriver driver = webDriverPool.pop();
			try {
				driver.quit();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
