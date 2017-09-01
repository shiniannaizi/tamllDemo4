<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/1
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
%>
<html>
<head>
    <title>修改商品信息</title>
    <style type="text/css">
        input{
            height: 36px;
            font-size: 16px;
        }
    </style>
</head>
<body>
   <div>
       <form action="#" method="post">
           <div>
               <table border="1px" width="50%" STYLE="margin: 0 auto">
                   <tr>
                       <td width="15%">商品名称:</td>
                       <td>
                           <input type="text" name="prod_name" id="prod_name" value="${product.product_Name}"
                           style="width: 100%"/>
                       </td>
                   </tr>
                   <tr>
                       <td>商品价格:</td>
                       <td>
                           <input type="number" name="prod_ori_price" id="prod_ori_price"
                                  value="${product.product_Orignal_Price}"
                                  style="width: 100%"/>
                       </td>
                   </tr>
                   <tr>
                       <td>促销价:</td>
                       <td>
                           <input type="number" name="prod_pro_price" id="prod_pro_price"
                                  value="${product.product_Promote_Price}"
                                  style="width: 100%"/>
                       </td>
                   </tr>
                   <tr>
                       <td>商品库存:</td>
                       <td>
                           <input type="number" name="prod_stock" id="prod_stock" value="${product.product_Stock}"
                                  style="width: 100%"/>
                       </td>
                   </tr>
                   <tr>
                       <td>商品简介:</td>
                       <td>
                           <input type="text" name="prod_subtitle" id="prod_subtitle"
                                  value="${product.product_Subtitle}"
                                  style="width: 100%"/>
                       </td>
                   </tr>
                   <tr>
                       <td>商品分类:</td>
                       <td>
                           <select name="prod_category" id="prod_category">
                               <c:forEach items="${categorys}" var="category">
                               <option <c:if test="${category.category_Id eq product.category.category_Id}">
                                   selected</c:if> value="${category.category_Id}">${category.category_Name}</option>
                               </c:forEach>
                           </select>
                       </td>
                   </tr>
                   <c:if test="${!empty product.product_First_Image}">
                       <tr>
                           <td>商品封面:</td>
                           <td>
                               <img style="width: 100px;height: 100px"
                                    src="<c:url value="${context}/uploads/${product.product_First_Image}"/>">
                               </img>
                           </td>
                       </tr>
                   </c:if>
                   <c:if test="${!empty product.propertyValues}">
                       <tr>
                           <td colspan="2">商品属性:</td>
                           <c:forEach items="${propertyMap}" var="map" varStatus="status">
                           <tr>
                               <td>${map.key}</td>
                               <td>
                                   <input type="text" name="${map.key}" value="${map.value}"/>
                               </td>
                           </c:forEach>
                           </tr>
                       </tr>
                   </c:if>
                   <tr>
                       <td colspan="2" STYLE="text-align: center">
                           <input type="submit" value="修改信息"/>
                       </td>
                   </tr>
               </table>
           </div>
       </form>
   </div>
</body>
</html>
