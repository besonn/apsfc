package com.hpe.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.pojo.ShoppingCartPo;
import com.hpe.pojo.UserPo;
import com.hpe.service.IOrdersService;
import com.hpe.serviceImpl.OrdersServiceImpl;
import com.hpe.util.Page;

/**
 * Servlet implementation class UsersOrderServlet
 */
@WebServlet("/UsersOrderServlet")
public class UsersOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IOrdersService os = new OrdersServiceImpl();  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * 
	* @Title: addOrder
	* @Description: 添加订单
	* @throws
	 */
	protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1. 获取当前userid和订单
		/// 1.1 useid不存在，提示请先登录
		HttpSession hSession = request.getSession();
		UserPo user = (UserPo)request.getSession().getAttribute("user");
		
		ArrayList<ShoppingCartPo> shoppingCart = (ArrayList<ShoppingCartPo>)hSession.getAttribute("shoppingCart");
		/// 2. 调用Service添加订单
		int result = os.addOrder(""+user.getId(), shoppingCart);
		if(result == 1) {
			hSession.removeAttribute("shoppingCart");
			response.getWriter().write(
				"<script>"+
					"alert('订单提交成功');"+
					"window.location.href='"+request.getContextPath()+"/qiantai/index.jsp';"+
				"</script>"
			);
		}
		else {
			response.getWriter().write(
				"<script>"+
					"alert('订单提交失败');"+
					"window.location.href='"+request.getContextPath()+"/qiantai/index.jsp';"+
				"</script>"
			);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/// 根据action判断调用哪个方法
		String action = request.getParameter("action");
		if(action.equals("addOrder")) {
			addOrder(request, response);
		}
		else if(action.equals("searchmyname")) {
			String name = request.getParameter("menuname");
			String curpagestr = request.getParameter("curpage");
			HttpSession hSession = request.getSession();
			UserPo user = (UserPo)request.getSession().getAttribute("user");
			int id = user.getId();
			List list = os.findMyMenu(name,id);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/qiantai/order.jsp").forward(request, response);
		}
		else if(action.equals("searchmydate")) {
			String date = request.getParameter("date");
			HttpSession hSession = request.getSession();
			UserPo user = (UserPo)request.getSession().getAttribute("user");
			int id = user.getId();
			List list = os.findMyDate(date,id);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/qiantai/order.jsp").forward(request, response);
		}
		else if(action.equals("searchmyall")) {
			HttpSession hSession = request.getSession();
			UserPo user = (UserPo)request.getSession().getAttribute("user");
			int id = user.getId();
			List list = os.findMyAll(id);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/qiantai/order.jsp").forward(request, response);
		}
		else if(action.equals("searchmyde")) {
			HttpSession hSession = request.getSession();
			UserPo user = (UserPo)request.getSession().getAttribute("user");
			int id = user.getId();
			String de = request.getParameter("de");
			String delivery = null;
			if(de.equals("true"))delivery = "1";
			else delivery = "0";
			List list = os.findMyAll(id, delivery);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/qiantai/order.jsp").forward(request, response);
		}
	}

}
