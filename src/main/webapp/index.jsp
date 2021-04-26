<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<script type="text/javascript" src="static/jquery-3.6.0.js"></script>
<style>
    table {
        margin: 100px auto 0px;
    }

    .sub-item a {
        margin-left: 60px;
    }

    .warp {
        margin: 0 auto;
        width: 500px;
    }

    h1 {
        margin-top: 100px;
        text-align: center;
    }

    a {
        display: inline-block;
        text-decoration: none;
        border: 1px solid black;
    }

</style>
<body>
<div class="warp">
    <h1>学生信息管理系统</h1>
    <form id="myform" action="login" method="post" onsubmit="return myf">
        <table>
            <tr>
                <td>学号：</td>
                <td>
                    <label>
                        <input class="val" type="text" name="name">
                    </label>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <label>
                        <input class="val" type="password" name="password">
                    </label>
                </td>
            </tr>
            <tr>
                <td class="sub-item" colspan="2">
                    <a href="javascript:;" class="a1 login">登录</a>
                    <a href="newLogin.jsp" class="a1">注册</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<script>
  $(".login").click(function () {
    $("#myform").submit();
  });
  const myf = $(function () {
    $("form").submit(function () {
      let flag = false;
      let inp = $(".val");
      inp.each(function () {
        if (($(this).val() !== "") && ($(this).val() != null)) {
          flag = true;
        } else if (($(this).val() === "") || ($(this).val() == null)) {
          $(this).css("border", "1px solid red").focus(function () {
            $(this).css("outline", "none").css("border", "1px solid red");
          })
          flag = false;
        }
      });
      return flag;
    });
  });

</script>
</body>
</html>