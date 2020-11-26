package com.hpe.serviceImpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hpe.dao.IOrdersDao;
import com.hpe.daoImpl.OrdersDaoImpl;
import com.hpe.pojo.OrderPo;
import com.hpe.pojo.ShoppingCartPo;
import com.hpe.service.IOrdersService;
import com.hpe.util.DBUtil;
import com.hpe.util.Page;
/**
 * 
* @Name: OrdersServiceImpl
* @Description: 订单服务层实现类
* @author yukinoo
* @date 2019年9月5日
*
 */
public class OrdersServiceImpl implements IOrdersService {
	private IOrdersDao od = new OrdersDaoImpl();
	@Override
	public Page findOrders(Page page) {
		return od.findOrders(page);
	}
	@Override
	public List getRankOrder() {
		// TODO Auto-generated method stub
		return od.getRankOrder();
	}
	@Override
	public int addOrder(String userid, ArrayList<ShoppingCartPo> shoppingCart) {
		int result = 0;
		try {
			/// 1. 开启事务
			DBUtil.beginTranscation();
			for(ShoppingCartPo cart: shoppingCart) {
				/// 2.1 根据当前数据生成相应订单
				OrderPo oPo = new OrderPo();
				oPo.setDelivery("0");
				oPo.setMenuid(""+cart.getMenuid());
				oPo.setMenusum(""+cart.getSum());
				oPo.setUserid(userid);
				oPo.setTimes(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				/// 2.2 调用Dao层，将订单加入数据库
				result = od.addOrder(oPo);
				if(result==0) break;
			}
			/// 3. 关闭事物
			DBUtil.endTranscation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				DBUtil.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			/// 无论提交结果如何，都需要将事务中用到的connection关闭
			try {
				DBUtil.closeConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/// 2. 
		return result;
	}
	@Override
	public List findTodayOrders() {
		// TODO Auto-generated method stub
		return od.findTodayOrders();
	}
	@Override
	public Page findByUser(Page page,int id) {
		// TODO Auto-generated method stub
		return od.findByuser(page,id);
	}
	@Override
	public Page findByMenu(Page page,String name) {
		// TODO Auto-generated method stub
		return od.findByMenu(page,name);
	}
	@Override
	public Page findByDate(Page page,String date) {
		// TODO Auto-generated method stub
		return od.findByDate(page,date);
	}
	@Override
	public List findMyMenu( String name,int id) {
		// TODO Auto-generated method stub
		return od.findMyMenu( name, id);
	}
	@Override
	public List findMyDate( String date,int id) {
		// TODO Auto-generated method stub
		return od.findMyDate( date, id);
	}
	@Override
	public List findMyAll(int id) {
		// TODO Auto-generated method stub
		return od.findMyAll(id);
	}
	@Override
	public List findMyAll(int id, String delivery) {
		// TODO Auto-generated method stub
		return od.findMyAll(id, delivery);
	}
	@Override
	public int change(int id) {
		// TODO Auto-generated method stub
		return od.change(id);
	}
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return od.delete(id);
	}
	
}
