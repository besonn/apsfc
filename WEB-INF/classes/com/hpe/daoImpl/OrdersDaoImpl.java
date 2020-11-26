package com.hpe.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hpe.dao.IOrdersDao;
import com.hpe.pojo.NoticePo;
import com.hpe.pojo.OrderInfo;
import com.hpe.pojo.OrderPo;
import com.hpe.pojo.RankInfo;
import com.hpe.pojo.TodayOrderInfo;
import com.hpe.util.DBUtil;
import com.hpe.util.Page;
/**
 * 
* @Name: OrdersDaoImpl
* @Description: 订单持久层实现类
* @author yukinoo
* @date 2019年9月5日
*
 */
public class OrdersDaoImpl implements IOrdersDao {
	private DBUtil dbUtil = new DBUtil();
	@Override
	public Page findOrders(Page page) {
		String sql = "SELECT o.id,u.realname,u.phone,u.address,m.name,o.menusum,m.price1 price,o.times,o.delivery FROM apsfc.orders o,apsfc.users u,apsfc.menus m where o.userid=u.id and o.menuid=m.id";
		try {
			page = dbUtil.getQueryPage(OrderInfo.class, sql, null, page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public List getRankOrder() {
		String sql = "select m.id menuid,m.name menuname,sum(o.menusum) as `sum` from menus m,orders o where m.id=o.menuid group by o.menuid order by `sum` desc limit 3";
		List list = null;
		try {
			list = dbUtil.getQueryList(RankInfo.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int addOrder(OrderPo order) {
		int result = 0;
		System.out.println(order);
		String sql = "insert into orders(userid,menuid,menusum,times,delivery) values(?,?,?,?,?)";
		Object[] objects = {order.getUserid(),order.getMenuid(),order.getMenusum(),order.getTimes(),order.getDelivery()};
		/// 当前方法处于事务中，事务中所有sql语句必须使用同一个connection对象
		try {
			result = dbUtil.execute(DBUtil.getConnection(), sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List findTodayOrders() {
		String sql = "select m.id menuid,m.name menuname,sum(o.menusum) as `sum`,m.price1 price from menus m,orders o where m.id=o.menuid and date_format(o.times,'%Y%m%d')=? group by o.menuid";
		List list = null;
		Object[] objects = {new SimpleDateFormat("yyyyMMdd").format(new Date())};
		try {
			list = dbUtil.getQueryList(TodayOrderInfo.class, sql, objects);
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Page findByuser(Page page,int id) {
		String sql = "SELECT o.id,u.realname,u.phone,u.address,m.name,o.menusum,m.price1 price,o.times,o.delivery FROM apsfc.orders o,apsfc.users u,apsfc.menus m where o.userid=? and o.userid=u.id and o.menuid=m.id";
		Object[] objects = {id};
		try {
			page = dbUtil.getQueryPage(OrderInfo.class, sql, objects,page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public Page findByMenu(Page page,String name) {
		String sql = "SELECT o.id,u.realname,u.phone,u.address,m.name,o.menusum,m.price1 price,o.times,o.delivery FROM apsfc.orders o,apsfc.users u,apsfc.menus m where m.name=? and o.userid=u.id and o.menuid=m.id";
		Object[] objects = {name};
		try {
			page = dbUtil.getQueryPage(OrderInfo.class, sql, objects,page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public Page findByDate(Page page,String date) {
		String sql = "SELECT o.id,u.realname,u.phone,u.address,m.name,o.menusum,m.price1 price,o.times,o.delivery FROM apsfc.orders o,apsfc.users u,apsfc.menus m where date_format(o.times,'%Y-%m-%d')=? and o.userid=u.id and o.menuid=m.id";
		Object[] objects = {date};
		try {
			page = dbUtil.getQueryPage(OrderInfo.class, sql, objects,page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public List findMyMenu( String name,int id) {
		String sql = "SELECT o.id,u.realname,u.phone,u.address,m.name,o.menusum,m.price1 price,o.times,o.delivery FROM apsfc.orders o,apsfc.users u,apsfc.menus m where o.userid=u.id and o.menuid=m.id and o.userid=? and m.name=?";
		List list =	null;
		try {
			Object[] objects = {id,name};
			list = dbUtil.getQueryList(OrderInfo.class, sql, objects);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List findMyDate( String date,int id) {
		String sql = "SELECT o.id,u.realname,u.phone,u.address,m.name,o.menusum,m.price1 price,o.times,o.delivery FROM apsfc.orders o,apsfc.users u,apsfc.menus m where o.userid=u.id and o.menuid=m.id and o.userid=? and date_format(o.times,'%Y-%m-%d')=?";
		List list =	null;
		try {
			Object[] objects = {id,date};
			list = dbUtil.getQueryList(OrderInfo.class, sql, objects);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List findMyAll(int id) {
		String sql = "SELECT o.id,u.realname,u.phone,u.address,m.name,o.menusum,m.price1 price,o.times,o.delivery FROM apsfc.orders o,apsfc.users u,apsfc.menus m where o.userid=u.id and o.menuid=m.id and o.userid=?";
		List list =	null;
		try {
			Object[] objects = {id};
			list = dbUtil.getQueryList(OrderInfo.class, sql, objects);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List findMyAll(int id, String delivery) {
		String sql = "SELECT o.id,u.realname,u.phone,u.address,m.name,o.menusum,m.price1 price,o.times,o.delivery FROM apsfc.orders o,apsfc.users u,apsfc.menus m where o.userid=u.id and o.menuid=m.id and o.userid=? and o.delivery=?";
		List list =	null;
		try {
			Object[] objects = {id,delivery};
			list = dbUtil.getQueryList(OrderInfo.class, sql, objects);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int change(int id) {
		/// 根据id进行修改
		String sql="update orders set delivery=1 where id=?";
		/// 占位符的参数列表
		Object[] objects = {id};
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
	public int delete(int id) {
		String sql="delete from orders where id=?";
		/// 占位符的参数列表
		Object[] objects = {id};
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
	
}
