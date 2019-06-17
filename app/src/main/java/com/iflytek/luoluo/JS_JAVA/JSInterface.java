package com.iflytek.luoluo.JS_JAVA;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.iflytek.luoluo.MainActivity;
import com.iflytek.luoluo.pojo.Api;
import com.iflytek.luoluo.util.DataBaseUtil;

import java.util.List;

public class JSInterface {
    public static final String JS_INTERFACE_NAME = "JSInterface";//JS调用类名
    private Context mContext;
    private WebView webView;

    public JSInterface(Context mContext, WebView webView) {
        this.mContext = mContext;
        this.webView = webView;
    }

    public JSInterface() {
    }

    @JavascriptInterface
    public String apibyid() {
        String str = "";
        List<Api> list= DataBaseUtil.apibyid(MainActivity.jsdata());
        String id1 = MainActivity.jsdata().substring(MainActivity.jsdata().indexOf("==") + 2);
        Gson gson = new Gson();
        str = gson.toJson(list);
        return str;
    }

    @JavascriptInterface
    public String aaaaa() {
        Gson gson = new Gson();
        String str = gson.toJson(DataBaseUtil.findApiData());
        return str;
    }
    @JavascriptInterface
    public String appmsgget() {
        Gson gson = new Gson();
        String str = gson.toJson(DataBaseUtil.findappmsg());
        return str;
    }
    @JavascriptInterface
    public String tokenmsgbyid(String id) {
        Gson gson = new Gson();
        String str = gson.toJson(DataBaseUtil.findtokenmsgbyid(id));
        return str;
    }



   /* @JavascriptInterface
    public void hello(String content) {
        Log.i("bqt", "JS 调用原生时是否发生在主线程：" + (Looper.myLooper() == Looper.getMainLooper()));//false
        new Handler(Looper.getMainLooper()).post(() -> //WebView等UI操作必须在主线程中进行
                Toast.makeText(mContext, "原生的hello方法被调用了：" + content, Toast.LENGTH_SHORT).show());

        SystemClock.sleep(3000);//模拟耗时操作

        String call = "javascript:javacalljs(" + System.currentTimeMillis() + ")";//格式很重要，大部分错误都是由于格式问题导致的
        new Handler(Looper.getMainLooper()).post(() -> webView.loadUrl(call));//WebView等UI操作必须在主线程中进行
    }

    @JavascriptInterface
    public void hello2(String content) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show());

        SystemClock.sleep(3000);//模拟耗时操作

        String call = "javascript:javacalljs2(" + System.currentTimeMillis() + ")";//JS此方法的返回值会通过onReceiveValue回调到原生
        new Handler(Looper.getMainLooper()).post(() -> webView.evaluateJavascript(call, value -> {
            Log.i("bqt", "ValueCallback 是否发生在主线程：" + (Looper.myLooper() == Looper.getMainLooper()));//true
            Toast.makeText(mContext, "【onReceiveValue】" + value, Toast.LENGTH_SHORT).show();
        }));
    }*/
}

