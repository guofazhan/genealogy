package com.genealogy.web.controller.report;

import com.genealogy.common.Page;
import com.genealogy.web.controller.BaseController;
import com.genealogy.web.model.OperationRecordEntity;
import com.genealogy.web.service.IOperationRecordService;
import com.genealogy.web.vo.OperationRecordReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author G2Y
 * @version [版本号, 2018/7/29 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/report/log")
public class LogController extends BaseController {


    private static final String PREFIX = "report/log";

    /**
     * 操作记录服务
     */
    @Autowired
    private IOperationRecordService operationRecordService;

    @GetMapping()
    String index(Model model) {
        return PREFIX + "/index";
    }

    /**
     * 操作记录分页查询
     * @param vo
     * @return
     */
    @PostMapping(value = "/page")
    @ResponseBody
    public Page<OperationRecordEntity> page(@RequestBody OperationRecordReqVo vo) {
        return operationRecordService.page(vo);
    }
}
