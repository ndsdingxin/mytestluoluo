package com.iflytek.luoluo.util;

import java.util.ArrayList;
import java.util.List;

public class StrInputDataUtil {
    private static List<String[][]> list;
    public  static List<String[][]> processinputdata(String str){
         list = new ArrayList<String[][]>();
        String s1=str.substring(str.indexOf("==")+2,str.indexOf("publicHeadinput"));
        System.out.println(s1);
        String [][] api= {{"id",s1}};
        String s2=str.substring(str.indexOf("++")+2,str.indexOf("pulicBodyinput"));
        System.out.println(s2);
        String arr1[]=s2.split("&");
        String [][] pbh= new String[arr1.length][2];
        for (int i=0;i<arr1.length;i++){
            String arr11[]=arr1[i].split("=");
                pbh[i][0] = arr11[0];
                pbh[i][1] = arr11[1];
        }
        System.out.println(pbh);
        list.add(pbh);
        String s3=str.substring(str.indexOf("--")+2,str.indexOf("privateBodyinput"));
        System.out.println(s3);
        String arr2[]=s3.split("&");
        String [][] pbb= new String[arr2.length][2];
        for (int i=0;i<arr2.length;i++){
            String arr21[]=arr2[i].split("=");
            pbb[i][0] = arr21[0];
            pbb[i][1] = arr21[1];
        }
        System.out.println(pbb);
        list.add(pbb);
        String s4=str.substring(str.indexOf("**")+2);
        System.out.println(s4);

        String arr3[]=s4.split("&");
        String [][] pvb= new String[arr3.length][2];
        for (int i=0;i<arr3.length;i++){
            String arr31[]=arr3[i].split("=");
            pvb[i][0] = arr31[0];
            pvb[i][1] = arr31[1];
        }
        System.out.println(pvb);
        list.add(pvb);

        return  list;
    }

}
