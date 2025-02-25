package CipherplusAPI.UserService;

import CipherplusAPI.BaseService.BaseService;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class UserServices extends BaseService {

    public static final String BASE_PATH = "/api/LoggedinUser";



    public Response loginUser(){

         return getRequest(BASE_PATH);

    }






}
