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

import com.hpe.pojo.NoticePo;
import com.hpe.pojo.TypePo;
import com.hpe.service.INoticeService;
import com.hpe.serviceImpl.NoticeServiceImpl;
import com.hpe.util.Page;

/**
 * 
* @Name: NoticeServlet
* @Description: TODO
* @author yukinoo
* @date 2019年9月5日
*
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private INoticeService ns = new NoticeServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * 
	* @Title: findNotice
	* @Description: 寻找特定页面公告
	* @return void
	* @throws
	 */
	protected void findNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		page = ns.findNotice(page);
		//System.out.println(page);
		request.setAttribute("page", page);	/// 将该数据放进作用域
		/// 3. 根据返回值，进行相关页面的相应 
		request.getRequestDispatcher("/admin/notice.jsp").forward(request, response);
	}
	
	/**
	 * 
	* @Title: addNotice
	* @Description: 添加公告
	* @return void
	* @throws
	 */
	protected void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		NoticePo notice = new NoticePo(name, content, time);
		int result = ns.addNotice(notice);
		/// 添加成功
		if(result==1) {
			response.getWriter().write(
				"<script>"+
					"alert('添加成功！');"+
					"window.location.href='"+request.getContextPath()+"/NoticeServlet?action=findnotice&curpage=1';"+
				"</script>"
			);
		}
		/// 类别名称已存在
		else if(result==-1) {
			response.getWriter().write(
				"<script>"+
					"alert('该公告已存在！');"+
					"window.location.href='"+request.getContextPath()+"/admin/notice_add.jsp';"+
				"</script>"
			);
		}
		/// 6.根据结果0，修改失败，提示“修改失败！”，则重新修改
		else if(result==0){
			response.getWriter().write(
				"<script>"+
					"alert('添加失败！');"+
					"window.location.href='"+request.getContextPath()+"/admin/notice_add.jsp';"+
				"</script>"
			);
		}
	}
	
	/**
	 * 
	* @Title: deleteNotice
	* @Description: 删除公告
	* @return void
	* @throws
	 */
	protected void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String cur = request.getParameter("cur");
		NoticePo notice = new NoticePo();
		notice.setId(id);
		int result = ns.deleteNotice(notice);
		/// 删除成功
		if(result==1) {
			response.getWriter().write(
				"<script>"+
					"alert('删除成功！');"+
					"window.location.href='"+request.getContextPath()+"/NoticeServlet?action=findnotice&curpage="+cur+"';"+
				"</script>"
			);
		}
		/// 删除失败
		else if(result==0){
			response.getWriter().write(
				"<script>"+
					"alert('删除失败！');"+
					"window.location.href='"+request.getContextPath()+"/NoticeServlet?action=findnotice&curpage="+cur+"';"+
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
		if(action.equals("findnotice")) {
			findNotice(request,response);
		}
		else if(action.equals("addnotice")) {
			addNotice(request, response);
		}
		else if(action.equals("deletenotice")) {
			deleteNotice(request, response);
		}
	}

}
