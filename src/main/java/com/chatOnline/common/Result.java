package com.chatOnline.common;

import java.io.Serializable;
//结果返回类
public class Result implements Serializable {
    private Integer status;//1成功 其他表示失败
    private String msg;//失败信息
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(Integer code, Object data) {
        this.status = code;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.status = code;
        this.msg = msg;
    }

    public static Result succeed(Object data){
        return new Result(200,data);
    }
    public static Result Error(Integer code,String msg){
        return new Result(code,msg);
    }

}
