package Cipherplus.Pages.AdminPage;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageGifts extends BaseClass {


    int bookCatId =1;
    int clothingCatId = 2;
    int electronicCatId = 3;
    int HealthAndFitnessCatId = 4;
    int stationaryCatId = 5;
    int OrganicStoreCatId = 6;
    int eVouchersCatId = 7;
    int plantCatId = 8;
    int HomeApplianceCatId = 9;




    By btn_AddLoc = By.xpath("//button[text()='ADD']");
    By searchBoxLoc = By.xpath("//input[@placeholder='Search for gifts...']");
    By drop_allCategoryLoc = By.xpath("(//select[@class='form-select'])[1]");
    By adminLinkLoc = By.linkText("Admin");
    By manageGiftLoc = By.xpath("//h4[text()='Manage Gifts']");





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
    public void enterProductName(String productname)
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

















































}
