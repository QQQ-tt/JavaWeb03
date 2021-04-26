<%--
  Created by IntelliJ IDEA.
  User: qtx
  Date: 2021/4/23
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="static/jquery-3.6.0.js"></script>
<style>
    .warp {
        width: 300px;
        margin: 100px auto 0;
    }

    th, .cen {
        text-align: center;
    }

    .title-text {
        font-size: larger;
    }

    tr {
        height: 50px;
    }

</style>
<body>
<div class="warp">
    <form id="myform" action="add" method="post" onsubmit="return f">
        <table>
            <thead>
            <tr>
                <th colspan="2"><span class="title-text">欢迎新同学</span></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="text-field">姓名：</td>
                <td><input class="val" type="text" name="name"></td>
            </tr>
            <tr>
                <td class="text-field">密码：</td>
                <td><input class="val p1" type="password"></td>
            </tr>
            <tr>
                <td class="text-field">确认密码：</td>
                <td><input class="val p2" type="password" name="password"></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    男<input class="check" type="radio" name="sex" value="1">
                    女<input class="check" type="radio" name="sex" value="0">
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td class="cen" colspan="2">
                    <input type="submit" value="注册">
                </td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
<script>
  const f = $("#myform").submit(function () {
    let flag = false;
    $(".val").each(function () {
      if (($(this).val() !== "") && ($(this).val() != null)) {
        $(this).css("border", "1px solid black").focus(function () {
          $(this).css("outline", "none").css("border", "1px solid black");
        })
        flag = true;
      } else if (($(this).val() === "") || ($(this).val() == null)) {
        $(this).css("border", "1px solid red").focus(function () {
          $(this).css("outline", "none").css("border", "1px solid red");
        })
        flag = false;
      }
    });
    let sex = $("input:radio[name='sex']:checked").val();
    if (sex == null) {
      flag = false;
    }
    return flag;
  });
  $(".val").each(function () {
    $(this).blur(function () {
      if (($(this).val() !== "") && ($(this).val() != null)) {
        $(this).css("border", "1px solid black").focus(function () {
          $(this).css("outline", "none").css("border", "1px solid black");
        })
      }
    })
  })
</script>
</body>
</html>
