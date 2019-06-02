<%--
  Created by IntelliJ IDEA.
  User: zhaoxiaoqin
  Date: 2019/5/25
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>title</title>
</head>
<body>
<p id="tip">用户名：${username}</p>
<p id="tip2">用户名：${password}</p>
<form action="/index/fileupload" method="post" enctype="multipart/form-data">
    文件上传:<input type="file" name="upload"/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
