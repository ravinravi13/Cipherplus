package CipherplusAPI.RewardService;

import CipherplusAPI.BaseService.BaseService;
import io.restassured.response.Response;

public class rewardService extends BaseService {

    public static final String BASE_PATH = "/api/Rewards";


    public Response getProduct()
    {
        return getRequest(BASE_PATH);
    }











}
