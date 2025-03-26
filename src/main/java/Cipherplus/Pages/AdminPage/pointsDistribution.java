package Cipherplus.Pages.AdminPage;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

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
    By Btn_AddPoints = By.xpath("(//button[@class='add-points-point-dist'])");
    By txt_Name = By.xpath("//div[@class='row mb-2']//div[1]");
    By txt_EarnedPointAddPoints = By.xpath("(//div[@class='col points-earned-card'])[1]");
    By txt_LifetimePointsAddPoints = By.xpath("(//div[@class='col lifetime-points-card'])[1]");
    By txt_pointsForDistributionAddPoints = By.xpath("(//div[@class='col points-for-distribution-card'])[1]");














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
    public List<List<String>> getDirectReportees() {
        List<List<String>> actualDirectReport = new ArrayList<>();

        // Locate the table using JavaScript Executor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement table = (WebElement) js.executeScript("return document.querySelector('table.table.table-borderless.direct-reportees');");
        System.out.println("Table found.");

        // Locate the rows using JavaScript Executor
        List<WebElement> rows = (List<WebElement>) js.executeScript("return arguments[0].getElementsByTagName('tr');", table);
        System.out.println("Number of rows found: " + rows.size());

        // Loop through each row, but limit to 30 rows
        int rowCount = 0; // Counter for the number of rows processed
        for (WebElement row : rows) {
            if (rowCount >= 30) { // Check if we've already processed 30 rows
                break; // Exit the loop if we have
            }

            List<WebElement> cells = (List<WebElement>) js.executeScript("return arguments[0].getElementsByTagName('td');", row);
            List<String> rowData = new ArrayList<>();

            // Check if cells are found
            if (!cells.isEmpty()) {
                for (WebElement cell : cells) {
                    String value = (String) js.executeScript("return arguments[0].innerText.trim();", cell);
                    // Only add the value if it's not "Add Points"
                    if (!value.equals("Add Points")) {
                        rowData.add(value);
                    }
                }

                // Only add rowData if it contains valid data
                if (!rowData.isEmpty()) {
//                     System.out.println("Row data: " + rowData);
                     actualDirectReport.add(rowData);
                    rowCount++; // Increment the counter after adding a valid row
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


    @Step("Click the Add points Button")
    public void clickAddPointsButton() {
        // Wait for the buttons to be present
        List<WebElement> buttons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Btn_AddPoints));

        // Get the number of buttons found
        int buttonLength = buttons.size();

        // Check if there are any buttons available
        if (buttonLength > 0) {
            // Generate a random index to select a button
            Random random = new Random();
            int randomIndex = random.nextInt(buttonLength);

            // Click the randomly selected button
            buttons.get(randomIndex).click();
        } else {
            System.out.println("No buttons found to click.");
        }
    }


    @Step("Get Name from UI who to provide")
    public String  getNameFromAddPoints()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_Name)).getText();

    }

    @Step("Check Earned points who to Provide UI side")
    public String getEarnedPointsFromAddPoints()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_EarnedPointAddPoints)).getText();
    }

    @Step("Check Lifetime points who to Provide UI side")
    public String getLifeTimePointsFromAddPoints()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_LifetimePointsAddPoints)).getText();
    }

    @Step("Check Points for Distribution who to Provide UI side")
    public String getPointsForDistributionFromAddPoints()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(txt_pointsForDistributionAddPoints)).getText();
    }




}
