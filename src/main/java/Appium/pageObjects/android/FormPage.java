package Appium.pageObjects.android;

import Appium.utils.AndroidActions;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidActions {

    AndroidDriver driver;
    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;
    @AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
    private WebElement maleOption;

    @AndroidFindBy(id="android:id/text1")
    private WebElement countryName;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement submitButton;

    @AndroidFindBy(xpath="(//android.widget.Toast)[1]")
    private WebElement toastCheck;

    public void setNameField(String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender){
        if(gender.contains("female"))
            femaleOption.click();
        else
            maleOption.click();
    }

    public void setCountryName(String country){
        countryName.click();
        findElementScrollAction(country);

    }

    public CataloguePage submitForm(){
        submitButton.click();
        return new CataloguePage(driver);
    }

    public String checkToastMessage() {

        return toastCheck.getAttribute("name");
    }

    public void setActivity()
    {
        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity");
        driver.startActivity(activity);
    }
}
