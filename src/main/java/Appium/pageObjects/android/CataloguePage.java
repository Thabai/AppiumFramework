package Appium.pageObjects.android;

import Appium.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CataloguePage extends AndroidActions {

    AndroidDriver driver;
    public CataloguePage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addProduct;

    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement checkCart;

    @AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
    private WebElement checkIfOnCartPage;

    public void addToCartByIndex(int index){
        addProduct.get(index).click();
    }

    public CartPage checkProductsInCart() throws InterruptedException {
        checkCart.click();
        waitForElementToAppear(checkIfOnCartPage);
        return new CartPage(driver);
    }

}
