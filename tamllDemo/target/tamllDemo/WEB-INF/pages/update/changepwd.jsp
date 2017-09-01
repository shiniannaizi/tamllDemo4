<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/24
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>修改密码</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/DataTables/css/regist.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/jquery-1.4.2.js">
    </script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/changepwd.js">
    </script>
</head>
<body>
<form action="<%=request.getContextPath() %>/changepassword" method="POST" onsubmit="return checkFrom()">
    <h1>修改密码</h1>
    <table>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                ${USER_CONTEXT.user_Name}
                <input type="hidden" name="userName" value="${USER_CONTEXT.user_Name}">
            </td>
        </tr>
        <tr>
            <td class="tds">旧密码：</td>
            <td>
                <input type="password" name="oldpassword" id="oldpassword"/>
                <span style="color: #d91210">
                    <%= request.getAttribute("errMsg") == null ? "":request.getAttribute("errMsg") %>
                </span>
            </td>
        </tr>
        <tr>
            <td class="tds">新密码：</td>
            <td>
                <input type="password" name="newpassword1" id="newpassword1"
                       onblur="checkpwd(),ensurpwd()"/>
                <span id="npwspanid1"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="newpassword2" id="newpassword2" onblur="ensurpwd()"/>
                <span id="npwspanid2"></span>
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" style="text-align: center">
                <input type="submit" value="确认修改"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
