<%--
  Created by IntelliJ IDEA.
  User: qtx
  Date: 2021/4/22
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    td, th {
        border: solid 1px red;
        text-align: center;
    }

    .my-input {
        border: none;
        box-shadow: 0px 0px 0px 0px;
        outline: none;
    }

    a {
        text-decoration: none;
    }

    a:link {
        color: #265301;
    }

    a:hover {
        background-color: aquamarine;
    }

    .warp {
        width: 700px;
        margin: 50px auto 0px;
        height: 500px;
        background-color: bisque;
    }

    h1 {
        text-align: center;
    }

    .center-l {
        float: left;
        margin-left: 50px;
        margin-top: 50px;
        font-size: larger;
    }

    .content-r {
        float: right;
        margin-right: 50px;
    }

    .content:before {
        content: "";
        display: block;
        clear: both;
    }
</style>
<body>
<div class="warp">
    <div>
        <h1>欢迎:${uname }</h1>
    </div>
    <div class="content">
        <div class="center-l">
            <a href="newLogin.jsp">添加用户</a>
        </div>
        <div class="content-r">
            <table>
                <thead>
                <tr>
                    <th>昵称</th>
                    <th>密码</th>
                    <th>性别</th>
                    <th>学号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="list" items="${UserAll }">
                    <tr>
                        <td>
                            <p id="name${list.id}" class="my-input" contenteditable="true">${list.name}</p>
                        </td>
                        <td>
                            <p id="password${list.id}" class="my-input" contenteditable="true">${list.password}</p>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${list.sex==1}">男</c:when>
                                <c:when test="${list.sex==0}">女</c:when>
                            </c:choose></td>
                        <td>${list.number}</td>
                        <td>
                            <a href="javascript:;" onclick="del(${list.id})">删除</a>
                            <a href="javascript:;" onclick="update(${list.id})">修改</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<form action="revise" method="post">
    <input id="uid" type="hidden" name="uid">
    <input id="newname" type="hidden" name="name">
    <input id="newpassword" type="hidden" name="password">
</form>
<form action="remove" method="post">
    <input id="remove" type="hidden" name="id">
</form>
<script>
  function update(id) {
    var uname = getDom("#name" + id);
    var upassword = getDom("#password" + id);
    var newname = getDom("#newname");
    var newpassword = getDom("#newpassword");
    var uid = getDom("#uid");
    newname.value = uname.innerHTML;
    newpassword.value = upassword.innerHTML;
    uid.value = id;
    document.forms[0].submit();
  }

  function del(id) {
    var uid = getDom("#remove");
    uid.value = id;
    document.forms[1].submit();
  }

  function getDom(d) {
    return document.querySelector(d);
  }
</script>
</body>
</html>
