<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${path}/DataTables/css/foot.css"/>
<div id="common_foot">
	<p>
		Copyright © 2011-2015 达内软件技术有限公司 版权所有 保留一切权利 苏B2-20130048号 | 京ICP备09062682号-9
		<br>
		网络文化经营许可证苏网文[2012]0401-019号 | <shiro:hasAnyRoles name="superadmin,admin">
		<a href="${path}/backend/login"
		   style="text-decoration: none;color:#000;">后台入口</a>
	</shiro:hasAnyRoles>
	</p>
</div>
