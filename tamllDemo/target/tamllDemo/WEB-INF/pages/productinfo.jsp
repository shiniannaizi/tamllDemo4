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
    <script type="text/javascript" src="${context}/DataTables/js/jquery-1.4.2.js"></script>
    <script>
        /* 文档就绪函数,在整个html文档加载完之后立即执行 */
        $(function(){
            /* 为购买数量输入框添加失去焦点事件
             * 当输入失去焦点时, 检查购买数量是否合法
             * 购买数量必须是 大于0的整数
             * 正则表达式为: /^[1-9][0-9]*$/
             */
            $("#buyNumInp").blur(function(){
                //正则: 大于0的整数
                var reg = /^[1-9][0-9]*$/;
                var id = document.getElementById("pid").value.trim();
                var errmsg = document.getElementById("errMsg");
                //获取购买数量
                var buyNum = $(this).val();
                if(!reg.test(buyNum)){
                    alert("您输入的购买数量不合法!");
                    $(this).val(1);
                    return;
                }
                $.ajax({
                    type:'GET',
                    url:'/checkprodstock/'+id+'/'+buyNum,
                    success: function (data){
                        if(data == "false"){
                            errmsg.innerHTML = "库存数量不足".fontsize(2).fontcolor("red");
                            $("#buyNumInp").val(1);
                        }
                    }
                });
            });
            $("#updatecart").click(function () {
                var id = document.getElementById("pid").value.trim();
                //获取购买数量
                var buyNum = $("#buyNumInp").val();
                location.href="${context}/updatecart/"+id+"/"+buyNum;
            });
        });
        /* 为"减号(-)"添加点击事件, 实现点击"减号"将购买数量减1 */
        function delNumClick(){
            var $buyNumInp =  $("#buyNumInp");
            var id = document.getElementById("pid").value.trim();
            //获取输入框内的购买数量
            var buyNum = $buyNumInp.val();
            if(buyNum>1){//购买数量不能小于1
                //减1再存入输入框
                $buyNumInp.val(parseInt(buyNum)-1);
            }
        };
        /* 为"加号(+)"添加点击事件, 实现点击"加号"将购买数量加1 */
        function addNumClick (){
            var $buyNumInp =  $("#buyNumInp");
            var id = document.getElementById("pid").value.trim();
            var errmsg = document.getElementById("errMsg");
            //获取输入框内的购买数量
            var buyNum = $buyNumInp.val();
            //减1再存入输入框
            $buyNumInp.val(parseInt(buyNum)+1);
            $.ajax({
                type:'GET',
                url:'/checkprodstock/'+id+'/'+(parseInt(buyNum)+1),
                success: function (data){
                    if(data == "false"){
                        errmsg.innerHTML = "库存数量不足".fontsize(2).fontcolor("red");
                        $("#buyNumInp").val(parseInt(buyNum));
                    }
                }
            });
        };
    </script>
</head>
<body>
<%@include file="/_head.jsp"%>
<div id="warp">
    <span style="color: red;text-align: center" id="errMsg"></span>
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
    <form action="#" method="post" style="width: 100%">
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
	            <a href="javascript:void(0)" id="delNum" onclick="delNumClick()">-</a>
	            <input type="text" id="buyNumInp" name="buyNum" oldBuyNum="1" value="1">
		        <a href="javascript:void(0)" id="addNum" onclick="addNumClick()">+</a>
                </span>
                </span>
            </div>
            <div id="right_bottom">
                <input type="hidden" id="pid" value="${product.product_Id}"/>
                <input class="add_cart_but" id="updatecart" type="button" value=""/><br/>
                <a href="/prodreview/${product.product_Id}" style="font-size: 24px;margin-left: 550px">商品评价</a>
            </div>
        </div>
    </form>
</div>
<%@include file="/_foot.jsp"%>
</body>
</html>
