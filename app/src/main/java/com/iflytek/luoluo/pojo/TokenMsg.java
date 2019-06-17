package com.iflytek.luoluo.pojo;

public class TokenMsg {
    /*
    tokenmsg (id integer primary key autoincrement,
    taxnum varchar(50),
    taxtoken varchar(50),
    dizhi varchar(50),
    remark varchar(50))
     */

    private  String id;
    private  String taxnum;
    private  String taxtoken;
    private  String dizhi;
    private  String remark;

    public String getTaxnum() {
        return taxnum;
    }

    public String getId() {
        return id;
    }

    public String getTaxtoken() {
        return taxtoken;
    }

    public String getDizhi() {
        return dizhi;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTaxnum(String taxnum) {
        this.taxnum = taxnum;
    }

    public void setTaxtoken(String taxtoken) {
        this.taxtoken = taxtoken;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public TokenMsg(String id, String taxnum, String taxtoken, String dizhi, String remark) {
        this.id = id;
        this.taxnum = taxnum;
        this.taxtoken = taxtoken;
        this.dizhi = dizhi;
        this.remark = remark;
    }

    public TokenMsg(String taxnum, String taxtoken, String dizhi, String remark) {
        this.taxnum = taxnum;
        this.taxtoken = taxtoken;
        this.dizhi = dizhi;
        this.remark = remark;
    }

    public TokenMsg() {
    }

    @Override
    public String toString() {
        return "TokenMsg{" +
                "id='" + id + '\'' +
                ", taxnum='" + taxnum + '\'' +
                ", taxtoken='" + taxtoken + '\'' +
                ", dizhi='" + dizhi + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
