package test.tests.prankCaller;

import org.openqa.selenium.WebDriver;
import test.config.SeleniumConfig.AbstractPage;

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
