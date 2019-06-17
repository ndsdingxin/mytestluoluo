package com.iflytek.luoluo.interceptor;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.bean.PrivateData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.SecurityUtil;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.bean.PublicData;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.bean.RequestMode;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.exception.ExConstants;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.exception.OpensnsException;
import com.iflytek.luoluo.pojo.Api;
import com.iflytek.luoluo.util.DataBaseUtil;

import org.apache.xml.serialize.XMLSerializer;


public  class RequestInterceptor implements Interceptor {
    private static   List<String[][]> list;

    public void setList(List<String[][]> list) {
        this.list = list;
    }

    private static String shuihao = com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.user_Tax;
    private static final String[] TAXNUM = { "taxnum","taxNum"};
    private static String INVOICETIMESTART ="invoiceTimeStart";
    private static String INVOICETIMEEND = "invoiceTimeEnd";
    private static String start = "2018-01-01 00:00:00";
    private static String end = "2019-05-01 23:59:59";
    @Override
    public Response intercept(Chain chain) throws IOException {



        Map<String, String> headers = getheaders();
        Request request = chain.request()
                .newBuilder()
                .addHeader("Connection", headers.get("Connection"))
                .addHeader("userTax", headers.get("userTax"))
                .addHeader("compress", headers.get("compress"))
                .addHeader("appKey", headers.get("appKey"))
                .addHeader("appRate", headers.get("appRate"))
                .addHeader("dataType", headers.get("dataType"))
                .addHeader("signMethod", headers.get("signMethod"))
                 .addHeader("Content-Type", headers.get("Content-Type"))
                 .addHeader("accessToken", headers.get("accessToken"))
//                    .addHeader("Cookie", "add cookies here")
                .build();
        Log.v("zcb", "request:" + request.toString());
        Log.v("zcb", "request headers:" + request.headers().toString());
        return chain.proceed(request);
    }
    public static Map<String, String> getheaders(){

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("userTax", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.user_Tax); // ISV下商家需要填写当前商户税号，普通商家模式用户可以不填写此值
        headers.put("compress", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_compressType);// 压缩方式：提供GZIP 置空“”不压缩
        headers.put("appKey", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_key); // 用户申请的appkey
        headers.put("appRate", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_rate); // app并发请求数 ，平台默认10如需升级请联系开放平台
        headers.put("dataType", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_dataType); // 数据请求格式： JSON/XML
        headers.put("signMethod", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_signType); // 加密方式：提供AES/AES，不可为空
        headers.put("accessToken", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.app_accessToken); // 用户Oauth登录后得到的令牌accessToken
        headers.put("Content-Type", com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData.contentType); // http发送模式
        if (list!=null){
            String head[][] = list.get(1);
            if (head.length!=0){
                headers.clear();
                for (int i=0;i<head.length;i++){
                    headers.put(head[i][0],head[i][1]);
                }
            }
        }
        return headers;

    }

    private static PrivateData<Object> getPvData() {
        //私有请求参数消息体

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TAXNUM[0], StateData.taxNum);
        map.put(INVOICETIMESTART,start);
        map.put(INVOICETIMEEND, end);
        if (list!=null){
            map.clear();
            String pvmap[][] = list.get(3);
            if (pvmap.length!=0){
                map.clear();
                for (int i=0;i<pvmap.length;i++){
                    map.put(pvmap[i][0],pvmap[i][1]);
                }
            }
        }
        new PrivateData<Object>();
        PrivateData<Object> pvData = new PrivateData<Object>();
        List<Map<String, Object>> pvlist = new ArrayList<Map<String, Object>>();
        pvlist.add(map);
        pvData.setServicedata(pvlist);
        return pvData;
    }

    // 定义请求体中的公共数据
    private static PublicData getPublicData() {
        String version= StateData.app_apiVersion;
        String timestamp= String.valueOf(System.currentTimeMillis());
        String method= StateData.API_METHOD[8];
        if (list!=null){
            String id = list.get(0)[0][1];
            if (!id.equals("")){
                List<Api> apis = DataBaseUtil.apibyid(id);
                Api api = apis.get(0);
                 version= api.getVersion();
                 timestamp= String.valueOf(System.currentTimeMillis());;
                 method= api.getApi();
            }
        }
        PublicData pdData = new PublicData();
        pdData.setVersion(version); // API版本
        pdData.setTimestamp(timestamp);
        pdData.setMethod(method);// API名称
        return pdData;
    }

    private static RequestMode getRequestMode(PublicData pdata, PrivateData<Object> pvData) {
        RequestMode requestMode = new RequestMode();
        requestMode.setPrivate(pvData);
        requestMode.setPublic(pdata);
        return requestMode;
    }


    public static  String getJsson (){

        String clientReqParam = "";
        String aesParams = "";
        Map<String, String> headers = getheaders();
        RequestMode requestMode = getRequestMode(getPublicData(), getPvData());
        try {
            if ("json".equalsIgnoreCase(headers.get("dataType"))) {
                clientReqParam = installJsonStr(requestMode, clientReqParam);
            } else if ("xml".equalsIgnoreCase(headers.get("dataType"))) {
               // clientReqParam = installXmlStr(requestMode);
            }
        } catch (Exception e) {
           // throw new OpensnsException(ExConstants.input_Data_Type, ExConstants.input_Data_Type_Msg);
        }
        System.out.println("客户端组装的参数格式-->：" + clientReqParam);
        try {
            // 每个APP密钥不同，根据当前APP赋值相应密钥值
            aesParams = (String) SecurityUtil.AESEncrypt(clientReqParam, StateData.app_secret);
        } catch (Exception e) {
           // throw new OpensnsException(ExConstants.security_Decryption, ExConstants.security_Encryption_Msg);
        }
        return  aesParams;
    }

    private static String installJsonStr(RequestMode requestMode, String clientReqParam) throws Exception {
        JSONObject jSONObj = new JSONObject();
        JSONObject publicObj = new JSONObject();
        JSONObject privateObj = new JSONObject();
        publicObj.put("method", requestMode.getPublic().getMethod());
        publicObj.put("version", requestMode.getPublic().getVersion());
        publicObj.put("timestamp", requestMode.getPublic().getTimestamp());
        privateObj.put("servicedata", requestMode.getPrivate().getServicedata());
        jSONObj.put("public", publicObj);
        jSONObj.put("private", privateObj);
        clientReqParam = jSONObj.toString();
        return clientReqParam;
    }

   /* private static String installXmlStr(RequestMode requestMode) throws Exception {
        JSONObject jsonObject =JSONObject.fromObject(requestMode);
        JSON.
        XMLSerializer xml = new XMLSerializer();
        String xmlStr = xml.write(jsonObject, "UTF-8");
        return xmlStr;
    }*/
}