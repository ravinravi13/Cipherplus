package Cipherplus.Base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class BaseClass {


    long timeOuts = 20;
    static long maxWaitTime = 20;
    public static RemoteWebDriver driver;
    public static WebDriverWait wait;



    @Step("Launch the Browser : {0}")
    public void launchBrowser(String browserName){

        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        }
        else if(browserName.equals("edge")){
            driver = new EdgeDriver();
        }
        else{
            System.out.println("Invalid Browser has being passed");
        }
        if(driver!=null){
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOuts));
            driver.get("https://cipherdevui.azurewebsites.net");
            wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
            System.out.println("Driver initialized and URL loaded");
        } else {
            System.err.println("Driver initialization failed.");
        }
    }










    public static Properties prop = new Properties();

    static {
        try(InputStream input = BaseClass.class.getClassLoader().getResourceAsStream("Config/Values.properties")){
            if(input!=null){
                prop.load(input);
            }
            else{
                throw new IOException("Properties file not found");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }














}






















