package com.zxc.query;

import org.apache.commons.lang.StringUtils;

import com.zxc.entities.Student;

/**
 * 
 * 子类自己的查询条件：用户名，email，部门
 * 
 */
public class StudentQuery extends BaseQuery {

	private String stuNum;
	private String stuName;
	private String stuAddress;
	private Long clasId;
	private Long stuId;

	public StudentQuery() {
		super(Student.class.getName());
	}

	@Override
	protected void addWhere() {
		if (StringUtils.isNotBlank(stuNum)) {
			addCondition("o.stuNum like ?", "%" + stuNum + "%");
		}
		if (StringUtils.isNotBlank(stuName)) {
			addCondition("o.stuName like ?", "%" + stuName + "%");
		}
		if (StringUtils.isNotBlank(stuAddress)) {
			addCondition("o.stuAddress like ?", "%" + stuAddress + "%");
		}
		if (clasId != null && clasId != -1L) {
			addCondition("o.clas.clasId=?", clasId);
		}
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

	public String getStuAddress() {
		return stuAddress;
	}

	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}

	public Long getClasId() {
		return clasId;
	}

	public void setClasId(Long clasId) {
		this.clasId = clasId;
	}

	public Long getStuId() {
		return stuId;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

}
