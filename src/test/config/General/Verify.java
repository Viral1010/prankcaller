package test.config.General;

import org.openqa.selenium.WebDriver;
import test.config.SeleniumConfig.AbstractPage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by user on 06-Dec-16.
 */
public class Verify extends AbstractPage {


    /**
     * Initialize UserAbstractPage.
     *
     * @param driver .
     */
    public Verify(WebDriver driver) {
        super(driver);
    }


    public void elementIsDisplayed(String fileName, String elementName) {
        boolean bool;
        Methods methods = new Methods(driver);
        if (autoLog) {
            methods.log("VerifyEvents " + elementName + " is displayed");
        }
        bool = methods.findElement(fileName, elementName).isDisplayed();

        assertThat(methods.findElement(fileName, elementName).isDisplayed()).isEqualTo(true);
    }


}
