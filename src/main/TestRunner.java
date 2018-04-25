package main;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONArray;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import test.config.General.JsonFileConfig;

import java.util.*;

public class TestRunner {


    public static void main(String[] args) {

        JsonFileConfig config = new JsonFileConfig();

        TestRunner runner = new TestRunner();

        ArrayList<String> Browsers = config.getBrowsers();

        String executeBy = config.getExecutionBy();

        System.out.println(config.getDeviceNameArray());
        JSONArray devices = config.getDeviceNameArray();

        for (Object device : devices){
            System.out.println(device);
            for (String Browser : Browsers) {
                //System.out.println("Browser Name : " + Browser);

                //System.out.println("Selected Execution Option : " + executeBy);

                if (executeBy.equals("class")) {
                    runner.classExecution(Browser,device.toString());
                } else if (executeBy.equals("methods")) {
                    runner.runByMethod(Browser);
                } else if (executeBy.equals("groups")) {
                    runner.runByGroups(Browser);
                }
            }
        }


    }

    public void runByMethod(String Browser) {
        JsonFileConfig config = new JsonFileConfig();

        Set<String> classList = config.getExecutionMethods();

        List<XmlSuite> suites = new ArrayList<XmlSuite>();

        XmlSuite suite = new XmlSuite();

        Map<String, String> testClassParameters = new HashMap<>();
        testClassParameters.put("selenium.browser", Browser);
        suite.setParameters(testClassParameters);

        suite.setParallel("methods");
        suite.setThreadCount(1);

        XmlTest test = new XmlTest(suite);

        List<XmlClass> classes = new ArrayList<XmlClass>();

        for (String name : classList) {
            XmlClass classNa = new XmlClass(name);

            String suiteName = name.split("\\.")[2];
            String testName = name.split("\\.")[1];

            suite.setName(suiteName);
            test.setName(testName);

            ArrayList<String> methodNames = config.getExecutionMethodName(name);

            List<XmlInclude> includes = new ArrayList<XmlInclude>();

            for (String eachMethod : methodNames) {

                includes.add(new XmlInclude(eachMethod));
            }
            classNa.setIncludedMethods(includes);

            classes.add(classNa);
        }
        test.setXmlClasses(classes);
        suites.add(suite);

        System.out.println(suite.toXml());

        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }


    public void classExecution(String Browser, String device) {
        JsonFileConfig config = new JsonFileConfig();

        ArrayList<String> classList = config.getExecutionClass();



        List<XmlSuite> suites = new ArrayList<XmlSuite>();

        XmlSuite suite = new XmlSuite();

        Map<String, String> testClassParameters = new HashMap<>();
        testClassParameters.put("selenium.browser", Browser);
        testClassParameters.put("deviceName", device);
        suite.setParameters(testClassParameters);
        suite.setParallel("methods");
        suite.setThreadCount(1);

        for (String className : classList) {

            //System.out.println(className);
            String suiteName = className.split("\\.")[0];
            String testName = className.split("\\.")[1];

            suite.setName(suiteName);


            XmlTest test = new XmlTest(suite);
            //test.setName(testName);

            List<XmlClass> classes = new ArrayList<XmlClass>();

            XmlClass classNa = new XmlClass(className);

            classes.add(classNa);
            test.setXmlClasses(classes);
            suites.add(suite);
        }

        System.out.println(suite.toXml());

        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }



    public void runByGroups(String Browser) {

        JsonFileConfig config = new JsonFileConfig();

        List<XmlSuite> suites = new ArrayList<XmlSuite>();

        XmlSuite suite = new XmlSuite();
        XmlTest test = new XmlTest(suite);

        Map<String, String> testClassParameters = new HashMap<>();
        testClassParameters.put("selenium.browser", Browser);
        suite.setParameters(testClassParameters);

        suite.setParallel("methods");
        suite.setThreadCount(1);

        ArrayList<String> runList = config.getExecutionGroupRun();

        List<XmlClass> classes = new ArrayList<XmlClass>();

        for (String run : runList) {

            Set<String> classList = config.getKeys(run);

            for (String name : classList) {

                XmlClass classNa = new XmlClass(name);

                String suiteName = name.split("\\.")[0];
                String testName = name.split("\\.")[1];

                suite.setName(suiteName);
                test.setName(testName);

                ArrayList<String> methodNames = config.getExecutionMethodName(name);

                List<XmlInclude> includes = new ArrayList<XmlInclude>();

                for (String eachMethod : methodNames) {

                    includes.add(new XmlInclude(eachMethod));
                }
                classNa.setIncludedMethods(includes);

                classes.add(classNa);
            }
        }
        test.setXmlClasses(classes);
        suites.add(suite);

        System.out.println(suite.toXml());

        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }

    public String generateRandomAlphabetic(int Length) {
        String RandomName = RandomStringUtils.randomAlphabetic(Length);
        return RandomName;
    }

}




