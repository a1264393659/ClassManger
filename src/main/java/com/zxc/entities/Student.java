package com.zxc.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Student {
	private Long stuId;// 学生id
	private String stuNum;// 学生编号
	private String stuName;// 学生姓名
	private String stuSex;// 学生性别
	private Date stuBirth;// 学生出生日期
	private String stuPwd;// 学生密码
	private String stuPhone;// 学生联系电话
	private String stuAddress;// 学生家庭住址
	private Clas clas;// 班级多对一
	private Set<Course> courses = new HashSet<Course>();// 课程多对多

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Student() {

	}

	public Student(String stuNum) {
		this.stuNum = stuNum;
	}

	public Student(Long stuId) {
		this.stuId = stuId;
	}

	public Long getStuId() {
		return stuId;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public Date getStuBirth() {
		return stuBirth;
	}

	public void setStuBirth(Date stuBirth) {
		this.stuBirth = stuBirth;
	}

	public String getStuPwd() {
		return stuPwd;
	}

	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public String getStuAddress() {
		return stuAddress;
	}

	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}

	public Clas getClas() {
		return clas;
	}

	public void setClas(Clas clas) {
		this.clas = clas;
	}

}
