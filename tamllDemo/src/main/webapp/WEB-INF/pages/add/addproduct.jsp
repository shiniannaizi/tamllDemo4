<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/30
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>添加商品</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/DataTables/css/backstyle.css"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/backend/addProdInfo" method="POST" enctype="multipart/form-data">
    <h1>添加商品</h1>
    <table>
        <tr>
            <td class="tds">商品名称：</td>
            <td>
                <input type="text" name="prod_name" id="prod_name"/>
            </td>
        </tr>
        <tr>
            <td class="tds">商品价格：</td>
            <td>
                <input type="number" name="prod_ori_price" id="prod_ori_price"/>
            </td>
        </tr>
        <tr>
            <td class="tds">促销价：</td>
            <td>
                <input type="number" id="prod_pro_price" name="prod_pro_price" />
            </td>
        </tr>
        <tr>
            <td class="tds">商品库存：</td>
            <td>
                <input type="text" name="prod_stock" id="prod_stock"/>
            </td>
        </tr>
        <tr>
            <td class="tds">生产日期：</td>
            <td>
                <input type="date" name="prod_create_date" id="prod_create_date"/>
            </td>
        </tr>
        <tr>
            <td class="tds">商品分类：</td>
            <td>
                <select name="prod_category" id="prod_category">
                    <c:forEach items="${categorys}" var="category">
                        <option value="${category.category_Id}">${category.category_Name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="tds">商品简介：</td>
            <td>
                <textarea id="prod_subtitle" name="prod_subtitle" cols="30" rows="5"></textarea>
            </td>
        </tr>
        <tr>
            <td class="tds">商品封面：</td>
            <td>
                <input type="file" name="prod_first_image" id="prod_first_image"/>
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" class="tds">
                <input type="submit" value="添加商品"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
