package com.student.util;

import com.student.dao.rowamapper.RowedMapper;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** 业务的操作(CURD) @Author: QTX @Date: 2021/4/6 */
public class JdbcTemplpate {

  /**
   * 用于增删改的方法
   *
   * @param sql 从dao层传入sql语句
   * @param params 表示将接受的参数不确定
   * @throws SQLException
   */
  public static void executeUpdate(String sql, Object... params) throws SQLException {
    // 获取链接
    Connection conn = JdbcUtil.getConn();
    // sql执行器
    PreparedStatement pst = conn.prepareStatement(sql);
    // 设置参数
    setParams(pst, params);
    // 执行sql
    pst.executeUpdate();
    // 关闭连接
    JdbcUtil.close(pst, null);
  }

  /**
   * 用于查询的公共方法
   *
   * @param sql 从dao层传入sql语句
   * @param rm 传入对应实体类的处理结果集工具实现类
   * @param params 表示将接受的参数不确定
   * @return
   * @throws SQLException
   */
  public static List executeQuery(String sql, RowedMapper rm, Object... params)
      throws SQLException {
    List list = new ArrayList();
    // 获得连接
    Connection conn = JdbcUtil.getConn();
    // sql执行器
    PreparedStatement pst = conn.prepareStatement(sql);
    // 设置参数
    setParams(pst, params);
    // 执行sql语句返回结果集
    ResultSet resultSet = pst.executeQuery();

    while (resultSet.next()) {
      Object o = rm.setObject(resultSet);
      list.add(o);
    }
    JdbcUtil.close(pst, resultSet);
    return list;
  }

  @SneakyThrows
  private static void setParams(PreparedStatement pst, Object[] params) {
    if (params != null) {
      for (int i = 0; i < params.length; i++) {
        pst.setObject(i + 1, params[i]);
      }
    }
  }
}
