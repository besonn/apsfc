package com.hpe.serviceImpl;

import com.hpe.dao.*;
import com.hpe.daoImpl.*;
import com.hpe.pojo.*;
import com.hpe.service.IAdminService;

public class AdminServiceImpl implements IAdminService {

	private IAdminDao ad = new AdminDaoImpl();
	@Override
	public AdminPo login(String name, String pwd) {
		// TODO Auto-generated method stub
		return ad.find(name,pwd);
	}
	@Override
	public int update(AdminPo admin) {
		/// 1.查找当前数据库中是否存在已经同名的账号，存在则输出-1
		AdminPo apo1 = ad.findByName(admin.getName());
		if(apo1!=null && apo1.getId()!=admin.getId()) {
			return -1;
		}
		/// 2.如果不存在，根据id修改，并根据结果返回相应的值
		return ad.update(admin);
	}
	@Override
	public boolean checkPassword(int id, String pwd) {
		AdminPo apo1 = ad.findById(id);
		if(apo1!=null&&!pwd.equals(apo1.getPwd()))return false;
		return true;
	}

}
