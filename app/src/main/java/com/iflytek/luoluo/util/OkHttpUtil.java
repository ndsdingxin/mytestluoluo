package com.iflytek.luoluo.util;

import android.util.Log;

import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.service.HttpClientService;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.Compress;
import com.iflytek.luoluo.interceptor.RequestInterceptor;

import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;


public class OkHttpUtil {
    private static String str;

    private String serverName;
    private Map<String, String> headers;
    private  String aesParams;
    OkHttpUtil( String serverName,  Map<String, String> headers,   String aesParams){
        this.serverName=serverName;
        this.headers=headers;
        this.aesParams=aesParams;
    };
 public static String sendSyncSingleHttp( String serverName,  Map<String, String> headers,   String aesParams){
     OkHttpClient okHttpClient = new OkHttpClient();

     OkHttpClient.Builder builder=   new OkHttpClient.Builder();
     // builder.addInterceptor(new RequestInterceptor());
     String param ="";
     try{
         param = java.net.URLEncoder.encode(aesParams, HTTP.UTF_8);
         if ("GZIP".equals((String)headers.get("compress"))) {
             param = Compress.compress(param); // 压缩
         }
     }catch (Exception e){
         e.printStackTrace();
     }
     //   RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), aesParams);

     FormBody formbody = new FormBody.Builder().add("param",param).build();
     // RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), aesParams);
     //创建request
     Request request = new Request
             .Builder().
             addHeader("userTax", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.user_Tax).
             addHeader("compress", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_compressType).
             addHeader("appKey", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_key).
             addHeader("appRate", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_rate).
             addHeader("dataType", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_dataType).
             addHeader("signMethod", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_signType).
             addHeader("accessToken", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_accessToken).
             addHeader("Accept-Encoding", "gzip").
             addHeader("Content-Type", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.contentType)
             .url(serverName)
             .post(formbody)
             .build();
     okHttpClient.newCall(request).enqueue(new Callback() {
         @Override
         public void onFailure(Call call, IOException e) {

         }

         @Override
         public void onResponse(Call call, Response response) throws IOException {

             str=new HttpClientService().printResult(response);
             System.out.println("123");
             //                Log.d("tag" ,"onResponse: "+response.body().string());
         }
     });

     return  str;
 };


 //调用的方法
    public  OkHttpUtil(final String serverName, final Map<String, String> headers, final  String aesParams, final SuccessfullCallback successfullCallback){

            OkHttpClient okHttpClient = new OkHttpClient();

            OkHttpClient.Builder builder=   new OkHttpClient.Builder();
            // builder.addInterceptor(new RequestInterceptor());
            String param ="";
            try{
                param = java.net.URLEncoder.encode(aesParams, HTTP.UTF_8);
                if ("GZIP".equals((String)headers.get("compress"))) {
                    param = Compress.compress(param); // 压缩
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            //   RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), aesParams);

            FormBody formbody = new FormBody.Builder().add("param",param).build();
            // RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), aesParams);
            //创建request
            Request request = new Request
                    .Builder().
                    addHeader("userTax", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.user_Tax).
                    addHeader("compress", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_compressType).
                    addHeader("appKey", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_key).
                    addHeader("appRate", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_rate).
                    addHeader("dataType", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_dataType).
                    addHeader("signMethod", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_signType).
                    addHeader("accessToken", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_accessToken).
                    addHeader("Accept-Encoding", "gzip").
                    addHeader("Content-Type", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.contentType)
                    .url(serverName)
                    .post(formbody)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    str=new HttpClientService().printResult(response);
                    successfullCallback.onSuccessfullCallback(str);
                    System.out.println("123");
                    //                Log.d("tag" ,"onResponse: "+response.body().string());
                }
            });
    }

  public  interface  SuccessfullCallback{
        public String onSuccessfullCallback(String str);
    }

}
