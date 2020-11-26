package com.hpe.dao;
import com.hpe.pojo.*;
/**
 * 
* @Name: IUserDao
* @Description: User持久层接口
* @author yukinoo
* @date 2019年9月4日
*
 */
public interface IUsersDao {
	/// 按账号密码查询
	public UserPo find(String name, String pwd);
	/// 按账号查询
	public UserPo findByName(String name);
	/// 更新
	public int update(UserPo user);
	
	int register(UserPo user);
}
