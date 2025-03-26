package Testcase.Admin.Scenario_07;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.AdminPage.pointsDistribution;
import Cipherplus.Pages.dashBoard;
import Utilities.dataBaseConnect;
import Utilities.readExcel;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


 //   @Test(priority = 2, description = "Verify the names and points of direct reportees", groups = {"Login", "Smoke Test", "Regression Test", "Admin", "Point Distribution"})
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

        actualDirectReportee =   obj_pointsDistribution.getDirectReportees();

        String query = "SELECT top 30 Name, Email, PointsForDistribution, PointsEarned, LifetimePoints, UnitName FROM Employee " +
                "WHERE Manager ='testing.team_iat@ilink-systems.com' and isActive=1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            List<String> rowData = new ArrayList<>();
            rowData.add(resultSet.getString("Name"));
            rowData.add(resultSet.getString("Email"));
            rowData.add(resultSet.getString("PointsForDistribution"));
            rowData.add(resultSet.getString("PointsEarned"));
            rowData.add(resultSet.getString("LifetimePoints"));
            rowData.add(resultSet.getString("UnitName"));
            expectedDirectReportee.add(rowData);
        }
//
        // Assert that the sizes of both lists are equal
        Assert.assertEquals(actualDirectReportee.size(), expectedDirectReportee.size(), "Size mismatch between actual and expected direct reportees." + actualDirectReportee);

        // Loop through each expected and actual reportee
        for (int i = 0; i < expectedDirectReportee.size(); i++) {
            List<String> expectedRow = expectedDirectReportee.get(i);
            List<String> actualRow = actualDirectReportee.get(i);


            // Assert that each row matches
            Assert.assertEquals(actualRow, expectedRow, "Validation failed at index " + i + ": Expected " + expectedRow + " but found " + actualRow);
//            System.out.println("Actual row is : "+ actualRow);
//            System.out.println("Expected  row is : "+ expectedRow);
//            System.out.println("=========================================================");
        }

        // Close resources
        resultSet.close();
        statement.close();
        connection.close();
    }




  //  @Test(priority = 3, description = "Verify the names and points of direct reportees", groups = {"Login", "Smoke Test", "Regression Test", "Admin", "Point Distribution"})
    @Description("This attempt aims to verify the earned points, distributed points, and lifetime points, and to cross-check them with the database")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Admin"),
            @Feature("Points Distribution"),
            @Feature("Direct Reportee")
    })
    public void VerifyBuDropDown() throws IOException {
        int rows = readExcel.getRowCount(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("pointsDistributionBUDropDown"));
        System.out.println("Rows count = " + rows);
        for(int i=0;i<=rows ;i++)
        {
            String BUname  = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("pointsDistributionBUDropDown"), i, 0);
            System.out.println("BU Name" + i + BUname);
            obj_pointsDistribution.selectBUDropDown(BUname);


        }

    }








    @Test(priority = 4, description = "Verify if the admin is able to provide points for their direct reportees", groups = {"Login", "Smoke Test", "Regression Test", "Admin", "Point Distribution"})
    @Description("This attempt aims to verify if the admin can provide points and validate those points using the database and UI after they have been provided.")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Admin"),
            @Feature("Points Distribution"),
            @Feature("Direct Reportee"),
            @Feature("Add points")
    })
    public void VerifyProvidePoints()
    {
      obj_pointsDistribution.clickAddPointsButton();
      String AddPointsProvideEmployee = obj_pointsDistribution.getNameFromAddPoints();
      String query = " SELECT  PointsEarned, LifetimePoints, PointsForDistribution FROM Employee " +
              "WHERE Manager ='testing.team_iat@ilink-systems.com' and isActive=1 and Name =?";
        Connection connection = obj_dataBaseConnect.dbconnect();

        try(PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1,AddPointsProvideEmployee);
            String expectedEarnedPoints = "";
            String expectedLifeTimePoints ="";
            String expectedPointsForDistribution ="";

            try(ResultSet resultSet  = preparedStatement.executeQuery())
            {
                while(resultSet.next())
                {
                    expectedEarnedPoints = resultSet.getString("PointsEarned");
                     expectedLifeTimePoints = resultSet.getString("LifetimePoints");
                    expectedPointsForDistribution = resultSet.getString("PointsForDistribution");
                }
            }
            String actualEarnedPoints = obj_pointsDistribution.getEarnedPointsFromAddPoints();
            String actualLifeTimePoints = obj_pointsDistribution.getLifeTimePointsFromAddPoints();
            String actualPointsForDistribution = obj_pointsDistribution.getPointsForDistributionFromAddPoints();


            Assert.assertEquals(actualEarnedPoints,expectedEarnedPoints,"Earned Points not Matched in Add Points");
            Assert.assertEquals(actualLifeTimePoints,expectedLifeTimePoints,"LifeTime points not matched in Add Points");
            Assert.assertEquals(actualPointsForDistribution,expectedPointsForDistribution,"points for Distribution is not matched");













        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }





















}
