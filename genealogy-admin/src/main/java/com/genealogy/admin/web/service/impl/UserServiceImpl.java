package com.genealogy.admin.web.service.impl;

import com.genealogy.admin.web.dao.UserMapper;
import com.genealogy.admin.web.model.EntityHelper;
import com.genealogy.admin.web.model.UserEntity;
import com.genealogy.admin.web.service.IUserService;
import com.genealogy.admin.web.vo.UserReqVo;
import com.genealogy.common.Page;
import com.genealogy.common.utils.PasswordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务实现类
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public UserEntity login(String loginName, String password) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(1);
		userEntity.setLoginName(loginName);
		userEntity.setIsLock(0);
		userEntity.setPassword(password);
		userEntity.setShowName("系统管理员");
		return userEntity;
		//return userMapper.queryUserByNameAndPassword(loginName, password);
	}

	@Override
	public List<UserEntity> queryAll() {
		return userMapper.queryAll();
	}


	@Override
	public Page<UserEntity> page(UserReqVo vo) {
		List<UserEntity> list = userMapper.list(vo);
		int total = userMapper.count(vo);
		//int page = vo.getOffset() / vo.getLimit() + 1;
		return new Page<>(list,total,0,vo.getLimit());
	}

	@Override
	public UserEntity get(Integer id) {
		return userMapper.get(id);
	}

	@Override
	public UserEntity queryUserByName(String loginName) {
		return userMapper.queryUserByName(loginName);
	}

	@Override
	public int save(UserEntity entity) {
		//补全实体信息
		EntityHelper.compleSaveEntity(entity);
		if(null == entity.getPosition()){
			entity.setPosition("");
			entity.setIsLock(0);
			entity.setSex(0);
		}
		if(null == entity.getAddress()){
			entity.setAddress("");
		}

		if(null == entity.getEmail()){
			entity.setEmail("");
		}

		if(null == entity.getMobile()){
			entity.setMobile("");
		}
		entity.setPassword(PasswordUtils.encrypt(entity.getLoginName(),entity.getPassword()));
		if(null == queryUserByName(entity.getLoginName())){
			return userMapper.save(entity);
		}
		return 0;
	}

	@Override
	public int update(UserEntity entity) {
		//补全实体信息
		EntityHelper.compleUpdateEntity(entity);
		if(null != entity.getPassword() && !"".equals(entity.getPassword())){
			entity.setPassword(PasswordUtils.encrypt(entity.getLoginName(),entity.getPassword()));
		}
		if(null == queryUserByName(entity.getLoginName())){
			return userMapper.update(entity);
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		return userMapper.delete(id);
	}
}
