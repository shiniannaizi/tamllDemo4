<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/31
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>商品信息详情</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${context}/DataTables/css/prodInfo.css"/>
</head>
<body>
<div id="warp">
    <div id="left">
        <div id="left_top">
            <img src="<c:url value="${context}/uploads/${product.product_First_Image}"/>"/>
        </div>
        <div id="left_bottom">
            <img id="lf_img" src="${context}/DataTables/img/prodInfo/lf.jpg"/>
            <img id="mid_img" src="<c:url value="${context}/uploads/${product.product_First_Image}"/>"
                 width="60px" height="60px"/>
            <img id="rt_img" src="${context}/DataTables/img/prodInfo/rt.jpg"/>
        </div>
    </div>
    <form action="#"  method="post" style="width: 100%">
        <div id="right">
            <div id="right_top">
                <span id="prod_name">${product.product_Name}<br/></span>
                <br>
                <span id="prod_desc">${product.product_Subtitle}<br/></span>
            </div>
            <div id="right_middle">
				<span id="right_middle_span">
					EasyMall 价：
				<span class="price_red">￥${product.product_Orignal_Price}
				<br/>
			    运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：满 100 免运费<br />
			    服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：由EasyMall负责发货，并提供售后服务<br />
			    购买数量：
	            <a href="javascript:void(0)" id="delNum" onclick="">-</a>
	            <input type="text" id="buyNumInp" name="buyNum" value="1">
		        <a href="javascript:void(0)" id="addNum" onclick="">+</a>
                </span>
                </span>
            </div>
            <div id="right_bottom">
                <input type="hidden" name="pid" value=""/>
                <input class="add_cart_but" type="submit" value=""/>
            </div>
        </div>
    </form>
</div>
</body>
</html>
