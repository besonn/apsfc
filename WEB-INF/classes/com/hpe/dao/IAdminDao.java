package com.hpe.dao;
import com.hpe.pojo.*;
/**
 * 
* @Name: IAdminDao
* @Description: Admin持久层接口
* @author 郑玉龙
* @date 2019年9月3日
*
 */
public interface IAdminDao {
	/// 根据用户名和密码查询，登陆
	AdminPo find(String name,String pwd);
	
	/// 根据用户名查询
	AdminPo findByName(String name);
	
	/// 根据id查询
	AdminPo findById(int id);
	
	/// 更新
	int update(AdminPo admin);
}
