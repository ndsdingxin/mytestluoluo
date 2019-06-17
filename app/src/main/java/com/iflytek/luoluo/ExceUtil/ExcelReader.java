package com.iflytek.luoluo.ExceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.iflytek.luoluo.pojo.Api;
import com.iflytek.luoluo.pojo.AppMsg;
import com.iflytek.luoluo.pojo.TokenMsg;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelReader {
    
    private  InputStream is = null;
    private  List<Object> plList = new ArrayList<Object>();
    private Integer i;
    public ExcelReader(String excelPath ,Integer i) throws FileNotFoundException{
        this.i = i;
        try{
            this.is = new FileInputStream(new File(excelPath));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public  List<Object> excelReader(){
        if (i==1){
            try {
                Workbook book = Workbook.getWorkbook(is);
                Sheet sheet =  book.getSheet(0);
                for(int i=1; i<sheet.getRows(); i++){

                    AppMsg p=new AppMsg(sheet.getCell(0, i).getContents(),
                            sheet.getCell(1, i).getContents(),
                            sheet.getCell(2, i).getContents(),
                            sheet.getCell(3, i).getContents(),
                            sheet.getCell(4, i).getContents(),
                            sheet.getCell(5, i).getContents()
                    );
                    plList.add(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (i==2){
            try {
                Workbook book = Workbook.getWorkbook(is);
                Sheet sheet =  book.getSheet(0);
                for(int i=1; i<sheet.getRows(); i++){

                    TokenMsg p=new TokenMsg(sheet.getCell(0, i).getContents(),
                            sheet.getCell(1, i).getContents(),
                            sheet.getCell(2, i).getContents(),
                            sheet.getCell(3, i).getContents(),
                            sheet.getCell(4, i).getContents()
                    );
                    plList.add(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                Workbook book = Workbook.getWorkbook(is);
                Sheet sheet =  book.getSheet(0);
                for(int i=1; i<sheet.getRows(); i++){

                    Api p=new Api(sheet.getCell(0, i).getContents(),
                            sheet.getCell(1, i).getContents(),
                            sheet.getCell(2, i).getContents(),
                            sheet.getCell(3, i).getContents()
                    );
                    plList.add(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  plList;
    }
}
