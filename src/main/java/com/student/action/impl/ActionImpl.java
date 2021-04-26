package com.student.action.impl;

import com.student.action.Action;
import com.student.bean.UserBean;
import com.student.core.ActionContext;
import com.student.factory.ObjectFactory;
import com.student.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** @Author: QTX @Date: 2021/4/26 */
public class ActionImpl implements Action {
  UserService userService = (UserService) ObjectFactory.getObj("UserService");

  @Override
  public String login() {
    String flag = "";
    HttpServletRequest req = ActionContext.getActionContext().getRequest();
    String name = req.getParameter("name");
    String password = req.getParameter("password");
    boolean login = this.userService.login(name, password);
    if (login) {
      flag = "success";
      req.getSession().setAttribute("uname", name);
    } else {
      flag = "error";
    }
    return flag;
  }

  @Override
  public String add() {
    HttpServletRequest req = ActionContext.getActionContext().getRequest();
    String name = req.getParameter("name");
    String password = req.getParameter("password");
    String sex = req.getParameter("sex");
    this.userService.add(name, password, Integer.parseInt(sex));
    req.getSession().setAttribute("name", name);
    return "success";
  }

  @Override
  public String remove() {
    HttpServletRequest req = ActionContext.getActionContext().getRequest();
    String id = req.getParameter("id");
    this.userService.remove(Integer.parseInt(id));
    return "success";
  }

  @Override
  public String revise() {
    HttpServletRequest req = ActionContext.getActionContext().getRequest();
    String uid = req.getParameter("uid");
    String name = req.getParameter("name");
    String password = req.getParameter("password");
    this.userService.revise(name, password, Integer.parseInt(uid));
    return "success";
  }

  @Override
  public String query() {
    HttpServletRequest req = ActionContext.getActionContext().getRequest();
    List<UserBean> list = this.userService.query();
    req.getSession().setAttribute("UserAll", list);
    return "success";
  }
}
