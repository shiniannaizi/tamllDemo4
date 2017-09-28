<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${path}/DataTables/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<div id="common_head">
	<div id="line1">
		<div id="content1">
			<c:if test="${!empty USER_CONTEXT.user_Name}">
				<a href="<%=basePath %>/userinfo/${USER_CONTEXT.user_Id}">${USER_CONTEXT.user_Name}</a>
                ,&nbsp;&nbsp;欢迎您的到来,<shiro:hasAnyRoles name="superadmin,admin">管理员</shiro:hasAnyRoles>
				<a href="<c:url value="/doLogout"/>">&nbsp;&nbsp;退出</a>
			</c:if>
            <c:if test="${empty USER_CONTEXT.user_Name}">
                <a href="<%=path %>/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="<%=path %>/regist">注册</a>
            </c:if>
		</div>
	</div>
	<div id="line2">
		<img id="logo" src="<%=request.getContextPath() %>/DataTables/img/head/logo.jpg"/>
		<input type="text" name=""/>
		<input type="button" value="搜 索"/>
		<span id="goto">
			<a id="goto_order" href="/myorderlist">我的订单</a>
			<a id="goto_cart" href="/mycart">我的购物车</a>
			<a id="goto_review" href="/myprodreview">我的留言</a>
		</span>
		<img id="erwm" src="<%=request.getContextPath() %>/DataTables/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="#">首页</a></li>
				<li><a href="/productlist">全部商品</a></li>
				<li><a href="#">手机数码</a></li>
				<li><a href="#">电脑平板</a></li>
				<li><a href="#">家用电器</a></li>
				<li><a href="#">汽车用品</a></li>
				<li><a href="#">食品饮料</a></li>
				<li><a href="#">图书杂志</a></li>
				<li><a href="#">服装服饰</a></li>
				<li><a href="#">理财产品</a></li>
			</ul>
		</div>
	</div>
</div>
