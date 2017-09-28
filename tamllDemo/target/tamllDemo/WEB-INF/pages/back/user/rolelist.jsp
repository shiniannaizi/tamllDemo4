<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/28
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色列表</title>
    <style type="text/css">
        .sub_td{
            text-align: center;
        }
    </style>
    <script type="text/javascript" src="${context}/DataTables/js/jquery-3.2.1.js"></script>
    <script>
        $(function () {
            var errMsg = document.getElementById("err").value.trim();
            var errspan = document.getElementById("errspan");
            if (errMsg == "null"){
                document.getElementById("trerr").style.display = "none";
            }else {
                errspan.innerHTML = errMsg;
            }
        });
    </script>
</head>
<body>
<div style="margin-left: 20%"><shiro:hasRole name="superadmin"><a href="${context}/management/add/addrole">添加角色</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${context}/management/add/addright">添加权限</a></shiro:hasRole></div>
<c:forEach var="role" items="${roles}">
<div align="center">角色名：${role.role_Name}</div>
<table border="1px" align="center" style="margin: 0 auto">
    <input type="hidden" id="err" value="${errMsg}" />
    <tr id="trerr">
        <td class="sub_td" colspan="5">
            <span style="color: #d91210;text-align: center" id="errspan">
            </span>
        </td>
    </tr>
    <tr>
        <td class="sub_td" width="100px">角色权限</td>
        <td class="sub_td" width="200px">权限描述</td>
    </tr>
    <c:forEach var="right" items="${role.rights}">
        <tr>
            <td class="sub_td">${right.right_Name}</td>
            <td class="sub_td">${right.right_Describe}</td>
        </tr>
    </c:forEach>
</table>
</c:forEach>
</body>
</html>
