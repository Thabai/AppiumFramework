package Appium;

import TestUtils.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerceToast extends BaseTest {

//    @BeforeMethod
//    public void preSetup()
//    {
//        //screen to app home page
//        formPage.setActivity();
//    }

    @Test
    public void fillFormNameErrorValidation() {

        formPage.setGender("female");
        formPage.setCountryName("Australia");
        formPage.submitForm();
        Assert.assertEquals(formPage.checkToastMessage(), "Please enter your name");
    }

    @Test
    public void fillFormNameTestFailure() {

        formPage.submitForm();
        Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
    }

    @Test
    public void fillFormPositiveFlow() {
        formPage.setNameField("Carly Morris");
        formPage.setGender("female");
        formPage.setCountryName("Brazil");
        formPage.submitForm();
        Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
    }


}

