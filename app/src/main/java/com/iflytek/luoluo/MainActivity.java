package com.iflytek.luoluo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.luoluo.JS_JAVA.JSInterface;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util.StateData;
import com.iflytek.luoluo.interceptor.RequestInterceptor;
import com.iflytek.luoluo.myactivity.ApiPutActivity;
import com.iflytek.luoluo.pojo.Api;
import com.iflytek.luoluo.util.DataBaseUtil;
import com.iflytek.luoluo.util.JsUtil;
import com.iflytek.luoluo.util.OkHttpUtil;
import com.iflytek.luoluo.util.StrInputDataUtil;
import com.iflytek.mytestluoluo.R;

import java.util.List;

public class MainActivity extends Activity {
    private WebView webView;
    private AlertDialog dialog;
    private List<Api> list;
    private static String jsdata;
    private Handler handler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DataBaseUtil(this);
        list = DataBaseUtil.findApiData();
        final String str = getIntent().getStringExtra("msg");
        webView = findViewById(R.id.wwtestvoice1);
        WebSettings settings = webView.getSettings();
// 存储(storage)
// 启用HTML5 DOM storage API，默认值 false
        settings.setDomStorageEnabled(true);
// 启用Web SQL Database API，这个设置会影响同一进程内的所有WebView，默认值 false
// 此API已不推荐使用，参考：https://www.w3.org/TR/webdatabase/
        settings.setDatabaseEnabled(true);
// 启用Application Caches API，必需设置有效的缓存路径才能生效，默认值 false
// 此API已废弃，参考：https://developer.mozilla.org/zh-CN/docs/Web/HTML/Using_the_application_cache
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(this.getCacheDir().getAbsolutePath());
// 定位(location)
        settings.setGeolocationEnabled(true);
// 是否保存表单数据
        settings.setSaveFormData(true);
// 是否当webview调用requestFocus时为页面的某个元素设置焦点，默认值 true
        settings.setNeedInitialFocus(true);
// 是否支持viewport属性，默认值 false
// 页面通过`<meta name="viewport" ... />`自适应手机屏幕
        settings.setUseWideViewPort(true);
// 是否使用overview mode加载页面，默认值 false
// 当页面宽度大于WebView宽度时，缩小使页面宽度等于WebView宽度
        settings.setLoadWithOverviewMode(true);

// 布局算法
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
// 是否支持Javascript，默认值false
        settings.setJavaScriptEnabled(true);
// 是否支持多窗口，默认值false
        settings.setSupportMultipleWindows(true);
// 是否可用Javascript(window.open)打开窗口，默认值 false
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
// 资源访问
        settings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        settings.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
// 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        settings.setAllowFileAccessFromFileURLs(true);
// 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        settings.setAllowUniversalAccessFromFileURLs(true);
// 资源加载
        settings.setLoadsImagesAutomatically(true); // 是否自动加载图片
        settings.setBlockNetworkImage(false);       // 禁止加载网络图片
        settings.setBlockNetworkLoads(false);       // 禁止加载所有网络资源
// 缩放(zoom)
        settings.setSupportZoom(true);          // 是否支持缩放
        settings.setBuiltInZoomControls(true);// 是否使用内置缩放机制
        settings.setDisplayZoomControls(false);  // 是否显示内置缩放控件
// 默认文本编码，默认值 "UTF-8"
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setDefaultFontSize(16);        // 默认文字尺寸，默认值16，取值范围1-72
        settings.setDefaultFixedFontSize(16);   // 默认等宽字体尺寸，默认值16
        settings.setMinimumFontSize(8);         // 最小文字尺寸，默认值 8
        settings.setMinimumLogicalFontSize(8);  // 最小文字逻辑尺寸，默认值 8
        settings.setTextZoom(100);              // 文字缩放百分比，默认值 100
// 字体
        settings.setStandardFontFamily("sans-serif");   // 标准字体，默认值 "sans-serif"
        settings.setSerifFontFamily("serif");           // 衬线字体，默认值 "serif"
        settings.setSansSerifFontFamily("sans-serif");  // 无衬线字体，默认值 "sans-serif"
        settings.setFixedFontFamily("monospace");       // 等宽字体，默认值 "monospace"
        settings.setCursiveFontFamily("cursive");       // 手写体(草书)，默认值 "cursive"
        settings.setFantasyFontFamily("fantasy");       // 幻想体，默认值 "fantasy"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 用户是否需要通过手势播放媒体(不会自动播放)，默认值 true
            settings.setMediaPlaybackRequiresUserGesture(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0以上允许加载http和https混合的页面(5.0以下默认允许，5.0+默认禁止)
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 是否在离开屏幕时光栅化(会增加内存消耗)，默认值 false
            settings.setOffscreenPreRaster(false);
        }
        /*if (isNetworkConnected(this)) {
            // 根据cache-control决定是否从网络上取数据
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            // 没网，离线加载，优先加载缓存(即使已经过期)
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }*/


// deprecated
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setDatabasePath(this.getDir("database", Context.MODE_PRIVATE).getPath());
        settings.setGeolocationDatabasePath(this.getFilesDir().getPath());
        if (str != null) {
            if (str.contains("apiupd")) {
                String url = "file:///android_asset/html/apiupd.html";
                webView.loadUrl(url);
            }else if (str.contains("--")) {
                String url = "file:///android_asset/html/privateBody.html";
                webView.loadUrl(url);

            }else if (str.contains("++")) {
                String url = "file:///android_asset/html/publicBody.html";
                webView.loadUrl(url);
            }else if (str.contains("==")) {
                String url = "file:///android_asset/html/publicHead.html";
                webView.loadUrl(url);
            }
        } else {
            webView.loadUrl("file:///android_asset/html/api.html");
        }


