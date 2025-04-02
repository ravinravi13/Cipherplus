package Cipherplus.Pages.AdminPage;

import Cipherplus.Base.BaseClass;
import Utilities.dataBaseConnect;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ManageRewardPrograms extends BaseClass {

    dataBaseConnect obj_dataBaseConnect =new dataBaseConnect();
    private WebDriver driver;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust format as needed

    By adminLinkLoc = By.linkText("Admin");
    By nav_manageRewardProgramLoc = By.xpath("//h4[text()='Manage Reward Programs']");
    By btn_addPoint = By.xpath("//button[text()='ADD']");
    By searchBoxLoc = By.xpath("//input[@placeholder='Search by Name...']");
    By dropdown_postDropDown = By.xpath("(//select[@class='form-select'])[1]");
    By dropDown_locationDropDown = By.xpath("(//select[@class='form-select'])[2]");By eventNameField = By.id("eventName");By eventDescriptionField = By.id("eventDescription");By locationDropdown = By.id("relevantLocations");By pointsValueField = By.xpath("(//input[@id='pointValue'])[1]");
   By offerExpiresField = By.id("offerExpires");
   By dateOpenedField = By.xpath("(//input[@id='dateOpened'])[1]");
   By approverDropdown = By.xpath("(//select[@id='approver'])[1]");
   By addPostButton = By.xpath("//button[text()='Add Post']");
   By clearButton = By.xpath("//button[text()='Clear']");








    @Step("Click Admin Link in Navigation Bar")
    public void clickAdminNav() {
        wait.until(ExpectedConditions.presenceOfElementLocated(adminLinkLoc)).click();
    }


    @Step("Click Add point button")
    public void clickAddButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_addPoint)).click();
    }



    @Step("Click Manager reward program container")
    public void clickManagerRewardProgram() {
        wait.until(ExpectedConditions.presenceOfElementLocated(nav_manageRewardProgramLoc)).click();
    }


    @Step("Enter the event name in search box : {0}")
    public void enterNameofEvent(String eventname) {
       WebElement element=  wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLoc));
       element.clear();
       element.sendKeys(eventname);
    }

    @Step("Select Post event dropdown as : {0}")
    public void selectPostEvent(String post) {
        WebElement element=  wait.until(ExpectedConditions.presenceOfElementLocated(dropdown_postDropDown));
        Select select = new Select(element);
        select.selectByVisibleText(post);
    }

    @Step("Select Location dropdown as : {0}")
    public void selectLocationDropdown(String location) {
        WebElement element=  wait.until(ExpectedConditions.presenceOfElementLocated(dropDown_locationDropDown));
        Select select = new Select(element);
        select.selectByVisibleText(location);
    }



    @Step("Get the Post event from Database")
   public List<List<String>> getAllPost()
   {
       Connection connection = obj_dataBaseConnect.dbconnect();
       List<List<String>> actualPost = new ArrayList<>();
       String query = "  select Title,Description,DateOpened,DateClosed from Post where LocationId = 40 ";

       try(PreparedStatement preparedStatement = connection.prepareStatement(query))
       {
           try(ResultSet resultSet = preparedStatement.executeQuery())
           {
               List<String> rowData = new ArrayList<>();
               while (resultSet.next())
               {
                   rowData.add(resultSet.getString("Title"));
                   rowData.add(resultSet.getString("Description"));
                   rowData.add(resultSet.getString("DateOpened"));
                   rowData.add(resultSet.getString("DateClosed"));
                   actualPost.add(rowData);
               }
           }
           catch (SQLException e) {
               throw new RuntimeException(e);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

      return actualPost;
   }



   public String NameOfEvent() {

        Connection connection = obj_dataBaseConnect.dbconnect();
        String query = "  select Title,Description,DateOpened,DateClosed from Post where LocationId = 40 ";
        String EventName = "";
        List<String> rowData = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    rowData.add(resultSet.getString("Title"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int nameEventLength = rowData.size();

        if (nameEventLength > 0) {
            // Generate a random index to select a button
            Random random = new Random();
            int randomIndex = random.nextInt(nameEventLength);

            EventName =  rowData.get(randomIndex);
        }
        
        return EventName;

    }


    public List<String> getFirstColumnTexts() throws InterruptedException {
        // Locate the table
        Thread.sleep(2000);
        WebElement table = BaseClass.driver.findElement(By.xpath("//table[contains(@class,'table table-borderless')]"));

        // Locate all rows in the table (excluding the header)
        List<WebElement> rows = table.findElements(By.xpath(".//tr")); // Use '.' to search within the table context

        // List to accumulate the text of the first column
        List<String> firstColumnTexts = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) { // Start from 1 to skip the header row
            List<WebElement> cells = rows.get(i).findElements(By.xpath(".//td")); // Get all cells in the current row
            if (!cells.isEmpty()) {
                // Add only the text of the first cell (first column) to the list
                firstColumnTexts.add(cells.get(0).getText()); // Get text of the first cell
            }
        }

        return firstColumnTexts; // Return the list of texts from the first column
    }




    public void validateTableSorting(String sortingType) throws InterruptedException {
        // Wait for the table to update
        Thread.sleep(2000); // Consider replacing this with a more robust wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class,'table table-borderless')]"))); // Adjust selector as needed

        // Retrieve all rows from the table
        List<WebElement> rows = BaseClass.driver.findElements(By.xpath("//table[contains(@class,'table-borderless')]/tbody/tr")); // Adjust selector as needed

        List<LocalDate> datesOpened = new ArrayList<>();
        List<LocalDate> datesExpires = new ArrayList<>();

        for (WebElement row : rows) {
            // Ensure the row is visible
            if (row.isDisplayed()) {
                // Extract the date values from the row
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() >= 5) { // Ensure the row has at least 5 cells
                    String dateOpenedText = cells.get(3).getText(); // 4th column (index 3)
                    String dateExpiresText = cells.get(4).getText(); // 5th column (index 4)
                    System.out.println("Date Opened = " + dateOpenedText + "\n"+"offer expires = "+dateExpiresText);
                    // Check if the dateOpenedText is valid before parsing
                    if (!dateOpenedText.equals("N/A") && !dateOpenedText.isEmpty()) {
                        LocalDate dateOpened = LocalDate.parse(dateOpenedText, dateFormatter);
                        datesOpened.add(dateOpened);
                    }

                    // Check if the dateExpiresText is valid before parsing
                    if (!dateExpiresText.equals("N/A") && !dateExpiresText.isEmpty()) {
                        LocalDate dateExpires = LocalDate.parse(dateExpiresText, dateFormatter);
                        datesExpires.add(dateExpires);
                    }
                }
            }
        }

        // Validate sorting based on the selected option
        switch (sortingType) {
            case "newestToOldest":
                List<LocalDate> sortedOpenedDesc = new ArrayList<>(datesOpened);
                Collections.sort(sortedOpenedDesc, Collections.reverseOrder());
                Assert.assertEquals(datesOpened, sortedOpenedDesc, "The dates in the 'Date Opened' column are not sorted from newest to oldest.");
                break;

            case "oldestToNewest":
                List<LocalDate> sortedOpenedAsc = new ArrayList<>(datesOpened);
                Collections.sort(sortedOpenedAsc);
                Assert.assertEquals(datesOpened, sortedOpenedAsc, "The dates in the 'Date Opened' column are not sorted from oldest to newest.");
                break;

            case "expiresSoonerToLater":
                List<LocalDate> sortedExpiresAsc = new ArrayList<>(datesExpires);
                Collections.sort(sortedExpiresAsc);
                Assert.assertEquals(datesExpires, sortedExpiresAsc, "The dates in the 'Offer Expires' column are not sorted from sooner to later.");
                break;

            case "expiresLaterToSooner":
                List<LocalDate> sortedExpiresDesc = new ArrayList<>(datesExpires);
                Collections.sort(sortedExpiresDesc, Collections.reverseOrder());
                Assert.assertEquals(datesExpires, sortedExpiresDesc, "The dates in the 'Offer Expires' column are not sorted from later to sooner.");
                break;

            default:
                Assert.fail("Invalid sorting type provided.");
        }
    }




//    public void validateTableSorting(String sortingType) throws InterruptedException {
//        // Wait for the table to update
//        Thread.sleep(2000); // Consider replacing this with a more robust wait
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class,'table table-borderless')]"))); // Adjust selector as needed
//
//        // Retrieve all rows from the table
//        List<WebElement> rows = BaseClass.driver.findElements(By.xpath("//table[contains(@class,'table table-borderless')]/tbody/tr")); // Adjust selector as needed
//
//        List<LocalDate> datesOpened = new ArrayList<>();
//        List<LocalDate> datesExpires = new ArrayList<>();
//
//        for (WebElement row : rows) {
//            // Ensure the row is visible
//            if (row.isDisplayed()) {
//                // Extract the date values from the row
//                List<WebElement> cells = row.findElements(By.tagName("td"));
//                if (cells.size() >= 5) { // Ensure the row has at least 5 cells
//                    String dateOpenedText = cells.get(3).getText(); // 4th column (index 3)
//                    String dateExpiresText = cells.get(4).getText(); // 5th column (index 4)
//
//                    // Parse the dates
//                    LocalDate dateOpened = LocalDate.parse(dateOpenedText, dateFormatter);
//                    LocalDate dateExpires = LocalDate.parse(dateExpiresText, dateFormatter);
//
//                    datesOpened.add(dateOpened);
//                    datesExpires.add(dateExpires);
//                }
//            }
//        }
//
//        // Validate sorting based on the selected option
//        switch (sortingType) {
//            case "newestToOldest":
//                List<LocalDate> sortedOpenedDesc = new ArrayList<>(datesOpened);
//                Collections.sort(sortedOpenedDesc, Collections.reverseOrder());
//                Assert.assertEquals(datesOpened, sortedOpenedDesc, "The dates in the 'Date Opened' column are not sorted from newest to oldest.");
//                break;
//
//            case "oldestToNewest":
//                List<LocalDate> sortedOpenedAsc = new ArrayList<>(datesOpened);
//                Collections.sort(sortedOpenedAsc);
//                Assert.assertEquals(datesOpened, sortedOpenedAsc, "The dates in the 'Date Opened' column are not sorted from oldest to newest.");
//                break;
//
//            case "expiresSoonerToLater":
//                List<LocalDate> sortedExpiresAsc = new ArrayList<>(datesExpires);
//                Collections.sort(sortedExpiresAsc);
//                Assert.assertEquals(datesExpires, sortedExpiresAsc, "The dates in the 'Offer Expires' column are not sorted from sooner to later.");
//                break;
//
//            case "expiresLaterToSooner":
//                List<LocalDate> sortedExpiresDesc = new ArrayList<>(datesExpires);
//                Collections.sort(sortedExpiresDesc, Collections.reverseOrder());
//                Assert.assertEquals(datesExpires, sortedExpiresDesc, "The dates in the 'Offer Expires' column are not sorted from later to sooner.");
//                break;
//
//            default:
//                Assert.fail("Invalid sorting type provided.");
//        }
//    }
//



    public void fillOutForm(String eventName, String eventDescription, String location, int pointsValue, String offerExpires, String dateOpened, String approver) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventNameField)).sendKeys(eventName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventDescriptionField)).sendKeys(eventDescription);

        Select locationSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locationDropdown)));
        locationSelect.selectByVisibleText(location);

        wait.until(ExpectedConditions.visibilityOfElementLocated(pointsValueField)).sendKeys(String.valueOf(pointsValue));
        wait.until(ExpectedConditions.visibilityOfElementLocated(offerExpiresField)).sendKeys(offerExpires);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateOpenedField)).sendKeys(dateOpened);

        Select approverSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(approverDropdown)));
        approverSelect.selectByVisibleText(approver);
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(addPostButton)).click();
    }




















}
