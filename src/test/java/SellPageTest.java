import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SellPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    SellPage sellPage;
    PaymentPage paymentPage;
        @Test(testName = "Search", dataProvider = "dp")
            public void Search(String search,Integer result) throws InterruptedException {
                driver.launchApp(); //Step 1
                LoginPage loginPage = new LoginPage(driver);
                HomePage homePage = new HomePage(driver);
                SellPage sellPage = new SellPage(driver);
                Thread.sleep(15000);
                loginPage.LoginTrue(); //Step 2
                homePage.SellPage(); //Step 3
                sellPage.sendKeySearch(search);// Step 4
                Thread.sleep(6000);
                if(result == 1) {
                    sellPage.isDisplayedProduct(); //Step 5
                }
                else{
                    Assert.assertEquals(sellPage.getPageFooter(),"Chưa có dữ liệu."); //Step 6
                }
                driver.closeApp(); //Step 7
        }
        @Test
            public void QuickSell() throws InterruptedException {
                driver.launchApp(); //Step 1
                LoginPage loginPage = new LoginPage(driver);
                HomePage homePage = new HomePage(driver);
                SellPage sellPage = new SellPage(driver);
                Thread.sleep(15000);
                loginPage.LoginTrue(); //Step 2
                homePage.SellPage(); //Step 3
                sellPage.QuickSellClick(); //Step 4
                sellPage.SendKeyQuickSellPrice(); //Step 5
                String quickSellPrice = sellPage.getQuickSellPrice();
                sellPage.AddClick(); //Step 6
                Assert.assertEquals(sellPage.getPrice(),quickSellPrice); //Step 7
                driver.closeApp(); //Step 8
        }
        @Test
            public void SellProduct() throws InterruptedException {
                driver.launchApp(); //Step 1
                LoginPage loginPage = new LoginPage(driver);
                HomePage homePage = new HomePage(driver);
                SellPage sellPage = new SellPage(driver);
                PaymentPage paymentPage = new PaymentPage(driver);                Thread.sleep(8000);
                Thread.sleep(15000);
                loginPage.LoginTrue(); //Step 2
                homePage.SellPage(); //Step 3
                int productPrice = sellPage.getProductPrice();
                sellPage.PlusClick(); //Step 4
//                sellPage.MinusClick(); //Step 5
                int amount = sellPage.getAmount();
                int expectedPrice = productPrice * amount;
                int actualPrice = sellPage.getPriceInInt();
                Assert.assertEquals(actualPrice,expectedPrice); //Step 6
                sellPage.SellClick(); //Step 7
                sellPage.PaymentClick(); //Step 8
                int paymentPrice = paymentPage.getOrderPrice();
                Assert.assertEquals(paymentPrice,actualPrice); //Step 9
                driver.closeApp();//Step 10
        }
        @DataProvider
            public Object[][] dp(){
                return new Object[][]{
                        new Object[]{"Town",1},
                        new Object[]{"xyzsynchro",0}
                };
    }
}
