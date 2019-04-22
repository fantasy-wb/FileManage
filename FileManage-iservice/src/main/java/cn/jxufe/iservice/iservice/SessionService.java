package cn.jxufe.iservice.iservice;

import cn.jxufe.beans.model.UserOnline;

import java.util.List;

public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}
