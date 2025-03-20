package Cipherplus.Pages;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import jdk.dynalink.beans.StaticClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class cheerUpBuddyPage extends BaseClass {



  By nav_cheerUpBuddyLoc = By.linkText("Cheer Up Buddy");
  By dropDown_category = By.tagName("select");
  By allBadgesLoc = By.xpath("(//div[@class='badges'])");
  By badgesName = By.xpath("(//p[@class='badge-p'])");
  By btn_viewBadgeLoc = By.xpath("(//button[@class='view']//img)[3]");
  By btn_sendGiftLoc = By.xpath("(//img[@alt='gift'])[1]");
  By noBadgeFoundMsg = By.xpath("//td[text()='No badges matching the criteria']");
  By badgeCount = By.cssSelector("body > div.fade.cheerup-custom-modal.modal.show > div > div > div.modal-body > div > div > div");
  By closeViewBadge = By.className("btn-close");
  By employeeEmailIdLoc = By.id("employeeEmail");
  By getEmployeeEmailIdCCLoc = By.id("cc");
  By commentsLoc = By.id("comments");
  By Btn_cheerUpLoc = By.xpath("//button[text()='CheerUp']");
  By suggestMailId = By.xpath("//ul[@class='suggestions-list']//li[1]");
  By giveBadgeForm = By.className("modal-content");
  By leaderBoardBadgeNameLoc = By.xpath("//h4[contains(@class,'badge-leaderboard-title text-center')]");
  By leaderBoardHighestBadgeEmpCountLoc = By.xpath("//div[@class='text-center mt-2']//b[1]");
  By listViewBadges = By.xpath("(//img[@alt='view'])");
 By  displayBadgeRowCount = By.xpath("//table[contains(@class,'display-badges-table table')]/tbody[1]/tr");

 public static int randomIndex;






  @Step("Check give badge form is display")
  public boolean checkGiveBadgeForm()
  {
    return wait.until(ExpectedConditions.presenceOfElementLocated(giveBadgeForm)).isDisplayed();
  }




  @Step("Check Cheer UP got disable")
  public boolean checkCheerUpDisable()
  {
   WebElement element=  wait.until(ExpectedConditions.presenceOfElementLocated(Btn_cheerUpLoc));
      return element.getDomAttribute("disabled")!=null;
  }




  @Step("click Cheer up Button")
  public void clickCheerUpButton()
  {
    wait.until(ExpectedConditions.presenceOfElementLocated(Btn_cheerUpLoc)).click();
  }



  @Step("Enter employee mail Id in CC : {0}")
  public void enterEmployeeMailIdCC(String mailIdCC)
  {
    WebElement BadgeMailIDCC = wait.until(ExpectedConditions.presenceOfElementLocated(getEmployeeEmailIdCCLoc));
    BadgeMailIDCC.clear();
    BadgeMailIDCC.sendKeys(mailIdCC);
  }

  @Step("Enter comment : {0}")
  public void enterComments(String Comment)
  {
    WebElement badgeComment = wait.until(ExpectedConditions.presenceOfElementLocated(commentsLoc));
    badgeComment.clear();
    badgeComment.sendKeys(Comment);
  }




  @Step("Enter employee mail Id as : {0}")
  public void enterEmployeeMailId(String mailId)
  {
    WebElement BadgeMailID = wait.until(ExpectedConditions.presenceOfElementLocated(employeeEmailIdLoc));
    BadgeMailID.clear();
    BadgeMailID.sendKeys(mailId);
  }



  @Step("click suggestion Mail Id")
  public void clickSuggestionMailId()
  {
    wait.until(ExpectedConditions.presenceOfElementLocated(suggestMailId)).click();
  }



  @Step("Get the Badges count")
  public String getBadgesCount()
  {
    return wait.until(ExpectedConditions.presenceOfElementLocated(badgeCount)).getText();
  }

  @Step("Click Close icon in view badges")
  public void clickCloseViewButton()
  {
    wait.until(ExpectedConditions.presenceOfElementLocated(closeViewBadge)).click();
  }


  @Step("Click view badge button in badges")
  public void clickViewBadgeButton()
  {
    wait.until(ExpectedConditions.presenceOfElementLocated(btn_viewBadgeLoc)).click();
  }

  @Step("Click view badge button in badges")
  public void clickSendBadgeButton(String BadgePath)
  {
    wait.until(ExpectedConditions.presenceOfElementLocated(btn_sendGiftLoc)).click();
  }


  @Step("check no badge message in Display badge form")
  public boolean checkNoBadgeFound()
  {
    return wait.until(ExpectedConditions.presenceOfElementLocated(noBadgeFoundMsg)).isDisplayed();
  }






  @Step("Click Cheer up Buddy navigation")
  public void clickCheepUpBuddy()
  {
    wait.until(ExpectedConditions.presenceOfElementLocated(nav_cheerUpBuddyLoc)).click();
  }


  @Step("Select badge  category :  {0}")
  public void  selectBadgesCategory(String Category)
  {
    Select badgesCategory = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(dropDown_category)));
    badgesCategory.selectByVisibleText(Category);
  }


  @Step("Click the Browser alert message")
  public String AlertHandling()
  {

    Alert alert = BaseClass.driver.switchTo().alert();
    String alertMessage = alert.getText();
    alert.accept();
    return alertMessage;

  }


  public void displayBadgeDetails()
  {
    List<WebElement> viewButtons = BaseClass.driver.findElements(listViewBadges);
    List<WebElement> badgeNames = driver.findElements(By.xpath("//p[@class='badge-p']"));

    Random random = new Random();
     randomIndex = random.nextInt(viewButtons.size());
    WebElement viewBtn = viewButtons.get(randomIndex);

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewBtn);
    viewBtn.click();


  }


  public String getBadgeName()
  {
      List<WebElement> badgeNames = driver.findElements(By.xpath("//p[@class='badge-p']"));
      return badgeNames.get(randomIndex).getText();
  }


  public int getDisplayingBadgeRowCount()
  {
      List<WebElement> TableRow = BaseClass.driver.findElements(displayBadgeRowCount);
      return TableRow.size();
  }





















  public  Map<String,String> getBadgesDetails()
  {
    Map<String,String> BadgesDetails  = new HashMap<>();
    List<WebElement> badges = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allBadgesLoc));
    List<WebElement> badgeNames = driver.findElements(By.xpath("//p[@class='badge-p']"));
    List<WebElement> categories = driver.findElements(By.xpath("(//div[@class='description-category']//span)"));


    System.out.println("Total badges count = "+ badges.size());
    System.out.println("Total badgeNames count = "+ badgeNames.size());
    System.out.println("Total categories count = "+ categories.size());

     for(int i=0;i<badges.size();i++)
     {
       String badgeName = badgeNames.get(i).getText();
       String badgeCategoryName = categories.get(i).getText();
       BadgesDetails.put("Badge Name ",badgeName);
       BadgesDetails.put("Category ",badgeCategoryName);
     }

    System.out.println("Badges Details count = "+BadgesDetails.size());
     return BadgesDetails;


   }


   @Step("Click the Badge")
   public void clickBadge()
   {
      WebElement BadgeElement = wait.until(ExpectedConditions.presenceOfElementLocated(badgesName));
       String BadgeNameString = BadgeElement.getText();
     BadgeElement.click();

   }

  @Step("Check selected Badge Name in Badge leaderBoard")
  public void clickBadgeLeaderBoard()
  {
    WebElement BadgeElement = wait.until(ExpectedConditions.presenceOfElementLocated(leaderBoardBadgeNameLoc));
    String BadgeNameString = BadgeElement.getText();
    BadgeElement.click();

  }

  @Step("Check Highest Badge of employee")
  public String getBadgeLeaderboardHighestBadgeEmpName()
  {
    WebElement FirstEmpBadgeCount = wait.until(ExpectedConditions.presenceOfElementLocated(leaderBoardHighestBadgeEmpCountLoc));
      return FirstEmpBadgeCount.getText();
  }

































  }
























