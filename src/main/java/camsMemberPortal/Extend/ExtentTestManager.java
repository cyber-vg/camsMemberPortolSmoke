package camsMemberPortal.Extend;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static synchronized ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    public static synchronized void setTest(ExtentTest extentTest) {
        extentTestThreadLocal.set(extentTest);
    }

    public static synchronized void removeTest() {
        extentTestThreadLocal.remove();
    }
}
