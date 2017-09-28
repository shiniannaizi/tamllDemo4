<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/28
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String context = request.getContextPath();
    request.setAttribute("context",context);
%>
<html>
<head>
    <title>新增权限</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="${context}/DataTables/css/regist.css"/>
</head>
<body>
<form action="${context}/management/add/addrightinfo" method="POST">
    <h1>新增权限</h1>
    <table>
        <tr>
            <td colspan="2" style="text-align: center;color: red">
                <%= request.getAttribute("errMsg") == null ? "":request.getAttribute("errMsg") %>
            </td>
        </tr>
        <tr>
            <td class="tds">权限名：</td>
            <td>
                <input type="text" name="rightName" id="rightName" />
            </td>
        </tr>
        <tr>
            <td class="tds">权限描述：</td>
            <td>
                <input type="text" name="rightDescribe" id="rightDescribe" />
            </td>
        </tr>
        <tr>
            <td class="td_sub" colspan="2" class="tds">
                <input type="submit" value="新增权限" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>

