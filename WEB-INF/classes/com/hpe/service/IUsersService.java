package com.hpe.service;
import com.hpe.pojo.*;
/**
 * 
* @Name: IUserService
* @Description: User服务层接口
* @author yukinoo
* @date 2019年9月4日
*
 */
public interface IUsersService {
	/// 登录
	UserPo login(String name,String pwd);
	///  修改：1.成功，0.失败，-1.用户名已存在
	int update(UserPo user);
	
	int register(UserPo user);
}
