package com.genealogy.web.controller.sys;

import com.genealogy.web.model.AreaEntity;
import com.genealogy.web.service.IAreaService;
import com.genealogy.common.aspect.annotation.ParamVailds;
import com.genealogy.common.message.ResponseMessage;
import com.genealogy.common.message.respcode.RespCode;
import com.genealogy.common.message.respcode.RespHelper;
import com.genealogy.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 地区管理控制层
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/sys/area")
public class AreaController extends BaseController {

    private static final String PREFIX = "system/area";

    @Autowired
    private IAreaService areaService;

    /**
     * 地区管理入口
     *
     * @param model
     * @return
     */
    @RequiresPermissions("sys:area:index")
    @GetMapping()
    String index(Model model) {
        return PREFIX + "/index";
    }

    /**
     * 地区添加
     * @param model
     * @param parentId
     * @return
     */
    @RequiresPermissions("sys:area:add")
    @GetMapping("/add/{pId}")
    String add(Model model, @PathVariable("pId") Integer parentId) {
        model.addAttribute("parentId", parentId);
        if (parentId == 0) {
            model.addAttribute("parentName", "根目录");
        } else {
            model.addAttribute("parentName",  areaService.get(parentId).getAreaName());
        }
        return PREFIX + "/add";
    }

    /**
     * 地区编辑
     * @param model
     * @param areaId
     * @return
     */
    @RequiresPermissions("sys:area:edit")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Integer areaId) {
        AreaEntity area = areaService.get(areaId);
        model.addAttribute("area", area);
        model.addAttribute("parentId", area.getParentId());
        if (area.getParentId() == 0) {
            model.addAttribute("parentName", "根目录");
        } else {
            model.addAttribute("parentName", areaService.get(area.getParentId()).getAreaName());
        }
        return PREFIX + "/edit";
    }


    /**
     * 查询地区列表
     * @param model
     * @return
     */
    @RequiresPermissions("sys:area:index")
    @PostMapping(value = "/list")
    @ResponseBody
    public List<AreaEntity> list(Model model) {
        return areaService.queryAll();
    }

    /**
     * 添加地区信息
     * @param entity
     * @return
     */
    @ParamVailds
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseMessage save(AreaEntity entity) {
        areaService.save(entity);
        return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
    }

    /**
     * 更新地区信息
     * @param entity
     * @return
     */
    @ParamVailds
    @RequiresPermissions("sys:area:edit")
    @PostMapping(value = "/edit")
    @ResponseBody
    public ResponseMessage update(AreaEntity entity) {
        if(areaService.update(entity)<=0){
            return RespHelper.buildResponseMessage(RespCode.COMM_FAIL,null);
        }
        return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
    }

    /**
     * 删除ID
     * @param id
     * @return
     */
    @RequiresPermissions("sys:area:remove")
    @PostMapping(value = "/remove")
    @ResponseBody
    public ResponseMessage remove(@NotNull(message = "ID 不能为空") Integer id) {
        if(areaService.delete(id)<=0){
            return RespHelper.buildResponseMessage(RespCode.COMM_FAIL,null);
        }
        return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
    }
}
