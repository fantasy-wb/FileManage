package cn.jxufe.utils.handler;


import cn.jxufe.beans.exception.FileDownloadException;
import cn.jxufe.beans.exception.LimitAccessException;
import cn.jxufe.beans.result.BaseResult;
import cn.jxufe.utils.HttpUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ExceptionHandler(value = AuthorizationException.class)
	public Object handleAuthorizationException(HttpServletRequest request) {
		if (HttpUtils.isAjaxRequest(request)) {
			return BaseResult.buildFail("暂无权限，请联系管理员！");
		} else {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("error/403");
			return mav;
		}
	}

	@ExceptionHandler(value = ExpiredSessionException.class)
	public String handleExpiredSessionException() {
		return "login";
	}

	@ExceptionHandler(value = LimitAccessException.class)
	public BaseResult handleLimitAccessException(LimitAccessException e) {
		return BaseResult.buildFail(e.getMessage());
	}

	@ExceptionHandler(value = FileDownloadException.class)
	public BaseResult handleFileDownloadException(FileDownloadException e) {
		return BaseResult.buildFail(e.getMessage());
	}
}
