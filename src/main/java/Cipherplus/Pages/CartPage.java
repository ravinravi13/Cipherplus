package Cipherplus.Pages;

import Cipherplus.Base.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BaseClass {



    By nav_GiftStoreLoc = By.linkText("Gift Store");
    By product_singleProductCart = By.tagName("h4");
    By getPoint_SingleProductLoc = By.xpath("//div[@class='col']//b[1]");
    By CartEmptyMsgLoc = By.xpath("//h2[text()='Your cart is empty!']");
    By icon_cartLoc = By.className("new-cart-img");
    By cart_successMessageLoc = By.xpath("//div[text()='Item added to cart successfully!']");
    By btn_buyNowLoc = By.xpath("(//button[text()='Buy Now'])[1]");
    By btn_exploreLoc = By.xpath("//button[@class='explore-button']");
    By link_giftStoreLoc = By.className("gift-store-link");
    By btn_viewAllLoc = By.xpath("//button[text()='View All Products']");

    By SingleProductCartLoc = By.xpath("(//div[@class='col-4']//img)[2]");
    By SingleProductNameLoc = By.xpath("(//p[@class='product-name'])[2]");
    By singleProductPointLoc = By.cssSelector("#root > div > main > div > div.gifts-cards-container > div:nth-child(2) > div:nth-child(5) > span");
    By cartPageSingleProductNameLoc = By.tagName("h4");
    By cartPageSinglePoint = By.xpath("//h4[text()='The Psychology of Money']/following-sibling::b");
    By cartDeleteSingleProduct = By.className("x-img");
    By btn_cartCloseLoc = By.className("close-button");
   By cart_subTotalLoc = By.xpath("(//div[@class='col Sub-Total']//b)[2]");
   By cart_TotalAvailablePoints = By.xpath("(//div[@class='col total-available']//b)[2]");
   By cart_RemainingAvailablePoint =By.cssSelector("#root > div > main > div > div.row.total-pts > div.col.Sub-Total > h6:nth-child(2) > span > b");

   By cart_ProceedClaimLoc = By.className("cart-button");
   By Checkout_otherOptionLoc = By.xpath("(//input[@name='address'])[3]");
   By txt_AddressLoc = By.xpath("//input[@placeholder='Enter other address']");
    By txt_PinCodeLoc = By.xpath("//input[@placeholder='Enter Pincode']");
    By txt_PhoneNumberLoc = By.xpath("//input[@placeholder='Enter Phone Number']");

    By btn_CheckOutSaveLoc = By.xpath("//button[text()='Save']");

   By checkOutSuccessMsgLoc = By.xpath("//p[text()='Gift added successfully!']");
   By CheckoutCloseBtnLoc = By.className("close-button");



    @Step("Enter the Address in Checkout page : {0}")
    public void enterAddress(String address)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(txt_AddressLoc)).sendKeys(address);
    }

    @Step("Enter the Pin code in Checkout page : {0}")
    public void enterPincode(String pincode)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(txt_PinCodeLoc)).sendKeys(pincode);
    }

    @Step("Enter the Phone number in Checkout page : {0}")
    public void enterPhoneNumber(String phoneNumber)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(txt_PhoneNumberLoc)).sendKeys(phoneNumber);
    }


    @Step("click Save button in checkout page")
    public void clickSaveButton()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_CheckOutSaveLoc)).click();
    }



    @Step("click the navigation bar of gift store")
    public void clickGiftStoreNavigation()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(nav_GiftStoreLoc)).click();
    }

    @Step("click proceed to claim button")
    public void clickProceedToClaimButton()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(cart_ProceedClaimLoc)).click();
    }

    @Step("click Other option in checkbox")
    public void clickOtherOption()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(Checkout_otherOptionLoc)).click();
    }

    @Step("Get the Product name from cart Section")
    public String extractSingleSearchProductName()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(product_singleProductCart)).getText();
    }


    @Step("Get the Points values in cart section")
    public int getPointsValueCart(){
        String strPointsValue = wait.until(ExpectedConditions.presenceOfElementLocated(getPoint_SingleProductLoc)).getText();
        String[] strExtractPoints = strPointsValue.split(":");
        return Integer.parseInt(strExtractPoints[1]);

    }

    @Step("Get the message of cart empty 'Your cart is empty!'  ")
    public String getCartMessage()
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(CartEmptyMsgLoc)).getText();
    }


   @Step("Click the cart icon")
   public void clickCartIcon()
   {
       wait.until(ExpectedConditions.presenceOfElementLocated(icon_cartLoc)).click();
   }




    @Step("Check success pop-up message of cart 'Item added to cart successfully!' ")
    public Boolean checkCartSuccessPopUp(){

        return driver.findElement(cart_successMessageLoc).isDisplayed();

    }
    @Step("Check success pop-up message of cart 'Item added to cart successfully!' and Verify the text")
    public String getCartSuccessMessageText(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(cart_successMessageLoc)).getText();
    }


    @Step("Click the Buy Now button")
    public void clickBuyNowButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_buyNowLoc)).click();
    }

    @Step("Click the 'Explore' button on Homepage")
    public void clickExploreBtn()  {
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_exploreLoc)).click();
    }



    @Step("Click the Gift store link cart page")
   public void clickGiftStoreLink()
   {
       wait.until(ExpectedConditions.presenceOfElementLocated(link_giftStoreLoc)).click();
   }

    @Step("Click the view All button in gift store page")
    public void clickViewAllButton()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_viewAllLoc)).click();
    }

    @Step("Click the product of cart icon")
    public void clickSingleProductCartIcon()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(SingleProductCartLoc)).click();
    }

    @Step("Get the Product name")
    public String extractSingleProductName(){

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(SingleProductNameLoc));
        return element.getDomAttribute("title");

    }

    @Step("Get the Product points")
    public String extractSingleProductPoint(){

       return wait.until(ExpectedConditions.presenceOfElementLocated(singleProductPointLoc)).getText();

    }


    @Step("Get the Product name in cart page")
    public String cartExtractSingleProductName(){

        return wait.until(ExpectedConditions.presenceOfElementLocated(cartPageSingleProductNameLoc)).getText();

    }


    @Step("Get the Product point value in cart page")
    public String cartExtractSingleProductPoint(){

        String point = wait.until(ExpectedConditions.presenceOfElementLocated(cartPageSinglePoint)).getText();
        String[] splitString = point.split(":");
        return splitString[1];

    }


    @Step("Click the delete icon in cart page")
    public void clickSingleProductDeleteIcon()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(cartDeleteSingleProduct)).click();
    }

    @Step("Click Close button in cart page")
    public void clickCartCloseButton()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_cartCloseLoc)).click();
    }


    @Step("Get the Sub-total point value in cart page")
    public int cartExtractSubTotalPoint(){

        return Integer.parseInt( wait.until(ExpectedConditions.presenceOfElementLocated(cart_subTotalLoc)).getText());

    }


    @Step("Get the Total Available point value in cart page")
    public int cartExtractTotalAvailablePoint(){

        return Integer.parseInt( wait.until(ExpectedConditions.presenceOfElementLocated(cart_TotalAvailablePoints)).getText());

    }

    @Step("Get the Remaining Available point value in cart page")
    public int cartExtractRemainingTotalAvailablePoint(){

        return Integer.parseInt( wait.until(ExpectedConditions.presenceOfElementLocated(cart_RemainingAvailablePoint)).getText());

    }


    @Step("Check check out success message")
    public Boolean isDisplayCheckOutSucessMsg(){

        return  wait.until(ExpectedConditions.presenceOfElementLocated(checkOutSuccessMsgLoc)).isDisplayed();

    }

    @Step("click close button in checkout page")
    public void clickCloseButton(){

        wait.until(ExpectedConditions.presenceOfElementLocated(CheckoutCloseBtnLoc)).click();

    }








    }













