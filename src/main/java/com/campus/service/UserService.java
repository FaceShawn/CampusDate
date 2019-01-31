package com.campus.service;

import java.util.List;

import com.campus.model.User;

/**
 * 
 */
public interface UserService {

    void insert(User user);

    void update(User user);

    void delete(String id);

    User findById(String id);
    
    User findByEmail(String email);

    User findByUserName(String userName);

    List<User> findAll();
    
    List<User> findByUserGroup(String userGroup);
    
    List<User> findByUserGroupAndDeptCode(String userGroup, String deptCode);
}
