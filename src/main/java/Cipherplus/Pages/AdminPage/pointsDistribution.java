package Cipherplus.Pages.AdminPage;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    By tableDirectReport = By.xpath("//table[contains(@class,'table table-borderless')]//tbody//tr");
    By tableDirectReportData = By.xpath("(//table[contains(@class,'table table-borderless')]//td)");
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

    @Step("Get the Direct Reports from UI Side")
    public List<List<String>> getDirectReportees(){
        List<List<String>> actualDirectReport = new ArrayList<>();

        // Locate the table
        WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[contains(@class,'table table-borderless direct-reportees')]")));
        System.out.println("Table found.");

        // Locate the rows
        List<WebElement> rows = table.findElements(By.xpath(".//tr")); // Use '.' to limit the search to the current table
        System.out.println("Number of rows found: " + rows.size());

        // Loop through each row
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td")); // Use '.' to limit the search to the current row
            List<String> rowData = new ArrayList<>();

            // Check if cells are found
            if (!cells.isEmpty()) {
                for (int i = 0; i < cells.size(); i++) {

                    String value = cells.get(i).getText().trim();
                    // Only add the value if it's not "Add Points"
                    if (!value.equals("Add Points")) {
                        rowData.add(value);
                    }
                }

                // Only add rowData if it contains valid data
                if (!rowData.isEmpty()) {
                    System.out.println("Row data: " + rowData);
                    actualDirectReport.add(rowData);
                }
            }
        }

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
