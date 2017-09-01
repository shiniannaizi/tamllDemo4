<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/31
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="DataTables/css/login.css"/>
    <script type="text/javascript" src="DataTables/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="DataTables/js/login.js"></script>
    <title>管理员登录</title>
</head>
<body>
<h1>管理员登录</h1>
<form action="${context}/backend/doLogin" method="POST">
    <table>
        <tr>
            <td colspan="2" style="text-align: center;color: red">
                <%= request.getAttribute("errMsg") == null ? "":request.getAttribute("errMsg") %>
            </td>
        </tr>
        <tr>
            <td class="tdx">用户名：</td>
            <td>
                <input type="text" name="adminname" id="adminname"/>
            </td>
        </tr>
        <tr>
            <td class="tdx">密&nbsp;&nbsp; 码：</td>
            <td>
                <input type="password" name="password" id="password"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:center">
                <input type="submit" value="登 陆"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
