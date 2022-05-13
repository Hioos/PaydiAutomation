import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class LoginPage extends PageBase  {
    public LoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    @AndroidFindBy(accessibility = "btnusername")
    MobileElement UserName;
    @AndroidFindBy(accessibility = "btnpassword")
    MobileElement Password;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup")
    MobileElement Login;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView")
    MobileElement Notification_Text;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup")
    MobileElement Notification_Close;
    public void EnterUserName(String name){
        clear(UserName);
        sendKeys(UserName,name);
    }
    public void EnterPassword(String pass){
        clear(Password);
        sendKeys(Password,pass);
    }
    public void Login(){
        click(Login);
    }
    public String notificationText(){
       return getText(Notification_Text);
    }

    public void LoginTrue(){
        clear(UserName);
        sendKeys(UserName,"SmoKingCat");
        clear(Password);
        sendKeys(Password,"123");
        click(Login);
        int s =driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup")).size();
        int txt =driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView")).size();
        if(s > 0 && txt > 0){
            System.out.println("true");
            click(Notification_Close);
            sendKeys(Password,"0000");
            click(Login);
        }
    }
    public boolean isDisplayed(){
        return isDisplayed(UserName);
    }
}
