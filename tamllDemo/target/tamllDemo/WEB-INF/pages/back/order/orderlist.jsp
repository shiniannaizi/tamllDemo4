<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/18
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>订单列表</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link href="${context}/DataTables/css/orderList.css" rel="stylesheet" type="text/css">
</head>
<body STYLE="background-image: none;font-size: 15px;margin-left: 220px">
<h1>订单列表</h1>
<a href="<c:url value="/backend/pageorderlist/${0}/${fn:length(orderlist)}"/>">分页查询</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="/backend/pageorderlist/${-3}/${fn:length(orderlist)}"/>">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="/backend/pageorderlist/${3}/${fn:length(orderlist)}"/>">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="/backend/orderliststatus/${0}"/>">未支付</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="/backend/orderliststatus/${1}"/>">未发货</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="/backend/orderliststatus/${2}"/>">未收货</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="/backend/orderliststatus/${3}"/>">已完成</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="/backend/dateorderlist/${3}"/>">近三天</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="/backend/dateorderlist/${7}"/>">近一周</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
            <div>订单状态：
            <c:if test="${order.order_Status==0 }">
                <font color="red">未支付</font><br />
            </c:if>
            <c:if test="${order.order_Status==1 }">
                <font color="blue">已支付</font>&nbsp;&nbsp;&nbsp;
                支付时间：<fmt:formatDate value="${order.order_Pay_Date}"
                                     type="both" dateStyle="medium" timeStyle="medium"/>&nbsp;&nbsp;&nbsp;
                <a href="<c:url value="/backend/updateorderlist/${order.order_Number}/${2}"/>">发货</a><br />
            </c:if>
            <c:if test="${order.order_Status==2 }">
                <font color="blue">已发货</font>
                发货时间：<fmt:formatDate value="${order.order_Delivery_Date}"
                                type="both" dateStyle="medium" timeStyle="medium"/>
                发货人：${order.order_Post_Name}
            </c:if>
            <c:if test="${order.order_Status==3 }">
                <font color="blue">已完成</font>&nbsp;&nbsp;&nbsp;
                收货时间：<fmt:formatDate value="${order.order_Confim_Date}"
                                     type="both" dateStyle="medium" timeStyle="medium"/>&nbsp;&nbsp;&nbsp;
                <a href="<c:url value="/backend/orderinfo/${order.order_Number}"/>">订单详情</a><br />
            </c:if>
            </div>
            收货地址：${order.order_Recive_Info}<br/>
        </dd>
    </dl>

    <table width="1200" border="0" cellpadding="0"
           cellspacing="1" style="background:#d8d8d8;color:#333333">
        <tr>
            <th width="276" align="center" valign="middle" bgcolor="#f3f3f3">商品ID</th>
            <th width="247" align="center" valign="middle" bgcolor="#f3f3f3">商品名称</th>
            <th width="231" align="center" valign="middle" bgcolor="#f3f3f3">商品单价</th>
            <th width="214" align="center" valign="middle" bgcolor="#f3f3f3">购买数量</th>
            <th width="232" align="center" valign="middle" bgcolor="#f3f3f3">小计</th>
        </tr>
        <c:forEach items="${order.orderItems }" var="orderitem">
            <tr>
                <td align="center" valign="middle" bgcolor="#FFFFFF">${orderitem.product.product_Id }</td>
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
</body>
</html>
