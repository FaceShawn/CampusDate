package com.campus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.campus.model.Dept;
import com.campus.repository.DeptRepository;
import com.campus.service.DeptService;


/**
 * 
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptRepository deptRepository;

    @Override
    public  Dept findByDeptCode(String deptCode) {
        return deptRepository.findByDeptCode(deptCode);
    }

    @Override
    public List<Dept> findAll() {
        return deptRepository.findAll();
    }
}
