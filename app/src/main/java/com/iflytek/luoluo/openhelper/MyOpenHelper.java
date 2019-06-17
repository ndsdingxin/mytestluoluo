package  com.iflytek.luoluo.openhelper;

import android.R.integer;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, "luoluodata.db",null,1);
        //meikewuliao
        // TODO Auto-generated constructor stub
    }
    /*
     * private integer id;
        private String code;
        private String name;
        private String guige;
        private String position;
        private String beizhu;
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table api (id integer primary key autoincrement,api varchar(50),version varchar(50),remark varchar(50))");
        db.execSQL("create table appmsg (id integer primary key autoincrement,appid varchar(50),appsecret varchar(50),accesstoken varchar(50),taxtableid varchar(50),beizhu varchar(50))");
        db.execSQL("create table tokenmsg (id integer primary key autoincrement,taxnum varchar(50),taxtoken varchar(50),dizhi varchar(50),remark varchar(50))");
        db.execSQL("create table publicbody (id integer primary key autoincrement,method varchar(50),version varchar(50),timestamp varchar(50),beizhu varchar(50))");
        db.execSQL("create table publicheader (id integer primary key autoincrement,userTax varchar(50),compress varchar(50),appKey varchar(50),appRate varchar(50),dataType varchar(50),signMethod varchar(50),accessToken varchar(50),beizhu varchar(50))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        //db.execSQL("alter table info add phone varchar(50)");
        db.execSQL("create table api (id integer primary key autoincrement,api varchar(50),version varchar(50),remark varchar(50))");
        db.execSQL("create table appmsg (id integer primary key autoincrement,appid varchar(50),appsecret varchar(50),accesstoken varchar(50),taxtableid varchar(50),beizhu varchar(50))");
        db.execSQL("create table tokenmsg (id integer primary key autoincrement,taxnum varchar(50),taxtoken varchar(50),dizhi varchar(50),remark varchar(50))");
        db.execSQL("create table publicbody (id integer primary key autoincrement,method varchar(50),version varchar(50),timestamp varchar(50),beizhu varchar(50))");
        db.execSQL("create table publicheader (id integer primary key autoincrement,userTax varchar(50),compress varchar(50),appKey varchar(50),appRate varchar(50),dataType varchar(50),signMethod varchar(50),accessToken varchar(50),beizhu varchar(50))");
    }

}
