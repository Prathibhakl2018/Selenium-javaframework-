package Databind;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Bind {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
   FileInputStream file  = new FileInputStream(System.getProperty("user.dir")+"\\Testdata\\test.xlsx");
   XSSFWorkbook workbook = new XSSFWorkbook(file);
    XSSFSheet sheet=workbook.getSheet("sheet1");
   int noofrows= sheet.getLastRowNum();
  int   totalcell =sheet.getRow(1).getLastCellNum();
  System.out.println("number of rows"+noofrows);
  System.out.println("number of cell"+totalcell);
  for(int r=0;r<=noofrows;r++) {
	  XSSFRow currentrow=sheet.getRow(r);
            for(int c=0;c<totalcell;c++) {
            	XSSFCell currentcell=currentrow.getCell(c);
            	System.out.println(currentcell.toString());
            	
            	//
                    }
            System.out.println();
            
  }

   workbook.close();
   file.close();
	}

}

