package com.hpe.daoImpl;
import com.hpe.dao.*;
import com.hpe.util.*;
import com.hpe.pojo.*;
/**
 * 
* @ClassName: AdminDaoImpl
* @Description: Admin持久层实体类
* @author yukinoo
* @date 2019年9月3日
*
 */
public class AdminDaoImpl implements IAdminDao{
	private DBUtil dbUtil = new DBUtil();
	public AdminPo find(String name, String pwd) {
		/// 查询语句(动态)
		String sql = "select * from admin where name=? and pwd=?";
		/// 参数列表
		Object[] objects = {name, pwd};
		/// 调用DBUtil中的getObject方法进行查询
		AdminPo aPo = null;
		try {
			aPo = (AdminPo)dbUtil.getObject(AdminPo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// 返回结果
		return aPo;
	}
	@Override
	public AdminPo findByName(String name) {
		/// 查询语句(动态)
		String sql = "select * from admin where name=?";
		/// 参数列表
		Object[] objects = {name};
		/// 调用DBUtil中的getObject方法进行查询
		AdminPo aPo = null;
		try {
			aPo = (AdminPo)dbUtil.getObject(AdminPo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// 返回结果
		return aPo;
	}
	@Override
	public int update(AdminPo admin) {
		/// 根据id进行修改
		String sql="update admin set name=?,pwd=? where id=?";
		/// 占位符的参数列表
		Object[] objects = {admin.getName(), admin.getPwd(), admin.getId()};
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
	public AdminPo findById(int id) {
		/// 查询语句(动态)
		String sql = "select * from admin where id=?";
		/// 参数列表
		Object[] objects = {id};
		/// 调用DBUtil中的getObject方法进行查询
		AdminPo aPo = null;
		try {
			aPo = (AdminPo)dbUtil.getObject(AdminPo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/// 返回结果
		return aPo;
	}
}
