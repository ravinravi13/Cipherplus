package Testcase.Admin.Scenario_07;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.AdminPage.pointsDistribution;
import Cipherplus.Pages.dashBoard;
import Utilities.dataBaseConnect;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pointsDistributionTest extends BaseClass {

    dashBoard obj_dashBoard = new dashBoard();
    pointsDistribution obj_pointsDistribution = new pointsDistribution();
    dataBaseConnect obj_dataBaseConnect =new dataBaseConnect();











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
        obj_pointsDistribution.clickAdminNav();
        obj_pointsDistribution.clickPointsDistribution();
    }



   @Test(priority = 1, description = "Verify the points displayed in the Points Distribution",groups = {"Login","Smoke Test","Regression Test","Admin","Point Distribution"})
    @Description("This attempt aims to verify the earned points, distributed points, and lifetime points, and to cross-check them with the database")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Admin"),
            @Feature("Points Distribution")})
    public void verifyPointsOfAdmin() throws SQLException {

        Connection connection = obj_dataBaseConnect.dbconnect();

        String query = "select PointsEarned, PointsForDistribution,LifetimePoints from Employee where Name ='testing' ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        String pointEarned = obj_pointsDistribution.locatePointsEarned();
        String pointsDistribution = obj_pointsDistribution.locatePointsForDistribution();
        String lifetimePoints = obj_pointsDistribution.locateLifePoints();

        while(resultSet.next()) {
            if (pointEarned.equals("Points Earned")) {
                String point = obj_pointsDistribution.getPointsEarned();
                Assert.assertEquals(point, resultSet.getString("PointsEarned"));
            } else if (pointsDistribution.equals("Points for Distribution")) {
                String point = obj_pointsDistribution.getPointsForDistribution();
                Assert.assertEquals(point, resultSet.getString("PointsForDistribution"));
            } else if (lifetimePoints.equals("Lifetime Points")) {
                String point = obj_pointsDistribution.getLifetimePoints();
                Assert.assertEquals(point, resultSet.getString("LifetimePoints"));
            }

        }

    }


//    @Test(priority = 2, description = "Verify the names and points of direct reportees",groups = {"Login","Smoke Test","Regression Test","Admin","Point Distribution"})
//    @Description("This attempt aims to verify the earned points, distributed points, and lifetime points, and to cross-check them with the database")
//    @Severity(SeverityLevel.CRITICAL)
//    @Features({
//            @Feature("Admin"),
//            @Feature("Points Distribution"),@Feature("Direct Reportee")})
//    public void verifyDirectReportee() throws SQLException {
//        Connection connection = obj_dataBaseConnect.dbconnect();
//
//        List<Map<String, String>> actualDirectReport = new ArrayList<>();
//        actualDirectReport = obj_pointsDistribution.getDirectReportees();
//        List<Map<String, String>> expectedDirectReport = new ArrayList<>();
//
//        obj_pointsDistribution.getDirectReportees();
//        String query = "select Name,Email,PointsEarned, PointsForDistribution,LifetimePoints,UnitName from Employee where Manager ='testing.team_iat@ilink-systems.com'";
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//
//        Map<String, String> reporteeData = null;
//        while (resultSet.next()) {
//            reporteeData = new HashMap<>();
//            reporteeData.put("Name", resultSet.getString("Name"));
//            reporteeData.put("Email", resultSet.getString("Email"));
//            reporteeData.put("Earned points", resultSet.getString("PointsEarned"));
//            reporteeData.put("Points for Distribution", resultSet.getString("PointsForDistribution"));
//            reporteeData.put("Lifetime Points", resultSet.getString("LifetimePoints"));
//            reporteeData.put("BU", resultSet.getString("UnitName"));
//        }
//
//        expectedDirectReport.add(reporteeData);
//
//
//
//
//
//
//
//    }

