package Appium;

import Appium.pageObjects.android.FormPage;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.options.UiAutomator2Options;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class BaseTest {

        public AndroidDriver driver;
        public FormPage formPage;
        public AppiumDriverLocalService service;

        @BeforeClass
        public void ConfigureAppium() throws MalformedURLException {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("C://Users//thaba//AppData//Roaming//npm//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
            service.start();

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("CPhone12");

//            options.setChromedriverExecutable("C://Users//thaba//Desktop//AppiumFramework//src//test//java//Resources//chromedriver_win32(1)//chromedriver.exe");
//        options.setApp("C://Users//thaba//Desktop//AppiumFramework//src//test//java//Resources//ApiDemos-debug.apk");
            options.setApp("C://Users//thaba//Desktop//AppiumFramework//src//test//java//Resources//General-Store.apk");


            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            formPage = new FormPage(driver);
        }

        @AfterClass
        public void TearDown(){
            driver.quit();
            service.stop();
        }
    }
