package Testcase.Scenario_03;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.dashBoard;
import Cipherplus.Pages.giftStore;
import CipherplusAPI.Models.ResponseModel.RewardResponse;
import CipherplusAPI.RewardService.rewardService;
import Utilities.readExcel;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class giftStoreTest extends BaseClass {

    giftStore obj_giftStore = new giftStore();
    dashBoard obj_dashBoard = new dashBoard();


   @BeforeTest(groups = "BaseLogin")
    public void enterApplication() throws InterruptedException {
        launchBrowser("chrome");

        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0116")));
        emailField.sendKeys(getProperty("mailId"));
        driver.findElement(By.id("idSIButton9")).click();
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0118")));
        passwordField.sendKeys(getProperty("Password"));
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("idSIButton9"))).click();
//      driver.findElement(By.id("idSIButton9")).click();

    }


    @Test(priority = 1, description = "verify Searching",groups = {"GiftStore","Smoke Test"})
    @Description("This test attempts the Searching features display accurate product by passing valid product name")
    @Severity(SeverityLevel.BLOCKER)
    @Features({@Feature("GiftStore"),@Feature("Searching")})
    public void VerifySearchingFeature() throws InterruptedException, IOException {

        obj_dashBoard.clickExploreBtn();
        obj_giftStore.clickGiftStore();
        obj_giftStore.clickViewAllProduct();
        int rows = readExcel.getRowCount(System.getProperty("user.dir")+BaseClass.getProperty("excelFilepath"),BaseClass.getProperty("excelSheetName"));

        for(int i=1;i<=rows;i++)
        {
            // Read the data from Excel

            String productName = readExcel.getCellData(System.getProperty("user.dir")+BaseClass.getProperty("excelFilepath"),BaseClass.getProperty("excelSheetName"),i,0);
            // pass values into application
            System.out.println(productName);
            obj_giftStore.EnterProductName(productName);
            Thread.sleep(5000);
            String productDescription = obj_giftStore.getProductName();

            if(productName.equals(productDescription)){
                System.out.println("Test passed");
            }
            else{
                System.out.println("Test failed");
            }


        }

    }




    @Test(priority = 3, description = "verify a view all product with API data source", groups = {"GiftStore", "Smoke Test"})
    @Description("This test attempts to verify the product all present in application validate with API")
    @Link(type = "external",url = "https://cipherdev.azurewebsites.net/api/Rewards")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("GiftStore "),
            @Feature("Cart Feature")})
    public void VerifyLocationProduct() {
        rewardService obj_rewardService = new rewardService();
        Response response = obj_rewardService.getProduct();
        RewardResponse rewardResponse = response.as(RewardResponse.class);

        Map<String, List<Double>> expectedMap = new HashMap<>();
        for (RewardResponse.Product product : rewardResponse.getResult()) {
            String productName = product.getName();
            double productCost = product.getCost();

            // If the product name is already in the map, add the cost to the list
            if (expectedMap.containsKey(productName)) {
                expectedMap.get(productName).add(productCost);
            } else {
                // If the product name is not in the map, create a new list and add the cost
                List<Double> costList = new ArrayList<>();
                costList.add((double) productCost);
                expectedMap.put(productName, costList);
            }
        }

        Map<String, List<Double>> actualMap = new HashMap<String, List<Double>>(obj_giftStore.getListProductName());

        for (RewardResponse.Product product : rewardResponse.getResult()) {
            String productName = product.getName();
            double productCost = product.getCost();

            expectedMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
        }

        // Create a map to store actual product names and their costs

        // Populate actualMap (assuming you have a method to get the actual products)
        for (RewardResponse.Product product : rewardResponse.getResult()) {
            String productName = product.getName();
            double productCost = product.getCost();

            actualMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
        }

        // Validate the maps
        // Check if all keys in expectedMap are present in actualMap
//        for (String productName : expectedMap.keySet()) {
//            Assert.assertTrue(actualMap.containsKey(productName), "Product " + productName + " is missing in actualMap.");
//            Assert.assertEquals(actualMap.get(productName), expectedMap.get(productName),
//                    "list for product " + productName + " does not match.");
//        }

        // Check if all keys in actualMap are present in expectedMap
        for (String productName : actualMap.keySet()) {
            Assert.assertTrue(expectedMap.containsKey(productName), "Unexpected product " + productName + " found in actualMap.");
        }

    }




    @Test(priority = 4, description = "Verify Book category product of location based with API data source", groups = {"GiftStore", "Smoke Test"})
    @Description("This test attempts to verify the product of book category with API response to ensure all products" +
            "are in application")
    @Link(type = "external",url = "https://cipherdev.azurewebsites.net/api/Rewards")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("GiftStore "),
            @Feature("Book Category")})
    public void VerifyBookCategory() throws InterruptedException {


        obj_giftStore.clickBackToCategories();
        obj_giftStore.clickBookCategory();
        obj_giftStore.clickProductLocation();
        Thread.sleep(2000);
        obj_giftStore.selectProductLocation("India");
        Thread.sleep(2000);
        Map<String, List<Double>> actualMap = new HashMap<String, List<Double>>(obj_giftStore.getListProductName());



        rewardService obj_rewardService = new rewardService();
        Response response = obj_rewardService.getProduct();
        RewardResponse rewardResponse = response.as(RewardResponse.class);

        Map<String, List<Double>> expectedMap = new HashMap<>();
        for (RewardResponse.Product product : rewardResponse.getResult()) {
            if(product.getLocationId() == 34 && product.getCategoryId()==1) {
                String productName = product.getName();
                double productCost = product.getCost();

                expectedMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
            }

        }


        // Validate the maps
        // Check if all keys in expectedMap are present in actualMap
        for (String productName : expectedMap.keySet()) {
            Assert.assertTrue(actualMap.containsKey(productName), "Product " + productName + " is missing in actualMap.");
            Assert.assertEquals(actualMap.get(productName), expectedMap.get(productName),
                    "Cost list for product " + productName + " does not match.");
        }

        // Check if all keys in actualMap are present in expectedMap
        for (String productName : actualMap.keySet()) {
            Assert.assertTrue(expectedMap.containsKey(productName), "Unexpected product " + productName + " found in actualMap.");
        }




    }








    @Test(priority = 5, description = "Verify Cloths category product of location based with API data source", groups = {"GiftStore", "Smoke Test"})
    @Description("This test attempts to verify the product of clothes category with API response to ensure all products" +
            "are in application")
    @Link(type = "external",url = "https://cipherdev.azurewebsites.net/api/Rewards")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("GiftStore "),
            @Feature("Cloths Category")})
    public void VerifyClothsCategory() throws InterruptedException {


        obj_giftStore.clickBackToCategories();
        obj_giftStore.clickClothesCategory();
        obj_giftStore.clickProductLocation();
        Thread.sleep(2000);
        obj_giftStore.selectProductLocation("India");
        Thread.sleep(2000);
        Map<String, List<Double>> actualMap = new HashMap<String, List<Double>>(obj_giftStore.getListProductName());




        rewardService obj_rewardService = new rewardService();
        Response response = obj_rewardService.getProduct();
        RewardResponse rewardResponse = response.as(RewardResponse.class);

        Map<String, List<Double>> expectedMap = new HashMap<>();
        for (RewardResponse.Product product : rewardResponse.getResult()) {
            if(product.getLocationId() == 34 && product.getCategoryId()==2) {
                String productName = product.getName();
                double productCost = product.getCost();

                expectedMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
            }

        }

        // Validate the maps
        // Check if all keys in expectedMap are present in actualMap
        for (String productName : expectedMap.keySet()) {
            Assert.assertTrue(actualMap.containsKey(productName), "Product " + productName + " is missing in actualMap.");
            Assert.assertEquals(actualMap.get(productName), expectedMap.get(productName),
                    "Cost list for product " + productName + " does not match.");
        }

        // Check if all keys in actualMap are present in expectedMap
        for (String productName : actualMap.keySet()) {
            Assert.assertTrue(expectedMap.containsKey(productName), "Unexpected product " + productName + " found in actualMap.");
        }







    }







    @Test(priority = 6, description = "Verify Electronic category product of location based with API data source", groups = {"GiftStore", "Smoke Test"})
    @Description("This test attempts to verify the product of electronic category with API response to ensure all products" +
            "are in application")
    @Link(type = "external",url = "https://cipherdev.azurewebsites.net/api/Rewards")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("GiftStore "),
            @Feature("Electronic Category")})
    public void verifyElectronicCategory() throws InterruptedException {


        obj_giftStore.clickBackToCategories();
        obj_giftStore.clickElectronicCategory();
        obj_giftStore.clickProductLocation();
        Thread.sleep(2000);
        obj_giftStore.selectProductLocation("India");
        Thread.sleep(2000);
        Map<String, List<Double>> actualMap = new HashMap<String, List<Double>>(obj_giftStore.getListProductName());


        rewardService obj_rewardService = new rewardService();
        Response response = obj_rewardService.getProduct();
        RewardResponse rewardResponse = response.as(RewardResponse.class);

        Map<String, List<Double>> expectedMap = new HashMap<>();
        for (RewardResponse.Product product : rewardResponse.getResult()) {
            if (product.getLocationId() == 34 && product.getCategoryId() == 3) {
                String productName = product.getName();
                double productCost = product.getCost();

                expectedMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
            }

        }

        // Validate the maps
        // Check if all keys in expectedMap are present in actualMap
        for (String productName : expectedMap.keySet()) {
            Assert.assertTrue(actualMap.containsKey(productName), "Product " + productName + " is missing in actualMap.");
            Assert.assertEquals(actualMap.get(productName), expectedMap.get(productName),
                    "Cost list for product " + productName + " does not match.");
        }

        // Check if all keys in actualMap are present in expectedMap
        for (String productName : actualMap.keySet()) {
            Assert.assertTrue(expectedMap.containsKey(productName), "Unexpected product " + productName + " found in actualMap.");
        }


    }







        @Test(priority = 7, description = "Verify Fitness category product of location based with API data source", groups = {"GiftStore", "Smoke Test"})
        @Description("This test attempts to verify the product of Fitness category with API response to ensure all products" +
                "are in application")
        @Link(type = "external",url = "https://cipherdev.azurewebsites.net/api/Rewards")
        @Severity(SeverityLevel.BLOCKER)
        @Features({
                @Feature("GiftStore "),
                @Feature("Fitness Category")})
        public void verifyFitnessCategory() throws InterruptedException {


            obj_giftStore.clickBackToCategories();
            obj_giftStore.clickFitnessCategory();
            obj_giftStore.clickProductLocation();
            Thread.sleep(2000);
            obj_giftStore.selectProductLocation("India");
            Thread.sleep(2000);
            Map<String, List<Double>> actualMap = new HashMap<String, List<Double>>(obj_giftStore.getListProductName());



            rewardService obj_rewardService = new rewardService();
            Response response = obj_rewardService.getProduct();
            RewardResponse rewardResponse = response.as(RewardResponse.class);

            Map<String, List<Double>> expectedMap = new HashMap<>();
            for (RewardResponse.Product product : rewardResponse.getResult()) {
                if(product.getLocationId() == 34 && product.getCategoryId()==4) {
                    String productName = product.getName();
                    double productCost = product.getCost();

                    expectedMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
                }

            }

            // Validate the maps
            // Check if all keys in expectedMap are present in actualMap
            for (String productName : expectedMap.keySet()) {
                Assert.assertTrue(actualMap.containsKey(productName), "Product " + productName + " is missing in actualMap.");
                Assert.assertEquals(actualMap.get(productName), expectedMap.get(productName),
                        "Cost list for product " + productName + " does not match.");
            }

            // Check if all keys in actualMap are present in expectedMap
            for (String productName : actualMap.keySet()) {
                Assert.assertTrue(expectedMap.containsKey(productName), "Unexpected product " + productName + " found in actualMap.");
            }
    }


    @Test(priority = 8, description = "Verify Plants category product of location based with API data source", groups = {"GiftStore", "Smoke Test"})
    @Description("This test attempts to verify the product of Plants category with API response to ensure all products" +
            "are in application")
    @Link(type = "external",url = "https://cipherdev.azurewebsites.net/api/Rewards")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("GiftStore "),
            @Feature("Plants Category")})
    public void verifyPlantsCategory() throws InterruptedException {


        obj_giftStore.clickBackToCategories();
        obj_giftStore.clickPlantsCategory();
        obj_giftStore.clickProductLocation();
        Thread.sleep(2000);
        obj_giftStore.selectProductLocation("India");
        Thread.sleep(2000);
        Map<String, List<Double>> actualMap = new HashMap<String, List<Double>>(obj_giftStore.getListProductName());




        rewardService obj_rewardService = new rewardService();
        Response response = obj_rewardService.getProduct();
        RewardResponse rewardResponse = response.as(RewardResponse.class);

        Map<String, List<Double>> expectedMap = new HashMap<>();
        for (RewardResponse.Product product : rewardResponse.getResult()) {
            if(product.getLocationId() == 34 && product.getCategoryId()==8) {
                String productName = product.getName();
                double productCost = product.getCost();

                expectedMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
            }

        }

        // Validate the maps
        // Check if all keys in expectedMap are present in actualMap
        for (String productName : expectedMap.keySet()) {
            Assert.assertTrue(actualMap.containsKey(productName), "Product " + productName + " is missing in actualMap.");
            Assert.assertEquals(actualMap.get(productName), expectedMap.get(productName),
                    "Cost list for product " + productName + " does not match.");
        }

        // Check if all keys in actualMap are present in expectedMap
        for (String productName : actualMap.keySet()) {
            Assert.assertTrue(expectedMap.containsKey(productName), "Unexpected product " + productName + " found in actualMap.");
        }
    }


    @Test(priority = 9, description = "Verify stationary category product of location based with API data source", groups = {"GiftStore", "Smoke Test"})
    @Description("This test attempts to verify the product of stationary category with API response to ensure all products" +
            "are in application")
    @Link(type = "external",url = "https://cipherdev.azurewebsites.net/api/Rewards")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("GiftStore "),
            @Feature("Stationary Category")})
    public void verifyStationaryCategory() throws InterruptedException {


        obj_giftStore.clickBackToCategories();
        obj_giftStore.clickStationaryCategory();
        obj_giftStore.clickProductLocation();
        Thread.sleep(2000);
        obj_giftStore.selectProductLocation("India");
        Thread.sleep(2000);
        Map<String, List<Double>> actualMap = new HashMap<String, List<Double>>(obj_giftStore.getListProductName());




        rewardService obj_rewardService = new rewardService();
        Response response = obj_rewardService.getProduct();
        RewardResponse rewardResponse = response.as(RewardResponse.class);

        Map<String, List<Double>> expectedMap = new HashMap<>();
        for (RewardResponse.Product product : rewardResponse.getResult()) {
            if(product.getLocationId() == 34 && product.getCategoryId()==5) {
                String productName = product.getName();
                double productCost = product.getCost();

                expectedMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
            }

        }

        // Validate the maps
        // Check if all keys in expectedMap are present in actualMap
        for (String productName : expectedMap.keySet()) {
            Assert.assertTrue(actualMap.containsKey(productName), "Product " + productName + " is missing in actualMap.");
            Assert.assertEquals(actualMap.get(productName), expectedMap.get(productName),
                    "Cost list for product " + productName + " does not match.");
        }

        // Check if all keys in actualMap are present in expectedMap
        for (String productName : actualMap.keySet()) {
            Assert.assertTrue(expectedMap.containsKey(productName), "Unexpected product " + productName + " found in actualMap.");
        }
    }






    @Test(priority = 10, description = "Verify Home appliances category product of location based with API data source", groups = {"GiftStore", "Smoke Test"})
    @Description("This test attempts to verify the product of Home appliances category with API response to ensure all products" +
            "are in application")
    @Link(type = "external",url = "https://cipherdev.azurewebsites.net/api/Rewards")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("GiftStore "),
            @Feature("Home Appliances Category")})
    public void verifyHomeApplianceCategory() throws InterruptedException {


        obj_giftStore.clickBackToCategories();
        obj_giftStore.clickHomeAppliancesCategory();
        obj_giftStore.clickProductLocation();
        Thread.sleep(2000);
        obj_giftStore.selectProductLocation("India");
        Thread.sleep(2000);
        Map<String, List<Double>> actualMap = new HashMap<String, List<Double>>(obj_giftStore.getListProductName());




        rewardService obj_rewardService = new rewardService();
        Response response = obj_rewardService.getProduct();
        RewardResponse rewardResponse = response.as(RewardResponse.class);

        Map<String, List<Double>> expectedMap = new HashMap<>();
        for (RewardResponse.Product product : rewardResponse.getResult()) {
            if(product.getLocationId() == 34 && product.getCategoryId()==9) {
                String productName = product.getName();
                double productCost = product.getCost();

                expectedMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
            }

        }

        // Validate the maps
        // Check if all keys in expectedMap are present in actualMap
        for (String productName : expectedMap.keySet()) {
            Assert.assertTrue(actualMap.containsKey(productName), "Product " + productName + " is missing in actualMap.");
            Assert.assertEquals(actualMap.get(productName), expectedMap.get(productName),
                    "Cost list for product " + productName + " does not match.");
        }

        // Check if all keys in actualMap are present in expectedMap
        for (String productName : actualMap.keySet()) {
            Assert.assertTrue(expectedMap.containsKey(productName), "Unexpected product " + productName + " found in actualMap.");
        }
    }






    @Test(priority = 11, description = "Verify E-voucher category product of location based with API data source", groups = {"GiftStore", "Smoke Test"})
    @Description("This test attempts to verify the product of E-voucher category with API response to ensure all products" +
            "are in application")
    @Link(type = "external",url = "https://cipherdev.azurewebsites.net/api/Rewards")
    @Severity(SeverityLevel.BLOCKER)
    @Features({
            @Feature("GiftStore "),
            @Feature("E-voucher Category")})
    public void verifyE_voucherCategory() throws InterruptedException {


        obj_giftStore.clickBackToCategories();
        obj_giftStore.clickVoucherCategory();
        obj_giftStore.clickProductLocation();
        Thread.sleep(2000);
        obj_giftStore.selectProductLocation("India");
        Thread.sleep(2000);
        Map<String, List<Double>> actualMap = new HashMap<String, List<Double>>(obj_giftStore.getListProductName());




        rewardService obj_rewardService = new rewardService();
        Response response = obj_rewardService.getProduct();
        RewardResponse rewardResponse = response.as(RewardResponse.class);

        Map<String, List<Double>> expectedMap = new HashMap<>();
        for (RewardResponse.Product product : rewardResponse.getResult()) {
            if(product.getLocationId() == 34 && product.getCategoryId()==7) {
                String productName = product.getName();
                double productCost = product.getCost();

                expectedMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(productCost);
            }

        }

        // Validate the maps
        // Check if all keys in expectedMap are present in actualMap
        for (String productName : expectedMap.keySet()) {
            Assert.assertTrue(actualMap.containsKey(productName), "Product " + productName + " is missing in actualMap.");
            Assert.assertEquals(actualMap.get(productName), expectedMap.get(productName),
                    "Cost list for product " + productName + " does not match.");
        }

        // Check if all keys in actualMap are present in expectedMap
        for (String productName : actualMap.keySet()) {
            Assert.assertTrue(expectedMap.containsKey(productName), "Unexpected product " + productName + " found in actualMap.");
        }
    }





































































































}

























