package com.genealogy.web.service.impl;

import com.genealogy.web.dao.UserMapper;
import com.genealogy.web.model.EntityHelper;
import com.genealogy.web.model.UserEntity;
import com.genealogy.web.service.IUserService;
import com.genealogy.web.vo.UserReqVo;
import com.genealogy.common.Page;
import com.genealogy.common.utils.PasswordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, String> loginMap = new HashMap<>();
		loginMap.put("loginName", loginName);
		loginMap.put("password", PasswordUtils.encrypt(loginName, password));
		return userMapper.queryUserByNameAndPassword(loginMap);
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
		return new Page<>(list, total, 0, vo.getLimit());
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
		if (null == entity.getPosition()) {
			entity.setPosition("");
			entity.setIsLock(0);
			entity.setSex(0);
		}
		if (null == entity.getAddress()) {
			entity.setAddress("");
		}

		if (null == entity.getEmail()) {
			entity.setEmail("");
		}

		if (null == entity.getMobile()) {
			entity.setMobile("");
		}
		entity.setPassword(PasswordUtils
				.encrypt(entity.getLoginName(), entity.getPassword()));
		if (null == queryUserByName(entity.getLoginName())) {
			return userMapper.save(entity);
		}
		return 0;
	}

	@Override
	public int update(UserEntity entity) {
		//补全实体信息
		EntityHelper.compleUpdateEntity(entity);
		if (null != entity.getPassword() && !"".equals(entity.getPassword())) {
			entity.setPassword(PasswordUtils
					.encrypt(entity.getLoginName(), entity.getPassword()));
		}
		if (null == queryUserByName(entity.getLoginName())) {
			return userMapper.update(entity);
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		return userMapper.delete(id);
	}
}