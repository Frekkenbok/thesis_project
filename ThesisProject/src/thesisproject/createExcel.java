/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisproject;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 *
 * @author Евгения
 */
public class createExcel {
 Connection conn = null;
 Statement stat = null;
 ResultSet res = null;
 String userName = "root";
 String password = "Password";
 String url = "jdbc:mysql://localhost:3306/testdb";
 
    public static void main(String[] args)
    {
     Workbook wb = new HSSFWorkbook();
    Sheet sheet = wb.createSheet("new sheet");

    Header header = sheet.getHeader();
    header.setCenter("Center Header");
    header.setLeft("Left Header");
String LAST_NAME_COLUMN_HEADER = "Фамилия";
String Quantity_COLUMN_HEADER = "Кол-во";
HSSFRow row;
        HSSFCell cell;
   row = (HSSFRow) sheet.createRow(0);
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellValue(LAST_NAME_COLUMN_HEADER); 
        cell = row.createCell(1, Cell.CELL_TYPE_STRING);
        cell.setCellValue(Quantity_COLUMN_HEADER); 
        try
        {
             FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Евгения\\Desktop\\workbook.xls");
    wb.write(fileOut);
    fileOut.close();
            System.out.println("Done");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
