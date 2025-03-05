package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  dataBaseConnect {


    String connectionUrl = "jdbc:sqlserver://heropointsdev.database.windows.net;encrypt=true;" +
            "database=heropointsdevdb;user=readuser;password=User@2025;";

     Connection con;

    public  Connection dbconnect() {
        {
            try {
                con = DriverManager.getConnection(connectionUrl);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }






}
