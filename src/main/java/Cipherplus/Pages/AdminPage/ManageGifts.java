package Cipherplus.Pages.AdminPage;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

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










    @Step("Click the Add Button")
    public void clickAdd()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_AddLoc)).click();
    }












































}
