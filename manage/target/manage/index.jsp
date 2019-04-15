<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <%--<link rel="stylesheet" type="text/css" href="./style/css/login.css">--%>
    <style>
        .myDiv {
            text-align: center;
            width: 300px;
            height: 200px;
            position: absolute;
            left: 50%;
            top: 50%;
            margin: -100px 0 0 -150px;
            line-height: 40px;
        }

        body {

            background: url(./style/images/1534410915.jpg) no-repeat;
            background-size: cover;
        }
    </style>
    <title>李氏集团登录窗口</title>
</head>
<body>
<div class="myDiv">
    <h1 class="myTitle"></h1>
    <form action="/google/user.do" method="post">
        <div>
            <label>账户：</label>
            <input name="username" value=""/>
        </div>
        <div>
            <label>密码：</label>
            <input type="password" name="password" value=""/>
        </div>
        <button type="submit">登录</button>
    </form>
</div>
</body>
</html>
