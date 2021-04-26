package com.student.service.impl;

import com.student.bean.UserBean;
import com.student.dao.UserDao;
import com.student.factory.ObjectFactory;
import com.student.service.UserService;
import com.student.transaction.TransAction;
import com.student.util.NumberUtil;

import java.util.List;

/** @Author: QTX @Date: 2021/4/26 */
public class UserServiceImpl implements UserService {
  UserDao userDao = (UserDao) ObjectFactory.getObj("UserDao");
  TransAction action = (TransAction) ObjectFactory.getObj("TransAction");
  NumberUtil numberUtil = (NumberUtil) ObjectFactory.getObj("NumberUtil");

  public UserServiceImpl() {}

  @Override
  public boolean login(String name, String password) {
    this.action.begin();
    List select = this.userDao.select(name);
    this.action.commit();
    if (select.size() != 0) {
      UserBean user = (UserBean) select.get(0);
      boolean equals = user.getPassword().equals(password);
      return equals;
    } else {
      return false;
    }
  }

  @Override
  public void add(String name, String password, int sex) {
    this.action.begin();
    int number = this.numberUtil.setNumber(this.userDao);
    this.userDao.insert(name, password, sex, number);
    this.action.commit();
  }

  @Override
  public List query() {
    this.action.begin();
    List<UserBean> userBeans = this.userDao.select();
    this.action.commit();
    return userBeans;
  }

  @Override
  public List query(String name) {
    this.action.begin();
    List<UserBean> userBean = this.userDao.select(name);
    this.action.commit();
    return userBean;
  }

  @Override
  public void remove(int id) {
    this.action.begin();
    this.userDao.delete(id);
    this.action.commit();
  }

  @Override
  public void revise(String name, String password, int id) {
    this.action.begin();
    this.userDao.update(name, password, id);
    this.action.commit();
  }
}
