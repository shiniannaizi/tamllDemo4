<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/21
  Time: 18:30
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
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="../../../DataTables/css/regist.css"/>
    <script type="text/javascript" src="../../../DataTables/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="../../../DataTables/js/Area.js"></script>
    <script type="text/javascript" src="../../../DataTables/js/AreaData_min.js"></script>
    <script type="text/javascript" src="../../../DataTables/js/regist.js"></script>
    <script type="text/javascript">
       function changeImage(thisObj){
          thisObj.src = "<%= request.getContextPath() %>/valiImage?time="+new Date().getTime();
       }
    </script>
</head>
<body>
<form action="${context}/insert" method="POST" onsubmit="return checkForm()">
    <h1>欢迎注册EasyMall</h1>
    <table>
        <tr>
            <td colspan="2" style="text-align: center;color: red">
                <%= request.getAttribute("errMsg") == null ? "":request.getAttribute("errMsg") %>
            </td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username" id="username" onblur="checkUserName()"/>
                <span id="usernameid"></span>
                <input type="hidden" id="cvalue" name="userAddress">
            </td>
        </tr>
        <tr>
            <td class="tds">性别：</td>
            <td>
                <select name="usersex" id="usersex">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
        </select>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password" id="password" onblur="checkPassWord(),enSurePass()"/>
                <span id="passwordid"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2" id="password2" onblur="enSurePass()"/>
                <span id="ensureid"></span>
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
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email" id="email" onblur="checkEmail()"/>
                <span id="emailid"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr" id="valistr" onblur="checkValistr()"/>
                <img src="${context}/valiImage" onclick="changeImage(this)"/>
                <span id="valistrid"></span>
            </td>
        </tr>
        <tr>
            <td class="td_sub" colspan="2" class="tds">
                <input type="submit" value="注册用户" onclick="getAreaNamebyID()"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
