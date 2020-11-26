package com.hpe.dao;

import java.util.List;

import com.hpe.pojo.TypePo;
import com.hpe.util.Page;
/**
 * 
* @Name: ITypesDao
* @Description: Types持久层接口
* @author yukinoo
* @date 2019年9月5日
*
 */
public interface ITypesDao {
	///
	List findAllTypes();
	
	/// 寻找特定页面菜品类别
	Page findTypes(Page page);
	
	/// 通过名字寻找
	TypePo findByName(String name);
	
	/// 添加类别
	int addType(TypePo type);
	
	/// 修改类别
	int updateType(TypePo type);
	
	/// 通过id寻找
	TypePo findById(int id);
	
	/// 删除类别
	int deleteType(TypePo type);
	
}
