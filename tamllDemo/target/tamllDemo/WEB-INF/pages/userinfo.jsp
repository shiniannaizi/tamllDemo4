<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/24
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>个人信息详情</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/DataTables/css/userinfo.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/DataTables/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        $(function () {
            var errMsg = document.getElementById("err").value.trim();
            var errspan = document.getElementById("errspan");
            if (errMsg == "null"){
                document.getElementById("trerr").style.display = "none";
            }else {
                errspan.innerHTML = errMsg;
            }
        })
        function a() {
            window.location.href = "<%=request.getContextPath() %>/changepwd";
        }
        function b() {
            window.location.href = "<%=request.getContextPath() %>/reciveinfo/${user.user_Id}";
        }
        function c() {
            window.location.href = "<%=request.getContextPath() %>/updateuser/${user.user_Id}";
        }
    </script>
</head>
<body>
<h1>个人信息详情</h1>
<table>
    <input type="hidden" id="err" value="<%= request.getAttribute("errMsg")%>" />
    <tr id="trerr">
        <td class="sub_td" colspan="2">
            <span style="color: #d91210;text-align: center" id="errspan">
            </span>
        </td>
    </tr>
    <tr>
        <td class="tds">用户名：</td>
        <td>
            ${user.user_Name}
        </td>
    </tr>
    <tr>
        <td class="tds">性别：</td>
        <td>
            ${user.user_Sex}
        </td>
    </tr>
    <tr>
        <td class="tds">邮箱：</td>
        <td>
            ${user.user_Email}
        </td>
    </tr>
    <tr>
        <td class="tds">地址：</td>
        <td>
            ${user.user_Address}
        </td>
    </tr>
    <tr>
        <td class="sub_td" colspan="2" class="tds">
            <input type="button" value="修改密码" onclick="a()"/>
        </td>
    </tr>
    <tr>
        <td class="sub_td" colspan="2" class="tds">
            <input type="button" value="修改个人信息" onclick="c()"/>
        </td>
    </tr>
    <tr>
        <td class="sub_td" colspan="2" class="tds">
            <input type="button" value="收货地址详情" onclick="b()"/>
        </td>
    </tr>
</table>
</body>
</html>
