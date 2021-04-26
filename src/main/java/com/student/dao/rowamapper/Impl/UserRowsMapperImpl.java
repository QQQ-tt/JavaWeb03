package com.student.dao.rowamapper.Impl;

import com.student.bean.UserBean;
import com.student.dao.rowamapper.RowedMapper;
import lombok.SneakyThrows;

import java.sql.ResultSet;

/** @Author: QTX @Date: 2021/4/6 */
public class UserRowsMapperImpl implements RowedMapper {
  @SneakyThrows
  @Override
  public Object setObject(ResultSet rs) {
    UserBean userBean = new UserBean();
    userBean.setId(rs.getInt("s_id"));
    userBean.setName(rs.getString("s_name"));
    userBean.setPassword(rs.getString("s_password"));
    userBean.setSex(rs.getInt("s_sex"));
    userBean.setNumber(rs.getInt("s_number"));
    return userBean;
  }
}
