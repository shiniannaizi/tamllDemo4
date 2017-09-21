<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/31
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <title>商品列表</title>
    <link href="${context}/DataTables/css/prodList.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="/_head.jsp"%>
<div id="content">
    <div id="search_div">
        <form method="post" action="/productlist">
            <span class="input_span">商品名：<input type="text" name="name" value="${name}"/></span>
            <span class="input_span">商品种类：<input type="text" name="category" value="${category }"/></span>
            <span class="input_span">商品价格区间：<input type="text" name="minprice" value="${minprice}"/> -
				<input type="text" name="maxprice" value="${maxprice }"/></span>
            <input type="submit" value="查询">
        </form>
    </div>
    <div id="prod_content">
        <c:forEach items="${productlist}" var="prod">
            <div id="prod_div">
                <a href="/productinfo/${prod.product_Id}">
                    <img style="width: 100%;height: 221px"
                         src="<c:url value="${context}/uploads/${prod.product_First_Image}"/>"></img>
                </a>
                <div id="prod_name_div">
                    <a href="/productinfo/${prod.product_Id}">
                            ${prod.product_Name}
                    </a>
                </div>
                <div id="prod_price_div">
                    ￥${prod.product_Orignal_Price}元
                </div>
                <div>
                    <div id="gotocart_div">
                        <a href="/addcart/${prod.product_Id}">加入购物车</a>
                    </div>
                    <div id="say_div">
                        库存：${prod.product_Stock }
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div style="clear: both"></div>
</div>
<%@include file="/_foot.jsp"%>
</body>
</html>

