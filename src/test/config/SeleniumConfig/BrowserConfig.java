package test.config.SeleniumConfig;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import test.config.General.JsonFileConfig;
import test.config.General.Methods;
import test.config.General.PerformAction;
import test.config.General.Verify;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

public class  BrowserConfig {

    public boolean autoLog;
    protected AndroidDriver driver;
    //protected WebDriver driver;
    protected PerformAction performAction;
    protected Verify verify;
    protected String targetBrowser;
    protected String deviceName;
    JsonFileConfig fileConfig = new JsonFileConfig();


    @BeforeTest(alwaysRun = true)
    public void fetchSuiteConfiguration(ITestContext testContext) {
        targetBrowser = testContext.getCurrentXmlTest().getParameter("selenium.browser");
        deviceName = testContext.getCurrentXmlTest().getParameter("deviceName");
        System.out.println("Device : "+deviceName);
        //System.out.println("method name:" + method.getTestMethod().getMethodName());
    }


    @BeforeMethod
    public void browserConfig(Method method) {

        autoLog = fileConfig.getAutoLog();

        //String className = this.getClass().getName();
        String suiteName = method.getName();
        System.out.println("Test name : "+suiteName);

        DesiredCapabilities capability = null;
        String APKFilePath = fileConfig.getAPKFilePath();
        String Browser = fileConfig.getOS();

        if (fileConfig.checkForSauceLab()) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platform", fileConfig.getSauceLabPlatform());
            caps.setCapability("version", fileConfig.getSauceLabVersion());
            caps.setBrowserName(fileConfig.getBrowser());
            String URL = "https://" + fileConfig.getSauceLabUserName() + ":" + fileConfig.getSauceLabAccessKey() + "@ondemand.saucelabs.com:443/wd/hub";
        } else {
            if (Browser.equalsIgnoreCase("Android")) {
                if(suiteName.equalsIgnoreCase("launchApp")){
                    System.out.println("Suite : "+suiteName);
                    DesiredCapabilities caps = DesiredCapabilities.android();
                    caps.setCapability("appiumVersion", "1.4.0");
                    caps.setCapability("deviceName", deviceName);
                    caps.setCapability("udid", deviceName);
                    caps.setCapability("deviceType", fileConfig.getDeviceType());
                    caps.setCapability("deviceOrientation", "portrait");
                    caps.setCapability("browserName", "");
                    caps.setCapability("platformVersion", fileConfig.getPlatformVersion());
                    caps.setCapability("platformName", "Android");
                    caps.setCapability("app", fileConfig.getAPKFilePath());
                    caps.setCapability("appPackage", "tracebust.prank.app");
                    caps.setCapability("appActivity", "tracebust.prank.app.MainActivity");
                    caps.setCapability("noReset", true);
                    try {
                        driver = new AndroidDriver(new URL("http://0.0.0.0:4730/wd/hub"), caps);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                } else if (suiteName.equalsIgnoreCase("prankcaller")){
                    System.out.println("Suite : "+suiteName);
                    DesiredCapabilities caps = DesiredCapabilities.android();
                    caps.setCapability("appiumVersion", "1.4.0");
                    caps.setCapability("deviceName", deviceName);
                    caps.setCapability("udid", deviceName);
                    caps.setCapability("deviceType", fileConfig.getDeviceType());
                    caps.setCapability("deviceOrientation", "portrait");
                    caps.setCapability("browserName", "");
                    caps.setCapability("platformVersion", fileConfig.getPlatformVersion());
                    caps.setCapability("platformName", "Android");
                    caps.setCapability("app", fileConfig.getAPK1FilePath());
                    caps.setCapability("appPackage", "prank.caller.io");
                    caps.setCapability("appActivity", "prank.caller.io.MainActivity");
                    caps.setCapability("noReset", true);
                    try {
                        driver = new AndroidDriver(new URL("http://0.0.0.0:4730/wd/hub"), caps);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                }


            }
        }
        Methods methods = new Methods(driver);

        if (autoLog) {
            methods.log("Open Browser : " + Browser);
        }
        performAction = new PerformAction(driver);
        verify = new Verify(driver);

    }


    @AfterMethod
    public void close() {

        driver.quit();

    }


}
