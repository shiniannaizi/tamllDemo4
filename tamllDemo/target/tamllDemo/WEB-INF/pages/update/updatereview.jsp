<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/25
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改我的留言</title>
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
        function update() {
            var myreview = document.getElementById("myreview").value.trim();
            var errspan = document.getElementById("errspan");
            if (myreview==null||myreview==""||myreview==undefined){
                errspan.innerHTML = "评价不能为空";
                return false;
            }else {
                return true;
            }
        }
    </script>
</head>
<body>
<%@include file="/_head.jsp"%>
<form action="/updatereviewinfo/${review.product.product_Id}">
<table border="1px" align="center" style="margin: 0 auto" width="800px">
    <input type="hidden" id="err" value="${errMsg}" />
    <tr id="trerr">
        <td class="sub_td" colspan="5">
            <span style="color: #d91210;text-align: center" id="errspan">
            </span>
        </td>
    </tr>
    <tr>
        <td class="sub_td" width="20%">留言商品</td>
        <td class="sub_td" width="80%">${review.product.product_Name}</td>
    </tr>
    <tr>
        <td class="sub_td" width="20%">留言时间</td>
        <td class="sub_td" width="80%"><fmt:formatDate value="${review.review_Create_Date}"
                                           type="date" dateStyle="long"/></td>
    </tr>
    <tr>
        <td class="sub_td" width="20%">留言内容</td>
        <td class="sub_td" width="80%" style="width: 200px">
            <input width="100%" type="text" value="${review.review_Content}" id="myreview" name="myreview" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="submit" value="修改评价" onsubmit="return update()">
        </td>
    </tr>
</table>
</form>
</body>
</html>
