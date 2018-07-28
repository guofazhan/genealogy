package com.genealogy.admin.web.controller.sys;

import com.genealogy.admin.web.controller.BaseController;
import com.genealogy.common.annotation.ParamVailds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 地区管理控制层
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@ParamVailds
@Controller
@RequestMapping("/sys/area")
public class AreaController extends BaseController {

    private static final String PREFIX = "system/area";

    /**
     * 地区管理入口
     *
     * @param model
     * @return
     */
    @GetMapping()
    String index(Model model) {
        return PREFIX + "/index";
    }
}