package com.hpe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.pojo.TypePo;
import com.hpe.service.ITypesService;
import com.hpe.serviceImpl.TypesServiceImpl;
import com.hpe.util.Page;

/**
 * 
* @Name: TypesServlet
* @Description: 菜品类别Servlet
* @author yukinoo
* @date 2019年9月5日
*
 */
@WebServlet("/TypesServlet")
public class TypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ITypesService ts = new TypesServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * 
	* @Title: findMenus
	* @Description: 查询当前页菜品类别
	* @return void
	* @throws
	 */
	protected void findTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		page = ts.findTypes(page);
		request.setAttribute("page", page);	/// 将该数据放进作用域
		/// 3. 根据返回值，进行相关页面的相应 
		request.getRequestDispatcher("/admin/type.jsp").forward(request, response);
		
	}
	
	/**
	 * 
	* @Title: findAllTypes
	* @Description:	寻找所有的菜品类别
	* @return void
	* @throws
	 */
	protected void findAllTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1. 调用Service获取所有类别
		List list = ts.findAllTypes();
		/// 2. 将数据放入request作用域
		request.setAttribute("types", list);
		/// 3. 转发请求并跳转至type.jsp
		request.getRequestDispatcher("admin/type.jsp").forward(request, response);
	}
	
	/**
	 * 
	* @Title: add
	* @Description: 添加菜品类别
	* @throws
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		TypePo type = new TypePo(name);
		int result = ts.addType(type);
		/// 添加成功
		if(result==1) {
			response.getWriter().write(
				"<script>"+
					"alert('添加成功！');"+
					"window.location.href='"+request.getContextPath()+"/TypesServlet?action=findtypes&curpage=1';"+
				"</script>"
			);
		}
		/// 类别名称已存在
		else if(result==-1) {
			response.getWriter().write(
				"<script>"+
					"alert('该类别已存在');"+
					"window.location.href='"+request.getContextPath()+"/admin/type_add.jsp';"+
				"</script>"
			);
		}
		/// 6.根据结果0，修改失败，提示“修改失败！”，则重新修改
		else if(result==0){
			response.getWriter().write(
				"<script>"+
					"alert('添加失败！');"+
					"window.location.href='"+request.getContextPath()+"/admin/type_add.jsp';"+
				"</script>"
			);
		}
	}
	/**
	 * 
	* @Title: updateType
	* @Description: 修改类别
	* @return void
	* @throws
	 */
	protected void updateType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String step = request.getParameter("step");
		if(step.equals("a")) {
			int id = Integer.parseInt(request.getParameter("id"));
			TypePo type = ts.findById(id);
			request.setAttribute("type", type);
			request.setAttribute("cur", request.getParameter("cur"));
			request.getRequestDispatcher("admin/type_update.jsp").forward(request, response);
		}
		else if(step.equals("b")){
			String name = request.getParameter("name");
			int id = Integer.parseInt(request.getParameter("id"));
			String cur = request.getParameter("cur");
			TypePo type = new TypePo(id, name);
			int result = ts.updateType(type);
			if(result==1) {
				response.getWriter().write(
					"<script>"+
						"alert('修改成功！');"+
						"window.location.href='"+request.getContextPath()+"/TypesServlet?action=findtypes&curpage="+cur+"';"+
					"</script>"
				);
			}
			/// 类别名称已存在
			else if(result==-1) {
				response.getWriter().write(
					"<script>"+
						"alert('该类别名称已存在');"+
						"window.location.href='"+request.getContextPath()+"/TypesServlet?action=updatetype&step=a&id="+id+"';"+
					"</script>"
				);
			}
			/// 更新失败
			else if(result==0){
				response.getWriter().write(
					"<script>"+
						"alert('修改失败！');"+
						"window.location.href='"+request.getContextPath()+"/TypesServlet?action=updatetype&step=a&id="+id+"';"+
					"</script>"
				);
			}
		}
	}
	
	/**
	 * 
	* @Title: deleteType
	* @Description: 删除类别
	* @return void
	* @throws
	 */
	protected void deleteType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String cur = request.getParameter("cur");
		TypePo type = new TypePo(id, null);
		int result = ts.deleteType(type);
		/// 删除成功
		if(result==1) {
			response.getWriter().write(
				"<script>"+
					"alert('删除成功！');"+
					"window.location.href='"+request.getContextPath()+"/TypesServlet?action=findtypes&curpage="+cur+"';"+
				"</script>"
			);
		}
		/// 删除失败
		else if(result==0){
			response.getWriter().write(
				"<script>"+
					"alert('删除失败！');"+
					"window.location.href='"+request.getContextPath()+"/TypesServlet?action=findtypes&curpage="+cur+"';"+
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
		if(action.equals("findtypes")) {
			findTypes(request,response);
		}
		else if(action.equals("add")) {
			add(request, response);
		}
		else if(action.equals("updatetype")) {
			updateType(request,response);
		}
		else if(action.equals("deletetype")) {
			deleteType(request,response);
		}
	}

}
