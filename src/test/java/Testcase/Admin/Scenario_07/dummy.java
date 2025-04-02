package Testcase.Admin.Scenario_07;

import Cipherplus.Base.BaseClass;
import Utilities.dataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dummy extends BaseClass {
    static dataBaseConnect obj_dataBaseConnect =new dataBaseConnect();

    public static void main(String[] args) {


        String Empname = "Sachin Jaiswal";




        Connection connection = obj_dataBaseConnect.dbconnect();
        String query ="  select E.Name ,T.Name,T.Date,T.EmpName,T.Type,T.Points from [dbo].[Transaction] T inner join Employee E on T.UserId=E.EmployeeId where E.Name=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1,Empname);

            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                while(resultSet.next())
                {
                    String cloumn1 = resultSet.getString(1);
                    String cloumn2 = resultSet.getString(2);
                    String cloumn3 = resultSet.getString(3);
                    String cloumn4 = resultSet.getString(4);
                    String cloumn5 = resultSet.getString(5);
                    String cloumn6 = resultSet.getString(6);

                    System.out.println(cloumn1 +" "+ cloumn2+" "+cloumn3+ " "+cloumn4 +" " +cloumn5 + " "+cloumn6);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }











}
