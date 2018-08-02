package com.genealogy.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 用户密码工具类
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PasswordUtils {

	private static final String SALT = "1qazxsw2";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd,
				ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String loginName, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd,
				ByteSource.Util.bytes(loginName + SALT), HASH_ITERATIONS)
				.toHex();
		return newPassword;
	}

	public static void main(String[] args) {
		System.out.println(PasswordUtils.encrypt("guofazhan", "12345678"));
		System.out.println(PasswordUtils.encrypt("1"));
	}
}
