package com.hpe.service;
import com.hpe.pojo.*;
/**
 * 
* @Name: IAdminService
* @Description: Admin服务层接口
* @author yukinoo
* @date 2019年9月3日
*
 */
public interface IAdminService {
	/// 登陆
	public AdminPo login(String name,String pwd);
	
	/// 修改：1.成功，0.失败，-1.用户名已存在
	public int update(AdminPo admin);
	
	/// 查询密码是否正确
	public boolean checkPassword(int id, String pwd);
}
