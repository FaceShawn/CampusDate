package com.campus.service;

import java.util.List;

import com.campus.model.Dept;

public interface DeptService {


	Dept findByDeptCode(String deptCode);
	    
	List<Dept> findAll();

}

