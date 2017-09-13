<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/9
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getContextPath();
%>
<html>
<head>
    <title>文件上传</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${basePath}/DataTables/css/prodList.css"/>
    <script type="text/javascript" src="${basePath }/DataTables/js/jquery-3.2.1.js"></script>
    <!-- webuploader.js -->
    <script type="text/javascript" src="${basePath }/DataTables/js/webuploader.js"> </script>
    <!-- webuploader.css -->
    <link rel="stylesheet" type="text/css" href="${basePath }/DataTables/css/webuploader.css">
    <script type="text/javascript" src="${basePath }/DataTables/js/myuploader.js"></script>
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
<body>
<table border="1px">
    <input type="hidden" id="err" value="<%= request.getAttribute("errMsg")%>" />
    <tr id="trerr">
        <td class="sub_td" colspan="2">
            <span style="color: #d91210;text-align: center" id="errspan">
            </span>
        </td>
    </tr>
    <tr>
        <td class="sub_td">图片详情</td>
        <td class="sub_td">图片操作</td>
    </tr>
    <c:forEach var="prodimg" items="${prodImageList}">
        <tr>
            <td class="sub_td"><img style="width: 250px;height: 250px"
                                    src="<c:url value="${basePath}/uploads/${prodimg.product_Image_Address}"/>"></td>
            <td class="sub_td">
                <a href="<c:url value="/backend/deleteprodimg/${prodimg.product_Image_Id}/${prodimg.product.product_Id}"/> ">删除</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td class="sub_td" colspan="2">
            <div id="uploader-demo" >
                <!--用来存放item，图片列表fileList-->
                <div id="fileList" class="uploader-list"></div>
                <div id="filePicker" >选择图片</div>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
