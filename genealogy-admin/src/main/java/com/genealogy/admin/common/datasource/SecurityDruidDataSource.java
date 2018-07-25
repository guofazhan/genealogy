package com.genealogy.admin.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.genealogy.admin.common.utils.RSAUtils;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SecurityDruidDataSource extends DruidDataSource {

	/**
	 * 解密后的密码
	 */
	private String decryptPwd;

	/**
	 * 返回解密后的密码
	 *
	 * @return
	 */
	@Override
	public String getPassword() {
		if (null == decryptPwd || "".equals(decryptPwd)) {
			decryptPwd = RSAUtils.decrypt(password);
		}
		return decryptPwd;
	}
}
