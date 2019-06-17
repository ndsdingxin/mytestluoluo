package com.iflytek.luoluo.ExceUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.iflytek.luoluo.pojo.Api;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

	public class ExcelWriter {
	    
        private OutputStream os = null;
        
        public ExcelWriter(String excelPath) throws FileNotFoundException{
            this.os = new FileOutputStream(excelPath);
        }
        
        public boolean excelWrite(List list){
            try {
                //创建一个可写的Workbook
                WritableWorkbook wwb = Workbook.createWorkbook(os);
                
                //创建一个可写的sheet,第一个参数是名字,第二个参数是第几个sheet
                WritableSheet sheet = wwb.createSheet("第一个sheet", 0);
                String title[]= {"id","api","版本","备注"};
                for (int i = 0; i < title.length; i++) {
                	sheet.addCell(new Label(i, 0, title[i]));
				}
                for(int i=0; i<list.size(); i++){
                	Api api = (Api)list.get(i);
                    
                    //创建一个Label,第一个参数是x轴,第二个参数是y轴,第三个参数是内容,第四个参数可选,指定类型
                    Label label1 = new Label(0, i+1, api.getId()+"");
                    Label label2 = new Label(1, i+1, api.getApi());
                    Label label3 = new Label(2, i+1, api.getVersion());
                    Label label4 = new Label(3, i+1, api.getRemark());

                    //把label加入sheet对象中
                    sheet.addCell(label1);
                    sheet.addCell(label2);
                    sheet.addCell(label3);
                    sheet.addCell(label4);
                }
                //保存到Workbook中
                wwb.write();
                //只有执行close时才会写入到文件中,可能在close方法中执行了io操作
                wwb.close();
                
                return true;
                
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
            
            return false;
        }
        
    }
