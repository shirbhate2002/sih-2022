package com.vaidilya.sih;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result_list_class {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("result")
    @Expose
    private List<Result_class> result = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Result_class> getResult() {
        return result;
    }

    public void setResult(List<Result_class> result) {
        this.result = result;
    }

}
