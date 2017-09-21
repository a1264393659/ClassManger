package com.zxc.service;

import com.zxc.entities.Clas;

public interface IClasService extends IBaseService<Clas> {
	
	boolean findByclasNum(String clasNum);
	
	boolean findByclasName(String clasName);
	
}
