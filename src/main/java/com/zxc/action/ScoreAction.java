package com.zxc.action;

import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.zxc.entities.Score;
import com.zxc.query.PageList;
import com.zxc.query.ScoreQuery;
import com.zxc.service.IClasService;
import com.zxc.service.ICourseService;
import com.zxc.service.IScoreService;

public class ScoreAction extends CRUDAction<Score> {
	private IScoreService scoreService;
	private ICourseService courseService;
	private IClasService clasService;
	private PageList pageList;
	private ScoreQuery baseQuery = new ScoreQuery();
	private Score score;
	private BigDecimal stuScore;
	private String stuNum;
	private String couName;

	public void setScoreService(IScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

	public void setClasService(IClasService clasService) {
		this.clasService = clasService;
	}

	@Override
	protected void list() throws Exception {
		logger.debug("list");
		this.pageList = scoreService.findByQuery(baseQuery);
		putContext("allClas", clasService.getAll());
		putContext("allCourses", courseService.getAll());
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return super.input();
	}

	@Override
	public String save() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(stuScore);
		System.out.println(stuNum);
		System.out.println(couName);
		try {
			Score score = scoreService.findBystucou(stuNum, couName);
			score.setScoNum(stuScore);
			scoreService.update(score);
			out.print("{\"success\":true,\"msg\":\"保存成功\"}");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"success\":false,\"msg\":\"保存失败\"}");
		}
		return NONE;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepareInput() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void prepareSave() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Score getModel() {
		return score;
	}

	public ScoreQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(ScoreQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public PageList getPageList() {
		return pageList;
	}

	public BigDecimal getStuScore() {
		return stuScore;
	}

	public void setStuScore(BigDecimal stuScore) {
		this.stuScore = stuScore;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getCouName() {
		return couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

}
