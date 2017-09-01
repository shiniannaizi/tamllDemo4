<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/28
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>收货信息详情</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/DataTables/css/recive.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/DataTables/js/jquery-1.4.2.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/DataTables/js/reciveinfo.js">
    </script>
    <style type="text/css">
        td{
            font-size: 18px;
        }
    </style>
</head>
<body>
<h1>收货信息详情</h1>
<table border="1px">
    <input type="hidden" id="err" value="<%= request.getAttribute("errMsg")%>" />
    <tr id="trerr">
        <td class="sub_td" colspan="5">
            <span style="color: #d91210;text-align: center" id="errspan">
            </span>
        </td>
    </tr>
    <tr>
        <td class="sub_td">收货人姓名</td>
        <td class="sub_td">收货人手机号</td>
        <td class="sub_td">收货地址</td>
        <td class="sub_td">详细地址</td>
        <td class="sub_td">操作</td>
    </tr>
    <c:forEach var="recive" items="${recives}">
        <tr>
            <td class="sub_td">${recive.user_Recive_Name}</td>
            <td class="sub_td">${recive.user_Recive_Phone}</td>
            <td class="sub_td">${recive.user_Recive_Address}</td>
            <td class="sub_td">${recive.user_Recive_Detail_Address}</td>
            <td class="sub_td">
                <a href="<c:url value="/updaterecive/${recive.user_Recive_Id}"/>">修改</a>
                <a href="<c:url value="/deleterecive/${USER_CONTEXT.user_Id}/${recive.user_Recive_Id}"/>">删除</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td class="sub_td" colspan="5" class="tds">
            <input type="submit" value="新增收货信息" onclick="return a()"/>
            <input type="hidden" name="userId" id="userId" value="${USER_CONTEXT.user_Id}" />
            <span id="relist"></span>
        </td>
    </tr>
</table>
</body>
</html>