//    @Test(priority = 2, description = "Verify the names and points of direct reportees", groups = {"Login", "Smoke Test", "Regression Test", "Admin", "Point Distribution"})
//    @Description("This attempt aims to verify the earned points, distributed points, and lifetime points, and to cross-check them with the database")
//    @Severity(SeverityLevel.CRITICAL)
//    @Features({
//            @Feature("Admin"),
//            @Feature("Points Distribution"),
//            @Feature("Direct Reportee")
//    })
//    public void verifyDirectReportee() throws SQLException {
//        Connection connection = obj_dataBaseConnect.dbconnect();
//
//        List<Map<String, String>> actualDirectReport = obj_pointsDistribution.getDirectReportees();
//        List<Map<String, String>> expectedDirectReport = new ArrayList<>();
//
//        String query = "SELECT Name, Email, PointsEarned, PointsForDistribution, LifetimePoints, UnitName " +
//                "FROM Employee WHERE Manager ='testing.team_iat@ilink-systems.com'";
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//
//        while (resultSet.next()) {
//            Map<String, String> reporteeData = new HashMap<>();
//            reporteeData.put("Name", resultSet.getString("Name"));
//            reporteeData.put("Email", resultSet.getString("Email"));
//            reporteeData.put("Earned Points", resultSet.getString("PointsEarned"));
//            reporteeData.put("Points for Distribution", resultSet.getString("PointsForDistribution"));
//            reporteeData.put("Lifetime Points", resultSet.getString("LifetimePoints"));
//            reporteeData.put("BU", resultSet.getString("UnitName"));
//
//            expectedDirectReport.add(reporteeData);
//        }
//
//        // Normalize the keys of both actual and expected data
//        Map<String, String> normalizedExpected = normalizeKeys(expectedDirectReport);
//        Map<String, String> normalizedActual = normalizeKeys(actualDirectReport);
//
//        // Assert that the normalized maps are equal
//        Assert.assertEquals(normalizedActual, normalizedExpected);
//    }
//
//
//    private Map<String, String> normalizeKeys(List<Map<String, String>> data) {
//        Map<String, String> normalizedMap = new HashMap<>();
//        for (Map<String, String> map : data) {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                // Normalize the key by trimming and converting to lowercase
//                String normalizedKey = entry.getKey().trim().toLowerCase();
//                normalizedMap.put(normalizedKey, entry.getValue());
//            }
//        }
//        return normalizedMap;
//    }



    @Test(priority = 2, description = "Verify the names and points of direct reportees", groups = {"Login", "Smoke Test", "Regression Test", "Admin", "Point Distribution"})
    @Description("This attempt aims to verify the earned points, distributed points, and lifetime points, and to cross-check them with the database")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Admin"),
            @Feature("Points Distribution"),
            @Feature("Direct Reportee")
    })
    public void verifyDirectReportee() throws SQLException{
        Connection connection = obj_dataBaseConnect.dbconnect();
        List<List<String>> actualDirectReportee = new ArrayList<>();
        List<List<String>> expectedDirectReportee = new ArrayList<>();

        actualDirectReportee = obj_pointsDistribution.getDirectReportees();

        String query = "SELECT TOP 1502 Name, Email, PointsForDistribution, PointsEarned, LifetimePoints, UnitName FROM Employee " +
                "WHERE Manager ='testing.team_iat@ilink-systems.com'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

//        while (resultSet.next()) {
//            List<String> rowData = new ArrayList<>();
//            rowData.add(resultSet.getString("Name"));
//            rowData.add(resultSet.getString("Email"));
//            rowData.add(resultSet.getString("PointsForDistribution"));
//            rowData.add(resultSet.getString("PointsEarned"));
//            rowData.add(resultSet.getString("LifetimePoints"));
//            rowData.add(resultSet.getString("UnitName"));
//            expectedDirectReportee.add(rowData);
//        }
//
//        // Assert that the sizes of both lists are equal
//        Assert.assertEquals(actualDirectReportee.size(), expectedDirectReportee.size(), "Size mismatch between actual and expected direct reportees.");
//
//        // Loop through each expected and actual reportee
//        for (int i = 0; i < expectedDirectReportee.size(); i++) {
//            List<String> expectedRow = expectedDirectReportee.get(i);
//            List<String> actualRow = actualDirectReportee.get(i);
//
//
//            // Assert that each row matches
//            Assert.assertEquals(actualRow, expectedRow, "Validation failed at index " + i + ": Expected " + expectedRow + " but found " + actualRow);
//        }
//
//        // Close resources
//        resultSet.close();
//        statement.close();
//        connection.close();
    }






















}
