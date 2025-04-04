package Testcase.Admin.Scenario_07;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.AdminPage.ManageGifts;
import Cipherplus.Pages.dashBoard;
import Utilities.TestDataGenerator;
import Utilities.dataBaseConnect;
import Utilities.readExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ManageGiftsTest extends BaseClass {

    dashBoard obj_dashBoard = new dashBoard();
    ManageGifts obj_ManageGifts = new ManageGifts();
    dataBaseConnect obj_dataBaseConnect = new dataBaseConnect();

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
        obj_ManageGifts.clickAdminNav();
        obj_ManageGifts.clickManageGift();
    }


   // @Test
    public void VerifyAllCategoryProduct() throws IOException, InterruptedException {
        List<List<String>> expectedList = new ArrayList<>();
        Connection connection = obj_dataBaseConnect.dbconnect();
        int rows = readExcel.getRowCount(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("manageGiftAllCategorySheetName"));

        for (int i = 1; i <= rows; i++) {
            String categoryName = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("manageGiftAllCategorySheetName"), i, 0);
            obj_ManageGifts.selectProductCategeory(categoryName); // Select category from UI

            // Fetch actual data from the UI
            List<List<String>> actualList = obj_ManageGifts.getManageGiftTable();

            // Fetch expected data from the database
            List<List<String>> dbExpectedList = fetchExpectedDataFromDatabase(connection, categoryName);
            expectedList.addAll(dbExpectedList); // Add the expected data to the main list

            // Compare actualList with expected data from the database
            compareData(actualList, dbExpectedList);
        }
    }

    private List<List<String>> fetchExpectedDataFromDatabase(Connection connection, String categoryName) {
        List<List<String>> expectedList = new ArrayList<>();
        String query = "SELECT Name, Description, LocationId, Cost, Quantity, ? AS Category FROM Reward WHERE CategoryId=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            String catId = getCategoryId(categoryName);
            preparedStatement.setString(1, categoryName);
            preparedStatement.setString(2, catId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    List<String> rowData = new ArrayList<>();
                    rowData.add(normalizeString(resultSet.getString("Name")));
                    rowData.add(normalizeString(categoryName));
                    rowData.add(normalizeString(resultSet.getString("Description")));
                    rowData.add(normalizeString(resultSet.getString("Cost")));
                    rowData.add(normalizeString(resultSet.getString("Quantity")));
                    expectedList.add(rowData);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching data from database for category: " + categoryName, e);
        }

        return expectedList;
    }

    private void compareData(List<List<String>> actualList, List<List<String>> expectedList) {
        for (int j = 0; j < expectedList.size(); j++) {
            if (j >= actualList.size()) {
                throw new AssertionError("Actual list does not have enough rows to compare with expected list.");
            }

            List<String> expectedRow = expectedList.get(j);
            List<String> actualRow = actualList.get(j);

            for (int k = 0; k < expectedRow.size(); k++) {
                String expectedValue = normalizeString(expectedRow.get(k));
                String actualValue = normalizeString(actualRow.get(k));
                assertEquals(actualValue, expectedValue, "Mismatch at row " + j + ", column " + k);
            }
        }
    }

    // Normalize method updated for better handling
    private String normalizeString(String input) {
        if (input == null) return "";
        // Replace non-breaking spaces, normalize spaces, and standardize capitalization
        return input.replaceAll("\u00A0", "")
                .replaceAll("\\s+", " ")  // Replace multiple spaces with single space
                .trim()
                .toLowerCase();
    }
    // Helper method to get the category ID based on the category name
    private String getCategoryId(String categoryName) {
        switch (categoryName) {
            case "Books":
                return obj_ManageGifts.bookCatId;
            case "Clothing":
                return obj_ManageGifts.clothingCatId;
            case "Electronics":
                return obj_ManageGifts.electronicCatId;
            case "Health and Fitness":
                return obj_ManageGifts.HealthAndFitnessCatId;
            case "Stationary":
                return obj_ManageGifts.stationaryCatId; // Ensure this is defined
            case "Organic Store":
                return obj_ManageGifts.OrganicStoreCatId; // Ensure this is defined
            case "eVouchers":
                return obj_ManageGifts.eVouchersCatId;
            case"Plants":
                return obj_ManageGifts.plantCatId;

            case "Home Appliance":
                return obj_ManageGifts.HomeApplianceCatId;

            default:
                throw new IllegalArgumentException("Unknown category: " + categoryName);

        }
    }

        @AfterClass(groups = {"BaseLogin"})
        public void teardown ()
        {
            BaseClass.driver.quit();
        }




        @Test
    public void VerifyAddNewGift()
    {
        TestDataGenerator dataGenerator = new TestDataGenerator();

        // Initiating the add gift process
        obj_ManageGifts.clickAdd();

        // Entering with modified values to match expected output
        obj_ManageGifts.enterGiftName("Automation Testing");
        obj_ManageGifts.enterGiftDescription("Automation Testing");
        obj_ManageGifts.selectGiftCategeory("Organic Store");
        obj_ManageGifts.selectGiftLocation("chennai");
        String pointvalue = Integer.toString(dataGenerator.generateRandomPointsValue());
        obj_ManageGifts.enterGiftPointsValue(pointvalue);
        String quantityvalue = Integer.toString(dataGenerator.generateRandomQuantity());
        obj_ManageGifts.enterGiftQuantity("1");
        obj_ManageGifts.enterGiftProductName("Testing");
        // Sending updated product image
        obj_ManageGifts.sendProductImage(System.getProperty("user.dir") + "//testData//Image//testimage.jpg");
        if((pointvalue < 0) || (quantityvalue < 0))
        {
            obj_ManageGifts.clickAddGiftButton();
            obj_ManageGifts.enterSearchBox("Automation Testing");
        }
    }























}


















