package com.campus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.campus.service.UserService;

/**
 * 
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController11 {

	
    @Autowired
    private UserService userService;
    
    
    /**
     * 跳转到管理员主页面
     */
    @RequestMapping(value="/index" ,method = RequestMethod.GET)
    public String index(){
        return "admin/index";
    }
    
    
//    /**
//     * 跳转到院系管理页面
//     */
//    @RequestMapping(value="/dept" ,method = RequestMethod.GET)
//    public String dept(){
//        return "admin/dept";
//    }
    
}
