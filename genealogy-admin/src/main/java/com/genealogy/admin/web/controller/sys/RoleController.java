package com.genealogy.admin.web.controller.sys;

import com.genealogy.admin.web.controller.BaseController;
import com.genealogy.admin.web.model.RoleEntity;
import com.genealogy.admin.web.service.IRoleService;
import com.genealogy.admin.web.vo.RoleAddAndEditReqVo;
import com.genealogy.admin.web.vo.RoleReqVo;
import com.genealogy.common.annotation.ParamVailds;
import com.genealogy.common.response.RespCode;
import com.genealogy.common.response.RespHelper;
import com.genealogy.common.response.ResponseMessage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色管理控制层
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    private static final String PREFIX = "system/role";

    @Autowired
    private IRoleService roleService;

    /**
     * 角色管理入口
     *
     * @param model
     * @return
     */
    @GetMapping()
    @RequiresPermissions("sys:role:index")
    String index(Model model) {
        return PREFIX + "/index";
    }

    /**
     * 角色添加
     * @param model
     * @return
     */
    @GetMapping("/add")
    @RequiresPermissions("sys:role:add")
    String add(Model model) {
        return PREFIX + "/add";
    }

    /**
     * 角色编辑
     * @param model
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("sys:role:edit")
    String edit(Model model, @PathVariable("id") Integer roleId) {
        RoleEntity roleEntity = roleService.get(roleId);
        model.addAttribute("role", roleEntity);
        return PREFIX + "/edit";
    }

    /**
     * 角色管理分页查询
     * @param vo
     * @return
     */
    @PostMapping(value = "/page")
    @ResponseBody
    @RequiresPermissions("sys:role:index")
    public List<RoleEntity> page(RoleReqVo vo) {
        return roleService.queryAll();
    }

    /**
     * 添加角色信息
     * @param vo
     * @return
     */
    @ParamVailds
    @PostMapping(value = "/save")
    @ResponseBody
    @RequiresPermissions("sys:role:add")
    public ResponseMessage save(RoleAddAndEditReqVo vo) {
        System.out.println("保存角色信息:" + vo);
        roleService.save(vo);
        return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
    }

    /**
     * 更新角色信息
     * @param vo
     * @return
     */
    @ParamVailds
    @PostMapping(value = "/edit")
    @ResponseBody
    @RequiresPermissions("sys:role:edit")
    public ResponseMessage update(RoleAddAndEditReqVo vo) {
        if(roleService.update(vo)<=0){
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
    @RequiresPermissions("sys:role:remove")
    public ResponseMessage remove(@NotNull(message = "ID 不能为空") Integer id) {
        if(roleService.delete(id)<=0){
            return RespHelper.buildResponseMessage(RespCode.COMM_FAIL,null);
        }
        return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
    }
}
