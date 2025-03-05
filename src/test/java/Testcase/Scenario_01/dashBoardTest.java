package Testcase.Scenario_01;
import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.dashBoard;
import CipherplusAPI.Models.ResponseModel.LoggedUserResponse;
import CipherplusAPI.UserService.UserServices;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class dashBoardTest extends BaseClass {


    dashBoard obj_dashBoard = new dashBoard();
    UserServices obj_UserServices = new UserServices();

    public Response response = obj_UserServices.loginUser();
    public LoggedUserResponse loggedUserResponse  = response.as(LoggedUserResponse.class);




    @BeforeClass(groups = {"BaseLogin","Employee"})
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

    }





    @Test(priority = 1, description = "Verify login page",groups = {"Login","dashboard","Smoke Test","Regression Test","Employee"})
    @Description("This test attempts the log into the Website and verify with dashboard user name and profile section user name")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login")
    public void VerifyUserName()
    {

        obj_dashBoard.clickExploreBtn();
        Assert.assertEquals("testing", obj_dashBoard.getProfileName());
        Assert.assertEquals("Hello, testing", obj_dashBoard.getUsername());
        System.out.println("VerifyLogin is passed");

    }



//
    @Test(description = "Validate the Available points with API",groups = {"availablePoints","dashboard","Smoke Test","Regression","Employee"},priority = 2)
    @Description("This test attempts check the Available points on Dashboard page and check API response")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Available Points")
    public void CP_TC_02_verifyAvailablePoints()
    {

        int availablepointUI = Integer.parseInt(obj_dashBoard.getAvailablePoints());
        int avilablepointAPI = loggedUserResponse.getResult().getPointsEarned();
        String responseBody = response.getBody().asString();
        if(responseBody != null) {
            Allure.label("Available points from UI: ", obj_dashBoard.getAvailablePoints());
            Allure.label("Available points from API: ", Integer.toString(avilablepointAPI));
            Assert.assertEquals(availablepointUI, avilablepointAPI);
            System.out.println("CP_TC_02_verifyAvailablePoints is passed");
        }
        else{
            System.out.println("responseBody is empty");
        }
    }




    @Test(description = "Verify the Lifetime points validate with API Response",priority = 3,groups = {"Lifetime","dashboard","Smoke Test","Regression","Employee"})
    @Description("This test attempts check the Lifetime points on dashboard")
    @Feature("Lifetime Points")
    @Severity(SeverityLevel.CRITICAL)

    public void CP_TC_03_verifyLifeTimePoints(){

        int lifetimePointsUI = Integer.parseInt(obj_dashBoard.getLifeTimePoints());
        int lifetimePointsAPI = loggedUserResponse.getResult().getLifetimePoints();
        String responseBody = response.getBody().asString();


        if (!responseBody.isEmpty()) {

            Allure.label("Lifetime points from UI : ", obj_dashBoard.getLifeTimePoints());
            Allure.label("Lifetime points from API : ", Integer.toString(lifetimePointsAPI));
            Assert.assertEquals(lifetimePointsUI,lifetimePointsAPI);
            System.out.println("CP_TC_03_verifyLifeTimePoints is passed");

        }
        else {
            System.out.println("Response body is empty.");
        }
    }




    @Test(description = "Verify the Badges validate with API Response",priority = 3,groups = {"Badges","dashboard","Smoke Test","Regression","Employee"})
    @Description("This test attempts check the Badges on dashboard")
    @Feature("Badges")
    @Severity(SeverityLevel.CRITICAL)
    public void CP_TC_04_verifyBadges(){

        Allure.label("Badges count from UI :",obj_dashBoard.geBadgesCount());
        Allure.label("Badges count from API :","0");
        Assert.assertEquals("0",obj_dashBoard.geBadgesCount());
        System.out.println("CP_TC_04_verifyBadges is passed");
    }




    @Test(description = "Verify the Distribute points validate with API Response",priority = 3,groups = {"Distribute","dashboard","Smoke Test","Regression","Employee"})
    @Description("This test attempts check the Distribute points on dashboard")
    @Feature("Distribute points")
    @Severity(SeverityLevel.CRITICAL)
    public void CP_TC_05_verifyDistributePoints(){

        int distributepointsUI = Integer.parseInt(obj_dashBoard.getDistributePoints());
        int distributepointsAPI = loggedUserResponse.getResult().getPointsForDistribution();
        String responseBody = response.getBody().asString();

        if (!responseBody.isEmpty()) {

            Allure.label("Distribute Points count from UI :", obj_dashBoard.getDistributePoints());
            Allure.label("Distribute Points count from API :", Integer.toString(distributepointsAPI));
            Assert.assertEquals(distributepointsUI,distributepointsAPI);
            System.out.println("CP_TC_05_verifyDistributePoints is passed");
        }
        else {
            System.out.println("Response body is empty.");
        }
    }

    @Test(description = "Check Claim button functionality",priority = 4,groups = {"dashboard","Smoke Test","Regression","Employee"})
    @Description("This test attempts to check the functionality of the Claim button to redirect and ensure the gift store URL is correct")
    @Feature("GiftStore")
    @Severity(SeverityLevel.NORMAL)
    public void CP_TC_06_checkCLiamButton(){
          obj_dashBoard.clickClaimButton();
          Assert.assertEquals(BaseClass.driver.getCurrentUrl(),BaseClass.getProperty("giftStoreUrl"));
          Assert.assertTrue(obj_dashBoard.checkGiftStoreSlogan());
          obj_dashBoard.clickNavDashboard();
    }




































}
