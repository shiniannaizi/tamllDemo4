<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/1
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>商品列表</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${context}/DataTables/css/prodList.css"/>
    <script type="text/javascript" src="${context}/DataTables/js/jquery-1.4.2.js"></script>
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
    </script>
</head>
<body STYLE="background-image: none;font-size: 15px;margin-top: -160px">
<h1>商品列表</h1>
<table border="1px">
    <input type="hidden" id="err" value="<%= request.getAttribute("errMsg")%>" />
    <tr id="trerr">
        <td class="sub_td" colspan="5">
            <span style="color: #d91210;text-align: center" id="errspan">
            </span>
        </td>
    </tr>
    <tr>
        <td class="sub_td">商品名称</td>
        <td class="sub_td" style="width: 100px;height: 100px">商品封面</td>
        <td class="sub_td">商品价格</td>
        <td class="sub_td">促销价格</td>
        <td class="sub_td">商品库存</td>
        <td class="sub_td">生产日期</td>
        <td class="sub_td">更新日期</td>
        <td class="sub_td" style="width: 200px">商品简介</td>
        <td class="sub_td" style="width: 200px">商品操作</td>
    </tr>
    <c:forEach var="product" items="${productlist}">
        <tr>
            <td class="sub_td">${product.product_Name}</td>
            <td class="sub_td"><img style="width: 100px;height: 100px"
                                    src="<c:url value="${context}/uploads/${product.product_First_Image}"/>"></img></td>
            <td class="sub_td">${product.product_Orignal_Price}元</td>
            <td class="sub_td">${product.product_Promote_Price}元</td>
            <td class="sub_td">${product.product_Stock}</td>
            <td class="sub_td"><fmt:formatDate value="${product.product_Create_Date}"
                                               type="date" dateStyle="long"/></td>
            <td class="sub_td"><fmt:formatDate value="${product.product_Update_Date}"
                                               type="date" dateStyle="long"/></td>
            <td class="sub_td" style="width: 200px">${product.product_Subtitle}</td>
            <td class="sub_td" style="width: 200px">
                <a href="<c:url value="/backend/updateprod/${product.product_Id}"/>" target="rightFrame">修改</a>
                <a href="<c:url value=""/>" target="rightFrame">删除</a>
                <a href="<c:url value=""/>">上传图片</a>
                <a href="<c:url value="/backend/addproperty/${product.product_Id}"/>" target="rightFrame">设置属性</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
