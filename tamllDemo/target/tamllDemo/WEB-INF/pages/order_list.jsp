<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/12
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>我的订单</title>
    <link href="${context}/DataTables/css/orderList.css" rel="stylesheet" type="text/css">
</head>
<body style="text-align:center;">
<%@include file="/_head.jsp" %>
<c:forEach items="${orderlist }" var="order">
    <dl class="Order_information">
        <dt>
            <h3>订单信息</h3>
        </dt>
        <dd>
            订单编号：${order.order_Number}<br />
            下单时间：<fmt:formatDate value="${order.order_Create_Date}"
                                 type="both" dateStyle="medium" timeStyle="medium"/><br />
            订单金额：${order.order_Total}元<br />
            支付状态：
            <c:if test="${order.order_Status==0 }">
                <font color="red">未支付</font>&nbsp;&nbsp;&nbsp;
                <a href="/deleteorder/${order.order_Number}">
                    <img src="${context }/DataTables/img/orderList/sc.jpg" width="69" height="19"></a>
                <a href="/paypage/${order.order_Number}/${order.order_Total}">
                    <img src="${context }/DataTables/img/orderList/zx.jpg" width="69" height="19"></a><br />
            </c:if>
            <c:if test="${order.order_Status==1 }">
                <font color="blue">已支付</font><br/>
            </c:if>
            收货地址：${order.order_Recive_Info}<br/>
            支付方式：在线支付
        </dd>
    </dl>

    <table width="1200" border="0" cellpadding="0"
           cellspacing="1" style="background:#d8d8d8;color:#333333">
        <tr>
            <th width="276" height="30" align="center" valign="middle" bgcolor="#f3f3f3">商品图片</th>
            <th width="247" align="center" valign="middle" bgcolor="#f3f3f3">商品名称</th>
            <th width="231" align="center" valign="middle" bgcolor="#f3f3f3">商品单价</th>
            <th width="214" align="center" valign="middle" bgcolor="#f3f3f3">购买数量</th>
            <th width="232" align="center" valign="middle" bgcolor="#f3f3f3">小计</th>
        </tr>
        <c:forEach items="${order.orderItems }" var="orderitem">
            <tr>
                <td align="center" valign="middle" bgcolor="#FFFFFF">
                    <img src="<c:url value="${context}/uploads/${orderitem.product.product_First_Image}"/>"
                         width="90" height="105">
                </td>
                <td align="center" valign="middle" bgcolor="#FFFFFF">${orderitem.product.product_Name }</td>
                <td align="center" valign="middle" bgcolor="#FFFFFF">${orderitem.product.product_Orignal_Price}元</td>
                <td align="center" valign="middle" bgcolor="#FFFFFF">${orderitem.orderItem_Product_Number }件</td>
                <td align="center" valign="middle" bgcolor="#FFFFFF">
                        ${orderitem.product.product_Orignal_Price*orderitem.orderItem_Product_Number}元
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="Order_price">${order.order_Total }元</div>
</c:forEach>
<%@include file="/_foot.jsp" %>
</body>
</html>
