package Testcase.Admin.Scenario_07;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.AdminPage.ManageRewardPrograms;
import Cipherplus.Pages.dashBoard;
import Utilities.TestDataGenerator;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerRewardProgramTest extends BaseClass {


    ManageRewardPrograms obj_ManageRewardPrograms = new ManageRewardPrograms();
    dashBoard obj_dashBoard = new dashBoard();

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust format as needed



    @BeforeClass(groups = "BaseLogin")
    public void enterApplication() throws InterruptedException {
        launchBrowser("chrome");

        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0116")));
        emailField.sendKeys(getProperty("mailId"));
        driver.findElement(By.id("idSIButton9")).click();
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0118")));
        passwordField.sendKeys(getProperty("Password"));
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("idSIButton9"))).click();
//      driver.findElement(By.id("idSIButton9")).click();
        obj_dashBoard.clickExploreBtn();
        obj_ManageRewardPrograms.clickAdminNav();
        obj_ManageRewardPrograms.clickManagerRewardProgram();
    }









    //@Test(priority = 1, description = "Verify the search functionality for event names in the Manage Reward Program module.", groups = {"Login", "Smoke Test", "Regression Test", "Admin", "Point Distribution"})
    @Description("\"Validate that the search functionality in the Manage Reward Program accurately retrieves event names based on user input. Ensure proper handling of edge cases and display of appropriate messages for no results")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Admin"),
            @Feature("Manage Reward Program"),
            @Feature("Searching")
    })
    public void searchPostName() throws InterruptedException {
        int c = 10;
        for (int i = 0; i < c; i++) {
            String actual = obj_ManageRewardPrograms.NameOfEvent();
            actual = actual.trim();
            Thread.sleep(2000);
            obj_ManageRewardPrograms.enterNameofEvent(actual);
            List<String> expected = obj_ManageRewardPrograms.getFirstColumnTexts();

            for(String expect : expected) {
                Assert.assertEquals(actual, expect, "The expected event name does not match the actual event name.");
            }
        }
    }


  //  @Test(priority = 2, description = "Verify that the 'Date of Opened' is earlier than the 'Date of Expires' for proper chronological order.", groups = {"Login", "Smoke Test", "Regression Test", "Admin", "Manage Rewards program"})
    @Description("Ensure the 'Date of Opened' is the starting date and precedes the 'Date of Expires,' verifying proper chronological order.")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Admin"),
            @Feature("Manage Reward Program"),
            @Feature("Filter drop down"),
            @Feature("Newest to Oldest")
    })

    public void compareDateOpenedAndExpires() throws InterruptedException {
       Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class,'table table-borderless')]"))); // Adjust selector as needed

        // Retrieve all rows from the table
        List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'table table-borderless')]/tbody/tr")); // Adjust selector as needed

        for (WebElement row : rows) {
            // Get the Date Opened and Date Expires from the respective columns
            String dateOpenedText = row.findElement(By.xpath(".//td[4]")).getText(); // Assuming "Date Opened" is in the 4th column
            String dateExpiresText = row.findElement(By.xpath(".//td[5]")).getText(); // Assuming "Date Expires" is in the 5th column

            // Print the values for debugging
            System.out.println("Date Opened: " + dateOpenedText);
            System.out.println("Date Expires: " + dateExpiresText);

            // Parse the dates
            LocalDate dateOpened = LocalDate.parse(dateOpenedText, dateFormatter);
            LocalDate dateExpires = LocalDate.parse(dateExpiresText, dateFormatter);

            // Validate that the Date Opened is before the Date Expires
            Assert.assertTrue(dateOpened.isBefore(dateExpires),
                    "Date Opened (" + dateOpened + ") should be before Date Expires (" + dateExpires + ") for row: " + row.getText());
        }
    }




   // @Test(priority = 3, description = "Verify drop-down date filters for accurate sorting in the date column", groups = {"Login", "Smoke Test", "Regression Test", "Admin", "Manage Rewards program"})
    @Description("Validate that the drop-down filters for date sorting—Newest to Oldest, Oldest to Newest, Expires Sooner to Later, and Expires Later to Sooner—accurately sort and display dates from the date column. Ensure proper functionality across all filter options and verify that edge cases, such as identical dates, are handled correctly.")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Admin"),
            @Feature("Manage Reward Program"),
            @Feature("Filter drop down"),
            @Feature("Newest to Oldest"),
            @Feature("Oldest to Oldest"),
            @Feature("Expires sooner to later"),
            @Feature("Expires later to sooner")
    })
    public void testFilteringOptions() throws InterruptedException {

        // Test "Newest to Oldest"
        obj_ManageRewardPrograms.selectPostEvent("Newest to Oldest");
        obj_ManageRewardPrograms.validateTableSorting("newestToOldest");


        // Test "Oldest to Newest"
        obj_ManageRewardPrograms.selectPostEvent("Oldest to Newest");
        obj_ManageRewardPrograms.validateTableSorting("oldestToNewest");

        // Test "Expires Sooner to Later"
        obj_ManageRewardPrograms.selectPostEvent("Expires Sooner to Later");
        obj_ManageRewardPrograms.validateTableSorting("expiresSoonerToLater");

        // Test "Expires Later to Sooner"
        obj_ManageRewardPrograms.selectPostEvent("Expires Later to Sooner");
        obj_ManageRewardPrograms.validateTableSorting("expiresLaterToSooner");
    }




    @Test(priority = 4, description = "Verify Admin able to creating Add new program", groups = {"Login", "Smoke Test", "Regression Test", "Admin", "Manage Rewards program"})
    @Description("Verify that the Admin can successfully create and add a new program, and it is displayed in the program list without errors")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Admin"),
            @Feature("Manage Reward Program"),
            @Feature("Add new program"),
    })
    public void ValidateAddNewRewardProgram()
    {
        obj_ManageRewardPrograms.clickAddButton();
        TestDataGenerator dataGenerator = new TestDataGenerator();
        int pointsValue = dataGenerator.generateRandomPointsValue();
        String dateOpened = dataGenerator.generateRandomDate(true);
        System.out.println("Date opened = "+dateOpened);
        String offerExpiresDate = TestDataGenerator.generateFutureDate(dateOpened);
        System.out.println("offerExpiredate = "+offerExpiresDate);
        String invalidDate = "2023-02-30";
        obj_ManageRewardPrograms.enterEventname("Test by ravi");
        obj_ManageRewardPrograms.enterEventDescription("Testing");
        obj_ManageRewardPrograms.selectReleventLocation("chennai");
        obj_ManageRewardPrograms.enterPointsValue(pointsValue);
        obj_ManageRewardPrograms.enterOfferExpires(offerExpiresDate);
        obj_ManageRewardPrograms.enterDateOpened(dateOpened);
        obj_ManageRewardPrograms.selectApproverName("TA Team");
        obj_ManageRewardPrograms.submitForm();
    }













    @AfterClass(groups = {"BaseLogin"})
    public void teardown()
    {
        BaseClass.driver.quit();
    }












}


































