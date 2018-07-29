package com.genealogy.admin.web.controller.family;

import com.genealogy.admin.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 户口管理控制层
 * @author G2Y
 * @version [版本号, 2018/7/29 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/family/rr")
public class RegisteredResidenceController extends BaseController {

    private static final String PREFIX = "family/rr";

    @GetMapping()
    String index(Model model) {
        return PREFIX + "/index";
    }
}
