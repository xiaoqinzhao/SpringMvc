<%--
  Created by IntelliJ IDEA.
  User: zhaoxiaoqin
  Date: 2019/5/25
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>201631107110赵小钦</title>
    <script src="../js/jquery-3.3.1.min.js"></script>
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
            height: 500px;
            margin: -180px 0 0 -330px;
            border-radius: 5px;
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

        }
        div.input{
            margin-left: 12%;
        }
        div.form-group{
            margin-left: 65%;
        }
        #submit{
            background-color: rgba(0,0,0,0.5);
            -webkit-mask-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0,0,0,1)), to(rgba(0,0,0,0)));
            width: 60px;
            height: 40px;
            color: aqua;
        }
        #submit:hover{
            background-color:orange;
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
        });

        function check(){
            var email=$('#txtemail').val();
            var telnumber=$('#telnumber').val();
            var pwd=$('#password').val();
            var confirmpwd=$('#confirmpassword').val();

            $('#password').next().text('');
            $('#confirmpassword').next().text('');
            $('#telnumber').next().text('');
            $('#txtemail').next().text('');

            var myReg = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}|17[0-9]{9}$/;
            if(!myReg.test(telnumber)) {
                //不符合格式要求
                $('#telnumber').next().text('手机格式不正确');
                return false;
            }

            myReg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
            if(!myReg.test(email)){
                //不符合格式要求
                $('#txtemail').next().text('email格式不正确');
                return false;
            }

            if(pwd.length<6||pwd.length>12)
            {
                $('#password').next().text('密码长度应为6-12');
                return false;
            }else if(pwd!=confirmpwd)
            {
                $('#confirmpassword').after("<span style='color: red;'>密码输入不一致</span>");
                return false;
            }


        }

    </script>
</head>
<body>
<div class="login-form" onsubmit="return check()">
    <div class="login-header">
        <a href="javascript:;" title="关闭" id="close" class="login-close close">×</a>
        <h3 class="loginlabel">用户注册</h3>
    </div>
    <div class="col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1">
        <form action="http://www.baidu.com" method="get">
            <div class="input">
                <label class="username">手机号:</label>
                <input type="text" class="uname" id="telnumber" name="telnumber" autocomplete="off" spellcheck="false" placeholder="请输入手机号">
                <span style='color: red;'></span>
            </div>
            <div class="input">
                <label class="username">邮箱:</label>
                <input type="txt" class="uname" id="txtemail" name="email" autocomplete="off" spellcheck="false" placeholder="请输入邮箱">
                <span style='color: red;'></span>
            </div>
            <div class="input">
                <label class="username">密码:</label>
                <input type="password" class="uname" id="password" name="password" autocomplete="off" spellcheck="false" placeholder="请输入密码">
                <span style='color: red;'></span>
            </div>
            <div class="input">
                <label class="username">确认密码:</label>
                <input type="password" class="uname" id="confirmpassword" name="confirmpassword" autocomplete="off" spellcheck="false" placeholder="请再次输入密码">
                <span style='color: red;'></span>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-info btn-lg btn-block" id="submit">注 册</button>
            </div>
        </form>
    </div>
</div>
<div class="login-form-mask"></div>
</body>
</html>
