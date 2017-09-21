package com.zxc.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.zxc.entities.Course;
import com.zxc.query.CourseQuery;
import com.zxc.query.PageList;
import com.zxc.service.ICourseService;

public class CourseAction extends CRUDAction<Course> {
	private ICourseService courseService;
	private Course course;
	private PageList pageList;
	private CourseQuery baseQuery = new CourseQuery();
	private Long couId;

	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

	@Override
	protected void list() throws Exception {
		logger.debug("list");
		this.pageList = courseService.findByQuery(baseQuery);
	}

	@Override
	public String input() throws Exception {
		logger.debug("input");
		return INPUT;
	}

	@Override
	@InputConfig(methodName = Action.INPUT)
	public String save() throws Exception {
		if (couId == null) {
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
			courseService.save(course);
		} else {
			courseService.update(course);
		}
		return RELOAD;
	}

	// ajax鍒犻�?
	@Override
	public String delete() throws Exception {
		logger.debug("delete");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			if (couId != null) {
				courseService.delete(couId);
				out.print("{\"success\":true,\"msg\":\"鍒犻櫎鎴愬姛\"}");
			} else {
				out.print("{\"success\":false,\"msg\":\"鍒犻櫎澶辫触锛氭病鏈夊搴旂殑鏁版嵁\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"success\":false,\"msg\":\"<font color='red'>寮傚父淇℃伅锛�"
					+ e.getMessage() + "</font>\"}");
		}
		return NONE;
	}

	@Override
	public void prepareInput() throws Exception {
		if (couId != null) {
			course = courseService.get(couId);
		}
	}

	@Override
	public void prepareSave() throws Exception {
		if (couId != null) {
			course = courseService.get(couId);
		} else {
			course = new Course();
		}
	}

	@Override
	public Course getModel() {
		return course;
	}

	public CourseQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(CourseQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public Long getCouId() {
		return couId;
	}

	public void setCouId(Long couId) {
		this.couId = couId;
	}

	public PageList getPageList() {
		return pageList;
	}

	// ajax楠岃瘉鐝骇缂栧彿鏄惁閲嶅�?
	private String couNum;

	public String getCouNum() {
		return couNum;
	}

	public void setCouNum(String couNum) {
		this.couNum = couNum;
	}

	public String checkcouNum() throws Exception {
		logger.debug("checkcouNum");
		System.out.println("checkcouNum:" + couNum);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		if (couId == null) {// 鏂板鐩存帴楠岃瘉鐢ㄦ埛鍚嶆槸鍚﹂噸澶�
			System.out.println(courseService.findBycouNum(couNum));
			out.print(courseService.findBycouNum(couNum));
		}
		return NONE;
	}

	// ajax楠岃瘉鐝骇鍚嶇О鏄惁閲嶅
	private String couName;

	public String getCouName() {
		return couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

	public String checkcouName() throws Exception {
		logger.debug("checkcouName");
		System.out.println("checkcouName:" + couName);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		if (couId == null) {// 鏂板鐩存帴楠岃瘉鐢ㄦ埛鍚嶆槸鍚﹂噸澶�
			System.out.println(courseService.findBycouName(couName));
			out.print(courseService.findBycouName(couName));
		}
		return NONE;
	}
}
