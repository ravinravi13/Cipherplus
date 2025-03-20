package Cipherplus.Pages.AdminPage;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pointsDistribution extends BaseClass {




    By adminLinkLoc = By.linkText("Admin");
    By pointsDistributionLoc = By.xpath("//h4[text()='Points Distribution']");
    By dropBU = By.xpath("//button[@class='btn dropdown-toggle']");
    By directReportLoc =  By.xpath("//span[text()='Direct Reportees']");
    By tableDirectReport = By.xpath("(//table[contains(@class,'table table-borderless')]//tr)[2]");
    By tableDirectReportData = By.xpath("(//table[contains(@class,'table table-borderless')]//tr)[2]");
    By pointsEarnedLabel = By.xpath("//div[text()='Points Earned']");
    By pointForDistributionLabel = By.xpath("//div[text()='Points for Distribution']");
    By lifePointsLabel = By.xpath("//div[text()='Lifetime Points']");
    By txt_pointsEarnedLabel = By.xpath("(//div[@class='col pointsdist-cards']//div)[1]");
    By txt_pointForDistributionLabel = By.xpath("(//div[@class='col pointsdist-cards']//div)[3]");
    By txt_lifePointsLabel = By.xpath("(//div[@class='col pointsdist-cards']//div)[5]");




    @Step("Click Admin Link in Navigation Bar")
    public void clickAdminNav()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(adminLinkLoc)).click();
    }

    @Step("Click Points Distribution Conatiner")
    public void clickPointsDistribution()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(pointsDistributionLoc)).click();
    }


    @Step("Select BU dropdown as :  {0}")
    public void selectBUDropDown(String BUName)
    {
      WebElement BUDropdown =  wait.until(ExpectedConditions.presenceOfElementLocated(dropBU));
        Select BUdrop = new Select (BUDropdown);
        BUdrop.selectByVisibleText("");
    }


    @Step("Click Direct Reportees button")
    public void clickDirectReporteeButton()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(directReportLoc)).click();
    }



//   public void getDirectReportees()
//   {
//
//
//     List<Map<String,String>> actualDirectReport = new ArrayList<>();
//
//       WebElement directReportable = wait.until(ExpectedConditions.presenceOfElementLocated(tableDirectReport));
//       List<WebElement> tableContent = BaseClass.driver.findElements(tableDirectReportData);
//
//       for(int i=0;i<tableContent.size();i++)
//       {
//
//
//
//
//       }
//
//
//
//
//   }


    @Step("Get Direct Reportees from UI table")
    public List<Map<String, String>> getDirectReportees() {
        List<Map<String, String>> actualDirectReport = new ArrayList<>();
        List<WebElement> tableContent = BaseClass.driver.findElements(tableDirectReportData);

        // Ensure there are enough rows to prevent IndexOutOfBoundsException
        for (WebElement row : tableContent) {
            // Get the text of each cell in the row
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Ensure there are cells in the row
            if (!cells.isEmpty()) {
                Map<String, String> reporteeData = new HashMap<>();

                // Assuming the first cell contains "Name", second contains "Email", and so on
                if (cells.size() >= 6) { // Ensure there are enough cells
                    reporteeData.put("Name", cells.get(0).getText());
                    reporteeData.put("Email", cells.get(1).getText());
                    reporteeData.put("Points for Distribution", cells.get(2).getText());
                    reporteeData.put("Earned Points", cells.get(3).getText());
                    reporteeData.put("Lifetime Points", cells.get(4).getText());
                    reporteeData.put("Bu", cells.get(5).getText());
                    // Handle case for BU

                    actualDirectReport.add(reporteeData); // Add to the list
                }
            }
        }
        System.out.println("Actual : \n" +actualDirectReport);
        return actualDirectReport;
    }



   @Step("Locate Points Earned label")
   public String locatePointsEarned()
   {
       WebElement pointEarnedWebElement = wait.until(ExpectedConditions.presenceOfElementLocated(pointsEarnedLabel));
       return pointEarnedWebElement.getText();
   }

    @Step("Locate Points for Distribution label")
    public String locatePointsForDistribution()
    {
        WebElement pointForDistributionWebElement = wait.until(ExpectedConditions.presenceOfElementLocated(pointForDistributionLabel));
        return pointForDistributionWebElement.getText();
    }

    @Step("Locate Lifetime Points label")
    public String locateLifePoints()
    {
        WebElement lifePointsWebElement = wait.until(ExpectedConditions.presenceOfElementLocated(lifePointsLabel));
        return lifePointsWebElement.getText();
    }


    @Step("Get points of points Earned")
    public String getPointsEarned()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_pointsEarnedLabel)).getText();
    }

    @Step("Get points of points for distribution")
    public String getPointsForDistribution()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_pointForDistributionLabel)).getText();
    }

    @Step("Get points of Life time points")
    public String getLifetimePoints()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_lifePointsLabel)).getText();
    }




}
