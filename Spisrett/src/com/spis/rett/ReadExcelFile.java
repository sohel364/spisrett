package com.spis.rett;
import java.io.File;
import java.io.IOException;

import android.content.Context;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcelFile {

  private String inputFile;
  Context context;
  
  public  ReadExcelFile(Context context) {
	this.context=context;
  }
  
  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }
  

  public void read() throws IOException  {
    File inputWorkbook = new File(inputFile);
    
    int dataLimit=50;
    String[][] product=new String[dataLimit][12];
    String[] nutritions=new String[39];
    float [][] nutr_info=new float[dataLimit][39];
    
    String temp;
    Workbook w;
    try {
      w = Workbook.getWorkbook(inputWorkbook);
      // Get the first sheet
      Sheet sheet = w.getSheet(0);
      Cell cell;
      CellType type;
      for(int i=11;i<50;i++)
      {
    	   cell = sheet.getCell(i, 0);
           type = cell.getType();
          nutritions[i-11]=cell.getContents();
      }
      
      for (int j = 1; j <= dataLimit; j++) {
    	  product[j-1][0]="j";
        for (int i = 2; i < 11; i++) {
        	
          cell = sheet.getCell(i, j);
          type = cell.getType();
          if (type == CellType.EMPTY) {
        	  temp="";
          }
          else 
          {
        	  temp=cell.getContents();
        	  product[j-1][i-1]=temp;
          }
        }
       
      }
      
      for (int j = 1; j <= dataLimit; j++) {
    	
        for (int i = 11; i < 50; i++) {
        	
          cell = sheet.getCell(i, j);
          type = cell.getType();
          if (type == CellType.EMPTY) {
        	  nutr_info[j-1][i-11]=0;
          }
          else 
          {
        	  temp=cell.getContents();
        	  try {
        		  nutr_info[j-1][i-11]=Float.parseFloat(cell.getContents());
			} catch (NumberFormatException e) {
				nutr_info[j-1][i-11]=0;
			}
        	  
          }
             
        }
      }
      
      new DatabaseManager(context).addOfflineData(nutritions,product,nutr_info);
    } catch (BiffException e) {
      e.printStackTrace();
    }
  }

} 