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
    <link href="<%=request.getContextPath()%>/DataTables/css/prodList.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="content">
    <div id="search_div">
        <form method="post" action="#">
            <span class="input_span">商品名：<input type="text" name="name"/></span>
            <span class="input_span">商品种类：<input type="text" name="category"/></span>
            <span class="input_span">商品价格区间：<input type="text" name="minprice"/>
                - <input type="text" name="maxprice"/></span>
            <input type="submit" value="查 询">
        </form>
    </div>
    <div id="prod_content">
        <c:forEach var="product" items="${productlist}">
        <div class="prod_div">
            <a href="/productinfo/${product.product_Id}">
                <img src="<c:url value="${context}/uploads/${product.product_First_Image}"/>"></img>
            </a>
            <div id="prod_name_div">
                    <a href="/productinfo/${product.product_Id}">
                            <p style="text-align: center;font-size: 12px;">
                                    ${product.product_Name}
                            </p>
                    </a>
            </div>
            <div id="prod_price_div">
                ￥${product.product_Orignal_Price}元
            </div>
            <div>
                <div id="gotocart_div">
                    <a href="#">加入购物车</a>
                </div>
                <div id="say_div">
                    133人评价
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

