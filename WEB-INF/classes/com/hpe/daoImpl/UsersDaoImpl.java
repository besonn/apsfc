package com.hpe.daoImpl;

import com.hpe.dao.IUsersDao;
import com.hpe.pojo.UserPo;
import com.hpe.util.*;
/**
 * 
* @Name: UserDaoImpl
* @Description: User持久层实体类
* @author yukinoo
* @date 2019年9月4日
*
 */
public class UsersDaoImpl implements IUsersDao {
	
	private DBUtil dbUtil = new DBUtil();
	@Override
	public UserPo find(String name, String pwd) {
		/// 查询语句(动态)
		String sql = "select * from apsfc.users where name=? and pwd=?";
		/// 参数列表
		Object[] objects = {name, pwd};
		/// 调用DBUtil中的getObject方法进行查询
		UserPo uPo = null;
		try {
			uPo = (UserPo)dbUtil.getObject(UserPo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// 返回结果
		return uPo;
	}
	@Override
	public UserPo findByName(String name) {
		/// 查询语句(动态)
		String sql = "select * from apsfc.users where name=?";
		/// 参数列表
		Object[] objects = {name};
		/// 调用DBUtil中的getObject方法进行查询
		UserPo uPo = null;
		try {
			uPo = (UserPo)dbUtil.getObject(UserPo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uPo;
	}
	@Override
	public int update(UserPo user) {
		/// 根据id进行修改
		String sql="update apsfc.users set name=?,pwd=?,realname=?,sex=?,card=?,address=?,phone=?,email=?,code=? where id=?";
		/// 占位符的参数列表
		Object[] objects = {user.getName(), user.getPwd(), user.getRealname(), user.getSex(), user.getCard(), user.getAddress(), user.getPhone(), user.getEmail(), user.getCode(), user.getId()};
		/// 借助dbUtil执行语句
		int result=0;
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int register(UserPo user) {
		String sql="insert into users(name,pwd,realname,sex,card,address,phone,email,code) values(?,?,?,?,?,?,?,?,?)";
		/// 占位符的参数列表
		Object[] objects = {user.getName(), user.getPwd(), user.getRealname(), user.getSex(), user.getCard(), user.getAddress(), user.getPhone(), user.getEmail(), user.getCode()};
		/// 借助dbUtil执行语句
		int result=0;
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
