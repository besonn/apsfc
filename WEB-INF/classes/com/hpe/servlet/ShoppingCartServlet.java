package com.hpe.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.pojo.MenuPo;
import com.hpe.pojo.ShoppingCartPo;
import com.hpe.pojo.UserPo;
import com.hpe.service.IMenusService;
import com.hpe.serviceImpl.MenusServiceImpl;

/**
 * 
* @Name: ShoppingCartServlet
* @Description: 餐车
* @author yukinoo
* @date 2019年9月9日
*
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IMenusService ms = new MenusServiceImpl();
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * 
	* @Title: addToCart
	* @Description: 加入餐车
	* @return void
	* @throws
	 */
	protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1. 获取参数
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		int curpage = Integer.parseInt(request.getParameter("curpage"));
		/// 2. 餐车是否存在？
		HttpSession hSession = request.getSession();
		ArrayList<ShoppingCartPo> shoppingCart = (ArrayList<ShoppingCartPo>)hSession.getAttribute("shoppingCart");
		/// 2.1 若餐车不存在，创建餐车
		if(shoppingCart==null) {
			shoppingCart = new ArrayList<ShoppingCartPo>();
		}
		/// 2.2 看餐车中是否存在该菜名，存在则数量加一，不存在将菜单加入餐车
		boolean flag = false;
		for(ShoppingCartPo cart: shoppingCart) {
			if(cart.getMenuid()== menuid) {
				cart.setSum(cart.getSum()+1);
				flag=true;break;
			}
		}
		if(!flag) {
			MenuPo menu = ms.findById(menuid);
			ShoppingCartPo cart = new ShoppingCartPo(menu.getName(), menu.getPrice1(), 1, menuid);
			shoppingCart.add(cart);
		}
		hSession.setAttribute("shoppingCart", shoppingCart);
		/// 3. 跳转回原页面
		response.sendRedirect(request.getContextPath()+"/qiantai/index.jsp?curpage="+curpage);
	}
	
	/**
	 * 
	* @Title: delCart
	* @Description: 取消订单
	* @return void
	* @throws
	 */
	protected void delCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1. 获取参数
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		HttpSession hSession = request.getSession();
		ArrayList<ShoppingCartPo> shoppingCart = (ArrayList<ShoppingCartPo>)hSession.getAttribute("shoppingCart");
		Iterator<ShoppingCartPo> it = shoppingCart.iterator();
		while(it.hasNext()) {
			ShoppingCartPo cart = it.next();
			if(cart.getMenuid()==menuid) {
				it.remove();
				break;
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/// 根据action判断调用哪个方法
		String action = request.getParameter("action");
		if(action.equals("add")) {
			addToCart(request, response);
		}
		else if(action.equals("delall")) {
			HttpSession hSession = request.getSession();
			hSession.removeAttribute("shoppingCart");
			String curpage = request.getParameter("curpage");
			response.sendRedirect(request.getContextPath()+"/qiantai/index.jsp?curpage="+curpage);
		}
		else if(action.equals("delcart")) {
			int curpage = Integer.parseInt(request.getParameter("curpage"));
			delCart(request, response);
			response.sendRedirect(request.getContextPath()+"/qiantai/index.jsp?curpage="+curpage);
		}
		else if(action.equals("delcart1")) {
			delCart(request, response);
			response.sendRedirect(request.getContextPath()+"/qiantai/shoppingcar.jsp");
		}
	}

}
