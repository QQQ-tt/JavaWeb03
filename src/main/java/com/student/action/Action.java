package com.student.action;

/** @Author: QTX @Date: 2021/4/26 */
public interface Action {
  /**
   * 登录
   *
   * @return 是否成功
   */
  String login();

  /**
   * 添加
   *
   * @return 是否成功
   */
  String add();

  /**
   * 删除
   *
   * @return 是否成功
   */
  String remove();

  /**
   * 修改
   *
   * @return 是否成功
   */
  String revise();

  /**
   * 查询
   *
   * @return 是否成功
   */
  String query();
}
