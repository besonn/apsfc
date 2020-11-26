package com.hpe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.service.IMenusService;
import com.hpe.service.INoticeService;
import com.hpe.service.IOrdersService;
import com.hpe.serviceImpl.MenusServiceImpl;
import com.hpe.serviceImpl.NoticeServiceImpl;
import com.hpe.serviceImpl.OrdersServiceImpl;
import com.hpe.util.Page;


@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IMenusService ms = new MenusServiceImpl();
    private INoticeService ns = new NoticeServiceImpl();
	private IOrdersService os = new OrdersServiceImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void allInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1. 获取菜单信息列表，并将其放入作用域
		/// 1.1 获取当前页码
		int curpagenum;
		String curpage = request.getParameter("curpage");
		if(curpage == null || "".equals(curpage)) {
			curpagenum = 1;
		}else {
			curpagenum = Integer.parseInt(curpage);
		}
		/// 1.2 调用MenusService去获取当前页面的菜单信息
		Page page = new Page();
		/// 设置该page的当前页码和每页的条数
		page.setCurPage(curpagenum);
		page.setPageNumber(6);
		page = ms.findMenusAll(page);
		//System.out.println(page);
		request.setAttribute("page", page);
		/// 2. 获取通告信息列表，并将其放入作用域
		List noticeList = ns.findAllNotice();
		request.setAttribute("noticeList", noticeList);
		/// 3. 获取销售排行榜信息（前三条），并将其放入作用域
		List rankList = os.getRankOrder();
		System.out.println(rankList);
		request.setAttribute("rankList", rankList);
		/// 4. 跳转到首页
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/// 根据action判断调用哪个方法
		String action = request.getParameter("action");
		/// 查询所有菜品类别
		if(action.equals("allInfo")) {
			allInfo(request,response);
		}
	}

}
