package com.student.dao.impl;

import com.student.dao.UserDao;
import com.student.dao.rowamapper.Impl.UserRowsMapperImpl;
import com.student.util.JdbcTemplpate;
import lombok.SneakyThrows;

import java.util.List;

/** @Author: QTX @Date: 2021/4/22 */
public class UserDaoImpl implements UserDao {
  @SneakyThrows
  @Override
  public void insert(String name, String password, int sex, int number) {
    String sql = "insert into student(s_name,s_password,s_sex,s_number) values (?,?,?,?)";
    JdbcTemplpate.executeUpdate(sql, name, password, sex, number);
  }

  @SneakyThrows
  @Override
  public void delete(int id) {
    String sql = "delete from student where s_id = ?";
    JdbcTemplpate.executeUpdate(sql, id);
  }

  @SneakyThrows
  @Override
  public void update(String name, String password, int id) {
    String sql = "update student set s_name = ?,s_password = ? where s_id = ?";
    JdbcTemplpate.executeUpdate(sql, name, password, id);
  }

  @SneakyThrows
  @Override
  public List select() {
    String sql = "select * from student";
    return JdbcTemplpate.executeQuery(sql, new UserRowsMapperImpl(), null);
  }

  @SneakyThrows
  @Override
  public List select(int id) {
    String sql = "select * from student where s_id = ?";
    return JdbcTemplpate.executeQuery(sql, new UserRowsMapperImpl(), id);
  }

  @SneakyThrows
  @Override
  public List select(String name) {
    String sql = "select * from student where s_name = ?";
    return JdbcTemplpate.executeQuery(sql, new UserRowsMapperImpl(), name);
  }
}
