package cn.jxufe.beans.enums;

public enum Errcode {
	
	// 系统通用代码
	A00000,			// 未知错误
	A00001,			// 系统错误
	A00002,			// 拒绝访问（接口鉴权失败）
	A00003,			// 参数错误
	A00004,			// 未登录
	A00005,			// 业务操作失败
	A00006,			// 业务操作成功
	A00007,			// 不支持错误
	A00008,			// 主键ID在数据库（KV数据库）中不存在
	A00009,			// 越权操作
	A00010,			// 已被锁定，拒绝操作
	A00011,			// 非法访问，拒绝服务
	A00012,			// 服务器过于繁忙
	A00013,			// 验证错误
	A00014,         //业务办理成功
	A00015,         // 和微币不足
	A00016,         // 商品库存不足
	A00017,         // 商品已下架

	A10001,			// TOKEN 异常
	A10002,			// TOKEN 过期
	A10003,			// TOKEN 超出权限
	A10005,			// TOKEN 无效
	A10006,			// TOKEN 验证成功
	
	
}