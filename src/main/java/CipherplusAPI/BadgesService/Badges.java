package CipherplusAPI.BadgesService;

import CipherplusAPI.BaseService.BaseService;
import io.restassured.response.Response;

public class Badges extends BaseService {


   public static final String BASE_PATH ="/api/Badges";



   public Response getBadgesDetails()
   {
       return getRequest(BASE_PATH);
   }






































}
