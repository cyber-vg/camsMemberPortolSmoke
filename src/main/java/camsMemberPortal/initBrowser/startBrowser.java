package camsMemberPortal.initBrowser;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class startBrowser {
Playwright playwright;
Browser browser;
BrowserContext browserContext;	
static Page page;
Properties prop;

private static ThreadLocal<Playwright> tlPlaywright=new ThreadLocal<Playwright>() ;
private static ThreadLocal<Browser> tlBrowser=new ThreadLocal<Browser>() ;
private static ThreadLocal<BrowserContext> tlBrowserContext=new ThreadLocal<BrowserContext>() ;
private static ThreadLocal<Page> tlPage=new ThreadLocal<Page>() ;

public static Playwright getPlaywright() {
	return tlPlaywright.get();
}
public static Browser getBrowser() {
	return tlBrowser.get();
}
public static BrowserContext getBrowserContext() {
	return tlBrowserContext.get();
}
public static Page getPage() {
	return tlPage.get();
}





public Page initBrowser(Properties prop) {
	Dimension  screensize	= Toolkit.getDefaultToolkit() .getScreenSize()	;
	int width=(int)screensize.getWidth();
	int height=(int)screensize.getHeight();	
		
	String browserName=  prop.getProperty("browser").trim();
		System.out.println("Browser name is "+ browserName);
		playwright = Playwright.create();
		
		switch (browserName.toLowerCase().trim()) {
		case "chromium":
			browser =playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));	
			tlBrowser.set(browser);
			break;
		case "firefox":
			browser= playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));	
			tlBrowser.set(browser);
			break;
		case "safari":
			 browser =playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));	
			 tlBrowser.set(browser);
			 break;
		case "chrome":
			browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));	
			tlBrowser.set(browser);
			break;

		default:
			System.out.println("Wrong browser");
			break;
		}
//		browserContext=browser.newContext( new Browser.NewContextOptions().setViewportSize(width,height));
//		page=browserContext.newPage();
//		page.navigate(prop.getProperty("url"));
//		
		tlBrowserContext.set(getBrowser().newContext());
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url"));
		
		return getPage();
	}
	
public Properties init_prop() {
	
	try {
		FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
	prop=new Properties();
	prop.load(ip);
	
	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
return prop;
}
//public static String takeScreenshot() {
//	String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
//	//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
//	
//	page.screenshot(new Page.ScreenshotOptions()
//			  .setPath(Paths.get(path))
//			  .setFullPage(true));
//	
//	
//	return path;
//}
public static String takeScreenshot() {
	String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
	//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
	
	byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
	String base64Path = Base64.getEncoder().encodeToString(buffer);
	
	return base64Path;
}
}

