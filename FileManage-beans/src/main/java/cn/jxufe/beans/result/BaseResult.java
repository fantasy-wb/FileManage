package cn.jxufe.beans.result;


import cn.jxufe.beans.enums.Errcode;

/**
 * 普通返回结果
 */
public class BaseResult {
    private String code;
    private String errmsg;
    private Object data;

    public static BaseResult buildSuccess() {
        BaseResult result = new BaseResult();
        result.code = Errcode.A00006.toString();
        return result;
    }

    public static BaseResult buildSuccess(Object data) {
        BaseResult result = new BaseResult();
        result.code = Errcode.A00006.toString();
        result.setData(data);
        return result;
    }
    public static BaseResult buildFail(Object data) {
        BaseResult result = new BaseResult();
        result.code = Errcode.A00005.toString();
        result.setData(data);
        return result;
    }

    public BaseResult buildParamError() {
        this.code = Errcode.A00003.toString();
        this.errmsg = "参数错误";
        return this;
    }

    public BaseResult build(Errcode errcode, String errmsg) {
        this.code = errcode.toString();
        this.errmsg = errmsg;
        return this;
    }

    public BaseResult() {
    }

    public BaseResult(String code, String errmsg) {
        this.code = code;
        this.errmsg = errmsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
