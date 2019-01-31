package com.campus.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.campus.common.utils.EncryptUtil;
import com.campus.common.utils.ErrorResponseUtil;
import com.campus.model.User;
import com.campus.service.UserService;

@Controller
@RequestMapping(value = "/SAT")
public class SATController {
	@Autowired
    private UserService userService;
	
	  /**
     * 根据id删除老师或者学生
     */
    @RequestMapping(value = "/deleteSAT", method = RequestMethod.GET)
    public void goDeleteSAT(HttpServletRequest request ,HttpServletResponse res) {
//        int userid = Integer.valueOf(request.getParameter("userid"));
        String id = request.getParameter("id");
        userService.delete(id);
       // return "login/man/Maintence";
        try{
	    	res.sendRedirect("http://localhost:8080/admin/Change");}catch(Exception e){
	    		
	    	}
    }
    
    /**
     * 将学生或者老师密码置为6个零
     */
 
    @RequestMapping(value = "/changeSAT", method = RequestMethod.GET)
    public void goChangeSAT(HttpServletRequest request ,HttpServletResponse res) {
//        int userid = Integer.valueOf(request.getParameter("userid"));
        String id = request.getParameter("id");
        User user=userService.findById(id);
       
        // sha-1 加密
        String shaPwd=null;
        try {
            shaPwd = EncryptUtil.encryptSHA("000000") ;
        } catch (NoSuchAlgorithmException e) {
           
        }
        user.setPassword(shaPwd);
        userService.insert(user);
       // return "login/man/Maintence";
        try{
	    	res.sendRedirect("http://localhost:8080/admin/Change");}catch(Exception e){
	    		
	    	}
    }
}
