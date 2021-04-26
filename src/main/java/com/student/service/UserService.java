package com.student.service;

import java.util.List;

/** @Author: QTX @Date: 2021/4/26 */
public interface UserService {

  /**
   * 登录
   *
   * @param name 昵称
   * @param password 密码
   * @return
   */
  boolean login(String name, String password);

  /**
   * 添加用户
   *
   * @param name 昵称
   * @param password 密码
   * @param sex 性别
   */
  void add(String name, String password, int sex);

  /**
   * 查询所有用户
   *
   * @return
   */
  List query();

  /**
   * 根据昵称查询
   *
   * @param name 昵称
   * @return
   */
  List query(String name);

  /**
   * 删除用户
   *
   * @param id 用户id
   */
  void remove(int id);

  /**
   * 修改信息
   *
   * @param name 昵称
   * @param password 密码
   * @param id 用户id
   */
  void revise(String name, String password, int id);
}
