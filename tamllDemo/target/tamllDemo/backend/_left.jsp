<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/30
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>_left</title>
    <meta charset="utf-8"/>
    <style type="text/css">
        /* css样式 */
        body{
            background-color: #32323A;
            font-family: "微软雅黑";
            font-size: 14px;
        }
        body,div{
            margin: 0px;
            padding:0px;
        }
        div#menu_bar{
            font-size: 20px;
            color:#FFFFFF;
        }
        div#menu_bar td{
            border-top: 1px solid #cccccc;
            padding: 5px 0;
            text-indent: 15px;
            letter-spacing: 3px;
        }
        div#menu_bar div:last-child{
            border-bottom: 1px solid #cccccc;
        }
        div#menu_bar td:hover{
            background-color: #797981;
        }
        div#menu_bar div a{
            font-size: 20px;
            color:#FFFFFF;
            text-decoration: none;
        }
        div#menu_bar div ul{
            margin: 0;
            display: none;
        }
    </style>
    <script type="text/javascript" src="${path}/DataTables/js/jquery-1.4.2.js">
    </script>
    <script type="text/javascript">
        function openDiv(thisobj){
            $(thisobj).parents("div").siblings().find("ul").hide();//隐藏其他分组的div(除当前分组div以外的)
            $(thisobj).next().toggle();//切换当前分组的状态(如果显示则隐藏, 如果隐藏则显示)
        }
    </script>
</head>
<body margin="0">
<div id="menu_bar">
    <div><a href="javascript:void(0)" onclick="openDiv(this)">> 商品管理</a>
        <ul>
            <li><a href="${path}/backend/addProd" target="rightFrame">商品添加</a></li>
            <li><a href="${path}/backend/prodlist" target="rightFrame">商品列表</a></li>
        </ul>
    </div>
    <div><a href="javascript:void(0)" onclick="openDiv(this)">> 用户管理</a>
        <ul>
            <li><a href="${path}/management/userlist" target="rightFrame">用户列表</a></li>
            <li><a href="${path}/management/add/manager" target="rightFrame">新增管理员</a></li>
        </ul>
    </div>
    <div><a href="javascript:void(0)" onclick="openDiv(this)">> 权限管理</a>
        <ul>
            <li><a href="${path}/management/rolelist" target="rightFrame">角色列表</a></li>
            <li><a href="#" target="rightFrame">asdsad</a></li>
        </ul>
    </div>
    <div><a href="javascript:void(0)" onclick="openDiv(this)">> 订单管理</a>
        <ul>
            <li><a href="${path}/backend/orderlist" target="rightFrame">全部订单</a></li>
            <li><a href="${path}/backend/orderliststatus/${4}" target="rightFrame">退款通知</a></li>
        </ul>
    </div>
    <div><a href="${path}/backend/salelist" target="rightFrame" onclick="openDiv(this)">> 销售榜单</a>
    </div>
</div>
</body>
</html>
