<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/24
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>新增收货人信息</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/DataTables/css/regist.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/Area.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/AreaData_min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/DataTables/js/addrecive.js"></script>
</head>
<body>
<form action="<%=request.getContextPath() %>/addreciveinfo" method="POST" onsubmit="return checkForm()">
    <h1>新增收货人信息</h1>
    <table>
        <span style="color: #d91210;text-align: center">
            <%= request.getAttribute("errMsg") == null ? "":request.getAttribute("errMsg") %>
        </span>
        <tr>
            <td class="tds">收货人姓名：</td>
            <td>
                <input type="text" name="user_recive_name" id="user_recive_name" onblur="checkName()"/>
                <span id="namespan"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">收货人手机号：</td>
            <td>
                <input type="text" name="user_recive_phone" id="user_recive_phone" onblur="checkPhone()"/>
                <span id="phonespan"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">收货地址：</td>
            <td>
                <select id="seachprov" name="seachprov" onChange="changeComplexProvince(this.value, sub_array,
            'seachcity', 'seachdistrict');"></select>&nbsp;&nbsp;&nbsp;
                <select id="seachcity" name="homecity" onChange="changeCity(this.value,'seachdistrict',
            'seachdistrict');"></select>&nbsp;&nbsp;
                <span id="seachdistrict_div"><select id="seachdistrict" name="seachdistrict"></select></span>
                <input type="hidden" name="user_recive_address" id="user_recive_address"/>
            </td>
        </tr>
        <tr>
            <td class="tds">详细地址:</td>
            <td>
                <textarea name="user_Recive_Detail_Address" id="" cols="30" rows="5"></textarea>
                <input type="hidden" name="userId" value="${USER_CONTEXT.user_Id}"/>
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" style="text-align: center">
                <input type="submit" value="添加信息" onclick="getAreaNamebyID()"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
