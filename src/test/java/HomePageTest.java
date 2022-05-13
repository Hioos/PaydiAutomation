import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{
    HomePage homePage;
    LoginPage loginPage;
    PaymentPage payment;

        @Test
            public void PointGiveAway() throws InterruptedException {
            driver.launchApp();//Step 1
            homePage = new HomePage(driver);
            loginPage = new LoginPage(driver);
            Thread.sleep(15000);
            loginPage.LoginTrue();//Step 2
            homePage.PointGAclick();//Step 3
            homePage.SendKeyPointGANumberTxt(); //Step 4
            homePage.SendKeyPointGAOrderPriceTxt(); //Step 5
            homePage.SendKeyPointGAamount(); //Step 6
            homePage.FinishPointGA(); // Step 7
            homePage.NotificationCloseClick();// Step 8
            driver.closeApp();//Step 9
        }
        @Test
            public void QRPay() throws InterruptedException {
            driver.launchApp(); //Step 1
            homePage = new HomePage(driver);
            loginPage = new LoginPage(driver);
            Thread.sleep(15000);
            loginPage.LoginTrue(); //Step 2
            homePage.QRPayClick(); //Step 3
            homePage.PriceTxtbox(); //Step 4
            homePage.VNPAYQR(); //Step 5
            homePage.NextQR();
            homePage.isDisplayedChrome(); //Step 6
            driver.closeApp(); //Step 7
        }
        @Test(dataProvider = "PaymentPercentage")
            public void Payment(String orderPrice,String moneyReceived, String percentage, String number, String result) throws  InterruptedException{
            driver.launchApp();//Step 1
            homePage = new HomePage(driver);
            loginPage = new LoginPage(driver);
            payment = new PaymentPage(driver);
            Thread.sleep(15000);
            loginPage.LoginTrue(); //Step 2
            homePage.PaymentClick(); //Step 3
            payment.SendKeyOrderPrice(orderPrice); //Step 4
            payment.SendKeyMoneyReceived(moneyReceived); //Step 5
            payment.discountClick(); //Step 6
            payment.SendKeyPercentDiscount(percentage); //Step 7
            payment.finishDiscount(); //Step 8
            payment.SendKeyNumberTextbox(number); //Step 9
            payment.MethodCash(); //Step 10
            if(result == "true"){
                payment.isSuccess(); //Step 11.1
            }
            else {
                String s = payment.getTextNotification(); //Step 12.1
                Assert.assertEquals(s, result); //Step 12.2
            }
                driver.closeApp(); //Step 13
        }
        @DataProvider
            public Object[][] PaymentPercentage(){
            return new Object[][]{
                    new Object[]{payment.randomPrice(),"60000",payment.randomPercentage(),payment.randomNumberGen(),"true"},
                    new Object[]{"","50000",payment.randomPercentage(),payment.randomNumberGen(),"Vui lòng nhập tổng số tiền"},
                    new Object[]{payment.randomPrice(),"999",payment.randomPercentage(),payment.randomNumberGen(),"Tiền nhập vào không hợp lệ. Xin vui lòng thử lại !"},
                    new Object[]{payment.randomPrice(),"60000",payment.randomPercentage(),"053"+payment.randomNumberGen(),"Số điện thoại không hợp lệ. Xin vui lòng thử lại !"},
                    new Object[]{payment.randomPrice(),"",payment.randomPercentage(),payment.randomNumberGen(),"Vui lòng nhập số tiền nhận từ khách hàng"},
                    new Object[]{payment.randomPrice(),"60000",payment.randomPercentage(),"","true"}
            };
        }
}
