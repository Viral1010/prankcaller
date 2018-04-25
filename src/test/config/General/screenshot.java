package test.config.General;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import test.config.SeleniumConfig.AbstractPage;


public class screenshot extends AbstractPage {

    /**
     * Initialize UserAbstractPage.
     *
     * @param driver .
     */
    public screenshot(WebDriver driver) {
        super(driver);
    }

    public String takeScreenShot(String deviceName, String stepNumber) {

        JsonFileConfig fileConfig = new JsonFileConfig();
        Dimension size;
        String destDir;
        DateFormat dateFormat;
        String fileURL = null;

        // Set folder name to store screenshots.
        destDir = "/Users/god/Desktop/Screenshots/Android";
        // Capture screenshot.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Set date format to set It as screenshot file name.
        dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        // Create folder under project with name "screenshots" provided to destDir.
        new File(destDir).getAbsoluteFile().mkdirs();

        // Set file name using current date time.
        String destFile = deviceName + "-" + dateFormat.format(new Date()) + "-" + stepNumber + ".png";

        try {
            // Copy paste file at destination folder location
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));

           fileURL = AWSConnection(destDir,destFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileURL;
    }


    public String AWSConnection(String path, String FileName) {

        String accessKeyId = "AKIAJ3OOUCRE3AF2DE2A";
        String secretKey = "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";
        //*** Provide bucket name ***
        String bucketName = "symbaventurestests";
        //*** Provide key ***
        String keyName = FileName;
        //*** Provide file name ***
        String uploadFileName = path + "/" + FileName;

        //AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());

        AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretKey);


        AmazonS3 s3client = new AmazonS3Client(credentials);
        String URl = null;

        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            File file = new File(uploadFileName);
            //System.out.println("File : "+uploadFileName);
            s3client.putObject(new PutObjectRequest(
                    bucketName, keyName, file).withCannedAcl(CannedAccessControlList.PublicRead));

            //s3client.getResourceUrl("your-bucket", "some-path/some-key.jpg");

            System.out.println("S3 Image URL : "+s3client.getUrl(bucketName,FileName));

           // URl = "https://s3.amazonaws.com/"+bucketName+"/"+FileName;
            URl=s3client.getUrl(bucketName,FileName).toString();

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

        return URl;


    }

    /*public static void main(String[] args) {
        AWSConnection();
    }*/
}


