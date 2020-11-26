<%@page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/qiantai/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>-ZW私房菜</title>
<meta content="" name=keywords />
<meta content="" name=description />
<link href="css/dingcanall.css" rel="stylesheet" type="text/css" />
<link href="css/newslist_time2.css" rel="stylesheet" type="text/css" />	
<link href="css/dingcanche.css" rel="stylesheet" type="text/css" />	
<link href="css/dingcanweekmenu.css" rel="stylesheet" type="text/css" />									
</head>

<body style='background: transparent'>
	<c:if test="${empty page}">
		<jsp:forward page="../IndexServlet?action=allInfo"></jsp:forward>
	</c:if>
	<table width="900" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td align="left" valign="top"><jsp:include flush="fasle"
					page="top.jsp" /></td>
		</tr>
		<tr>
			<td height="30"></td>

		</tr>

		<tr>
			<td align="left" valign="top"><table width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tr>
						<td width="59%" align="left" valign="top"><div id='pdv_3606'
								class='pdv_class' title=''
								style='width: 648px; top: 0px; left: 0px; z-index: 12'>
								<div id='spdv_3606' class='pdv_content'
									style='overflow: visible; width: 100%;'>
									<div class="pdv_border"
										style="margin: 0; padding: 0; height: 100%; border: 0px solid; background:;">
										<div style="padding: 0px">
											<div id="dingcanall2">

												<div style="margin-top: 0px; padding: px;">

													<div id="mm_01" class="dingcanall_connow">
														<table>
														<c:forEach items="${page.list}" var="menu">
															<tr style="float:left; width: 320px;height: 200px">
																<td style="margin-top: 10px;">
																	<div>
																		<table>
																			<tr>
																				<td rowspan="5" class="bookPic"><img
																					src="${pageContext.request.contextPath}/${menu.imgpath}"
																					style="border: 1px solid #300;" width="70%" /></td>
																				<td><span>菜名:</span></td>
																				<td><span><strong>${menu.name}</strong></span></td>
																			</tr>
																			<tr>
																				<td><span>市场价格:</span></td>
																				<td><span>${menu.price}</span></td>
																			</tr>
																			<tr>
																				<td><span>会员价格:</span></td>
																				<td><span><strong style="color: red;">${menu.price1}</strong></span></td>
																			</tr>
																			<tr>
																				<td><span>配料:</span></td>
																				<td><span>${menu.burden}</span></td>
																			</tr>
																			<tr>
																				<td><span>菜品类型:</span></td>
																				<td><span>${menu.typename}</span></td>
																			</tr>
																			<tr>
																				<td colspan="2" style="height: 40px;"><a
																					href="${pageContext.request.contextPath}/ShoppingCartServlet?action=add&menuid=${menu.id}&curpage=${page.curPage}">加入餐车</a></td>
																				<td></td>
																			</tr>
																		</table>
																	</div>
																</td>						
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
																    	<a href="${pageContext.request.contextPath }/IndexServlet?action=allInfo&curpage=1">[首页]</a>
																    </c:otherwise>
															   	</c:choose> 
															    
															    <%--如果当前就是最后一页，尾页不能点击--%>
															    <c:choose>
															    	<c:when test="${requestScope.page.curPage eq requestScope.page.totalPage }">
															    		<span style="color: gray; front-size: 12px;">[尾页]</span>
															    	</c:when>
																    <c:otherwise>
																    	<a href="${pageContext.request.contextPath }/IndexServlet?action=allInfo&curpage=${requestScope.page.totalPage }">[尾页]</a>
																    </c:otherwise>
															    </c:choose>
															    
															    <c:choose>
															    	<c:when test="${requestScope.page.curPage eq 1 }">
															    		<span style="color: gray; front-size: 12px;">[上一页]</span>
															    	</c:when>
																    <c:otherwise>
																    	<a href="${pageContext.request.contextPath }/IndexServlet?action=allInfo&curpage=${requestScope.page.curPage - 1 }">[上一页]</a>
																    </c:otherwise>
																</c:choose>
															    
																<c:choose>
															    	<c:when test="${requestScope.page.curPage eq requestScope.page.totalPage }">
															    		<span style="color: gray; front-size: 12px;">[下一页]</span>
															    	</c:when>
																    <c:otherwise>
																    	<a href="${pageContext.request.contextPath }/IndexServlet?action=allInfo&curpage=${requestScope.page.curPage + 1 }">[下一页]</a>
																    </c:otherwise>
																</c:choose>
															</td>
														</tr>
													</table>
												</div>
											</div>
										</div>
									</div>

										<div id="dingcanall_bottom_left">&nbsp;</div>
										<div id="dingcanall_bottom_right">&nbsp;</div>
										<input type="hidden" name="picw" id="picw" value="150" /> <input
											type="hidden" name="pich" id="pich" value="140" /> <input
											type="hidden" name="fittype" id="fittype" value="auto" />
									</div>
								</div>
							</div>
							</div></td>
						<td width="41%" align="right" valign="top"><table width="243"
								border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td></td>
								</tr>
								<tr>
									<td valign="top"><div id='pdv_' class='pdv_class'
											title='网站公告'
											style='width: 243px; top: 0px; left: 0px; z-index: 3'>
											<div id='spdv_3603' class='pdv_content'
												style='overflow: hidden; width: 100%; height: 100%'>
												<div class="pdv_border"
													style="border: 0px; height: 100%; padding: 0; margin: 0; background: url(base/border/640/images/left.jpg) repeat-y">
													<div
														style="height: 100%; background: url(images/right.jpg) right repeat-y">
														<div
															style="height: 43px; background: url(images/bg.jpg) 0px 0px no-repeat">
															<div
																style="float: left; font: bold 16px/43px 'Microsoft YaHei', 'SimSun', Arial, Sans-Serif; text-align: left; padding-left: 50px; color: #feab43;">
																餐厅公告</div>
															<div
																style="float: right; width: 60px; height: 43px; text-align: right; background: url(images/bg.jpg) -840px 0px no-repeat">
																<a href="news/class/"
																	style="font: 12px/43px simsun; color: #505050; margin-right: 12px; display: inline">更多&gt;&gt;</a>
															</div>
														</div>
														<div style="margin: 0px 3px; padding: 10px;" align="left">
															

															<ul class="newslist_time2">
																<c:forEach items="${noticeList}" var="item">
																<li class="newslist_time2"><div class="time">${item.times.substring(0,10)}</div>
																	<a href="#" class="newslist_time2">${item.name}</a>
																</li>
																</c:forEach>
															</ul>


														</div>
													</div>
												</div>
												<div
													style="margin-top: -10px; height: 10px; line-height: 10px; background: url(images/bg.jpg) 0px -220px no-repeat">&nbsp;</div>
												<div
													style="float: right; margin-top: -10px; width: 10px; height: 10px; line-height: 10px; background: url(images/bg.jpg) -890px -220px no-repeat">&nbsp;</div>
											</div>
										</div></td>
								</tr>
								<tr>
									<td height="10">&nbsp;</td>
								</tr>
								<tr>
									<td valign="top">
										<div id='pdv_3614' class='pdv_class' title='我的餐车'
											style='width: 243px; top: 0px; left: 0px; z-index: 2'>
											<div id='spdv_3614' class='pdv_content'
												style='overflow: visible; width: 100%;'>
												<div class="pdv_border"
													style="margin: 0; padding: 0; height: 100%; border: 0px solid; background:;">
													<div
														style="height: 25px; margin: 1px; display: none; background:;">
														<div
															style="float: left; margin-left: 12px; line-height: 25px; font-weight: bold; color:">
															我的餐车</div>
														<div
															style="float: right; margin-right: 10px; display: none">
															<a href="-1" style="line-height: 25px; color:">更多</a>
														</div>
													</div>
													<div style="padding: 0px">
														<div id="dingcanche">
															<div id="dingcanche2">
																<div id="dingcanche_top">
																	<div id="dingcanche_top_left">我的餐车</div>
																	<div id="dingcanche_top_right">&nbsp;</div>
																</div>
																<div id="dcinfo" style="margin: 0px 3px 1px 3px;"></div>
																<table width="100%" border="0" cellspacing="0"
																	style="background: #fef0d3; font-size: 12px;">
																	<tr>
																		<td align="center">菜单名称</td>
																		<td align="center">单价</td>
																		<td align="center">数量</td>
																		<td align="center">操作</td>
																	</tr>
																	<c:forEach items="${sessionScope.shoppingCart}" var="cart">
																	<tr>
																		<td align="center">${cart.name }</td>
																		<td align="center">${cart.price }</td>
																		<td align="center">${cart.sum }</td>
																		<td align="center"><a
																			href="${pageContext.request.contextPath }/ShoppingCartServlet?action=delcart&menuid=${cart.menuid}&curpage=${requestScope.page.curPage }">取消</a></td>
																	</tr>
																<c:set var="count" value="${count+cart.sum }"></c:set>
																<c:set var="price1" value="${price1+cart.sum*cart.price }"></c:set>
                                                                </c:forEach>
																</table>
																<div style="height: 24px; margin: 5px 3px 1px 3px;">
																	<div
																		style="float: left; line-height: 24px; padding-left: 25px;">小&nbsp;&nbsp;计：</div>
																	<div
																		style="float: right; line-height: 24px; padding-right: 15px;">
																		<font id="allnums" style="color: #ff0000;">${count}</font>份
																	</div>
																	<div
																		style="float: right; line-height: 24px; padding-right: 30px;">
																		<font id="cpprice" style="color: #ff0000;">${price1}</font>元
																	</div>
																</div>
																<div style="height: 30px; margin: 5px 3px 1px 3px;">
																	<table width="100%" border="0" cellspacing="0">
																		<tr>
																			<td align="center" width="40%"></td>
																			<td align="center" width="40%"><a
																				href="${pageContext.request.contextPath }/UsersOrderServlet?action=addOrder"><img
																					src="images/canche_submit.gif" border="0" /></a></td>
																			<td align="center" width="40%"><a
																				href="${pageContext.request.contextPath }/ShoppingCartServlet?action=delall&curpage=${requestScope.page.curPage }"><img
																					src="images/quxiao2.gif" border="0" /></a></td>
																		</tr>
																	</table>

																</div>
															</div>
														</div>
													</div>
													<div id="dingcanche_bottom_left">&nbsp;</div>
													<div id="dingcanche_bottom_right">&nbsp;</div>
													<input type="hidden" name="modnums_b" id="modnums_b"
														value="" />
													<script>
														$("div.cpline_d:even")
																.addClass(
																		"cpline_s");
													</script>
												</div>
											</div>
										</div>


									</td>
								</tr>
								<tr>
									<td height="10">&nbsp;</td>
								</tr>
								<tr>
									<td valign="top"><div id='pdv_3613' class='pdv_class'
											title='本周菜单'
											style='width: 243px; top: 0px; left: 0px; z-index: 5'>
											<div id='spdv_3613' class='pdv_content'
												style='overflow: hidden; width: 100%; height: 100%'>
												<div class="pdv_border"
													style="margin: 0; padding: 0; height: 100%; border: 0px solid; background:;">
													<div
														style="height: 25px; margin: 1px; display: none; background:;">
														<div
															style="float: left; margin-left: 12px; line-height: 25px; font-weight: bold; color:">
															本周菜单</div>
														<div
															style="float: right; margin-right: 10px; display: none">
															<a href="-1" style="line-height: 25px; color:">更多</a>
														</div>
													</div>
													<div style="padding: 0px">
														
														<div id="dingcanweekmenu">
															<div id="dingcanweekmenu2">
																<div id="dingcanweekmenu_top">
																	<div id="dingcanweekmenu_top_left">销售排行榜</div>
																	<div id="dingcanweekmenu_top_right">&nbsp;</div>
																</div>
																<div style="padding: px;">
																	<div class="dingcanweekmenuinfo" align="left">
																		<link href="css/newslist_time2.css" rel="stylesheet"
																			type="text/css" />
																		<c:forEach items="${rankList}" var="item">
																		<fmt:formatNumber var="sum" value="${item.sum}"/>
															            
																		<li class="newslist_time2"><div class="time">已销售${sum}次</div>
																			<a href="#" class="newslist_time2">${item.menuname}</a></li>
																		</c:forEach>
																	</div>
																</div>
															</div>
														</div>
														<!--<div id="dingcanweekmenu_bottom_left"></div>
                    <div id="dingcanweekmenu_bottom_right">&nbsp;</div>-->
													</div>
												</div>
												<!-- </div>-->
											</div></td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="10">&nbsp;</td>
		</tr>
		<tr>
			<td height="50" align="center" valign="middle"><jsp:include
					flush="fasle" page="copyright.jsp" /></td>
		</tr>

	</table>
</body>
</html>