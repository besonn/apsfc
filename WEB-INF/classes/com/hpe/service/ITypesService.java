package com.hpe.service;

import java.util.List;

import com.hpe.pojo.TypePo;
import com.hpe.util.Page;

/**
 * 
* @Name: ITypesService
* @Description: types服务层接口
* @author yukinoo
* @date 2019年9月5日
*
 */
public interface ITypesService {
	/// 查找所有菜品类别
	List findAllTypes();
	
	/// 查找特定页面菜品类别
	Page findTypes(Page page);
	
	/// 添加新类别
	int addType(TypePo type);
	
	/// 修改类别
	int updateType(TypePo type);
	
	/// 通过id寻找
	TypePo findById(int id);
	
	/// 删除类别
	int deleteType(TypePo type);
}
