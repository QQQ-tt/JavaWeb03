package com.student.util;

import com.student.bean.UserBean;
import com.student.dao.UserDao;

import java.util.List;

/** @Author: QTX @Date: 2021/4/23 */
public class NumberUtil {

  public int setNumber(UserDao userDao) {
    int num = 0;
    List list = userDao.select();

    if (list.size() == 0) {
      // 初始学号
      num = 100_001;
    } else {
      UserBean userBean = (UserBean) list.get(list.size() - 1);
      int number = userBean.getNumber();
      num = number + 1;
    }
    return num;
  }
}
