package com.student.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/** @Author: QTX @Date: 2021/4/22 */
public class JdbcUtil {

  /** 创建一个本地连接 */
  private static DataSource ds = null;
  /** 通过线程本地变量保存连接 */
  private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

  static {
    Properties properties = new Properties();
    try {
      properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream("datasource.properties"));
      ds = BasicDataSourceFactory.createDataSource(properties);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 获取数据库连接
   *
   * @return 数据库连接
   */
  public static Connection getConn() throws SQLException {
    // 从连接池获取
    Connection conn = threadLocal.get();
    if (conn == null) {
      // 从本地连接DataSource获取
      conn = ds.getConnection();
      // 将连接放到连接池变量中
      threadLocal.set(conn);
    }
    return conn;
  }
  /** 关闭连接-->将连接放回池子中 */
  public static void closeConn() throws SQLException {
    Connection conn = threadLocal.get();
    if (conn != null) {
      // 将连接放回池子中
      threadLocal.remove();
      conn.close();
    }
  }

  public static void close(PreparedStatement pst, ResultSet resultSet) throws SQLException {
    if (pst != null) {
      pst.close();
    }
    if (resultSet != null) {
      resultSet.close();
    }
  }
}
