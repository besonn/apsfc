<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/">
<link href="images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
</head>

<body>

	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">


				<div align="center">
					<table id="table2" class="line_table"
						style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
						cellPadding="0">
						<tbody style="margin: 0; padding: 0">
							<tr>
								<td class="line_table" align="center" colspan="12"><span
									class="left_bt2">销售订单查询结果信息列表</span></td>
							</tr>
							<tr>
								<td class="line_table" align="center"><span
									class="left_bt2">ID</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">真实姓名</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">联系方式</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">家庭住址</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">菜品名称</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购数量</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">单价(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">合计(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购时间</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">是否派送</span></td>
								<td class="line_table" align="center" colspan="2"><span
									class="left_bt2">确认订单</span></td>
							</tr>
							
							<c:forEach items="${requestScope.page.list }" var="order">
		                        <tr>
									<td class="line_table" align="center"><span
										class="left_txt">${order.userid }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${order.realname }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${order.phone }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${order.address }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${order.name }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${order.menusum }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${order.price }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${order.price*order.menusum }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${order.times }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">
											<c:choose>
												<c:when test="${order.delivery eq 1 }">是</c:when>
												<c:otherwise>否</c:otherwise>
											</c:choose>
										</span></td>
		
									<td class="line_table" align="center"><a
										href="${pageContext.request.contextPath }/OrdersServlet?action=de&id=${order.userid }&curpage=${requestScope.page.curPage }">确认</a></td>
									<td class="line_table" align="center"><a
										href="${pageContext.request.contextPath }/OrdersServlet?action=delete&id=${order.userid }&curpage=${requestScope.page.curPage }">取消</a></td>
								</tr>
							</c:forEach>
							
							
					<tr>
						<td class="line_table" align="center" colspan="11" height="20">
						<span class="left_bt2">第${requestScope.page.curPage }页
								&nbsp;&nbsp;共${requestScope.page.totalPage }页
						</span>&nbsp;&nbsp;
						
							<%--如果当前是第一页，首页不能点击--%> 
						    <c:choose>
						    	<c:when test="${requestScope.page.curPage eq 1 }">
						    		<span style="color: gray; front-size: 12px;">[首页]</span>
						    	</c:when>
							    <c:otherwise>
							    	<a href="${pageContext.request.contextPath }/OrdersServlet?action=findorders&curpage=1">[首页]</a>
							    </c:otherwise>
						   	</c:choose> 
						    
						    <%--如果当前就是最后一页，尾页不能点击--%>
						    <c:choose>
						    	<c:when test="${requestScope.page.curPage eq requestScope.page.totalPage }">
						    		<span style="color: gray; front-size: 12px;">[尾页]</span>
						    	</c:when>
							    <c:otherwise>
							    	<a href="${pageContext.request.contextPath }/OrdersServlet?action=findorders&curpage=${requestScope.page.totalPage }">[尾页]</a>
							    </c:otherwise>
						    </c:choose>
						    
						    <c:choose>
						    	<c:when test="${requestScope.page.curPage eq 1 }">
						    		<span style="color: gray; front-size: 12px;">[上一页]</span>
						    	</c:when>
							    <c:otherwise>
							    	<a href="${pageContext.request.contextPath }/OrdersServlet?action=findorders&curpage=${requestScope.page.curPage - 1 }">[上一页]</a>
							    </c:otherwise>
							</c:choose>
						    
							<c:choose>
						    	<c:when test="${requestScope.page.curPage eq requestScope.page.totalPage }">
						    		<span style="color: gray; front-size: 12px;">[下一页]</span>
						    	</c:when>
							    <c:otherwise>
							    	<a href="${pageContext.request.contextPath }/OrdersServlet?action=findorders&curpage=${requestScope.page.curPage + 1 }">[下一页]</a>
							    </c:otherwise>
							</c:choose>
						</td>
					</tr>
							
					</table>
				</div>
				
				
				
				
			</td>
		</tr>
	</table>
</body>
</html>