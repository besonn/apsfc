package com.hpe.dao;
import com.hpe.pojo.MenuPo;
import com.hpe.util.*;
/**
 * 
* @Name: IMenusDao
* @Description: Menus持久层接口
* @author yukinoo
* @date 2019年9月5日
*
 */
public interface IMenusDao {
	/// 根据页码获取相应的Page
	Page findMenus(Page page);
	
	/// 根据菜单名字进行查找
	MenuPo findByName(String name);
	
	/// 插入菜单
	int addMenus(MenuPo menu);
	
	/// 通过id寻找
	MenuPo findById(int id);
	
	/// 更新菜单
	int updateMenu(MenuPo menu);
	
	/// 删除菜单
	int deleteMenu(MenuPo menu);
}
