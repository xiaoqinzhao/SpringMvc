<%--
  Created by IntelliJ IDEA.
  User: zhaoxiaoqin
  Date: 2019/6/1
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>201631107110赵小钦</title>
    <script src="/js/jquery.js"></script>
    <style>
        .login-form-mask {
            z-index: 9998;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: #000;
            opacity: 0.4;
            filter: alpha(opacity=40);
            display: none;
        }
        .login-form {
            z-index: 9999;
            position: fixed;
            top: 40%;
            left: 55%;
            width: 520px;
            height: 350px;
            margin: -180px 0 0 -330px;
            border-radius: 10px;
            border: solid 2px #666;
            background-color: rgba(60,60,60,0.8);
            display: none;
            box-shadow: 0 0 10px #666;
        }
        .login-close {
            margin-left: 95%;
        }
        h3.loginlabel{
            font-size: 30px;
            text-align: center;
            color: aqua;
            -webkit-mask-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0,0,0,1)), to(rgba(0,0,0,0)));
        }
        label.username{
            color: aqua;
            -webkit-mask-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0,0,0,1)), to(rgba(0,0,0,0)));
            font-size: 20px;
            display: inline-block;
            width: 5em;
            text-align: right;
        }
        input.uname{
            color: aqua;
            -webkit-mask-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0,0,0,1)), to(rgba(0,0,0,0)));
            background-color: rgba(0,0,0,0.5);
            width: 45%;
            height: 25px;
            font-size: 16px;
            margin-top: 10px;
            border-radius: 5px;
        }
        input.uname:focus{
            border-color: red;
        }
        div.input{
            margin-left: 12%;
        }
        div.form-group{
            margin-top: 20px;
            text-align: center;
        }
        button.submit{
            background-color: rgba(0,0,0,0.5);
            -webkit-mask-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0,0,0,1)), to(rgba(0,0,0,0)));
            width: 25%;
            height: 40px;
            color: aqua;
        }
        button.submit:hover{
            background-color:orange;
        }
        #tip{
            color: red;
            display: block;
            text-align: center;
        }
    </style>
    <script>
        //设置登录页面弹出效果
        $(document).ready(function($) {
            $('.login-form-mask').fadeIn(100);
            $('.login-form').slideDown(200);
            $('.login-close').click(function() {
                $('.login-form-mask').fadeOut(100);
                $('.login-form').slideUp(200);
            });
            $('#close').click(function () {
                history.go(-1);
            });
            $('#submit').click(function () {
                $.post("/index/login",{
                        username:$('#username').val(),
                        password:$('#password').val()
                    },
                    function (data) {
                        if (data) {
                            $(location).attr('href', '/index/fileuploadpage');
                            $('#tip').text('')
                        } else {
                            $('#tip').text('密码错误或用户名不存在');
                        }
                    }
                );
            });
            $('#register').click(function () {
                $(location).attr("href","/question/regpage");
            });
        });


    </script>

</head>
<body>
<div class="login-form">
    <div class="login-header">
        <a href="javascript:;" title="关闭" id="close" class="login-close close">×</a>
        <h3 class="loginlabel">用户登录</h3>
        <span id="tip"></span>
    </div>
    <div class="col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1">
        <div class="input">
            <label class="username">用户名:</label>
            <input type="text" class="uname" id="username" name="username" autocomplete="off" spellcheck="false" placeholder="请输入用户名">
        </div>
        <div class="input">
            <label class="username">密码:</label>
            <input type="password" class="uname" id="password" name="password" autocomplete="off" spellcheck="false" placeholder="请输入密码">
        </div>
        <div class="form-group">
            <button class="btn btn-info btn-lg btn-block submit" id="submit">登录</button>
            <button class="btn btn-info btn-lg btn-block submit" id="register">注册</button>
        </div>
    </div>
</div>
<div class="login-form-mask"></div>
</body>
</html>
