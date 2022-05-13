import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static AppiumDriver driver;
    @BeforeTest
    public static void Android_Setup() throws MalformedURLException{
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "sdk_gphone_x86");
        cap.setCapability("uid", "emulator-5554");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "11");
        cap.setCapability("appPackage", "com.paydi");
        cap.setCapability("appActivity", "com.paydi.MainActivity");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url, cap);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @AfterSuite
    public static void tearDown(){
        if(null!=driver){
            driver.quit();
        }
    }
}
