package com.jxxx.zf.bean;

import com.blankj.utilcode.util.ToastUtils;


public class Result<T>  {


    private int status;
    private String error;
    private String message;
    private boolean success;
    private T data;

    public int getStatus() {
        if(status==0 || !isSuccess()){
            ToastUtils.showLong(message);
        }
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
