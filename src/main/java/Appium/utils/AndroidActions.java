package Appium.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumUtils{

    AndroidDriver driver;
    public AndroidActions(AndroidDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void longPressAction(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",((RemoteWebElement) element).getId(),
                "duration",2000));
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down", "percent", 3.0
            ));
        } while (canScrollMore);
    }

    public void findElementScrollAction(String element) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+element+"\"));")).click();
    }

    public void swipeAction (WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

    public void dragDropAction(WebElement source) throws InterruptedException {
        ((JavascriptExecutor)driver). executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)source).getId(),
                "endX", 840,
                "endY", 732
        ));
        Thread.sleep(3000);
    }


}
