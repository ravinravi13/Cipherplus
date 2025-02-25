package Utilities;

import Cipherplus.Base.BaseClass;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class dataProvider extends BaseClass {



    public void readDataFromExcel(){

        try {
            FileInputStream file;
            file = new FileInputStream(System.getProperty("user.dir")+"\\testData\\data.xlxs");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }





}
