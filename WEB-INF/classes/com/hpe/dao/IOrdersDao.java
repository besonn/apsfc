package com.hpe.dao;

import java.util.List;

import com.hpe.pojo.OrderPo;
import com.hpe.util.Page;

/**
 * 
* @Name: IOrdersDao
* @Description: 订单持久层接口
* @author yukinoo
* @date 2019年9月5日
*
 */
public interface IOrdersDao {
	/// 查询特定页面订单
	Page findOrders(Page page);
	
	/// 获取销售排行榜前三
	List getRankOrder();
	
	/// 添加新订单
	int addOrder(OrderPo order);
	
	/// 寻找本日订单
	List findTodayOrders();
	
	Page findByuser(Page page,int id);
	
	Page findByMenu(Page page,String name);
	
	Page findByDate(Page page,String date);
	
	List findMyMenu(String name,int id);
	
	List findMyDate(String date,int id);
	
	List findMyAll(int id);
	
	List findMyAll(int id,String delivery);
	
	int change(int id);
	
	int delete(int id);
}
