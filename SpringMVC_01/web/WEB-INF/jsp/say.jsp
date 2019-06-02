<%--
  Created by IntelliJ IDEA.
  User: zhaoxiaoqin
  Date: 2019/3/17
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
int all=out.getBufferSize();//获得缓冲区大小
int remain=out.getRemaining();//获得剩余缓冲区大小
int use=all-remain;//使用的缓冲区大小
out.println("使用的缓冲区大小"+use+"<br>");
%>
name:<input type="text" value="${name}"/>
url:<input type="text" value="${url}"/>
UserName:${user.name}
UserPassword:${user.password}
</body>
</html>
