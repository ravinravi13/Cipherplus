package Testcase.Scenario_06;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.CartPage;
import Cipherplus.Pages.cheerUpBuddyPage;
import Utilities.dataBaseConnect;
import Utilities.readExcel;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cheerUpBuddyTest extends BaseClass {


    cheerUpBuddyPage obj_cheerUpBuddyPage = new cheerUpBuddyPage();
    CartPage obj_CartPage = new CartPage();
    dataBaseConnect obj_dataBaseConnect =new dataBaseConnect();


    public int count = 0;


    @BeforeTest(groups = "BaseLogin")
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
        obj_CartPage.clickExploreBtn();
        obj_cheerUpBuddyPage.clickCheepUpBuddy();
    }



  //  @Test(priority = 1, description = "Verify the Badge count and no badges as message 'No badges matching the criteria' ")
    @Description("This test aims to ensure the usability of testing badge counts and the absence of badge messages. The precondition is that the badge count should be 0")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("Cheer up Buddy "),
            @Feature("View Badge")})
    public void VerifyBadgeCount() throws SQLException {

        int rows = readExcel.getRowCount(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("cheerUpBuddyExcelSheetName"));
        for (int i = 1; i <= rows; i++) {
            obj_cheerUpBuddyPage.clickCheepUpBuddy();
            obj_cheerUpBuddyPage.displayBadgeDetails();
            String BadgeName = obj_cheerUpBuddyPage.getBadgeName();
            System.out.println("Badge name = "+ BadgeName);
            String actualBadgeCount = obj_cheerUpBuddyPage.getBadgesCount();
            String[] split = actualBadgeCount.split(":");
            if (split[1].trim().equals("0")) {
                boolean actualNoBadgeResult = obj_cheerUpBuddyPage.checkNoBadgeFound();
                Assert.assertTrue(actualNoBadgeResult);
                obj_cheerUpBuddyPage.clickCloseViewButton();

            } else {

//                Connection connection = obj_dataBaseConnect.dbconnect();
//                String query = "select  GivenDate,comments from EmployeeBadges where BadgeName =? and  GivenBy=6";
//                PreparedStatement pstmt = connection.prepareStatement(query);
//                pstmt.setString(1, BadgeName);
//                ResultSet rs = pstmt.executeQuery();
//                while (rs.next())
//                {
//
//                }
//
//
//
//                int tableRowCount = obj_cheerUpBuddyPage.getDisplayingBadgeRowCount();
//
//
//
//                Assert.assertEquals(tableRowCount,);

                obj_cheerUpBuddyPage.clickCloseViewButton();


            }


        }
    }


   // @Test(priority = 2, description = "Verify to send badge all three categories' ")
    @Description("This test aims to ensure that badges of three categories are sent to raveendran.manickam@ilink-systems.com and that an entry is made in the view badge and badge count.")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Cheer up Buddy "),
            @Feature("View Badge")})

    public void VerifySendBadgeOfNegativeSide() throws IOException, InterruptedException {

        int rows = readExcel.getRowCount(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("cheerUpBuddyExcelSheetName"));
        System.out.println("Rows count = " + rows);
        for (int i = 1; i <= rows; i++) {
            String BadgeCategory = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("cheerUpBuddyExcelSheetName"), i, 0);
            System.out.println("Badge category " + i + BadgeCategory);
            obj_cheerUpBuddyPage.selectBadgesCategory(BadgeCategory);
            String SendBadge = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("cheerUpBuddyExcelSheetName"), i, 1);
            Thread.sleep(2000);
            obj_cheerUpBuddyPage.clickSendBadgeButton(SendBadge);
