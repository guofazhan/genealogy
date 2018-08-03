package com.genealogy.admin.web.controller.sys;

import com.genealogy.admin.web.controller.BaseController;
import com.genealogy.admin.web.model.PositionEntity;
import com.genealogy.admin.web.service.IPositionService;
import com.genealogy.admin.web.vo.PositionReqVo;

import com.genealogy.common.aspect.annotation.ParamVailds;
import com.genealogy.common.message.ResponseMessage;
import com.genealogy.common.message.respcode.RespCode;
import com.genealogy.common.message.respcode.RespHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 职位管理控制层
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/sys/position")
public class PositionController extends BaseController {

    private static final String PREFIX = "system/position";

    @Autowired
    private IPositionService positionService;

    /**
     * 职位管理入口
     *
     * @param model
     * @return
     */
    @RequiresPermissions("sys:position:index")
    @GetMapping()
    String index(Model model) {
        return PREFIX + "/index";
    }

    /**
     * 职位添加
     * @param model
     * @return
     */
    @RequiresPermissions("sys:position:add")
    @GetMapping("/add")
    String add(Model model) {
        return PREFIX + "/add";
    }

    /**
     * 职位编辑
     * @param model
     * @param positionId
     * @return
     */
    @RequiresPermissions("sys:position:edit")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Integer positionId) {
        PositionEntity positionEntity = positionService.get(positionId);
        model.addAttribute("position", positionEntity);
        return PREFIX + "/edit";
    }

    /**
     * 职位管理分页查询
     * @param vo
     * @return
     */
    @RequiresPermissions("sys:position:index")
    @PostMapping(value = "/page")
    @ResponseBody
    public List<PositionEntity> page(PositionReqVo vo) {
        return positionService.queryAll();
    }

    /**
     * 添加职位信息
     * @param entity
     * @return
     */
    @ParamVailds
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:position:save")
    @ResponseBody
    public ResponseMessage save(PositionEntity entity) {
        System.out.println("保存职位信息:" + entity);
        positionService.save(entity);
        return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
    }

    /**
     * 更新职位信息
     * @param entity
     * @return
     */
    @ParamVailds
    @PostMapping(value = "/edit")
    @ResponseBody
    @RequiresPermissions("sys:position:edit")
    public ResponseMessage update(PositionEntity entity) {
        if(positionService.update(entity)<=0){
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
    @RequiresPermissions("sys:position:remove")
    public ResponseMessage remove(@NotNull(message = "ID 不能为空") Integer id) {
        if(positionService.delete(id)<=0){
            return RespHelper.buildResponseMessage(RespCode.COMM_FAIL,null);
        }
        return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
    }
}
