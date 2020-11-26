<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
								<td class="line_table" align="center" colspan="8" height="20">
									<span class="left_bt2">类别信息列表</span>
								</td>
							</tr>
							<tr>
								<td class="line_table" align="center" width="30%"><span
									class="left_bt2">类别ID</span></td>
								<td class="line_table" align="center" width="30%"><span
									class="left_bt2">类别名称</span></td>
								<td class="line_table" align="center" width="20%"></td>
								<td class="line_table" align="center" width="20%"></td>
							</tr>
							<c:forEach items="${requestScope.page.list }" var="type">
								<tr>
									<td class="line_table" align="center" width="30%"><span
										class="left_txt">${type.id }</span></td>
									<td class="line_table" align="center" width="30%"><span
										class="left_txt">${type.name }</span></td>
									<td class="line_table" align="center" width="20%"><a
										href="${pageContext.request.contextPath}/TypesServlet?action=updatetype&step=a&id=${type.id }&cur=${requestScope.page.curPage }" target="main">修改</a></td>
									<td class="line_table" align="center" width="20%"><a
										href="${pageContext.request.contextPath}/TypesServlet?action=deletetype&id=${type.id }&cur=${requestScope.page.curPage }" target="main">删除</a></td>
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
								    	<a href="${pageContext.request.contextPath }/TypesServlet?action=findtypes&curpage=1">[首页]</a>
								    </c:otherwise>
							   	</c:choose> 
							    
							    <%--如果当前就是最后一页，尾页不能点击--%>
							    <c:choose>
							    	<c:when test="${requestScope.page.curPage eq requestScope.page.totalPage }">
							    		<span style="color: gray; front-size: 12px;">[尾页]</span>
							    	</c:when>
								    <c:otherwise>
								    	<a href="${pageContext.request.contextPath }/TypesServlet?action=findtypes&curpage=${requestScope.page.totalPage }">[尾页]</a>
								    </c:otherwise>
							    </c:choose>
							    
							    <c:choose>
							    	<c:when test="${requestScope.page.curPage eq 1 }">
							    		<span style="color: gray; front-size: 12px;">[上一页]</span>
							    	</c:when>
								    <c:otherwise>
								    	<a href="${pageContext.request.contextPath }/TypesServlet?action=findtypes&curpage=${requestScope.page.curPage - 1 }">[上一页]</a>
								    </c:otherwise>
								</c:choose>
							    
								<c:choose>
							    	<c:when test="${requestScope.page.curPage eq requestScope.page.totalPage }">
							    		<span style="color: gray; front-size: 12px;">[下一页]</span>
							    	</c:when>
								    <c:otherwise>
								    	<a href="${pageContext.request.contextPath }/TypesServlet?action=findtypes&curpage=${requestScope.page.curPage + 1 }">[下一页]</a>
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
