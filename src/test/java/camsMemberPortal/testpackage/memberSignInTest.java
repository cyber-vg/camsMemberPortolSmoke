package camsMemberPortal.testpackage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


import camsMemberPortal.AppConstant.AppConstants;
import camsMemberPortal.base.BaseTest;


public class memberSignInTest extends BaseTest {
	 private static final Logger logger = LogManager.getLogger(memberSignInTest.class);
	
	@Test (priority=1)
	public void signInTitleTest()
	{
 
	String actualTitle= signin.getHomePageTitle();
	 logger.info("Starting test method");
		Assert.assertEquals(actualTitle, AppConstants.Login_Page_Title);
		
	}
	@Test(priority=2)
	public void signInURLTest()
	{
		String actualURL= signin.getHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("url"));
	}
	@Test  (priority=3)
	public void validLoginUseEmailTest()
	{
		Boolean cardVisible= signin.validLoginusingEmail(prop.getProperty("username"),prop.getProperty("password"));
	Assert.assertTrue(cardVisible);
	}
	@Test  (priority=4)
	public void validLoginUsePhoneTest()
	{
		Boolean cardVisible= signin.validLoginUsingPhone(prop.getProperty("validPhonenNO").trim(),prop.getProperty("password").trim());
	Assert.assertTrue(cardVisible);
	}
	@Test  (priority=5)
	
	public void invalidLoginUseEmail() {
		Boolean erroroccur = signin.invalidLoginUsingEmail(prop.getProperty("invalidemail"), prop.getProperty("invalidpassword"));
		Assert.assertTrue(erroroccur);
	}
@Test  (priority=6)
	
	public void invalidLoginUsePhone() {
		Boolean erroroccur = signin.invalidLoginUsingPhoneNo(prop.getProperty("invalidmoblieNo"), prop.getProperty("invalidpassword"));
		Assert.assertTrue(erroroccur);
	}

@Test  (priority=7)

public void invalidLoginUseWrongemailFormat() {
	Boolean erroroccur = signin.wrongemailformat(prop.getProperty("wrongemailFormat"), prop.getProperty("invalidpassword"));
	Assert.assertTrue(erroroccur);
}
@Test  (priority=8)

public void invalidLoginUseWrongPhoneFormat() {
	Boolean erroroccur = signin.wrongPhoneformat(prop.getProperty("wrongmoblieFormat"), prop.getProperty("invalidpassword"));
	Assert.assertTrue(erroroccur);
}
@Test  (priority=9)

public void forgotpasswordUsindInvalidPhoneTest() {
	Boolean erroroccur = signin.forgotpasswordUsindInvalidPhone(prop.getProperty("invalidmoblieNo"));
	Assert.assertTrue(erroroccur);
}

@Test  (priority=10)

public void forgotpasswordUsindInvalidEmailTest() {
	  

	Boolean erroroccur = signin.forgotpasswordUsindInvalidEmail(prop.getProperty("invalidemail"));
	Assert.assertTrue(erroroccur);
}
@Test (priority = 11)
public void forgotPasswordUsingValidEmailInvalidOTPTest() {
	Boolean erroroccurBoolean = signin.forgotPasswordUsingValidEmailInvalidOTP(prop.getProperty("username"),prop.getProperty("invalidOTP"));
	Assert.assertTrue(erroroccurBoolean);
}
}
