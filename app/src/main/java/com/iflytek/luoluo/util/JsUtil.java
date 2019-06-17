package com.iflytek.luoluo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.iflytek.luoluo.MainActivity;
import com.iflytek.luoluo.myactivity.ApiPutActivity;

public class JsUtil {

    public static void dpput(String str, Activity activity){
        if (str.equals("dpput")){
            Intent intent = new Intent(activity, ApiPutActivity.class);
            activity.startActivity(intent);
        }
    }
    public static void dbappkeyput(String str, Activity activity){
        if (str.equals("dbappkeyput")){
            Intent intent = new Intent(activity, ApiPutActivity.class);
            intent.putExtra("msg", str);
            activity.startActivity(intent);
        }
    }
    public static void dbusertaxput(String str, Activity activity){
        if (str.equals("dbusertaxput")){
            Intent intent = new Intent(activity, ApiPutActivity.class);
            intent.putExtra("msg", str);
            activity.startActivity(intent);
        }
    }
    public static void apidel(String str){
        if (str.indexOf("apidel")>=0){
            String id = str.substring(str.lastIndexOf("==")+2);
            DataBaseUtil.apidel(id);
        }
    }
    public static void apiupd(final String str, final Activity activity){
        if (str.indexOf("apiupd")>=0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String id = str.substring(str.lastIndexOf("==")+2);
                    Intent intent = new Intent((Context)activity, MainActivity.class);
                    intent.putExtra("msg",str);
                    activity.startActivity(intent);
                  //  activity.finish();
                }
            }).start();

        }
    }
    public static void apiuse(final String str, final Activity activity){
        if (str.indexOf("apiuse")>=0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String id = str.substring(str.lastIndexOf("==")+2);
                    Intent intent = new Intent((Context)activity, MainActivity.class);
                    intent.putExtra("msg",str);
                    activity.startActivity(intent);
                  //  activity.finish();
                }
            }).start();

        }
    }
    public static void publicHeadinput(final String str, final Activity activity){
        if (str.indexOf("publicHeadinput")>=0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String id = str.substring(str.lastIndexOf("==")+2);
                    Intent intent = new Intent((Context)activity, MainActivity.class);
                    intent.putExtra("msg",str);
                    activity.startActivity(intent);
                  //  activity.finish();
                }
            }).start();

        }
    }
    public static void pulicBodyinput(final String str, final Activity activity){
        if (str.indexOf("pulicBodyinput")>=0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String id = str.substring(str.lastIndexOf("==")+2);
                    Intent intent = new Intent((Context)activity, MainActivity.class);
                    intent.putExtra("msg",str);
                    activity.startActivity(intent);
                  //  activity.finish();
                }
            }).start();

        }
    }

}
