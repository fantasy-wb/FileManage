//package cn.jxufe.web.realms;
//
//import cn.jxufe.beans.model.User;
//import cn.jxufe.iservice.iservice.IUserService;
//import com.alibaba.dubbo.config.annotation.Reference;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.crypto.hash.SimpleHash;
//import org.apache.shiro.realm.AuthenticatingRealm;
//import org.apache.shiro.util.ByteSource;
//
//public class ShiroRealm extends AuthenticatingRealm {
//
//	@Reference
//	private IUserService userService;
//
//	/*
//	 *
//	 * @see 该方法的调用时机为UserController.login()方法中执行Subject.login()时
//	 */
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//		// TODO Auto-generated method stub
//
//		  UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
//		  String username = token.getUsername();
//		  User findUser = null;
//		  Object principal = null;
//		  if(username.contains("@")){
//			  findUser = userService.findUserByEmail(username);
//			  principal = findUser.getUserEmail();
//		  }else{
//			  findUser = userService.findUserByName(username);
//			  principal = findUser.getUserName();
//		  }
//
//
//		  if(null == findUser){
//			  throw new UnknownAccountException("该用户不存在");
//		  }else if(findUser.getValidateStatus() != 0){
//			  throw new UnknownAccountException("该账号未被有效的邮箱验证");
//		  }
//		  // 判断帐号是否锁定
//		  else if ( 1 == findUser.getLockStatus()) {
//				// 抛出 帐号锁定异常
//			  throw new LockedAccountException("错误登录次数超过5次,请10分钟后再试！");
//		  }
//		  /*
//		   * principal ：认证的实体信息
//		   * credentials : 从数据库获取的密码
//		   * realmName : 用户的角色，调用父类的getName()
//		   */
//		  //由shiro完成密码比对
//		  /*Object principal = findUser.getUserName();*/
//		  Object credentials = findUser.getPassword();
//		  System.out.println(credentials);
//		  String realmName = getName();
//		  //把用户名做盐值
//		  ByteSource credentialsSalt = ByteSource.Util.bytes(findUser.getUserName());
//
//		  SimpleAuthenticationInfo info  = null;
//		  info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
//
//		  return info;
//	}
//
//
//}
