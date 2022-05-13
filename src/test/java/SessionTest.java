import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SessionTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    @Test(dataProvider = "dp")
    public void loginPage(String userName, String passWord,String result) throws InterruptedException, MalformedURLException {
        driver.launchApp();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        Thread.sleep(15000);
        loginPage.EnterUserName(userName);
        loginPage.EnterPassword(passWord);
        loginPage.Login();
            String s = loginPage.notificationText();
            Assert.assertEquals(s,result);

        driver.closeApp();
    }
    @DataProvider
    public Object[][] dp(){
        return new Object[][] {
                new Object[] {"idyap","123","Không tìm thấy thông tin tài khoản. Xin vui lòng thử lại !"},
                new Object[] {"paydi","321","Thông tin đăng nhập tài khoản không đúng. Xin vui lòng thử lại sau !"},
                new Object[] {"","123","Vui lòng nhập tên đăng nhập mật khẩu"},
                new Object[] {"paydi","","Vui lòng nhập tên đăng nhập mật khẩu"},
                new Object[] {"","","Vui lòng nhập tên đăng nhập mật khẩu"},
        };
    }
}
