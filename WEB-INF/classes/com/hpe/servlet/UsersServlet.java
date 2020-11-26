package com.hpe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.pojo.*;
import com.hpe.service.*;
import com.hpe.serviceImpl.*;

/**
 * 
* @Name: UserServlet
* @Description: 用户Servlet
* @author yukinoo
* @date 2019年9月4日
*
 */
@WebServlet("/UserServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IUsersService userService = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * 
	* @Title: login
	* @Description: 登陆
	* @return void
	* @throws
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 针对登陆请求进行处理
		/// 1.获取请求中的账号和密码参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		/// 2.调用Service层中的login方法，并获取返回值
		UserPo userPo = userService.login(name, pwd);
		/// 3.如果返回值不为空，提示“登陆成功！”，则进入后台主界面
		if(userPo != null) {
			/// 保存当前账号信息，以便main.jsp显示
			HttpSession hSession = request.getSession();
			hSession.setAttribute("user", userPo);
			//hSession.setAttribute("adminName", name);
			//UserPo up = (UserPo)hSession.getAttribute("user");
			//System.out.println(up.getAddress());
			response.getWriter().write(
				"<script>"+
					"alert('恭喜你登陆成功');"+
					"window.location.href='"+request.getContextPath()+"/index.jsp';"+
				"</script>"
			);
		}
		/// 4.如果返回值为空，提示“账号或密码不正确，请重新登陆”，再次进入登陆页面
		else {
			response.getWriter().write(
				"<script>"+
					"alert('登陆失败！请检查用户名和密码');"+
					"window.location.href='"+request.getContextPath()+"/qiantai/login.jsp';"+
				"</script>"
			);
		}
	}
	/**
	 * 
	* @Title: logout
	* @Description: 登出
	* @return void
	* @throws
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hSession = request.getSession();
		UserPo up = (UserPo)hSession.getAttribute("user");
		if(up==null) {
			response.getWriter().write(
				"<script>"+
					"alert('您还没有登录，请先登录');"+
					"window.location.href='"+request.getContextPath()+"/qiantai/login.jsp';"+
				"</script>"
			);
		}
		hSession.removeAttribute("user");
		response.getWriter().write(
			"<script>"+
				"alert('登出成功');"+
				"window.parent.location.href='"+request.getContextPath()+"/index.jsp';"+
			"</script>"
		);
	}
	/**
	 * 
	* @Title: update
	* @Description:	更新
	* @return void
	* @throws
	 */
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1.获取表单中修改的账号和密码
		UserPo user = (UserPo)request.getSession().getAttribute("user");
		String name = user.getName();
		String pwd = request.getParameter("pwd");
		int id = Integer.parseInt(request.getParameter("id"));
		String realname = request.getParameter("realname");
		String sex = user.getSex();
		String age = request.getParameter("age");
		String card = request.getParameter("card");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
		/// 2.向service提交修改业务
		/// 3.获取service层返回的结果
		UserPo user1 = new UserPo(id, name, pwd, realname, sex, age, card, address, phone, email, code, "1");
		int result = userService.update(user1);
		/// 4.根据结果1，如果注册成功，提示“注册成功”，重新登陆
		System.out.println(result);
		if(result==1) {
			/// 此时需要将Session域中的user
			HttpSession hSession = request.getSession();
			hSession.removeAttribute("user");
			response.getWriter().write(
				"<script>"+
					"alert('修改成功！');"+
					"window.parent.location.href='"+request.getContextPath()+"/index.jsp';"+
				"</script>"
			);
		}
		/// 5.根据结果-1，注册失败，如果用户名已存在，提示“用户名已存在！”，则重新注册
		else if(result==-1) {
			response.getWriter().write(
				"<script>"+
					"alert('用户名已存在！请重新修改');"+
					"window.location.href='"+request.getContextPath()+"/qiantai/center.jsp';"+
				"</script>"
			);
		}
		/// 6.根据结果0，修改失败，提示“注册失败！”，则重新注册
		else if(result==0){
			response.getWriter().write(
				"<script>"+
					"alert('修改失败！');"+
					"window.location.href='"+request.getContextPath()+"/qiantai/center.jsp';"+
				"</script>"
			);
		}
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1.获取表单中修改的账号和密码
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String card = request.getParameter("card");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
		/// 2.向service提交修改业务
		/// 3.获取service层返回的结果
		UserPo user1 = new UserPo( name, pwd, realname, sex, age, card, address, phone, email, code, "1");
		int result = userService.register(user1);
		/// 4.根据结果1，如果修改成功，提示“修改成功”，重新登陆
		if(result==1) {
			/// 此时需要将Session域中的user
			HttpSession hSession = request.getSession();
			hSession.removeAttribute("user");
			response.getWriter().write(
				"<script>"+
					"alert('注册成功！');"+
					"window.parent.location.href='"+request.getContextPath()+"/index.jsp';"+
				"</script>"
			);
		}
		/// 5.根据结果-1，修改失败，如果用户名已存在，提示“用户名已存在！请重新修改”，则重新修改
		else if(result==-1) {
			response.getWriter().write(
				"<script>"+
					"alert('用户名已存在！');"+
					"window.location.href='"+request.getContextPath()+"/qiantai/reg.jsp';"+
				"</script>"
			);
		}
		/// 6.根据结果0，修改失败，提示“修改失败！”，则重新修改
		else if(result==0){
			response.getWriter().write(
				"<script>"+
					"alert('注册失败！');"+
					"window.location.href='"+request.getContextPath()+"/qiantai/reg.jsp';"+
				"</script>"
			);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getParameter("action");
		
		
		/// 登陆
		if(action.equals("login")) {
			login(request, response);
		}
		/// 登出
		else if(action.equals("logout")) {
			logout(request, response);
		}
		else if(action.equals("update")) {
			update(request, response);
		}
		else if(action.equals("register")) {
			register(request, response);
		}
		else if(action.equals("check")) {
			response.getWriter().write(
				"<script>"+
					"alert('您还没有登录，请先登录');"+
					"window.location.href='"+request.getContextPath()+"/qiantai/login.jsp';"+
				"</script>"
			);
		}
	}

}
