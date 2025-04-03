package Testcase.Admin.Scenario_07;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.AdminPage.ManageGifts;
import Cipherplus.Pages.dashBoard;
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

public class ManageGiftsTest extends BaseClass {

    dashBoard obj_dashBoard = new dashBoard();
    ManageGifts obj_ManageGifts = new ManageGifts();
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
        obj_ManageGifts.clickAdminNav();
       obj_ManageGifts.clickManageGift();
    }



    @Test
    public void VerifyAllCategoryProduct() throws IOException {

        int rows = readExcel.getRowCount(System.getProperty("user.dir")+BaseClass.getProperty("excelFilepath"),BaseClass.getProperty("manageGiftAllCategorySheetName"));
        String query;
        for(int i=1;i<=rows;i++)
        {
            String categoryName = readExcel.getCellData(System.getProperty("user.dir")+BaseClass.getProperty("excelFilepath"),BaseClass.getProperty("manageGiftAllCategorySheetName"),i,0);
            obj_ManageGifts.selectProductCategeory(categoryName);

            switch (categoryName)
            {
                case "Books":
                    query="select Name,Description,LocationId,Cost,Quantity,'?' as Category from Reward where CategoryId=?";
                    Connection connection = obj_dataBaseConnect.dbconnect();
            }

        }
    }



































    @AfterClass(groups = {"BaseLogin"})
    public void teardown()
    {
        BaseClass.driver.quit();
    }



















}
