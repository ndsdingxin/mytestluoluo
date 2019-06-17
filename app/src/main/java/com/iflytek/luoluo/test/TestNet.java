package com.iflytek.luoluo.test;


import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData;
import com.iflytek.luoluo.interceptor.RequestInterceptor;
import com.iflytek.luoluo.util.OkHttpUtil;

public class TestNet {

    public static  void  main (String args[]){
     new OkHttpUtil(StateData.url, RequestInterceptor.getheaders(), RequestInterceptor.getJsson(), new OkHttpUtil.SuccessfullCallback() {
            @Override
            public String onSuccessfullCallback(String str) {
                System.out.println(str);
                data(str);
                return str;
            }
        });
           /*    String str =  OkHttpUtil.sendSyncSingleHttp(StateData.url,RequestInterceptor.getheaders(),RequestInterceptor.getJsson());
            System.out.println(str+"==========================");*/
    }

    public static void  data(String str){
        System.out.println(str+"===============================");
    }
}
