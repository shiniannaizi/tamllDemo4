<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/22
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String context = request.getContextPath();
    request.setAttribute("context",context);
    //获取请求中的所有Cookie信息
    Cookie[] cookies = request.getCookies();
    Cookie remnameCookie = null;
    Cookie aotologinCookie = null;
    if (cookies!=null){
        for (Cookie c : cookies){
            if ("remname".equals(c.getName())){
                remnameCookie = c;
            }
            if ("autologin".equals(c.getName())){
                aotologinCookie = c;
            }
        }
    }
    String username = null;
    if (remnameCookie != null){
        username = remnameCookie.getValue();
        //对用户名进行URL解码
        username = URLDecoder.decode(username,"utf-8");
    }

%>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="DataTables/css/login.css"/>
    <script type="text/javascript" src="DataTables/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="DataTables/js/login.js"></script>
    <script type="text/javascript">
        $(function () {
            var name = document.getElementById("username").value.trim();
            if (name!=null && "" != name){
                checkUserName();
            }
        })
    </script>
    <title>EasyMall欢迎您登陆</title>
</head>
<body>
<h1>欢迎登陆EasyMall</h1>
<form action="${context}/doLogin" method="POST" onsubmit="return checkUserName()">
    <table>
        <tr>
            <td colspan="2" style="text-align: center;color: red">
                <%= request.getAttribute("errMsg") == null ? "":request.getAttribute("errMsg") %>
            </td>
        </tr>
        <tr>
            <td class="tdx">用户名：</td>
            <td>
                <input type="text" name="username" id="username" onblur="checkUserName()"
                       value="<%= username == null ? "":username%>"/>
                <span id="usernameid"></span>
            </td>
        </tr>
        <tr>
            <td class="tdx">密&nbsp;&nbsp; 码：</td>
            <td>
                <input type="password" name="password" id="password"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="checkbox" name="remname" value="true"
                        <%=remnameCookie == null ? "":"checked='checked'"%>/>记住用户名
                <input type="checkbox" name="autologin" value="true"
                        <%=aotologinCookie == null ? "":"checked='checked'"%>/>30天内自动登陆
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