        // 对应当前project的asserts目录
        // 也可以写file:///sdcard/index.html
        // 因为127.0.0.1被gynimotion虚拟机占用 所以用cmd ipv4 真实地址
//        mWebView.loadData("http://169.254.253.95:8080/equipsystem/get", "text/html", "UTF-8");
//         mWebView.loadUrl("http://169.254.253.95:8080/equipsystem/get");
//         mWebView.loadUrl("https://www.baidu.com");
        // webView.loadUrl("http://www.baidu.com/");//调用loadUrl方法为WebView加入链接,测试环境


        // The undocumented magic method override
        // Eclipse will swear at you if you try to put @Override here
        // For Android 3.0+

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what==1){
                    webView.loadUrl("file:///android_asset/html/api.html");
                }else  if (msg.what==2){
                    Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                }else  if (msg.what==3){

                }
            }
        };
        webView.addJavascriptInterface(new JSInterface(), JSInterface.JS_INTERFACE_NAME);

        webView.setWebChromeClient(new WebChromeClient() {
            /**
             * 处理alert弹出框
             */
            @Override
            public boolean onJsAlert(WebView view, String url, final String message, final JsResult result) {
                //api导入

                jsdata = message;
                Log.d("ssss", "Alert:-------------" + message);
               /* new Thread(new Runnable() {
                    @Override
                    public void run() {*/
                try {
                    dialog = new AlertDialog
                            .Builder(MainActivity.this)
                            .setTitle("Alert").setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (message.contains("dpput")){
                                                JsUtil.dpput(message, MainActivity.this);
                                                Message m1 = new Message();
                                                m1.what=1;
                                                handler.sendMessage(m1);
                                            }else   if (message.contains("dbapidel")){
                                                DataBaseUtil.dbapidel();
                                                Message m1 = new Message();
                                                m1.what=1;
                                                handler.sendMessage(m1);

                                            }else   if (message.contains("dbappkeyput")){
                                                JsUtil.dbappkeyput(message, MainActivity.this);
                                                Message m1 = new Message();
                                                m1.what=1;
                                                handler.sendMessage(m1);

                                            }else   if (message.contains("dbappkeydel")){
                                                DataBaseUtil.dbappkeydel();
                                                Message m1 = new Message();
                                                m1.what=1;
                                                handler.sendMessage(m1);

                                            }else   if (message.contains("dbusertaxput")){
                                                JsUtil.dbusertaxput(message, MainActivity.this);
                                                Message m1 = new Message();
                                                m1.what=1;
                                                handler.sendMessage(m1);

                                            }else   if (message.contains("dbusertaxdel")){
                                                DataBaseUtil.dbusertaxdel();
                                                Message m1 = new Message();
                                                m1.what=1;
                                                handler.sendMessage(m1);

                                            }else if (message.contains("apidel")) {
                                                //apidel
                                                JsUtil.apidel(message);
                                                Message m1 = new Message();
                                                m1.what=1;
                                                handler.sendMessage(m1);
                                            } else if (message.contains("apiupdinput")) {
                                                //apiupdinput
                                                DataBaseUtil.apiupdinput(message);
                                                Message m1 = new Message();
                                                m1.what=1;
                                                handler.sendMessage(m1);
                                            } else if (message.contains("apiupd")) {
                                                //apiupd
                                                JsUtil.apiupd(message, MainActivity.this);
                                            } else if (message.contains("apiuse")) {
                                                //apiupd

                                                JsUtil.apiuse(message, MainActivity.this);
                                            }else if (message.contains("publicHeadinput")) {
                                                //apiupd
                                                String ss =str+message.toString();
                                                JsUtil.publicHeadinput(ss, MainActivity.this);
                                            }else if (message.contains("pulicBodyinput")) {
                                                //apiupd
                                                List<String[][]> list = StrInputDataUtil.processinputdata(message.toString());
                                                DataBaseUtil.updaip(list);
                                                String ss =str+message.toString();
                                                JsUtil.pulicBodyinput(ss, MainActivity.this);
                                            }else if (message.contains("privateBodyinput")) {
                                                //apiupd
                                             //   JsUtil.privateBodyinput(message+str, MainActivity.this);
                                                String ss =str+message.toString();
                                                List<String[][]> list = StrInputDataUtil.processinputdata(ss);
                                                new RequestInterceptor().setList(list);
                                               /* new OkHttpUtil(StateData.url, RequestInterceptor.getheaders(), RequestInterceptor.getJsson(), new OkHttpUtil.SuccessfullCallback() {
                                                    @Override
                                                    public String onSuccessfullCallback(String str) {
                                                        Message m1 = new Message();
                                                        m1.what=2;
                                                        m1.obj=str;
                                                        handler.sendMessage(m1);
                                                        return str;
                                                    }
                                                });*/

                                            }
                                        }
                                    }).start();

                                    result.confirm();

                                }
                            }).setCancelable(true)
                            .create();
                    dialog.show();


                } catch (Exception e) {

                    e.printStackTrace();

                }
                  /*  }
                }).start();*/

//            result.confirm();      //不加上面的代码也能出框，为嘛呢？
                return true;
//            return super.onJsConfirm(view,url,message, result);
            }

        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("加载api到数据库");
        menu.setHeaderTitle("jfdjf");
        menu.setHeaderTitle("jfdjf");
        menu.setHeaderTitle("jfdjf");


        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static String jsdata() {
        return jsdata;
    }
}
