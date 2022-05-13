import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class AccountPageTest extends TestBase {
    HomePage homePage;
    AccountPage accountPage;
    UpdateInfoPage updateInfoPage;
    LoginPage loginPage;
    ChargePoint chargePoint;
    AddMemberCardPage addMemberCardPage;
    MemberCardPage memberCardPage;
    RatePage rate;
    ChangePasswordPage changePasswordPage;

    @Test(testName = "Update Info",dataProvider = "dp")
    public void updateInfo(String storeNumber, String managerName, String managerNumber, String address,Integer result) throws InterruptedException, MalformedURLException {
        driver.launchApp();//Step 1
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        updateInfoPage = new UpdateInfoPage(driver);
        accountPage = new AccountPage(driver);
        Thread.sleep(15000);
        loginPage.LoginTrue();//Step Group
        homePage.AccountPage();//Step 2
        accountPage.UpdateInfoClick();//Step 3
        updateInfoPage.SendKeyStoreNumber(storeNumber);//Step 4
        updateInfoPage.SendKeyManager(managerName);//Step 5
        updateInfoPage.SendKeyManagerNumber(managerNumber);//Step 6
        updateInfoPage.chooseCity();//Step 7
        updateInfoPage.chooseDistrict();//Step 8
        updateInfoPage.chooseWard();// Step 9
        updateInfoPage.SendKeyAddress(address);//Step 10
        updateInfoPage.Update();//Step 11
        if(result == 1) {
            Assert.assertEquals(accountPage.getNotificationText(),"Thành công");//Step 12.1
        }
        else{
            Assert.assertNotEquals(accountPage.getNotificationText(),"Thành công");//Step 13.1
        }
        driver.closeApp();//Step 14
    }
    @Test(testName = "Log Out")
    public void logOut() throws InterruptedException {
        driver.launchApp(); //Step 1
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
        Thread.sleep(15000); //Step 2
        loginPage.LoginTrue(); //Step 3
        homePage.AccountPage(); //Step 4
        Thread.sleep(5000);
        accountPage.swipeScreen();
        accountPage.LogOut(); //Step 5
        accountPage.ConfirmNotification(); //Step 6
        loginPage.isDisplayed(); //Step 7
    }
    @Test (testName = "chargePoint",dataProvider = "dpcharge")
    public void chargePoint(String amount,Integer result) throws InterruptedException {
        driver.launchApp();//Step 1
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
        chargePoint = new ChargePoint(driver);
        Thread.sleep(15000);
        loginPage.LoginTrue(); //Step 2
        homePage.AccountPage(); //Step 3
        String mPoint = accountPage.getmPoint(); //Step 4
        accountPage.clickMpointCharge(); //Step 5
        String mPointCharge = chargePoint.CurrentPoint(); //Step 6
        Assert.assertEquals(mPointCharge,mPoint);//Step 7
        chargePoint.ChargePointTxt(amount);//Step 8
        chargePoint.ChargePointNextClick();//Step 9
        if(result == 0 ){
            String notitxt = chargePoint.ChargePointNotiTxt();//Step 10.1
            Assert.assertEquals(notitxt,"Vui lòng nhập số điểm nạp"); //Step 10.2
        }
        else{
            chargePoint.isDisplayedChrome(); //Step 11.1
        }
        driver.closeApp(); //Step 12
    }
    @Test(testName = "Membership Card", dataProvider = "card")
    public void CreateCard(String email,String amount,String result) throws InterruptedException {
        driver.launchApp(); //Step 1
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
        memberCardPage = new MemberCardPage(driver);
        addMemberCardPage = new AddMemberCardPage(driver);
        Thread.sleep(15000);
        loginPage.LoginTrue();//Step 2
        homePage.AccountPage();//Step 3
        accountPage.clickCardManagement();//Step 4
        memberCardPage.isCurrenCardDisplayed(); //Step 5
        memberCardPage.AddCardClick(); //Step 6
        addMemberCardPage.sendKeyEmail(email); //Step 7
        addMemberCardPage.sendKeyAmount(amount); //Step 8
        addMemberCardPage.sendCardClick(); //Step 9
        String actualresult = addMemberCardPage.NotificationTxt(); //Step 10
        Assert.assertEquals(actualresult,result); //Step 11
        driver.closeApp(); //Step 12
    }
    @Test(testName ="changePass",dataProvider = "changePass")
    public void ChangePassword(String currentPass,String newPass,String confirmNewPass,String result) throws InterruptedException {
        driver.launchApp();//Step 1
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
        changePasswordPage = new ChangePasswordPage(driver);
        Thread.sleep(15000); //Step 2
        loginPage.LoginTrue(); //Step 3
        homePage.AccountPage(); //Step 4
        Thread.sleep (3000);
        accountPage.ChangePasswordClick(); //Step 5
        changePasswordPage.sendKeyCurrentPassword(currentPass); //Step 6
        changePasswordPage.sendKeyNewPassword(newPass); //Step 7
        changePasswordPage.sendKeyConfirmNewPassword(confirmNewPass); //Step 8
        changePasswordPage.clickChangePassConfirm(); //Step 9
        if(result =="true"){
            loginPage.isDisplayed(); //Step 10.1
        }
        else {
            String actualResult = changePasswordPage.getNotificationText();
            Assert.assertEquals(actualResult, result); //Step 11.1
        }
        driver.closeApp(); //Step 12
        }
    @Test(testName = "Rating", dataProvider = "feedback")
    public void Rating(String content,Integer result) throws InterruptedException {
        driver.launchApp(); //Step 1
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
        rate = new RatePage(driver);
        Thread.sleep(8000);
        loginPage.LoginTrue();//Step 2
        homePage.AccountPage();//Step 3
        accountPage.RatingClick();//Step 4
        rate.StarRate();//Step 5
        rate.sendKeyFeedback(content);//Step 6
        rate.clickSend();//Step 7
        if(result == 0){
            String d = rate.getNotification();
            Assert.assertEquals(d,"Vui lòng điền nội dung đánh giá"); //Step 8.1
        }
        else{
            String s = accountPage.getNotificationText();
            Assert.assertEquals(s,"Đánh giá thành công"); //Step 9.1
        }
        driver.closeApp(); //Step 10
    }
    @DataProvider
    public Object[][] changePass(){
        return new Object[][]{
                new Object[]{"","laks","laks","Hãy nhập đầy đủ thông tin"},
                new Object[]{"123","laks","skal","Mật khẩu xác nhận không đúng"},
                new Object[]{"123","","skal","Hãy nhập đầy đủ thông tin"},
                new Object[]{"","","","Hãy nhập đầy đủ thông tin"},
                new Object[]{"skas","laks","laks","Mật khẩu không chính xác"},
                new Object[]{"123","0000","0000","true"},
                new Object[]{"0000","123","123","true"}

        };
    }
    @DataProvider
    public Object[][] feedback(){
        return new Object[][]{
                new Object[]{"",0},
                new Object[]{"good",1},
        };
    }
    @DataProvider
    public Object[][] card(){
        return new Object[][]{
                new Object[]{"zx","10","Vui lòng nhập đúng định dạng email"},
                new Object[]{"v@gm.com","xyzsynchro","Vui lòng nhập số lượng"},
                new Object[]{"v@gm.com","","Vui lòng nhập số lượng"},
                new Object[]{"","10","Vui lòng nhập đúng định dạng email"},
                new Object[]{"v@gm.com","10","Thành công, sau khi tạo mã thành công sẽ gửi danh sách mã đến mail đã nhập"},
                new Object[]{"","","Vui lòng nhập đúng định dạng email"},
        };
    }
    @DataProvider
    public Object[][] dpcharge(){
        return new Object[][]{
                new Object[]{"0",0},
                new Object[]{"10000",1},
                new Object[]{"150000",1}
    };}
    @DataProvider
    public Object[][] dp(){
        return new Object[][]{
                new Object[]{updateInfoPage.randomNumberGen(),updateInfoPage.randomCharGen(),updateInfoPage.randomNumberGen(),"9 ngo 112",1},
                new Object[]{updateInfoPage.randomNumberGen(),"",updateInfoPage.randomNumberGen(),"9 ngo 113",0},
                new Object[]{"",updateInfoPage.randomCharGen(),updateInfoPage.randomNumberGen(),"9 ngo 114",0},
                new Object[]{updateInfoPage.randomNumberGen(),"",updateInfoPage.randomNumberGen(),"9 ngo 115",0},
                new Object[]{updateInfoPage.randomNumberGen(),updateInfoPage.randomCharGen(),"","9 ngo 116",0},
                new Object[]{"0"+updateInfoPage.randomNumberGen(),updateInfoPage.randomCharGen(),updateInfoPage.randomNumberGen(),"9 ngo 117",0},
                new Object[]{updateInfoPage.randomNumberGen(),updateInfoPage.randomCharGen(),"0"+updateInfoPage.randomNumberGen(),"9 ngo 11h",0},
                new Object[]{"","","","",0},
        };
    }

}

