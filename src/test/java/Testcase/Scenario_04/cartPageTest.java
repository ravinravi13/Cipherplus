package Testcase.Scenario_04;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.CartPage;
import CipherplusAPI.Models.ResponseModel.LoggedUserResponse;
import CipherplusAPI.UserService.UserServices;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class cartPageTest extends BaseClass {


    CartPage obj_CartPage = new CartPage();

    UserServices obj_UserServices = new UserServices();





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

    }



    @Test(priority = 2, description = "verify a product in empty Cart section",groups = {"Cart","Smoke Test"})
    @Description("This test attempts verifies empty cart section user message")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("GiftStore "),
            @Feature("Cart Feature")})
    public void VerifyCartMessage()
    {
        obj_CartPage.clickCartIcon();
        String actual = obj_CartPage.getCartMessage();
        Assert.assertEquals(actual,"Your cart is empty!");
    }


    @Test(priority = 3, description = "Verify the product after I add it to the cart and check add to cart success message. ",groups = {"Cart","Smoke Test"})
    @Description("This test attempts verifies of name and points the product after I add it to the cart  and verify success message")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("GiftStore "),
            @Feature("Cart Feature")})
    public void VerifyAddProductInCart() throws InterruptedException {
        obj_CartPage.clickGiftStoreLink();
        obj_CartPage.clickViewAllButton();
        Thread.sleep(5000);
        String productName = obj_CartPage.extractSingleProductName();
        String productPoint = obj_CartPage.extractSingleProductPoint();

        obj_CartPage.clickSingleProductCartIcon();
        Assert.assertTrue(obj_CartPage.checkCartSuccessPopUp());
        Assert.assertEquals(obj_CartPage.getCartSuccessMessageText(),"Item added to cart successfully!");

        obj_CartPage.clickCartIcon();
        Thread.sleep(6000);
        String expectedProductName =obj_CartPage.cartExtractSingleProductName();
        String expectedProductPoint = obj_CartPage.cartExtractSingleProductPoint();

        Assert.assertEquals(productName,expectedProductName);
        Assert.assertEquals(productPoint,expectedProductPoint.trim());
    }

    @Test(priority = 4, description = "Verify the product delete. ",groups = {"Cart","Smoke Test"})
    @Description("This test attempts verify delete feature in cart and cross check with empty cart message display or not")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("GiftStore "),
            @Feature("Cart Feature")})
    public void VerifyDeleteProductInCart() throws InterruptedException {
        obj_CartPage.clickSingleProductDeleteIcon();
        obj_CartPage.clickCartCloseButton();
        Thread.sleep(2000);
        String actual = obj_CartPage.getCartMessage();
        Assert.assertEquals(actual,"Your cart is empty!");
    }


    @Test(priority = 5, description = "Verify total points,sub-total and Remaining points in cart page",groups = {"Cart","Smoke Test"})
    @Description("This test attempts verify total points,sub-total and Remaining points in cart page by adding product in cart ensure calculation displaying or not")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("GiftStore "),
            @Feature("Cart Feature")})
    public void VerifyPointsCart() throws InterruptedException {
        Response response = obj_UserServices.loginUser();
        LoggedUserResponse loggedUserResponse  = response.as(LoggedUserResponse.class);

        int avilablepointAPI = loggedUserResponse.getResult().getPointsEarned();

        obj_CartPage.clickGiftStoreLink();
        obj_CartPage.clickViewAllButton();
        obj_CartPage.clickSingleProductCartIcon();
        Thread.sleep(3000);
        obj_CartPage.clickSingleProductCartIcon();
        obj_CartPage.clickCartIcon();
        Thread.sleep(3000);

        String strActualSubTotalPoint = obj_CartPage.cartExtractSingleProductPoint();
        int actualSubTotalPoint = Integer.parseInt(strActualSubTotalPoint.trim());
        actualSubTotalPoint+=Integer.parseInt(obj_CartPage.cartExtractSingleProductPoint().trim());
        int expectedSubtotal = obj_CartPage.cartExtractSubTotalPoint();


        int actualTotalAvailablepointUI = obj_CartPage.cartExtractTotalAvailablePoint();

        int actualRemainingAvailablePoint = obj_CartPage.cartExtractRemainingTotalAvailablePoint();


        Assert.assertEquals(actualTotalAvailablepointUI,avilablepointAPI);
        Assert.assertEquals(actualSubTotalPoint,expectedSubtotal);
        Assert.assertEquals(actualTotalAvailablepointUI-actualSubTotalPoint,actualRemainingAvailablePoint);


    }



    @Test(priority = 6, description = "Verify the Checkout product ",groups = {"Cart","Smoke Test"})
    @Description("This test attempts verify checkout the product by choosing other option to enter address,pin code and phone number details")
    @Severity(SeverityLevel.CRITICAL)
    @Features({
            @Feature("GiftStore "),
            @Feature("Cart Feature")})
    public void VerifyCheckProduct() throws InterruptedException {
        obj_CartPage.clickProceedToClaimButton();
        obj_CartPage.clickOtherOption();
        obj_CartPage.enterAddress("ilink chennai office");
        obj_CartPage.enterPincode("600096");
        obj_CartPage.enterPhoneNumber("8610267329");
        obj_CartPage.clickSaveButton();

       Boolean actual= obj_CartPage.isDisplayCheckOutSucessMsg();
       Assert.assertTrue(actual);
       obj_CartPage.clickCloseButton();

    }




    @AfterClass(groups = {"BaseLogin"})
    public void teardown()
    {
        BaseClass.driver.quit();
    }























}
