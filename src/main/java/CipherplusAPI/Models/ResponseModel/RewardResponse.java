package CipherplusAPI.Models.ResponseModel;

import CipherplusAPI.BaseService.BaseService;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RewardResponse extends BaseService {


    @JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    @JsonProperty("errorMessage")
    private String errorMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<Product> getResult() {
        return result;
    }

    public void setResult(List<Product> result) {
        this.result = result;
    }

    @JsonProperty("result")
    private List<Product> result;





    public static class Product{


        @JsonProperty("id")
        private int id;

        @JsonProperty("quantity")
        private int quantity;

        @JsonProperty("cost")
        private double cost;

        @JsonProperty("name")
        private String name;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLocationId() {
            return locationId;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductUrl() {
            return productUrl;
        }

        public void setProductUrl(String productUrl) {
            this.productUrl = productUrl;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        @JsonProperty("description")
        private String description;

        @JsonProperty("locationId")
        private int locationId;

        @JsonProperty("productName")
        private String productName;

        @JsonProperty("productUrl")
        private String productUrl;

        @JsonProperty("categoryId")
        private int categoryId;



    }




}
