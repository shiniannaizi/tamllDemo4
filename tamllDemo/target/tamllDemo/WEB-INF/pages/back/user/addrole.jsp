<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/28
  Time: 20:27
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
    <title>新增角色</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="${context}/DataTables/css/regist.css"/>
</head>
<body>
<form action="${context}/management/add/addroleinfo" method="POST">
    <h1>新增角色</h1>
    <table>
        <tr>
            <td colspan="2" style="text-align: center;color: red">
                <%= request.getAttribute("errMsg") == null ? "":request.getAttribute("errMsg") %>
            </td>
        </tr>
        <tr>
            <td class="tds">角色名：</td>
            <td>
                <input type="text" name="roleName" id="roleName" />
            </td>
        </tr>
        <tr>
            <td class="tds">权限选择：</td>
            <td>
                <select name="rightName">
                    <c:forEach items="${rights}" var="right">
                        <option value="${right.right_Name}">${right.right_Name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="td_sub" colspan="2" class="tds">
                <input type="submit" value="新增角色" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
