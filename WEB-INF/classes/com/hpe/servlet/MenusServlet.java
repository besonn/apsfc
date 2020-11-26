package com.hpe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.pojo.MenuPo;
import com.hpe.pojo.TypePo;
import com.hpe.service.IMenusService;
import com.hpe.serviceImpl.*;
import com.hpe.util.*;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import java.util.List;

/**
 * 
* @Name: MenusServlet
* @Description: 处理菜单的相关请求
* @author yukinoo
* @date 2019年9月5日
*
 */
@WebServlet("/MenusServlet")
public class MenusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static IMenusService ms = new MenusServiceImpl(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	/**
	 * 查询当前页的菜单
	 */
	protected void findMenus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		page = ms.findMenusAll(page);
		request.setAttribute("page", page);	/// 将该数据放进作用域
		/// 3. 根据返回值，进行相关页面的相应 
		request.getRequestDispatcher("/admin/menus.jsp").forward(request, response);
		
	}
	/**
	 * 查找所有菜品类别
	 */
	protected void findAllTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1. 直接调用Service层，获取所有菜品
		List list = ms.findTypeAll();
		/// 2. 将获取的所有菜品放到作用域
		request.setAttribute("types", list);
		/// 3. 请求转发并跳转到menus_add.jsp
		request.getRequestDispatcher("admin/menus_add.jsp").forward(request, response);
	}
	/**
	 * 
	* @Title: addMenus
	* @Description: 添加新菜单
	* @return void
	* @throws
	 */
	protected void addMenus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1. 获取表单中的菜单数据
		/// 1.1 创建SmartUpload
		SmartUpload sUpload = new SmartUpload();
		/// 1.2 初始化对象
		sUpload.initialize(getServletConfig(), request, response);
		/// 1.3 执行上传
		try {
			sUpload.upload();
			/// 1.4 借助SmartUpload获取参数
			String name = sUpload.getRequest().getParameter("name");
			String burden = sUpload.getRequest().getParameter("burden");
			String price = sUpload.getRequest().getParameter("price");
			String price1 = sUpload.getRequest().getParameter("price1");
			String brief = sUpload.getRequest().getParameter("brief");
			String typeid = sUpload.getRequest().getParameter("typeid");
			/// 1.4.1 获取图片路径
			SmartFile file = sUpload.getFiles().getFile(0);
			String imgpath = "img/" + file.getFileName();
			/// 2. 调用Sevice层添加
			MenuPo menu = new MenuPo(name, typeid, burden, brief, price, price1, imgpath);
			int result = ms.addMenus(menu);
			/// 3. 根据返回结果处理响应页面
			/// 3.1 返回1，则提示添加成功，并跳转至menus.jsp|MenusServlet?action=all
			if(result == 1) {
				/// 保存上传文件到服务器指定路径
				sUpload.save("/img");
				
				response.getWriter().write(
					"<script>"+
						"alert('添加成功！');"+
						"window.location.href='"+request.getContextPath()+"/MenusServlet?action=all';"+
					"</script>"
				);
			}
			/// 3.2 返回-1，则提示菜单名也存在，请重新添加，并回到添加页面
			else if(result == -1) {
				response.getWriter().write(
					"<script>"+
						"alert('菜单名已存在，请重新添加！');"+
						"window.location.href='"+request.getContextPath()+"/admin/menus_add.jsp';"+
					"</script>"
				);
			}
			/// 3.3 返回0，则提示添加失败，请重新添加，并回到添加页面
			else if(result == 0) {
				response.getWriter().write(
					"<script>"+
						"alert('添加失败');"+
						"window.location.href='"+request.getContextPath()+"/admin/menus_add.jsp';"+
					"</script>"
				);
			}
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: updateMenu
	* @Description:	更新菜单
	* @return void
	* @throws
	 */
	protected void updateMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String step = request.getParameter("step");
		if(step.equals("a")) {
			/// 1. 直接调用Service层，获取所有菜品
			List list = ms.findTypeAll();
			/// 2. 将获取的所有菜品放到作用域
			request.setAttribute("types", list);
			
			int id = Integer.parseInt(request.getParameter("id"));
			MenuPo menu = ms.findById(id);
			request.setAttribute("menu", menu);
			request.setAttribute("cur", request.getParameter("cur"));
			request.getRequestDispatcher("admin/menus_update.jsp").forward(request, response);
		}
		else if(step.equals("b")){
			int id = Integer.parseInt(request.getParameter("id"));
			String cur = request.getParameter("cur");
			String name = request.getParameter("name");
			String typeid = request.getParameter("typeid");
			String burden = request.getParameter("burden");
			String brief = request.getParameter("brief");
			String price = request.getParameter("price");
			String price1 = request.getParameter("price1");
			MenuPo menu = ms.findById(id);
			menu.setName(name);menu.setTypeid(typeid);
			menu.setBurden(burden);menu.setBrief(brief);
			menu.setPrice(price);menu.setPrice1(price1);
			int result = ms.updateMenu(menu);
			if(result==1) {
				response.getWriter().write(
					"<script>"+
						"alert('修改成功！');"+
						"window.location.href='"+request.getContextPath()+"/MenusServlet?action=all&curpage="+cur+"';"+
					"</script>"
				);
			}
			/// 类别名称已存在
			else if(result==-1) {
				response.getWriter().write(
					"<script>"+
						"alert('该菜品名称已存在');"+
						"window.location.href='"+request.getContextPath()+"/MenusServlet?action=updatemenu&step=a&id="+id+"&curpage="+cur+"';"+
					"</script>"
				);
			}
			/// 更新失败
			else if(result==0){
				response.getWriter().write(
					"<script>"+
						"alert('修改失败！');"+
						"window.location.href='"+request.getContextPath()+"/MenusServlet?action=updatemenu&step=a&id="+id+"&curpage="+cur+"';"+
					"</script>"
				);
			}
		}
	}
	
	protected void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String cur = request.getParameter("cur");
		MenuPo menu = new MenuPo();
		menu.setId(id);
		int result = ms.deleteMenu(menu);
		/// 删除成功
		if(result==1) {
			response.getWriter().write(
				"<script>"+
					"alert('删除成功！');"+
					"window.location.href='"+request.getContextPath()+"/MenusServlet?action=all&curpage="+cur+"';"+
				"</script>"
			);
		}
		/// 删除失败
		else if(result==0){
			response.getWriter().write(
				"<script>"+
					"alert('删除失败！');"+
					"window.location.href='"+request.getContextPath()+"/MenusServlet?action=all&curpage="+cur+"';"+
				"</script>"
			);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/// 根据action判断调用哪个方法
		String action = request.getParameter("action");
		/// 菜单查询
		if(action.equals("all")) {
			/// 查询菜单
			findMenus(request,response);
		}
		/// 查找所有菜品
		else if(action.equals("findTypeAll")) {
			findAllTypes(request, response);
		}
		else if(action.equals("add")) {
			addMenus(request, response);
		}
		else if(action.equals("updatemenu")) {
			updateMenu(request, response);
		}
		else if(action.equals("deletemenu")) {
			deleteMenu(request, response);
		}
	}

}
