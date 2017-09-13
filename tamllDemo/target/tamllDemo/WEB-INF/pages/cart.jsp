<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/9/11
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String context = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>我的购物车</title>
  	<link href="${context}/DataTables/css/cart.css" rel="stylesheet" type="text/css">
  	<script type="text/javascript" src="${app}/DataTables/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
$(function(){
	$(".prodDel").click(function(){
		if(confirm("您确定删除吗？")){
			//获取商品id
			var id = $(this).parent().prev().prev()
				.children("input[type='text']").attr("id");
			location.href="${context}/deletecart/"+id;
		}
	});
	//为"-"绑定点击事件
	$(".delNum").click(function(){
		//获取商品id
		var id = $(this).next().attr("id");
		//获取当前商品对应输入框中的值
		var pnum = $(this).next().val();
		//在当前的基础上减一
		pnum = pnum - 1;
		//判断修改还是删除
		if(pnum>0){//执行修改
			location.href="${context}/editcart/"+id+"/"+pnum;
		}else{//执行删除
			location.href="${context}/deletecart/"+id;
		}
	});
	//为"+"绑定点击事件
	$(".addNum").click(function(){
		var id = $(this).prev().attr("id");
		var pnum =$(this).prev().val();
		pnum = parseInt(pnum)+1;
		location.href="${context}/editcart/"+id+"/"+pnum;
	});
	$(".buyNumInp1").blur(function(){
		//获取商品id 
		var id = $(this).attr("id");
		//获取修改购买数量：当前输入框的值
		var newPnum = $(this).val();
		//获取修改前的数量
		var oldPnum =$("#hid_"+id).val();
		if(newPnum==0){//执行删除
			location.href="${context}/deletecart/"+id;
		}else if(newPnum!=oldPnum){//不同
			//验证数据的合法性
			var reg = /^[1-9][0-9]*$/;
			if(!reg.test(newPnum)){
				alert("请您输入正整数！");
				$(this).val(oldPnum);
			}else{
				location.href="${context}/editcart/"+id+"/"+newPnum;
			}
		}
	});
});
</script>
</head>
<body>
<%@include file="/_head.jsp" %>
	<div class="warp">
        <span style="color: red;text-align: center">${msg }</span>
		<!-- 标题信息 -->
	<div id="title">
		<input name="allC" type="checkbox" value="" onclick=""/>
		<span id="title_checkall_text">全选</span>
		<span id="title_name">商品</span>
		<span id="title_price">单价（元）</span>
		<span id="title_buynum">数量</span>
		<span id="title_money">小计（元）</span>
		<span id="title_del">操作</span>
	</div>
	<!-- 购物信息 -->
<c:set var="money" value="0"/>
<c:forEach items="${cart}" var="entry">
	<div id="prods">
		<input name="prodC" type="checkbox" value="" onclick=""/>
		<img src="<c:url value="${context}/uploads/${entry.key.product_First_Image}"/>" width="90" height="90" />
		<span id="prods_name">${entry.key.product_Name }</span>
		<span id="prods_price">${entry.key.product_Orignal_Price }</span>
		<span id="prods_buynum"> 
			<input type="hidden" id="hid_${entry.key.product_Id }" value="${entry.value}"/>
			<a href="javascript:void(0)" class="delNum" >-</a>
			<input id="${entry.key.product_Id }" class="buyNumInp1" type="text" value="${entry.value }" >
			<a href="javascript:void(0)" class="addNum" >+</a>
		</span>
		<span id="prods_money">${entry.key.product_Orignal_Price*entry.value}</span>
		<c:set var="money" value="${money+entry.key.product_Orignal_Price*entry.value}"/>
		<span id="prods_del"><a class="prodDel" href="javascript:void(0)">删除</a></span>
	</div>
</c:forEach>
	<!-- 总计条 -->
		<div id="total">
			<div id="total_1">
				<input name="allC" type="checkbox" value=""/> 
				<span>全选</span>
				<a id="del_a" href="#">删除选中的商品</a>
				<span id="span_1">总价：</span>
				<span id="span_2">￥${money }</span>
			</div>
			<div id="total_2">	
				<a id="goto_order" href="/orderaddpage">去结算</a>
			</div>
		</div>
	</div>
<%@include file="/_foot.jsp" %>
</body>
</html>