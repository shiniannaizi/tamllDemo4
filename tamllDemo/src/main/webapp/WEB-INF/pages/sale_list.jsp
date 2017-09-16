<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/14
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <style type="text/css">
        body {
            background: #F5F5F5;
            text-align: center;
        }

        table {
            text-align: center;
            margin: 0px auto;
        }

        th {
            background-color: silver;
        }
    </style>
    <title>销售榜单</title>
</head>
<body>
<h1>销售榜单</h1>
<a href="/backend/downsalelist">销售榜单下载</a>
<hr>
<table bordercolor="black" border="1" width="95%" cellspacing="0px" cellpadding="5px">
    <tr>
        <th>商品id</th>
        <th>商品名称</th>
        <th>销售总量</th>
    </tr>
    <c:forEach items="${map}" var="map">
        <tr>
            <td>${map.key.product_Id }</td>
            <td>${map.key.product_Name }</td>
            <td>${map.value }</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
