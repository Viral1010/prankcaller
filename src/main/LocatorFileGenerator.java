package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class LocatorFileGenerator {

    public static void main(String args[]) {

        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        LocatorFileGenerator generator = new LocatorFileGenerator();
        String file = "";
        System.out.println("Enter File Name");

        JSONObject mainObj = new JSONObject();

        try {
            file = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!generator.checkFile(file)) {
            generator.createFileName(file);
        } else {
            mainObj = generator.readFile(file);
        }

        mainObj = generator.buildingFile(mainObj);


        generator.writeFile(file, mainObj);


    }

    public JSONObject buildingFile(JSONObject mainObj) {
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));

        JSONObject obj = new JSONObject();

        LocatorFileGenerator generator = new LocatorFileGenerator();

        String locatorType = "";
        String locatorName = "";
        String name = "";
        String automode = "";
        String textType = "";
        String defaultValue = "";

        try {

            System.out.println("------------------------");

            System.out.println("Enter Element name");
            name = enterElementName(br);


            System.out.println("Enter locator type");
            locatorType = enterLocatorType(br);


            obj.put("locatorType", locatorType);
            System.out.println("Enter locator value");
            locatorName = br.readLine();
            obj.put("locatorValue", locatorName);

            System.out.println("Enter Text: Say Y or N");
            String text = br.readLine();

            if (text.equalsIgnoreCase("y")) {

                System.out.println("Auto mode ??:Say Y or N");
                automode = br.readLine();

                if (automode.equalsIgnoreCase("y")) {
                    obj.put("auto", true);
                    System.out.println("Enter texttype like email,First Name");
                    textType = br.readLine();
                    obj.put("textType", textType);
                    obj.put("default", "");
                } else {
                    obj.put("auto", false);
                    System.out.println("Enter default Value");
                    defaultValue = br.readLine();
                    obj.put("default", defaultValue);
                    obj.put("textType", "");
                }

            } else {
                obj.put("default", "");
                obj.put("textType", "");
                obj.put("auto", false);
            }


            mainObj.put(name, obj);

            System.out.println("Want to enter another element Say. Y or N");
            String anotherTry = br.readLine();

            if (anotherTry.equalsIgnoreCase("y")) {
                generator.buildingFile(mainObj);
            }

            System.out.println("Done");


        } catch (IOException e) {
            e.printStackTrace();
        }


        return mainObj;
    }


    public String enterElementName(BufferedReader br) {

        String name = null;
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (name.equals(null) || name.equals("")) {
            System.out.println("Please enter valid name in element Name");
            enterElementName(br);
        }

        return name;
    }


    public String enterLocatorType(BufferedReader br) {

        String locatorType = null;
        try {
            locatorType = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (locatorType.equals(null) || locatorType.equals("")) {
            System.out.println("Please enter valid name in element Name");
            enterElementName(br);
        }

        if (locatorType.equalsIgnoreCase("xpath") ||
                locatorType.equalsIgnoreCase("id") |
                        locatorType.equalsIgnoreCase("className") ||
                locatorType.equalsIgnoreCase("tagName") ||
                locatorType.equalsIgnoreCase("linkText") ||
                locatorType.equalsIgnoreCase("partialLinkText") ||
                locatorType.equalsIgnoreCase("name") ||
                locatorType.equalsIgnoreCase("cssSelector")
                ) {

        } else {

            System.out.println("Please enter valid Locator type");

            System.out.println("Only xpath, id, classname, tagname , linktext, partialLinkText, name, cssSelector are valid ");
            enterElementName(br);
        }


        return locatorType;
    }


    public boolean checkFile(String fileName) {
        boolean bool = false;
        File file = new File("src\\resources\\ObjectRepo\\" + fileName + ".json");
        if (file.exists()) {
            bool = true;
        }
        return bool;
    }

    public boolean createFileName(String fileName) {
        boolean bool = false;
        File file = new File("src\\resources\\ObjectRepo\\" + fileName + ".json");
        try {
            if (file.createNewFile()) {
                bool = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public void writeFile(String fileName, JSONObject jsonContent) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        boolean bool = false;
        File file = new File("src\\resources\\ObjectRepo\\" + fileName + ".json");

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(jsonContent.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject readFile(String fileName) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(new File("src\\resources\\ObjectRepo\\" + fileName + ".json")));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
