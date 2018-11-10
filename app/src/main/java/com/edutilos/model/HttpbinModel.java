package com.edutilos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class HttpbinModel implements Serializable {
    @SerializedName("args")
    private Map<String,String> args;
    @SerializedName("headers")
    private Map<String,String> headers;
    @SerializedName("origin")
    private String origin;
    @SerializedName("url")
    private String url;


    public HttpbinModel(Map<String, String> args, Map<String, String> headers, String origin, String url) {
        this.args = args;
        this.headers = headers;
        this.origin = origin;
        this.url = url;
    }

    public HttpbinModel(String origin, String url) {
        this.origin = origin;
        this.url = url;
    }

    public HttpbinModel() {
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


    @Override
    public String toString() {
        return "HttpbinModel{" +
                "args=" + args +
                ", headers=" + headers +
                ", origin='" + origin + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
