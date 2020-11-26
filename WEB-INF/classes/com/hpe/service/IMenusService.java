package com.hpe.service;
import java.util.List;

import com.hpe.pojo.MenuPo;
import com.hpe.util.*;
/**
 * 
* @Name: IMenusService
* @Description: Menus服务层接口
* @author yukinoo
* @date 2019年9月5日
*
 */
public interface IMenusService {
	/// 根据亚麻获取当前页
	Page findMenusAll(Page page);
	
	/// 获取所有菜品
	List findTypeAll();
	
	/// 添加新菜单
	int addMenus(MenuPo menu);
	
	/// 通过id寻找
	MenuPo findById(int id);
	
	/// 更新菜单
	int updateMenu(MenuPo menu);
	
	/// 删除菜单
	int deleteMenu(MenuPo menu);
}
