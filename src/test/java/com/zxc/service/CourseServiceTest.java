package com.zxc.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zxc.service.ICourseService;
import com.zxc.service.IScoreService;

public class CourseServiceTest extends BaseServiceTest{
	@Autowired
	ICourseService courseService;
	IScoreService scoreService;
	
	@Test
	public void test() {
		
	}
}
