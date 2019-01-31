package com.campus.service.impl;

import org.springframework.stereotype.Service;

import com.campus.common.ConstantMsg;
import com.campus.model.User;
import com.campus.repository.UserRepository;
import com.campus.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
	public void delete(String id) {
        User user = userRepository.findById(id);
        if (user != null){
            userRepository.delete(user);
        }
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }
    
    @Override
    public  User findByEmail(String email) {
    	return userRepository.findByEmail(email);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByUserGroup(String userGroup) {
    	return userRepository.findByUserGroup(userGroup);
    }
    
    @Override
    public List<User> findByUserGroupAndDeptCode(String userGroup, String deptCode) {
    	return userRepository.findByUserGroupAndDeptCode(userGroup, deptCode);
    };
}
