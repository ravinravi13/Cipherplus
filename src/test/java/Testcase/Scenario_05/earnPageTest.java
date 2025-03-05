package Testcase.Scenario_05;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.dashBoard;
import Cipherplus.Pages.earnPage;
import Utilities.dataBaseConnect;
import Utilities.readExcel;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class earnPageTest extends BaseClass {

    earnPage obj_earnPage = new earnPage();
    dataBaseConnect obj_dataBaseConnect =new dataBaseConnect();
    dashBoard obj_dashBoard = new dashBoard();

    @BeforeClass(groups = {"BaseLogin", "Employee"})
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

    }



    @Test(priority = 1, description = "verify the posts data cross check with Database ", groups = {"Earn", "Smoke Test"})
    @Description("This test attempts to verify All posts present in respective categories")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("Earn "),
            @Feature("Post")})
    public void sample() throws SQLException, IOException {
        // Initialize the connection
        Connection connection = obj_dataBaseConnect.dbconnect();
        List<Map<String, String>> expectedEarnList = new ArrayList<>();
        List<Map<String, String>> actualEarnList;

        String query = "SELECT Title, Description, PointValue, Approver FROM Post WHERE Approver = ?";
        Allure.step("Use this Query to fetch data" + "\n" + query);

        // Click on the Earn navigation bar
        obj_earnPage.clickEarnNavBar();

        // Get the number of rows in the Excel sheet
        int rows = readExcel.getRowCount(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("earnExcelSheetName"));

        // Loop through each category in the Excel sheet
        for (int i = 1; i <= rows; i++) {
            expectedEarnList.clear();
            String categoryName = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("earnExcelSheetName"), i, 0);
            obj_earnPage.clickCategories(readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("earnExcelSheetName"), i, 1));
            actualEarnList = obj_earnPage.getEarnTableContent();

            // Prepare the statement for the current category
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, categoryName);
                ResultSet rs = pstmt.executeQuery();

                // Fetch expected values from the database
                while (rs.next()) {
                    Map<String, String> earnMap = new HashMap<>();
                    earnMap.put("Title", rs.getString("Title"));
                    earnMap.put("Description", rs.getString("Description"));
                    earnMap.put("Reward Point", rs.getString("PointValue"));
                    earnMap.put("Approver", rs.getString("Approver"));
                    expectedEarnList.add(earnMap);
                }

                // Assert that the sizes of both lists are equal
                Assert.assertEquals(expectedEarnList.size(), actualEarnList.size(), "The size of expected and actual earnings lists do not match.");

                // Validate that each actual value exists in the expected values
                for (Map<String, String> actual : actualEarnList) {
                    boolean found = false;
                    for (Map<String, String> expected : expectedEarnList) {
                        if (actual.get("Title").trim().equals(expected.get("Title").trim()) &&
                                actual.get("Description").trim().equals(expected.get("Description").trim()) &&
                                actual.get("Reward Point").trim().equals(expected.get("Reward Point").trim()) && // Ensure key matches
                                actual.get("Approver").trim().equals(expected.get("Approver").trim())) {
                            found = true;
                            break; // Exit the inner loop if a match is found
                        }
                    }
                    Assert.assertTrue(found, "Actual value not found in expected values: " + actual);
                }

                // Close the ResultSet
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Assert.fail("SQL Exception occurred: " + e.getMessage());
            }

            System.out.println(categoryName+" is finished");
        }

        // Close the connection after all operations
        connection.close();
    }



    @Test(priority = 2, description = "verify the Apply button to Successfully Apply the Event", groups = {"Earn", "Smoke Test"})
    @Description("This test attempts to verify the Apply button's functionality")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("Earn "),
            @Feature("Apply Button")})

    public void VerifyApplyButton() throws IOException, InterruptedException {

        int rows = readExcel.getRowCount(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("earnExcelSheetName"));

        for(int i=1;i<=rows;i++)
        {
            obj_earnPage.clickCategories(readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("earnExcelSheetName"), i, 1));
            obj_earnPage.clickRandomApplyButton();
            Thread.sleep(2000);
            obj_earnPage.clickOkButton();
            String expectAppliedSuccessMsg = obj_earnPage.getAppliedSuccessfullyMsg();
            obj_earnPage.clickCloseButton();
           Assert.assertEquals("Applied successfully",expectAppliedSuccessMsg);
        }
    }

























}



























