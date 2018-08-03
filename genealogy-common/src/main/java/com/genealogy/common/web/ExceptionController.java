package com.genealogy.common.web;

import com.genealogy.common.exception.BaseException;
import com.genealogy.common.exception.ValidException;
import com.genealogy.common.message.respcode.RespCode;
import com.genealogy.common.message.respcode.RespHelper;
import com.genealogy.common.message.ResponseMessage;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guofazhan
 * @version [版本号, 2018/8/2 0002 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
public class ExceptionController implements ErrorController {
	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	private static final String ERROR_PATH = "/error";

	private static final String PREFIX = "error";

	@Autowired
	ErrorAttributes errorAttributes;

	/**
	 * 错误页面返回
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { ERROR_PATH }, produces = { "text/html" })
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		logger.error("errorHtml", getError(request));
		int code = response.getStatus();
		String viewName;
		switch (code) {
		case 404:
			viewName = PREFIX + "/404";
			break;
		case 403:
			viewName = PREFIX + "/403";
			break;
		default:
			viewName = PREFIX + "/500";
			break;
		}

		return new ModelAndView(viewName);
	}

	/**
	 * json 错误返回
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public ResponseEntity<ResponseMessage> error(HttpServletRequest request) {
		return new ResponseEntity(buildErrorResponseBody(getError(request)),
				HttpStatus.OK);
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	/**
	 * 根据异常构建错误消息
	 *
	 * @param throwable
	 * @return
	 */
	protected ResponseMessage buildErrorResponseBody(Throwable throwable) {
		//根据异常构建错误消息
		ResponseMessage resMessage = RespHelper
				.buildResponseMessage(RespCode.COMM_ERROR, null);
		System.out.println("异常信息:" + throwable);
		if (throwable instanceof BaseException
				|| throwable instanceof ValidException) {
			resMessage = RespHelper
					.buildResponseMessage((BaseException) throwable);
		} else if (throwable instanceof UnknownAccountException) {
			resMessage = RespHelper
					.buildResponseMessage(RespCode.ACCOUNT_ERROR, null);
		} else if (throwable instanceof LockedAccountException) {
			resMessage = RespHelper
					.buildResponseMessage(RespCode.ACCOUNT_LOCK, null);
		}

		return resMessage;
	}

	/**
	 * 获取异常信息
	 *
	 * @param request
	 * @return
	 */
	protected Throwable getError(HttpServletRequest request) {
		return errorAttributes.getError(new ServletRequestAttributes(request));
	}

}
