package com.zxc.service;

import com.zxc.entities.Student;

public interface IStudentService extends IBaseService<Student>{

	boolean findBystuNum(String stuNum);

	Student findByLogin(String stuName, String stuPwd);
}
