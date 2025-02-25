package CipherplusAPI.Models.ResponseModel;

import CipherplusAPI.BaseService.BaseService;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BadgesResponse extends BaseService {



    @JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Details> getResult() {
        return result;
    }

    public void setResult(List<Details> result) {
        this.result = result;
    }

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("result")
    private List<Details> result;





  public static class Details{


        @JsonProperty("id")
        private int id ;

      @JsonProperty("name")
   private String name;

      @JsonProperty("description")
   private String description;

      @JsonProperty("ProductName")
   private String ProductName;
      @JsonProperty("ProductUrl")
   private String ProductUrl;

      @JsonProperty("categoryId")
      private int categoryId;










      public int getId() {
          return id;
      }

      public void setId(int id) {
          this.id = id;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public String getDescription() {
          return description;
      }

      public void setDescription(String description) {
          this.description = description;
      }

      public String getProductName() {
          return ProductName;
      }

      public void setProductName(String productName) {
          ProductName = productName;
      }

      public String getProductUrl() {
          return ProductUrl;
      }

      public void setProductUrl(String productUrl) {
          ProductUrl = productUrl;
      }

      public int getCategoryId() {
          return categoryId;
      }

      public void setCategoryId(int categoryId) {
          this.categoryId = categoryId;
      }









  }












}
