package com.campus.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.common.utils.EncryptUtil;
import com.campus.common.utils.ErrorResponseUtil;
import com.campus.common.utils.StringUtil;
import com.campus.model.User;
import com.campus.service.UserService;

@Controller
@RequestMapping(value="/aec")
public class AdminElseController {
	 @Autowired
	    private UserService userService;
	 /**
	     * 管理员注册
	     *
	     * @param request
	     */
	    @RequestMapping(value="/manageregistering" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	    @ResponseBody
	    public String registering(HttpServletRequest request){
	        // 获取传递过来的参数
	        String email = request.getParameter("email");
	        String userName = request.getParameter("userName");
	        String dept_code = request.getParameter("dept_code");
	        String password = request.getParameter("password");
	        String passwordConfirm = request.getParameter("passwordConfirm");
	        
	        
	        // 验证参数是否合法
	        if(StringUtil.isEmpty(email)) {
	            return ErrorResponseUtil.setResponse("400", "Email can not be null");
	        }
	       
	        if(StringUtil.isEmpty(password)) {
	            return ErrorResponseUtil.setResponse("400", "Password can not be null");
	        }
	        if(StringUtil.isEmpty(passwordConfirm)) {
	            return ErrorResponseUtil.setResponse("400", "Confirm password can not be null");
	        }
	        if(!password.equals(passwordConfirm)){
	            return ErrorResponseUtil.setResponse("400", "Two passwords are not same");
	        }

	        // 验证用户名是否已经注册过
	        User user ;
	        try {
	            user = userService.findByEmail(email);
	        }catch(Exception e){
	            user = null;
	        }
	        if(user != null){
	            return ErrorResponseUtil.setResponse("400", "The email is registered, please turn to login");
	        }
	        // md5 加密
	        String md5Pwd = EncryptUtil.getMD5(password);
	        // sha-1 加密
	        String shaPwd;
	        try {
	            shaPwd = EncryptUtil.encryptSHA(password) ;
	        } catch (NoSuchAlgorithmException e) {
	            return ErrorResponseUtil.setResponse("400", "The password can not be encrypted");
	        }
	        
	        // 将参数写入新模型
	        Date date = new Date();
	        User newUser = new User();
	        
	        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
	        
	        newUser.setEmail(email);
	        newUser.setUserName(userName);
	        newUser.setDeptCode(dept_code);
	        newUser.setPassword(shaPwd);
	        
	        // 验证用户类型，设置用户组
	        newUser.setUserGroup("alu");
	        
	        newUser.setCreateTime(date);
	        newUser.setUpdateTime(date);
	        
	        // 将新用户写入数据库
	        try {
	            userService.insert(newUser);
	        }catch(Exception e){
	            return ErrorResponseUtil.setResponse("400", e.getMessage());
	        }
	        
	        return ErrorResponseUtil.setResponse("200", "register success");
	        
	    }
	    
	    /**
	     * 根据id删除管理员
	     */
	    @RequestMapping(value = "/delete", method = RequestMethod.GET)
	    //@ResponseBody
	    public void goDelete(HttpServletRequest request ,HttpServletResponse res) {
//	        int userid = Integer.valueOf(request.getParameter("userid"));
	        String id = request.getParameter("id");
	      System.out.println("1111"+id);
	        userService.delete(id);
	       // return "login/man/Maintence";
	    	//http://localhost:8080/admin/Maintence
	    		//return "redirect:admin/Maintence";
	        try{
	    	res.sendRedirect("http://localhost:8080/admin/Maintence");}catch(Exception e){
	    		
	    	}
	    }
	    
	    /**
	     * 将管理员密码置为6个零
	     */
	 
	    @RequestMapping(value = "/change", method = RequestMethod.GET)
	    public void goChange(HttpServletRequest request ,HttpServletResponse res) {
//	        int userid = Integer.valueOf(request.getParameter("userid"));
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
		    	res.sendRedirect("http://localhost:8080/admin/Maintence");}catch(Exception e){
		    		
		    	}
	    }
	    
	  
	    
	    
	
}
