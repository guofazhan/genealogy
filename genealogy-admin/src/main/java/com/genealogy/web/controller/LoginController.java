package com.genealogy.web.controller;

import com.genealogy.common.aspect.annotation.Log;
import com.genealogy.web.vo.LoginReqVo;
import com.genealogy.common.aspect.annotation.ParamVailds;
import com.genealogy.common.message.ResponseMessage;
import com.genealogy.common.message.respcode.RespCode;
import com.genealogy.common.message.respcode.RespHelper;
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

	@ParamVailds
	@ResponseBody
	@Log("登录")
	@PostMapping(value = "/signin")
	public ResponseMessage signIn(LoginReqVo reqVo) {
		//用户登录鉴权
		SecurityUtils.getSubject()
				.login(new UsernamePasswordToken(reqVo.getLoginName(),
						reqVo.getPassword()));
		return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
	}
}
