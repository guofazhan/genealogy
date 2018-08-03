package com.genealogy.web.common.shiro;

import com.genealogy.web.model.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ShiroHelper {

	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}

	public static UserEntity getUser() {
		Object object = getSubjct().getPrincipal();
		return (UserEntity) object;
	}

	public static void logout() {
		getSubjct().logout();
	}
}
