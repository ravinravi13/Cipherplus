package Testcase.Scenario_02;

import Cipherplus.Base.BaseClass;
import Cipherplus.Pages.leaderBoard;
import io.qameta.allure.*;
import org.testng.annotations.Test;

public class LeaderBoardTest extends BaseClass {



    leaderBoard obj_leaderBoard = new leaderBoard();





    @Test(priority = 1, description = "Verify the User Interaction",groups = {"Leaderboard,","search"})
    @Severity(SeverityLevel.NORMAL)
    @Feature("Leaderboard")
    @Description("This test attempts to User Interaction how the system react the Invalid data such as Non-existent name" +
            "special character and mixed with special character")

    public void CP_TC_01_verifyUerInteraction(){
        obj_leaderBoard.clickLeaderBoard();
        obj_leaderBoard.enterTextSearchBox("");

    }


















}
