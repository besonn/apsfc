package com.hpe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.service.IAdminService;
import com.hpe.serviceImpl.AdminServiceImpl;
import com.hpe.pojo.*;
/**
 * 
* @Name: AdminServlet
* @Description: Admin控制层Servlet
* @author yukinoo
* @date 2019年9月3日
*
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IAdminService as = new AdminServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 登陆
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 针对登陆请求进行处理
		/// 1.获取请求中的账号和密码参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		/// 2.调用Service层中的login方法，并获取返回值
		AdminPo ap = as.login(name, pwd);
		/// 3.如果返回值不为空，提示“登陆成功！”，则进入后台主界面
		if(ap != null) {
			/// 保存当前账号信息，以便main.jsp显示
			HttpSession hSession = request.getSession();
			hSession.setAttribute("admin", ap);
			//hSession.setAttribute("adminName", name);
			
			response.getWriter().write(
				"<script>"+
					"alert('恭喜你登陆成功');"+
					"window.location.href='"+request.getContextPath()+"/admin/main.jsp';"+
				"</script>"
			);
		}
		/// 4.如果返回值为空，提示“账号或密码不正确，请重新登陆”，再次进入登陆页面
		else {
			response.getWriter().write(
				"<script>"+
					"alert('登陆失败！请检查用户名和密码');"+
					"window.parent.location.href='"+request.getContextPath()+"/admin/index.jsp';"+
				"</script>"
			);
		}
	}
	/**
	 * 更改管理员信息
	 */
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1.获取表单中修改的账号和密码
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String newpwd = request.getParameter("newpwd");
		int id = Integer.parseInt(request.getParameter("id"));
		
		if(!as.checkPassword(id,pwd)) {
			response.getWriter().write(
				"<script>"+
					"alert('原密码错误，请重新输入');"+
					"window.location.href='"+request.getContextPath()+"/admin/admin_update.jsp';"+
				"</script>"
			);
			return;
		}
		
		/// 2.向service提交修改业务
		/// 3.获取service层返回的结果
		AdminPo admin2 = new AdminPo(id, name, newpwd);
		int result = as.update(admin2);
		/// 4.根据结果1，如果修改成功，提示“修改成功”，重新登陆
		if(result==1) {
			/// 此时需要将Session域中的admin
			HttpSession hSession = request.getSession();
			hSession.removeAttribute("admin");
			response.getWriter().write(
				"<script>"+
					"alert('修改成功！');"+
					"window.parent.location.href='"+request.getContextPath()+"/admin/index.jsp';"+
				"</script>"
			);
		}
		/// 5.根据结果-1，修改失败，如果用户名已存在，提示“用户名已存在！请重新修改”，则重新修改
		else if(result==-1) {
			response.getWriter().write(
				"<script>"+
					"alert('用户名已存在！请重新修改');"+
					"window.location.href='"+request.getContextPath()+"/admin/admin_update.jsp';"+
				"</script>"
			);
		}
		/// 6.根据结果0，修改失败，提示“修改失败！”，则重新修改
		else if(result==0){
			response.getWriter().write(
				"<script>"+
					"alert('修改失败！');"+
					"window.location.href='"+request.getContextPath()+"/admin/admin_update.jsp';"+
				"</script>"
			);
		}
	}
	
	/**
	 * 
	* @Title: logout
	* 注销退出
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1.将当前账号的共享数据删除
		request.getSession().removeAttribute("admin");
		/// 2.跳转到登陆页面
		response.getWriter().write(
			"<script>"+
				"alert('登出成功！');"+
				"window.parent.location.href='"+request.getContextPath()+"/admin/index.jsp';"+
			"</script>"
		);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/// 为了在一个doPost方法中处理多个业务请求，需要一些标记来区分不同的请求
		/// 对于login提供一个login标签
		String action = request.getParameter("action");
		if(action.equals("login")){
			login(request,response);
		}
		else if(action.equals("update")) {
			update(request,response);
		}
		else if(action.equals("logout")) {
			logout(request,response);
		}
	}

}
