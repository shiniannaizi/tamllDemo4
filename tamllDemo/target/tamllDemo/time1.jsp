<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/23
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>正在跳转</title>
</head>
<body>

<p style="font-size: 36px;color: red;text-align: center">恭喜你注册成功，成为EasyMall商城会员<span id="second"></span><br />
5秒后跳转首页，如果不跳转请<a href="index.jsp">点击这里</a></p>






</body>
<script type="text/javascript">

    var time = 5;

    var timer = setInterval(
        function(){
            var second = document.getElementById("second");
            if(time>=1){
                second.innerHTML = "还有"+time+"秒";
                time--;
            }else{
                clearInterval(timer);//关闭定时器
                location.href = "index.jsp";//跳转页面
            }

        },1000)


</script>

</html>
