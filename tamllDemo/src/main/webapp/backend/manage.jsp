<%--
  Created by IntelliJ IDEA.
  User: 肖松
  Date: 2017/8/30
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理页面</title>
    <meta charset="utf-8"/>
    <style type="text/css">
    </style>
</head>
<!-- frameset组织页面结构 -->
<frameset rows="13%, 87%" frameborder="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/backend/_top.jsp" noresize="noresize"/>
    <frameset cols="14%, 86%" frameborder="0" framespacing="0">
        <frame src="<%=request.getContextPath()%>/backend/_left.jsp" noresize="noresize"/>
        <frame src="<%=request.getContextPath()%>/backend/_right.jsp" name="rightFrame"  noresize="noresize"/>
    </frameset>
</frameset>
<body>
</body>
</html>
