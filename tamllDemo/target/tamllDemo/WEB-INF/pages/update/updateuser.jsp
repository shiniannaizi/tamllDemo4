<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/24
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>更新个人信息</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/DataTables/css/regist.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/Area.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/AreaData_min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/updateuser.js"></script>
</head>
<body>
<form action="<%=request.getContextPath() %>/updateuserinfo/${user.user_Id}" method="POST"
      onsubmit="return checkEmail()">
    <h1>更新个人信息</h1>
    <table>
        <span style="color: #d91210;text-align: center">
            <%= request.getAttribute("errMsg") == null ? "":request.getAttribute("errMsg") %>
        </span>
        <tr>
            <input type="hidden" id="userId" value="${user.user_Id}"/>
            <td class="tds">用户名：</td>
            <td>
                ${user.user_Name}
            </td>
        </tr>
        <tr>
            <td class="tds">性别：</td>
            <td>
                <select name="usersex" id="usersex">
                    <option <c:if test="${user.user_Sex eq '男'}">selected</c:if> value="男">男</option>
                    <option <c:if test="${user.user_Sex eq '女'}">selected</c:if> value="女">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="tds">地址：</td>
            <td>
                <select id="seachprov" name="seachprov" onChange="changeComplexProvince(this.value, sub_array,
            'seachcity', 'seachdistrict');"></select>&nbsp;&nbsp;&nbsp;
                <select id="seachcity" name="homecity" onChange="changeCity(this.value,'seachdistrict',
            'seachdistrict');"></select>&nbsp;&nbsp;
                <span id="seachdistrict_div"><select id="seachdistrict" name="seachdistrict"></select></span>
                <input type="hidden" name="address" id="address" value="${user.user_Address}"/>
            </td>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email" id="email" value="${user.user_Email}"
                       onblur="checkEmail()"/>
                <span id="emailid"></span>
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" style="text-align: center">
                <input type="submit" value="修改信息" onclick="getUserAreaNamebyID()"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
