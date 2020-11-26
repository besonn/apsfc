package com.hpe.serviceImpl;

import java.util.List;

import com.hpe.dao.IMenusDao;
import com.hpe.dao.ITypesDao;
import com.hpe.daoImpl.*;
import com.hpe.pojo.MenuPo;
import com.hpe.service.IMenusService;
import com.hpe.util.Page;
/**
 * 
* @Name: MenusServiceImpl
* @Description: Menus服务层实现类
* @author yukinoo
* @date 2019年9月5日
*
 */
public class MenusServiceImpl implements IMenusService {
	private IMenusDao md = new MenusDaoImpl();
	private ITypesDao td = new TypesDaoImpl();
	@Override
	public Page findMenusAll(Page page) {
		return md.findMenus(page);
	}
	@Override
	public List findTypeAll() {
		/// 直接调用Dao层获取所有菜品
		return td.findAllTypes();
	}
	@Override
	public int addMenus(MenuPo menu) {
		/// 添加新菜单
		/// 1. 调用Dao层，查看是否存在同名的菜单，如存在返回-1
		MenuPo mp1 = md.findByName(menu.getName());
		if(mp1 != null) {
			return -1;
		}
		/// 2. 否则，直接调用Dao层，执行插入，并返回插入结果
		return md.addMenus(menu);
	}
	@Override
	public MenuPo findById(int id) {
		// TODO Auto-generated method stub
		return md.findById(id);
	}
	@Override
	public int updateMenu(MenuPo menu) {
		MenuPo menu1 = md.findByName(menu.getName());
		if(menu1 != null && menu.getId()!=menu1.getId()) {
			return -1;
		}
		System.out.println(menu.toString());
		return md.updateMenu(menu);
	}
	@Override
	public int deleteMenu(MenuPo menu) {
		// TODO Auto-generated method stub
		return md.deleteMenu(menu);
	}

}
