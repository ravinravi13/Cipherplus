package Cipherplus.Pages;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class leaderBoard extends BaseClass {








    By lnk_leaderBoardLoc = By.linkText("Leaderboard");
    By  searchLoc = By.tagName("input");





    @Step("Click the LeaderBoard navigation bar")
    public void clickLeaderBoard(){

        wait.until(ExpectedConditions.presenceOfElementLocated(lnk_leaderBoardLoc)).click();
    }


    @Step("Enter the text in leaderboard section : {0}")
    public void enterTextSearchBox(String text){

        wait.until(ExpectedConditions.presenceOfElementLocated(searchLoc)).sendKeys(text);
    }











}
