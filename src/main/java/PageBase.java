import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class PageBase {
    AppiumDriver driver;
    public static final long WAIT = 10;
    public PageBase(AppiumDriver appiumDriver ){
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver),this);
        driver = appiumDriver;
    }
    public void waitForVisibility(MobileElement element){
        WebDriverWait wait = new WebDriverWait(driver,WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void clear(MobileElement element){
        waitForVisibility(element);
        element.clear();
    }
    public void click(MobileElement element){
        waitForVisibility(element);
        element.click();
    }
    public void sendKeys(MobileElement element,String text){
        waitForVisibility(element);
        element.sendKeys(text);
    }
    public String getText(MobileElement element){
        waitForVisibility(element);
        return element.getText();
    }
    public boolean isDisplayed(MobileElement element){
        return element.isDisplayed();
    }
    public void swipeScreen() {
        Dimension dim = driver.manage().window().getSize();
        int start_x = (int) (dim.width * 0.5);
        int start_y = (int) (dim.height * 0.8);
        int end_x = (int) (dim.width * 0.5);
        int end_y = (int) (dim.width * 0.4);
        TouchAction touch = new TouchAction(driver);
        touch.press(PointOption.point(start_x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(end_x,end_y)).release().perform();
    }
    public void swipeTime() {
        Random rd = new Random();
        int swipeTime = rd.nextInt(5-1+1)+1;
//	      System.out.println(swipeTime);
        for(int i = 0 ; i <= swipeTime ; i++) {
            swipeScreen();
        }
    }

}
