package com.hpe.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.service.IOrdersService;
import com.hpe.serviceImpl.OrdersServiceImpl;
import com.hpe.util.Page;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOrdersService os = new OrdersServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void findOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1. 获取当前页码
		String curpagestr = request.getParameter("curpage");
		int curpage = 0;
		if(curpagestr==null || "".equals(curpagestr)) {
			/// 第一次访问，不存在该变量，则设置为首页
			curpage = 1;
		}else {
			curpage = Integer.parseInt(curpagestr);
		}
		/// 2. 调用Service层去处理，并获取返回值
		Page page = new Page();
		page.setCurPage(curpage);
		page = os.findOrders(page);
		//System.out.println(page);
		request.setAttribute("page", page);	/// 将该数据放进作用域
		/// 3. 根据返回值，进行相关页面的相应 
		//System.out.println(page);
		request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
	}
	
	/**
	 * 
	* @Title: findToday
	* @Description: 寻找本日销售记录
	* @return void
	* @throws
	 */
	protected void findTodayOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 调用Service寻找今天的订单
		request.setAttribute("list", os.findTodayOrders());
		request.getRequestDispatcher("/admin/order_statistic.jsp").forward(request, response);
	}
	
	/**
	 * 修改状态
	 */
	protected void change(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int result = os.change(id);
		String curpage = request.getParameter("curpage");
		if(result==1) {
			response.getWriter().write(
				"<script>"+
					"alert('确定成功');"+
					"window.location.href='"+request.getContextPath()+"/OrdersServlet?action=findorders&curpage="+curpage+"';"+
				"</script>"
			);
		}
		else {
			response.getWriter().write(
				"<script>"+
					"alert('确定失败');"+
					"window.location.href='"+request.getContextPath()+"/OrdersServlet?action=findorders&curpage="+curpage+"';"+
				"</script>"
			);
		}
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int result = os.delete(id);
		String curpage = request.getParameter("curpage");
		if(result==1) {
			response.getWriter().write(
				"<script>"+
					"alert('删除成功');"+
					"window.location.href='"+request.getContextPath()+"/OrdersServlet?action=findorders&curpage="+curpage+"';"+
				"</script>"
			);
		}
		else {
			response.getWriter().write(
				"<script>"+
					"alert('删除失败');"+
					"window.location.href='"+request.getContextPath()+"/OrdersServlet?action=findorders&curpage="+curpage+"';"+
				"</script>"
			);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/// 根据action判断调用哪个方法
		String action = request.getParameter("action");
		/// 查询所有菜品类别
		if(action.equals("findorders")) {
			findOrders(request,response);
		}
		else if(action.equals("findtoday")) {
			findTodayOrders(request, response);
		}
		else if(action.equals("searchid")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String curpagestr = request.getParameter("curpage");
			int curpage = 0;
			if(curpagestr==null || "".equals(curpagestr)) {
				/// 第一次访问，不存在该变量，则设置为首页
				curpage = 1;
			}else {
				curpage = Integer.parseInt(curpagestr);
			}
			Page page = new Page();page.setCurPage(curpage);
			page = os.findByUser(page,id);
			request.setAttribute("page", page);
			request.setAttribute("action", action);
			request.setAttribute("key", "id");
			request.setAttribute("val", id);
			request.getRequestDispatcher("/admin/order_search.jsp").forward(request, response);
		}
		else if(action.equals("searchname")) {
			String key = request.getParameter("key");
			String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
			if(key==null||"".equals(key))name=request.getParameter("name");
			String curpagestr = request.getParameter("curpage");
			int curpage = 0;
			if(curpagestr==null || "".equals(curpagestr)) {
				/// 第一次访问，不存在该变量，则设置为首页
				curpage = 1;
			}else {
				curpage = Integer.parseInt(curpagestr);
			}
			Page page = new Page();page.setCurPage(curpage);
			page = os.findByMenu(page, name);
			request.setAttribute("page", page);
			request.setAttribute("action", action);
			request.setAttribute("key", "name");
			request.setAttribute("val", name);
			request.getRequestDispatcher("/admin/order_search.jsp").forward(request, response);
		}
		else if(action.equals("searchdate")) {
			String date = request.getParameter("date");
			String curpagestr = request.getParameter("curpage");
			int curpage = 0;
			if(curpagestr==null || "".equals(curpagestr)) {
				/// 第一次访问，不存在该变量，则设置为首页
				curpage = 1;
			}else {
				curpage = Integer.parseInt(curpagestr);
			}
			Page page = new Page();page.setCurPage(curpage);
			page = os.findByDate(page, date);
			request.setAttribute("page", page);
			request.setAttribute("action", action);
			request.setAttribute("key", "date");
			request.setAttribute("val", date);
			request.getRequestDispatcher("/admin/order_search.jsp").forward(request, response);
		}
		else if(action.equals("de")) {
			change(request ,response);
		}
		else if(action.equals("delete")) {
			delete(request, response);	
		}
	}

}
