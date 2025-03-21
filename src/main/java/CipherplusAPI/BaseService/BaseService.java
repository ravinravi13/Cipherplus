package CipherplusAPI.BaseService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseService {



    public static final String BASE_URL = "https://cipherdev.azurewebsites.net";
    RequestSpecification requestSpecification;

    public BaseService() {
        RestAssured.defaultParser = Parser.JSON;
        requestSpecification  = given().baseUri(BASE_URL);
    }



    public Response getRequest(String endpoint) {
        return requestSpecification.contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + System.getenv("AZURE_ENTRA_TOKEN"))
                .get(endpoint);
    }






}
