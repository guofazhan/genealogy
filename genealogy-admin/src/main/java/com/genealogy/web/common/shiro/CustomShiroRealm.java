package com.genealogy.web.common.shiro;

import com.genealogy.web.model.UserEntity;
import com.genealogy.web.service.IMenuService;
import com.genealogy.web.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CustomShiroRealm extends AuthorizingRealm {

	/**
	 * 日志
	 */
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(CustomShiroRealm.class);

	/**
	 * 用户服务
	 */
	@Autowired
	private IUserService userService;

	/**
	 * 菜单（权限）服务
	 */
	@Autowired
	private IMenuService menuService;

	/**
	 * 获取授权信息
	 *
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {
		Set<String> codes = menuService
				.queryCodesByUserId(ShiroHelper.getUser().getUserId());
		//去除空值
		codes = codes.stream().filter((v) -> !(null == v || "".equals(v.trim()))).collect(Collectors.toSet());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(codes);
		logger.info("Query User：{} has codes：{}",
				ShiroHelper.getUser().getUserId(), codes);
		return info;
	}

	/**
	 * 获取身份验证信息
	 * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
	 *
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		String loginName = (String) authenticationToken.getPrincipal();
		String password = new String(
				(char[]) authenticationToken.getCredentials());
		UserEntity user = userService.login(loginName, password);
		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		// 账号锁定
		if (user.getIsLock() == 1) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		//返回登录认证信息
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
				password, getName());
		return info;
	}
}
