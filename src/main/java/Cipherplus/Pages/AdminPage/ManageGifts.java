package Cipherplus.Pages.AdminPage;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageGifts extends BaseClass {


   public String  bookCatId ="1";
    public String clothingCatId = "2";
    public String electronicCatId = "3";
    public String HealthAndFitnessCatId = "4";
    public String stationaryCatId = "5";
    public String OrganicStoreCatId = "6";
    public String eVouchersCatId = "7";
    public String plantCatId = "8";
    public String HomeApplianceCatId = "9";



    By btn_AddLoc = By.xpath("//button[text()='ADD']");
    By searchBoxLoc = By.xpath("//input[@placeholder='Search for gifts...']");
    By drop_allCategoryLoc = By.xpath("(//select[@class='form-select'])[1]");
    By adminLinkLoc = By.linkText("Admin");
    By manageGiftLoc = By.xpath("//h4[text()='Manage Gifts']");
    By giftNameLoc = By.xpath("(//input[@id='name'])[1]");
    By giftDescriptionLoc = By.xpath("(//textarea[@id='description'])[1]");
    By drop_giftcategory = By.id("categoryId");
    By giftPointsValue = By.id("cost");
    By giftQuantityLoc = By.xpath("(//input[@id='quantity'])[1]");
    By giftProductNameLoc = By.id("productName");
    By file_giftProductImageLoc = By.id("productUrl");
    By drop_giftLocation =By.id("locationId");
   By btn_AddpostLoc = By.xpath("//button[text()='Add Gift']");



    @Step("Click Manage Gift Container")
    public void clickManageGift() {
        wait.until(ExpectedConditions.presenceOfElementLocated(manageGiftLoc)).click();
    }





    @Step("Click Admin Link in Navigation Bar")
    public void clickAdminNav() {
        wait.until(ExpectedConditions.presenceOfElementLocated(adminLinkLoc)).click();
    }

    @Step("Click the Add Button")
    public void clickAdd()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_AddLoc)).click();
    }


    @Step("Enter the product Name : {0} ")
    public void enterSearchBox(String productname)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLoc));
        element.clear();
        element.sendKeys(productname);
    }

    @Step("Select the product Category : {0} ")
    public void selectProductCategeory(String categoryName)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(drop_allCategoryLoc));
        Select select = new Select(element);
        select.selectByVisibleText(categoryName);
    }



    public List<List<String>> getManageGiftTable() throws InterruptedException {
        List<List<String>> actualManageGiftTable = new ArrayList<>();
        Thread.sleep(2000); // Consider using WebDriverWait instead of Thread.sleep for better practice
        // Locate the table
        WebElement table = driver.findElement(By.xpath("//table[contains(@class,'table table-borderless')]"));

        // Get all rows from the table
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

        // Iterate through each row
        for (WebElement row : rows) {
            // Get all columns in the row
            List<WebElement> columns = row.findElements(By.tagName("td"));

            // Check if there are at least 6 columns
            if (columns.size() >= 6) {
                // Create a list to store the current row's data
                List<String> rowData = new ArrayList<>();

                // Extract data from the columns, skipping the fourth column (index 3)
                for (int i = 0; i < columns.size(); i++) {
                    if (i == 3) {
                        continue; // Skip the fourth column
                    }
                    String cellData = normalizeString(columns.get(i).getText());
                    rowData.add((cellData.toLowerCase().trim())); // Add cell data to the row list
                }

                // Add the row data to the main list
                actualManageGiftTable.add(rowData);
            }
        }


        return actualManageGiftTable;
    }


    private String normalizeString(String input) {
        if (input == null) return "";

        // Replace non-breaking spaces, normalize spaces, and standardize capitalization
        String normalized = input.replaceAll("\u00A0", "")
                .replaceAll("\\s+", " ")  // Replace multiple spaces with a single space
                .trim()
                .toLowerCase();

        // Ensure 'speaker' is followed directly by '650' without a space
        normalized = normalized.replaceAll("(speaker)\\s+(650)", "$1$2");

        return normalized;
    }



    @Step("Enter the Gift Name : {0} ")
    public void enterGiftName(String giftName)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(giftNameLoc));
        element.clear();
        element.sendKeys(giftName);
    }


    @Step("Enter the Gift description : {0} ")
    public void enterGiftDescription(String giftDescription)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(giftDescriptionLoc));
        element.clear();
        element.sendKeys(giftDescription);
    }


    @Step("Select the Gift Category : {0} ")
    public void selectGiftCategeory(String categoryName)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(drop_giftcategory));
        Select select = new Select(element);
        select.selectByVisibleText(categoryName);
    }

    @Step("Enter the Gift Points Value : {0} ")
    public void enterGiftPointsValue(String PointValue)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(giftPointsValue));
        element.clear();
        element.sendKeys(PointValue);
    }

    @Step("Enter the Gift Quantity : {0} ")
    public void enterGiftQuantity(String quantity)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(giftQuantityLoc));
        element.clear();
        element.sendKeys(quantity);
    }

    @Step("Enter the Gift ProductName : {0} ")
    public void enterGiftProductName(String Name)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(giftProductNameLoc));
        element.clear();
        element.sendKeys(Name);
    }

    @Step("Enter the Gift ProductName : {0} ")
    public void sendProductImage(String Image)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(file_giftProductImageLoc));
        element.clear();
        element.sendKeys(Image);
    }

    @Step("Select the Gift Location : {0} ")
    public void selectGiftLocation(String Location)
    {

        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(drop_giftLocation));
        Select select = new Select(element);
        select.selectByVisibleText(Location);
    }


  @Step("Click Add Gift button")
    public void clickAddGiftButton()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_AddpostLoc)).click();
    }



    public Boolean checkAddNewGiftIsDisplay()
    {
       return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[text()='Add New Gift']"))).isDisplayed();
    }









































}
