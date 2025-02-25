package Utilities;

import Cipherplus.Base.BaseClass;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class readExcel extends BaseClass {

//    // Excel data --> Workbook --> Sheets --> Rows ---> Cells
//

    public static FileInputStream fi;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle   style;
    public static FileOutputStream fo;









    public static int getRowCount(String excelFilePath,String excelSheet)
    {
       try
       {
           fi = new FileInputStream(excelFilePath);
           wb = new XSSFWorkbook(fi);
           ws = wb.getSheet(excelSheet);
           int rowCount = ws.getLastRowNum();
           wb.close();
           fi.close();
           return rowCount;
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

    }

   public static int getCellCount(String excelFilePath,String excelSheet,int rowNum)
   {
       try
       {
           fi = new FileInputStream(excelFilePath);
           wb = new XSSFWorkbook(fi);
           ws = wb.getSheet(excelSheet);
           row = ws.getRow(rowNum);
           int cellCount = row.getLastCellNum();
           wb.close();
           fi.close();
           return cellCount;
       }catch (IOException e) {
           throw new RuntimeException(e);
       }

   }

   public static String getCellData(String excelFilePath, String excelSheet,int rowNum,int colNum) throws IOException {


           fi = new FileInputStream(excelFilePath);
           wb = new XSSFWorkbook(fi);
           ws = wb.getSheet(excelSheet);
           row = ws.getRow(rowNum);
           cell = row.getCell(colNum);
           String data;
           try {
               DataFormatter formatter = new DataFormatter();
               data = formatter.formatCellValue(cell);
           } catch (Exception e) {
              data="";
           }

       wb.close();
       fi.close();
       return data;

   }


  public static void setCellData(String excelFile,String excelSheet,int rowNum,int colNum,String Data)
  {
      try {
          fi = new FileInputStream(excelFile);
          wb = new XSSFWorkbook(fi);
          ws = wb.getSheet(excelSheet);
          row = ws.getRow(rowNum);
          cell = row.createCell(colNum);
          cell.setCellValue(Data);
          fo = new FileOutputStream(excelFile);
          wb.write(fo);
          wb.close();
          fi.close();
          fo.close();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

  }


  public static void fillGreenColor(String excelFile,String excelSheet,int rowNum,int colNum)
  {
      try {
          fi = new FileInputStream(excelFile);
          wb = new XSSFWorkbook(fi);
          ws = wb.getSheet(excelSheet);
          row = ws.getRow(rowNum);
          cell = row.getCell(colNum);
          style = wb.createCellStyle();
          style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
          style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          cell.setCellStyle(style);
          fo = new FileOutputStream(excelFile);
          wb.write(fo);
          wb.close();
          fi.close();
          fo.close();

      } catch (Exception e) {
          throw new RuntimeException(e);
      }
  }



    public static void fillRedColor(String excelFile,String excelSheet,int rowNum,int colNum)
    {
        try {
            fi = new FileInputStream(excelFile);
            wb = new XSSFWorkbook(fi);
            ws = wb.getSheet(excelSheet);
            row = ws.getRow(rowNum);
            cell = row.getCell(colNum);
            style = wb.createCellStyle();
            style.setFillBackgroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
            fo = new FileOutputStream(excelFile);
            wb.write(fo);
            wb.close();
            fi.close();
            fo.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }









//    public static void main(String[] args) {
//        try {
//            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/testData/testdata.xlsx");
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//            XSSFSheet sheet = workbook.getSheet("Sheet1");
//            int totalRows = sheet.getLastRowNum();
//            int totalCells = sheet.getRow(1).getLastCellNum();
//
//            System.out.println("Total rows :" + totalRows);
//            System.out.println("Total cells :" + totalCells);
//////
//            for (int row = 1; row <= totalRows; row++) {
//                XSSFRow currentRow = sheet.getRow(row);
//
//                for (int cel = 0; cel < totalCells; cel++) {
//                    XSSFCell cell = currentRow.getCell(cel);
//                    System.out.println(cell.toString());
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
}


















