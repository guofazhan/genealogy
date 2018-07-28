package com.genealogy.admin.web.model;

import com.genealogy.admin.web.shiro.ShiroHelper;
import com.genealogy.common.utils.DateUtil;

/**
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EntityHelper {

    /**
     * 补全实体信息
     * @param entity
     * @param <T>
     */
    public static  <T extends BaseEntity> void compleSaveEntity(T entity) {
        entity.setIsDel(0);
        entity.setCreateBy(ShiroHelper.getUser().getLoginName());
        entity.setLastModifyBy(ShiroHelper.getUser().getLoginName());
        entity.setCreateTime(DateUtil.formatCurrentDateTime(DateUtil.PATTERN_TIME));
        entity.setLastModifyTime(DateUtil.formatCurrentDateTime(DateUtil.PATTERN_TIME));
    }

    public static  <T extends BaseEntity> void compleUpdateEntity(T entity) {
        entity.setIsDel(0);
        entity.setCreateTime(DateUtil.formatCurrentDateTime(DateUtil.PATTERN_TIME));
        entity.setLastModifyTime(DateUtil.formatCurrentDateTime(DateUtil.PATTERN_TIME));
    }
}
