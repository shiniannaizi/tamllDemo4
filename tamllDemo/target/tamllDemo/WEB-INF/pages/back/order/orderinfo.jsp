<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/21
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单信息详情</title>
    <style type="text/css">
        input{
            height: 36px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div>
    <table border="1px" width="50%" STYLE="margin: 0 auto">
        <tr>
            <td width="15%">订单编号:</td>
            <td>
                ${order.order_Number}
            </td>
        </tr>
        <tr>
            <td>订单生成日期:</td>
            <td>
                <fmt:formatDate value="${order.order_Create_Date}"
                                type="both" dateStyle="medium" timeStyle="medium"/>
            </td>
        </tr>
        <tr>
            <td>订单支付日期:</td>
            <td>
                <fmt:formatDate value="${order.order_Pay_Date}"
                                type="both" dateStyle="medium" timeStyle="medium"/>
            </td>
        </tr>
        <tr>
            <td>订单发货人:</td>
            <td>
                ${order.order_Post_Name}
            </td>
        </tr>
        <tr>
            <td>订单发货地址:</td>
            <td>
                ${order.order_Post_Address}
            </td>
        </tr>
        <tr>
            <td>发货时间:</td>
            <td>
                <fmt:formatDate value="${order.order_Delivery_Date}"
                                type="both" dateStyle="medium" timeStyle="medium"/>
            </td>
        </tr>
        <tr>
            <td>订单收货地址:</td>
            <td>
                ${order.order_Recive_Info}
            </td>
        </tr>
        <tr>
            <td>收货时间:</td>
            <td>
                <fmt:formatDate value="${order.order_Confim_Date}"
                                type="both" dateStyle="medium" timeStyle="medium"/>
            </td>
        </tr>
        <tr>
            <td>订单商品总数:</td>
            <td>
                ${order.order_Total_Number}
            </td>
        </tr>
        <tr>
            <td>订单金额:</td>
            <td>
                ${order.order_Total}
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                商品信息
            </td>
        </tr>
        <c:forEach items="${order.orderItems}" var="orderitem">
            <tr>
                <td>商品ID:</td>
                <td>
                    ${orderitem.product.product_Id}
                </td>
            </tr>
            <tr>
                <td>商品名称:</td>
                <td>
                    ${orderitem.product.product_Name}
                </td>
            </tr>
            <tr>
                <td>商品数量:</td>
                <td>
                    ${orderitem.orderItem_Product_Number}
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2" STYLE="text-align: center">
                <input type="button" value="删除订单" onclick="<c:url value="" /> "/>
                <input type="button" value="确认退款" onclick="<c:url value="" /> "/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
