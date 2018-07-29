package com.genealogy.admin.web.service.impl;

import com.genealogy.admin.web.dao.AreaMapper;
import com.genealogy.admin.web.model.AreaEntity;
import com.genealogy.admin.web.model.EntityHelper;
import com.genealogy.admin.web.service.IAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("areaService")
public class AreaServiceImpl implements IAreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<AreaEntity> queryAll() {
        return areaMapper.queryAll();
    }

    @Override
    public AreaEntity get(Integer id) {
        return areaMapper.get(id);
    }

    @Override
    public int save(AreaEntity entity) {
        EntityHelper.compleSaveEntity(entity);
        return areaMapper.save(entity);
    }

    @Override
    public int update(AreaEntity entity) {
        EntityHelper.compleUpdateEntity(entity);
        return areaMapper.update(entity);
    }

    @Override
    public int delete(Integer id) {
        return areaMapper.delete(id);
    }
}
