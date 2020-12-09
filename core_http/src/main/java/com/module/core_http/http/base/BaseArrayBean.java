package com.module.core_http.http.base;


import java.util.List;

/**
 * Description：数组
 *@author yy
 @DATE 2019/8/8
 @VERSION 6.3.0
 */

public class BaseArrayBean<T> {

    /**
     * code : 1
     * msg : 获取成功
     * data : {} 对象
     */


    private int code;
    private String msg;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}