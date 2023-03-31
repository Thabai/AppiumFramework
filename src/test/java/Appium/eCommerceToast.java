package Appium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerceToast extends BaseTest{

    @Test
    public void fillFormNameErrorValidation() throws InterruptedException{

        formPage.setGender("female");
        formPage.setCountryName("Australia");
        formPage.submitForm();
        Assert.assertEquals(formPage.checkToastMessage(), "Please enter your name");

    }

    @Test
    public void fillFormPositiveFlow() throws InterruptedException{
        formPage.setNameField("Carly Morris");
        formPage.setGender("female");
        formPage.setCountryName("Australia");
        formPage.submitForm();
        Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
    }

}

