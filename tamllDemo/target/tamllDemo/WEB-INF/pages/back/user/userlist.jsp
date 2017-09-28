<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/27
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
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
<table border="1px" align="center" style="margin: 0 auto">
    <input type="hidden" id="err" value="${errMsg}" />
    <tr id="trerr">
        <td class="sub_td" colspan="5">
            <span style="color: #d91210;text-align: center" id="errspan">
            </span>
        </td>
    </tr>
    <tr>
        <td class="sub_td" width="20%">用户名</td>
        <td class="sub_td" width="10%">性别</td>
        <td class="sub_td" width="30%">地址</td>
        <td class="sub_td" width="20%">邮箱</td>
        <td class="sub_td" width="20%">角色</td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td class="sub_td">${user.user_Name}</td>
            <td class="sub_td">${user.user_Sex}</td>
            <td class="sub_td">${user.user_Address}</td>
            <td class="sub_td">${user.user_Email}</td>
            <td class="sub_td">${user.role.role_Name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