//                System.out.println("The Xpath of "+ i +SendBadge );
            int CheerUpFormExcelSheetNameRows = readExcel.getRowCount(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"));
            for (int j = 1; j <= CheerUpFormExcelSheetNameRows - 2; j++) {
                String EmployeeMailId = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 0);
                obj_cheerUpBuddyPage.enterEmployeeMailId(EmployeeMailId);

                String EmployeeMaildCC = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 1);
                obj_cheerUpBuddyPage.enterEmployeeMailIdCC(EmployeeMaildCC);

                String Comments = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 2);
                obj_cheerUpBuddyPage.enterComments(Comments);

                obj_cheerUpBuddyPage.clickCheerUpButton();
                Thread.sleep(8000);
                String actualAlertMsg = obj_cheerUpBuddyPage.AlertHandling();

                if (actualAlertMsg.equals("Employee email does not match any record.")) {
                    Assert.assertEquals(actualAlertMsg, BaseClass.getProperty("EmailIdNotMatch"));
                    System.out.println(BaseClass.getProperty("EmailIdNotMatch") + "is passed");
                    readExcel.setCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4, "Passed");
                    readExcel.fillGreenColor(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4);
                    count++;
                    System.out.println("Count = " + count);
                } else if (actualAlertMsg.equals("Employee email and comments are required.")) {
                    Assert.assertEquals(actualAlertMsg, BaseClass.getProperty("MissingFields"));
                    System.out.println(BaseClass.getProperty("MissingFields") + " is Passed");
                    readExcel.setCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4, "Passed");
                    readExcel.fillGreenColor(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4);
                    count++;
                    System.out.println("Count = " + count);
                } else {
                    readExcel.setCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4, "Failed");
                    readExcel.fillRedColor(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4);
                }

            }
            obj_cheerUpBuddyPage.clickCloseViewButton();
        }


    }


   // @Test(priority = 3, description = "Verify to send badge all three categories' ")
    @Description("This test aims to ensure that badges of three categories are sent to raveendran.manickam@ilink-systems.com and that an entry is made in the view badge and badge count.")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("Cheer up Buddy "),
            @Feature("View Badge")})

    public void VerifySendBadgeOfPositiveSide() throws IOException, InterruptedException {

        int rows = readExcel.getRowCount(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("cheerUpBuddyExcelSheetName"));
        System.out.println("Rows count = " + rows);
        for (int i = 1; i <= rows; i++) {
            String BadgeCategory = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("cheerUpBuddyExcelSheetName"), i, 0);
            System.out.println("Badge category " + i + BadgeCategory);
            obj_cheerUpBuddyPage.selectBadgesCategory(BadgeCategory);
            String SendBadge = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("cheerUpBuddyExcelSheetName"), i, 1);
            Thread.sleep(2000);
            obj_cheerUpBuddyPage.clickSendBadgeButton(SendBadge);
//                System.out.println("The Xpath of "+ i +SendBadge );
            int CheerUpFormExcelSheetNameRows = readExcel.getRowCount(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"));
            for (int j = CheerUpFormExcelSheetNameRows - 1; j <= CheerUpFormExcelSheetNameRows; j++) {

                String EmployeeMailId = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 0);
                obj_cheerUpBuddyPage.enterEmployeeMailId(EmployeeMailId);

                String EmployeeMaildCC = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 1);
                obj_cheerUpBuddyPage.enterEmployeeMailIdCC(EmployeeMaildCC);

                String Comments = readExcel.getCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 2);
                obj_cheerUpBuddyPage.enterComments(Comments);

                obj_cheerUpBuddyPage.clickCheerUpButton();


                if (!obj_cheerUpBuddyPage.checkGiveBadgeForm()) {
                    boolean isDisplayForm = obj_cheerUpBuddyPage.checkGiveBadgeForm();
                    Assert.assertFalse(isDisplayForm);
                    readExcel.setCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4, "Passed");
                    readExcel.fillGreenColor(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4);
                    System.out.println("Badge is passed and button is enabled");
                } else if (obj_cheerUpBuddyPage.checkCheerUpDisable()) {
                    boolean isCheerUpBtn = obj_cheerUpBuddyPage.checkCheerUpDisable();
                    if (isCheerUpBtn) {
                        System.out.println("The button is disabled.");
                    } else {
                        System.out.println("The button is enabled.");
                    }
                } else {
                    readExcel.setCellData(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4, "Failed");
                    readExcel.fillRedColor(System.getProperty("user.dir") + BaseClass.getProperty("excelFilepath"), BaseClass.getProperty("CheerUpBuddySendBadgeFormExcelSheetName"), j, 4);
                }


            }
        }

    }


    @Test
    public void dummy() throws InterruptedException {
        obj_cheerUpBuddyPage.validateBadgeLeaderboard();
    }





    @AfterClass(groups = {"BaseLogin"})
    public void teardown()
    {
        BaseClass.driver.quit();
    }























//    @Test
//    public void verifyBadgesDetails()
//    {
//        Map<String,String> expectedBadgesDetails = new HashMap<>();
//        Badges obj_Badges = new Badges();
//        Response response = obj_Badges.getBadgesDetails();
//        BadgesResponse badgesResponse = response.as(BadgesResponse.class);
//
//        obj_cheerUpBuddyPage.clickCheepUpBuddy();
//        Map<String, String> actualBadgeDetails = obj_cheerUpBuddyPage.getBadgesDetails();
//
//        System.out.println("The response size "+badgesResponse.getResult().size());
//
//        List<BadgesResponse.Details> detailsList = badgesResponse.getResult();
//
//       for(int i=0;i<detailsList.size();i++)
//       {
//           BadgesResponse.Details details = detailsList.get(i);
//           expectedBadgesDetails.put("Badge Name", details.getName());
//           if (details.getCategoryId() == 1) {
//               expectedBadgesDetails.put("Category", "Go-getter");
//           } else if (details.getCategoryId() == 2) {
//               expectedBadgesDetails.put("Category", "Thinker");
//           } else {
//               expectedBadgesDetails.put("Category", "brin");
//           }
//       }
//
//
//
//        System.out.println("After print response The response size "+badgesResponse.getResult().size());
//        System.out.println("expectedBadgesDetails "+expectedBadgesDetails);
//        System.out.println("actualBadgeDetails "+actualBadgeDetails);
//
//
//
//
//    }


}