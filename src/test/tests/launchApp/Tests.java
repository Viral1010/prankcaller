package test.tests.launchApp;


import io.appium.java_client.android.AndroidKeyCode;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.config.General.JsonFileConfig;
import test.config.General.screenshot;
import test.config.SeleniumConfig.BrowserConfig;

import java.util.ArrayList;


public class Tests extends BrowserConfig {


    //@Test
    public void demo() {

        System.out.println("Test start.");
    }

    @Test(priority = 1)
    public void prankcaller() {
        String FileName = "prankCaller";
        boolean startStatus = true;

        String mostPopularPrankName = "You Got The Drugs?";

        screenshot screen = new screenshot(driver);
        JsonFileConfig fileConfig = new JsonFileConfig();
        Steps step = new Steps();
        test.tests.prankCaller.Verify verify = new test.tests.prankCaller.Verify(driver);
        int failCount = 0;

        String status;
        ArrayList<String> screenshotlist = new ArrayList<>();
        String type = fileConfig.getDeviceType();
        String model = fileConfig.getDeviceName();
        String version = fileConfig.getPlatformVersion();
        String OS = fileConfig.getOS();
        ArrayList<String> stepList = new ArrayList<>();

        performAction.pause(20);

        performAction.pause(10);

        try {
            //Step 1 : access detels.
            performAction.click(FileName, "accessYourContact");

            performAction.pause(6);

            performAction.click(FileName, "accessYourContact");

            performAction.pause(6);
            performAction.click(FileName, "accessYourContact");

            performAction.pause(6);
            performAction.click(FileName, "accessYourContact");

            performAction.pause(10);
        } catch (Exception e) {
            startStatus = false;
        }

        try {
            performAction.click(FileName, "appPermitionButton");
            performAction.pause(2);
            driver.pressKeyCode(AndroidKeyCode.BACK);
            performAction.pause(20);

            performAction.pause(20);

        } catch (Exception e) {
        }
        try {
            if(startStatus==true) {
                performAction.pause(20);

                performAction.pause(20);

                performAction.pause(15);
                screenshotlist.add(screen.takeScreenShot(deviceName, "1"));
                stepList.add("Open Application.");
                performAction.pause(5);

                //Step 2 : Swipe steps.
                step.swipRightToLeft(driver);
                stepList.add("Swipe step 1.");
                performAction.pause(15);

                step.swipRightToLeft(driver);
                stepList.add("Swipe step 2.");
                performAction.pause(15);

                //Step : 3 : Click on get start button.
                performAction.click(FileName, "getStartedButton");
            }
        } catch (Exception e) {
        }
        try {
            stepList.add("Click on Get Staeted button.");
            performAction.pause(15);
            screenshotlist.add(screen.takeScreenShot(deviceName, "2"));
            performAction.pause(10);

            performAction.pause(20);

            //Step : 4 : Click on most popular parank by name.
            performAction.click(FileName, "mostPopularPrankText");
            //performAction.clickOnElementFromList(FileName, "mostPopularPrankName", mostPopularPrankName);
            stepList.add("Click on most popular prank.");
            performAction.pause(10);
            screenshotlist.add(screen.takeScreenShot(deviceName, "3"));

            performAction.androidScrollToElementManually(550, 250);
            performAction.pause(2);
            performAction.androidScrollToElementManually(550, 250);
            performAction.pause(2);

            /*performAction.click(FileName,"friendPhoneNumber");
            performAction.pause(2);*/
            performAction.sendKeys(FileName, "friendPhoneNumber", "5033267667");
            stepList.add("Enter friend phone number.");
            performAction.pause(3);
            driver.hideKeyboard();
            performAction.pause(3);

            /*performAction.androidScrollToElementManually(550, 250);
            performAction.pause(2);
            performAction.androidScrollToElementManually(550, 250);*/
            performAction.pause(5);

            performAction.sendKeys(FileName, "fakeCallerIDNumber", "5035451234");
            stepList.add("Enter faker ID number.");
            performAction.pause(3);
            driver.hideKeyboard();
            performAction.pause(3);

            performAction.androidScrollToElementManually(550, 250);
            performAction.pause(2);
            performAction.androidScrollToElementManually(550, 250);
            performAction.pause(5);
            screenshotlist.add(screen.takeScreenShot(deviceName, "4"));

            performAction.click(FileName, "placeCallButton");
            performAction.pause(1);
            performAction.click(FileName, "placeCallButton");
            stepList.add("Click on place call button.");
            performAction.pause(10);
            screenshotlist.add(screen.takeScreenShot(deviceName, "5"));
        } catch (Exception e) {
            e.printStackTrace();
            failCount++;
        }

        try {

            if ("Connected".equalsIgnoreCase(performAction.getText(FileName, "connectText"))) {
                System.out.println("pass");
            } else {
                System.out.println("Fail");
                failCount++;
            }

            performAction.pause(15);
        } catch (Exception e) {
            e.printStackTrace();
            failCount++;
        }

        try {
            performAction.pause(10);

            performAction.click(FileName, "endCallButton");
            performAction.pause(1);
            //performAction.click(FileName, "endCallButton");
            stepList.add("Call end.");
            screenshotlist.add(screen.takeScreenShot(deviceName, "6"));

            //Step : 4 : Verify Home page and details.
            performAction.pause(15);


        } catch (Exception e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            performAction.pause(15);
            performAction.click(FileName, "menuButton");
            performAction.pause(15);

            /*performAction.click(FileName,"menuButton");
            performAction.pause(5);*/

            performAction.click(FileName, "historyMenuButton");
            performAction.pause(15);

            performAction.pause(15);
            if ("(503) 326-7667".contains(performAction.getText(FileName, "historyCallConnectionNumber"))) {
                System.out.println("Pass");
            } else {
                System.out.println("Fail");
                failCount++;
            }

            performAction.pause(5);

        } catch (Exception e) {
            e.printStackTrace();
            failCount++;
        }

        if (failCount >= 1) {
            status = "fail";
        } else {
            status = "success";
        }

        System.out.println("Status : " + status);
        System.out.println("Type : " + type);
        System.out.println("Model : " + deviceName);
        System.out.println("Version : " + version);
        System.out.println("OS : " + OS);
        System.out.println("log : " + stepList.toString());
        System.out.println("Screenshots : " + screenshotlist.toString());

        String insertQuery = "INSERT INTO pandora.emulator_log(status, type, model, version, os, log, screenshots) VALUES ('" + status + "','" + type + "','" + model + "','" + version + "','" + OS + "','" + stepList.toString() + "','" + screenshotlist.toString() + "')";
        System.out.println("Query : " + insertQuery);

        step.addConnectionAndDataInDB(insertQuery);

        performAction.pause(5);
        //driver.removeApp("prank.caller.io");
        performAction.pause(10);
        if (failCount >= 1) {
            Assert.fail();
        }

    }

