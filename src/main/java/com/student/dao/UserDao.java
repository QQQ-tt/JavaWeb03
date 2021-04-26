package com.student.dao;

import java.util.List;

/** @Author: QTX @Date: 2021/4/22 */
public interface UserDao {

  /**
   * 添加用户
   *
   * @param name 用户名
   * @param password 密码
   * @param sex 性别
   * @param number 学号
   */
  void insert(String name, String password, int sex, int number);

  /**
   * 根据id删除用户
   *
   * @param id 用户id
   */
  void delete(int id);

  /**
   * 修改信息
   *
   * @param name 新昵称
   * @param password 新密码
   * @param id 用户id
   */
  void update(String name, String password, int id);

  /**
   * 查询所有用户
   *
   * @return
   */
  List select();

  /**
   * 根据id查询用户
   *
   * @param id 用户id
   * @return
   */
  List select(int id);

  /**
   * 根据昵称查询
   *
   * @param name 用户昵称
   * @return
   */
  List select(String name);
}
