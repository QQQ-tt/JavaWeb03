package com.student.bean;

import lombok.Data;

/** @Author: QTX @Date: 2021/4/22 */
@Data
public class UserBean {
  /** 学生id */
  private int id;
  /** 姓名 */
  private String name;
  /** 密码 */
  private String password;
  /** 性别 0：女、1：男 */
  private int sex;
  /** 学号 */
  private int number;
}
