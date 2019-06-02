<%--
  Created by IntelliJ IDEA.
  User: zhaoxiaoqin
  Date: 2019/5/25
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
    <style>
        h1{
            background: -webkit-linear-gradient(top,  #bccfe3 0%,#d2dded 100%);
            margin-top: 0px;
        }
        input{
            margin-top: 7px;
            margin-bottom: 7px;
        }
        input[type="text"]{
            background: -webkit-linear-gradient(top,  #bccfe3 0%,#d2dded 80%);
            border-radius: 7px;
            height: 30px;
            width: 200px;
        }
        input[type="text"]:focus{
            background: -webkit-linear-gradient(top,  #ffcfe3 0%,#d2dded 80%);
            border-color: cornflowerblue;
        }
        label{
            color: orange;
        }
        input[type="submit"]{
            background: -webkit-linear-gradient(top,  #bccfe3 0%,#d2dded 80%);
            border-radius: 7px;
            width: 200px;
            height: 30px;
        }
        input[type="submit"]:hover{
            background: -webkit-linear-gradient(top,  #ffcfe3 0%,#d2dded 80%);
        }
        form{
            margin: auto;
            margin-top: 100px;
            border-collapse: collapse;
            border: 1px black solid;
            width: 400px;
            height: auto;
        }
    </style>
</head>
<body>
<form style="text-align: center" action="/login" method="post">
    <h1 style="text-align: center">登录</h1>
    <table style="margin-left: auto;margin-right: auto">
        <tr>
            <td align="right"><label>用户名：</label></td>
            <td><input type="text" name="username"/></td>
        </tr>

        <tr>
            <td align="right"><label>密码：</label></td>
            <td><input type="text" name="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form>
</body>
</html>
