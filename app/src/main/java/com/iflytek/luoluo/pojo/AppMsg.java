package com.iflytek.luoluo.pojo;

public class AppMsg {

    /*
    id integer primary key autoincrement,
    appid varchar(50),
    appsecret varchar(50),
    accesstoken varchar(50),
    taxtableid varchar(50),
    beizhu varchar(50))"
     */

    private  String id;
    private  String appid;
    private  String appsecret;
    private  String accesstoken;
    private  String taxtableid;
    private  String beizhu;

    public String getAppsecret() {
        return appsecret;
    }

    public String getId() {
        return id;
    }

    public String getAppid() {
        return appid;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public String getTaxtableid() {
        return taxtableid;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public void setTaxtableid(String taxtableid) {
        this.taxtableid = taxtableid;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public AppMsg(String id, String appid, String appsecret, String accesstoken, String taxtableid, String beizhu) {
        this.id = id;
        this.appid = appid;
        this.appsecret = appsecret;
        this.accesstoken = accesstoken;
        this.taxtableid = taxtableid;
        this.beizhu = beizhu;
    }

    public AppMsg(String appid, String appsecret, String accesstoken, String taxtableid, String beizhu) {
        this.appid = appid;
        this.appsecret = appsecret;
        this.accesstoken = accesstoken;
        this.taxtableid = taxtableid;
        this.beizhu = beizhu;
    }

    public AppMsg() {
    }

    @Override
    public String toString() {
        return "AppMsg{" +
                "id='" + id + '\'' +
                ", appid='" + appid + '\'' +
                ", appsecret='" + appsecret + '\'' +
                ", accesstoken='" + accesstoken + '\'' +
                ", taxtableid='" + taxtableid + '\'' +
                ", beizhu='" + beizhu + '\'' +
                '}';
    }
}
