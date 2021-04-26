package com.student.transaction.impl;

import com.student.transaction.TransAction;
import com.student.util.JdbcUtil;
import lombok.SneakyThrows;

import java.sql.Connection;

/** @Author: QTX @Date: 2021/4/6 */
public class TransActionImpl implements TransAction {

  @SneakyThrows
  @Override
  public void begin() {
    Connection conn = JdbcUtil.getConn();
    conn.setAutoCommit(false);
  }

  @SneakyThrows
  @Override
  public void commit() {
    Connection conn = JdbcUtil.getConn();
    conn.commit();
    JdbcUtil.closeConn();
  }

  @SneakyThrows
  @Override
  public void rollback() {
    Connection conn = JdbcUtil.getConn();
    conn.rollback();
    JdbcUtil.closeConn(); // 放回池子中
  }
}
