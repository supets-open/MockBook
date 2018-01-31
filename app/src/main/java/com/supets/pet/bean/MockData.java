package com.supets.pet.bean;

import java.util.Date;

public class MockData {

    private Long id;
    private String url;
    private String requestParam;
    private String data;
    private Date time;

    public MockData(Long id, String url, String requestParam, String data,
                    Date time) {
        this.id = id;
        this.url = url;
        this.requestParam = requestParam;
        this.data = data;
        this.time = time;
    }

    public MockData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRequestParam() {
        return this.requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

}
