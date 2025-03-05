package Cipherplus.Pages;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class earnPage extends BaseClass {


    By nav_earnLoc = By.linkText("Earn");
    By cat_generalLoc = By.xpath("//p[text()='General']");
    By cat_TALoc = By.xpath("//p[text()='TA Team']");
    By cat_EELoc = By.xpath("//p[text()='EE Team']");
    By cat_LAndDLoc = By.xpath("//p[text()='L&D Team']");
    By cat_WellnessLoc = By.xpath("//p[text()='Wellness']");
    By cat_AllCategoriesLoc = By.xpath("//p[text()='All Categories']");
    By filter_filterLoc = By.tagName("select");
    By searchBoxLoc = By.tagName("input");
    By earn_tableBodyLoc = By.className("earn-cat-tbody");
    By earn_tableContentsLoc = By.xpath("(//div[@class='earn-cat-tbody-item'])");
    By earn_tableTitleLoc = By.className("earn-body-title");
    By earn_tableDescriptionLoc = By.className("earn-body-des");
    By earn_tablePointLoc = By.className("earn-body-pt");
    By earn_tableApproverLoc = By.className("earn-body-app");
    By earn_tableApplyNowBtnLoc = By.xpath("(//button[text()='Apply'])");
    By applyButtons = By.xpath("(//button[@class='beautiful-button5'])");
    By titlesLoc = By.xpath("(//p[@class='earn-body-title'])");
    By actionOkButton = By.xpath("//button[@class='btn btn-primary']");
    By appliedSuccessfullMsg = By.xpath("//p[text()='Applied successfully']");
    By closeButton = By.className("close-button");
    By textBoxLoc = By.xpath("//input[@placeholder='Enter reason here']");











    @Step("Click the post category of :  {0} ")
    public void clickCategories(String category)
    {
        By postCategory = By.xpath(category);
        wait.until(ExpectedConditions.presenceOfElementLocated(postCategory)).click();
    }




    @Step("Click earn on Navigation bar")
    public void clickEarnNavBar()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(nav_earnLoc)).click();
    }


    @Step("Click the general category ")
    public void clickGenearlCategory()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(cat_generalLoc)).click();
    }


    @Step("Click the TA category ")
    public void clickTACategory()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(cat_TALoc)).click();
    }


    @Step("Click the EE category ")
    public void clickEECategory()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(cat_EELoc)).click();
    }

    @Step("Click the L&D category ")
    public void clickLandDCategory()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(cat_LAndDLoc)).click();
    }

    @Step("Click the wellness category ")
    public void clickWellnessDCategory()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(cat_WellnessLoc)).click();
    }

    @Step("Click the All categories category ")
    public void clickAllCategoriesCategory()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(cat_AllCategoriesLoc)).click();
    }



    @Step("Select the Drop of All points as : {0}")
   public void selectPoints(String pointsValues)
   {
       WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(filter_filterLoc));
       Select dropdown = new Select(element);
       dropdown.selectByVisibleText(pointsValues);
   }

    @Step("Enter text in searchBox as : {0}")
    public void enterTextSearchBox(String earnSearchValue)
    {
        WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLoc));
        element.clear();
        element.sendKeys(earnSearchValue);

    }


    @Step("Get earn table content ")
    public  List<Map<String,String>> getEarnTableContent()
    {

        List<Map<String,String>>earnList = new ArrayList<>();
         WebElement Table = wait.until(ExpectedConditions.presenceOfElementLocated(earn_tableBodyLoc));
         List<WebElement> tableRows = Table.findElements(earn_tableContentsLoc);
         for(int i=0;i<tableRows.size();i++)
         {
             Map<String, String> earnMap = new HashMap<>();
             String earnTableTitle = tableRows.get(i).findElement(earn_tableTitleLoc).getText();
             String earnTableDescription = tableRows.get(i).findElement(earn_tableDescriptionLoc).getDomAttribute("title");
             String earnTablePoint =  tableRows.get(i).findElement(earn_tablePointLoc).getText();
             String earnTableApprover = tableRows.get(i).findElement(earn_tableApproverLoc).getText();
             earnMap.put("Title",earnTableTitle);
             earnMap.put("Description",earnTableDescription);
             earnMap.put("Reward Point",earnTablePoint);
             earnMap.put("Approver",earnTableApprover);

             earnList.add(earnMap);

         }

     return earnList;

    }


    @Step("Click the Apply button in table")
    public void clickRandomApplyButton() throws InterruptedException {
        List<WebElement> buttons = BaseClass.driver.findElements(applyButtons);

        List<WebElement> titles = BaseClass.driver.findElements(titlesLoc);

        if(!buttons.isEmpty() && !titles.isEmpty())
        {
            Random random = new Random();
            int randomIndex = random.nextInt(buttons.size());
            WebElement randomApplyButton = buttons.get(randomIndex);
            String buttonTitle = titles.get(randomIndex).getText();
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomApplyButton);
            randomApplyButton.click();

            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(textBoxLoc));
            element.sendKeys("Testing");
            System.out.println("Clicked button title : " + buttonTitle);
        }else {
            System.out.println("No buttons or titles found.");
        }


    }


    @Step("Click the OK button")
    public void clickOkButton()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(actionOkButton)).click();
    }

    @Step("Verify the Success Message of 'Applied Successfully' ")
    public String getAppliedSuccessfullyMsg()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(appliedSuccessfullMsg)).getText();

    }


    @Step("Click the Close Event Sign Up")
    public void clickCloseButton()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(closeButton)).click();
    }

    @Step("Enter the Text in Event Sign Up")
    public void enterEventSignUpTextBox(String message)
    {

    }






















































}
