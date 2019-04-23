package cn.jxufe.beans.result;

import java.util.HashMap;

public class BaseResult extends HashMap<String, Object> {

	private static final long serialVersionUID = -8713837118340960775L;

	// 成功
	private static final Integer SUCCESS = 0;
	// 警告
	private static final Integer WARN = 1;
	// 异常 失败
	private static final Integer FAIL = 500;

	public BaseResult() {
		put("code", SUCCESS);
		put("msg", "操作成功");
	}

	public static BaseResult buildFail(Object msg) {
		BaseResult responseBo = new BaseResult();
		responseBo.put("code", FAIL);
		responseBo.put("msg", msg);
		return responseBo;
	}

	public static BaseResult warn(Object msg) {
		BaseResult responseBo = new BaseResult();
		responseBo.put("code", WARN);
		responseBo.put("msg", msg);
		return responseBo;
	}

	public static BaseResult buildSuccess(Object msg) {
		BaseResult responseBo = new BaseResult();
		responseBo.put("code", SUCCESS);
		responseBo.put("msg", msg);
		return responseBo;
	}

	public static BaseResult buildSuccess() {
		return new BaseResult();
	}

	public static BaseResult buildFail() {
		return BaseResult.buildFail("");
	}

	@Override
	public BaseResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
