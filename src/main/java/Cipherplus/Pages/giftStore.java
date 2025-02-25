package Cipherplus.Pages;

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

public class giftStore extends BaseClass {



    By link_GiftStoreLoc = By.linkText("Gift Store");
    By btn_viewAllProductLoc = By.xpath("//button[text()='View All Products']");
    By bookLoc = By.xpath("//p[text()='Books']");
    By clothesLoc = By.xpath("//p[text()='Clothes']");
    By electronicLoc =By.xpath("//p[text()='Electronics']");
    By fitnessloc = By.xpath("//p[text()='Fitness']");
    By plantsLoc = By.xpath("//p[text()='Plants']");
    By stationaryLoc = By.xpath("//p[text()='Stationery']");
    By HomeAppliancesLoc = By.xpath("//p[text()='Home Appliances']");
    By vouchersLoc = By.xpath("//p[text()='Vouchers']");
    By dropFilterLocationLoc = By.xpath("//button[text()='Filter by location']");
    By dropFilterPointValues = By.xpath("//button[text()='Filter by point value']");

    By inp_searchBoxLoc = By.tagName("input");

    By product_singleProduct = By.xpath("(//p[@class='product-name'])[1]");
    By product_singleProductCart = By.tagName("h4");
    By point_SingleProductLoc = By.xpath("(//div[@class='gifts-cards']//span)[3]");
    By notFoundItemLoc = By.xpath("//p[text()='No items found for the search term.']");

    By productNameLoc = By.className("product-name");

    By ProductNameCssLoc =  By.cssSelector("#root > div > main > div > div.gifts-cards-container > div > p.product-name");

    By pointsValuesCssLoc = By.cssSelector("#root > div > main > div > div.gifts-cards-container > div > div:nth-child(5) > span");
    By backToCategoriesLoc = By.xpath("//button[text()='Back to Categories']");










    @Step("Click Gift store from Navigation bar")
   public void clickGiftStore(){
       wait.until(ExpectedConditions.presenceOfElementLocated(link_GiftStoreLoc)).click();
   }


   @Step("Click View All Product button")
   public void clickViewAllProduct(){
       wait.until(ExpectedConditions.presenceOfElementLocated(btn_viewAllProductLoc)).click();
   }


   @Step("Click the Book Category")
   public void clickBookCategory(){
       wait.until(ExpectedConditions.presenceOfElementLocated(bookLoc)).click();
   }

    @Step("Click the Clothes Category")
    public void clickClothesCategory(){
        wait.until(ExpectedConditions.presenceOfElementLocated(clothesLoc)).click();
    }

    @Step("Click the Electronic Category")
    public void clickElectronicCategory(){
        wait.until(ExpectedConditions.presenceOfElementLocated(electronicLoc)).click();
    }

    @Step("Click the Fitness Category")
    public void clickFitnessCategory(){
        wait.until(ExpectedConditions.presenceOfElementLocated(fitnessloc)).click();
    }

    @Step("Click the Plants Category")
    public void clickPlantsCategory(){
        wait.until(ExpectedConditions.presenceOfElementLocated(plantsLoc)).click();
    }

    @Step("Click the Stationary Category")
    public void clickStationaryCategory(){
        wait.until(ExpectedConditions.presenceOfElementLocated(stationaryLoc)).click();
    }


    @Step("Click the Home Appliances Category")
    public void clickHomeAppliancesCategory(){
        wait.until(ExpectedConditions.presenceOfElementLocated(HomeAppliancesLoc)).click();
    }

    @Step("Click the Voucher Category")
    public void clickVoucherCategory(){
        wait.until(ExpectedConditions.presenceOfElementLocated(vouchersLoc)).click();
    }





    @Step("Click location dropdown")
    public void clickProductLocation(){
       wait.until(ExpectedConditions
               .presenceOfElementLocated(dropFilterLocationLoc)).click();
    }

    @Step("Select product location : {0}")
    public void selectProductLocation(String location){
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.linkText(location))).click();
    }




    @Step("Select Filter points values : {0}")
    public void selectFilterPointsValue(String Points){
        Select dropdown = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(dropFilterPointValues)));
        dropdown.selectByVisibleText(Points);
    }







    @Step("Locate search filed and Enter the text of product name and")
    public void EnterProductName(String ProductName){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(inp_searchBoxLoc));
        element.clear();
        element.sendKeys(ProductName);


    }


    @Step("Get the Product name")
    public String extractSingleSearchProductName(){

     WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(product_singleProduct));
     return element.getDomAttribute("title");

    }

    @Step("Get the Product name from cart section")
    public String extractSingleSearchProductNameCart(){
        return  wait.until(ExpectedConditions.presenceOfElementLocated(product_singleProductCart)).getText();

    }





    @Step("Get the points values from select product")
    public String extractPointSingleProduct(){
        return  wait.until(ExpectedConditions.presenceOfElementLocated(point_SingleProductLoc)).getText();

    }



    @Step("check message of 'the No items found for the search term' ")
    public boolean checkNotFoundItemMsg(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(notFoundItemLoc)).isDisplayed();

    }

    public String getProductName(){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(productNameLoc));
        return  element.getDomAttribute("title");

    }

    @Step("Click Back to categories button")
    public void clickBackToCategories()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(backToCategoriesLoc)).click();
    }









    @Step("Get the each product of title and point")
    public Map<String, List<Double>> getListProductName() {
        Map<String, List<Double>> productDetails = new HashMap<>();

        List<WebElement> titleElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ProductNameCssLoc));


        List<WebElement> pointsElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(pointsValuesCssLoc));


        for (int i = 0; i < titleElements.size(); i++) {
            WebElement titleElement = titleElements.get(i);
            WebElement pointsElement = pointsElements.get(i);

            String title = titleElement.getAttribute("title"); // Use getAttribute instead of getDomAttribute
            String points = pointsElement.getText();

            // If the title is not already in the map, create a new list
            productDetails.putIfAbsent(title, new ArrayList<>());
            // Add the points to the list associated with the title
            productDetails.get(title).add(Double.valueOf(points));
        }


        return productDetails; // Return the map containing product details
    }










}
