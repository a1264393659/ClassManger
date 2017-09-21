package com.zxc.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.zxc.entities.Clas;
import com.zxc.entities.Student;
import com.zxc.query.PageList;
import com.zxc.query.StudentQuery;
import com.zxc.service.IClasService;
import com.zxc.service.IStudentService;

public class StudentAction extends CRUDAction<Student> {
	private IStudentService studentService;
	private IClasService clasService;
	private Student student;
	private Clas clas;
	private PageList pageList;
	private StudentQuery baseQuery = new StudentQuery();
	private Long stuId;

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public void setClasService(IClasService clasService) {
		this.clasService = clasService;
	}

	@Override
	protected void list() throws Exception {
		logger.debug("list");
		this.pageList = studentService.findByQuery(baseQuery);
		putContext("allClas", clasService.getAll());
	}

	@Override
	public String input() throws Exception {
		logger.debug("input");
		putContext("allClas", clasService.getAll());
		return INPUT;
	}

	@Override
	@InputConfig(methodName = Action.INPUT)
	public String save() throws Exception {
		logger.debug("save");
		// 濡傛灉娌℃湁閫夋嫨鐝骇
		Clas clas = student.getClas();
		if (clas != null && clas.getClasId() == -1) {
			student.setClas(null);
		}
		if (stuId == null) {
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
			studentService.save(student);
		} else {
			studentService.update(student);
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
			if (stuId != null) {
				studentService.delete(stuId);
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
		if (stuId != null) {
			student = studentService.get(stuId);
		}
	}

	@Override
	public void prepareSave() throws Exception {
		if (stuId != null) {
			// 淇濆瓨淇敼鍚庢病鏈夊嚭鐜板湪jsp椤甸潰鐨勫睘鎬т笉涓㈠�?
			student = studentService.get(stuId);
			// 姝ゆ椂Clas鏄寔涔呯姸鎬�
			// �?��彉student鐨凜las涓嶈兘鏄寔涔呯姸鎬�?			student.setClas(null);
		} else {
			// 鏂板鍚庣殑淇濆�?
			student = new Student();
		}
	}

	@Override
	public Student getModel() {
		return student;
	}

	public StudentQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(StudentQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public Long getStuId() {
		return stuId;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	public PageList getPageList() {
		return pageList;
	}

	// ajax楠岃瘉�?�?��鏄惁閲嶅
	private String stuNum;

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public Clas getClas() {
		return clas;
	}

	public void setClas(Clas clas) {
		this.clas = clas;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String checkstuNum() throws Exception {
		logger.debug("checkstuNum");
		System.out.println("checkstuNum:" + stuNum);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		if (stuId == null) {// 鏂板鐩存帴楠岃瘉鐢ㄦ埛鍚嶆槸鍚﹂噸澶�
			System.out.println(studentService.findBystuNum(stuNum));
			out.print(studentService.findBystuNum(stuNum));
		}
		return NONE;
	}

}
