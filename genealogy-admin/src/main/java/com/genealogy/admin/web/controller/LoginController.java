package com.genealogy.admin.web.controller;

import com.genealogy.admin.web.vo.LoginReqVo;
import com.genealogy.common.annotation.ParamVailds;
import com.genealogy.common.response.RespCode;
import com.genealogy.common.response.RespHelper;
import com.genealogy.common.response.ResponseMessage;
import com.genealogy.common.utils.PasswordUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录控制层
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@ParamVailds
@Controller
public class LoginController extends BaseController {

	/**
	 * 日志
	 */
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	/**
	 * 进入登录页面
	 *
	 * @return
	 */
	@GetMapping("/login")
	public String index() {
		return "login";
	}

	@PostMapping(value = "/signin")
	public @ResponseBody
	ResponseMessage signIn(LoginReqVo reqVo) {
		//加密后密码
		String encryptPassword = PasswordUtils
				.encrypt(reqVo.getLoginName(), reqVo.getPassword());

		//用户登录鉴权
		SecurityUtils.getSubject()
				.login(new UsernamePasswordToken(reqVo.getLoginName(),
						encryptPassword));

		return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
	}
}
