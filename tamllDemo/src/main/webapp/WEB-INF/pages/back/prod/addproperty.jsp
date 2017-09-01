<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/2
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品属性值</title>
    <style type="text/css">
        input{
            height: 36px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div>
    <form action="/backend/addpropertyinfo/<%=request.getAttribute("productId")%>" method="post">
        <div>
            <table border="1px" width="50%" STYLE="margin: 0 auto">
                <c:forEach items="${propertys}" var="property" varStatus="status">
                <tr>
                    <td width="15%">${property.property_Name}</td>
                    <td>
                        <input type="text" name="${property.property_Name}"
                               style="width: 100%"/>
                    </td>
                </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <input type="submit" value="提交"/>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
</body>
</html>
