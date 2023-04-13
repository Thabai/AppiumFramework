package TestUtils;

import Appium.pageObjects.android.FormPage;
import Appium.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BaseTest extends AppiumUtils {

        public AndroidDriver driver;
        public FormPage formPage;
        public AppiumDriverLocalService service;

        @BeforeClass
        public void ConfigureAppium() throws IOException {

            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//Resources//data.properties");
            prop.load(fis);

            String nodeModulePath = prop.getProperty("nodeModulePath");
            System.out.println(nodeModulePath);
            String ipAddress = prop.getProperty("ipAddress");
            String port = prop.getProperty("port");

            service = startAppiumServer(nodeModulePath, ipAddress, Integer.parseInt(port));

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(prop.getProperty("androidDeviceName"));

//            options.setApp(System.getProperty("user.dir") + "//src//test//java//Resources//chromedriver_win32(1)//chromedriver.exe");
//            options.setApp(System.getProperty("user.dir") + "//src//test//java//Resources//ApiDemos-debug.apk");

            options.setApp(System.getProperty("user.dir") + "//src//test//java//Resources//General-Store.apk");

            driver = new AndroidDriver(service.getUrl(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            formPage = new FormPage(driver);
        }

        @AfterClass
        public void TearDown(){
            driver.quit();
            service.stop();
        }
    }
