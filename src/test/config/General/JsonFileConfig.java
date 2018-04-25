package test.config.General;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Viral on 11/26/2016.
 */
public class JsonFileConfig {


    public static void main(String[] args) {

        JsonFileConfig s = new JsonFileConfig();
        System.out.println(s.getBrowsers());
    }

    public JSONObject loadJsonFile(String jsonFilePath) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;


        try {

            Object obj = parser.parse(new FileReader(jsonFilePath));

            jsonObject = (JSONObject) obj;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject getGeneralFile() {

        File jsonFile = new File("src/resources/general.json");

        JSONObject file = loadJsonFile(jsonFile.getAbsolutePath());

        return file;
    }

    public boolean getAutoHighlightValue() {
        boolean bool;

        JSONObject object = getGeneralFile();

        bool = (boolean) ((JSONObject) object.get("general")).get("HighightElement");
        return bool;
    }

    public boolean getAutoLog() {
        boolean bool;

        JSONObject object = getGeneralFile();

        bool = (boolean) ((JSONObject) object.get("general")).get("AutoLog");
        return bool;

    }

    public String getURL() {
        String bool;

        JSONObject object = getGeneralFile();

        bool = ((JSONObject) object.get("project")).get("url").toString();
        return bool;

    }

    public String getAPKFilePath() {
        String bool;

        JSONObject object = getGeneralFile();

        bool = ((JSONObject) object.get("project")).get("APKFilePath").toString();
        return bool;

    }

    public String getAPK1FilePath() {
        String bool;

        JSONObject object = getGeneralFile();

        bool = ((JSONObject) object.get("project")).get("APKFilePath1").toString();
        return bool;

    }

    public String getBrowser() {
        String bool;
        JSONObject object = getGeneralFile();

        bool = ((JSONObject) object.get("project")).get("browserName").toString();
        return bool;

    }

    public boolean checkForSauceLab() {
        boolean bool;
        JSONObject object = getGeneralFile();
        bool = (boolean) ((JSONObject) object.get("project")).get("saucelab");
        return bool;
    }

    public String getSauceLabUserName() {
        String bool;
        JSONObject object = getGeneralFile();
        bool = ((JSONObject) object.get("saucelab")).get("userName").toString();
        return bool;
    }

    public String getSauceLabAccessKey() {
        String bool;
        JSONObject object = getGeneralFile();
        bool = ((JSONObject) object.get("saucelab")).get("accessKey").toString();
        return bool;
    }

    public String getSauceLabPlatform() {
        String bool;
        JSONObject object = getGeneralFile();
        bool = ((JSONObject) object.get("saucelab")).get("platform").toString();
        return bool;
    }

    public String getSauceLabVersion() {
        String bool;
        JSONObject object = getGeneralFile();
        bool = ((JSONObject) object.get("saucelab")).get("version").toString();
        return bool;
    }

    public JSONObject getElementValue(String fileName, String elementName) {

        Map<String, String> elementData = new HashMap<String, String>();
        File jsonFile = new File("src/resources/ObjectRepo/" + fileName + ".json");
        JSONObject file = loadJsonFile(jsonFile.getAbsolutePath());
        JSONObject object = (JSONObject) file.get(elementName);
        return object;
    }

    public JSONObject loadExecutionFile() {
        File jsonFile = new File("src/resources/execution.json");

        JSONObject file = loadJsonFile(jsonFile.getAbsolutePath());

        return file;
    }

    public ArrayList getExecutionBrowsers() {
        ArrayList bool;
        JSONObject object = loadExecutionFile();

        bool = (JSONArray) ((JSONObject) object.get("run")).get("browsers");
        return bool;

    }

    public String getExecutionBy() {
        String bool;
        JSONObject object = loadExecutionFile();

        bool = ((JSONObject) object.get("run")).get("by").toString();
        return bool;

    }

    public ArrayList<String> getBrowsers() {
        ArrayList<String> bool;
        JSONObject object = loadExecutionFile();

        bool = (JSONArray) ((JSONObject) object.get("run")).get("browsers");

        return bool;

    }

    public Set<String> getExecutionMethods() {

        JSONObject object = loadExecutionFile();

        JSONObject method = (JSONObject) object.get("methods");

        Set<String> Keys = method.keySet();

        return Keys;

    }

    public ArrayList<String> getExecutionMethodName(String Key) {

        ArrayList<String> bool;
        JSONObject object = loadExecutionFile();

        JSONObject method = (JSONObject) object.get("methods");

        bool = (JSONArray) method.get(Key);
        return bool;

    }


    public boolean executeParallel() {
        boolean bool;
        JSONObject object = loadExecutionFile();

        bool = (boolean) ((JSONObject) object.get("run")).get("by");
        return bool;

    }

    public int getParallelThreadCount() {
        int bool;
        JSONObject object = loadExecutionFile();
        bool = (Integer) ((JSONObject) object.get("run")).get("by");
        return bool;
    }

    public ArrayList<String> getExecutionClass() {

        ArrayList<String> bool;
        JSONObject object = loadExecutionFile();

        bool = (JSONArray) object.get("class");
        return bool;

    }


    public Object[] getExecutionGroups() {
        Object[] bool;
        JSONObject object = loadExecutionFile();

        JSONObject group = (JSONObject) object.get("groups");

        bool = (group.keySet()).toArray();
        return bool;
    }

    public ArrayList<String> getExecutionGroupRun() {

        ArrayList<String> bool;
        JSONObject object = loadExecutionFile();

        JSONObject groupRun = (JSONObject) object.get("groups");

        bool = (JSONArray) groupRun.get("run");
        return bool;

    }

    public Set<String> getKeys(String GroupName) {

        JSONObject object = loadExecutionFile();

        JSONObject group = (JSONObject) object.get("groups");

        JSONObject selectedGroup = (JSONObject) group.get(GroupName);

        Set<String> Keys = selectedGroup.keySet();


        return Keys;

    }

    public String getDeviceName() {
        String bool;
        JSONObject object = getGeneralFile();

        bool = ((JSONObject) object.get("project")).get("deviceName").toString();
        return bool;

    }

    public String getDeviceType() {
        String bool;
        JSONObject object = getGeneralFile();

        bool = ((JSONObject) object.get("project")).get("deviceType").toString();
        return bool;

    }

    public String getPlatformVersion() {
        String bool;
        JSONObject object = getGeneralFile();

        bool = ((JSONObject) object.get("project")).get("platformVersion").toString();
        return bool;

    }

    public String getOS() {
        String bool;
        JSONObject object = getGeneralFile();

        bool = ((JSONObject) object.get("project")).get("os").toString();
        return bool;

    }

    public JSONArray getDeviceNameArray() {
        JSONArray bool;
        JSONObject object = getGeneralFile();

        bool = (JSONArray) ((JSONObject) object.get("project")).get("deviceName");
        return bool;

    }

}
