package com.zxc.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zxc.entities.Course;
import com.zxc.entities.Score;
import com.zxc.entities.Student;
import com.zxc.service.ICourseService;
import com.zxc.service.IScoreService;
import com.zxc.service.IStudentService;

public class ScoreServiceTest extends BaseServiceTest {
	@Autowired
	IScoreService scoreService;
	IStudentService studentService;
	ICourseService courseService;

	@Test
	public void test() {
		Score score = new Score();
		score.setStudent(new Student(11L));
		score.setCourse(new Course(5L));
		score.setScoNum(new BigDecimal(85.4));
		scoreService.save(score);
	}

	@Test
	public void test1() {
		List<Object[]> list = scoreService.findMaxMin();
		System.out.println(list.size());
		System.out.println(Arrays.toString(list.get(0)));
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
	}

	@Test
	public void test2() {
		String stuNum = "5";
		List<Object[]> list = scoreService.findStuCourse(stuNum);
		System.out.println(list.size());
		System.out.println(Arrays.toString(list.get(0)));
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
	}
	
	@Test
	public void test3() {
		String stuNum = "5";
		BigDecimal decimal = scoreService.findTotalcouCre(stuNum);
		System.out.println(decimal);
	}
}
