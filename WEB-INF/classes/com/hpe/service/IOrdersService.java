package com.hpe.service;

import java.util.ArrayList;
import java.util.List;

import com.hpe.pojo.ShoppingCartPo;
import com.hpe.util.Page;

/**
 * 
* @Name: IOrdersService
* @Description: 订单服务层接口
* @author yukinoo
* @date 2019年9月5日
*
 */
public interface IOrdersService {
	/// 查询特定页面订单
	Page findOrders(Page page);
	
	/// 获取销售排行榜
	List getRankOrder();
	
	/// 添加新订单
	int addOrder(String userid, ArrayList<ShoppingCartPo> shoppingCart);
	
	/// 获取本日订单
	List findTodayOrders();
	
	Page findByUser(Page page,int id);
	
	Page findByMenu(Page page,String name);
	
	Page findByDate(Page page,String date);
	
	List findMyMenu(String name,int id);
	
	List findMyDate(String date,int id);
	
	List findMyAll(int id);
	
	List findMyAll(int id,String delivery);
	
	int change(int id);
	
	int delete(int id);
}
