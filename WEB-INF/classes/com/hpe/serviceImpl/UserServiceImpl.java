package com.hpe.serviceImpl;

import com.hpe.pojo.UserPo;
import com.hpe.service.IUsersService;
import com.hpe.dao.*;
import com.hpe.daoImpl.*;

public class UserServiceImpl implements IUsersService {
	
	private IUsersDao userd = new UsersDaoImpl();
	@Override
	public UserPo login(String name, String pwd) {
		return userd.find(name, pwd);
	}
	@Override
	public int update(UserPo user) {
		/// 1.查找当前数据库中是否存在已经同名的账号，存在则输出-1
		UserPo upo1 = userd.findByName(user.getName());
		if(upo1!=null && upo1.getId()!=user.getId()) {
			return -1;
		}
		/// 2.如果不存在，根据id修改，并根据结果返回相应的值
		return userd.update(user);
	}
	@Override
	public int register(UserPo user) {
		/// 1.查找当前数据库中是否存在已经同名的账号，存在则输出-1
		UserPo upo1 = userd.findByName(user.getName());
		if(upo1!=null && upo1.getId()!=user.getId()) {
			return -1;
		}
		/// 2.如果不存在，根据id修改，并根据结果返回相应的值
		return userd.register(user);
	}

}
