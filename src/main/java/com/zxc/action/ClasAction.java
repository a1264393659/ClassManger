package com.zxc.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.zxc.entities.Clas;
import com.zxc.query.ClasQuery;
import com.zxc.query.PageList;
import com.zxc.service.IClasService;

public class ClasAction extends CRUDAction<Clas> {
	private IClasService clasService;
	private Clas clas;
	private PageList pageList;
	private ClasQuery baseQuery = new ClasQuery();
	private Long clasId;

	public void setClasService(IClasService clasService) {
		this.clasService = clasService;
	}

	@Override
	protected void list() throws Exception {
		logger.debug("list");
		this.pageList = clasService.findByQuery(baseQuery);
	}

	@Override
	public String input() throws Exception {
		logger.debug("input");
		return INPUT;
	}

	@Override
	@InputConfig(methodName = Action.INPUT)
	public String save() throws Exception {
		if (clasId == null) {
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
			clasService.save(clas);
		} else {
			clasService.update(clas);
		}
		return RELOAD;
	}

	// ajax删除
	@Override
	public String delete() throws Exception {
		logger.debug("delete");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			if (clasId != null) {
				clasService.delete(clasId);
				out.print("{\"success\":true,\"msg\":\"删除成功\"}");
			} else {
				out.print("{\"success\":false,\"msg\":\"删除失败：没有对应的数据\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"success\":false,\"msg\":\"该班级有学生,无法删除\"}");
		}
		return NONE;
	}

	@Override
	public void prepareInput() throws Exception {
		if (clasId != null) {
			clas = clasService.get(clasId);
		}
	}

	@Override
	public void prepareSave() throws Exception {
		if (clasId != null) {
			clas = clasService.get(clasId);
		} else {
			clas = new Clas();
		}
	}

	@Override
	public Clas getModel() {
		return clas;
	}

	public ClasQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(ClasQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public Long getClasId() {
		return clasId;
	}

	public void setClasId(Long clasId) {
		this.clasId = clasId;
	}

	public PageList getPageList() {
		return pageList;
	}

	// ajax验证班级编号是否重复
	private String clasNum;

	public String getClasNum() {
		return clasNum;
	}

	public void setClasNum(String clasNum) {
		this.clasNum = clasNum;
	}

	public String checkclasNum() throws Exception {
		logger.debug("checkclasNum");
		System.out.println("checkclasNum:" + clasNum);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		if (clasId == null) {// 新增直接验证用户名是否重�?
			System.out.println(clasService.findByclasNum(clasNum));
			out.print(clasService.findByclasNum(clasNum));
		}
		return NONE;
	}
	
	//ajax验证班级名称是否重复
	private String clasName;
	
	public String getClasName() {
		return clasName;
	}

	public void setClasName(String clasName) {
		this.clasName = clasName;
	}

	public String checkclasName() throws Exception {
		logger.debug("checkclasName");
		System.out.println("checkclasName:" + clasName);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		if (clasId == null) {// 新增直接验证用户名是否重�?
			System.out.println(clasService.findByclasName(clasName));
			out.print(clasService.findByclasName(clasName));
		}
		return NONE;
	}

}
