package Appium;

import Appium.pageObjects.android.CartPage;
import Appium.pageObjects.android.CataloguePage;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.StartsActivity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


    public class eCommerceE2ETest extends BaseTest {

        @BeforeMethod
        public void preSetup()
        {
            //screen to app home page
            formPage.setActivity();
        }

        @Test(dataProvider = "getData")
        public void eCommerceTest3(String name, String gender, String country) throws InterruptedException {

            CataloguePage cataloguePage = addCustomerInfo(name, gender, country);
            CartPage cartPage = addProductsToCart(cataloguePage);

            Assert.assertEquals(cartPage.checkCartTotalSum(), cartPage.actualCartTotal());

            cartPage.acceptTC();
            cartPage.submitOrder();

        }


        private static CartPage addProductsToCart(CataloguePage cataloguePage) throws InterruptedException {
            cataloguePage.addToCartByIndex(0);
            cataloguePage.addToCartByIndex(0);
            return cataloguePage.checkProductsInCart();
        }

        private CataloguePage addCustomerInfo(String name, String gender, String country) {
            formPage.setNameField(name);
            formPage.setGender(gender);
            formPage.setCountryName(country);
            return formPage.submitForm();
        }

        @DataProvider
        public Object[][] getData(){
//            return new Object[][] { {"Carly Morris", "female", "Australia"} };
            return new Object[][] { {"Carly Morris", "female", "Australia"},{"Bob Dylan", "male", "Argentina"} };
        }


}