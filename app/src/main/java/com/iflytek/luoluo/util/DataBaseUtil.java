package com.iflytek.luoluo.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.iflytek.luoluo.MainActivity;
import com.iflytek.luoluo.openhelper.MyOpenHelper;
import com.iflytek.luoluo.pojo.Api;
import com.iflytek.luoluo.pojo.AppMsg;
import com.iflytek.luoluo.pojo.TokenMsg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUtil {
    private static String path = "/data/data/com.iflytek.mytestluoluo/databases/luoluodata.db";
    private static MyOpenHelper openHelper;
    private static SQLiteDatabase sql;
    private static Cursor cursor;
    private static Context context;

    public DataBaseUtil(Context context) {
        openHelper = new MyOpenHelper(context);
        sql = openHelper.getReadableDatabase();
        this.context = context;
    }

    public static MyOpenHelper getOpenHelper(Context context) {
        return openHelper;
    }

    private static boolean isdbexit() {
        File f = new File(path);
        if (f.exists()) {
            return true;
        } else {
            return false;
        }
    }


    public static List<Api> findApiData() {
        if (!isdbexit()) {
            return null;
        }
        ;

        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        cursor = sql.rawQuery("select * from api", null);
        StringBuffer sb = new StringBuffer();
        if (cursor != null && cursor.getCount() > 0) {
            List<Api> list = new ArrayList<Api>();
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String api = cursor.getString(1);
                String version = cursor.getString(2);
                String remark = cursor.getString(3);
                Api api1 = new Api(id, api, version, remark);
                list.add(api1);
                sb.append(id + "=====" + api + "=====" + version + "=====" + remark);
            }
            return list;
        } else {
            return null;
        }
    }

    public static void putAppkeyData(List<Object> list) {
        if (!isdbexit()) {
            return;
        }
        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        for (int i = 0; i < list.size(); i++) {
            AppMsg appMsg = (AppMsg)list.get(i);
            try {
                sql.execSQL("insert into appmsg(api,version,remark) values (?,?,?) ", new Object[]{appMsg.getAppid(),appMsg.getAppsecret(),appMsg.getAccesstoken(),appMsg.getTaxtableid(),appMsg.getBeizhu()});

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
    public static void putUsertaxData(List<Object> list) {
        if (!isdbexit()) {
            return;
        }
        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        for (int i = 0; i < list.size(); i++) {
            TokenMsg tokenMsg = (TokenMsg)list.get(i);
            try {
                sql.execSQL("insert into tokenmsg(api,version,remark) values (?,?,?) ", new Object[]{tokenMsg.getTaxnum(),tokenMsg.getTaxtoken(),tokenMsg.getDizhi(),tokenMsg.getRemark()});

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
    public static void putApiData(List<Object> list) {
        if (!isdbexit()) {
            return;
        }
        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        for (int i = 0; i < list.size(); i++) {
            Api api = (Api)list.get(i);
            try {
                sql.execSQL("insert into api(api,version,remark) values (?,?,?) ", new Object[]{api.getApi(), api.getVersion(), api.getRemark()});

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static void dbapidel() {
        if (!isdbexit()) {
            return;
        }
        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        sql.execSQL("drop table api");
        sql.execSQL("create table api (id integer primary key autoincrement,api varchar(50),version varchar(50),remark varchar(50))");
    }

    public static List<Api> apibyid(String str) {
        List<Api> list = new ArrayList<Api>();
        try {

            if (!isdbexit()) {
                return null;
            }
            ;

            if (sql == null) {
                sql = openHelper.getReadableDatabase();
            }
            String id1 = str.substring(str.indexOf("==") + 2);
            cursor = sql.rawQuery("select * from api where id=?", new String[]{id1});
            StringBuffer sb = new StringBuffer();
            Api api1 = null;
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    String api = cursor.getString(1);
                    String version = cursor.getString(2);
                    String remark = cursor.getString(3);
                    api1 = new Api(id, api, version, remark);
                    sb.append(id + "=====" + api + "=====" + version + "=====" + remark);
                    list.add(api1);
                }
                return list;
            } else {

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public static void apidel(String id) {
        if (!isdbexit()) {
            return;
        }
        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        try {
            sql.execSQL("delete from api where id=?  ", new Object[]{id});

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public static void updaip(List<String[][]> list) {
        if (!isdbexit()) {
            return;
        }
        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        try {
            sql.execSQL("update  api set api=?,version=? where id=?  ", new Object[]{list.get(2)[0][1], list.get(2)[1][1], list.get(0)[0][1]});

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public static void apiupdinput(String str) {
        if (!isdbexit()) {
            return;
        }
        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        Gson gson = new Gson();
        String obj = str.substring(str.lastIndexOf("==") + 2);

        String jsonstr = obj.replaceAll("'", "\"");
        jsonstr = jsonstr.substring(1);
        jsonstr = jsonstr.substring(0, jsonstr.length() - 1);
        System.out.println(jsonstr);
        Api api = gson.fromJson(jsonstr, Api.class);
        try {
            sql.execSQL("update api set api=?, version=?, remark=? where id=? ", new Object[]{api.getApi(), api.getVersion(), api.getRemark(), api.getId()});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static List<AppMsg> findappmsg() {
        if (!isdbexit()) {
            return null;
        }
        ;

        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        cursor = sql.rawQuery("select * from appmsg", null);
        StringBuffer sb = new StringBuffer();
        if (cursor != null && cursor.getCount() > 0) {
            List<AppMsg> list = new ArrayList<AppMsg>();
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String appid = cursor.getString(1);
                String accesstoken = cursor.getString(2);
                String taxtableid = cursor.getString(3);
                String beizhu = cursor.getString(4);
                AppMsg appMsg = new AppMsg(id, appid, accesstoken, taxtableid, beizhu);
                list.add(appMsg);
                sb.append(id + "=====" + appid + "=====" + accesstoken+ "=====" + taxtableid+ "=====" + beizhu);
            }
            return list;
        } else {
            return null;
        }
    }
    public static List<TokenMsg> findtokenmsgbyid(String fid) {
        if (!isdbexit()) {
            return null;
        }
        ;

        if (sql == null) {
            sql = openHelper.getReadableDatabase();
        }
        cursor = sql.rawQuery("select * from tokenmsg where id=?", new String[]{fid});
        StringBuffer sb = new StringBuffer();
        if (cursor != null && cursor.getCount() > 0) {

            /*
              private  String id;
    private  String taxnum;
    private  String taxtoken;
    private  String dizhi;
    private  String remark;

             */
            List<TokenMsg> list = new ArrayList<TokenMsg>();
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String taxnum = cursor.getString(1);
                String taxtoken = cursor.getString(2);
                String dizhi = cursor.getString(3);
                String remark = cursor.getString(4);
                TokenMsg tokenMsg = new TokenMsg(id, taxnum, taxtoken, dizhi, remark);
                list.add(tokenMsg);
                sb.append(id + "=====" + taxnum + "=====" + taxtoken+ "=====" + dizhi+ "=====" + remark);
            }
            return list;
        } else {
            return null;
        }
    }

}