package Appium;

import Appium.pageObjectsAndroid.CartPage;
import Appium.pageObjectsAndroid.CataloguePage;
import Appium.pageObjectsAndroid.FormPage;
import TestUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class eCommerceE2ETest extends BaseTest {


        @BeforeMethod(alwaysRun = true)
        public void preSetup() throws InterruptedException {
//            screen to app home page
            formPage.setActivity();
        }

        //json hashmap
        @DataProvider
        public Object[][] getData() throws IOException {
            List<HashMap<String, String>> data = getJsonData(System.getProperty( "user.dir") + "//src//test//java//TestData//eCommerce.json");
            return new Object[][] { {data.get(0)}, {data.get(1)} };
        }



        @Test(dataProvider = "getData", groups = {"Smoke"})
        public void E2ETest(HashMap<String,String> input) throws InterruptedException {
            formPage = new FormPage(driver);

            CataloguePage cataloguePage = addCustomerInfo(input.get("name"), input.get("gender"), input.get("country"));
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

        private CataloguePage addCustomerInfo(String name, String gender, String country) throws InterruptedException {
            formPage = new FormPage(driver);
            formPage.setNameField(name);
            formPage.setGender(gender);
            formPage.setCountryName(country);
            return formPage.submitForm();
        }

}