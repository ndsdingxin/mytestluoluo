package com.iflytek.luoluo.pojo;

public class Api {

    private String id;
    private String api;
    private String version;
    private String remark;


    public String getId() {
        return id;
    }

    public String getApi() {
        return api;
    }

    public String getVersion() {
        return version;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Api(String id, String api, String version, String remark) {
        this.id = id;
        this.api = api;
        this.version = version;
        this.remark = remark;
    }

    public Api(String api, String version, String remark) {
        this.api = api;
        this.version = version;
        this.remark = remark;
    }

    public Api() {
    }

    @Override
    public String toString() {
        return "Api{" +
                "id='" + id + '\'' +
                ", api='" + api + '\'' +
                ", version='" + version + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
