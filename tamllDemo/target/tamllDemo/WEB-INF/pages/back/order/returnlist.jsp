<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/21
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>退货通知</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link href="${context}/DataTables/css/orderList.css" rel="stylesheet" type="text/css">
</head>
<body STYLE="background-image: none;font-size: 15px;margin-left: 220px">
<h1>订单列表</h1>
<c:forEach items="${orderlist }" var="order">
    <dl class="Order_information">
        <dt>
            <h3>订单信息</h3>
        </dt>
        <dd>
            订单编号：<a href="<c:url value="/backend/orderinfo/${order.order_Number}"/> ">
                ${order.order_Number}</a><br />
            下单时间：<fmt:formatDate value="${order.order_Create_Date}"
                                 type="both" dateStyle="medium" timeStyle="medium"/><br />
            订单金额：${order.order_Total}元<br />
            收货地址：${order.order_Recive_Info}<br/>
        </dd>
    </dl>
</c:forEach>
</body>
</html>
