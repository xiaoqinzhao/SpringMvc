<%--
  Created by IntelliJ IDEA.
  User: zhaoxiaoqin
  Date: 2019/5/23
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>title</title>
</head>
<body>
<form action="/FileUpLoad2_war_exploded/fileupload" method="post" enctype="multipart/form-data">
    文件上传:<input type="file" name="upload"/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
