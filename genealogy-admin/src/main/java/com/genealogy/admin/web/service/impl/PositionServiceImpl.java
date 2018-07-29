package com.genealogy.admin.web.service.impl;

import com.genealogy.admin.web.dao.PositionMapper;
import com.genealogy.admin.web.model.EntityHelper;
import com.genealogy.admin.web.model.PositionEntity;
import com.genealogy.admin.web.service.IPositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 职位服务实现类
 * @author GUOFAZHAN
 * @version [版本号, ${date} 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("positionService")
public class PositionServiceImpl implements IPositionService {

    @Resource
    private PositionMapper positionMapper;

    @Override
    public List<PositionEntity> queryAll() {
        return positionMapper.queryAll();
    }

    @Override
    public PositionEntity get(Integer id) {
        return positionMapper.get(id);
    }

    @Override
    public void save(PositionEntity entity) {
        //补全信息
        EntityHelper.compleSaveEntity(entity);
        positionMapper.save(entity);
    }

    @Override
    public int update(PositionEntity entity) {
        //补全信息
        EntityHelper.compleUpdateEntity(entity);
        return positionMapper.update(entity);
    }

    @Override
    public int delete(Integer id) {
        return positionMapper.delete(id);
    }
}
