//package cn.jxufe.beans.result;
//
//
//import cn.jxufe.beans.enums.Errcode;
//
///**
// * 普通返回结果()
// */
//public class BaseResult0 {
//    private Integer code;
//    private String msg;
//    private Object data;
//
//    public static BaseResult buildSuccess() {
//        BaseResult result = new BaseResult();
//        result.code = 0;
//        return result;
//    }
//
//    public static BaseResult buildSuccess(Object data) {
//        BaseResult result = new BaseResult();
//        result.code = 0;
//        result.setData(data);
//        return result;
//    }
//    public static BaseResult buildFail(Object data) {
//        BaseResult result = new BaseResult();
//        result.code = 0;
//        result.setData(data);
//        return result;
//    }
//
//    public BaseResult buildParamError() {
//        this.code = -1;
//        this.msg = "参数错误";
//        return this;
//    }
//
//    public BaseResult build(Errcode errcode, String msg) {
//        this.code = -1;
//        this.msg = msg;
//        return this;
//    }
//
//    public BaseResult() {
//    }
//
//    public BaseResult(Integer code, String errmsg) {
//        this.code = code;
//        this.msg = errmsg;
//    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getErrmsg() {
//        return msg;
//    }
//
//    public void setErrmsg(String errmsg) {
//        this.msg = errmsg;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//}
