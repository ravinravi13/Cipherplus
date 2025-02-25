package CipherplusAPI.EmployeeBadgesServices;

import CipherplusAPI.BaseService.BaseService;
import io.restassured.response.Response;

public class EmployeeBadgeServices extends BaseService {

    public static final String BASE_PATH ="/api/EmployeeBadges/6";


    public Response getEmployeeBadge()
    {
       return getRequest(BASE_PATH);
    }












}
