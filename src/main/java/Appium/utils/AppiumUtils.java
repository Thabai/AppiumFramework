package Appium.utils;

import android.annotation.SuppressLint;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
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

//    @TargetApi(Build.VERSION_CODES.O)
    @SuppressLint("NewApi")
    public void waitForElementToAppear(WebElement element, AppiumDriver driver){
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.attributeContains((element), "text", "Cart"));
    }
}
