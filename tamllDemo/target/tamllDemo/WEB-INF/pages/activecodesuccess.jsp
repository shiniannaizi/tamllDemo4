<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活成功</title>
<script type="text/javascript">
	window.onload = function() {
		var time = document.getElementById("time");
		var val = time.innerHTML;
		var it = window.setInterval(function() {
			//倒计时减1
			val = val - 1;
			if (val >= 0) {
				//重新赋值 ,表示时间一直在递减
				time.innerHTML = val;
				if (val == 0) {
					//清除对象，节省资源
					window.clearInterval(it);
					window.location.href = "${pageContext.request.contextPath}/login.jsp";
				}
			} else {
				//清除对象，节省资源
				window.clearInterval(it);
			}
		}, 1000);
	};
</script>
</head>
<body>
	<p style="font-size: 24px;text-align: center">激活成功，
	<span id="time" style="color:red;font-size: 36px;text-align: center">5</span>s后跳转到登录页面,
        如果不跳转请<a href="index.jsp">点击这里</a></p>
</body>
</html>