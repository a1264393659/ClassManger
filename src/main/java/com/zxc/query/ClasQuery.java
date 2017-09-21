package com.zxc.query;

import org.apache.commons.lang.StringUtils;

import com.zxc.entities.Clas;

/**
 * 
 * 子类自己的查询条件：用户名，email，部门
 * 
 */
public class ClasQuery extends BaseQuery {

	private String clasNum;
	private String clasName;

	public ClasQuery() {
		super(Clas.class.getName());
	}

	@Override
	protected void addWhere() {
		if (StringUtils.isNotBlank(clasNum)) {
			addCondition("o.clasNum like ?", "%" + clasNum + "%");
		}
		if (StringUtils.isNotBlank(clasName)) {
			addCondition("o.clasName like ?", "%" + clasName + "%");
		}
	}

	public String getClasNum() {
		return clasNum;
	}

	public void setClasNum(String clasNum) {
		this.clasNum = clasNum;
	}

	public String getClasName() {
		return clasName;
	}

	public void setClasName(String clasName) {
		this.clasName = clasName;
	}

}
