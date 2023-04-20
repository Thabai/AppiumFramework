package Appium;

import Appium.pageObjectsAndroid.FormPage;
import TestUtils.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerceToastTest extends BaseTest {
    public FormPage formPage;

    @Test
    public void fillFormNameErrorValidation() {

        formPage = new FormPage(driver);
        formPage.setGender("female");
        formPage.setCountryName("Australia");
        formPage.submitForm();
        Assert.assertEquals(formPage.checkToastMessage(), "Please enter your name");
    }

    @Test
    public void fillFormNameTestFailure() {

        formPage = new FormPage(driver);
        formPage.submitForm();
        Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
    }

    @Test
    public void fillFormPositiveFlow() {
        formPage = new FormPage(driver);
        formPage.setNameField("Carly Morris");
        formPage.setGender("female");
        formPage.setCountryName("Brazil");
        formPage.submitForm();
//
//        Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
    }


}