    @Test(priority = 2)
    public void launchApp() {
        String FileName = "launchApp";
        screenshot screen = new screenshot(driver);
        JsonFileConfig fileConfig = new JsonFileConfig();
        Steps step = new Steps();
        Verify verify = new Verify(driver);
        int failCount = 0;

        String status;
        ArrayList<String> screenshotlist = new ArrayList<>();
        String type = fileConfig.getDeviceType();
        String model = fileConfig.getDeviceName();
        String version = fileConfig.getPlatformVersion();
        String OS = fileConfig.getOS();
        ArrayList<String> stepList = new ArrayList<>();

        performAction.pause(20);

        performAction.pause(10);

        try {
            //Step 1 : access detels.
            screenshotlist.add(screen.takeScreenShot(deviceName, "1"));

            performAction.click(FileName, "accessYourContact");

            performAction.pause(6);

            performAction.click(FileName, "accessYourContact");

            performAction.pause(6);
            performAction.click(FileName, "accessYourContact");

            performAction.pause(6);
            performAction.click(FileName, "accessYourContact");
            performAction.pause(10);
        } catch (Exception e) {
        }

        try {
            performAction.click(FileName, "appPermitionButton");
            performAction.pause(2);
            driver.pressKeyCode(AndroidKeyCode.BACK);
        } catch (Exception e) {
        }

        try {

            performAction.pause(15);

            performAction.pause(10);

            performAction.pause(15);
            stepList.add("Open Application.");

            //Step : 2 : place call
            performAction.pause(5);
            screenshotlist.add(screen.takeScreenShot(deviceName, "2"));
            performAction.pause(1);
            performAction.click(FileName, "phoneNumber");
            performAction.pause(2);
            performAction.clear(FileName, "phoneNumber");
            performAction.pause(2);
            performAction.click(FileName, "destNumberBlankOkButton");
            performAction.pause(2);
            performAction.sendKeys(FileName, "phoneNumber", "15033267667");

            performAction.pause(5);

            performAction.sendKeys(FileName, "callerID", "5035451234");
            performAction.pause(1);
            driver.hideKeyboard();
            performAction.pause(3);

            performAction.androidScrollToElementManually(550, 250);
            performAction.pause(2);
            performAction.androidScrollToElementManually(550, 250);
            performAction.pause(2);

            screenshotlist.add(screen.takeScreenShot(deviceName, "3"));

            performAction.click(FileName, "callNow");
            performAction.pause(1);
            performAction.click(FileName, "callNow");
            stepList.add("Place Call.");

            //Step : 3 : End Call
            performAction.pause(15);

            if (!"15033267667".equalsIgnoreCase(performAction.getText(FileName, "ConnectionNumber"))) {
                System.out.println("Fail");
                failCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            failCount++;
        }

        try {

            performAction.pause(5);

            screenshotlist.add(screen.takeScreenShot(deviceName, "4"));

            performAction.click(FileName, "endCallButton");
            stepList.add("Call end.");

            //Step : 4 : Verify Home page and details.
            performAction.pause(15);

            performAction.androidScrollToElementManually(200, 650);
            performAction.pause(4);
            performAction.androidScrollToElementManually(200, 650);
            performAction.pause(4);

            if ("NUMBER TO CALL".equalsIgnoreCase(performAction.getText(FileName, "numberToCallText"))) {
                System.out.println("pass");
            } else {
                System.out.println("Fail");
                failCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            failCount++;
        }

        try {
            performAction.pause(2);

            if (Integer.valueOf(performAction.getText(FileName, "integerNumberText")) > 0) {
                System.out.println("Pass");
            } else {
                System.out.println("Fail");
                failCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            failCount++;
        }

        try {
            screenshotlist.add(screen.takeScreenShot(deviceName, "5"));
            performAction.pause(4);
            stepList.add("Verify Home screen.");

            performAction.click(FileName, "menuButton");
            performAction.pause(2);

            performAction.click(FileName, "callHistoryMenuButton");
            performAction.pause(10);
            screenshotlist.add(screen.takeScreenShot(deviceName, "6"));


            if ("(503) 326-7667".contains(performAction.getText(FileName, "historyCallConnectionNumber"))) {
                System.out.println("Pass");
            } else {
                System.out.println("Fail");
                failCount++;
            }

            performAction.pause(10);
            stepList.add("Verify call history.");

        } catch (Exception e) {
            e.printStackTrace();
            failCount++;
        }

        if (failCount >= 1) {
            status = "fail";
        } else {
            status = "success";
        }

        System.out.println("Status : " + status);
        System.out.println("Type : " + type);
        System.out.println("Model : " + deviceName);
        System.out.println("Version : " + version);
        System.out.println("OS : " + OS);
        System.out.println("log : " + stepList.toString());
        System.out.println("Screenshots : " + screenshotlist.toString());

        String insertQuery = "INSERT INTO pandora.emulator_log(status, type, model, version, os, log, screenshots) VALUES ('" + status + "','" + type + "','" + model + "','" + version + "','" + OS + "','" + stepList.toString() + "','" + screenshotlist.toString() + "')";
        System.out.println("Query : " + insertQuery);

        step.addConnectionAndDataInDB(insertQuery);

        if (failCount >= 1) {
            status = "fail";
            Assert.fail();
        }

    }

}
