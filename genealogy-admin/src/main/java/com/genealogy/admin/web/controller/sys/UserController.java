package com.genealogy.admin.web.controller.sys;

import com.genealogy.admin.web.controller.BaseController;
import com.genealogy.admin.web.model.UserEntity;
import com.genealogy.admin.web.service.IUserService;
import com.genealogy.admin.web.vo.UserReqVo;
import com.genealogy.common.Page;
import com.genealogy.common.annotation.ParamVailds;
import com.genealogy.common.response.RespCode;
import com.genealogy.common.response.RespHelper;
import com.genealogy.common.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 用户管理控制层
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {

	private static final String PREFIX = "system/user";


	@Autowired
	private IUserService userService;

	/**
	 * 用户管理入口
	 *
	 * @param model
	 * @return
	 */
	@GetMapping()
	String index(Model model) {
		return PREFIX + "/index";
	}

	/**
	 * y用户添加
	 * @param model
	 * @return
	 */
	@GetMapping("/add")
	String add(Model model) {
		return PREFIX + "/add";
	}

	/**
	 * 用户编辑
	 * @param model
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Integer userId) {
		UserEntity userEntity = userService.get(userId);
		model.addAttribute("user", userEntity);
		return PREFIX + "/edit";
	}


	/**
	 * 用户管理分页查询
	 * @param vo
	 * @return
	 */
	@PostMapping(value = "/page")
	@ResponseBody
	public Page<UserEntity> page(@RequestBody UserReqVo vo) {
		return userService.page(vo);
	}

	/**
	 * 校验登录用户名是否存在
	 * @param loginName
	 * @return
	 */
	@PostMapping("/exit")
	@ResponseBody
	boolean exit(String loginName) {
		UserEntity userEntity = userService.queryUserByName(loginName);
		return userEntity == null;
	}

	/**
	 * 添加用户信息
	 * @param entity
	 * @return
	 */
	@ParamVailds
	@PostMapping(value = "/save")
	@ResponseBody
	public ResponseMessage save(UserEntity entity) {
		System.out.println("保存用户信息:" + entity);
		userService.save(entity);
		return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
	}

	/**
	 * 更新用户信息
	 * @param entity
	 * @return
	 */
	@ParamVailds
	@PostMapping(value = "/edit")
	@ResponseBody
	public ResponseMessage update(UserEntity entity) {
		if(userService.update(entity)<=0){
			return RespHelper.buildResponseMessage(RespCode.COMM_FAIL,null);
		}
		return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
	}


	/**
	 * 删除ID
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/remove")
	@ResponseBody
	public ResponseMessage remove(@NotNull(message = "ID 不能为空") Integer id) {
		if(userService.delete(id)<=0){
			return RespHelper.buildResponseMessage(RespCode.COMM_FAIL,null);
		}
		return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
	}

}
