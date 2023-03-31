package Appium.pageObjects.android;

import Appium.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


//GrandParent AppiumUtils -> AndroidActions -> CartPage
//getformattedAmount
public class CartPage extends AndroidActions {

    AndroidDriver driver;
    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> cartPrice;
    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement cartTotal;

    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement terms;
    @AndroidFindBy(id="android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(className="android.widget.CheckBox")
    private WebElement cartCheckbox;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement cartSubmitButton;


    public double checkCartTotalSum(){
        int cost= cartPrice.size();
        double totalSum = 0;

        for(int i=0; i<cost; i++) {
            String amount = cartPrice.get(i).getText();
            Double productPrice = getFormattedAmount(amount);
            totalSum = totalSum + productPrice;
        }

        return totalSum;
    }

    public Double actualCartTotal(){
        return getFormattedAmount(cartTotal.getText());
    }

    public void acceptTC(){
        longPressAction(terms);
        acceptButton.click();
    }

    public void submitOrder(){
        cartCheckbox.click();
        cartSubmitButton.click();
    }

}
