package Appium.utils;

import android.annotation.SuppressLint;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


public abstract class AppiumUtils {

    public AppiumDriverLocalService service;
    public AppiumDriverLocalService startAppiumServer(String nodeModulePath, String ipAddress, int port){
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(nodeModulePath)).withIPAddress(ipAddress).usingPort(port).build();
        service.start();
        return service;
    }

    public Double getFormattedAmount(String amount){
        Double amountToNumber = Double.parseDouble(amount.substring(1));
        return amountToNumber;

    }


    @SuppressLint("NewApi")
    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException{

        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8 );
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<>() {
                });
        return data;
    }


    @SuppressLint("NewApi")
    public void waitForElementToAppear(AppiumDriver driver, WebElement element) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(5)).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.attributeContains((element), "text", "Cart"));
    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports//"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}
