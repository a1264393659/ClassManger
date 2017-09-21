package com.zxc.service;

import com.zxc.entities.Course;

public interface ICourseService extends IBaseService<Course> {

	boolean findBycouNum(String couNum);

	boolean findBycouName(String couName);
	
}
