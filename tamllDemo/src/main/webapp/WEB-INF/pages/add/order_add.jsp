<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/11
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>订单添加页面</title>
    <link href="${context}/DataTables/css/addOrder.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="/_head.jsp" %>
<div class="warp">
    <form name="form1" method="post" action="/addorder">
        <h3>增加订单</h3>
        <div id="forminfo">
            <span class="lf">收货地址：</span>
            <c:forEach items="${recives}" var="recive">
                <input type="radio" name="recive" id="recive"
                       value="${recive.user_Recive_Id}"/>${recive.user_Recive_Name}-${recive.user_Recive_Phone}-
                ${recive.user_Recive_Address}-${recive.user_Recive_Detail_Address}<br>
            </c:forEach>
            <br> 支付方式：<input name="" type="radio" value="" checked="checked">&nbsp;在线支付
        </div>
        <table width="1200" height="80" border="1" cellpadding="0" cellspacing="0" bordercolor="#d8d8d8">
            <tr>
                <th width="276">商品图片</th>
                <th width="247">商品名称</th>
                <th width="231">商品单价</th>
                <th width="214">购买数量</th>
                <th width="232">小计</th>
            </tr>
            <c:set var="money" value="0"/>
            <c:forEach items="${cart}" var="entry">
                <tr>
                    <td><img src="<c:url value="${context}/uploads/${entry.key.product_First_Image}"/>"
                             width="90" height="90" /></td>
                    <td>${entry.key.product_Name }</td>
                    <td>${entry.key.product_Orignal_Price }元</td>
                    <td>${entry.value }件</td>
                    <td>${entry.key.product_Orignal_Price*entry.value }元</td>
                </tr>
                <c:set var="money"
                       value="${money+entry.key.product_Orignal_Price*entry.value}"/>
            </c:forEach>
        </table>
        <div class="Order_price">总价：${money }元</div>
        <div class="add_orderbox">
            <input name="" type="submit" value="增加订单" class="add_order_but">
        </div>
    </form>
</div>
<%@include file="/_foot.jsp" %>
</body>
</html>
