package Appium.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AppiumUtils {

    AppiumDriver driver;

    public AppiumUtils(AppiumDriver driver){
        this.driver = driver;
    }
    public Double getFormattedAmount(String amount){
        Double amountToNumber = Double.parseDouble(amount.substring(1));
        return amountToNumber;

    }

    public void waitForElementToAppear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.attributeContains((element), "text", "Cart"));
    }
}
