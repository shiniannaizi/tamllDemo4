<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/25
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品评价</title>
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
        function add() {
            var addreview = document.getElementById("addreview").value.trim();
            var errspan = document.getElementById("errspan");
            if (addreview==null||addreview==""||addreview==undefined){
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
<table border="1px" align="center" style="margin: 0 auto">
    <input type="hidden" id="err" value="<%= request.getAttribute("errMsg")%>" />
    <tr id="trerr">
        <td class="sub_td" colspan="5">
            <span style="color: #d91210;text-align: center" id="errspan">
            </span>
        </td>
    </tr>
    <tr>
        <td class="sub_td" width="100px">留言人</td>
        <td class="sub_td" width="200px">留言时间</td>
        <td class="sub_td" width="600px">留言内容</td>
    </tr>
    <c:forEach var="review" items="${reviews}">
        <tr>
            <td class="sub_td">${review.user.user_Name}</td>
            <td class="sub_td"><fmt:formatDate value="${review.review_Create_Date}"
                                               type="date" dateStyle="long"/></td>
            <td class="sub_td" style="width: 200px">${review.review_Content}</td>
        </tr>
    </c:forEach>
</table>
<form action="${context}/addprodreview/${productId}" method="POST" style="float: left;margin-left: 37%">
    <textarea placeholder="请输入商品评价" name="addreview" id="addreview" cols="80" rows="10" onblur="add()"></textarea>
    <input type="submit" value="添加留言" onsubmit="return add()"/>
</form>
</body>
</html>
