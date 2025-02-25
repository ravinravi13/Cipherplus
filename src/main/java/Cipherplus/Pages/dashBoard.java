package Cipherplus.Pages;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class dashBoard extends BaseClass {




   By txt_profileNameLoc = By.xpath("//button[contains(@class,'profile-drop btn-sm')]");
   By txt_userNameLoc = By.xpath("//div[@class='mt-5 dashboard-title']//h5[1]");
   By btn_exploreLoc = By.xpath("//button[@class='explore-button']");
   By txt_availablePointLoc = By.xpath("//div[@class='ms-2 card1-pts']//h4[1]");
   By txt_lifeTimePointsLoc = By.xpath("//div[@class='card2']//h4[1]");
   By txt_badgesCountLoc = By.xpath("//div[@class='card3']//h4[1]");
   By txt_distributePointsLoc = By.xpath("//div[@class='card4']//h4[1]");


















    @Step("Get the Profile name of home page from user from right corner")
    public String getProfileName() {
        
        String profileTxt = "";
        try {
            Thread.sleep(2000);
            profileTxt = wait.until(ExpectedConditions.presenceOfElementLocated(txt_profileNameLoc)).getText();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return profileTxt;
    }

    @Step("Get the User name of home page")
    public String getUsername() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_userNameLoc)).getText();
    }

    @Step("Click the 'Explore' button on Homepage")
    public void clickExploreBtn()  {
        wait.until(ExpectedConditions.elementToBeClickable(btn_exploreLoc)).click();
    }

    @Step("Get the available points from dashboard page near claim button")
    public String getAvailablePoints(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_availablePointLoc)).getText();
    }

    @Step("Get the available points from dashboard page")
    public String getLifeTimePoints(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_lifeTimePointsLoc)).getText();
    }

    @Step("Get the Badges count from dashboard page")
    public String geBadgesCount(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_badgesCountLoc)).getText();
    }

    @Step("Get the Distribute points from dashboard page")
    public String getDistributePoints(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_distributePointsLoc)).getText();
    }









    }



















