import org.testng.Assert;
import org.testng.annotations.Test;

public class ImportPageTest extends TestBase{
    LoginPage loginPage;
    HomePage homePage;
    ImportPage importPage;
    ProductCategoryPage productCategoryPage;
    ProductPage productPage;
    CartPage cartPage;
    OrderStatusPage orderStatusPage;
    OrderDetailPage orderDetailPage;
        @Test
            public void BuyProduct() throws InterruptedException{
                driver.launchApp();//Step 1
                LoginPage loginPage = new LoginPage(driver);
                HomePage homePage = new HomePage(driver);
                ImportPage importPage = new ImportPage(driver);
                ProductCategoryPage productCategoryPage = new ProductCategoryPage(driver);
                ProductPage productPage = new ProductPage(driver);
                CartPage cartPage = new CartPage(driver);
                OrderStatusPage orderStatusPage = new OrderStatusPage(driver);
                OrderDetailPage orderDetailPage = new OrderDetailPage(driver);
                Thread.sleep(15000);
                loginPage.LoginTrue();//Step 2
                homePage.ImportPage(); //Step 3
                importPage.ProductCategoryClick();//Step 4
                productCategoryPage.SendKeySearch("360g");
                productCategoryPage.ProductClick();//Step 5
                productPage.AddToCartClick(); //Step 6
                productPage.SendKeyAmount(); //Step 7
                    String str = productPage.getTextAmount();
                    int firstTime = Integer.parseInt(str);
                    System.out.println(firstTime);
                productPage.MinusClick(); //Step 8
                    String afterMinusStr = productPage.getTextAmount();
                    int secondTime = Integer.parseInt(afterMinusStr);
                Assert.assertEquals(secondTime,firstTime-1); //Step 9
                productPage.PlusClick(); //Step 9
                productPage.PlusClick(); //Step 10
                    String afterPlusStr = productPage.getTextAmount();
                    int thirdTime = Integer.parseInt(afterPlusStr);
                Assert.assertEquals(thirdTime,secondTime+2);//Step 11
                productCategoryPage.CartClick(); //Step 12
                String productName = cartPage.getProductName();
                int firstTimeCart = cartPage.getAmount();
                cartPage.MinusClick();//Step 13
                cartPage.MinusClick();//Step 14
                int secondTimeCart = cartPage.getAmount();
                Assert.assertEquals(secondTimeCart,firstTimeCart-2); //Step 15
                cartPage.PlusClick();//Step 15
                int thirdTimeCart = cartPage.getAmount();
                Assert.assertEquals(thirdTimeCart,secondTimeCart+1); //Step 16
                int priceEach = cartPage.getPriceEach();
                int price = cartPage.getPrice();
                Assert.assertEquals(price,priceEach*thirdTimeCart); // Step 17
                cartPage.OrderClick(); //Step 18
                Assert.assertEquals(orderStatusPage.getTextNotification(),"Đặt hàng thành công !");
                orderStatusPage.ClickCloseNotification(); //Step 19
                String priceStatus = orderStatusPage.getPrice();
                String dateStatus = orderStatusPage.getDate();
                String orderId = orderStatusPage.getOrderId();
                orderStatusPage.clickLastestOrder(); //Step 20
                Assert.assertEquals(orderDetailPage.getProductName(),productName); //Step 21
                Assert.assertEquals(orderDetailPage.actualPrice(),priceStatus); //Step 22
                Assert.assertEquals(orderDetailPage.getOrderDate(),dateStatus); //Step 23
                Assert.assertEquals(orderDetailPage.getOrderId(),orderId); //Step 24
                orderDetailPage.CancelOrderClick(); //Step 25
                Assert.assertEquals(orderStatusPage.getTextNotification(),"Đơn hàng đã bị hủy"); //Step 26
                driver.closeApp(); //Step 27
        }
}
