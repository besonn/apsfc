package com.hpe.daoImpl;

import com.hpe.dao.IMenusDao;
import com.hpe.util.*;
import com.hpe.pojo.*;
/**
 * 
* @Name: MenusDapImpl
* @Description: Menus持久层实现类
* @author yukinoo
* @date 2019年9月5日
*
 */
public class MenusDaoImpl implements IMenusDao {
	private DBUtil dbUtil = new DBUtil();
	/**
	 * 根据当前页获取对应的页面内容
	 */
	@Override
	public Page findMenus(Page page) {
		/// 查询语句
		String sql = "SELECT m.name,t.name typename,m.burden,m.brief,m.price,m.sums,m.price1,m.sums1,m.imgpath,m.id FROM apsfc.menus m,apsfc.types t where m.typeid=t.id";
		try {
			page = dbUtil.getQueryPage(MenuInfo.class, sql, null, page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public MenuPo findByName(String name) {
		/// 根据名字进行查找
		String sql = "select * from apsfc.menus where name=?";
		MenuPo mp1 = null;
		Object[] objects = {name};
		try {
			mp1 = (MenuPo)dbUtil.getObject(MenuPo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mp1;
	}
	@Override
	public int addMenus(MenuPo menu) {
		/// 添加新菜单
		String sql = "insert into menus(name,typeid,burden,brief,price,price1,imgpath) values(?,?,?,?,?,?,?)";
		Object[] objects = {menu.getName(),menu.getTypeid(),menu.getBurden(),menu.getBrief(),menu.getPrice(),menu.getPrice1(),menu.getImgpath()};
		int result = 0;
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public MenuPo findById(int id) {
		String sql = "SELECT * FROM menus where id=?";
		Object[] objects = {id};
		MenuPo menu = new MenuPo();
		try {
			menu = (MenuPo)dbUtil.getObject(MenuPo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
	}
	@Override
	public int updateMenu(MenuPo menu) {
		int result = 0;
		//System.out.println("------------------"+menu.getTypeid());
		String sql = "update menus set name=?,typeid=?,burden=?,brief=?,price=?,price1=? where id=?";
		Object[] objects = {menu.getName(), menu.getTypeid(), menu.getBurden(), menu.getBrief(), menu.getPrice(), menu.getPrice1(), menu.getId()};
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int deleteMenu(MenuPo menu) {
		int result = 0;
		String sql = "delete from menus where id=?";
		Object[] objects = {menu.getId()};
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
