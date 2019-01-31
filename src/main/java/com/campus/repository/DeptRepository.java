package com.campus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.model.Dept;

public interface DeptRepository extends JpaRepository<Dept, Long> {

	
	Dept findByDeptCode(String deptCode);
	
}

