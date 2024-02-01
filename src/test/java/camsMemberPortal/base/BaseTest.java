package camsMemberPortal.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;

import camsMemberPortal.Extend.ExtentTestManager;
import camsMemberPortal.initBrowser.startBrowser;
import camsMemberPortal.pages.memberSignIn;

public class BaseTest {
	startBrowser pf;
	public Page page;
protected	Properties prop ;
	
protected	memberSignIn signin;
	@BeforeTest
	public void setup() { 
		pf=new startBrowser();
	 prop=	pf.init_prop();
		page =pf.initBrowser(prop); 
		signin =new memberSignIn(page);
	}
		

	@AfterTest
		public void tearDown() {
		page.context().browser().close();
		}

}
