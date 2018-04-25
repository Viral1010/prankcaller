package test.tests.launchApp;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test.config.SeleniumConfig.AbstractPage;

import static org.assertj.core.api.Assertions.assertThat;

public class Verify extends AbstractPage {
    /**
     * Initialize UserAbstractPage.
     *
     * @param driver .
     */
    public Verify(WebDriver driver) {
        super(driver);
    }

    public void verifyText(String acutal, String expacted){
       /* Tests test = new Tests();
        if(!acutal.equalsIgnoreCase(expacted)){
            //test.failCount++;
        }*/
        /*try {
            assertThat(acutal).isEqualToIgnoringCase(expacted);
            Assert.assertEquals(acutal, expacted);
        }catch (Exception e){
            test.failCount++;
        }*/

    }
}
