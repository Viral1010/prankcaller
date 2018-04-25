package test.config.SeleniumConfig;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public abstract class AbstractPage extends BrowserConfig {

    public int DRIVER_WAIT = 15;

    /**
     * Initialize UserAbstractPage.
     *
     * @param driver .
     */
    public AbstractPage(WebDriver driver) {
        this.driver = (AndroidDriver) driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                DRIVER_WAIT);

        PageFactory.initElements(finder, this);

    }
}
