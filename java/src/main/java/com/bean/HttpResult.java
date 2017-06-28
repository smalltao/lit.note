package com.bean;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;

@XmlRootElement(name = "HttpResult")
public class HttpResult {

    private String code;

    private String msg;

    private Object obj;

    private ArrayList<Object> list;

    private HashMap<Object, Object> map;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }

    public HashMap<Object, Object> getMap() {
        return map;
    }

    public void setMap(HashMap<Object, Object> map) {
        this.map = map;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
