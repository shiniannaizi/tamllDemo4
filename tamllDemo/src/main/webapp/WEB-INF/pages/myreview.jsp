<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/25
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的留言</title>
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
<%@include file="/_head.jsp"%>
<table border="1px" align="center" style="margin: 0 auto">
    <input type="hidden" id="err" value="${errMsg}" />
    <tr id="trerr">
        <td class="sub_td" colspan="5">
            <span style="color: #d91210;text-align: center" id="errspan">
            </span>
        </td>
    </tr>
    <tr>
        <td class="sub_td" width="100px">留言商品</td>
        <td class="sub_td" width="200px">留言时间</td>
        <td class="sub_td" width="600px">留言内容</td>
        <td class="sub_td" style="width: 200px">商品操作</td>
    </tr>
    <c:forEach var="review" items="${myreviews}">
        <tr>
            <td class="sub_td">${review.product.product_Name}</td>
            <input type="hidden" id="prodId" value="${review.product.product_Id}"/>
            <td class="sub_td"><fmt:formatDate value="${review.review_Create_Date}"
                                               type="date" dateStyle="long"/></td>
            <td class="sub_td" style="width: 200px">${review.review_Content}</td>
            <td class="sub_td" style="width: 200px">
                <a href="<c:url value="/updatereview/${review.product.product_Id}"/>">修改</a>
                <a href="<c:url value="/deletereviewinfo/${review.product.product_Id}"/>">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>