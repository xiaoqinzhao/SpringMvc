<%--
  Created by IntelliJ IDEA.
  User: zhaoxiaoqin
  Date: 2019/5/31
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/account/findAll">测试</a>
<h1>测试保存</h1>
<form action="/account/save" method="post">
    姓名：<input type="text" name="name"/>
    金额：<input type="text" name="money"/>
    <input type="submit" value="提交">
</form>
</body>
</html>
