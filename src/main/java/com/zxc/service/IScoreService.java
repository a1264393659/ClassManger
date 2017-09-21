package com.zxc.service;

import java.math.BigDecimal;
import java.util.List;

import com.zxc.entities.Score;

public interface IScoreService extends IBaseService<Score> {
	List<Object[]> findMaxMin();

	List<Object[]> findMaxMin(Long couId);

	List<Object[]> findStuCourse(String stuNum);
	
	BigDecimal findTotalcouCre(String stuNum);
	
	Score findBystucou(String stuNum,String couName);
}
